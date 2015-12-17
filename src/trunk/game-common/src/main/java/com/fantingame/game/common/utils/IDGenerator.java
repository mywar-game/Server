package com.fantingame.game.common.utils;


import java.util.UUID;

public class IDGenerator {

	/**
	 * 创建一个ID
	 * 
	 * @return
	 */
	public static String getID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static void main(String[] args) {
		System.out.println(getID());
	}
}
