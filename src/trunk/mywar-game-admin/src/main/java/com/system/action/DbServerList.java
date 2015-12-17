package com.system.action;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.TDbServer;
import com.system.service.TDbServerService;

public class DbServerList extends ALDAdminPageActionSupport {

	/**  */
	private static final long serialVersionUID = -4749828771915478338L;

	private List<TDbServer> dbServerList;
	
	/** 搜索的服务器类型 */
	private Integer searchType;
	
	/** 搜索条件 */
	private String searchCondition;
	
	/** * */
	private Map<Integer, String> dbSeverIdAndInfoMap;
	
	public String execute(){
		TDbServerService dbServerService = ServiceCacheFactory.getServiceCache().getService(TDbServerService.class);
		IPage<TDbServer> list = dbServerService.findTDbServerPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			dbServerList = (List<TDbServer>) list.getData();
			for (TDbServer tdbServer : dbServerList) {
				if(tdbServer.getDbName().trim().equals("")){
					tdbServer.setDbName(super.getText("dbServerList.none"));
				}
				if(tdbServer.getPassword().trim().equals("")){
					tdbServer.setPassword(super.getText("dbServerList.none"));
				}
				if(tdbServer.getUserName().trim().equals("")){
					tdbServer.setUserName(super.getText("dbServerList.none"));
				}
			}
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}
	
	public String findDbSeverIdAndInfoMapByCondition() throws Exception{
		TDbServerService dbServerService = ServiceCacheFactory.getServiceCache().getService(TDbServerService.class);
		dbSeverIdAndInfoMap =dbServerService.findDbSeverIdAndInfoMapByCondition(searchType, URLDecoder.decode(searchCondition, "UTF-8").trim());
		return "find";
	}

	public void setDbServerList(List<TDbServer> dbServerList) {
		this.dbServerList = dbServerList;
	}

	public List<TDbServer> getDbServerList() {
		return dbServerList;
	}

	/**
	 * @return the searchType
	 */
	public Integer getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return the searchCondition
	 */
	public String getSearchCondition() {
		return searchCondition;
	}

	/**
	 * @param searchCondition the searchCondition to set
	 */
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * @return the dbSeverIdAndInfoMap
	 */
	public Map<Integer, String> getDbSeverIdAndInfoMap() {
		return dbSeverIdAndInfoMap;
	}

	/**
	 * @param dbSeverIdAndInfoMap the dbSeverIdAndInfoMap to set
	 */
	public void setDbSeverIdAndInfoMap(Map<Integer, String> dbSeverIdAndInfoMap) {
		this.dbSeverIdAndInfoMap = dbSeverIdAndInfoMap;
	}
}
