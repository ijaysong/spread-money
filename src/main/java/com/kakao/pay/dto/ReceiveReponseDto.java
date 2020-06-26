package com.kakao.pay.dto;

public class ReceiveReponseDto {

	private int receivedMoney;

	public int getReceivedMoney() {
		return receivedMoney;
	}

	public void setReceivedMoney(int receivedMoney) {
		this.receivedMoney = receivedMoney;
	}

	@Override
	public String toString() {
		return "ReceiveReponseDto [receivedMoney=" + receivedMoney + "]";
	}
	
}
