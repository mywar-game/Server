package com.fantingame.game.gamecenter.partener.fantin.service;

import java.io.Serializable;

public class JHead implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8555388736845364844L;
	/* 版本号 */
	private String version;
	/* 返回码 */
	private String ret;
	/* 来源 */
	private String source;
	/* 客户端agent */
	private String agent;
	/* 流水号 */
	private String flowCode;
	/* 签名密文 */
	private String sign;
	/* 应用ID */
	private String appId;
	/* 公司ID */
	private String partnerId;
	/* 渠道号 */
	private String qn;
	/* 扩展字符串 */
	private String ex;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public String getEx() {
		return ex;
	}

	public void setEx(String ex) {
		this.ex = ex;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
}
