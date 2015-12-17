package com.log.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.dataconfig.service.SystemArtifactService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.bo.UserArtifactLog;
import com.log.service.UserArtifactLogService;

/**
 * 获取用户神器操作日志
 * 
 * @author yezp
 */
public class UserArtifactLogList extends ALDAdminLogPageActionSupport {

	private static final long serialVersionUID = 1640801053723004524L;

	private List<UserArtifactLog> userArtifactLogList = new ArrayList<UserArtifactLog>();

	private Map<Integer, String> artifactIdNameMap;

	/** 玩家id和玩家map */
	private Map<String, User> userIdUserMap;

	private Integer searchType;

	private String isCommit = "F";

	public String execute() {
		UserArtifactLogService userArtifactLogService = ServiceCacheFactory
				.getServiceCache().getService(UserArtifactLogService.class);
		SystemArtifactService systemArtifactService = ServiceCacheFactory
				.getServiceCache().getService(SystemArtifactService.class);
		UserService userService = ServiceCacheFactory.getServiceCache()
				.getService(UserService.class);
		artifactIdNameMap = systemArtifactService.findArtifactIDNameMap();
		if (isCommit.equals("F")) {
			return SUCCESS;
		}

		String searchUserId = super.searchUser();
		// 搜索玩家的时候出错了，返回
		if (super.getErroDescrip() != null) {
			return SUCCESS;
		}

		IPage<Object> page = userArtifactLogService.findPageLogListByCondition(
				searchUserId, super.getStartDate(), super.getEndDate(),
				searchType, super.getToPage(), super.getPageSize());
		if (page == null) {
			return SUCCESS;
		}

		List<Object> list = (List<Object>) page.getData();
		if (list == null || list.size() == 0) {
			return SUCCESS;
		}

		StringBuffer userIds = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Object[] arr = (Object[]) list.get(i);
			UserArtifactLog log = new UserArtifactLog();
			log.setUserArtifactLogId(Integer.valueOf(arr[0].toString()));
			log.setUserId(arr[1].toString());
			log.setUserArtifactId(arr[2].toString());
			log.setArtifactId(Integer.valueOf(arr[3].toString()));
			log.setArtifactAttStr(arr[4].toString());
			log.setNum(Integer.valueOf(arr[5].toString()));
			log.setOperationType(Integer.valueOf(arr[6].toString()));
			log.setOperationSmallType(Integer.valueOf(arr[7].toString()));
			log.setCreatedTime(new Timestamp(DateUtil.stringtoDate(
					arr[8].toString(), DateUtil.FORMAT_ONE).getTime()));

			userArtifactLogList.add(log);
			userIds.append("'").append(arr[1].toString()).append("'");
			if (i < list.size() - 1) {
				userIds.append(",");
			}
		}
		userIdUserMap = userService.findUserIdAndUserMapByUserIds(userIds
				.toString());
		super.setTotalPage(page.getTotalPage());
		super.setTotalSize(page.getPageSize());

		return SUCCESS;
	}

	public List<UserArtifactLog> getUserArtifactLogList() {
		return userArtifactLogList;
	}

	public void setUserArtifactLogList(List<UserArtifactLog> userArtifactLogList) {
		this.userArtifactLogList = userArtifactLogList;
	}

	public Map<Integer, String> getArtifactIdNameMap() {
		return artifactIdNameMap;
	}

	public void setArtifactIdNameMap(Map<Integer, String> artifactIdNameMap) {
		this.artifactIdNameMap = artifactIdNameMap;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public Map<String, User> getUserIdUserMap() {
		return userIdUserMap;
	}

	public void setUserIdUserMap(Map<String, User> userIdUserMap) {
		this.userIdUserMap = userIdUserMap;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
