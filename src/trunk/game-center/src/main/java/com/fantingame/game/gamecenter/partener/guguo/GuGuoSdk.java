package com.fantingame.game.gamecenter.partener.guguo;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;

public class GuGuoSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(GuGuoSdk.class);
	
	private static GuGuoSdk ins;

	private static Properties prop;

	private String appKey;
	private String appId;
	private String partnerId;

	public static GuGuoSdk instance() {
		synchronized (GuGuoSdk.class) {
			if (ins == null) {
				ins = new GuGuoSdk();
			}
		}
		return ins;
	}

	private GuGuoSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appId = prop.getProperty("GuGuoSdk.appId");
			appKey = prop.getProperty("GuGuoSdk.appKey");
			partnerId = prop.getProperty("GuGuoSdk.partnerId");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean verifySession(String session,String uid,String timestamp){
		String sign = uid+"&"+timestamp+"&"+appKey;
		String encodeSign = MD5.MD5Encode(sign);
		if(encodeSign.equals(session)){
			return true;
		}
		return false;
	}
	

	public boolean checkPayCallbackSign(GuGuoPaymentObj cb) {
		try {
			String str = "openId="+cb.getOpenId()+"&serverId="+cb.getServerId()+"&serverName="+cb.getServerName()
					+"&roleId="+cb.getRoleId()+"&roleName="+cb.getRoleName()+"&orderId="+cb.getOrderId()+"&orderStatus="+cb.getOrderStatus()
					+"&payType="+cb.getPayType()+"&amount="+cb.getAmount()+"&remark="+cb.getRemark()+"&callBackInfo="+cb.getCallBackInfo()
					+"&payTime="+cb.getPayTime()+"&paySUTime="+cb.getPaySUTime()+"&app_key="+getAppKey();
			str = MD5.MD5Encode(str,"UTF-8");
			if(cb.getSign().equals(str)){
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
}
