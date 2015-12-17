package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.BAttrConstant;
import com.dataconfig.service.AttrConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class AttrConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -7813471115591631246L;
	
	private List<BAttrConstant> attrConstantList;
	
	public String execute() {
		AttrConstantService attrConstantService = ServiceCacheFactory.getServiceCache().getService(AttrConstantService.class);
		IPage<BAttrConstant> list = attrConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			attrConstantList = (List<BAttrConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setAttrConstantList(List<BAttrConstant> attrConstantList) {
		this.attrConstantList = attrConstantList;
	}

	public List<BAttrConstant> getAttrConstantList() {
		return attrConstantList;
	}

}
