package com.fantingame.game.gamecenter.partener.pada;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.util.MD5;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;


/**
 * 艺果
 * @author Administrator
 *
 */

public class PadaSdk {

	private static final Logger logger = Logger.getLogger(PadaSdk.class);

	private static PadaSdk ins;
	private static Properties prop;

	private static String appid;
	private static String appkey;
	private static String secretkey;
	private static String url;
	
	public static PadaSdk instance() {
		synchronized (PadaSdk.class) {
			if (ins == null) {
				ins = new PadaSdk();
			}
		}
		return ins;
	}

	private PadaSdk() {
		loadSdkProperties();
	}
	
	public void reload() {
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appid = prop.getProperty("PaDaSdk.appid");
			appkey = prop.getProperty("PaDaSdk.appkey");
			secretkey = prop.getProperty("PaDaSdk.secretkey");
			url = prop.getProperty("PaDaSdk.url");
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 验证登陆
	 * @param roleId
	 * @param roleToken
	 * @return
	 */
	public static boolean valifyLogin(String roleId, String roleToken) {
		String sign = MD5.MD5Encode("appId=" + appid + "&roleId=" + roleId + "&roleToken=" + roleToken + "&" + appkey);
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", appid);
		params.put("roleId", roleId);
		params.put("roleToken", roleToken);
		params.put("sign", sign);
		
		logger.info("appId=" + roleId + "&roleId=" + roleId + "&roleToken=" + roleToken + "&sign=" + sign);
		
		String jsonStr = UrlRequestUtils.execute(url, params, Mode.GET);
		Map<String, Object> ret = Json.toObject(jsonStr, Map.class);
		
		logger.info("rescode=" + ret.get("rescode") + "&resmsg=" + ret.get("resmsg"));
		
		int rescode = (Integer) ret.get("rescode");
		if (rescode == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 验证支付
	 * @param obj
	 * @return
	 */
	public static boolean valifyPay(PadaPaymentObj obj) {
		Map<String, String> params = new HashMap<String, String>();
		String appId = obj.getAppId();
		String cpOrderId = obj.getCpOrderId();
		String cpExInfo = obj.getCpExInfo();
		String roleId = obj.getRoleId();
		String orderId = obj.getOrderId();
		String orderStatus = obj.getOrderStatus();
		String payFee = obj.getPayFee();
		String productCode = obj.getProductCode();
		String productName = obj.getProductName();
		String productCount = obj.getProductCount();
		String payTime = obj.getPayTime();
		String sign = obj.getSign();
		if (Integer.valueOf(orderStatus) == 1) {
			return false;
		}
		
		String md5Sign = MD5.MD5Encode("appId=" + appId + "&cpOrderId=" + cpOrderId + "&cpExInfo=" + URLEncoder.encode(cpExInfo) + "&roleId=" + roleId + "&orderId=" + orderId
				+ "&orderStatus=" + orderStatus + "&payFee=" + payFee + "&productCode=" + productCode + "&productName=" + URLEncoder.encode(productName) + "&productCount=" + productCount
				+ "&payTime=" + payTime + "&" + secretkey);
		logger.info("PadaSdk valifyPay sign=" + sign + " md5Sign=" + md5Sign);
		if (sign.equals(md5Sign)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		
//		{"appId":"101132",
//		"cpOrderId":"0110372014112000000037",
//		"cpExInfo":"callbackurl=http://203.195.190.121:8089/webApi/padaPayment.do",
//		"roleId":"348761",
//		"orderId":"66642",
//		"orderStatus":"1",
//		"payFee":"280",
//		"productCode":"",
//		"productName":"月卡",
//		"productCount":"1",
//		"payTime":"20141120141904",
		//"sign":"fd72404df2ace9c8288ef385df573651"}>
		//<PadaPaymentObj [appId=101132, cpOrderId=0110372014112100000001, 
		//cpExInfo=,
		//roleId=351779, orderId=67327, orderStatus=2, payFee=50,
		//productCode=, productName=50钻石, productCount=1, payTime=, sign=c85f9ee8b1d59add8002eb28d1614c88]>
		
		String appId = "101132";
		String cpOrderId = "0110372014112100000001";
		String cpExInfo = "callbackurl=http://wapi.andr.hw.fantingame.com:80/webApi/padaPayment.do";
		String roleId = "351779";
		String orderId = "67327";
		String orderStatus = "2";
		String payFee = "50";
		String productCode = "";
		String productName = "50钻石";
		String productCount = "1";
		String payTime = "20141121221607";
		String sign = "c85f9ee8b1d59add8002eb28d1614c88";
		
		String md5Sign = MD5.MD5Encode("appId=" + appId + "&cpOrderId=" + cpOrderId + "&cpExInfo=" + URLEncoder.encode(cpExInfo) + "&roleId=" + roleId + "&orderId=" + orderId
				+ "&orderStatus=" + orderStatus + "&payFee=" + payFee + "&productCode=" + productCode + "&productName=" + URLEncoder.encode(productName) + "&productCount=" + productCount
				+ "&payTime=" + payTime + "&" + "a7782d001083a4814da284ab363cdb46");
		System.out.println(md5Sign);
//		String md5_32Sign = MD5_32("appId=" + appId + "&cpOrderId=" + cpOrderId + "&cpExInfo=" + URLEncoder.encode(cpExInfo) + "&roleId=" + roleId + "&orderId=" + orderId
//				+ "&orderStatus=" + orderStatus + "&payFee=" + payFee + "&productCode=" + productCode + "&productName=" + URLEncoder.encode(productName) + "&productCount=" + productCount
//				+ "&payTime=" + payTime + "&" + "0c6324ca08a88290babcfd36becb7ab6");
//		System.out.println(md5_32Sign);
		
	}
	
	public static String MD5_32(String sourceStr) {
		 String result = "";
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(sourceStr.getBytes());
	            byte b[] = md.digest();
	            int i;
	            StringBuffer buf = new StringBuffer("");
	            for (int offset = 0; offset < b.length; offset++) {
	                i = b[offset];
	                if (i < 0)
	                    i += 256;
	                if (i < 16)
	                    buf.append("0");
	                buf.append(Integer.toHexString(i));
	            }
	            result = buf.toString();
	            System.out.println("MD5(" + sourceStr + ",32) = " + result);
	            System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return result;
	}
}
