package com.kakao.pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kakao.pay.domain.ReceiveStatement;

@Mapper
public interface ReceiveStatementMapper {

	void insertReceiveStatementInfo(List<ReceiveStatement> receiveSmtList);
}
