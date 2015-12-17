package com.framework.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.framework.log.LogSystem;

public class MD5 {

	/**
	 * 16位
	 * 
	 * @param plainText
	 * @return
	 */
	public static String md5Of16(String plainText) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			LogSystem.error(e, "");
		}
		return result;
	}

	/**
	 * 32位
	 * 
	 * @param plainText
	 * @return
	 */
	public static String md5Of32(String plainText) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			LogSystem.error(e, "");
		}
		return result;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String getMd5(String str){
		String result = "";
		try {
			MessageDigest alga = MessageDigest.getInstance("MD5");
			alga.update(str.getBytes());
			byte digesta[] = alga.digest();
			StringBuilder sb = new StringBuilder();
			for (int n = 0; n < digesta.length; n++) {
				String stmp = Integer.toHexString(digesta[n] & 0xff);
				if (stmp.length() == 1) {
					sb.append("0");
				}
				sb.append(stmp);
			}
			return sb.toString().toUpperCase();
		}
		catch (Exception e) {
			LogSystem.error(e, "");
		}
		return result;
	}
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(getMd5("1644451728ddca5670f5d89ec012ff4c"));
	}
}
