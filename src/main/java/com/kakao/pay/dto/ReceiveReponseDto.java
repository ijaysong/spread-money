package com.kakao.pay.dto;

/*
 * 받기 API의 Response DTO
 */
public class ReceiveReponseDto {

	private int receivedMoney; // 받은 금액

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
