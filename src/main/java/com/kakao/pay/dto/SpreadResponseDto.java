package com.kakao.pay.dto;

/*
 * 뿌리기 API의 Response API
 */
public class SpreadResponseDto {
	
	private String token; // 토큰

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "SpreadResponseDto [token=" + token + "]";
	}
	
}
