package com.dataconfig.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.BBuildingconstraintConstant;
import com.dataconfig.service.BBuildingConstantService;
import com.dataconfig.service.BBuildingconstraintConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class BBuildingconstraintConstantList extends ALDAdminPageActionSupport {

	/** **/
	private static final long serialVersionUID = -2893689304009458668L;

	/** **/
	private List<BBuildingconstraintConstant> buildingconstraintConstantList;

	/** **/
	private Map<Integer, String> buildingIDNameMap = new LinkedHashMap<Integer, String>();
	
	public String execute() {
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);
		buildingIDNameMap = buildingConstantService.findBuildingIDNameMap();
		
		BBuildingconstraintConstantService buildingconstraintConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingconstraintConstantService.class);
		IPage<BBuildingconstraintConstant> list = buildingconstraintConstantService.findBBuildingconstraintConstantList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			buildingconstraintConstantList = (List<BBuildingconstraintConstant>) list.getData();
			super.setTotalSize(list.getTotalSize());
			super.setTotalPage(list.getTotalPage());
		}
		return SUCCESS;
	}
	
	public List<BBuildingconstraintConstant> getBuildingconstraintConstantList() {
		return buildingconstraintConstantList;
	}

	public void setBuildingconstraintConstantList(
			List<BBuildingconstraintConstant> buildingconstraintConstantList) {
		this.buildingconstraintConstantList = buildingconstraintConstantList;
	}

	public Map<Integer, String> getBuildingIDNameMap() {
		return buildingIDNameMap;
	}

	public void setBuildingIDNameMap(Map<Integer, String> buildingIDNameMap) {
		this.buildingIDNameMap = buildingIDNameMap;
	}
}
