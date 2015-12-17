package com.fantingame.game.gamecenter.partener.huawei;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 华为sdk
 * 
 * @author yezp
 */
public class HuaWeiSdk extends BaseSdk {
	private static final Logger logger = Logger.getLogger(HuaWeiSdk.class);

	private static HuaWeiSdk ins;
	private final static String PROTOCOL_HEAD = "https://";
	private static Properties prop;
	private String publicKey;
	private String host;

	public static HuaWeiSdk instance() {
		synchronized (HuaWeiSdk.class) {
			if (ins == null) {
				ins = new HuaWeiSdk();
			}
		}
		return ins;
	}

	private HuaWeiSdk() {
		loadSdkProperties();
	}

	public void reload() {
		loadSdkProperties();
	}

	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource(
					"sdk.properties"));
			publicKey = prop.getProperty("HuaWeiSdk.publicKey");
			host = prop.getProperty("HuaWeiSdk.host");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean checkPayCallbackSign(HuaWeiPaymentObj paymentObj) {
		return CommonUtil.rsaDoCheck(paymentObj.getMap(), paymentObj.getSign(),
				publicKey);
	}

	/**
	 * Host: api.vmall.com 
	 * Content-Type: application/x-www-form-urlencoded
	 * Content-Length: 85
	 * 
	 * nsp_svc=OpenUP.User.getInfo&nsp_ts=1358237366&access_token=mF_9.B5f-4.1JqM
	 * 
	 * @param token
	 * @return
	 */
	public HwUserInfo getUid(String token, long time) {
		// String url = PROTOCOL_HEAD + host + "?" + "nsp_svc=OpenUP.User.getInfo&nsp_ts=" + time + "&access_token=" + URLEncoder.encode(token, "utf-8");
		logger.info("HuaWeiSdk login token:" + token);
		String url = PROTOCOL_HEAD + host;
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("nsp_svc", "OpenUP.User.getInfo");
		paraMap.put("nsp_ts", Long.toString(time));
		paraMap.put("access_token", token);
		
		String jsonStr = UrlRequestUtils.executeHttps(url, paraMap, Mode.POST);
		logger.info("HuaWeiSdk.userInfo result:" + jsonStr);
		return Json.toObject(jsonStr, HwUserInfo.class);
	}

}
