package com.fantingame.game.gamecenter.help;

import java.math.BigDecimal;

import com.fantingame.game.gamecenter.exception.ServiceException;
import com.mysql.jdbc.StringUtils;

/**
 * 
 * @author jacky
 * 
 */
public class Assert {

	/**
	 * 确保参数不为空
	 * 
	 * @param s
	 * @param errorCode
	 * @param message
	 */
	public static void notEmtpy(String s, int errorCode, String message) {
		if (StringUtils.isNullOrEmpty(s)) {
			throw new ServiceException(errorCode, message);
		}
	}

	/**
	 * 确保参数不为空
	 * 
	 * @param d
	 * @param errorCode
	 * @param message
	 */
	public static void notEmtpy(Double d, int errorCode, String message) {
		if (d == null) {
			throw new ServiceException(errorCode, message);
		}
	}

	/**
	 * 确保参数不为空
	 * 
	 * @param l
	 * @param errorCode
	 * @param message
	 */
	public static void notEmtpy(Long l, int errorCode, String message) {
		if (l == null) {
			throw new ServiceException(errorCode, message);
		}
	}

	/**
	 * 确保参数不为空
	 * 
	 * @param l
	 * @param errorCode
	 * @param message
	 */
	public static void notEmtpy(Integer l, int errorCode, String message) {
		if (l == null) {
			throw new ServiceException(errorCode, message);
		}
	}

	/**
	 * 确保参数不为空
	 * 
	 * @param l
	 * @param errorCode
	 * @param message
	 */
	public static void notEmtpy(BigDecimal b, int errorCode, String message) {
		if (b == null) {
			throw new ServiceException(errorCode, message);
		}
	}

}
