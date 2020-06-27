package com.kakao.pay.domain;

/**
 * 받기 내역에 대한 정보를 담는 클래스
 */
public class ReceiveStatement {

	private int id; // ID
	private String token; // 토큰
	private int receiveIndex; // 뿌릴 인원에 대한 인덱스
	private int receiveMoney; // 뿌릴 금액(1/n)
	private int receiveUserId; // 받은 사용자 ID
	
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
	
	public int getReceiveIndex() {
		return receiveIndex;
	}
	
	public void setReceiveIndex(int receiveIndex) {
		this.receiveIndex = receiveIndex;
	}
	
	public int getReceiveMoney() {
		return receiveMoney;
	}
	
	public void setReceiveMoney(int receiveMoney) {
		this.receiveMoney = receiveMoney;
	}
	
	public int getReceiveUserId() {
		return receiveUserId;
	}
	
	public void setReceiveUserId(int receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	@Override
	public String toString() {
		return "ReceiveStatement [id=" + id + ", token=" + token + ", receiveIndex=" + receiveIndex + ", receiveMoney="
				+ receiveMoney + ", receiveUserId=" + receiveUserId + "]";
	}
	
}
