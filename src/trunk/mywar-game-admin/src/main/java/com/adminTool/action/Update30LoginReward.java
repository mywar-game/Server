package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.System30LoginReward;
import com.adminTool.service.System30LoginRewardService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 30天登录奖励配置
 * @author Administrator
 *
 */
public class Update30LoginReward extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 6530894355296718425L;
	
	private List<System30LoginReward> system30LoginRewardList;
	private Integer[] system30LoginRewardIdArr;
	private Integer[] dayArr;
	private String[] toolIdsArr;
	private String[] toolDescArr;
	private Boolean isCommit = false;
	
	@Override
	public String execute() {
		
		System30LoginRewardService service = ServiceCacheFactory.getServiceCache()
				.getService(System30LoginRewardService.class);
		if (isCommit) {
			for (int i = 0; i < system30LoginRewardIdArr.length; i++) {
				System30LoginReward reward = new System30LoginReward();
				reward.setSystem30LoginRewardId(system30LoginRewardIdArr[i]);
				reward.setDay(dayArr[i]);
				reward.setToolIds(toolIdsArr[i]);
				reward.setToolDesc(toolDescArr[i]);
				service.update(reward);
			}
		}
		system30LoginRewardList = service.findAll();
		return SUCCESS;
	}

	public List<System30LoginReward> getSystem30LoginRewardList() {
		return system30LoginRewardList;
	}

	public void setSystem30LoginRewardList(
			List<System30LoginReward> system30LoginRewardList) {
		this.system30LoginRewardList = system30LoginRewardList;
	}

	public Integer[] getSystem30LoginRewardIdArr() {
		return system30LoginRewardIdArr;
	}

	public void setSystem30LoginRewardIdArr(Integer[] system30LoginRewardIdArr) {
		this.system30LoginRewardIdArr = system30LoginRewardIdArr;
	}

	public Integer[] getDayArr() {
		return dayArr;
	}

	public void setDayArr(Integer[] dayArr) {
		this.dayArr = dayArr;
	}

	public String[] getToolIdsArr() {
		return toolIdsArr;
	}

	public void setToolIdsArr(String[] toolIdsArr) {
		this.toolIdsArr = toolIdsArr;
	}

	public String[] getToolDescArr() {
		return toolDescArr;
	}

	public void setToolDescArr(String[] toolDescArr) {
		this.toolDescArr = toolDescArr;
	}

	public Boolean getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(Boolean isCommit) {
		this.isCommit = isCommit;
	}
}
