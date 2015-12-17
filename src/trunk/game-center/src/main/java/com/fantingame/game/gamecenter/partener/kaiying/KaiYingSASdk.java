package com.fantingame.game.gamecenter.partener.kaiying;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;

public class KaiYingSASdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(KaiYingSASdk.class);

	private static KaiYingSASdk ins;

	private static Properties prop;

	private String partnerId;
	private String resource;
	private String key;

	public static KaiYingSASdk instance() {
		synchronized (KaiYingSASdk.class) {
			if (ins == null) {
				ins = new KaiYingSASdk();
			}
		}

		return ins;
	}

	private KaiYingSASdk() {
		loadSdkProperties();
	}

	public void reload() {
		loadSdkProperties();
	}

	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource(
					"sdk.properties"));
			resource = prop.getProperty("KaiYingSASdk.resource");
			key = prop.getProperty("KaiYingSASdk.key");
			partnerId = prop.getProperty("KaiYingSASdk.partnerId");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	
	public String sha256(String str){
		String temp = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes("GBK"));
			temp =  bytes2Hex(md.digest()); // to HexString
			return temp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	public  String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
	
	public int getServerId(String serverId){
		if(serverId.startsWith("a")){
			return 1000 + Integer.parseInt(serverId.substring(1, serverId.length()));
		}else if(serverId.startsWith("s")){
			return 2000 + Integer.parseInt(serverId.substring(1, serverId.length()));
		}else if(serverId.startsWith("h")){
			return 3000 + Integer.parseInt(serverId.substring(1, serverId.length()));
		}else if(serverId.startsWith("k")){
			return 4000 + Integer.parseInt(serverId.substring(1, serverId.length()));
		}
		return 0;
	}
	
	public static void main(String[] args) {
		String str = "a11";
		System.out.print(1000 + Integer.parseInt(str.substring(1, str.length())));
	}
	
	public String getServerId(int serverId){
		if(String.valueOf(serverId).startsWith("1")){
			return "a"+(serverId-1000);
		}else if(String.valueOf(serverId).startsWith("2")){
			return "s"+(serverId-2000);
		}else if(String.valueOf(serverId).startsWith("3")){
			return "h"+(serverId-3000);
		}else if(String.valueOf(serverId).startsWith("4")){
			return "k"+(serverId-4000);
		}
		return "";
	}
	
	public int getPartnerId(int serverId){
		if(String.valueOf(serverId).startsWith("1")){
			return 4001;
		}else if(String.valueOf(serverId).startsWith("2")){
			return 4001;
		}else if(String.valueOf(serverId).startsWith("3")){
			return 4002;
		}else if(String.valueOf(serverId).startsWith("4")){
			return 4002;
		}
		return 0;
	}

	public boolean checkPayCallbackSign(KaiYingTwPaymentObj cb) {

		try {
			String signString = KaiYingSASdk.instance().getKey() + cb.getUserId() + cb.getSid() + cb.getNumber() + 
					cb.getAmount() + cb.getRoleId() + cb.getPartnerOrderId() + cb.getTs() + KaiYingSASdk.instance().getResource();
			if (StringUtils.isBlank(cb.getSig())) {
				return false;
			}
			String sha256 = sha256(signString);
			logger.info("KaiYingTw sign:" + signString);
			logger.info("KaiYingTw sha256Sign:" + sha256);
			logger.info("KaiYingTw getSignature:" + cb.getSig());

			if (cb.getSig().equals(sha256)) {
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}


	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
