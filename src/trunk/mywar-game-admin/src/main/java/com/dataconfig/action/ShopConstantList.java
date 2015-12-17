package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.BShopConstant;
import com.dataconfig.service.ShopConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class ShopConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -4728293151159239115L;
	
	private List<BShopConstant> shopConstantList;
	
	public String execute() {
		ShopConstantService shopConstantService = ServiceCacheFactory.getServiceCache().getService(ShopConstantService.class);
		IPage<BShopConstant> list = shopConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			shopConstantList = (List<BShopConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setShopConstantList(List<BShopConstant> shopConstantList) {
		this.shopConstantList = shopConstantList;
	}

	public List<BShopConstant> getShopConstantList() {
		return shopConstantList;
	}

}
