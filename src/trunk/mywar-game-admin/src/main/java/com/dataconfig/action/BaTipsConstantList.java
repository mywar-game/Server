package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.BaTipsConstant;
import com.dataconfig.service.BaTipsConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class BaTipsConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 7573968733523216589L;

	private List<BaTipsConstant> baTipsConstantList;
	
	public String execute() {
		BaTipsConstantService baTipsConstantService = ServiceCacheFactory.getServiceCache().getService(BaTipsConstantService.class);
		IPage<BaTipsConstant> list = baTipsConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			baTipsConstantList = (List<BaTipsConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	/**
	 * 获取 baTipsConstantList 
	 */
	public List<BaTipsConstant> getBaTipsConstantList() {
		return baTipsConstantList;
	}

	/**
	 * 设置 baTipsConstantList 
	 */
	public void setBaTipsConstantList(List<BaTipsConstant> baTipsConstantList) {
		this.baTipsConstantList = baTipsConstantList;
	}

}
