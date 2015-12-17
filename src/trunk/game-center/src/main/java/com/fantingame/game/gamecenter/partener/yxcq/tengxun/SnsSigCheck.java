package com.fantingame.game.gamecenter.partener.yxcq.tengxun;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.fantingame.game.gamecenter.util.MD5;

public class SnsSigCheck {

	private final static Logger logger = Logger.getLogger(SnsSigCheck.class);
	
	// 编码方式
    private static final String CONTENT_CHARSET = "UTF-8";
   
    // HMAC算法
    private static final String HMAC_ALGORITHM = "HmacSHA1";
    
    public static void main(String[] args) {
    	//System.out.println(SnsSigCheck.encodeUrl("/v3/user/get_info"));
    	//appkey：228bf094169a40a3bd188ba37ebe8723
    	//HTTP请求方式：GET
    	//请求的URI路径（不含HOST）：/v3/user/get_info
    	//请求参数：openid=11111111111111111&openkey=2222222222222222&appid=123456&pf=qzone&format=json&userip=112.90.139.30 
    	
    	String method = "GET";
    	String url_path = "/webApi/yxcqTengxunPayment.do";
    	String appkey = "XTGcDu8adveK9QS5";
    	//"appid": 100703379,
        //"openid": "A3284A812ECA15269F85AE1C2D94EB37",
        //"openkey": "933FE8C9AB9C585D7EABD04373B7155F",
        //"userip": "192.168.5.114"
//    	String appid = "100703379";
//    	String openid = "A3284A812ECA15269F85AE1C2D94EB37";
//    	String openkey = "933FE8C9AB9C585D7EABD04373B7155F";
//    	String userip = "192.168.5.114";
    	
//    	+ "appid=1103285451&"
//    	+ "appmeta="
//    	+ "&billno=-APPSX24902-20141027-1854430194"
//    	+ "&channel_id=2002-android-2002-qq-1103285451-E82F6A120227FF960BD61D7BEDFE4DB8-qq"
//    	+ "&clientver=android"
//    	+ "&discountid="
//    	+ "&giftcoins=0"
//    	+ "&openid=E82F6A120227FF960BD61D7BEDFE4DB8"
//    	+ "&paychannel=qqpoint"
//    	+ "&paychannelsubid=1"
//    	+ "&payitem=50"
//    	+ "&providetype=7"
//    	+ "&token=B03FB07B620C1BA66C26A8685BB02D0927431"
//    	+ "&ts=1414407283"
//    	+ "&version=v3"
//    	+ "&zoneid=3"
//    	+ "&sig=ALmHv6btdFSh3yEkHjt91cftLtc%3D
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("appid", "1103285451");
    	params.put("appmeta", "");
    	params.put("billno", "-APPSX24902-20141027-1854430194");
    	params.put("channel_id", "2002-android-2002-qq-1103285451-E82F6A120227FF960BD61D7BEDFE4DB8-qq");
    	params.put("clientver", "android");
    	params.put("discountid", "");
    	params.put("giftcoins", "0");
    	params.put("openid", "E82F6A120227FF960BD61D7BEDFE4DB8");
    	params.put("paychannel", "qqpoint");
    	params.put("paychannelsubid", "1");
    	params.put("payitem", "50");
    	params.put("providetype", "7");
    	params.put("token", "B03FB07B620C1BA66C26A8685BB02D0927431");
    	params.put("ts", "1414407283");
    	params.put("version", "v3");
    	params.put("zoneid", "3");
    	System.out.println(SnsSigCheck.verifySig(method, url_path, params, appkey + "&", URLDecoder.decode("ALmHv6btdFSh3yEkHjt91cftLtc%3D")));
    	System.out.println(URLDecoder.decode("ALmHv6btdFSh3yEkHjt91cftLtc%3D"));
//    	params.put("openid", openid);
//    	params.put("openkey", openkey);
//    	params.put("appid", appid);
//    	params.put("userip", userip);
    	
//    	params.put("pf", "qzone");
//    	params.put("format", "json");
//    	params.put("userip", "112.90.139.30");
//    	System.out.println(SnsSigCheck.makeSource(method, url_path, params).equals("GET&%2Fv3%2Fuser%2Fget_info&appid%3D123456%26format%3Djson%26openid%3D11111111111111111%26openkey%3D2222222222222222%26pf%3Dqzone%26userip%3D112.90.139.30"));
//    	System.out.println(SnsSigCheck.verifySig(method, url_path, params, appkey + "&", "FdJkiDYwMj5Aj1UG2RUPc83iokk="));
//    	System.out.println("FdJkiDYwMj5Aj1UG2RUPc83iokk=");
//    	long t = new Date().getTime();
//    	System.out.println(t);
//    	System.out.println(MD5.MD5Encode(appkey + t));
//    	System.out.println(MD5.MD5Encode("XTGcDu8adveK9QS5" + "1414407283"));
//    	+ ""

    }
    
