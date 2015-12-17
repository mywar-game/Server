package com.framework.log;

import org.apache.log4j.Logger;

import com.framework.servicemanager.CustomerContextHolder;

/**
 * 日志系统
 * 
 * @author mengchao
 * 
 */
public class LogSystem {
	
	
	private final static Logger logger = Logger.getLogger(Logger.class);




	public static void debug(String disc) {
			logger.debug(makeDri(disc));
	}

	public static void info(String disc) {
			logger.info(makeDri(disc));
	}

	public static void warn(String disc) {
			logger.warn(makeDri(disc));
	}

	public static void error(Exception e, String disc) {
			logger.error(makeExeDri(e, disc));
	}

	public static void fatal(String disc) {
			logger.fatal(makeDriMutLayer(disc));
	}

	private static String makeDri(String disc) {
		StringBuffer strBu = new StringBuffer();
		try {
			throw new Exception();
		} catch (Exception e) {
			StackTraceElement[] el = e.getStackTrace();
			strBu.append("serverid:"+CustomerContextHolder.getSystemNum());
			strBu.append("\n");
			strBu.append(el[2].getClassName()).append(" ").append(
					el[2].getMethodName()).append(" ").append(
					el[2].getLineNumber());
		}
		strBu.append("  || ").append(disc).append("\n");
		return strBu.toString();
	}

	private static String makeDriMutLayer(String disc) {
		StringBuffer strBu = new StringBuffer();
		strBu.append("serverid:"+CustomerContextHolder.getSystemNum());
		strBu.append("\n");
		strBu.append(disc).append("\n");
		try {
			throw new Exception();
		} catch (Exception e) {
			StackTraceElement[] el = e.getStackTrace();
			int size = 0;
			if (el.length > 30) {
				size = 30;
			} else {
				size = el.length;
			}
			for (int i = 2; i < size; i++) {
				strBu.append(el[i].getClassName()).append("  ").append(
						el[i].getMethodName()).append("  ").append(
						el[i].getLineNumber()).append("\n");
			}
			if (el.length > 30) {
				strBu.append("...").append("\n");
			}
		}
		return strBu.toString();

	}

	private static String makeExeDri(Exception e, String description) {
		StackTraceElement[] el = e.getStackTrace();
		StringBuffer strBu = new StringBuffer(e.toString());
		strBu.append(" ");
		if (description == null) {
			description = "";
		}
		strBu.append(description);
		strBu.append("\n");
		strBu.append("serverid:"+CustomerContextHolder.getSystemNum());
		strBu.append("\n");
		int size = 0;
		if (el.length > 30) {
			size = 30;
		} else {
			size = el.length;
		}
		for (int i = 0; i < size; i++) {
			strBu.append(el[i].getClassName()).append(" ").append(
					el[i].getMethodName()).append(" ").append(
					el[i].getLineNumber()).append("\n");
		}
		if (el.length > 30) {
			strBu.append("...").append("\n");
		}
		return strBu.toString();
	}
}
