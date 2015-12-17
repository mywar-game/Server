package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.MonsterGeneConstant;
import com.dataconfig.service.MonsterGeneConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class MonsterGeneConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -1333524879608663809L;

	private List<MonsterGeneConstant> monsterGeneConstantList;
	
	public String execute() {
		MonsterGeneConstantService monsterGeneConstantService = ServiceCacheFactory.getServiceCache().getService(MonsterGeneConstantService.class);
		IPage<MonsterGeneConstant> list = monsterGeneConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			monsterGeneConstantList = (List<MonsterGeneConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setMonsterGeneConstantList(List<MonsterGeneConstant> monsterGeneConstantList) {
		this.monsterGeneConstantList = monsterGeneConstantList;
	}

	public List<MonsterGeneConstant> getMonsterGeneConstantList() {
		return monsterGeneConstantList;
	}
}
