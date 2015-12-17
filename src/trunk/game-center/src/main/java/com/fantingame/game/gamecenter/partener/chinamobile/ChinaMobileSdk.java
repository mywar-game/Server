package com.fantingame.game.gamecenter.partener.chinamobile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;

public class ChinaMobileSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(ChinaMobileSdk.class);
	
	private static ChinaMobileSdk ins;

	private static Properties prop;

	private String partnerId;
	
	private Map<String,UserInfo> userMap;
	
	private Map<String,Integer> goodsMap;

	public static ChinaMobileSdk instance() {
		synchronized (ChinaMobileSdk.class) {
			if (ins == null) {
				ins = new ChinaMobileSdk();
			}
		}

		return ins;
	}

	private ChinaMobileSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			partnerId = prop.getProperty("ChinaMobileSdk.partnerId");
			userMap = new ConcurrentHashMap<String, UserInfo>();
			goodsMap = new HashMap<String, Integer>();
			goodsMap.put("000077857001", 500);
			goodsMap.put("000077857002", 500);
			goodsMap.put("000077857003", 200);
			goodsMap.put("000077857004", 200);
			goodsMap.put("000077857005", 100);
			goodsMap.put("000077857006", 850);
			goodsMap.put("000077857007", 500);
			goodsMap.put("000077857008", 4500);
			goodsMap.put("000077857009", 1000);
			goodsMap.put("000077857010", 3000);
			goodsMap.put("000077857011", 5000);
			goodsMap.put("000077857012", 10);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public UserInfo verifySession(String session){
		if(ChinaMobileSdk.instance().getUserMap().containsKey(session)){
			return ChinaMobileSdk.instance().getUserMap().get(session);
		}
		return null;
	}
	

	public boolean checkPayCallbackSign(ChinaMobilePaymentObj cb) {
		
		try {
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
	public String getPartnerId() {
		return partnerId;
	}

	public Map<String, UserInfo> getUserMap() {
		return userMap;
	}

	public Map<String, Integer> getGoodsMap() {
		return goodsMap;
	}
}
