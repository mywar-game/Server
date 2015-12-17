package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemMallVipTimesRule;
import com.adminTool.service.UpdateSystemMallVipTimesRuleService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class DelSystemMallVipTimesRuleList extends ALDAdminActionSupport {

	private int id;
	private String isDel;
	private List<SystemMallVipTimesRule> list = new ArrayList<SystemMallVipTimesRule>();
	private int mallId;
	
	public List<SystemMallVipTimesRule> getList() {
		return list;
	}

	public void setList(List<SystemMallVipTimesRule> list) {
		this.list = list;
	}

	public int getMallId() {
		return mallId;
	}

	public void setMallId(int mallId) {
		this.mallId = mallId;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String execute() {
		UpdateSystemMallVipTimesRuleService service = ServiceCacheFactory.getServiceCache().getService(UpdateSystemMallVipTimesRuleService.class);
		service.deleteById(id);
		list = service.getListByMallId(mallId);
		isDel = "T";
		return SUCCESS;
	}
}
