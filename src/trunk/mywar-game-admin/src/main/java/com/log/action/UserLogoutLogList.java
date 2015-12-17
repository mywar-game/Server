package com.log.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.admin.util.Tools;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.bo.UserLogoutLog;
import com.log.service.UserLogoutLogService;

public class UserLogoutLogList extends ALDAdminLogPageActionSupport {

	/** * */
	private static final long serialVersionUID = -3856564640389773430L;
	private String isCommit = "F";
	private List<UserLogoutLog> userLogoutLogList = new ArrayList<UserLogoutLog>();
	
	@Override
	public String execute() throws Exception {
		if(isCommit.equals("T")){
			UserLogoutLogService userLogoutLogService = ServiceCacheFactory.getServiceCache().getService(UserLogoutLogService.class);
			IPage<Object> list = userLogoutLogService.findUserPageLogListByCondition(super.getLodoId(),super.getStartDate(),super.getEndDate(), super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
			if (list != null) {
				List<Object> lt = (List<Object>) list.getData();
				//如果没有数据看玩家是否存在
				if (lt == null || lt.size() == 0) {
					super.searchUser();
					if (!Tools.isEmpty(super.getErroDescrip())) {
						return SUCCESS;
					}
				}
				for(int i=0;i<lt.size();i++){
					Object[] arr = (Object[])lt.get(i);
					UserLogoutLog log = new UserLogoutLog();
					log.setUserId(arr[0].toString());
					log.setIp(arr[1].toString());
					log.setLogoutTime(new Timestamp(DateUtil.stringtoDate(arr[2].toString(), DateUtil.FORMAT_ONE).getTime()));
					log.setLiveMinutes(Integer.valueOf(arr[3].toString()));
					log.setUserName(arr[4].toString());
					log.setLodoId(Integer.valueOf(arr[5].toString()));
					userLogoutLogList.add(log);
				}
				super.setTotalPage(list.getTotalPage());
				super.setTotalSize(list.getTotalSize());
			}
		}
		return SUCCESS;
	}

	/**
	 * 获取 userLogoutLogList 
	 */
	public List<UserLogoutLog> getUserLogoutLogList() {
		return userLogoutLogList;
	}

	/**
	 * 设置 userLogoutLogList 
	 */
	public void setUserLogoutLogList(List<UserLogoutLog> userLogoutLogList) {
		this.userLogoutLogList = userLogoutLogList;
	}
	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
