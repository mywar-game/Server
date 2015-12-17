package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.RewardConstant;
import com.dataconfig.service.RewardConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class RewardConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -4053340086047405965L;

	private List<RewardConstant> rewardConstantList;
	
	public String execute() {
		RewardConstantService rewardConstantService = ServiceCacheFactory.getServiceCache().getService(RewardConstantService.class);
		IPage<RewardConstant> list = rewardConstantService.findPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			rewardConstantList = (List<RewardConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setRewardConstantList(List<RewardConstant> rewardConstantList) {
		this.rewardConstantList = rewardConstantList;
	}

	public List<RewardConstant> getRewardConstantList() {
		return rewardConstantList;
	}
	
}
