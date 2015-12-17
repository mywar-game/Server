package com.fantingame.game.common.utils;

import java.util.Random;

public class RandomUtils {

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
		Random random = new Random();
		int num = random.nextInt(max - min + 1) + min;
		return num;
	}
	
}
