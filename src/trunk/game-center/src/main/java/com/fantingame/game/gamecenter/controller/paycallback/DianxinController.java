package com.fantingame.game.gamecenter.controller.paycallback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.dianxin.DianxinPayMsgObj;
import com.fantingame.game.gamecenter.partener.dianxin.DianxinSdk;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class DianxinController {
private static Logger LOG = Logger.getLogger(DianxinController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/dianxinPayment.do")
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.Dianxin.getPartenerId());
		try {
			String method = req.getParameter("method");
			LOG.info("DianxinController payCallback method = " + method);
			if (method != null && method.equals("check")) {
				// 短信支付回调，不进行处理
			} else if (method != null && method.equals("callback")) {
				String cp_order_id = req.getParameter("cp_order_id"); // CP业务流水号
				String correlator = req.getParameter("correlator");
				String result_code = req.getParameter("result_code");
				String result_desc = req.getParameter("result_desc");
				int fee = Integer.valueOf(req.getParameter("fee"));
				String pay_type = req.getParameter("pay_type");
				String sign = req.getParameter("sign");
				
				LOG.info("DianxinController payCallback cp_order_id = " + cp_order_id + " correlator = " +
				correlator + " result_code = " + result_code + " sign = " + sign +
				" result_desc = " + result_desc + " fee = " + fee + " pay_type = " + pay_type + " sign = " + sign);
				DianxinPayMsgObj obj = new DianxinPayMsgObj();
				obj.setCp_order_id(cp_order_id);
				obj.setCorrelator(correlator);
				obj.setResult_code(result_code);
				obj.setResult_desc(result_desc);
				obj.setFee(fee);
				obj.setPay_type(pay_type);
				obj.setSign(sign);
				if (!DianxinSdk.instance().validateSign(cp_order_id, correlator, result_code, fee, pay_type, method, sign)) {
					LOG.error("DianxinController payCallback validateSign fail 签名不正确");
					return null;
				}
				if (ps.doPayment(obj)) {
					LOG.info("DianxinController payCallback success");
				} else {
					LOG.error("DianxinController payCallback fail");
				}
				
			}
		} catch (Exception e) {
			LOG.error("Dianxin payment error!",e);
		}
		// 返回支付信息
		return null;
	}
	
	/**
	 * 短信计费回调
	 * @param req
	 * @param res
	 * @return
	 */
//	@RequestMapping(value = "/webApi/dianxinMsgPayment.do")
//	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
//		PartnerService ps = serviceFactory.getBean(PartenerEnum.Dianxin.getPartenerId());
//		try {
//			String resultCode = req.getParameter("resultCode");
//			String cpparam = req.getParameter("cpparam");
//			String resultMsg = req.getParameter("resultMsg");
//			int cost = Integer.valueOf(req.getParameter("cost"));
//			String payType = req.getParameter("payType");
//			String validatecode = req.getParameter("validatecode");
//			String requestTime = req.getParameter("requestTime");
//
//			LOG.info("DianxinController payCallback : resultCode = " + resultCode + " cpparam = " + cpparam + " resultMsg = " + resultMsg + " cost = " + cost + " payType = " + payType + " validatecode = " + validatecode + " requestTime = " + requestTime);
//			if (resultCode == null || !resultCode.equalsIgnoreCase("00")) {
//				res.getWriter().print("ResultCode:" + resultCode + ",CpParam:" + cpparam);
//			} else {
//				if (StringUtils.isNotBlank(resultCode)
//						&& StringUtils.isNotBlank(cpparam)
//						&& StringUtils.isNotBlank(resultMsg)
//						&& StringUtils.isNotBlank(Integer.toString(cost))
//						&& StringUtils.isNotBlank(payType)
//						&& StringUtils.isNotBlank(validatecode)
//						&& StringUtils.isNotBlank(requestTime)) {
//					DianxinPayMsgObj obj = new DianxinPayMsgObj();
//					obj.setResultCode(resultCode);
//					obj.setCpparam(cpparam);
//					obj.setResultMsg(resultMsg);
//					obj.setCost(cost);
//					obj.setPayType(payType);
//					obj.setValidatecode(validatecode);
//					obj.setRequestTime(requestTime);
//					if (ps.doPayment(obj)) {
//						// 成功
//						res.getWriter().print("ResultCode:" + resultCode + ",CpParam:" + cpparam);
//					} else {
//						// 失败
//						res.getWriter().print("ResultCode:" + resultCode + ",CpParam:" + cpparam);
//					}
//				}
//			}
//		} catch (Exception e) {
//			LOG.error("Dianxin payment error!",e);
//		}
//		return null;
//	}
	
	/**
	 * 第三方计费回调
	 * @param req
	 * @param res
	 * @return
	 */
//	@RequestMapping(value = "/webApi/dianxinOthersPayment.do")
//	public ModelAndView payCallback2(HttpServletRequest req, HttpServletResponse res) {
//		PartnerService ps = serviceFactory.getBean(PartenerEnum.Dianxin.getPartenerId());
//		try {
//			
//			String resultCode = req.getParameter("resultCode");
//			String cpparam = req.getParameter("cpparam");
//			String payType = req.getParameter("payType");
//			String validatecode = req.getParameter("validatecode");
//			int gameGold = Integer.valueOf(req.getParameter("gameGold"));
//			
//			LOG.info("DianxinController payCallback2 : cpparam = " + cpparam + " gameGold = " + gameGold + " payType = " + payType + " validatecode = " + validatecode);
//			if (resultCode == null || !resultCode.equalsIgnoreCase("120")) {
//				res.getWriter().print("ResultCode:" + resultCode + ",CpParam:" + cpparam);
//			} else {
//				if (StringUtils.isNotBlank(resultCode)
//						&& StringUtils.isNotBlank(cpparam)
//
//						&& StringUtils.isNotBlank(Integer.toString(gameGold))
//						&& StringUtils.isNotBlank(payType)
//						&& StringUtils.isNotBlank(validatecode)) {
//					DianxinPayMsgObj obj = new DianxinPayMsgObj();
//					obj.setResultCode(resultCode);
//					obj.setCpparam(cpparam);
//					obj.setGameGold(gameGold);
//					obj.setPayType(payType);
//					obj.setValidatecode(validatecode);
//					
//					if (ps.doPayment(obj)) {
//						// 成功
//						res.getWriter().print("ResultCode:" + resultCode + ",CpParam:" + cpparam);
//					} else {
//						// 失败
//						res.getWriter().print("ResultCode:" + resultCode + ",CpParam:" + cpparam);
//					}
//				}
//			}
//		} catch (Exception e) {
//			LOG.error("Dianxin payment error!",e);
//		}
//		return null;
//	}
}
