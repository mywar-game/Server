package com.fantingame.game.gamecenter.controller.paycallback;

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
import com.fantingame.game.gamecenter.partener.dianjin.DianJinPaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

@Controller
public class DianJinController {
	private static Logger LOG = Logger.getLogger(DianJinController.class);

	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/djPayment.do", method = RequestMethod.GET)
	public ModelAndView payCallback(HttpServletRequest request,
			HttpServletResponse res) {
		LOG.info("91 djPayment.do");
		try {
			PartnerService ps = serviceFactory
					.getBean(PartenerEnum.DianJinAdroid.getPartenerId());
			DianJinPaymentObj data = new DianJinPaymentObj();
			String appid = request.getParameter("AppId");
			data.setAppId(appid);
			String act = request.getParameter("Act");
			data.setAct(act);
			String productName = request.getParameter("ProductName");
			LOG.info("91 ProductName:" + productName);
			// if(productName!=null && productName.length()>0 ){
			// try {
			// productName = new
			// String(productName.getBytes("ISO8859_1"),"UTF-8");
			// } catch (UnsupportedEncodingException e) {
			// LOG.error("",e);
			// }
			// }
			data.setProductName(productName);
			String consumeStreamId = request.getParameter("ConsumeStreamId");
			data.setConsumeStreamId(consumeStreamId);
			String cooOrderSerial = request.getParameter("CooOrderSerial");
			data.setCooOrderSerial(cooOrderSerial);
			String uin = request.getParameter("Uin");
			data.setUin(uin);
			String goodsId = request.getParameter("GoodsId");
			data.setGoodsId(goodsId);
			String goodsInfo = request.getParameter("GoodsInfo");
			LOG.info("91 GoodsInfo:" + goodsInfo);
			// if(goodsInfo!=null && goodsInfo.length()>0 ){
			// try {
			// LOG.info("91 GoodsInfo:" + goodsInfo);
			// goodsInfo = new String(goodsInfo.getBytes("ISO8859_1"),"UTF-8");
			// } catch (UnsupportedEncodingException e) {
			// LOG.error("91 goodsInfo ----",e);
			// }
			// }
			data.setGoodsInfo(goodsInfo);
			String goodsCount = request.getParameter("GoodsCount");
			data.setGoodsCount(goodsCount);
			String originalMoney = request.getParameter("OriginalMoney");
			data.setOriginalMoney(originalMoney);
			String orderMoney = request.getParameter("OrderMoney");
			data.setOrderMoney(orderMoney);
			String note = request.getParameter("Note");
			LOG.info("91 Note:" + note);
			// if(note!=null && note.length()>0 ){
			// try {
			// note = new String(note.getBytes("ISO8859_1"),"UTF-8");
			// LOG.info("91 Note:" + note);
			// } catch (UnsupportedEncodingException e) {
			// LOG.error("91 note-----",e);
			// }
			// }
			data.setNote(note);
			String payStatus = request.getParameter("PayStatus");
			data.setPayStatus(payStatus);
			String createTime = request.getParameter("CreateTime");
			data.setCreateTime(createTime);
			String sign = request.getParameter("Sign");
			data.setSign(sign);
			LOG.info("DianJinPaymentObj info:" + Json.toJson(data));

			res.setCharacterEncoding("UTF-8");
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			if (ps.doPayment(data)) {
				res.getWriter().write(
						"{\"ErrorCode\":\"1\",\"ErrorDesc\":\"接收成功\"}");
			} else {
				res.getWriter().write(
						"{\"ErrorCode\":\"0\",\"ErrorDesc\":\"接收失败\"}");
			}
		} catch (Exception e) {
			LOG.error("DianJinPaymentObj payment error!", e);
		}
		return null;
	}

}
