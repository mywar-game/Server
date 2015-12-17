package com.adminTool.action;

import com.adminTool.bo.TotalDayPayReward;
import com.adminTool.service.TotalDayPayRewardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加充值累积天数奖励配置
 * 
 * @author yezp
 */
public class AddTotalDayPayReward extends ALDAdminActionSupport implements
		ModelDriven<TotalDayPayReward> {

	private static final long serialVersionUID = -8570011928488178725L;

	private TotalDayPayReward totalDayPayReward = new TotalDayPayReward();
	private String isCommit = "F";

	private String[] dayArr;
	private String[] dropToolArr;
	private String[] descriptionArr;
	private String activityId;

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String execute() {
		if (dayArr == null) {
			isCommit = "F";
			return SUCCESS;
		}

		TotalDayPayRewardService service = ServiceCacheFactory
				.getServiceCache().getService(TotalDayPayRewardService.class);
		
		service.delTotalDayPayReward(totalDayPayReward.getActivityId());
		
		for (int i = 0; i < dayArr.length; i++) {
			boolean mark = false;
			if (dayArr[i] != null && !dayArr[i].equals("")) {
				try {
					int day = Integer.parseInt(dayArr[i]);
					totalDayPayReward.setDay(day);
					mark = true;
				} catch (NumberFormatException e) {
					mark = false;
				}
			}
			
			if (mark && dropToolArr[i] != null && !dropToolArr[i].equals("")) {
				totalDayPayReward.setDropTool(dropToolArr[i]);
			} else {
				mark = false;
			}
			
			if (mark && descriptionArr[i] != null && !descriptionArr[i].equals("")) {
				totalDayPayReward.setToolDesc(descriptionArr[i]);
			} else {
				mark = false;
			}
			
			if (mark) {
				service.addTotalDayPayReward(totalDayPayReward);
			}			
		}

		return SUCCESS;
	}

	public TotalDayPayReward getTotalDayPayReward() {
		return totalDayPayReward;
	}

	public void setTotalDayPayReward(TotalDayPayReward totalDayPayReward) {
		this.totalDayPayReward = totalDayPayReward;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String[] getDayArr() {
		return dayArr;
	}

	public void setDayArr(String[] dayArr) {
		this.dayArr = dayArr;
	}

	public String[] getDropToolArr() {
		return dropToolArr;
	}

	public void setDropToolArr(String[] dropToolArr) {
		this.dropToolArr = dropToolArr;
	}

	public String[] getDescriptionArr() {
		return descriptionArr;
	}

	public void setDescriptionArr(String[] descriptionArr) {
		this.descriptionArr = descriptionArr;
	}

	@Override
	public TotalDayPayReward getModel() {
		// TODO Auto-generated method stub
		return totalDayPayReward;
	}
}
