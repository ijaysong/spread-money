package com.kakao.pay.util;

import java.util.Random;

/*
 * 토큰 생성
 */
public class TokenCreator {

	private static final String[] RANDOM_TEMPLATE = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"
														+ "abcdefghijklmnopqrstuvwxyz"
														+ "1234567890").split(""); // 토큰 생성에 사용할 문자열
	
	private static final int LENGTH = 3;
	
	/**
	 * 토큰 생성
	 * 
	 * @return
	 */
	public static String createToken() {
		
		StringBuilder sb = new StringBuilder(LENGTH);
		Random random = new Random();
		
		for (int i = 0; i < LENGTH; i++) {
			
			// 토큰 생성에 사용할 문자열 중, 임의의 문자를 선택한다. 
			int idx = random.nextInt(RANDOM_TEMPLATE.length);
		    String chosen = RANDOM_TEMPLATE[idx];
		    
		    // 선택한 문자열을 StringBuilder에 더한다.
		    sb.append(chosen);
		}
		
		return sb.toString();
	}
}
