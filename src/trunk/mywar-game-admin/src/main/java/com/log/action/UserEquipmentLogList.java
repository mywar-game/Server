package com.log.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.dataconfig.service.EEquipmentConstantService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.bo.UserEquipmentLog;
import com.log.service.UserEquipmentLogService;

public class UserEquipmentLogList extends ALDAdminLogPageActionSupport {
	
	private static final long serialVersionUID = -6416087084248452149L;

	private List<UserEquipmentLog> userEquipmentLogList = new ArrayList<UserEquipmentLog>();
	
	private Map<Integer, String> equipmentIdNameMap;
	
	/**  玩家id和玩家map */
	private Map<String, User> userIdUserMap;
	
	public Map<String, User> getUserIdUserMap() {
		return userIdUserMap;
	}

	public void setUserIdUserMap(Map<String, User> userIdUserMap) {
		this.userIdUserMap = userIdUserMap;
	}

	private Integer searchType;
	
	private String isCommit = "F";
	
	public String execute() {
		UserEquipmentLogService userEquipmentLogService = ServiceCacheFactory.getServiceCache().getService(UserEquipmentLogService.class);
		EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		equipmentIdNameMap = equipmentConstantService.findEquipmentIDNameMap();
		if(isCommit.equals("T")){
			String searchUserId = super.searchUser();
			//搜索玩家的时候出错了，返回
			if (super.getErroDescrip() != null) {
				return SUCCESS;
			}
			
			IPage<Object> page = userEquipmentLogService.findPageLogListByCondition(searchUserId, super.getStartDate(), super.getEndDate(), searchType, super.getToPage(), super.getPageSize());
			if (page != null) {
				List<Object> list = (List<Object>) page.getData();
				if(list!=null && list.size()>0){
					StringBuffer userIds = new StringBuffer();
					for(int i=0;i<list.size();i++){
						Object[] arr = (Object[])list.get(i);
						UserEquipmentLog log = new UserEquipmentLog();
						log.setUserEquipmentLogId(Integer.valueOf(arr[0].toString()));
						log.setUserId(arr[1].toString());
						log.setUserEquipmentId(arr[2].toString());
						log.setEquipmentId(Integer.valueOf(arr[3].toString()));
						log.setUserHeroId(arr[4].toString());
						log.setEquipmentLevel(Integer.valueOf(arr[5].toString()));
						log.setType(Integer.valueOf(arr[6].toString()));
						log.setOperationTime(new Timestamp(DateUtil.stringtoDate(arr[7].toString(), DateUtil.FORMAT_ONE).getTime()));
						userEquipmentLogList.add(log);
						userIds.append("'").append(arr[1].toString()).append("'");
						if (i < list.size()-1) {
							userIds.append(",");
						}
					}
					userIdUserMap = userService.findUserIdAndUserMapByUserIds(userIds.toString());
					super.setTotalPage(page.getTotalPage());
					super.setTotalSize(page.getPageSize());
				}
			}
		}
		
		return SUCCESS;
	}

	public void setEquipmentIdNameMap(Map<Integer, String> equipmentIdNameMap) {
		this.equipmentIdNameMap = equipmentIdNameMap;
	}

	public Map<Integer, String> getEquipmentIdNameMap() {
		return equipmentIdNameMap;
	}
	
	public List<UserEquipmentLog> getUserEquipmentLogList() {
		return userEquipmentLogList;
	}
	
	public void setUserEquipmentLogList(List<UserEquipmentLog> userEquipmentLogList) {
		this.userEquipmentLogList = userEquipmentLogList;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	
}