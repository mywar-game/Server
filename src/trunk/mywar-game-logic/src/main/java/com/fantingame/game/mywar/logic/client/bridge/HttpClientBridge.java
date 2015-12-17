package com.fantingame.game.mywar.logic.client.bridge;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.mywar.logic.client.config.Config;
import com.fantingame.game.server.util.MD5;


/**
 * http 请求
 * 
 * @author yezp
 */
public class HttpClientBridge {

	public static final String RC = "rc";	
	private final static String PROTOCOL_HEAD = "http://";
	
	/**
	 * 发往礼包码服务器
	 * 
	 * @param reqUrl
	 * @param params
	 * @param md5str
	 * @return
	 */
	public static String sendToGiftbagServer(String reqUrl, String params, String md5str) {
		// 创建HttpClient实例
		HttpClient httpClient  = new HttpClient();
		// 创建Get方法实例
		String urlStr = checkStr(reqUrl, md5str) + params;
		LogSystem.info(urlStr);
		
		HttpMethod method = new GetMethod(urlStr);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		
		try {
			httpClient.executeMethod(method);
			String response = method.getResponseBodyAsString();
			return response;
		} catch (IOException e) {
			LogSystem.error(e, "");
		} finally {
			method.releaseConnection();
		}
		
		return null;
	}
	
	/**
	 * 获取校验字符串
	 * 
	 * @param reqUrl
	 * @param md5str
	 * @return
	 */
	public static String checkStr(String reqUrl, String md5str) {
		long time = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		
		sb.append(PROTOCOL_HEAD + Config.ins().getIp() + ":" + Config.ins().getPort() + "/giftbagServer/");
		sb.append(reqUrl);
		sb.append("?timestamp=");
		sb.append(time);
		sb.append("&md5sign=");
		sb.append(MD5.md5Of32(md5str + time + Config.ins().getSignKey()));
		return sb.toString();
	}
	
}
