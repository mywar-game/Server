package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.AlchemyConstant;
import com.dataconfig.service.AlchemyConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class AlchemyConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -6314602634576677096L;
	
	private List<AlchemyConstant> alchemyConstantList;
	
	public String execute() {
		AlchemyConstantService alchemyConstantService = ServiceCacheFactory.getServiceCache().getService(AlchemyConstantService.class);
		IPage<AlchemyConstant> list = alchemyConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			alchemyConstantList = (List<AlchemyConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setAlchemyConstantList(List<AlchemyConstant> alchemyConstantList) {
		this.alchemyConstantList = alchemyConstantList;
	}

	public List<AlchemyConstant> getAlchemyConstantList() {
		return alchemyConstantList;
	}

}
