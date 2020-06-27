package com.kakao.pay.domain;

public class ChatRoom {
	
	private int chatRoomId;
	private int userId;
	
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
