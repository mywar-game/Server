package com.framework.util;

import java.util.Random;

public class RandomUtil {

	public static Random random = new Random();

	/**
	 * 范围内 数值随机数生成
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return 是否在范围内
	 */
	public static int getRandomNum(int min, int max) {
		int num = random.nextInt(max - min + 1) + min;
		return num;
	}

	/**
	 * 随机数生成
	 * 
	 * @param number
	 *            数值
	 * @return 是否在范围内
	 */
	public static int getRandomNum(int number) {
		int num = random.nextInt(number);
		return num;
	}

	/**
	 * 随机1/2概率
	 * 
	 * @return 是否在范围内
	 */
	public static boolean isInTheLimits() {
		int num = random.nextInt(2);
		if (num == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成指定长度的字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijkmnpqrstuvwxyz123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

}
