package com.fantingame.game.gamecenter.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

public class UrlRequestUtils {
	public static final Logger LOG = Logger.getLogger(UrlRequestUtils.class);

	public class Mode {
		public final static String POST = "POST";
		public final static String GET = "GET";
	};

	public static String execute(String url, Map<String, String> paraMap, String mode) {
		// 请求者
		HttpUriRequest request = null;
		// 应答者
		HttpResponse response = null;
		// 配置对象
		// BasicHttpContext context = new BasicHttpContext();
		if (Mode.POST.equals(mode)) {// POST 请求
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
			LOG.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			request.abort();
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			request.abort();
			LOG.error(e.getMessage(), e);
		} finally {
			request.abort();
		}
		return null;
	}
	
	public static String executeHttps(String url, Map<String, String> paraMap, String mode) {
		// 请求者
		HttpUriRequest request = null;
		// 应答者
		HttpResponse response = null;
		// 配置对象
		// BasicHttpContext context = new BasicHttpContext();
		if (Mode.POST.equals(mode)) {// POST 请求
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
		response = getHttpsResponse(request);
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
			LOG.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			request.abort();
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			request.abort();
			LOG.error(e.getMessage(), e);
		} finally {
			request.abort();
		}
		return null;
	}
	
	public static String executeHttpsByJson(String url,JsonObject jsonObject) {
		// 请求者
		HttpPost httpPost = null;
		// 应答者
		HttpResponse response = null;
		try {
			httpPost = new HttpPost(url);
			StringEntity stringentity = new StringEntity(jsonObject.toString(),"UTF-8");
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(stringentity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 发送请求
		response = getHttpsResponse(httpPost);
		if (response == null || response.getStatusLine().getStatusCode() != 200) {// 通讯失败
			// 终止连接
			httpPost.abort();
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
			httpPost.abort();
			LOG.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			httpPost.abort();
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			httpPost.abort();
			LOG.error(e.getMessage(), e);
		} finally {
			httpPost.abort();
		}
		return null;
	}
	
	/**
	 * http json
	 * @param url
	 * @param jsonObject
	 * @return
	 */
	public static String executeHttpByJson(String url,JsonObject jsonObject) {
		// 请求者
		HttpPost httpPost = null;
		// 应答者
		HttpResponse response = null;
		try {
			httpPost = new HttpPost(url);
			StringEntity stringentity = new StringEntity(jsonObject.toString(),"UTF-8");
			httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
			httpPost.setEntity(stringentity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 发送请求
		response = getHttpResponse(httpPost);
		if (response == null || response.getStatusLine().getStatusCode() != 200) {// 通讯失败
			// 终止连接
			httpPost.abort();
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
			httpPost.abort();
			LOG.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			httpPost.abort();
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			httpPost.abort();
			LOG.error(e.getMessage(), e);
		} finally {
			httpPost.abort();
		}
		return null;
	}
	
	public static String executeHttpByString(String url,String info) {
		// 请求者
		HttpPost httpPost = null;
		// 应答者
		HttpResponse response = null;
		try {
			httpPost = new HttpPost(url);
			StringEntity stringentity = new StringEntity(info,"UTF-8");
			httpPost.addHeader("Host","passport_i.25pp.com");
			httpPost.setEntity(stringentity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 发送请求
		response = getHttpResponse(httpPost);
		if (response == null || response.getStatusLine().getStatusCode() != 200) {// 通讯失败
			// 终止连接
			httpPost.abort();
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
			httpPost.abort();
			LOG.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			httpPost.abort();
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			httpPost.abort();
			LOG.error(e.getMessage(), e);
		} finally {
			httpPost.abort();
		}
		return null;
	}

	public static String buildParam(Map paramMap) {

		StringBuffer sb = new StringBuffer();
		for (Iterator iterator = paramMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			sb.append(key).append("=").append(paramMap.get(key));
			if (iterator.hasNext()) {
				sb.append("&");
			}
		}
		String paraStr = sb.toString();

		return paraStr;
	}

	public static String buildURL(String url, Map paramMap) {
		if (paramMap == null || paramMap.isEmpty()) {
			return url;
		}
		if (StringUtils.isBlank(url)) {
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
			LOG.error("网络连接异常或者客户端协议错误:" + request.getURI());
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			request.abort();
			LOG.info("网络连接异常:" + request.getURI());
			LOG.error(e.getMessage(), e);
		}
		return null;
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
	public static HttpResponse getHttpsResponse(HttpUriRequest request) {
		try {
			// return
			// HttpConnectionPoolManager.getHttpClient().execute(request);
			HttpClient client = new DefaultHttpClient();
			HttpClient httpClient = wrapClient(client);
			httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH);
			return httpClient.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			request.abort();
			LOG.error("网络连接异常或者客户端协议错误:" + request.getURI());
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			request.abort();
			LOG.info("网络连接异常:" + request.getURI());
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static HttpClient wrapClient(HttpClient client) {
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");
            X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager 
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
                public X509Certificate[] getAcceptedIssuers() { return null; } 
            }; 
            ctx.init(null, new TrustManager[]{xtm}, null); 
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
            PoolingClientConnectionManager mgr = new PoolingClientConnectionManager(registry);
            return new DefaultHttpClient(mgr, client.getParams());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
	
	/**
	 * 酷动专用
	 * @param url
	 * @param paraMap
	 * @param mode
	 * @return
	 */
	public static String executeKudong(String url, String mid, String token, String mode) {
		// 请求者
		HttpUriRequest request = null;
		// 应答者
		HttpResponse response = null;
		// 配置对象
		// BasicHttpContext context = new BasicHttpContext();
		if (Mode.POST.equals(mode)) {// POST 请求
			request = new HttpPost(url + "?mid=" + mid);
			if (null != token) {
				request.setHeader("Cookie", "JSESSIONID=" + token);
			}
		} else {// GET 请求
			// 不进行处理
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
			LOG.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			request.abort();
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			request.abort();
			LOG.error(e.getMessage(), e);
		} finally {
			request.abort();
		}
		return null;
	}
	
	/**
	 * 腾讯专用
	 * @param url
	 * @param mid
	 * @param token
	 * @param mode
	 * @return
	 */

	public static String executeTengxun(String url, Map<String, String> paraMap, String sessionId, String sessionType, String orgLoc, String mode) {
		// 请求者
		HttpUriRequest request = null;
		// 应答者
		HttpResponse response = null;

		if (Mode.POST.equals(mode)) {// POST 请求
			request = new HttpPost(url);
			request.setHeader("Cookie", "session_id=" + sessionId + ";session_type=" + sessionType + ";org_loc=" + orgLoc);
			
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
			LOG.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			request.abort();
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			request.abort();
			LOG.error(e.getMessage(), e);
		} finally {
			request.abort();
		}
		return null;
	}
	
	public static String executeHttpsTengxun(String url, Map<String, String> paraMap, String sessionId, String sessionType, String orgLoc, String mode) {
		// 请求者
		HttpUriRequest request = null;
		// 应答者
		HttpResponse response = null;
		
		if (Mode.POST.equals(mode)) {// POST 请求
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
			Map<String, String> map = new HashMap<String, String>();
			Iterator<String> iter = paraMap.keySet().iterator();
			while (iter.hasNext()) {
			   String key = iter.next();
			   String value = paraMap.get(key);
			   map.put(key, URLEncoder.encode(value));
			}
			url = buildURL(url, map);
			LOG.info("url=" + url);
			request = new HttpGet(url);
			request.setHeader("Cookie", "session_id=" + URLEncoder.encode(sessionId) + ";session_type=" + URLEncoder.encode(sessionType) + ";org_loc=" + URLEncoder.encode(orgLoc)); // cookie
		}
		// 发送请求
		response = getHttpsResponse(request);
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
			LOG.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			request.abort();
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			request.abort();
			LOG.error(e.getMessage(), e);
		} finally {
			request.abort();
		}
		return null;
	}
	
	/**
	 * http json腾讯专用
	 * @param url
	 * @param jsonObject
	 * @return
	 */
	public static String executeHttpByJsonTengxun(String url, JsonObject jsonObject, String sessionId, String sessionType, String orgLoc) {
		// 请求者
		HttpPost httpPost = null;
		// 应答者
		HttpResponse response = null;
		try {
			httpPost = new HttpPost(url);
			StringEntity stringentity = new StringEntity(jsonObject.toString(),"UTF-8");
			httpPost.addHeader("content-type", "application/json");
			httpPost.setHeader("Cookie", "session_id=" + URLEncoder.encode(sessionId) + ";session_type=" + URLEncoder.encode(sessionType) + ";org_loc=" + URLEncoder.encode(orgLoc)); // cookie
			httpPost.setEntity(stringentity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 发送请求
		response = getHttpsResponse(httpPost);
		if (response == null || response.getStatusLine().getStatusCode() != 200) {// 通讯失败
			// 终止连接
			httpPost.abort();
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
			httpPost.abort();
			LOG.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			httpPost.abort();
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			httpPost.abort();
			LOG.error(e.getMessage(), e);
		} finally {
			httpPost.abort();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("log", "我爱你中国xxxxx");
		UrlRequestUtils.execute("http://192.168.1.100:4949/LogError/elog.do", paraMap, Mode.GET);
	}
}

class TrustAnyTrustManager implements X509TrustManager {
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}
 
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}
 
	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[] {};
	}
}