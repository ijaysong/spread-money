package com.kakao.pay.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kakao.pay.domain.Spread;

/*
 * 뿌리기 Mapper
 */
@Mapper
public interface SpreadMapper {

	/**
	 * 뿌리기 정보 등록
	 * 
	 * @param newSpread 뿌리기 정보
	 */
	void insertSpreadInfo(Spread newSpread);

	/**
	 * 토큰으로 뿌리기 정보 추출
	 * 
	 * @param token 토큰
	 * @return
	 */
	Spread findByToken(String token);

	/**
	 * 뿌리기 정보 전체 삭제
	 */
	void removeAll();
}
