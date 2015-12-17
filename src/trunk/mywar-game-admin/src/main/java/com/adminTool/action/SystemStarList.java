package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemStar;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.SystemStarService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 星图配置
 * @author Administrator
 *
 */
public class SystemStarList extends ALDAdminActionSupport {

	private static final long serialVersionUID = 1L;
	private List<SystemStar> systemStarList = new ArrayList<SystemStar>();
	private Boolean isCommit = false;
	private Integer starId = -1;

	private List<SystemStar> starList = new ArrayList<SystemStar>();
	
	public String execute() {
		
		SystemStarService systemStarService = ServiceCacheFactory.getServiceCache().getService(SystemStarService.class);
		if (isCommit == false) {
			
			systemStarList = systemStarService.findAllSystemStarPageList();
			if (systemStarList.size() == 0) {
				return SUCCESS;
			}
			ToolTypeConstant typeConstant = new ToolTypeConstant();
			for (SystemStar s : systemStarList) {
				String showRewardsStr = typeConstant.getToolTypeAndName(s.getRewards());
				s.setShowRewards(showRewardsStr);
			}
			return SUCCESS;
		} else {
			ToolTypeConstant typeConstant = new ToolTypeConstant();
			starList = systemStarService.findByStarId(starId);	
			if (starList != null) {
				for (SystemStar s : starList) {
					String showRewardsStr = typeConstant.getToolTypeAndName(s.getRewards());
					s.setShowRewards(showRewardsStr);
				}
			}
			return INPUT;
		}
	}
	
	public List<SystemStar> getSystemStarList() {
		return systemStarList;
	}

	public void setSystemStarList(List<SystemStar> systemStarList) {
		this.systemStarList = systemStarList;
	}

	public Boolean getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(Boolean isCommit) {
		this.isCommit = isCommit;
	}

	public Integer getStarId() {
		return starId;
	}

	public void setStarId(Integer starId) {
		this.starId = starId;
	}

	public List<SystemStar> getStarList() {
		return starList;
	}

	public void setStarList(List<SystemStar> starList) {
		this.starList = starList;
	}

}
