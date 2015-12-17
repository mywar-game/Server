package com.log.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.adminTool.service.UserService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.GuildLog;
import com.log.service.GuildLogService;

/**
 * 军团日志列表
 * @author hzy
 * 2012-7-23
 */
public class GuildLogList extends ALDAdminPageActionSupport {
	
	/**  */
	private static final long serialVersionUID = 1L;
	
	/**  */
	private List<GuildLog> guildLogList;
	
	/**  */
	private String inputName;
	
	/**  */
	private Long guildKey;
	
	/**  */
	private String searchOperation;
	
	/**  */
	private Map<String, String> userIdNameMap = new HashMap<String, String>();

	@Override
	public String execute() throws Exception {
		GuildLogService guildLogService = ServiceCacheFactory.getServiceCache().getService(GuildLogService.class);
		IPage<GuildLog> list = guildLogService.getGuildLogPageList(guildKey, inputName, searchOperation, super.getToPage(), super.getPageSize());
		if (list != null) {
			guildLogList = (List<GuildLog>)list.getData();
			if (guildLogList != null && guildLogList.size() > 0) {
				Set<Long> userIdSet = new HashSet<Long>();
				for (GuildLog guildLog : guildLogList) {
					userIdSet.add(guildLog.getUserId());
					userIdSet.add(guildLog.getOptUserId());
				}
				UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
				userIdNameMap = userService.findUserIdNameMapByUserIds(userIdSet.toString().substring(1, userIdSet.toString().length() - 1));
			}
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	/**
	 * @return the guildLogList
	 */
	public List<GuildLog> getGuildLogList() {
		return guildLogList;
	}

	/**
	 * @param guildLogList the guildLogList to set
	 */
	public void setGuildLogList(List<GuildLog> guildLogList) {
		this.guildLogList = guildLogList;
	}

	/**
	 * @return the inputName
	 */
	public String getInputName() {
		return inputName;
	}

	/**
	 * @param inputName the inputName to set
	 */
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	/**
	 * @return the guildKey
	 */
	public Long getGuildKey() {
		return guildKey;
	}

	/**
	 * @param guildKey the guildKey to set
	 */
	public void setGuildKey(Long guildKey) {
		this.guildKey = guildKey;
	}

	/**
	 * @return the userIdNameMap
	 */
	public Map<String, String> getUserIdNameMap() {
		return userIdNameMap;
	}

	/**
	 * @param userIdNameMap the userIdNameMap to set
	 */
	public void setUserIdNameMap(Map<String, String> userIdNameMap) {
		this.userIdNameMap = userIdNameMap;
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
}