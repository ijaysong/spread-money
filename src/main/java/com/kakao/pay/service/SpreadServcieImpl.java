package com.kakao.pay.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.pay.domain.ReceiveStatement;
import com.kakao.pay.domain.Spread;
import com.kakao.pay.dto.InquiryReponseDto;
import com.kakao.pay.dto.ReceiveReponseDto;
import com.kakao.pay.dto.SpreadResponseDto;
import com.kakao.pay.mapper.ReceiveStatementMapper;
import com.kakao.pay.mapper.SpreadMapper;
import com.kakao.pay.util.TokenCreator;

@Service
public class SpreadServcieImpl implements SpreadService {
	
	@Autowired
	private SpreadMapper spreadMapper;
	
	@Autowired
	private ReceiveStatementMapper receiveStatementMapper;
	
	@Transactional
	public SpreadResponseDto issueToken(int userId, int chatRoomId, int spreadTotalMoney, int spreadTotalNum) {
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
	
	private String decideToken() { 
		String token;
		boolean result;
		
		do {
			token = TokenCreator.createToken();
			result = spreadMapper.selectTokenExistence(token);
		} while (result);
		
		return token;
	}
	
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
	
	
	public ReceiveReponseDto allocateMoney(int userId, String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public InquiryReponseDto getInquiry(int userId, String token) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
