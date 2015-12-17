package com.fantingame.game.gamecenter.controller.paycallback.yxcq;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.yxcq.tengxun.SnsSigCheck;
import com.fantingame.game.gamecenter.partener.yxcq.tengxun.TengxunPayParamsObj;
import com.fantingame.game.gamecenter.partener.yxcq.tengxun.YXCQTengxunSdk;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.MD5;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 腾讯
 * @author Administrator
 *
 */
@Controller
public class YXCQTengxunController {
	
	private static Logger LOG = Logger.getLogger(YXCQTengxunController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	/**
	 * 新的支付接口
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/webApi/yxcqTengxunPay.do") 
	public ModelAndView mPayCallback(HttpServletRequest req, HttpServletResponse res) {
	
		PartnerService ps = serviceFactory.getBean(PartenerEnum.YXCQ_Tengxun.getPartenerId());
		
		String sessionId = "openid";
		String sessionType = "kp_actoken";
		String orgLoc = "/mpay/get_balance_m";
		
		String openid = req.getParameter("openid");
		String openkey = req.getParameter("openkey");
		String pay_token = req.getParameter("pay_token");
		String appid = YXCQTengxunSdk.instance().getQqAppId();
		String ts = req.getParameter("ts");
		String pf = req.getParameter("pf");
		String pfkey = req.getParameter("pfkey");
		String zoneid = req.getParameter("zoneid");
		String amt = req.getParameter("amt"); // 不参与查询余额
		
		// 我们的参数
		String ourSig = req.getParameter("ourSig"); // md5(order + money + appId);
		String order = req.getParameter("order");
		String money = req.getParameter("money");
		
		LOG.info("ourSig=" + ourSig + " order=" + order + " money=" + money + " ourSig=" + ourSig + " clientSig=" + MD5.MD5Encode(order + money + YXCQTengxunSdk.instance().getQqAppId()));
		if (!MD5.MD5Encode(order + money + YXCQTengxunSdk.instance().getQqAppId()).equalsIgnoreCase(ourSig)) {
			// 签名不通过
			try {
				res.getWriter().write("{\"ret\":-4,\"msg\":\"验证不通过\"}");
				LOG.info("YXCQTengxunController mPayCallback 验证不通过");
			} catch (IOException e) {
				LOG.info("YXCQTengxunController mPayCallback e=" + e.toString());
			}
			return null;
		}
		
		// 字符串格式
		Map<String, String> params = new HashMap<String, String>();
		params.put("openid", openid);
		params.put("openkey", openkey);
		params.put("pay_token", pay_token);
		params.put("appid", appid);
		params.put("ts", Long.toString(System.currentTimeMillis() / 1000));
		params.put("pf", pf);
		params.put("pfkey", pfkey);
		params.put("zoneid", zoneid);
				
		// 签名
		String sig = SnsSigCheck.makeSig("GET", YXCQTengxunSdk.instance().getGet_balance_URL(), params, YXCQTengxunSdk.instance().getQqAppKey() + "&");
		params.put("sig", sig);
		
		LOG.info("YXCQTengxunController mPayCallback params : " + params);
		LOG.info("YXCQTengxunController mPayCallback getGet_balance_URL : " + YXCQTengxunSdk.instance().getGet_balance_URL());
		
		String jsonStr = UrlRequestUtils.executeHttpsTengxun(YXCQTengxunSdk.instance().getGet_balance_url(), params, sessionId, sessionType, orgLoc, "GET");
		
		LOG.info("YXCQTengxunController mPayCallback jsonStr : " + jsonStr);
		
		Map<String, Object> resultMap = Json.toObject(jsonStr, Map.class);
		int ret = (Integer) resultMap.get("ret");
		if (ret != 0) {
			try {
				res.getWriter().write("{\"ret\":-1,\"msg\":\"请先登录\"}");
				LOG.info("YXCQTengxunController mPayCallback 请先登录");
			} catch (IOException e) {
				LOG.info(e.toString());
			}
			return null;
		}
		LOG.info("YXCQTengxunController mPayCallback amt : " + amt);

		int leftBalance = (Integer) resultMap.get("balance");
		if ((leftBalance / 10) < (Double.valueOf(money))) {
			try {
				res.getWriter().write("{\"ret\":-2,\"msg\":\"余额不足\"}");
				LOG.info("YXCQTengxunController mPayCallback 余额不足");
			} catch (IOException e) {
				LOG.info(e.toString());
			}
			return null;
		}
		// 扣费开始
		params.put("amt", amt);
		params.remove("sig");
		
		// 签名
		String paySig = SnsSigCheck.makeSig("GET", YXCQTengxunSdk.instance().getPay_m_URL(), params, YXCQTengxunSdk.instance().getQqAppKey() + "&");
		params.put("sig", paySig);
		
		LOG.info("YXCQTengxunController mPayCallback params : " + params);
		orgLoc  = "/mpay/pay_m";
		
		String payJsonStr = UrlRequestUtils.executeHttpsTengxun(YXCQTengxunSdk.instance().getPay_m_url(), params, sessionId, sessionType, orgLoc, "GET");
		
		LOG.info("YXCQTengxunController mPayCallback payJsonStr : " + payJsonStr);
		
		Map<String, Object> payResultMap = Json.toObject(payJsonStr, Map.class);
		int payRet = (Integer) payResultMap.get("ret");
		if (payRet == 0) {
			TengxunPayParamsObj obj = new TengxunPayParamsObj();
			obj.getParams().put("money", money);
			obj.getParams().put("order", order);
			obj.getParams().put("pf", pf);
			if (ps.doPayment(obj)) {
				try {
					res.getWriter().write("{\"ret\":0,\"msg\":\"支付成功\"}");
					LOG.info("YXCQTengxunController mPayCallback 支付成功");
				} catch (IOException e) {
					LOG.info(e.toString());
				}
			} else {
				try {
					res.getWriter().write("{\"ret\":-3,\"msg\":\"支付失败\"}");
					LOG.info("YXCQTengxunController mPayCallback 支付失败");
				} catch (IOException e) {
					LOG.info(e.toString());
				}
			}
		} else {
			try {
				res.getWriter().write("{\"ret\":-2,\"msg\":\"余额不足\"}");
				LOG.info("YXCQTengxunController mPayCallback 余额不足");
			} catch (IOException e) {
				LOG.info(e.toString());
			}
		}
		return null;
	}
	
	/**
	 * 此接口屏蔽，改用其他接口
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/webApi/yxcqTengxunPayment.do", method = RequestMethod.GET)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		// 屏蔽了该接口，避免出问题
		
//		PartnerService ps = serviceFactory.getBean(PartenerEnum.YXCQ_Tengxun.getPartenerId());
//		TengxunPayObj obj = new TengxunPayObj();
//		
//		// 参数写死例子
//		String appid = req.getParameter("appid");
//		String appmeta = req.getParameter("appmeta");
//		String billno = req.getParameter("billno");
//		String channel_id = req.getParameter("channel_id");
//		String clientver = req.getParameter("clientver");
//		String discountid = req.getParameter("discountid");
//		String giftcoins = req.getParameter("giftcoins");
//		String openid = req.getParameter("openid");
//		String paychannel = req.getParameter("paychannel");
//		String paychannelsubid = req.getParameter("paychannelsubid");
//		String payitem = req.getParameter("payitem");
//		String providetype = req.getParameter("providetype");
//		String token = req.getParameter("token");
//		String ts = req.getParameter("ts");
//		String version = req.getParameter("version");
//		String zoneid = req.getParameter("zoneid");
//		String sig = req.getParameter("sig");
//		obj.setAppid(appid);
//		obj.setAppmeta(appmeta);
//		obj.setBillno(billno);
//		obj.setChannel_id(channel_id);
//		obj.setClientver(clientver);
//		obj.setDiscountid(discountid);
//		obj.setGiftcoins(giftcoins);
//		obj.setOpenid(openid);
//		obj.setPaychannel(paychannel);
//		obj.setPaychannelsubid(paychannelsubid);
//		obj.setPayitem(payitem);
//		obj.setProvidetype(providetype);
//		obj.setToken(token);
//		obj.setTs(Long.valueOf(ts));
//		obj.setVersion(version);
//		obj.setZoneid(zoneid);
//		obj.setSig(sig);
//		
//		TengxunPayParamsObj payParamsObj = new TengxunPayParamsObj();
//		
//		Map<String, String> map = new HashMap<String, String>();
//		Enumeration paramNames = req.getParameterNames();
//		while (paramNames.hasMoreElements()) {
//			String paramName = (String) paramNames.nextElement();
//			String[] paramValues = req.getParameterValues(paramName);
//			if (paramValues.length == 1) {
//				String paramValue = paramValues[0];
//				map.put(paramName, paramValue);
//				payParamsObj.getParams().put(paramName, paramValue);
//			}
//		}
//		Set<Map.Entry<String, String>> set = map.entrySet();
//		for (Map.Entry entry : set) {
//			LOG.info("YXCQTengxunController payCallback() key=" + entry.getKey() + " value=" + entry.getValue());
//			// TODO 参数如果变化太频繁，则不能把参数写死。
//		}
////		try {
////			if (ps.doPayment(obj)) {
////				res.getWriter().write("{\"ret\":0,\"msg\":\"OK\"}");
////			} else {
////				res.getWriter().write("{\"ret\":4,\"msg\":\"请求参数错误\"}");
////				LOG.error("YXCQTengxunController doPayment error");
////			}
////		} catch (Exception e) {
////			LOG.error("YXCQTengxunController doPayment error");
////		}
////		
//		try {
//			if (ps.doPayment(payParamsObj)) {
//				res.getWriter().write("{\"ret\":0,\"msg\":\"OK\"}");
//			} else {
//				res.getWriter().write("{\"ret\":4,\"msg\":\"请求参数错误\"}");
//				LOG.error("YXCQTengxunController doPayment error");
//			}
//		} catch (Exception e) {
//			LOG.error("YXCQTengxunController doPayment error");
//		}
//		
		return null;
	}
	
	public static void main(String[] args) {
		
		// https://openapi.tencentyun.com/mpay/pay_m?
		//openkey=DAAC7705FAE263CEEA26F07BE2663044
		//&pay_token=119C2AD86415B50BCEB9E911AE6AABDD
		//&amt=50
		//&pf=desktop_m_qq-2002-android-1137-qq-1103285451-1A19B29D93B3CD6A98B38382509DE4E4
		//&ts=1417590084
		//&zoneid=2
		//&appid=1103285451
		//&openid=1A19B29D93B3CD6A98B38382509DE4E4
		//&pfkey=8fc392cbe5834460599d230b48cecd7a
		//&sig=XVvlmWUEcY5VgCGaCci6cUwoBMc%3D
		String sessionId = "openid";
		String sessionType = "kp_actoken";
		String orgLoc = "/mpay/pay_m";
		
		String openid = "1A19B29D93B3CD6A98B38382509DE4E4";
		String openkey = "DAAC7705FAE263CEEA26F07BE2663044";
		String pay_token = "119C2AD86415B50BCEB9E911AE6AABDD";
		String appid = "1103285451";
		String ts = "1417590084";
		String pf = "desktop_m_qq-2002-android-1137-qq-1103285451-1A19B29D93B3CD6A98B38382509DE4E4";
		String pfkey = "8fc392cbe5834460599d230b48cecd7a";
		String zoneid = "2";
		String amt = "50";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("openid", openid);
		params.put("openkey", openkey);
		params.put("pay_token", pay_token);
		params.put("appid", appid);
		params.put("ts", ts);
		params.put("pf", pf);
		params.put("pfkey", pfkey);
		params.put("zoneid", zoneid);
		params.put("amt", amt);
		
		// 签名
		String sig = SnsSigCheck.makeSig("GET", "/mpay/pay_m", params, "XTGcDu8adveK9QS5&");
		params.put("sig", URLEncoder.encode(sig));
				
		LOG.info("YXCQTengxunController mPayCallback : " + params);
		
		String jsonStr = UrlRequestUtils.executeHttpsTengxun("https://openapi.tencentyun.com//mpay/pay_m", params, sessionId, sessionType, orgLoc, "GET");
				
		LOG.info("YXCQTengxunController mPayCallback jsonStr : " + jsonStr);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ts", -1);
		int t = (Integer) map.get("ts");
		System.out.println(t);
				
	}
}
