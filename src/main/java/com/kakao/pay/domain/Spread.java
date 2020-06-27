package com.kakao.pay.domain;

/**
 * 뿌리기에 대한 정보를 담는 클래스
 */
public class Spread {

	private int id; // ID
	private String token; // 토큰
	private int spreadUserId; // 뿌리기 한 사람의 ID
	private String chatRoomId; // 채팅방 ID
	private String spreadTime; // 뿌린 시간
	private int spreadTotalMoney; // 뿌린 금액
	
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
	
	public String getChatRoomId() {
		return chatRoomId;
	}
	
	public void setChatRoomId(String chatRoomId) {
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
