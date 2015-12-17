package com.fantingame.game.gamecenter.partener.fantin.service;


/**
 * 基础参数
 * 
 * @author damon
 * @since 2012.08.02
 * 
 */
public class RequestInfo {
	
	/**
	 * 接口调用者
	 */
	private String source;
	/**
	 * uid
	 */
	private String uid;
	/**
	 * esid
	 */
	private String esid;
	/**
	 * 渠道
	 */
	private String qn;
	/**
	 * 客户端agent
	 */
	private String agent;
	/**
	 * 应用/游戏id(如果该参数为空，service会发送配置文件中的appId值)
	 */
	private String appId;
	
	public RequestInfo() {
		
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEsid() {
		return esid;
	}

	public void setEsid(String esid) {
		this.esid = esid;
	}

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String paraToString() {
		if (this == null)
			return "";
		StringBuffer buffer = new StringBuffer();
		if(this.esid!=null) {
			buffer.append("esid=").append(this.esid).append("&");
		}
		if(this.uid!=null) {
			buffer.append("uid=").append(this.uid).append("&");
		}
		if(buffer.length()>0) {
			return buffer.toString().substring(0,buffer.length()-1);
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		RequestInfo info=new RequestInfo();
		System.out.println(info.paraToString());
	}

}
