package com.fantingame.game.gamecenter.partener.snail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * 蜗牛Sdk
 * 
 * @author yezp
 */
@SuppressWarnings("restriction")
public class SnailSdk extends BaseSdk {

	private static final Logger logger = Logger.getLogger(SnailSdk.class);

	private static SnailSdk ins;
	private final static String PROTOCOL_HEAD = "http://";
	private static Properties prop;
	private String appId;
	private String appKey;
	private String verifyUrl;

	public static SnailSdk instance() {
		synchronized (SnailSdk.class) {
			if (ins == null) {
				ins = new SnailSdk();
			}
		}

		return ins;
	}

	private SnailSdk() {
		loadSdkProperties();
	}

	public void reload() {
		loadSdkProperties();
	}

	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource(
					"sdk.properties"));
			appKey = prop.getProperty("DianJinSdk.appKey");
			appId = prop.getProperty("DianJinSdk.appId");
			verifyUrl = prop.getProperty("DianJinSdk.verifyUrl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 验证登陆
	 * 
	 * @param token
	 * @param uid
	 * @return
	 */
	public boolean verifySession(String uin, String sessionId) {
		try {
			if (checkUserLogin(uin, sessionId) == 1) {
				return true;
			}
		} catch (Exception e) {
			logger.error("校验出现异常", e);
		}
		return false;
	}

	/**
	 * 检查用户登陆SESSIONID是否有效
	 * 
	 * @param uin
	 *            账号ID
	 * @param sessionID
	 * @return ErrorCode 错误码:0=失败，1=成功(SessionId 有效)，2=
	 *         AppId无效，3=Act器返回给应用无效，4=参数无效， 5= Sign无效， 11=SessionId无效
	 * @throws Exception
	 */
	public int checkUserLogin(String uin, String sessionId) throws Exception {
		String act = "4";
		StringBuilder strSign = new StringBuilder();
		strSign.append(appId);
		strSign.append(act);
		strSign.append(uin);
		strSign.append(sessionId);
		strSign.append(appKey);
		String sign = md5(strSign.toString());
		System.out.println(sign);
		StringBuilder getUrl = new StringBuilder();
		getUrl.append("AppId=");
		getUrl.append(appId);
		getUrl.append("&Act=");
		getUrl.append(act);
		getUrl.append("&Uin=");
		getUrl.append(uin);
		getUrl.append("&SessionId=");
		getUrl.append(sessionId);
		getUrl.append("&Sign=");
		getUrl.append(sign);
		return GetResult(HttpGetGo(getUrl.toString()));
	}

	/**
	 * 验证支付回调
	 * 
	 * @param obj
	 * @return
	 */
	public boolean checkPayCallbackSign(SnailPaymentObj obj) {
		try {
			int result = this.payResultNotify(obj.getAppId(), obj.getAct(),
					obj.getProductName(), obj.getConsumeStreamId(),
					obj.getCooOrderSerial(), obj.getUin(), obj.getGoodsId(),
					obj.getGoodsInfo(), obj.getGoodsCount(),
					obj.getOriginalMoney(), obj.getOrderMoney(), obj.getNote(),
					obj.getPayStatus(), obj.getCreateTime(), obj.getSign());
			if (result == 1) {
				return true;
			} else {
				logger.error("蜗牛MD5校验失败！错误码：" + result);
				return false;
			}

		} catch (Exception e) {
			logger.error("蜗牛");
		}

		return false;
	}

	/**
	 * 接收支付购买结果,开发者需要在后台配置支付回传地址。
	 * 
	 * @param appId
	 * @param act
	 * @param productName
	 * @param consumeStreamId
	 * @param cooOrderSerial
	 * @param uin
	 * @param goodsId
	 * @param goodsInfo
	 * @param goodsCount
	 * @param originalMoney
	 * @param orderMoney
	 * @param note
	 * @param payStatus
	 *            支付状态：0=失败，1=成功
	 * @param createTime
	 * @param fromSign
	 * @return 支付结果 错误码(0=失败，1=成功（已处理过的，也当作成功返回） 2=AppId无效，3= Act无效，4=参数无效，5=
	 *         Sign无效
	 * @throws UnsupportedEncodingException
	 */
	public int payResultNotify(String appId, String act, String productName,
			String consumeStreamId, String cooOrderSerial, String uin,
			String goodsId, String goodsInfo, String goodsCount,
			String originalMoney, String orderMoney, String note,
			String payStatus, String createTime, String fromSign)
			throws UnsupportedEncodingException {

		StringBuilder strSign = new StringBuilder();
		strSign.append(appId);
		strSign.append(act);
		strSign.append(productName);
		strSign.append(consumeStreamId);
		strSign.append(cooOrderSerial);
		strSign.append(uin);
		strSign.append(goodsId);
		strSign.append(goodsInfo);
		strSign.append(goodsCount);
		strSign.append(originalMoney);
		strSign.append(orderMoney);
		strSign.append(note);
		strSign.append(payStatus);
		strSign.append(createTime);
		strSign.append(appKey);
		String sign = md5(strSign.toString());

		if (!this.appId.equals(appId)) {
			return 2; // appId无效
		}
		if (!"3".equals(act)) {
			return 3; // Act无效
		}
		if (!sign.toLowerCase().equals(fromSign.toLowerCase())) {
			return 5; // sign无效
		}
		if ("1".equals(payStatus))// 支付成功,
		{
			return 1;
		}

		return 0; // 错误
	}

	/**
	 * 获取服务器返回的结果
	 * 
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 *             ErrorCode 错误码:0=失败，1=成功(SessionId 有效)，2= AppId
	 *             无效，3=Act器返回给应用无效，4=参数无效，5= Sign 无效， 11=SessionId 无效 ErrorDesc
	 *             错误描述 Account 账户信息，当且仅当sessionId 有效时，返回该参数给游戏服务器
	 *             返回消息格式{"ErrorCode":"1","ErrorDesc":"有效","Account":"dfasfwef"}
	 */
	private int GetResult(String jsonStr) throws Exception {
		// 这里需要引入JSON-LIB包内的JAR
		JSONObject jo = JSONObject.fromObject(jsonStr);
		return Integer.parseInt(jo.getString("ErrorCode"));
	}

	/**
	 * 对字符串进行MD5并返回结果
	 * 
	 * @param sourceStr
	 * @return
	 */
	private String md5(String sourceStr) {
		String signStr = "";
		try {
			byte[] bytes = sourceStr.getBytes("utf-8");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(bytes);
			byte[] md5Byte = md5.digest();
			if (md5Byte != null) {
				signStr = HexBin.encode(md5Byte);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return signStr;
	}

	/**
	 * 发送GET请求并获取结果
	 * 
	 * @param getUrl
	 * @return
	 * @throws Exception
	 */
	private String HttpGetGo(String getUrl) throws Exception {
		StringBuffer readOneLineBuff = new StringBuffer();
		String content = "";
		URL url = new URL(PROTOCOL_HEAD + verifyUrl + getUrl);
		URLConnection conn = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "utf-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			readOneLineBuff.append(line);
		}
		content = readOneLineBuff.toString();
		reader.close();
		return content;
	}

}
