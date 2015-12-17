package com.fantingame.game.gamecenter.partener.yuenan;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.alibaba.fastjson.JSON;
import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;

public class YueNanSdk extends BaseSdk {
	private static final Logger logger = Logger.getLogger(YueNanSdk.class);
	private static YueNanSdk ins;

	private static Properties prop;
	private String host;
	private String partnerId;
	private String appKey;
	private String appleKey;
	private String sanboxKey;
	private String googleAppKey;
	private String appleThridkey;

	public static YueNanSdk instance() {
		synchronized (YueNanSdk.class) {
			if (ins == null) {
				ins = new YueNanSdk();
			}
		}
		return ins;
	}
	
	private YueNanSdk() {
		 loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			partnerId = prop.getProperty("YueNanSdk.partnerId","5001");
			appKey = prop.getProperty("YueNanSdk.appKey");
			googleAppKey = prop.getProperty("YueNanSdk.googleAppKey");
			appleKey = prop.getProperty("YueNanSdk.appleKey");
			appleThridkey = prop.getProperty("YueNanSdk.appleThridkey");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean  checkPayCallbackSign(YueNanPaymentObj obj){
		try { 
			String sign = obj.getAmount();
			if("SMS".equals(obj.getTransactionType())){
				sign = sign + obj.getCode() + obj.getCountryCode() + obj.getCurrency() + 
						obj.getMessage() + obj.getPhone();
			}else if("CARD".equals(obj.getTransactionType())){
				sign = sign + obj.getCardCode() + obj.getCardSerial() + obj.getCardVendor() 
						+ obj.getCountryCode() + obj.getCurrency();
			}else if("GOOGLE_PLAY".equals(obj.getTransactionType())){
				sign = sign + obj.getCountryCode() + obj.getCurrency() + obj.getProductId();
			}else if("APPLE_ITUNES".equals(obj.getTransactionType())){
				sign = sign + obj.getCountryCode() + obj.getCurrency()+obj.getProductId();
			}else{
				sign = sign + obj.getCountryCode() + obj.getCurrency();
			}
			sign = sign+obj.getSandbox() + obj.getState() + obj.getStatus() + obj.getTarget() +obj.getTransactionId() 
					+ obj.getTransactionType();
			if("0".equals(obj.getSandbox())){
				if(obj.isGoogle()){
					sign = sign+ googleAppKey;//googlePlay
				}else if("APPLE_ITUNES".equals(obj.getTransactionType())){
					sign = sign+ appleKey;//ios apple
				}else if(obj.isApple()){
					sign = sign+appleThridkey;//ios第三方
				}else{
					sign = sign+ appKey;//android第三方
				}
			}else{
				sign = sign+ sanboxKey;
			}
			sign = MD5.MD5Encode(sign,"UTF-8");
			logger.info("general sign:"+sign+",hash:"+obj.getHash());
			if(sign.equals(obj.getHash())){
				if("USD".equals(obj.getCurrency())){
					double doller = Double.valueOf(obj.getAmount());
					obj.setAmount(doller*21000+"");
				}
				return true;
			}
		} catch (Exception e) {
			logger.error("checkPayCallbackSign error!obj:"+JSON.toJSONString(obj),e);
		}
		return false;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public String getSanboxKey() {
		return sanboxKey;
	}

	public String getGoogleAppKey() {
		return googleAppKey;
	}

	public String getAppleThridkey() {
		return appleThridkey;
	}

	public String getAppleKey() {
		return appleKey;
	}
	
}
