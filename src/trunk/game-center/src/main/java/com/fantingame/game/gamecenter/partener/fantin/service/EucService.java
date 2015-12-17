package com.fantingame.game.gamecenter.partener.fantin.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.fantingame.game.gamecenter.partener.fantin.httpclient.EucHttpClient;
import com.fantingame.game.gamecenter.partener.fantin.para.AuthParametric;
import com.fantingame.game.gamecenter.partener.fantin.util.GsonUtil;

public class EucService {

	private String appId;

	private String partnerId;

	private String key;
	/* SDK版本 */
	private String version;

	private String apiServer;

	private String source;

	private static EucService instance = null;

	private EucService() {

	}

	public static EucService getInstance() {
		if (null == instance) {
			instance = new EucService();
			Properties pro = getProperties();
			instance.init(pro);
		}
		return instance;
	}

	private static Properties getProperties() {
		InputStream is = EucService.class.getResourceAsStream("/client.properties");
		Properties pro = new Properties();
		try {
			pro.load(is);
		} catch (Exception e) {
			// log.warn("配置文件client.properties不存在");
			e.printStackTrace();
		}
		return pro;
	}

	private void init(Properties pro) {
		try {
			appId = ClientConfig.getProperty("appId", "");
			partnerId = ClientConfig.getProperty("partnerId", "");
			key = ClientConfig.getProperty("secertKey", "");
			version = ClientConfig.getProperty("version");
			source = ClientConfig.getProperty("source");
			apiServer = ClientConfig.getProperty("apiServer");
		} catch (Exception e) {
			// log.error(e, e);
			e.printStackTrace();
		}
	}

	/**
	 * 构建请求头部
	 * 
	 * @return
	 */
	public JHead buildDefaultRequestHeader() {
		JHead head = new JHead();
		head.setAppId(appId);
		// head.setVersion(version);
		head.setPartnerId(partnerId);
		head.setSource(source);
		return head;
	}

	/**
	 * 
	 * 
	 * @param path
	 *            请求路径
	 * @param jHead
	 *            请求头部
	 * @param jBody
	 * @param eucParser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JBean getResult(String path, JBody jBody, AuthParametric authPara, RequestInfo info) throws EucAPIException {
		// TODO 生成公共请求头
		JHead jHead = authPara.getVeriHeader(jBody, this, info);
		// TODO 生成请求参数
		JBean jbean = new JBean();
		jbean.setHead(jHead);
		jbean.setBody(jBody);
		// 请求参数
		String requestJSON = GsonUtil.toJson(jbean);
		System.out.println(requestJSON);
		// TODO 请求接口并返回结果
		String result = executeUrl(buildURL(apiServer + path, info), requestJSON);
		System.out.println(result);
		if (null == result || "".equals(result)) {
			return null;
		}
		// 返回结果
		return GsonUtil.extraJsonBean(result);
	}

	/**
	 * 执行HTTP URL请求,如果不使用本sdk提供的httpclient 可直接用自己的http客户端替换，可自已优化连接类 例如使用连接池的功能
	 * 
	 * @param url
	 *            请求地址，如果是https，不验证任何证书
	 * @param json
	 *            请求的json内容，使用标准的post方式传送的内容
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String executeUrl(String url, String json) {
		Map paraMap = new HashMap();
		paraMap.put("json", json);
		return EucHttpClient.httpPost(url, paraMap);
	}

	/**
	 * 请求参数信息
	 * 
	 * @param uri
	 *            uri信息
	 * @param info
	 *            请求信息
	 * @return
	 */
	public String buildURL(String url, RequestInfo info) {
		if (info == null)
			return url;
		StringBuffer buffer = new StringBuffer();
		buffer.append(url).append("?").append(info.paraToString());
		return buffer.toString();
	}

	public String getAppId() {
		return appId;
	}

	public String getVersion() {
		return version;
	}

	public String getKey() {
		return key;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public String getSource() {
		return source;
	}

}