package com.kakao.pay.dto;

import java.util.List;

/*
 * 조회 API의 Response DTO
 */
public class InquiryReponseDto {

	private String spreadTime; // 뿌린 시간
	private int spreadMoney; // 뿌린 금액
	private int receivedTotalMoney; // 받기 완료된 금액
	private List<ReceivedUserDto> receivedUserList; // 받기 완료된 정보(받은 금액, 받은 사용자 아이디)
	
	public String getSpreadTime() {
		return spreadTime;
	}
	
	public void setSpreadTime(String spreadTime) {
		this.spreadTime = spreadTime;
	}
	
	public int getSpreadMoney() {
		return spreadMoney;
	}
	
	public void setSpreadMoney(int spreadMoney) {
		this.spreadMoney = spreadMoney;
	}
	
	public int getReceivedTotalMoney() {
		return receivedTotalMoney;
	}
	
	public void setReceivedTotalMoney(int receivedTotalMoney) {
		this.receivedTotalMoney = receivedTotalMoney;
	}
	
	public List<ReceivedUserDto> getReceivedUserList() {
		return receivedUserList;
	}
	
	public void setReceivedUserList(List<ReceivedUserDto> receivedUserList) {
		this.receivedUserList = receivedUserList;
	}

	@Override
	public String toString() {
		return "InquiryReponseDto [spreadTime=" + spreadTime + ", spreadMoney=" + spreadMoney
				+ ", receivedTotalMoney=" + receivedTotalMoney + ", receivedUserList=" + receivedUserList + "]";
	}

}
