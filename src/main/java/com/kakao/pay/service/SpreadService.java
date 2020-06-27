package com.kakao.pay.service;

import com.kakao.pay.dto.InquiryReponseDto;
import com.kakao.pay.dto.ReceiveReponseDto;
import com.kakao.pay.dto.SpreadResponseDto;

public interface SpreadService {
	public SpreadResponseDto issueToken(int userId, int chatRoomId, int spreadTotalMoney, int spreadTotalNum);
	public ReceiveReponseDto allocateMoney(int userId, String chatRoomId, String token) throws Exception;
	public InquiryReponseDto getInquiry(int userId, String token) throws Exception;
}
