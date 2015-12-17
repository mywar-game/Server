package com.fantingame.game.gamecenter.partener.fantin.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fantingame.game.gamecenter.partener.fantin.util.CommonUtils;

/**
 * HTTP请求工具类
 * 
 * @author jay
 * @since 2013.3.27
 * @version 1.0
 * 
 */
public class EucHttpClient {


	private final static Log log = LogFactory.getLog(EucHttpClient.class);

	enum Mode {
		POST, GET;
	}

	/**
	 * 获取响应对象
	 * 
	 * @param request
	 *            请求对象
	 * @param context
	 *            配置参数
	 * @return
	 */
	public static HttpResponse getHttpResponse(HttpUriRequest request) {
		try {
			// return
			// HttpConnectionPoolManager.getHttpClient().execute(request);
			HttpClient httpClient = new DefaultHttpClient();
			return httpClient.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			request.abort();
			log.info("网络连接异常或者客户端协议错误:" + request.getURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			request.abort();
			log.info("网络连接异常:" + request.getURI());
		}
		return null;
	}

	/**
	 * HTTP GET 请求
	 * 
	 * @param url
	 * @param paraMap
	 * @return
	 */
	public static String httpGet(String url, Map<String, String> paraMap) {
		return execute(url, paraMap, Mode.GET);
	}

	/**
	 * HTTP POST 请求
	 * 
	 * @param url
	 * @param paraMap
	 * @return
	 */
	public static String httpPost(String url, Map<String, String> paraMap) {
		return execute(url, paraMap, Mode.POST);
	}

	/**
	 * HTTP请求
	 * 
	 * @param url
	 * @param paraMap
	 * @param mode
	 * @return
	 */
	public static String execute(String url, Map<String, String> paraMap, Mode mode) {
		// 请求者
		HttpUriRequest request = null;
		// 应答者
		HttpResponse response = null;
		// 配置对象
		// BasicHttpContext context = new BasicHttpContext();
		if (mode == Mode.POST) {// POST 请求
			request = new HttpPost(url);
			if (!paraMap.isEmpty()) {// 参数不为空
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				Set<String> key = paraMap.keySet();
				// 遍历
				for (Iterator it = key.iterator(); it.hasNext();) {
					String s = (String) it.next();
					nvps.add(new BasicNameValuePair(s, paraMap.get(s)));
				}
				try {
					((HttpPost) request).setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		} else {// GET 请求
			url = buildURL(url, paraMap);
			request = new HttpGet(url);
		}
		// 发送请求
		response = getHttpResponse(request);
		if (response == null || response.getStatusLine().getStatusCode() != 200) {// 通讯失败
			// 终止连接
			request.abort();
			return null;
		}
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity, "utf-8");
			} else {
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			request.abort();
			e.printStackTrace();
		} catch (IllegalStateException e) {
			request.abort();
			e.printStackTrace();
		} catch (IOException e) {
			request.abort();
			e.printStackTrace();
		} finally {
			request.abort();
		}
		return null;
	}

	/**
	 * 生成URL
	 * 
	 * @param url
	 * @param paraMap
	 * @return
	 */
	protected static String buildURL(String url, Map paramMap) {
		if (paramMap == null || paramMap.isEmpty()) {
			return url;
		}
		if (CommonUtils.isEmpty(url)) {
			return url;
		}
		StringBuffer sb = new StringBuffer();
		for (Iterator iterator = paramMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			sb.append(key).append("=").append(paramMap.get(key));
			if (iterator.hasNext()) {
				sb.append("&");
			}
		}
		String paraStr = sb.toString();
		StringBuffer buffer = new StringBuffer();

		buffer.append(url);
		if (url.indexOf("?") != -1) {
			if (url.indexOf("?") == url.length() - 1) {
				buffer.append(paraStr);
			} else {
				buffer.append("&").append(paraStr);
			}
		} else {
			buffer.append("?").append(paraStr);
		}
		return buffer.toString();
	}
}
