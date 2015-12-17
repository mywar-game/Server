package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemMallAmountRule;
import com.adminTool.service.SystemMallAmountRuleService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateSystemMallAmountRuleList extends ALDAdminActionSupport {

	private int mallId;
	private String isUpdateRule;
	
	private List<SystemMallAmountRule> list = new ArrayList<SystemMallAmountRule>();
	
	private int[] idArr;
	private int[] mallIdArr;
	private int[] timesArr;
	private int[] amountArr;
	
	@Override
	public String execute() {
		SystemMallAmountRuleService service = ServiceCacheFactory.getServiceCache().getService(SystemMallAmountRuleService.class);
		if (timesArr == null) {		
			list = service.getListByMallId(mallId);
			return INPUT;
		} else {
			service.deleteByMallId(mallId);
			List<SystemMallAmountRule> ll = new ArrayList<SystemMallAmountRule>();
			for (int i = 0; i < timesArr.length; i++) {
				int times = timesArr[i];
				int amount = amountArr[i];
				SystemMallAmountRule entity = new SystemMallAmountRule(mallId, times, amount);
				ll.add(entity);
			}
			if (ll.size() > 0) {
				service.save(ll);
			}
			isUpdateRule = "T";
			return SUCCESS;
		}
	}
	
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

	public int[] getIdArr() {
		return idArr;
	}

	public void setIdArr(int[] idArr) {
		this.idArr = idArr;
	}

	public int[] getMallIdArr() {
		return mallIdArr;
	}

	public void setMallIdArr(int[] mallIdArr) {
		this.mallIdArr = mallIdArr;
	}

	public int[] getTimesArr() {
		return timesArr;
	}

	public void setTimesArr(int[] timesArr) {
		this.timesArr = timesArr;
	}

	public int[] getAmountArr() {
		return amountArr;
	}

	public void setAmountArr(int[] amountArr) {
		this.amountArr = amountArr;
	}
	
	public String getIsUpdateRule() {
		return isUpdateRule;
	}

	public void setIsUpdateRule(String isUpdateRule) {
		this.isUpdateRule = isUpdateRule;
	}
}
