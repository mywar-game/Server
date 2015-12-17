package com.log.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.bo.UserResourceLog;
import com.log.service.UserResourceLogService;

public class UserResourceLogList extends ALDAdminLogPageActionSupport {
	
	private static final long serialVersionUID = 4234947616308485553L;

	private List<UserResourceLog> userResourceLoglist = new ArrayList<UserResourceLog>();
	private String isCommit = "F";
	/**  玩家id和玩家map */
	private Map<String, User> userIdUserMap;
	
	private String searchOperation;
	
	@Override
	public String execute() throws Exception {
		UserResourceLogService userResourceLogService = ServiceCacheFactory.getServiceCache().getService(UserResourceLogService.class);
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		if(isCommit.equals("T")){
			String searchUserId = super.searchUser();
			//搜索玩家的时候出错了，返回
			if (super.getErroDescrip() != null) {
				return SUCCESS;
			}
			
			IPage<Object> list = userResourceLogService.getUserResourceLogListBySouceType(searchUserId, searchOperation, 3, super.getStartDate(),super.getEndDate(), super.getToPage(), super.getPageSize());
			if (list != null) {
				List<Object> lt = (List<Object>) list.getData();
				if(lt!=null && lt.size()>0){
					StringBuffer userIds = new StringBuffer();
					for (int i = 0; i < lt.size(); i++) {
						Object[] arr = (Object[])lt.get(i);
						UserResourceLog log = new UserResourceLog();
						log.setUserResourceLogId(Integer.valueOf(arr[0].toString()));
						log.setUserId(arr[1].toString());
						log.setSourceType(Integer.valueOf(arr[2].toString()));
						log.setOperation(arr[3].toString());
						log.setChangeNum(Integer.parseInt(arr[4].toString()));
						log.setNowNum(Integer.parseInt(arr[5].toString()));
						log.setCreateTime(new Timestamp(DateUtil.stringtoDate(arr[6].toString(), DateUtil.FORMAT_ONE).getTime()));
						userResourceLoglist.add(log);
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

	public List<UserResourceLog> getUserResourceLoglist() {
		return userResourceLoglist;
	}

	public void setUserResourceLoglist(List<UserResourceLog> userResourceLoglist) {
		this.userResourceLoglist = userResourceLoglist;
	}

	public void setUserIdUserMap(Map<String, User> userIdUserMap) {
		this.userIdUserMap = userIdUserMap;
	}

	public Map<String, User> getUserIdUserMap() {
		return userIdUserMap;
	}

	public void setSearchOperation(String searchOperation) {
		this.searchOperation = searchOperation;
	}

	public String getSearchOperation() {
		return searchOperation;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}