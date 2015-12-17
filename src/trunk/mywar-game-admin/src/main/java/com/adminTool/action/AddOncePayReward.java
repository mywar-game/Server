package com.adminTool.action;

import com.adminTool.bo.OncePayReward;
import com.adminTool.service.OncePayRewardService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加单笔充值配置
 * 
 * @author yezp
 */
public class AddOncePayReward extends ALDAdminActionSupport implements
		ModelDriven<OncePayReward> {

	private static final long serialVersionUID = 7811821531453780315L;

	private OncePayReward oncePayReward = new OncePayReward();
	private String isCommit = "F";
	private String activityId;

	private String[] payMoneyArr;
	private String[] nextPayMoneyArr;
	private String[] rewardsArr;
	private String[] timesLimitArr;
	private String[] descriptionArr;

	public String execute() {
		if (payMoneyArr == null) {
			isCommit = "F";
			return SUCCESS;
		}
		
		OncePayRewardService service = ServiceCacheFactory.getServiceCache()
				.getService(OncePayRewardService.class);
		service.delOncePayRewardByActivityId(oncePayReward.getActivityId());
		
		for (int i = 0; i < payMoneyArr.length; i++) {
			boolean mark = false;
			if (payMoneyArr[i] != null && !payMoneyArr[i].equals("")) {
				try {
					int payMoney = Integer.parseInt(payMoneyArr[i]);
					oncePayReward.setPayMoney(payMoney);
					mark = true;
				} catch (NumberFormatException e) {
					mark = false;
				}
			}
			
			if (mark && nextPayMoneyArr[i] != null && !nextPayMoneyArr[i].equals("")) {
				try {
					int money = Integer.parseInt(nextPayMoneyArr[i]);
					oncePayReward.setNextPayMoney(money);
				} catch (NumberFormatException e) {
					mark = false;
				}				
			} else {
				mark = false;
			}
			
			if (mark && rewardsArr[i] != null && !rewardsArr[i].equals("")) {
				oncePayReward.setRewards(rewardsArr[i]);
			} else {
				mark = false;
			}
			
			if (mark && timesLimitArr[i] != null && !timesLimitArr[i].equals("")) {
				try {
					int timesLimit = Integer.parseInt(timesLimitArr[i]);
					oncePayReward.setTimesLimit(timesLimit);
				} catch (NumberFormatException e) {
					mark = false;
				}				
			} else {
				mark = false;
			}
			
			if (mark && descriptionArr[i] != null && !descriptionArr[i].equals("")) {
				oncePayReward.setDescription(descriptionArr[i]);
			} else {
				mark = false;
			}
			
			if (mark) {
				service.addOncePayReward(oncePayReward);
			}
		}
		
		return SUCCESS;
	}

	@Override
	public OncePayReward getModel() {
		// TODO Auto-generated method stub
		return oncePayReward;
	}

	public OncePayReward getOncePayReward() {
		return oncePayReward;
	}

	public void setOncePayReward(OncePayReward oncePayReward) {
		this.oncePayReward = oncePayReward;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String[] getPayMoneyArr() {
		return payMoneyArr;
	}

	public void setPayMoneyArr(String[] payMoneyArr) {
		this.payMoneyArr = payMoneyArr;
	}

	public String[] getNextPayMoneyArr() {
		return nextPayMoneyArr;
	}

	public void setNextPayMoneyArr(String[] nextPayMoneyArr) {
		this.nextPayMoneyArr = nextPayMoneyArr;
	}

	public String[] getRewardsArr() {
		return rewardsArr;
	}

	public void setRewardsArr(String[] rewardsArr) {
		this.rewardsArr = rewardsArr;
	}

	public String[] getTimesLimitArr() {
		return timesLimitArr;
	}

	public void setTimesLimitArr(String[] timesLimitArr) {
		this.timesLimitArr = timesLimitArr;
	}

	public String[] getDescriptionArr() {
		return descriptionArr;
	}

	public void setDescriptionArr(String[] descriptionArr) {
		this.descriptionArr = descriptionArr;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
