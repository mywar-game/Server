package com.dataconfig.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.BNpcConstant;
import com.dataconfig.service.BBuildingConstantService;
import com.dataconfig.service.BNpcConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class BNpcConstantList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 7846157689955662057L;
	
	private List<BNpcConstant> npcConstantList;
	
	private Map<Integer, String> buildingIDNameMap = new LinkedHashMap<Integer, String>();
	
	public String execute() {
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);
		buildingIDNameMap = buildingConstantService.findBuildingIDNameMap();
		
		BNpcConstantService npcConstantService = ServiceCacheFactory.getServiceCache().getService(BNpcConstantService.class);
		IPage<BNpcConstant> list = npcConstantService.findBNpcConstantList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			npcConstantList = (List<BNpcConstant>) list.getData();
			super.setTotalSize(list.getTotalSize());
			super.setTotalPage(list.getTotalPage());
		}
		return SUCCESS;
	}

	public List<BNpcConstant> getNpcConstantList() {
		return npcConstantList;
	}

	public void setNpcConstantList(List<BNpcConstant> npcConstantList) {
		this.npcConstantList = npcConstantList;
	}

	public Map<Integer, String> getBuildingIDNameMap() {
		return buildingIDNameMap;
	}

	public void setBuildingIDNameMap(Map<Integer, String> buildingIDNameMap) {
		this.buildingIDNameMap = buildingIDNameMap;
	}
}
