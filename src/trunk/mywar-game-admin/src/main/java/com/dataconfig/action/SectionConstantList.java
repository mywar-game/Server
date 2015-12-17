package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.SectionConstant;
import com.dataconfig.service.SectionConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class SectionConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 1610468325201563390L;

	private List<SectionConstant> sectionConstantList;
	
	public String execute() {
		SectionConstantService sectionConstantService = ServiceCacheFactory.getServiceCache().getService(SectionConstantService.class);
		IPage<SectionConstant> list = sectionConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			sectionConstantList = (List<SectionConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	/**
	 * 获取 sectionConstantList 
	 */
	public List<SectionConstant> getSectionConstantList() {
		return sectionConstantList;
	}

	/**
	 * 设置 sectionConstantList 
	 */
	public void setSectionConstantList(List<SectionConstant> sectionConstantList) {
		this.sectionConstantList = sectionConstantList;
	}

}
