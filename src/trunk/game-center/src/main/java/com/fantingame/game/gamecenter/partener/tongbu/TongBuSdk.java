package com.fantingame.game.gamecenter.partener.tongbu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;

public class TongBuSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(TongBuSdk.class);
	
	private static TongBuSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "http://";
	private String partnerId;
	private String appKey;
	private String appId;
	private String verifyUrl;

	public static TongBuSdk instance() {
		synchronized (TongBuSdk.class) {
			if (ins == null) {
				ins = new TongBuSdk();
			}
		}

		return ins;
	}

	private TongBuSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appKey = prop.getProperty("TongBuSdk.appKey");
			appId = prop.getProperty("TongBuSdk.appId");
			partnerId = prop.getProperty("TongBuSdk.partnerId");
			verifyUrl = prop.getProperty("TongBuSdk.verifyUrl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public String verifySession(String token){
		Map<String, String> params = new HashMap<String, String>();
		params.put("k", token);
		return UrlRequestUtils.execute(PROTOCOL_HEAD+verifyUrl,params,Mode.POST);
	}
	

	public boolean checkPayCallbackSign(TongBuPaymentObj data) {
		try {
			String sign = "source="+data.getSource()+"&trade_no="+data.getTradeNo()+"&amount="+data.getAmount()+
					"&partner="+appId+"&paydes="+data.getPaydes()+"&debug="+data.getDebug()+"&tborder="+data.getTborder()+"&key="+getAppKey();
			sign = MD5.MD5Encode(sign);
			if(data.getSign().equals(sign)){
				return true;
			}else{
				logger.error("同步 md5校验失败！");
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getVerifyUrl() {
		return verifyUrl;
	}

	public String getAppId() {
		return appId;
	}
	
	public static void main(String[] args){
		TongBuSdk.instance().verifySession("");
	}
}
