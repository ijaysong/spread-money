package com.kakao.pay.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kakao.pay.domain.ReceiveStatement;
import com.kakao.pay.domain.Spread;
import com.kakao.pay.dto.InquiryReponseDto;
import com.kakao.pay.dto.ReceiveReponseDto;
import com.kakao.pay.dto.SpreadResponseDto;
import com.kakao.pay.mapper.ReceiveStatementMapper;
import com.kakao.pay.mapper.SpreadMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpreadRestControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private SpreadMapper spreadMapper;
	
	@Autowired
	private ReceiveStatementMapper receiveStatementMapper;
	
	private HttpEntity<String> request;
	
	private int userId = 1;
	private String chatRoomId = "1";
	
	/**
	 * 헤더를 세팅한 기본 리퀘스트를 만든다
	 */
	@BeforeEach
	public void createRequest() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-USER-ID", userId+"");
		headers.set("X-ROOM-ID", chatRoomId);
		request = new HttpEntity<>("", headers);
	}
	
	/**
	 * 테스트 시작 전에 DB를 청소한다
	 */
	@BeforeEach
	public void cleanup() {
		spreadMapper.removeAll();
		receiveStatementMapper.removeAll();
	}
	
	@Test
	public void spread_whenRequested() {
		// 준비
		int amount = 5000;
		int number = 3;
		
		// 실행
		ResponseEntity<SpreadResponseDto> responseEntity = restTemplate
				.postForEntity("/spreading/money/{money}/num/{num}", 
						request, SpreadResponseDto.class, amount, number);
				
		// 단언
		assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
	}
	
	@Test
	public void receive_whenRequested() {
		// 준비
		int otherUserId = 2;
		int spreadTotalMoney = 1000;
		String token = "AAA";
		
		// Spread 준비
		insertSpreadToDB(token, otherUserId, spreadTotalMoney);
		
		// ReceiveStatement 준비
		insertReceiveStatementListToDB(token, spreadTotalMoney);
	
		// 실행
		ResponseEntity<ReceiveReponseDto> responseEntity = restTemplate
				.postForEntity("/receiving/token/{token}", 
						request, ReceiveReponseDto.class, token);
		
		// 단언
		assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
	}
	
	@Test
	public void inquiry_whenRequested() {
		// 준비
		int spreadTotalMoney = 1000;
		String token = "AAA";
		
		// Spread 준비
		insertSpreadToDB(token, userId, spreadTotalMoney);
		
		// ReceiveStatement 준비
		insertReceiveStatementListToDB(token, spreadTotalMoney);
	
		// 실행
		ResponseEntity<InquiryReponseDto> responseEntity = restTemplate
				.exchange("/inquiry/token/{token}", HttpMethod.GET, request, InquiryReponseDto.class, token);
		
		// 단언
		assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
	}
	
	/**
	 * DB에 Spread 객체를 넣는다
	 * @param token 토큰
	 * @param userId 뿌린 사람 ID
	 * @param spreadTotalMoney 뿌린 금액
	 */
	private void insertSpreadToDB(String token, int userId, int spreadTotalMoney) {
		Spread spread = new Spread();
		spread.setToken(token);
		spread.setSpreadUserId(userId);
		spread.setChatRoomId(chatRoomId);
		spread.setSpreadTotalMoney(spreadTotalMoney);
		spreadMapper.insertSpreadInfo(spread);
	}
	
	/**
	 * DB에 ReceiveStatement 객체 하나가 든 리스트를 넣는다
	 * @param token 토큰
	 * @param spreadTotalMoney 뿌린 금액
	 */
	private void insertReceiveStatementListToDB(String token, int spreadTotalMoney) {
		List<ReceiveStatement> rsList = new ArrayList<>();
		ReceiveStatement rs = new ReceiveStatement();
		rs.setReceiveIndex(0);
		rs.setReceiveMoney(spreadTotalMoney);
		rs.setToken(token);
		rsList.add(rs);
		receiveStatementMapper.insertReceiveStatementInfo(rsList);
	}
}
