package com.kakao.pay.domain;

public class Spread {

	private int id;
	private String token;
	private int spreadUserId;
	private int chatRoomId;
	private String spreadTime;
	private int spreadTotalMoney;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public int getSpreadUserId() {
		return spreadUserId;
	}
	
	public void setSpreadUserId(int spreadUserId) {
		this.spreadUserId = spreadUserId;
	}
	
	public int getChatRoomId() {
		return chatRoomId;
	}
	
	public void setChatRoomId(int chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	
	public String getSpreadTime() {
		return spreadTime;
	}
	
	public void setSpreadTime(String spreadTime) {
		this.spreadTime = spreadTime;
	}
	
	public int getSpreadTotalMoney() {
		return spreadTotalMoney;
	}
	
	public void setSpreadTotalMoney(int spreadTotalMoney) {
		this.spreadTotalMoney = spreadTotalMoney;
	}
	
	@Override
	public String toString() {
		return "Spread [id=" + id + ", token=" + token + ", spreadUserId=" + spreadUserId + ", chatRoomId=" + chatRoomId
				+ ", spreadTime=" + spreadTime + ", spreadTotalMoney=" + spreadTotalMoney + "]";
	}
	
}
