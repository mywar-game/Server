package com.fandingame.game.giftbag.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fandingame.game.giftbag.constant.ServiceReturnCode;
import com.fandingame.game.giftbag.exception.ServiceException;
import com.fandingame.game.giftbag.util.EncryptUtil;

/**
 * 检验sign
 * 
 * @author yezp
 */
public class CheckSigninterceptor implements HandlerInterceptor {
	private final static Logger log = Logger
			.getLogger(CheckSigninterceptor.class);
	private static final String key = "jjdadsfoo@11349$^%$113";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String signOld = request.getParameter("sign");
		// 兼容以前的那些sign 在程序内部的验证
		if (signOld != null) {
			return true;
		}
		String sign = request.getParameter("md5sign");
		if (sign == null || sign.equals("")) {
			return true;
		}
		long now = System.currentTimeMillis();
		String sendTime = request.getParameter("timestamp");
		String str = request.getQueryString();
		String[] valuesArray = getValues(str);
		if (sendTime == null) {
			throw new ServiceException(ServiceReturnCode.SIGN_CHECK_ERROR,
					"发送时间字段没有填写");
		}
		long timestamp = Long.valueOf(sendTime);
		if ((now - timestamp) > 1000 * 60) {
			log.info("请求时间超出范围：" + timestamp + "(" + new Date(timestamp) + ")");
			throw new ServiceException(ServiceReturnCode.SIGN_CHECK_ERROR,
					"请求时间超出范围（10s）");
		}
		String md5Str = "";
		for (String strValueTemp : valuesArray) {
			md5Str = md5Str + strValueTemp;
		}
		md5Str = md5Str + timestamp + key;
		// System.out.println("befor:"+md5Str);
		String md5 = EncryptUtil.getMD5(md5Str);
		System.out.println("after:" + md5);
		if (!md5.toLowerCase().equals(sign.toLowerCase())) {
			log.info("请求签名不正确：" + sign + "(" + md5 + ")");
			throw new ServiceException(ServiceReturnCode.SIGN_CHECK_ERROR,
					"请求签名不正确");
		}
		return true;
	}

	private String[] getValues(String queryString) {
		String[] result = new String[] {};
		if (queryString != null && !queryString.equals("")) {
			String[] tempArray = queryString.split("&");
			result = new String[tempArray.length - 2];
			int i = 0;
			for (String str : tempArray) {
				if (str != null && !str.equals("")) {
					String[] entryArray = str.split("=");
					if (!entryArray[0].equals("md5sign")
							&& !entryArray[0].equals("timestamp")) {
						result[i] = entryArray[1];
						i++;
					}
				}
			}
		}
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, 
			Exception ex) throws Exception {
	}

}
