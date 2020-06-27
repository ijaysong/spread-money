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

@RestController
public class SpreadRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(SpreadRestController.class);
	
	@Autowired
	private SpreadService service;
	
	@PostMapping("/spreading/money/{money}/num/{num}")
	public ResponseEntity<SpreadResponseDto> spread(@RequestHeader("X-USER-ID") int userId, 
			@RequestHeader("X-ROOM-ID") String chatRoomId,
			@PathVariable("money") int spreadTotalMoney,
			@PathVariable("num") int spreadTotalNum) {
		
		logger.info("뿌리기 API 시작");
		
		SpreadResponseDto result = service.issueToken(userId, chatRoomId, spreadTotalMoney, spreadTotalNum);
		ResponseEntity<SpreadResponseDto> entity = new ResponseEntity<>(result, HttpStatus.OK);
		
		logger.info("뿌리기 API 종료");
		
		return entity;
	}
	
	@PostMapping("/receiving/token/{token}")
	public ResponseEntity<ReceiveReponseDto> receive(@RequestHeader("X-USER-ID") int userId,
			@RequestHeader("X-ROOM-ID") String chatRoomId,
			@PathVariable("token") String token) throws Exception {
		
		logger.info("받기 API 시작");
		
		ReceiveReponseDto result = service.allocateMoney(userId, chatRoomId, token);
		ResponseEntity<ReceiveReponseDto> entity = new ResponseEntity<>(result, HttpStatus.OK);
		
		logger.info("받기 API 종료");
		
		return entity;
	}
	
	@GetMapping("/inquiry/token/{token}")
	public ResponseEntity<InquiryReponseDto> inquiry(@RequestHeader("X-USER-ID") int userId,
			@PathVariable("token") String token) throws Exception {
		
		logger.info("조회 API 시작");
		
		InquiryReponseDto result = service.getInquiry(userId, token);
		ResponseEntity<InquiryReponseDto> entity = new ResponseEntity<InquiryReponseDto>(result, HttpStatus.OK);
		
		logger.info("조회 API 종료");
		
		return entity;
	}
}
