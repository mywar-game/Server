package com.dataconfig.action;

import java.util.List;
import java.util.Map;

import com.dataconfig.bo.InstroductorReward;
import com.dataconfig.service.InstroductRewardService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class InstroductRewardList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 4134183703186010424L;
	
	private List<InstroductorReward> introductorRewardList;
	
	private Map<Integer, String> treasureIDNameMap;
	
	public String execute() {
		InstroductRewardService instroductRewardService = ServiceCacheFactory.getServiceCache().getService(InstroductRewardService.class);
		IPage<InstroductorReward> list = instroductRewardService.findInstroductRewardList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
			treasureIDNameMap = treasureConstantService.findTreasureIdNameMap();
			
			introductorRewardList = (List<InstroductorReward>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	/**
	 * @return the introductorRewardList
	 */
	public List<InstroductorReward> getIntroductorRewardList() {
		return introductorRewardList;
	}

	/**
	 * @param introductorRewardList the introductorRewardList to set
	 */
	public void setIntroductorRewardList(
			List<InstroductorReward> introductorRewardList) {
		this.introductorRewardList = introductorRewardList;
	}

	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap;
	}

	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap;
	}


}
