package com.fantingame.game.gamecenter.sdk;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.gamecenter.bo.UserToken;
import com.fantingame.game.gamecenter.constant.ServiceReturnCode;
import com.fantingame.game.gamecenter.constant.WebApiCode;
import com.fantingame.game.gamecenter.dao.UserInfoDao;
import com.fantingame.game.gamecenter.exception.ServiceException;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.util.EncryptUtil;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.json.Json;

public class GameApiSdk {

	private final static Logger LOG = Logger.getLogger(GameApiSdk.class);

	private final static String HTTP_URL_HEAD = "http://";
//	private final static String HOST = "linux64.cn:8080";
	private static GameApiSdk sdk;

	private static String MD5KEY="99712321$$%@ASDF1467KK98";
	private Map<String, Integer> payMap;
	
	private Map<String, Map<String, Object>> payList;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	private GameApiSdk() {
	}

	public static GameApiSdk getInstance() {
		synchronized (GameApiSdk.class) {
			if (sdk == null) {
				sdk = new GameApiSdk();
			}
		}
		return sdk;
	}

	@SuppressWarnings("unchecked")
	public UserToken loadUserToken(String partnerUserId, String partnerId, String serverId, String qn,GameServer gameServer, Map<String, String> params) {
		if (StringUtils.isBlank(partnerUserId) || StringUtils.isBlank(partnerId) || StringUtils.isBlank(serverId) || StringUtils.isBlank(qn)) {
			throw new ServiceException(WebApiCode.PARAM_ERROR, "loadUserToken失败，partnerUserId="+partnerUserId+",partnerId="+partnerId+",serverId="+serverId+",qn="+qn);
		}
		try {
			String url = HTTP_URL_HEAD + gameServer.getServerUrl()+":"+gameServer.getApiPort()+ "/gameApi/loadUserToken.do";

			String imei = params.get("imei");
			String mac = params.get("mac");
			String idfa = params.get("idfa");
			String ip = params.get("ip");
			String mobile = params.get("mobile");
			String extInfo = params.get("extInfo");

			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("partnerUserId", partnerUserId);
			paraMap.put("partnerId", partnerId);
			paraMap.put("serverId", serverId);
			paraMap.put("qn", qn);
			paraMap.put("imei", imei);
			paraMap.put("mac", mac);
			paraMap.put("idfa", idfa);
			paraMap.put("ip", ip);
			paraMap.put("mobile", mobile);
			// paraMap.put("extInfo", extInfo);
			
			String jsonStr = UrlRequestUtils.execute(url, paraMap, UrlRequestUtils.Mode.GET);
			Map<String, Object> ret = Json.toObject(jsonStr, Map.class);
			Map<String, String> map = (Map<String, String>) ret.get("userToken");
			
			UserToken tk = new UserToken();
			tk.setPartnerId(map.get("partnerId"));
			tk.setPartnerUserId(map.get("partnerUserId"));
			tk.setServerId(map.get("serverId"));
			tk.setToken(map.get("token"));
			tk.setUserId(map.get("userId"));
			tk.setExtInfo(extInfo);		
			return tk;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean doPayment(String partnerId, String serverId, String partnerUserId, String channel, String orderId, BigDecimal amount, int gold, String userIp, String remark,GameServer gameServer) {

		try {
			String url = HTTP_URL_HEAD + gameServer.getServerUrl()+":"+gameServer.getApiPort()+ "/gameApi/payment.do";
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("partnerId", partnerId);
			paraMap.put("serverId", serverId);
			paraMap.put("partnerUserId", partnerUserId);
			paraMap.put("channel", channel);
			paraMap.put("orderId", orderId);
			paraMap.put("amount", String.valueOf(amount));
			paraMap.put("gold", String.valueOf(gold));
			paraMap.put("userIp", userIp);
			paraMap.put("remark", remark);
			String timestamp = System.currentTimeMillis()+"";
			paraMap.put("timestamp", timestamp);
			LOG.info("do payment.partnerId[" + partnerId + "], serverId[" + serverId + "], partnerUserId[" + partnerUserId + "], channel[" + channel + "], orderId[" + orderId + "],  amount[" + String.valueOf(amount) + "], gold["
    				+ gold + "], userIp[" + userIp + "], remark[" + remark + "]");
			String sign =  EncryptUtil.getMD5((partnerId+serverId+partnerUserId+channel+orderId+String.valueOf(amount)+String.valueOf(gold)+ timestamp + MD5KEY));
			paraMap.put("sign", sign);
			String jsonStr = UrlRequestUtils.execute(url, paraMap, UrlRequestUtils.Mode.GET);
			Map<String, Object> retVal = Json.toObject(jsonStr, Map.class);
			int rc = Integer.parseInt(retVal.get("rc").toString());
			return rc == ServiceReturnCode.SUCCESS;

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}

	}
	
	/**
	 * 获取套餐
	 * 
	 * @param serverId
	 * @param playerIds
	 * @param gold
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getSystemGold(String serverId, String amount,GameServer gameServer) {
		if (payList != null && !payList.isEmpty()) {
			return (Integer)payList.get(amount).get("gold");
		}
		payList = new HashMap<String, Map<String, Object>>();
		try {
			String url = HTTP_URL_HEAD + gameServer.getServerUrl()+":"+gameServer.getApiPort()+ "/gameApi/getPaySettings.do";
			Map<String, String> paraMap = new HashMap<String, String>();
			String jsonStr = UrlRequestUtils.execute(url, paraMap, UrlRequestUtils.Mode.GET);
			Map<String, List<Map<String, Object>>> retVal = Json.toObject(jsonStr, Map.class);
			List<Map<String, Object>> list = retVal.get("list");
			for (Map<String, Object> item : list) {
				BigDecimal money = new BigDecimal((Double) item.get("money"));
				payList.put(Double.toString(money.doubleValue()), item);
			}
			return (Integer)payList.get(amount).get("gold");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return 0;
		}
	}

	/**
	 * 获取套餐
	 * 
	 * @param serverId
	 * @param playerIds
	 * @param gold
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getSystemGoldSetId(String serverId, String amount,GameServer gameServer) {
		if (payMap != null && !payMap.isEmpty()) {
			return payMap.get(amount);
		}
		payMap = new HashMap<String, Integer>();
		try {
			String url = HTTP_URL_HEAD + gameServer.getServerUrl()+":"+gameServer.getApiPort()+ "/gameApi/getPaySettings.do";
			Map<String, String> paraMap = new HashMap<String, String>();
			String jsonStr = UrlRequestUtils.execute(url, paraMap, UrlRequestUtils.Mode.GET);
			Map<String, List<Map<String, Object>>> retVal = Json.toObject(jsonStr, Map.class);
			List<Map<String, Object>> list = retVal.get("list");
			for (Map<String, Object> item : list) {
				BigDecimal money = new BigDecimal((Double) item.get("money"));
				payMap.put(Double.toString(money.doubleValue()), (Integer) item.get("systemGoldSetId"));
			}

			return payMap.get(amount);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return 0;
		}
	}
	
	/**
	 * 获得恺英安卓服务器组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getServers(){
		try {
			String url = HTTP_URL_HEAD+"210.65.163.94:81"+ "/webApi/getServers.do";
			Map<String, String> paraMap = new HashMap<String, String>();
			String jsonStr = UrlRequestUtils.execute(url, paraMap, UrlRequestUtils.Mode.GET);
			Map<String, List<Map<String, String>>> retVal = Json.toObject(jsonStr, Map.class);
			return retVal.get("slist");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	
	
	/**
	 * 获得恺英安卓服务器组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getUserTemp(String kingnetid,String sid){
		try {
			String url = HTTP_URL_HEAD+"210.65.163.94:81"+ "/webApi/getUserTemp.do";
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("kingnetid", kingnetid);
			paraMap.put("sid", sid);
			String jsonStr = UrlRequestUtils.execute(url, paraMap, UrlRequestUtils.Mode.GET);
			Map<String, Object> retVal = Json.toObject(jsonStr, Map.class);
			return (Map<String, Object>)retVal.get("user");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 恺英平台充值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> kaiying(HttpServletRequest req){
		try {
			String url = HTTP_URL_HEAD+"210.65.163.94:80"+ "/webApi/kaiYingTwPayment.do";
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("ts", req.getParameter("ts"));
			paraMap.put("sig", req.getParameter("sig"));
			paraMap.put("kda",req.getParameter("kda"));
			paraMap.put("user_id",req.getParameter("user_id"));
			paraMap.put("sid",req.getParameter("sid"));
			paraMap.put("number",req.getParameter("number"));
			paraMap.put("amount",req.getParameter("amount"));
			paraMap.put("role_id",req.getParameter("role_id"));
			paraMap.put("order_id",req.getParameter("order_id"));
			paraMap.put("active1",req.getParameter("active1"));
			paraMap.put("active2",req.getParameter("active2"));
			paraMap.put("pay_ref",req.getParameter("pay_ref"));
			paraMap.put("app_extra1",req.getParameter("app_extra1"));
			paraMap.put("app_extra2",req.getParameter("app_extra2"));
			String jsonStr = UrlRequestUtils.execute(url, paraMap, UrlRequestUtils.Mode.POST);
			Map<String, Object> retVal = Json.toObject(jsonStr, Map.class);
			return retVal;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	
	
	/**
	 * 获取用户信息
	 * 
	 * @param serverId
	 * @param playerIds
	 * @param gold
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getUser(String partnerUserId, String serverId, String partnerId,GameServer gameServer) {
		try {
			String url = HTTP_URL_HEAD + gameServer.getServerUrl()+":"+gameServer.getApiPort()+ "/gameApiAdmin/getUser.do";
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("partnerUserId", partnerUserId);
			paraMap.put("serverId", serverId);
			paraMap.put("partnerId", partnerId);
			String jsonStr = UrlRequestUtils.execute(url, paraMap, UrlRequestUtils.Mode.GET);
			Map<String, Object> retVal = Json.toObject(jsonStr, Map.class);
			Map<String, String> o = (Map<String, String>)retVal.get("result");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("roleid", o.get("userId"));
			result.put("rolename", o.get("username"));
			result.put("level", o.get("level"));
			result.put("gold", o.get("goldNum"));
			return result;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 检查设备Token
	 * 
	 * @param partnerId
	 * @param serverId
	 * @param userId
	 * @param deviceToken
	 */
	@SuppressWarnings("unchecked")
	public void checkDeviceToken(String userId, String deviceToken, GameServer gameServer) {
		String url = HTTP_URL_HEAD + gameServer.getServerUrl()+":"+gameServer.getApiPort()+ "/gameApi/checkDeviceToken.do";
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("userId", userId);
		paraMap.put("deviceToken", deviceToken);
		String jsonStr = UrlRequestUtils.execute(url, paraMap, UrlRequestUtils.Mode.POST);
		Map<String, Object> retVal = Json.toObject(jsonStr, Map.class);
		LOG.info("checkDeviceToken :" + retVal.get("rc"));
		
		return ;
	}

	public static void main(String[] args) {
		// Map<String, UserToken> map =
		// Json.toObject("{\"userToken\":{\"serverId\":\"t1\",\"token\":\"06B56BF0E54EF06B8D75A8AEA872476C\",\"userId\":\"2372373da1db476385736464615e7c1e\",\"partnerUserId\":\"22887719\",\"partnerId\":\"1001\"}}\"",
		// Map.class);
		// System.out.println(map);
//		System.out.println(GameApiSdk.getInstance().getSystemGoldSetId("h1", "200"));
	}

}