	/**
	 * URL编码 
	 * @param input
	 * @return
	 */
	public static String encodeUrl(String input) {
		try {
			return URLEncoder.encode(input, CONTENT_CHARSET).replace("+", "%20").replace("*", "%2A");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
			return null;
		}
	}
	
	/**
	 * 生成签名
	 * @param method
	 * @param url_path
	 * @param params
	 * @param secret
	 * @return
	 */
	public static String makeSig(String method, String url_path, Map<String, String> params, String secret) {
		logger.info("method=" + method);
		logger.info("url_path=" + url_path);
		logger.info("params=" + params);
		logger.info("secret=" + secret);
		
		String sig = null;
		try {
			Mac mac = Mac.getInstance(HMAC_ALGORITHM);
			SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(CONTENT_CHARSET), mac.getAlgorithm());
			mac.init(secretKey);
			String mk = makeSource(method, url_path, params);
			byte[] hash = mac.doFinal(mk.getBytes(CONTENT_CHARSET));
			
			// base64
			sig = new String(Base64Coder.encode(hash));
		} catch (Exception e) {
			logger.error(e);
		}
		return sig;
	}
	
	/**
	 * 生成签名所需源串
	 * @param method
	 * @param url_path
	 * @param params
	 * @return
	 */
	public static String makeSource(String method, String url_path, Map<String, String> params) {
		Object[] keys = params.keySet().toArray();
		Arrays.sort(keys);
		
		StringBuilder buffer = new StringBuilder(128);
		buffer.append(method.toUpperCase()).append("&").append(encodeUrl(url_path)).append("&");
		StringBuilder buffer2 = new StringBuilder();
		for (int i = 0; i < keys.length; i++) {
			buffer2.append(keys[i]).append("=").append(params.get(keys[i]));
			if (i != keys.length - 1) {
				buffer2.append("&");
			}
		}
		buffer.append(encodeUrl(buffer2.toString()));
		return buffer.toString();
	}
	
	public static boolean verifySig(String method, String url_path, Map<String, String> params, String secret, String sig) {
		params.remove("sig");
		codePayValue(params);
		String sig_new = makeSig(method, url_path, params, secret);
		logger.info("SnsSigCheck sig_new=" + sig_new + " sig=" + sig);
		return sig_new.equals(sig);
	}
	
	public static void codePayValue(Map<String, String> params) {
		Set<String> keySet = params.keySet();
		Iterator<String> itr = keySet.iterator();
		while (itr.hasNext()) {
			String key = (String) itr.next();
			String value = (String) params.get(key);
			value = encodeValue(value);
			params.put(key, value);
		}
	}
	
	public static String encodeValue(String s) {
		String rexp = "[0-9a-zA-Z!*\\(\\)]";
        StringBuffer sb = new StringBuffer(s);
        StringBuffer sbRtn = new StringBuffer();
        Pattern p = Pattern.compile(rexp);
        char temp;
        String tempStr;
        
        for (int i = 0; i < sb.length(); i++) 
        {
            temp = sb.charAt(i);
            tempStr = String.valueOf(temp);
            Matcher m = p.matcher(tempStr);

            boolean result = m.find();
            if (!result) {
                tempStr = hexString(tempStr);
            }
            sbRtn.append(tempStr);
        }

        return sbRtn.toString();
	}
	
	private static String hexString(String s) {
		byte[] b = s.getBytes();
		String retStr = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			retStr = "%" + hex.toUpperCase();
		}
		return retStr;
	}
	// http://msdk.qq.com/auth/verify_login/?sig=1&timestamp=12312341&encode=1&appid=100703379&openid=A3284A812ECA15269F85AE1C2D94EB37&openkey=933FE8C9AB9C585D7EABD04373B7155F&userip=192.168.5.114
}
