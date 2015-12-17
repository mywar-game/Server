package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.PayFeedBack;
import com.dataconfig.service.PayFeedBackService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class PayFeedBackList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 4450731190386386836L;
	
	private List<PayFeedBack> payFeedBackList;
	
	public String execute() {
		PayFeedBackService payFeedBackService = ServiceCacheFactory.getServiceCache().getService(PayFeedBackService.class);
		IPage<PayFeedBack> list = payFeedBackService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			payFeedBackList = (List<PayFeedBack>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setPayFeedBackList(List<PayFeedBack> payFeedBackList) {
		this.payFeedBackList = payFeedBackList;
	}

	public List<PayFeedBack> getPayFeedBackList() {
		return payFeedBackList;
	}

}
