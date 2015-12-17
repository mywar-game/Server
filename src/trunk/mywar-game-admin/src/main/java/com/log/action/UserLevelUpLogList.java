package com.log.action;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserLevelupLog;
import com.log.service.UserLevelupLogService;

public class UserLevelUpLogList extends ALDAdminLogPageActionSupport {
	
	private static final long serialVersionUID = 4233539363568281308L;
	private String isCommit = "F";
	private List<UserLevelupLog> userLevelUpLogList = new ArrayList<UserLevelupLog>();

	@Override
	public String execute() throws Exception {
		if(isCommit.equals("T")){
			String searchUserId = super.searchUser();
			//搜索玩家的时候出错了，返回
			if (super.getErroDescrip() != null) {
				return SUCCESS;
			}
			
			UserLevelupLogService userLevelupLogService = ServiceCacheFactory.getServiceCache().getService(UserLevelupLogService.class);
			IPage<UserLevelupLog> list = userLevelupLogService.findUserLevelUpLogByCondition(searchUserId,super.getStartDate(),super.getEndDate(), super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
			
			if (list != null) {
				userLevelUpLogList = (List<UserLevelupLog>) list.getData();
				super.setTotalPage(list.getTotalPage());
				super.setTotalSize(list.getTotalSize());
			}
		}
		return SUCCESS;
	}

	public List<UserLevelupLog> getUserLevelUpLogList() {
		return userLevelUpLogList;
	}

	public void setUserLevelUpLogList(List<UserLevelupLog> userLevelUpLogList) {
		this.userLevelUpLogList = userLevelUpLogList;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	
}
