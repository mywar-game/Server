package com.log.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.bo.UserHeroLog;
import com.log.service.UserHeroLogService;

public class UserHeroLogList extends ALDAdminLogPageActionSupport {
	
	private static final long serialVersionUID = 8226049974399737600L;

	private List<UserHeroLog> userHeroLogList = new ArrayList<UserHeroLog>();
	/**  玩家id和玩家map */
	private Map<String, User> userIdUserMap;
	//（1.获取 2.吞噬 3.被吞噬 4.进阶  5.出售）
	private Integer logType;
	private String isCommit = "F";
	public String execute() {
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		UserHeroLogService userHeroLogService = ServiceCacheFactory.getServiceCache().getService(UserHeroLogService.class);
		if(isCommit.equals("T")){
			String searchUserId = super.searchUser();
			//搜索玩家的时候出错了，返回
			if (super.getErroDescrip() != null) {
				return SUCCESS;
			}
			if(logType==null){
				logType = 0;
			}
			IPage<Object> page = userHeroLogService.findUserHeroLogByCondition(searchUserId,logType, super.getStartDate(),super.getEndDate(),super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
			if (page != null) {
				List<Object> list = (List<Object>) page.getData();
				if(list!=null && list.size()>0){
					StringBuffer userIds = new StringBuffer();
					for(int i=0;i<list.size();i++){
						Object[] arr = (Object[])list.get(i);
						UserHeroLog log = new UserHeroLog();
						log.setUserHeroLogId(Long.valueOf(arr[0].toString()));
						log.setUserId(arr[1].toString());
						log.setUserHeroId(arr[2].toString());
						log.setHeroId(Integer.valueOf(arr[3].toString()));
						log.setType(Integer.valueOf(arr[4].toString()));
						log.setExp(Long.valueOf(arr[5].toString()));
						log.setLevel(Integer.valueOf(arr[6].toString()));
						log.setPos(Short.valueOf(arr[7].toString()));
						log.setExpNum(Integer.valueOf(arr[8].toString()));
						log.setOperationTime(new Timestamp(DateUtil.stringtoDate(arr[9].toString(), DateUtil.FORMAT_ONE).getTime()));
						log.setHeroName(arr[10].toString());
						userHeroLogList.add(log);
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
	
	public List<UserHeroLog> getUserHeroLogList() {
		return userHeroLogList;
	}
	
	public void setUserHeroLogList(List<UserHeroLog> userHeroLogList) {
		this.userHeroLogList = userHeroLogList;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Map<String, User> getUserIdUserMap() {
		return userIdUserMap;
	}

	public void setUserIdUserMap(Map<String, User> userIdUserMap) {
		this.userIdUserMap = userIdUserMap;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}


}