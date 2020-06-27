package com.kakao.pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kakao.pay.domain.ChatRoom;

/*
 * 채팅방 Mapper
 */
@Mapper
public interface ChatRoomMapper {

	/**
	 * 채팅방 ID로 채팅방 정보를 추출
	 * 
	 * @param chatRoomId 채팅방 ID
	 * @return
	 */
	List<ChatRoom> findByChatRoomId(String chatRoomId);

}
