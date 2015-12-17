package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.System7LoginReward;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.System7LoginRewardService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 连续登录7天奖励配置
 * @author Administrator
 *
 */
public class Update7LoginReward extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 6530894355296718425L;
	
	private List<System7LoginReward> system7LoginRewardList;
	private Integer[] idArr;
	private Integer[] dayArr;
	private String[] toolIdsArr;
	private Boolean isCommit = false;
	
	@Override
	public String execute() {
		
		System7LoginRewardService service = ServiceCacheFactory.getServiceCache()
				.getService(System7LoginRewardService.class);
		if (isCommit) {
			for (int i = 0; i < idArr.length; i++) {
				System7LoginReward reward = new System7LoginReward();
				reward.setId(idArr[i]);
				reward.setDay(dayArr[i]);
				reward.setToolIds(toolIdsArr[i]);
				service.update(reward);
			}
		}
		system7LoginRewardList = service.findAll();
		
		ToolTypeConstant typeConstant = new ToolTypeConstant();
		if (system7LoginRewardList != null) {
			for (System7LoginReward s : system7LoginRewardList) {
				String show = typeConstant.getToolTypeAndName(s.getToolIds());
				s.setDesc(show);
			}
		}
		return SUCCESS;
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

	public Boolean getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(Boolean isCommit) {
		this.isCommit = isCommit;
	}
	public List<System7LoginReward> getSystem7LoginRewardList() {
		return system7LoginRewardList;
	}

	public void setSystem7LoginRewardList(
			List<System7LoginReward> system7LoginRewardList) {
		this.system7LoginRewardList = system7LoginRewardList;
	}
	public Integer[] getIdArr() {
		return idArr;
	}
	public void setIdArr(Integer[] idArr) {
		this.idArr = idArr;
	}
}
