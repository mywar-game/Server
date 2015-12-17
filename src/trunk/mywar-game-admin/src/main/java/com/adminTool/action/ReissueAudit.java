package com.adminTool.action;

import java.util.Map;

import com.adminTool.bo.PaymentOrder;
import com.adminTool.service.PaymentOrderService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.tool.ParseJason;

public class ReissueAudit extends ALDAdminActionSupport implements ModelDriven<PaymentOrder>{

	private static final long serialVersionUID = 2046707227897608460L;
	
	private PaymentOrder paymentOrder = new PaymentOrder();

	public String execute() {
		PaymentOrderService pos = ServiceCacheFactory.getServiceCache().getService(PaymentOrderService.class);
		String partnerOrderId = paymentOrder.getPartnerOrderId();
		paymentOrder = pos.findByOrderId(paymentOrder.getOrderId());
		// 发送补发信息给 game-web
		String response = pos.fillOrder(paymentOrder.getOrderId(), partnerOrderId, paymentOrder.getGameServerId());
		// 如果返回的 rc 不等于1000, 说明补发失败
		Map<String, String> map = ParseJason.jason2Map(response);
		if (!map.get("rc").equals("1000")) {
			return ERROR;
		}
		// 补发成功，将 status 修改1
		paymentOrder.setStatus(1);
		paymentOrder.setPartnerOrderId(partnerOrderId);
		pos.updatePaymentOrder(paymentOrder);
		
		return SUCCESS;
	}

	public PaymentOrder getPaymentOrder() {
		return paymentOrder;
	}

	public void setPaymentOrder(PaymentOrder paymentOrder) {
		this.paymentOrder = paymentOrder;
	}

	@Override
	public PaymentOrder getModel() {
		return paymentOrder;
	}
	
}
