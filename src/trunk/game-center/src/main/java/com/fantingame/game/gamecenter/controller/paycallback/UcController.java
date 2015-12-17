package com.fantingame.game.gamecenter.controller.paycallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
import com.fantingame.game.gamecenter.partener.uc.PayCallbackResponse;
import com.fantingame.game.gamecenter.partener.uc.UcSdk;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

@Controller
public class UcController {

	private static Logger LOG = Logger.getLogger(UcController.class);

	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/ucPayment.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView ucCallBack(HttpServletRequest req,
			HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.UC
				.getPartenerId());
		String retVal = "";
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(req.getInputStream(),
					"utf-8"));
			String ln;
			StringBuffer stringBuffer = new StringBuffer();
			while ((ln = in.readLine()) != null) {
				stringBuffer.append(ln);
				stringBuffer.append("\r\n");
			}

			PayCallbackResponse rsp = Json.toObject(stringBuffer.toString(),
					PayCallbackResponse.class);
			PayCallbackResponse poj = (PayCallbackResponse) rsp;
			if (poj != null && !UcSdk.instance().checkPayCallbackSign(poj)) {
				LOG.error("签名不正确" + Json.toJson(poj));
				retVal = "FAILURE";
				res.getWriter().write(retVal);
				return null;
			}

			if (ps.doPayment(rsp)) {
				retVal = "SUCCESS";
			} else {
				retVal = "SUCCESS";
			}

			res.getWriter().write(retVal);
			return null;
		} catch (Exception e) {
			LOG.info(e.getMessage(), e);
			retVal = "SUCCESS";
		}

		try {
			res.getWriter().write(retVal);
		} catch (IOException e) {
			LOG.error(e);
		}
		// ModelAndView modelView = new ModelAndView("empty", "output", retVal);

		return null;
	}
}
