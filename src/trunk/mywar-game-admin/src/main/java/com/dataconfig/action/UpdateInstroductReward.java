package com.dataconfig.action;

import com.dataconfig.bo.InstroductorReward;
import com.dataconfig.service.InstroductRewardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateInstroductReward extends ALDAdminActionSupport implements ModelDriven<InstroductorReward> {
	
	private static final long serialVersionUID = -129679858266578888L;

	private InstroductorReward instroductorReward = new InstroductorReward();
	
	private String isCommit = "F";
	
	public String execute() {
		InstroductRewardService instroductRewardService = ServiceCacheFactory.getServiceCache().getService(InstroductRewardService.class);
		if ("F".equals(isCommit)) {
			instroductorReward = instroductRewardService.findOneInstroductReward(instroductorReward.getInstroductorRewardId());
			return INPUT;
		} else {
			instroductRewardService.updateInstroductReward(instroductorReward);
			return SUCCESS;
		}
	}
	
	@Override
	public InstroductorReward getModel() {
		// TODO Auto-generated method stub
		return instroductorReward;
	}
	
	public String getIsCommit() {
		return isCommit;
	}
	
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	/**
	 * @return the instroductorReward
	 */
	public InstroductorReward getInstroductorReward() {
		return instroductorReward;
	}
	/**
	 * @param instroductorReward the instroductorReward to set
	 */
	public void setInstroductorReward(InstroductorReward instroductorReward) {
		this.instroductorReward = instroductorReward;
	}
	
}
