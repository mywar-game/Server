package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemMallVipTimesRule;
import com.adminTool.service.UpdateSystemMallVipTimesRuleService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateSystemMallVipTimesRuleList extends ALDAdminActionSupport {

	private static final long serialVersionUID = 3561415939363485414L;
	private int mallId;
	private String isUpdateRule;
	private int[] vipLevel;
	private int[] dailyMaxTimes;
	
	private List<SystemMallVipTimesRule> list = new ArrayList<SystemMallVipTimesRule>();
	
	@Override
	public String execute() {
		UpdateSystemMallVipTimesRuleService service = ServiceCacheFactory.getServiceCache().getService(UpdateSystemMallVipTimesRuleService.class);
		if (vipLevel == null) {
			list = service.getListByMallId(mallId);
			return SUCCESS;
		} else {
			service.deleteByMallId(mallId);
			List<SystemMallVipTimesRule> list1 = new ArrayList<SystemMallVipTimesRule>();
			for (int i = 0; i < vipLevel.length; i++) {
				int vipLev = vipLevel[i];
				int dai = dailyMaxTimes[i];
				SystemMallVipTimesRule r = new SystemMallVipTimesRule();
				r.setVipLevel(vipLev);
				r.setDailyMaxTimes(dai);
				r.setMallId(mallId);
				list1.add(r);
			}
			if (list1.size() > 0) {
				service.save(list1);
			}
			return INPUT;
		}
	}
	
	public int getMallId() {
		return mallId;
	}

	public void setMallId(int mallId) {
		this.mallId = mallId;
	}

	public String getIsUpdateRule() {
		return isUpdateRule;
	}

	public void setIsUpdateRule(String isUpdateRule) {
		this.isUpdateRule = isUpdateRule;
	}

	public List<SystemMallVipTimesRule> getList() {
		return list;
	}

	public void setList(List<SystemMallVipTimesRule> list) {
		this.list = list;
	}

	public int[] getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(int[] vipLevel) {
		this.vipLevel = vipLevel;
	}

	public int[] getDailyMaxTimes() {
		return dailyMaxTimes;
	}

	public void setDailyMaxTimes(int[] dailyMaxTimes) {
		this.dailyMaxTimes = dailyMaxTimes;
	}

}
