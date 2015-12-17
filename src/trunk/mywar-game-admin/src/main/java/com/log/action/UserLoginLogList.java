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
import com.log.bo.UserLoginLog;
import com.log.service.UserLoginLogService;

public class UserLoginLogList extends ALDAdminLogPageActionSupport {

	/** * */
	private static final long serialVersionUID = -5145912266994624909L;
	private String isCommit = "F";
	private List<UserLoginLog> userLoginLogList = new ArrayList<UserLoginLog>();
	
	@Override
	public String execute() throws Exception {
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		if(isCommit.equals("T")){
			IPage<Object> list = userLoginLogService.findUserPageLogListByCondition(super.getLodoId(),super.getStartDate(),super.getEndDate(), super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
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
					UserLoginLog log = new UserLoginLog();
					log.setUserId(arr[0].toString());
					log.setLevel(Integer.valueOf(arr[1].toString()));
					log.setIp(arr[2].toString());
					log.setLoginTime(new Timestamp(DateUtil.stringtoDate(arr[3].toString(), DateUtil.FORMAT_ONE).getTime()));
					log.setUserName(arr[4].toString());
					log.setLodoId(Integer.valueOf(arr[5].toString()));
					userLoginLogList.add(log);
				}
				super.setTotalPage(list.getTotalPage());
				super.setTotalSize(list.getTotalSize());
			}
		}
		
		return SUCCESS;
	}

	/**
	 * 获取 userLoginLogList 
	 */
	public List<UserLoginLog> getUserLoginLogList() {
		return userLoginLogList;
	}

	/**
	 * 设置 userLoginLogList 
	 */
	public void setUserLoginLogList(List<UserLoginLog> userLoginLogList) {
		this.userLoginLogList = userLoginLogList;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
