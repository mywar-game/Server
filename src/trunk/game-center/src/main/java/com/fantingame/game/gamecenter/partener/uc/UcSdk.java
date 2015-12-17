package com.fantingame.game.gamecenter.partener.uc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.EncryptUtil;
import com.fantingame.game.gamecenter.util.json.Json;

public class UcSdk extends BaseSdk {
	private static final Logger logger = Logger.getLogger(UcSdk.class);

	@SuppressWarnings("unused")
	private static ObjectMapper objectMapper = new ObjectMapper();

	private static UcSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "http://";
	private String partnerId;
	private String host;
	private UcGame ucGame;
	private String apiKey;

	public static UcSdk instance() {
		synchronized (UcSdk.class) {
			if (ins == null) {
				ins = new UcSdk();
			}
		}

		return ins;
	}

	private UcSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			host = prop.getProperty("UcSdk.host");
			ucGame = new UcGame();
			ucGame.setCpId(Integer.valueOf(prop.getProperty("UcSdk.cpId", "0")));
			ucGame.setGameId(Integer.valueOf(prop.getProperty("UcSdk.gameId", "0")));
			apiKey = prop.getProperty("UcSdk.apiKey");
			partnerId = prop.getProperty("UcSdk.partnerId");
			ucGame.setChannelId(prop.getProperty("UcSdk.channelId", ""));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 验证SID信息
	 * 
	 * @param sid
	 * @param serverId
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public SidInfoResponse sidInfo(String sid, String channelId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", Long.toString(System.currentTimeMillis()));// 当前系统时间
		params.put("service", "ucid.user.sidInfo");
		ucGame.setServerId(1333);
		// ucGame.setChannelId(channelId);
		params.put("game", ucGame);

		Map<String, String> data = new HashMap<String, String>();
		data.put("sid", sid);// 在uc sdk登录成功时，游戏客户端通过uc
								// sdk的api获取到sid，再游戏客户端由传到游戏服务器
		params.put("data", data);

		params.put("encrypt", "md5");

		String signSource = ucGame.getCpId() + "sid=" + sid + apiKey;

		String sign = EncryptUtil.getMD5(signSource);
		params.put("sign", sign.toLowerCase());

		String jsonStr = doPost(PROTOCOL_HEAD + host + "/ss/", Json.toJson(params));

		logger.info("UcSdk.sidInfo result:" + jsonStr);

		return Json.toObject(jsonStr, SidInfoResponse.class);
	}

	public String doPost(String url, String body) {
		StringBuffer stringBuffer = new StringBuffer();
		HttpEntity entity = null;
		BufferedReader in = null;
		HttpResponse response = null;
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpParams params = httpclient.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 20000);
			HttpConnectionParams.setSoTimeout(params, 20000);
			HttpPost httppost = new HttpPost(url);
			httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");

			httppost.setEntity(new ByteArrayEntity(body.getBytes("UTF-8")));
			response = httpclient.execute(httppost);
			entity = response.getEntity();
			in = new BufferedReader(new InputStreamReader(entity.getContent()));
			String ln;
			while ((ln = in.readLine()) != null) {
				stringBuffer.append(ln);
				stringBuffer.append("\r\n");
			}
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (IllegalStateException e2) {
			e2.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
					in = null;
				} catch (IOException e3) {
					e3.printStackTrace();
				}
			}
		}
		return stringBuffer.toString();
	}

	public boolean checkPayCallbackSign(PayCallbackResponse rsp) {
		String signSource = ucGame.getCpId() + "amount=" + rsp.getData().getAmount() + "callbackInfo=" + rsp.getData().getCallbackInfo() + "failedDesc="
				+ rsp.getData().getFailedDesc() + "gameId=" + rsp.getData().getGameId() + "orderId=" + rsp.getData().getOrderId() + "orderStatus=" + rsp.getData().getOrderStatus()
				+ "payWay=" + rsp.getData().getPayWay() + "serverId=" + rsp.getData().getServerId() + "ucid=" + rsp.getData().getUcid() + apiKey;
		logger.info("Uc sign:" + signSource);
		String sign = EncryptUtil.getMD5(signSource);
		logger.info("Uc md5Sign:" + sign);
		if (sign.toLowerCase().equals(rsp.getSign().toLowerCase())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为会员 0 非会员 1 会员
	 * 
	 * @param sidInfo
	 * @return
	 */
	public int isUcVip(String sid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", Long.toString(System.currentTimeMillis()));// 当前系统时间
		params.put("service", "ucid.vip.isVip");
		ucGame.setServerId(1333);
		// ucGame.setChannelId(channelId);
		params.put("game", ucGame);

		Map<String, String> data = new HashMap<String, String>();
		// 在uc sdk登录成功时，游戏客户端通过uc 
		// sdk的api获取到sid，再游戏客户端由传到游戏服务器
		data.put("sid", sid);
		params.put("data", data);
		params.put("encrypt", "md5");

		String signSource = ucGame.getCpId() + "sid=" + sid + apiKey;
		String sign = EncryptUtil.getMD5(signSource);
		params.put("sign", sign.toLowerCase());

		String jsonStr = doPost(PROTOCOL_HEAD + host + "/ss/", Json.toJson(params));
		logger.info("UcSdk.isUcVip result:" + jsonStr);
		
		VipInfoResponse vipInfo = Json.toObject(jsonStr, VipInfoResponse.class);
		return vipInfo.getData().getStatus();
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public static void main(String[] args) throws Exception {
		String json = "{\"id\":1376815057712,\"state\":{\"code\":10,\"msg\":\"无效的请求数据,校验签名失败\"},\"data\":\"\"}";
		SidInfoResponse s = Json.toObject(json, SidInfoResponse.class);
		System.out.println(s);
	}

	public String getPartnerId() {
		return partnerId;
	}
}
