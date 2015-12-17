package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemMallAmountRule;
import com.adminTool.service.SystemMallAmountRuleService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class DelSystemMallAmountRuleList extends ALDAdminActionSupport {

	private int id;
	private int mallId;
	
	private String isDel;
	private List<SystemMallAmountRule> list = new ArrayList<SystemMallAmountRule>();
	
	public int getMallId() {
		return mallId;
	}

	public void setMallId(int mallId) {
		this.mallId = mallId;
	}

	public List<SystemMallAmountRule> getList() {
		return list;
	}

	public void setList(List<SystemMallAmountRule> list) {
		this.list = list;
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
		SystemMallAmountRuleService service = ServiceCacheFactory.getServiceCache().getService(SystemMallAmountRuleService.class);
		service.deleteById(id);
		list = service.getListByMallId(mallId);
		isDel = "T";
		return SUCCESS;
	}
}
