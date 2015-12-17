package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.MallDiscount;
import com.adminTool.service.MallDiscountService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class MallAuditList extends ALDAdminPageActionSupport{

	private static final long serialVersionUID = -4178142158904715125L;
	
	private List<MallDiscount> mallDiscountList;
	
	
	public String execute() {
		MallDiscountService mds = ServiceCacheFactory.getServiceCache().getService(MallDiscountService.class);
		IPage<MallDiscount> mallDiscountPage = mds.findMallAuditList(super.getToPage(), 10);
		
		if (mallDiscountPage != null) {
			mallDiscountList = (List<MallDiscount>) mallDiscountPage.getData();

			super.setTotalPage(mallDiscountPage.getTotalPage());
			super.setTotalSize(mallDiscountPage.getTotalSize());
		}
		return SUCCESS;
	}


	public List<MallDiscount> getMallDiscountList() {
		return mallDiscountList;
	}


	public void setMallDiscountList(List<MallDiscount> mallDiscountList) {
		this.mallDiscountList = mallDiscountList;
	}



	
	
}
