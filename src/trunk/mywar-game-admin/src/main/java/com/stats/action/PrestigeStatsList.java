package com.stats.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.adminTool.constant.ToolTypeConstant;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.Prestige;
import com.stats.bo.PrestigeStats;
import com.stats.service.PrestigeService;

/**
 * 声望统计
 * @author Administrator
 *
 */
public class PrestigeStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 647336795038945440L;
	private String isCommit = "F";

	Map<String, PrestigeStats> resultMap = new HashMap<String, PrestigeStats>();
	List<Prestige> resultList = new ArrayList<Prestige>();
	
	public List<Prestige> getResultList() {
		return resultList;
	}

	public void setResultList(List<Prestige> resultList) {
		this.resultList = resultList;
	}

	public Map<String, PrestigeStats> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, PrestigeStats> resultMap) {
		this.resultMap = resultMap;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	@Override
	public String execute() {
		
		PrestigeService prestigeService = ServiceCacheFactory.getServiceCache().getService(PrestigeService.class);	
		if (isCommit != null && isCommit == "TF" || isCommit.equalsIgnoreCase("TF")) {
			isCommit = "F";
		}
		List<PrestigeStats> statsList = new ArrayList<PrestigeStats>();
		
		// 判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			statsList = prestigeService.findList();
		} else {
			statsList = prestigeService.findListInDate(getStartDate(), getEndDate());
		}

		for (PrestigeStats s : statsList) {
			String toolDesc = s.getToolDesc();
			if (toolDesc == "" || toolDesc.equals("")) {
				continue;
			}
			String[] toolDescArr1 = toolDesc.split(",");
			for (int i = 0; i < toolDescArr1.length; i++) {
				String[] toolDescArr2 = toolDescArr1[i].split("_");
				String toolType = toolDescArr2[0];
				String toolId = toolDescArr2[1];
				String toolCount = toolDescArr2[2];
				ToolTypeConstant typeConstant = new ToolTypeConstant();
				String desc = typeConstant.getToolTypeAndName(toolType + "," + toolId + "," + toolCount);
				
				if (resultMap.get(toolType + "_" + toolId + "_" + s.getTime()) == null) {
					PrestigeStats tempStats = new PrestigeStats();
					tempStats.setId(s.getId());
					tempStats.setSysNum(s.getSysNum());
					tempStats.setTime(s.getTime());
					tempStats.setToolName(desc.split(",")[1]);
					tempStats.setToolCount(toolCount);
					resultMap.put(toolType + "_" + toolId + "_" + s.getTime(), tempStats);
				}
			}
			
			String userDesc = s.getUserDesc();
			String[] userDescArr1 = userDesc.split(",");
			for (int j = 0; j < userDescArr1.length; j++) {
				String[] userDescArr2 = userDescArr1[j].split("_");
				String toolType = userDescArr2[0];
				String toolId = userDescArr2[1];
				String userCount = userDescArr2[2];
				if (resultMap.get(toolType + "_" + toolId + "_" + s.getTime()) != null) {
					PrestigeStats tempStats = resultMap.get(toolType + "_" + toolId + "_" + s.getTime());
					tempStats.setUserCount(userCount);
				}
			}
		}
		if (resultMap != null) {
			Iterator<String> iter = resultMap.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				PrestigeStats s = resultMap.get(key);
				Prestige prestige = new Prestige();
				prestige.setId(s.getId());
				prestige.setSysNum(s.getSysNum());
				prestige.setTime(s.getTime());
				prestige.setToolName(s.getToolName());
				prestige.setToolCount(s.getToolCount());
				prestige.setUserCount(s.getUserCount());
				resultList.add(prestige);
			}
		}
		Collections.sort(resultList, new Comparator<Prestige>() {
			public int compare(Prestige arg0, Prestige arg1) {
				return arg1.getTime().compareTo(arg0.getTime());
			}
		});
		return SUCCESS;
	}
}
