package com.kakao.pay.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.pay.domain.ChatRoom;
import com.kakao.pay.domain.ReceiveStatement;
import com.kakao.pay.domain.Spread;
import com.kakao.pay.dto.InquiryReponseDto;
import com.kakao.pay.dto.ReceiveReponseDto;
import com.kakao.pay.dto.ReceivedUserDto;
import com.kakao.pay.dto.SpreadResponseDto;
import com.kakao.pay.mapper.ChatRoomMapper;
import com.kakao.pay.mapper.ReceiveStatementMapper;
import com.kakao.pay.mapper.SpreadMapper;
import com.kakao.pay.util.TokenCreator;

/*
 * 뿌리기 기능 ServiceImpl
 */
@Service
public class SpreadServcieImpl implements SpreadService {
	
	@Autowired
	private SpreadMapper spreadMapper; // 뿌리기 Mapper
	
	@Autowired
	private ReceiveStatementMapper receiveStatementMapper; // 받기 내역 Mapper
	
	@Autowired
	private ChatRoomMapper chatRoomMapper; // 채팅방 Mapper
	
	private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 날짜 시간 포맷

	/**
	 * 뿌리기 정보 등록
	 * 
	 * @param userId 사용자 ID
	 * @param chatRoomId 채팅방 ID
	 * @param spreadTotalMoney 뿌릴 금액
	 * @param spreadTotalNum 뿌릴 인원
	 * 
	 */
	@Transactional
	public SpreadResponseDto issueToken(int userId, String chatRoomId, int spreadTotalMoney, int spreadTotalNum) {
		// 토큰 생성
		String token = decideToken();
		
		// 도메인 객체 생성
		Spread newSpread = new Spread();
		newSpread.setToken(token);
		newSpread.setChatRoomId(chatRoomId);
		newSpread.setSpreadTotalMoney(spreadTotalMoney);
		newSpread.setSpreadUserId(userId);
		
		// 인원수에 맞게 배분
		List<Integer> devidedMoneyList = divideMoney(spreadTotalMoney, spreadTotalNum);
		
		// 도메인 객체 생성
		List<ReceiveStatement> receiveSmtList = createReceiveStatementList(token, devidedMoneyList);
		
		// 뿌리기 정보 등록
		spreadMapper.insertSpreadInfo(newSpread);
		
		// 받기 내역 등록
		receiveStatementMapper.insertReceiveStatementInfo(receiveSmtList);
		
		SpreadResponseDto srDto = new SpreadResponseDto();
		srDto.setToken(token);
		
		return srDto;
	}
	
	/**
	 * DB에 이미 등록된 토큰인지 확인
	 * 
	 * @return
	 */
	private String decideToken() { 
		String token;
		Spread result;
		
		do {
			token = TokenCreator.createToken();
			result = spreadMapper.findByToken(token);
		} while (result != null);
		
		return token;
	}
	
	/**
	 * 금액 분배
	 * 
	 * @param spreadTotalMoney 뿌릴 금액
	 * @param spreadTotalNum 뿌릴 인원
	 * @return
	 */
	private List<Integer> divideMoney(int spreadTotalMoney, int spreadTotalNum) {
		int portion = spreadTotalMoney / spreadTotalNum;
		int remainder = spreadTotalMoney % spreadTotalNum;
		
		List<Integer> list = new ArrayList<>(spreadTotalNum);
		for(int i = 0; i < spreadTotalNum; i++) {
			int val = remainder > 0 ? portion + 1 : portion;
			remainder--;
			list.add(i, val);
		}
		
		return list;
	}
	
	/**
	 * 받기 내역 리스트 생성
	 * 
	 * @param token 토큰
	 * @param devidedMoneyList 분배된 금액
	 * @return
	 */
	private List<ReceiveStatement> createReceiveStatementList(
			String token, List<Integer> devidedMoneyList) {
		int size = devidedMoneyList.size();
		
		List<ReceiveStatement> receiveSmtList = new ArrayList<>(size); 
		
		for(int i = 0; i < size; i++) {
			ReceiveStatement newReceiveSmt = new ReceiveStatement();
			newReceiveSmt.setToken(token);
			newReceiveSmt.setReceiveIndex(i);
			newReceiveSmt.setReceiveMoney(devidedMoneyList.get(i));
			receiveSmtList.add(newReceiveSmt);
		}
		
		return receiveSmtList;
	}
	
