package com.kakao.pay.dto;

public class ReceivedUserDto {
	
	private int receivedMoney;
	private int userId;
	
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
