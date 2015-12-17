package com.log.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.bo.UserTreasureLog;
import com.log.service.UserTreasureLogService;

public class UserTreasureLogList extends ALDAdminLogPageActionSupport {

	private static final long serialVersionUID = -1661476638553707443L;
	
	private List<UserTreasureLog> userTreasureLogList = new ArrayList<UserTreasureLog>();
	
	private String searchTreasureName;
	
	private Integer searchTreasureId;
	
	private String searchCategory;
	
	private String searchOperation;
	
	private String isCommit = "F";
	/** **/
	private Map<Integer, String> treasureIdNameMap;
	
	private Map<Integer, String> heroIdNameMap;
	
	private Map<Integer, String> equipIdNameMap;
	
	private Map<Integer, String> artifactIdNameMap;
	
	/**  玩家id和玩家map */
	private Map<String, User> userIdUserMap;
	
	@Override
	public String execute() throws Exception {
		UserTreasureLogService userTreasureLogService = ServiceCacheFactory.getServiceCache().getService(UserTreasureLogService.class);
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		if(isCommit.equals("T")){
			String searchUserId = super.searchUser();
			//搜索玩家的时候出错了，返回
			if (super.getErroDescrip() != null) {
				return SUCCESS;
			}
			IPage<Object> list = userTreasureLogService.getUserTreasureLogList(searchUserId, searchTreasureId, searchCategory, searchOperation, super.getStartDate(), super.getEndDate(), super.getToPage(), super.getPageSize());
			if (list != null) {
				TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
				List<Object> lt = (List<Object>)list.getData();
				treasureIdNameMap = treasureConstantService.findTreasureIdNameMap(); // 道具
				heroIdNameMap = treasureConstantService.findSystemHeroIdNameMap(); // 英雄列表
				equipIdNameMap = treasureConstantService.findSystemEquipIdNameMap(); // 装备
				// artifactIdNameMap = treasureConstantService.findSystemArtifactIdNameMap(); // 神器
				
				if(lt!=null && lt.size()>0){
					StringBuffer userIds = new StringBuffer();
					for (int i = 0; i < lt.size(); i++) {
						Object[] arr = (Object[])lt.get(i);
						UserTreasureLog log = new UserTreasureLog();
						log.setUserTreasureLogId(Integer.valueOf(arr[0].toString()));
						log.setUserId(arr[1].toString());
						log.setTreasureId(Integer.valueOf(arr[2].toString()));
						log.setTreasureType(Integer.valueOf(arr[3].toString()));
						log.setCategory(Integer.valueOf(arr[4].toString()));
						log.setOperation(arr[5].toString());
						log.setChangeNum(Integer.valueOf(arr[6].toString()));
						log.setExtinfo(arr[7].toString());
						log.setCreateTime(new Timestamp(DateUtil.stringtoDate(arr[8].toString(), DateUtil.FORMAT_ONE).getTime()));
						userTreasureLogList.add(log);
						userIds.append("'").append(arr[1].toString()).append("'");
						if (i < lt.size()-1) {
							userIds.append(",");
						}
					}
					userIdUserMap = userService.findUserIdAndUserMapByUserIds(userIds.toString());
					super.setTotalPage(list.getTotalPage());
					super.setTotalSize(list.getTotalSize());
				}
			}
		}
		return SUCCESS;
	}

	public List<UserTreasureLog> getUserTreasureLogList() {
		return userTreasureLogList;
	}

	public void setUserTreasureLogList(List<UserTreasureLog> userTreasureLogList) {
		this.userTreasureLogList = userTreasureLogList;
	}

	/**
	 * @return the searchTreasureId
	 */
	public Integer getSearchTreasureId() {
		return searchTreasureId;
	}

	/**
	 * @param searchTreasureId the searchTreasureId to set
	 */
	public void setSearchTreasureId(Integer searchTreasureId) {
		this.searchTreasureId = searchTreasureId;
	}

	/**
	 * @return the searchOperation
	 */
	public String getSearchOperation() {
		return searchOperation;
	}

	/**
	 * @param searchOperation the searchOperation to set
	 */
	public void setSearchOperation(String searchOperation) {
		this.searchOperation = searchOperation;
	}

	/**
	 * @return the searchTreasureName
	 */
	public String getSearchTreasureName() {
		return searchTreasureName;
	}

	/**
	 * @param searchTreasureName the searchTreasureName to set
	 */
	public void setSearchTreasureName(String searchTreasureName) {
		this.searchTreasureName = searchTreasureName;
	}

	/**
	 * @return the treasureIdNameMap
	 */
	public Map<Integer, String> getTreasureIdNameMap() {
		return treasureIdNameMap;
	}

	/**
	 * @param treasureIdNameMap the treasureIdNameMap to set
	 */
	public void setTreasureIdNameMap(Map<Integer, String> treasureIdNameMap) {
		this.treasureIdNameMap = treasureIdNameMap;
	}

	/**
	 * @return the userIdUserMap
	 */
	public Map<String, User> getUserIdUserMap() {
		return userIdUserMap;
	}

	/**
	 * @param userIdUserMap the userIdUserMap to set
	 */
	public void setUserIdUserMap(Map<String, User> userIdUserMap) {
		this.userIdUserMap = userIdUserMap;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Map<Integer, String> getHeroIdNameMap() {
		return heroIdNameMap;
	}

	public void setHeroIdNameMap(Map<Integer, String> heroIdNameMap) {
		this.heroIdNameMap = heroIdNameMap;
	}

	public Map<Integer, String> getEquipIdNameMap() {
		return equipIdNameMap;
	}

	public void setEquipIdNameMap(Map<Integer, String> equipIdNameMap) {
		this.equipIdNameMap = equipIdNameMap;
	}

	public Map<Integer, String> getArtifactIdNameMap() {
		return artifactIdNameMap;
	}

	public void setArtifactIdNameMap(Map<Integer, String> artifactIdNameMap) {
		this.artifactIdNameMap = artifactIdNameMap;
	}

}