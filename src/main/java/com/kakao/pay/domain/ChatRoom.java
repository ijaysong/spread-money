package com.kakao.pay.domain;

/**
 * 채팅방에 대한 정보를 담는 클래스
 */
public class ChatRoom {
	
	private int chatRoomId; //채팅방 ID
	private int userId; // 유저 ID
	
	public int getChatRoomId() {
		return chatRoomId;
	}
	
	public void setChatRoomId(int chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ChatRoom [chatRoomId=" + chatRoomId + ", userId=" + userId + "]";
	}
	
}
