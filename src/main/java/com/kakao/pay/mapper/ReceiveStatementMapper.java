package com.kakao.pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kakao.pay.domain.ReceiveStatement;
import com.kakao.pay.dto.ReceiveReponseDto;

@Mapper
public interface ReceiveStatementMapper {

	void insertReceiveStatementInfo(List<ReceiveStatement> receiveSmtList);

	List<ReceiveStatement> findByTokenAndReceiveUserId(String token);

	boolean updateReceiveUserId(String token, int receiveIndex, int userId);

	List<ReceiveStatement> findByToken(String token);
}
