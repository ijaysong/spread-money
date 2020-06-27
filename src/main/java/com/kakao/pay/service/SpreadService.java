package com.kakao.pay.service;

import com.kakao.pay.dto.InquiryReponseDto;
import com.kakao.pay.dto.ReceiveReponseDto;
import com.kakao.pay.dto.SpreadResponseDto;

/*
 * 뿌리기 기능 Service
 */
public interface SpreadService {
	
	/**
	 * 뿌리기 API의 Service
	 * 
	 * @param userId 유저 ID
	 * @param chatRoomId 채팅방 ID
	 * @param spreadTotalMoney 뿌릴 금액
	 * @param spreadTotalNum 뿌릴 인원
	 * @return
	 */
	public SpreadResponseDto issueToken(int userId, String chatRoomId, int spreadTotalMoney, int spreadTotalNum);
	
	/**
	 * 받기 API의 Service
	 * 
	 * @param userId 유저 ID
	 * @param chatRoomId 채팅방 ID
	 * @param token 토큰
	 * @return
	 * @throws Exception
	 */
	public ReceiveReponseDto allocateMoney(int userId, String chatRoomId, String token) throws Exception;
	
	/**
	 * 조회 API의 Service
	 * 
	 * @param userId 유저 ID
	 * @param token 토큰
	 * @return
	 * @throws Exception
	 */
	public InquiryReponseDto getInquiry(int userId, String token) throws Exception;
}
