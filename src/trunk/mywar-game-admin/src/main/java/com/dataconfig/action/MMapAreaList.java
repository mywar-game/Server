package com.dataconfig.action;

import java.util.List;
import java.util.Map;

import com.dataconfig.bo.MMapArea;
import com.dataconfig.service.BaPveConstantService;
import com.dataconfig.service.MMapAreaService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class MMapAreaList extends ALDAdminPageActionSupport {

	/** **/
	private static final long serialVersionUID = -3891146514538643897L;
	
	/** **/
	private List<MMapArea> mapAreaList;
	
	/** **/
	private Map<String, String> baPveIdNamesMap;
	
	public String execute() {
		MMapAreaService mapAreaService = ServiceCacheFactory.getServiceCache().getService(MMapAreaService.class);
		IPage<MMapArea> list = mapAreaService.findMMapAreaList(super.getToPage(), DEFAULT_PAGESIZE);
		if (list != null) {
			mapAreaList = (List<MMapArea>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		BaPveConstantService baPveConstantService = ServiceCacheFactory.getServiceCache().getService(BaPveConstantService.class);
		baPveIdNamesMap = baPveConstantService.findBaPveIdNamesMap();
		return SUCCESS;
	}

	public List<MMapArea> getMapAreaList() {
		return mapAreaList;
	}

	public void setMapAreaList(List<MMapArea> mapAreaList) {
		this.mapAreaList = mapAreaList;
	}

	public void setBaPveIdNamesMap(Map<String, String> baPveIdNamesMap) {
		this.baPveIdNamesMap = baPveIdNamesMap;
	}

	public Map<String, String> getBaPveIdNamesMap() {
		return baPveIdNamesMap;
	}
}
