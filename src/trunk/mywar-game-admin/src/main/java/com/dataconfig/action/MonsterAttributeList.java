package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.MonsterAttribute;
import com.dataconfig.service.MonsterAttributeService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class MonsterAttributeList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = 1L;
	
	private List<MonsterAttribute> monsterAttributeList;
	
	public String execute() {
		MonsterAttributeService monsterAttributeService = ServiceCacheFactory.getServiceCache().getService(MonsterAttributeService.class);
		IPage<MonsterAttribute> list = monsterAttributeService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			monsterAttributeList = (List<MonsterAttribute>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	/**
	 * 获取  
	 */
	public List<MonsterAttribute> getMonsterAttributeList() {
		return monsterAttributeList;
	}

	/**
	 * 设置  
	 */
	public void setMonsterAttributeList(List<MonsterAttribute> monsterAttributeList) {
		this.monsterAttributeList = monsterAttributeList;
	}

}
