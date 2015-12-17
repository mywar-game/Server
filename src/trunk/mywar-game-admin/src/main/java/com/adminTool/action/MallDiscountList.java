package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.MallDiscount;
import com.adminTool.service.MallDiscountService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;


public class MallDiscountList extends ALDAdminPageActionSupport{

	private static final long serialVersionUID = 5960702668152932035L;
	
	private List<MallDiscount> discountList;
	
	public List<MallDiscount> getDiscountList() {
		return discountList;
	}

	public void setDiscountList(List<MallDiscount> discountList) {
		this.discountList = discountList;
	}

	public String execute() {
		MallDiscountService ads = ServiceCacheFactory.getServiceCache().getService(MallDiscountService.class);
		IPage<MallDiscount> page = ads.findMallDiscountList(super.getToPage(), 10);

		if (page != null) {
			discountList = (List<MallDiscount>) page.getData();
			
			for (int i = 0; i < discountList.size(); i++) {
				MallDiscount discount = discountList.get(i);
				
				if (ads.isOutOfDate(discount.getEndTime())) {
					discount.setStatus(3);
					ads.updateMallDiscount(discount);
				}
				
				if (discount.getStatus() != 1) {
					discountList.remove(i);
					i -= 1;
					continue;
				}
				
				if (discountList.size() == 0) {
					return SUCCESS;
				} else {
					String countdown = ads.computCountdown(discount.getStartTime(), discount.getEndTime());
					discount.setCountdown(countdown);
					
					
				}
			}
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
	
}
