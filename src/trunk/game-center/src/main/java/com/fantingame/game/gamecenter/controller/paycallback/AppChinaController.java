package com.fantingame.game.gamecenter.controller.paycallback;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.appchina.AppChinaPaymentObj;
import com.fantingame.game.gamecenter.partener.appchina.AppChinaSdk;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class AppChinaController {
	private static Logger LOG = Logger.getLogger(AppChinaController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/appChinaPayment.do")
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.AppChina.getPartenerId());
		try {
			/* 2、提取支付结果通知数据 */
			InputStream in = req.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));//请注意是UTF-8编码			
			String line = null;
			StringBuilder tranData = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				tranData.append(line);
			}
			line = tranData.toString();
			LOG.info("info:支付结果通知内容["+line+"]");//记录收到数据
			
			String result = "FAILURE";
			/* 3、解析支付结果通知数据成业务数据  */
			if (null == line || "".equals(line.trim())) {			
				LOG.info("error:支付结果通知内容为空");
			} else {
				int index = line.indexOf('&');
				if (0 > index) {				
					LOG.info("error:支付结果通知内容格式不对，请确认格式为tranddate={}&sing=。");
				} else {
					String transdata = line.substring(10,index);//transdata=
					String sign = line.substring(index+6);//sign=
					LOG.info("info:支付结果通知内容transdata["+transdata+"]");
					LOG.info("info:支付结果通知签名sign["+sign+"]");
					if (transdata == null || sign == null
							|| "".equalsIgnoreCase(transdata)
							|| "".equalsIgnoreCase(sign)) {					
						LOG.info("error:支付结果通知内容格式不对，请确认格式为tranddate={}&sing=。");
					} else {
						AppChinaPaymentObj data = new AppChinaPaymentObj();
						data.setTransdata(transdata);
						data.setSign(sign);
						if (ps.doPayment(data)) {
							result = "SUCCESS";
						}
					}
				}
			}
			/* 5、返回处理结果  */
			res.getWriter().write(result);
		} catch (Exception e) {
			LOG.error("anzhi payment error!",e);
		}
		return null;
	}
}
