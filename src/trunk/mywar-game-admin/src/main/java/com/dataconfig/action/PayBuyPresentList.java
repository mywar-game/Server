package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.PayBuyPresent;
import com.dataconfig.service.PayBuyPresentService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class PayBuyPresentList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -2214831572374533884L;

	private List<PayBuyPresent> payBuyPresentList;
	
	public String execute() {
		PayBuyPresentService payBuyPresentService = ServiceCacheFactory.getServiceCache().getService(PayBuyPresentService.class);
		IPage<PayBuyPresent> list = payBuyPresentService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			payBuyPresentList = (List<PayBuyPresent>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setPayBuyPresentList(List<PayBuyPresent> payBuyPresentList) {
		this.payBuyPresentList = payBuyPresentList;
	}

	public List<PayBuyPresent> getPayBuyPresentList() {
		return payBuyPresentList;
	}
	
}
