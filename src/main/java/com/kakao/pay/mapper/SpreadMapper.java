package com.kakao.pay.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kakao.pay.domain.Spread;

@Mapper
public interface SpreadMapper {

	void insertSpreadInfo(Spread newSpread);

	Spread findByToken(String token);
}
