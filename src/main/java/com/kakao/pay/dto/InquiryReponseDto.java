package com.kakao.pay.dto;

import java.util.List;

public class InquiryReponseDto {

	private String spreadTime;
	private int spreadMoney;
	private int receivedTotalMoney;
	private List<ReceivedUserDto> receivedUserList;
	
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
