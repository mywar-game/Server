package com.fantingame.game.msgbody.user;

import java.io.IOException;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

public class ReqLoadUserMapper implements ICodeAble {
	
	private String partnerUserId=""; 
	private String partnerId=""; 
	private String serverId=""; 
	private String qn=""; 
	private String imei=""; 
	private String mac="";
	private String idfa="";
	private String ip="";
	private String mobile="";
	private String extInfo="";
	
	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(partnerUserId);
		outputStream.writeUTF(partnerId);
		outputStream.writeUTF(serverId);
		outputStream.writeUTF(qn);
		outputStream.writeUTF(imei);
		outputStream.writeUTF(mac);
		outputStream.writeUTF(idfa);
		outputStream.writeUTF(ip);
		outputStream.writeUTF(mobile);
		outputStream.writeUTF(extInfo);
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		partnerUserId=inputStream.readUTF(); 
		partnerId=inputStream.readUTF(); 
		serverId=inputStream.readUTF(); 
		qn=inputStream.readUTF(); 
		imei=inputStream.readUTF(); 
		mac=inputStream.readUTF();
		idfa=inputStream.readUTF();
		ip=inputStream.readUTF();
		mobile=inputStream.readUTF();
		extInfo = inputStream.readUTF();
	}

	public String getPartnerUserId() {
		return partnerUserId;
	}

	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public ReqLoadUserMapper(String partnerUserId, String partnerId,
			String serverId, String qn, String imei, String mac, String idfa,
			String ip, String mobile) {
		super();
		this.partnerUserId = partnerUserId;
		this.partnerId = partnerId;
		this.serverId = serverId;
		this.qn = qn;
		this.imei = imei;
		this.mac = mac;
		this.idfa = idfa;
		this.ip = ip;
		this.mobile = mobile;
	}
	public ReqLoadUserMapper() {
		super();
	}
	public String getExtInfo() {
		return extInfo;
	}
	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}

	
}