	/**
	 * 뿌려진 돈 받기
	 * 
	 * @param userId 유저 ID
	 * @param chatRoomId 채팅방 ID
	 * @param token 토큰
	 * @return
	 * @exception Exception
	 */
	@Transactional
	public ReceiveReponseDto allocateMoney(int userId, String chatRoomId, String token) throws Exception {
		
		// 토큰으로 받기 내역 추출
		List<ReceiveStatement> receiveSmtList = receiveStatementMapper.findByToken(token);
		
		// 이미 받은 사용자인지 체크
		boolean isUsed = receiveSmtList.stream().anyMatch(rs -> rs.getReceiveUserId() == userId);
		if(isUsed) {
			throw new Exception("뿌리기 당 한 사용자는 한번만 받을 수 있습니다.");
		}
		
		// 자신이 뿌린 유저인지 체크
		Spread spread = spreadMapper.findByToken(token);
		if (spread.getSpreadUserId() == userId) {
			throw new Exception("자신이 뿌리기한 건은 자신이 받을 수 없습니다.");
		}
		
		// 뿌리기 실행된 채팅방에 속한 사용자인지 체크
		List<ChatRoom> chatRoomList = chatRoomMapper.findByChatRoomId(chatRoomId);
		boolean isIn = chatRoomList.stream().anyMatch(cr -> cr.getUserId() == userId);
		if (!isIn) {
			throw new Exception("뿌리기가 실행된 대화방과 동일한 대화방에 속한 사용자만이 받을 수 있습니다.");
		}
		
		// 뿌리기가 실행된지 10분이내인지 체크
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime spreadTime = LocalDateTime.parse(spread.getSpreadTime(), DATETIME_FORMATTER);
		if (spreadTime.plusMinutes(10).isBefore(today)) {
			throw new Exception("뿌린건에 대한 받기는 10분 동안 유효합니다.");
		}
		
		// 발급 되지 않은 뿌리기를 추출
		List<ReceiveStatement> notReceivedList = receiveSmtList.stream()
			.filter(rs -> rs.getReceiveUserId() == 0).collect(Collectors.toList());
		
		int receiveMoney = 0;
		
		for (ReceiveStatement rsRow : notReceivedList) {
			int receiveIndex = rsRow.getReceiveIndex();
			
			// 등록이 안되었으면, DB에 할당한다
			boolean isUpdated = receiveStatementMapper.updateReceiveUserId(token, receiveIndex, userId);
			
			if(isUpdated) {
				receiveMoney = rsRow.getReceiveMoney();
				break;
			}
		}
		
		ReceiveReponseDto rrDto = new ReceiveReponseDto();
		rrDto.setReceivedMoney(receiveMoney);
		
		return rrDto;
	}

	/**
	 * 받기 내역 조회
	 * 
	 * @param userId 사용자 ID
	 * @param token 토큰
	 * @return
	 * @exception Exception
	 */
	public InquiryReponseDto getInquiry(int userId, String token) throws Exception {
		
		// 뿌린 사람인지 체크
		Spread spread = spreadMapper.findByToken(token);
		if (spread.getSpreadUserId() != userId) {
			throw new Exception("뿌린 사람 자신만 조회를 할 수 있습니다.");
		}
		
		// 받기 내역에 대한 조회가 가능한 날짜인지 체크
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime spreadTime = LocalDateTime.parse(spread.getSpreadTime(), DATETIME_FORMATTER);
		if (spreadTime.plusDays(7).isBefore(today)) {
			throw new Exception("뿌린건에 대한 조회는 7일 동안 할 수 있습니다.");
		}
		
		// 토큰으로 받기 내역 정보 추출
		List<ReceiveStatement> newReceiveSmtList = receiveStatementMapper.findByTokenAndReceiveUserId(token);
		List<ReceivedUserDto> ruDtoList = newReceiveSmtList.stream()
			.map(rs -> {
				ReceivedUserDto rsDto = new ReceivedUserDto();
				rsDto.setReceivedMoney(rs.getReceiveMoney());
				rsDto.setUserId(rs.getReceiveUserId());
				return rsDto;
			}).collect(Collectors.toList());
		
		int receivedTotalMoney = newReceiveSmtList.stream()
				.mapToInt(rs -> rs.getReceiveMoney())
				.sum();
		
		InquiryReponseDto inquiryDto = new InquiryReponseDto();
		inquiryDto.setReceivedTotalMoney(receivedTotalMoney);
		inquiryDto.setReceivedUserList(ruDtoList);
		inquiryDto.setSpreadMoney(spread.getSpreadTotalMoney());
		inquiryDto.setSpreadTime(spread.getSpreadTime());
		
		return inquiryDto;
	}


	
}
