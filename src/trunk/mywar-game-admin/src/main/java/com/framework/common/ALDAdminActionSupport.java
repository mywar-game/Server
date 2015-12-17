package com.framework.common;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.admin.bo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author hzy
 * 2012-7-20
 */
public class ALDAdminActionSupport extends ActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;

	/** 管理员 */
	private AdminUser adminUser;

	/** 错误码 */
	private int erroCodeNum;

	/** 错误描述 */
	private String erroDescrip;
	
	/**
	 * 现有服务器(避免再次出现采集不了数据情况)
	 */
	private String servers;

	/**
	 * @param url 
	 * @param paramstemp 
	 */
	public static void msgSend(String url, NameValuePair[] paramstemp) {

		HttpClient httpClient = new HttpClient();

		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		NameValuePair[] params = paramstemp;

		getMethod.setQueryString(params);
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			getMethod.getResponseBody();
			// 读取内容
			// byte[] responseBody = getMethod.getResponseBody();
			// 处理内容
			// System.out.println(new String(responseBody, "utf-8"));
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
	}

	/**
	 * @return 管理员
	 */
	public AdminUser getAdminUser() {
		return adminUser;
	}

	/**
	 * @param adminUser 管理员
	 */
	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	/**
	 * @return 错误码 
	 */
	public int getErroCodeNum() {
		return erroCodeNum;
	}

	/**
	 * @param erroCodeNum 错误码 
	 */
	public void setErroCodeNum(int erroCodeNum) {
		this.erroCodeNum = erroCodeNum;
	}

	/**
	 * @return 错误描述
	 */
	public String getErroDescrip() {
		return erroDescrip;
	}

	/**
	 * @param erroDescrip 错误描述
	 */
	public void setErroDescrip(String erroDescrip) {
		this.erroDescrip = erroDescrip;
	}
	
	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}
}
