package com.fantingame.game.gamecenter.partener.yxcq.legame;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.MD5;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;

public class LegameSdk extends BaseSdk {
	
	private static final Logger logger = Logger.getLogger(LegameSdk.class);
	
	private static LegameSdk ins;

	private static Properties prop;

	private int cpid;
	private String serverKey;
	private String queryUrl;

	public static LegameSdk instance() {
		synchronized (LegameSdk.class) {
			if (ins == null) {
				ins = new LegameSdk();
			}
		}
		return ins;
	}

	private LegameSdk() {
		loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			cpid = Integer.valueOf(prop.getProperty("LegameSdk.cpid"));
			serverKey = prop.getProperty("LegameSdk.serverKey");
			queryUrl = prop.getProperty("LegameSdk.queryUrl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public Map<String, String> verifySession(String sid) {
		String m = "Query_user";
		String checkSign = MD5.MD5Encode(cpid + "&" + m + "&" + sid + "&" + serverKey);
		logger.info("Legame verifySession checkSign = " + checkSign);
		
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("m", m);
		paraMap.put("sid", sid);
		paraMap.put("cpid", Integer.toString(cpid));
		paraMap.put("checksign", checkSign);
		logger.info("queryUrl=" + queryUrl + "?m=" + m + "&sid=" + sid + "&cpid=" + cpid + "&checksign=" + checkSign);
		
		String result = UrlRequestUtils.execute(queryUrl, paraMap, Mode.POST);
		
		logger.info("Legame verifySession result = " + result);
		Map<String, String> map = parseLogin(result);
		
		String error = map.get("error");
		String uid = map.get("uid");
		String nickName = map.get("nickName");
		String checksign = map.get("checksign");
		
		Map<String, String> resultMap = new HashMap<String, String>();
		if (error.equals("0")) {
			
			String returnSign = MD5.MD5Encode(nickName + "&" + uid + "&" + serverKey, "UTF-8");
			if (returnSign.equals(checksign)) {
				// 成功
				resultMap.put("uid", uid);
				resultMap.put("nickName", nickName);
			} else {
				
			}
		}
		return resultMap;
	}
	
	protected static Map<String, String> parseLogin(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		InputStream in = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			in = new ByteArrayInputStream(xml.getBytes("utf8"));
			Document document = builder.parse(in);
			Element root = document.getDocumentElement();
			NodeList bookNodes1 = root.getChildNodes();
			NodeList headNodes = document.getElementsByTagName("info");
			
			if (headNodes != null) {
				for (int i = 0; i < headNodes.getLength();) {
					Node b = headNodes.item(0);
					String type = b.getAttributes().getNamedItem("type").getNodeValue();
					String error = b.getAttributes().getNamedItem("error").getNodeValue();
					map.put("type", type);
					map.put("error", error);
					break;
				}
			}
			
			if (bookNodes1 != null) {
				for (int i = 0; i < bookNodes1.getLength(); i++) {
					Node book = bookNodes1.item(i);   
					if (book.getNodeType() == Node.ELEMENT_NODE) {   
						String uid = book.getAttributes().getNamedItem("uid").getNodeValue();
		            	String nickName = book.getAttributes().getNamedItem("nickName").getNodeValue();
		            	String checksign = book.getAttributes().getNamedItem("checksign").getNodeValue();
		            	map.put("uid", uid);
		            	map.put("nickName", nickName);
		            	map.put("checksign", checksign);
		            	break;
					}
				}
			}
			
		} catch (Exception e) {
			logger.error(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
		return map;
	}

	public static void main(String[] args) {
		// <Legame payment:{"amount":"5.0","checksign":"71ce3274835570a75bfd6af767c42aaa","cp_ext":"","cp_id":"153","create_time":"2014-09-22 14:37:03","game_id":"183","order_no":"1409221437030101","sstatus":"支付成功","status":"1","uid":"3854263"}>
		// <Legame checkPayCallbackSign md5Sign = 0d41c2719a8425578505ebc83ba295c4 sign = 71ce3274835570a75bfd6af767c42aaa>
		
		List<String> list = new ArrayList<String>();
		list.add("order_no");
		list.add("create_time");
		list.add("amount");
		list.add("status");
		list.add("sstatus");
		list.add("cp_id");
		list.add("game_id");
		list.add("uid");
		list.add("cp_ext");
		Collections.sort(list);
		Map<String, String> map = new HashMap<String, String>();
		map.put("order_no", "1409221437030101");
		map.put("create_time", "2014-09-22 14:37:03");
		map.put("amount", "5.0");
		map.put("status", "1");
		map.put("sstatus", "支付成功");
		map.put("cp_id", "153");
		map.put("game_id", "183");
		map.put("uid", "3854263");
		map.put("cp_ext", "0111362014092200000012");
		
		try {
			StringBuilder sb = new StringBuilder();
			for (String str : list) {
				sb.append(map.get(str));
				sb.append("&");
			}
			sb.append("f3de9dae003e46c8bcdf75d7904418ee");
			String sign = MD5.MD5Encode(sb.toString(), "utf-8");
			
			System.out.println(sign);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkPayCallbackSign(LegamePaymentObj data) {
		List<String> list = new ArrayList<String>();
		list.add("order_no");
		list.add("create_time");
		list.add("amount");
		list.add("status");
		list.add("sstatus");
		list.add("cp_id");
		list.add("game_id");
		list.add("uid");
		list.add("cp_ext");
		Collections.sort(list);
		Map<String, String> map = new HashMap<String, String>();
		map.put("order_no", data.getOrder_no());
		map.put("create_time", data.getCreate_time());
		map.put("amount", data.getAmount());
		map.put("status", data.getStatus());
		map.put("sstatus", data.getSstatus());
		map.put("cp_id", data.getCp_id());
		map.put("game_id", data.getGame_id());
		map.put("uid", data.getUid());
		map.put("cp_ext", data.getCp_ext());
		
		try {
			StringBuilder sb = new StringBuilder();
			for (String str : list) {
				sb.append(map.get(str));
				sb.append("&");
			}
			sb.append(serverKey);
			String sign = MD5.MD5Encode(sb.toString(), "utf-8");
			logger.info("Legame checkPayCallbackSign md5Sign = " + sign + " sign = " + data.getChecksign());
			if (data.getChecksign().equals(sign)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
}
