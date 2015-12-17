package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemDiamondcat;
import com.adminTool.service.UpdateSystemDiamondcatService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateSystemDiamondcat extends ALDAdminActionSupport {

	private static final long serialVersionUID = 1L;
	private String isCommit = "F";
	private int activityId = -1;
	private List<SystemDiamondcat> list = new ArrayList<SystemDiamondcat>();
	//private String[] idArr;
	private String[] diamondUseArr;

	private String[] diamondReceiveArr;
	
	@Override
	public String execute() {
		
		UpdateSystemDiamondcatService service = ServiceCacheFactory.getServiceCache().getService(UpdateSystemDiamondcatService.class);
		
		if (isCommit.equals("F") || isCommit == "F") {
			list = service.getList(activityId);
			return INPUT;
		}
		List<SystemDiamondcat> list1 = new ArrayList<SystemDiamondcat>();
		for (int i = 0; i < diamondUseArr.length; i++) {
			SystemDiamondcat cat = new SystemDiamondcat(i + 1, Integer.valueOf(diamondUseArr[i]), Float.valueOf(diamondReceiveArr[i]), activityId);
			list1.add(cat);
		}
		if (list1.size() != 0) {
			service.deleteList(activityId);
			service.saveList(list1);
		}
		return SUCCESS;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public List<SystemDiamondcat> getList() {
		return list;
	}

	public void setList(List<SystemDiamondcat> list) {
		this.list = list;
	}
	public String[] getDiamondUseArr() {
		return diamondUseArr;
	}

	public void setDiamondUseArr(String[] diamondUseArr) {
		this.diamondUseArr = diamondUseArr;
	}

	public String[] getDiamondReceiveArr() {
		return diamondReceiveArr;
	}

	public void setDiamondReceiveArr(String[] diamondReceiveArr) {
		this.diamondReceiveArr = diamondReceiveArr;
	}
}
