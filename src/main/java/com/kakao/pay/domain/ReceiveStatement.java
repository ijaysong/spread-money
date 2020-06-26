package com.kakao.pay.domain;

public class ReceiveStatement {

	private int id;
	private String token;
	private int receiveIndex;
	private int receiveMoney;
	private int receiveUserId;
	
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
