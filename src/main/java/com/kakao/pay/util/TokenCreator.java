package com.kakao.pay.util;

import java.util.Random;

public class TokenCreator {

	private static final String[] RANDOM_TEMPLATE = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"
														+ "abcdefghijklmnopqrstuvwxyz"
														+ "1234567890").split("");
	
	private static final int LENGTH = 3;
	
	public static String createToken() {
		
		StringBuilder sb = new StringBuilder(LENGTH);
		Random random = new Random();
		
		for (int i = 0; i < LENGTH; i++) {
			int idx = random.nextInt(RANDOM_TEMPLATE.length);
		    String chosen = RANDOM_TEMPLATE[idx];
		    sb.append(chosen);
		}
		
		return sb.toString();
	}
}
