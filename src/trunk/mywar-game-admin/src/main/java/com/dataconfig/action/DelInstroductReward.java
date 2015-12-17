package com.dataconfig.action;

import com.dataconfig.bo.InstroductorReward;
import com.dataconfig.service.InstroductRewardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelInstroductReward extends ALDAdminActionSupport implements ModelDriven<InstroductorReward> {

	private static final long serialVersionUID = 8151498192362020085L;
	
	private InstroductorReward instroductorReward = new InstroductorReward();

	public void executeDel() {
		InstroductRewardService instroductRewardService = ServiceCacheFactory.getServiceCache().getService(InstroductRewardService.class);
		instroductRewardService.delInstroductReward(instroductorReward.getInstroductorRewardId());
	}
	
	@Override
	public InstroductorReward getModel() {
		return instroductorReward;
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
