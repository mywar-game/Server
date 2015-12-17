package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.PaymentOrder;
import com.adminTool.service.PaymentOrderService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class ReissueList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -5740183218256965903L;

	private List<PaymentOrder> paymentOrderList;
	
	public String execute() {
		
		PaymentOrderService pos = ServiceCacheFactory.getServiceCache().getService(PaymentOrderService.class);
		IPage<PaymentOrder> page = pos.findAllPaymentOrder(super.getToPage(), 2000);
		
		if (page != null) {
			paymentOrderList = (List<PaymentOrder>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		
		return SUCCESS;
	}

	public List<PaymentOrder> getPaymentOrderList() {
		return paymentOrderList;
	}

	public void setPaymentOrderList(List<PaymentOrder> paymentOrderList) {
		this.paymentOrderList = paymentOrderList;
	}
	
	
}
