package com.fantingame.game.gamecenter.controller.paycallback;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.huawei.HuaWeiPaymentObj;
import com.fantingame.game.gamecenter.partener.huawei.HuaWeiSdk;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 华为
 * 
 * @author yezp
 */
@Controller
public class HuaWeiController {
	private static Logger LOG = Logger.getLogger(HuaWeiController.class);

	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/huaWeiPayment.do")
	public ModelAndView payCallback(HttpServletRequest req,
			HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.huaWei
				.getPartenerId());
		try {
			String result = req.getParameter("result");
			String userName = req.getParameter("userName");
			String productName = req.getParameter("productName");
			String payType = req.getParameter("payType");
			String amount = req.getParameter("amount");
			String orderId = req.getParameter("orderId");
			String notifyTime = req.getParameter("notifyTime");
			String requestId = req.getParameter("requestId");
			String bankId = req.getParameter("bankId");
			String orderTime = req.getParameter("orderTime");
			String tradeTime = req.getParameter("tradeTime");
			String accessMode = req.getParameter("accessMode");
			String spending = req.getParameter("spending");
			String extReserved = req.getParameter("extReserved");
			String sign = req.getParameter("sign");
			if (StringUtils.isNotBlank(orderId)
					&& StringUtils.isNotBlank(amount)
					&& StringUtils.isNotBlank(sign)) {
				HuaWeiPaymentObj data = new HuaWeiPaymentObj();
				data.setResult(result);
				data.setUserName(userName);
				data.setProductName(productName);
				data.setPayType(payType);
				data.setAmount(amount);
				data.setOrderId(orderId);
				data.setNotifyTime(notifyTime);
				data.setRequestId(requestId);
				data.setBankId(bankId);
				data.setOrderTime(orderTime);
				data.setTradeTime(tradeTime);
				data.setAccessMode(accessMode);
				data.setSpending(spending);
				data.setExtReserved(extReserved);
				data.setSign(sign);
				Map<String, Object> map = getValue(req);
				data.setMap(map);
				
				if (!HuaWeiSdk.instance().checkPayCallbackSign(data)) {
					LOG.error("签名不正确" + Json.toJson(data));
					res.getWriter().write("{\"result\":\"1\"}");
					return null;
				}
				
				if (ps.doPayment(data)) {
					res.getWriter().write("{\"result\":\"0\"}");
				} else {
					res.getWriter().write("{\"result\":\"99\"}");
				}
			} else {
				res.getWriter().write("{\"result\":\"99\"}");
			}

		} catch (Exception e) {
			LOG.error("huawei payment error!", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getValue(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String key = "";
			String value = "";
			Iterator<String> it = request.getParameterMap().keySet().iterator();
			while (it.hasNext()) {
				key = it.next();
				value = ((Object[]) (request.getParameterMap().get(key)))[0]
						.toString();

				map.put(key, value);
			}
		} catch (Exception e) {
			return null;
		}

		return map;
	}

}
