package com.fantingame.game.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.google.common.base.Strings;

public class Constant {
	private static Logger LOG = Logger.getLogger(Constant.class);

	public static String ip = null;

	/**
	 * 获取字节数(一个中文相当于2个字节).
	 * 
	 * @param str
	 * @return
	 */
	public static int getBytes(String str) {
		if (Strings.isNullOrEmpty(str)) {
			return 0;
		}
		try {
			return str.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
			throw new NullPointerException("转换编码出错.");
		}
	}

	/**
	 * 整数转字节
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] intToBytes(int value) {
		int length = 4;
		byte[] bytes = new byte[length];
		bytes[3] = (byte) (value & 0xFF);
		bytes[2] = (byte) ((value >> 8) & 0xFF);
		bytes[1] = (byte) ((value >> 16) & 0xFF);
		bytes[0] = (byte) ((value >> 24) & 0xFF);
		return bytes;
	}

	/**
	 * 字节转整数
	 * 
	 * @param bytes
	 * @return
	 */
	public static int bytesToInt(byte[] bytes) {
		return (bytes[0] & 0xff) << 24 | (bytes[1] & 0xff) << 16 | (bytes[2] & 0xff) << 8 | (bytes[3] & 0xff) << 0;
	}

	/**
	 * 获取字符串HASHCODE
	 * 
	 * @param str
	 * @return
	 */
	public static long getHashCode(String str) {
		long h = 0;
		char val[] = str.toCharArray();
		for (int i = 0; i < val.length; i++) {
			System.out.println(val[i] + 0);
			h = 31 * h + val[i];
		}
		return Math.abs((long) h);
	}

	/**
	 * 是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (Strings.isNullOrEmpty(str)) {
			return false;
		}
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	// public static String getIp() {
	// if (ip != null) {
	// return ip;
	// }
	//
	// String[] prefixs = {"192.168.", "113.106."};
	// String ip = ServerUtil.getIp(prefixs);
	// if (ip == null) {
	// throw new NullPointerException("获取IP出错.");
	// }
	// return ip;
	// }
	//
	public static String urlEncode(String str) {
		if (str == null) {
			return null;
		}
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws SocketException {
		// System.out.println(Constant.getHashCode("fjdasokljfdaljokfwko"));
		System.out.println(getBytes("中文"));
		System.out.println(getBytes("abc"));
		System.out.println(getBytes("ab美a"));

	}

}
