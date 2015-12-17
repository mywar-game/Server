package com.fantingame.game.gamecenter.controller.paycallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.kuaiyong.Base64;
import com.fantingame.game.gamecenter.partener.kuaiyong.KuaiYongPaymentObj;
import com.fantingame.game.gamecenter.partener.kuaiyong.KuaiYongSdk;
import com.fantingame.game.gamecenter.partener.kuaiyong.RSAEncrypt;
import com.fantingame.game.gamecenter.partener.kuaiyong.RSASignature;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class KuaiYongController {
	private static Logger logger = Logger.getLogger(KuaiYongController.class);

	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/kuaiYongPayment.do", method = RequestMethod.POST)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.KuaiYong.getPartenerId());
		String notify_data = "";
		String orderid = "";
		String dealseq = "";
		String sign = "";
		String uid;
		String subject;
		String v;
		try {
			req.setCharacterEncoding("UTF-8");
			Map<String, String> transformedMap = new HashMap<String, String>();
			// 获得通知签名
			notify_data = req.getParameter("notify_data");
			transformedMap.put("notify_data", notify_data);
			orderid = req.getParameter("orderid");
			transformedMap.put("orderid", orderid);
			sign = req.getParameter("sign");
			transformedMap.put("sign", sign);
			dealseq = req.getParameter("dealseq");
			transformedMap.put("dealseq", dealseq);
			uid = req.getParameter("uid");
			transformedMap.put("uid", uid);
			subject = req.getParameter("subject");
			transformedMap.put("subject", subject);
			v = req.getParameter("v");
			transformedMap.put("v", v);
			// rsa签名验签
			String verify = getVerifyData(transformedMap);
			logger.info("verfiy data:" + verify);
			logger.info("sign is:" + sign);
			if (!RSASignature.doCheck(verify, sign, KuaiYongSdk.instance().getPaySecret(), "utf-8")) {
				logger.info("RSA验签失败，数据不可信" + verify);
			} else {
				logger.info("RSA验签成功，数据可信:" + verify);
				RSAEncrypt rsaEncrypt = new RSAEncrypt();
				// BASE64Decoder base64Decoder = new BASE64Decoder();
				// 加载公钥
				try {
					rsaEncrypt.loadPublicKey(KuaiYongSdk.instance().getPaySecret());
					logger.info("加载公钥成功");
				} catch (Exception e) {
					logger.error("load rsa public key failed, 加载公钥失败");
					logger.error(e, e);

				}
				// 公钥解密通告加密数据
				// byte[] dcDataStr = base64Decoder.decodeBuffer(notify_data);
				byte[] dcDataStr = Base64.decode(notify_data);
				byte[] plainData = rsaEncrypt.decrypt(rsaEncrypt.getPublicKey(), dcDataStr);
				String encodeDate = new String(plainData, "UTF-8");
				logger.info("KuaiYong Notify Data:" + encodeDate);
				String[] orders = StringUtils.split(encodeDate, "&");
				if (orders != null && orders.length == 3) {
					KuaiYongPaymentObj cp = new KuaiYongPaymentObj();
					logger.info("KuaiYong order id=:" + orders[0]);
					String[] mysorders = StringUtils.split(orders[0], "=");
					logger.info("KuaiYong my order id=:" + mysorders[1]);
					cp.setDealseq(mysorders[1]);
					String[] fees = StringUtils.split(orders[1], "=");
					logger.info("KuaiYong my fee=:" + fees[1]);
					cp.setFee(fees[1]);
					cp.setNotifyData(notify_data);
					cp.setOrderid(orderid);
					String[] result = StringUtils.split(orders[2], "=");
					logger.info("KuaiYong my result=:" + result[1]);
					cp.setPayresult(result[1]);
					cp.setSign(sign);
					cp.setSubject(subject);
					cp.setUid(uid);
					cp.setV(v);
					if(ps.doPayment(cp)){
						// 返回成功信息
						res.getWriter().write("success");
					}else{
						res.getWriter().write("failed");
					}
				} else {
					res.getWriter().write("failed");
				}
			}

		} catch (Exception e) {
			logger.info("KuaiYong notify Exception, url - " + req.getRequestURI() + "?" + notify_data);
			logger.error(e, e);
			try {
				res.getWriter().write("failed");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 设置关闭参数
	 * 
	 * @param request
	 * @param response
	 */
	private void closeSession(HttpServletRequest request, HttpServletResponse response) {
		try {

			response.setHeader("Connection", "close");
			// 不要让浏览器开辟缓存
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			// 程序立即过期
			response.setDateHeader("Expires", 0);

			// 不要让浏览其缓存程序
			response.setHeader("Pragma", "no-cache");

			try {
				HttpSession session = request.getSession();
				if (session != null) {
					session.invalidate();
				}
			} catch (Exception ex) {
				// logger.error(ex, ex);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	/**
	 * 获得验签名的数据
	 * 
	 * @param map
	 * @return
	 */
	private String getVerifyData(Map<String, String> map) {
		String signData = getSignData(map);
		return signData;
	}

	/**
	 * 获得MAP中的参数串；
	 * 
	 * @param params
	 * @return
	 */
	public static String getSignData(Map<String, String> params) {
		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);

			if ("sign".equals(key)) {
				continue;
			}
			String value = (String) params.get(key);
			if (value != null) {
				content.append((i == 0 ? "" : "&") + key + "=" + value);
			} else {
				content.append((i == 0 ? "" : "&") + key + "=");
			}
		}
		return content.toString();
	}

	// public static String getElementText(Element rootElement, String
	// elementName) {
	// if (rootElement != null) {
	// Element em = rootElement.element(elementName);
	// if (em != null)
	// return em.getText();
	// else
	// return null;
	// } else {
	// return null;
	// }
	// }
}
