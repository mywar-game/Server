package com.dataconfig.action;

import java.util.List;
import com.dataconfig.bo.TMallTreasureConstant;
import com.dataconfig.service.TMallTreasureConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * @author hzy
 * 2012-7-17
 */
public class TMallTreasureConstantList  extends ALDAdminPageActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4127012019338609700L;
	
	private List<TMallTreasureConstant> tmallTreasureConstantList;
	
	public String execute() {
		TMallTreasureConstantService tMallTreasureConstantService = ServiceCacheFactory.getServiceCache().getService(TMallTreasureConstantService.class);
		IPage<TMallTreasureConstant> list = tMallTreasureConstantService.findAllTMallTreasureConstant(super.getToPage(), 2000);
		IPage<TMallTreasureConstant> list1 = tMallTreasureConstantService.findAllTMallTreasureConstantForEquipment(super.getToPage(), 2000);
		if (list != null) {
			tmallTreasureConstantList = (List<TMallTreasureConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		if (list1 != null) {
			List<TMallTreasureConstant> tmallTreasureConstantList1 = (List<TMallTreasureConstant>) list1.getData();
			for (TMallTreasureConstant tmallTreasureConstant : tmallTreasureConstantList1) {
				tmallTreasureConstantList.add(tmallTreasureConstant);
			}
		}
		return SUCCESS;
	}
	
	public List<TMallTreasureConstant> getTmallTreasureConstantList() {
		return tmallTreasureConstantList;
	}
	
	public void setTmallTreasureConstantList(
			List<TMallTreasureConstant> tmallTreasureConstantList) {
		this.tmallTreasureConstantList = tmallTreasureConstantList;
	}

}
