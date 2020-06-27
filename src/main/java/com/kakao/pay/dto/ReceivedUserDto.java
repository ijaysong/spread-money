package com.kakao.pay.dto;

/*
 * 받기 완료된 정보의 Response DTO
 */
public class ReceivedUserDto {
	
	private int receivedMoney; // 받은 금액
	private int userId; // 받은 사용자 아이디
	
	public int getReceivedMoney() {
		return receivedMoney;
	}
	
	public void setReceivedMoney(int receivedMoney) {
		this.receivedMoney = receivedMoney;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ReceivedUserDto [receivedMoney=" + receivedMoney + ", userId=" + userId + "]";
	}
	
}
