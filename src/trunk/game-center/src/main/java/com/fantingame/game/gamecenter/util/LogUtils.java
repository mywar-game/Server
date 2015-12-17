package com.fantingame.game.gamecenter.util;

import org.apache.log4j.Appender;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * 日志工具
 * 
 * @author CJ
 * 
 */
public class LogUtils {
	public static void error(Logger log, Exception e) {
		log.error(errorException(e));
	}

	public static String errorException(Exception e) {
		StackTraceElement[] ste = e.getStackTrace();
		StringBuffer sb = new StringBuffer();
		sb.append(e.getMessage() + "\n");
		for (int i = 0; i < ste.length; i++) {
			sb.append(ste[i].toString() + "\n");
		}
		return sb.toString();
	}

	@SuppressWarnings("unused")
	private static Appender getAppender(String name) {
		DailyRollingFileAppender appender = new DailyRollingFileAppender();
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern("%d %p - <%m>%n");
		appender.setLayout(layout);
		appender.setDatePattern("'.'yyyyMMdd");
		appender.setFile("d:/var/log/gig/" + name);
		return appender;
	}
}
