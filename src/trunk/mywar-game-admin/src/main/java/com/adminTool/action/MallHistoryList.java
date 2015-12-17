package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.MallDiscount;
import com.adminTool.service.MallDiscountService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class MallHistoryList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -5731248696819470871L;
	
	private List<MallDiscount> discountList;
	
	public List<MallDiscount> getDiscountList() {
		return discountList;
	}

	public void setDiscountList(List<MallDiscount> discountList) {
		this.discountList = discountList;
	}

	public String execute() {
		MallDiscountService ads = ServiceCacheFactory.getServiceCache().getService(MallDiscountService.class);
		IPage<MallDiscount> page = ads.findMallHistory(super.getToPage(), 10);
		
		if (page != null) {
			discountList = (List<MallDiscount>) page.getData();
			
			for (MallDiscount discount : discountList) {
				int status = discount.getStatus();
				if (status == 2) {
					discount.setCountdown("已删除");
				} else if (status == 3) {
					discount.setCountdown("已结束");
				}
					
			}
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
}
