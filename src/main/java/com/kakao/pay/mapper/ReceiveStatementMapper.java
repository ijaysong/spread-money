package com.kakao.pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kakao.pay.domain.ReceiveStatement;
import com.kakao.pay.dto.ReceiveReponseDto;

/*
 * 받기 내역 Mapper
 */
@Mapper
public interface ReceiveStatementMapper {

	/**
	 * 받기 내역 등록
	 * 
	 * @param receiveSmtList DB에 등록할 받기 내역 리스트
	 */
	void insertReceiveStatementInfo(List<ReceiveStatement> receiveSmtList);

	/**
	 * 토큰과 받은 유저 ID로 받기 내역 추출
	 * 
	 * @param token 토큰
	 * @return
	 */
	List<ReceiveStatement> findByTokenAndReceiveUserId(String token);

	/**
	 * 받은 유저 ID 업데이트
	 * 
	 * @param token 토큰
	 * @param receiveIndex 뿌릴 인원에 대한 인덱스
	 * @param userId 유저 ID
	 * @return
	 */
	boolean updateReceiveUserId(String token, int receiveIndex, int userId);

	/**
	 * 토큰으로 받기 내역 추출
	 * 
	 * @param token 토큰
	 * @return
	 */
	List<ReceiveStatement> findByToken(String token);

	/**
	 * 받기 내역 전체 삭제
	 */
	void removeAll();
}
