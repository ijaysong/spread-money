package com.kakao.pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kakao.pay.domain.ChatRoom;

@Mapper
public interface ChatRoomMapper {

	List<ChatRoom> findByChatRoomId(String chatRoomId);

}
