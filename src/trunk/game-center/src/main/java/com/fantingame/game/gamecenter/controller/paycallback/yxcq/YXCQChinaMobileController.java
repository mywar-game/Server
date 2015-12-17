package com.fantingame.game.gamecenter.controller.paycallback.yxcq;

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
import com.fantingame.game.gamecenter.partener.yxcq.chinamobile.ChinaMobilePaymentObj;
import com.fantingame.game.gamecenter.partener.yxcq.chinamobile.YXCQChinaMobileSdk;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class YXCQChinaMobileController {
	private static Logger LOG = Logger.getLogger(YXCQChinaMobileController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/yxcqchinaMobilePayment.do")
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.ChinaMobile.getPartenerId());
		String feeType = req.getParameter("feeType");
		String versionId = req.getParameter("versionId");
		String cpid = req.getParameter("cpid");
		String contentId = req.getParameter("contentId");
		String channelId = req.getParameter("channelId");
		String consumerCode = req.getParameter("consumerCode");
		String password = req.getParameter("password");
		String cpparam = req.getParameter("cpparam");
		String packageID = req.getParameter("packageID");
		String hRet = req.getParameter("hRet");
		LOG.info("feeType:"+feeType+",versionId:"+versionId+",cpid:"+cpid+",contentId:"+contentId
				+",channelId:"+channelId+",consumerCode:"+consumerCode+",password:"+password
				+",cpparam:"+cpparam+",packageID:"+packageID+",hRet:"+hRet);
		try {
			if(StringUtils.isNotBlank(cpid)
					&& StringUtils.isNotBlank(consumerCode)
					&& StringUtils.isNotBlank(cpparam)){
				ChinaMobilePaymentObj data = new ChinaMobilePaymentObj();
				data.setFeeType(feeType);
				data.setChannelId(channelId);
				data.setConsumerCode(consumerCode);
				data.setContentId(contentId);
				data.setCpid(cpid);
				data.setCpparam(cpparam);
				data.setPackageID(packageID);
				data.setPassword(password);
				data.setVersionId(versionId);
				data.sethRet(hRet);
				if (ps.doPayment(data)) {
					res.getWriter().print("Successful");
				}else{
					res.getWriter().print("fail");
				}
			}else{
				res.getWriter().print("fail");
			}
		} catch (Exception e) {
			LOG.error("anzhi payment error!",e);
		}
		return null;
	}
}
