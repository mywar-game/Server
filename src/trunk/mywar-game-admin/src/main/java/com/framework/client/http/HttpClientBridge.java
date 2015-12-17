package com.framework.client.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.framework.common.MD5;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class HttpClientBridge {
	public static final String SUCCESS = "result";
	public static final String FAIL = "rc";

	public static final String RETURN_RC = "rc";
	public static final String RETURN_RT = "rt";
	public static final Integer RETURN_CODE = 1000;
	
	// 以上为历史遗留问题
	// 返回成功码
	public static final Integer RETURN_SUCCESS_CODE = 1000;

	public static String sendToPaymentController(String reqUrl, String params,
			String md5Str, String crcKey) {
		long time = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append(reqUrl);
		sb.append("?timestamp=").append(time);
		sb.append("&sign=");
		sb.append(MD5.getMd5(time + md5Str + crcKey));

		String url = sb.toString() + params;

		HttpMethod method = new GetMethod(url);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());

		HttpClient httpclient = new HttpClient();

		try {
			httpclient.executeMethod(method);
			String response = method.getResponseBodyAsString();
			LogSystem.info(response);
			return response;
		} catch (IOException e) {
			LogSystem.error(e, "");
		} finally {
			method.releaseConnection();
		}
		return null;
	}

	/**
	 * 
	 * @param reqUrl请求的地址
	 * @param params参数列表
	 * @param md5str需要做MD5处理的字符串
	 *            (参数值组合起来的)
	 * @return
	 */
	public static String sendToGameServer(String reqUrl, String params,
			String md5str) {
		// 创建HttpClient实例
		HttpClient httpclient = new HttpClient();
		// 创建Get方法实例
		String urlStr = checkStr(reqUrl, md5str) + params;
		HttpMethod method = new GetMethod(urlStr);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		try {
			httpclient.executeMethod(method);
//			byte[] responseArr = method.getResponseBody();
//			LogSystem.info("查询用户信息: response ------ byte " + method.getResponseBody());
//			String response = method.getResponseBodyAsString();
//			String response = new String(responseArr, "UTF-8");
//			LogSystem.info("查询用户信息: response" + response);
//			LogSystem.info("查询用户信息: response" + new String(method.getResponseBody(), "UTF-8"));
			
			InputStream inputStream = method.getResponseBodyAsStream();  
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
			StringBuffer stringBuffer = new StringBuffer();  
			String str= "";  
			while ((str = br.readLine()) != null) {  
				stringBuffer .append(str);  
			} 
			
			LogSystem.info("查询用户信息: response" + stringBuffer.toString());
			return stringBuffer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		} finally {
			method.releaseConnection();
		}
		return null;
	}

	/**
	 * 
	 * @param reqUrl
	 *            请求的地址
	 * @return
	 */
	public static String sendToGameServer(String reqUrl) {
		HttpClient httpclient = new HttpClient();
		HttpMethod method = new GetMethod(reqUrl);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		
		try {
			httpclient.executeMethod(method);
			String response = method.getResponseBodyAsString();
			return response;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		} finally {
			method.releaseConnection();
		}

		return null;
	}

	public static String sendToGameServer(String reqUrl, String params,
			String md5str, TGameServer gameServer) {
		// 创建HttpClient实例
		HttpClient httpclient = new HttpClient();
		// 创建Get方法实例
		String urlStr = checkStr(reqUrl, md5str, gameServer) + params;
		LogSystem.info(urlStr);

		HttpMethod method = new GetMethod(urlStr);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		try {
			httpclient.executeMethod(method);			
//			InputStream inputStream = method.getResponseBodyAsStream();  
//			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
//			StringBuffer stringBuffer = new StringBuffer();  
//			String str= "";  
//			while((str = br.readLine()) != null){  
//				stringBuffer .append(str );  
//			} 
//			
//			LogSystem.info("查询用户信息: response" + stringBuffer.toString());
//			return stringBuffer.toString();
			
			String response = method.getResponseBodyAsString();
			return response;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		} finally {
			method.releaseConnection();
		}
		return null;
	}

	/**
	 * 获得校验字符串
	 * 
	 * @return
	 */
	public static String checkStr(String reqUrl, String md5str) {
		TGameServer gameServer = DataSourceManager.getInstatnce()
				.getGameServerMap().get(CustomerContextHolder.getSystemNum());
		long time = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append(gameServer.getGameServerHttpUrl());
		sb.append(reqUrl);
		sb.append("?timestamp=");
		sb.append(time);
		sb.append("&md5sign=");
		sb.append(MD5.getMd5(md5str + time
				+ gameServer.getServerCommunicationKey()));
		return sb.toString();
	}

	/**
	 * 获得校验字符串，由用户指定发送到那个服务器
	 */
	public static String checkStr(String reqUrl, String md5str,
			TGameServer gameServer) {
		long time = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append(gameServer.getGameServerHttpUrl());
		sb.append(reqUrl);
		sb.append("?timestamp=");
		sb.append(time);
		sb.append("&md5sign=");
		sb.append(MD5.getMd5(md5str + time
				+ gameServer.getServerCommunicationKey()));
		return sb.toString();
	}

}
