package com.fantingame.game.gamecenter.partener.sqw;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fantingame.game.gamecenter.util.json.Json;

public class SanqiWanwanUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(SanqiWanwanUtil.class);
	
	/** 接口地址*/
	private static String BASE_URL = "http://gop.37wanwan.com/api/";
	/** 接口名字*/
	private static String apiName = "verifyUser";
	/** 游戏在平台上分配的唯一标识*/
	private static String gameId = "21";
	/** 游戏平台给游戏分配的一个私密字符串,只有游戏开发商和平台知道，用于通信加密校验 */
	private static String gameSecret = "e7c101ef867e41712616373ac9c8d20d";
	/** 开发商的名称*/
	private static String vendor = "egame";
	/** 时间戳 */
	private static String date;
	/** SDK版本*/
	private static String version = "1.0"; 
	/** 请求的集合*/
	private static Map<String, String> params = new HashMap<String, String>();

	
	public static void main(String[] args) throws Exception {
		
	}
	
	/**
	 * 验证登陆
	 * @param token
	 * @return
	 */
	public static String login(String token){
		apiName = "verifyUser";
		initRequest(gameId,gameSecret,vendor,apiName,token);
		String url = BASE_URL+apiName;//请求地址+接口名
		try {
			String info = sendPostRequest(url,params);
			Map<String, String> ret = Json.toObject(info, Map.class);
			if(ret != null && ret.containsKey("usergameid")){
				return ret.get("usergameid");
			}
		} catch (Exception e) {
			logger.error("sanqiwanwan login error!",e);
		}
		return null;
	}
	
	/**
	 * 验证支付
	 * @param payParams
	 * @return
	 */
	public static boolean verifyPay(Map<String,String> payParams,String url,String sign,String date){
		try {
			String params = sortEncoderParams(payParams);
			if(sign.equals(getAuthentication(gameId,gameSecret,vendor,date,url,params))){
				return true;
			}
		} catch (Exception e) {
			logger.error("sanqiwanwan verifyPay error!",e);
		}
		return false;
	}

	
	/**
	 * 初始化,填入你需要初始化的参数
	 * 
	 * @param id 游戏在平台上分配的唯一标识
	 * @param secret 游戏平台给游戏分配的一个私密字符串
	 * @param vendorName 开发商的名称
	 * @param api 接口名字
	 */
	private static void initRequest(String id, String secret, String vendorName, String api,String token) {
//		String gameId = id;
//		String gameSecret = secret;
//		String vendor = vendorName;
//		String apiName = api;
//		/** 这里键值对 是根据你请求的参数来设置的,因为verifyUser 就一个参数"token" 所以就只写一个.*/
		params.put("token",token);
	}

	/**
	 * 请求网络
	 * @param path url路径
	 * @param apiName 接口名字
	 * @param paramsMap 参数集合
	 * @return json字符串
	 * @throws Exception
	 */
	public static String sendPostRequest(String path,Map<String, String> paramsMap) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		conn.setReadTimeout(9 * 1000);
		conn.setDoOutput(true);
		conn.setRequestProperty("Connection","Keep-Alive");
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		// 设置请求头
		conn.setRequestProperty("Accept","application/json; version=" + version);
		/** 计算时间戳 */
		date = getDate();
		conn.setRequestProperty("Date",date);
		/** 设置请求头的参数不需要encode*/
		String headerParam = sortParams(paramsMap);
		conn.setRequestProperty("Authentication",getAuthentication(gameId,gameSecret,vendor,date,apiName,headerParam));
		/** 设置请求体参数需要encode */
		String bodyParam = sortEncoderParams(paramsMap);
		byte[] entitydata = bodyParam.getBytes();
		conn.setRequestProperty("Content-Length",String.valueOf(entitydata.length)); // 传递数据的长据
		conn.connect();
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		outStream.flush();
		outStream.close();
		logger.info("ResponseCode=" +conn.getResponseCode());
		if(conn.getResponseCode() != 200){
			logger.info("connent failed !");
			conn.disconnect();
			return null;
		}else{
			String result = inputStream2String(conn.getInputStream());
			logger.info("connent success !");
			logger.info("result = " + result);
			conn.disconnect();
			return result;
		}
	}

	/** 将输入流转变为字符串 */
	public static String inputStream2String(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;){
			out.append(new String(b,0,n));
		}
		String str = out.toString();
		return str;
	}
	/**
	 * 对参数进行排序
	 * @param params
	 * @return
	 */
	public static String sortParams(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (String key : keys){
			String value = params.get(key);
			prestr = prestr + key + "=" + value + "&";
		}
		prestr = prestr.substring(0,prestr.length() - 1);
		return prestr;
	}
	/**
	 * 对参数进行排序+encode
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String sortEncoderParams(Map<String, String> params) throws UnsupportedEncodingException {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (String key : keys){
			String value = params.get(key);
			prestr = prestr + key + "=" + URLEncoder.encode(value,"utf-8") + "&";
		}
		prestr = prestr.substring(0,prestr.length() - 1);
		return prestr;
	}
	/**
	 * 生成时间戳
	 * @return
	 */
	public static String getDate() {
		SimpleDateFormat dfs = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ",Locale.US);
		return dfs.format(new Date()).toString() + "GMT";
	}
	/**
	 * 计算授权号
	 * @param gameId
	 * @param gameSecret
	 * @param vendor
	 * @param date
	 * @param apiName
	 * @param params
	 * @return
	 */
	private static String getAuthentication(String gameId, String gameSecret, String vendor, String date, String apiName, String params) {
		String sign = getSign(date,apiName,params,gameSecret);
		String authentication = vendor + " " + gameId + ":" + sign;
		return authentication;
	}
	/**
	 * 计算签名
	 * @param date
	 * @param apiName
	 * @param params
	 * @param gameSecret
	 * @return
	 */
	private static String getSign(String date, String apiName, String params, String gameSecret) {
		String str = date + ":" + apiName + ":" + params + ":" + gameSecret;
		return md5(str);
	}
	/**
	 * MD5编码
	 * @param data
	 * @return
	 */
	public static String md5(String data) {
		try{
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(data.getBytes());
			byte[] digest = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : digest){
				sb.append(String.format("%02x",b & 0xFF));
			}
			return sb.toString();
		}catch(Exception e){
			return "";
		}
	}

}
