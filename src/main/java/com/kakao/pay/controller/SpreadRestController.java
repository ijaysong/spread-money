package com.kakao.pay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.pay.dto.InquiryReponseDto;
import com.kakao.pay.dto.ReceiveReponseDto;
import com.kakao.pay.dto.SpreadResponseDto;
import com.kakao.pay.service.SpreadService;
/**
 * 뿌리기 기능의 컨트롤러 
 */
@RestController
public class SpreadRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(SpreadRestController.class);
	
	@Autowired
	private SpreadService service;
	
	/**
	 * 뿌리기 API
	 * 
	 * @param userId 유저 ID
	 * @param chatRoomId 채팅방 ID
	 * @param spreadTotalMoney 뿌릴 금액
	 * @param spreadTotalNum 뿌릴 인원
	 * @return
	 */
	@PostMapping("/spreading/money/{money}/num/{num}")
	public ResponseEntity<SpreadResponseDto> spread(@RequestHeader("X-USER-ID") int userId, 
			@RequestHeader("X-ROOM-ID") String chatRoomId,
			@PathVariable("money") int spreadTotalMoney,
			@PathVariable("num") int spreadTotalNum) {
		
		logger.info("뿌리기 API 시작");
		
		// API 실행 
		SpreadResponseDto result = service.issueToken(userId, chatRoomId, spreadTotalMoney, spreadTotalNum);
		ResponseEntity<SpreadResponseDto> entity = new ResponseEntity<>(result, HttpStatus.OK);
		
		logger.info("뿌리기 API 종료");
		
		return entity;
	}
	
	/**
	 * 받기 API
	 * 
	 * @param userId 유저 ID
	 * @param chatRoomId 채팅방 ID
	 * @param token 토큰
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/receiving/token/{token}")
	public ResponseEntity<ReceiveReponseDto> receive(@RequestHeader("X-USER-ID") int userId,
			@RequestHeader("X-ROOM-ID") String chatRoomId,
			@PathVariable("token") String token) throws Exception {
		
		logger.info("받기 API 시작");
		
		// API 실행
		ReceiveReponseDto result = service.allocateMoney(userId, chatRoomId, token);
		ResponseEntity<ReceiveReponseDto> entity = new ResponseEntity<>(result, HttpStatus.OK);
		
		logger.info("받기 API 종료");
		
		return entity;
	}
	
	/**
	 * 조회 API
	 * 
	 * @param userId 유저 ID
	 * @param token 토큰
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/inquiry/token/{token}")
	public ResponseEntity<InquiryReponseDto> inquiry(@RequestHeader("X-USER-ID") int userId,
			@PathVariable("token") String token) throws Exception {
		
		logger.info("조회 API 시작");
		
		// API 실행
		InquiryReponseDto result = service.getInquiry(userId, token);
		ResponseEntity<InquiryReponseDto> entity = new ResponseEntity<InquiryReponseDto>(result, HttpStatus.OK);
		
		logger.info("조회 API 종료");
		
		return entity;
	}
}
