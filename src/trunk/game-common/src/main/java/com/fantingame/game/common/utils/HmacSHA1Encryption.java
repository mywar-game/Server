package com.fantingame.game.common.utils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HmacSHA1Encryption {
	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";
	
	public static String HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception{
		byte[] data = encryptKey.getBytes(ENCODING);
		SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
		Mac mac = Mac.getInstance(MAC_NAME);
		mac.init(secretKey);
		byte[] text = encryptText.getBytes(ENCODING);
		byte[] digest = mac.doFinal(text);
		StringBuilder sBuilder = bytesToHexString(digest);
		return sBuilder.toString();
	}
	
	public static StringBuilder bytesToHexString(byte[] bytesArray){
		if(bytesArray == null){
			return null;
		}
		
		StringBuilder sBuilder = new StringBuilder();
		for(byte b : bytesArray){
			String hv = String.format("%02x", b);
			sBuilder.append(hv);
		}
		
		return sBuilder;
	}
}
