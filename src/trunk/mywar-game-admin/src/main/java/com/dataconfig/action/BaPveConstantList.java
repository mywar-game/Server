package com.dataconfig.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.BaPveConstant;
import com.dataconfig.service.BaPveConstantService;
import com.dataconfig.service.MMapAreaService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class BaPveConstantList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -7816722996379965427L;

	private List<BaPveConstant> baPveConstantList;
	
	private Map<Integer, String> mapAreaIdNameMap;
	
	private String bigName = "";
	
	private String smallName = "";
	
	private Integer bigId;
	
	private Integer smallId;

	public String execute() {
//		System.out.println("bigName ====================== "+ bigName);
//		System.out.println("smallName ==================== "+ smallName);
		MMapAreaService mapAreaService = ServiceCacheFactory.getServiceCache().getService(MMapAreaService.class);
		mapAreaIdNameMap = mapAreaService.findMapAreaIdNameMap();
		try {
			bigName = URLDecoder.decode(bigName, "UTF-8").trim();
			smallName = URLDecoder.decode(smallName, "UTF-8").trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		System.out.println("bigName ====================== "+ bigName);
//		System.out.println("smallName ==================== "+ smallName);
		
		BaPveConstantService baPveConstantService = ServiceCacheFactory.getServiceCache().getService(BaPveConstantService.class);
		IPage<BaPveConstant> iPage = baPveConstantService.findBaPveConstantPageList(bigId, smallId, bigName, smallName, super.getToPage(), DEFAULT_PAGESIZE);
		if (iPage != null) {
			baPveConstantList = (List<BaPveConstant>) iPage.getData();
			super.setTotalPage(iPage.getTotalPage());
			super.setTotalSize(iPage.getTotalSize());
		}
		return SUCCESS;
	}
	
	public void setBaPveConstantList(List<BaPveConstant> baPveConstantList) {
		this.baPveConstantList = baPveConstantList;
	}

	public List<BaPveConstant> getBaPveConstantList() {
		return baPveConstantList;
	}

	public void setBigName(String bigName) {
		this.bigName = bigName;
	}

	public String getBigName() {
		return bigName;
	}

	public void setSmallName(String smallName) {
		this.smallName = smallName;
	}

	public String getSmallName() {
		return smallName;
	}

	public void setBigId(Integer bigId) {
		this.bigId = bigId;
	}

	public Integer getBigId() {
		return bigId;
	}

	public void setSmallId(Integer smallId) {
		this.smallId = smallId;
	}

	public Integer getSmallId() {
		return smallId;
	}

	public void setMapAreaIdNameMap(Map<Integer, String> mapAreaIdNameMap) {
		this.mapAreaIdNameMap = mapAreaIdNameMap;
	}

	public Map<Integer, String> getMapAreaIdNameMap() {
		return mapAreaIdNameMap;
	}
}
