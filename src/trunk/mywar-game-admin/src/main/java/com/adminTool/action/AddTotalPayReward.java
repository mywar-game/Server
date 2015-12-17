package com.adminTool.action;

import java.util.List;
import java.util.Map;

import com.adminTool.bo.TotalPayReward;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.TotalPayRewardService;
import com.dataconfig.bo.SystemHero;
import com.dataconfig.bo.SystemTool;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.SystemArtifactService;
import com.dataconfig.service.SystemHeroService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加累积充值配置
 * 
 * @author yezp
 */
public class AddTotalPayReward extends ALDAdminActionSupport implements
		ModelDriven<TotalPayReward> {

	private static final long serialVersionUID = -8694309461420870011L;

	private TotalPayReward totalPayReward = new TotalPayReward();

	private List<SystemTool> toolList;
	private Map<Integer, String> typeNameMap;
	private Map<Integer, String> artifactIdNameMap;
	private Map<Integer, String> equipmentMap;
	private List<SystemHero> heroList;

	private String[] idArr;
	private String[] payMoneyArr;
	private String[] rewardsArr;
	private String[] timesLimitArr;
	private String[] descriptionArr;

	private String isCommit = "F";
	private String activityId;

	public String execute() {
		TotalPayRewardService totalPayRewardService = ServiceCacheFactory
				.getServiceCache().getService(TotalPayRewardService.class);
		TTreasureConstantService treasureConstantService = ServiceCacheFactory
				.getServiceCache().getService(TTreasureConstantService.class);
		SystemArtifactService artifactService = ServiceCacheFactory
				.getServiceCache().getService(SystemArtifactService.class);
		EEquipmentConstantService equipmentService = ServiceCacheFactory
				.getServiceCache().getService(EEquipmentConstantService.class);
		SystemHeroService heroService = ServiceCacheFactory.getServiceCache()
				.getService(SystemHeroService.class);

		if (isCommit.equals("F")) {
			toolList = treasureConstantService.getToolList();
			ToolTypeConstant toolType = new ToolTypeConstant();
			typeNameMap = toolType.getToolTypeNameMap();
			artifactIdNameMap = artifactService.findArtifactIDNameMap();
			equipmentMap = equipmentService.findEquipmentIDNameMap();
			heroList = heroService.getHeroList();

			return INPUT;
		}

		if (payMoneyArr == null) {
			isCommit = "F";
			return SUCCESS;
		}

		// 将原有配置删除，再添加新的奖励配置
		totalPayRewardService.delTotalPayRewardByActivityId(totalPayReward
				.getActivityId());

		for (int i = 0; i < payMoneyArr.length; i++) {
			boolean mark = false;
			if (payMoneyArr[i] != null && !payMoneyArr[i].equals("")) {
				try {
					int payMoney = Integer.parseInt(payMoneyArr[i]);
					totalPayReward.setPayMoney(payMoney);
					mark = true;
				} catch (NumberFormatException e) {
					mark = false;
				}
			}

			if (mark && rewardsArr[i] != null && !rewardsArr[i].equals("")) {
				totalPayReward.setRewards(rewardsArr[i]);
			} else {
				mark = false;
			}

			if (mark && timesLimitArr[i] != null
					&& !timesLimitArr[i].equals("")) {
				try {
					int timesLimit = Integer.parseInt(timesLimitArr[i]);
					totalPayReward.setTimesLimit(timesLimit);
				} catch (NumberFormatException e) {
					mark = false;
				}
			} else {
				mark = false;
			}

			if (mark && descriptionArr[i] != null
					&& !descriptionArr[i].equals("")) {
				totalPayReward.setDescription(descriptionArr[i]);
			} else {
				mark = false;
			}
			
//			if (mark && i < idArr.length && idArr[i] != null
//					&& !idArr[i].equals("")) {
//				totalPayReward.setId(Integer.parseInt(idArr[i]));
//			} else {
//				mark = false;
//			}

			if (mark) {
				totalPayRewardService.addTotalPayReward(totalPayReward);
			}
		}

		return SUCCESS;
	}

	public TotalPayReward getTotalPayReward() {
		return totalPayReward;
	}

	public void setTotalPayReward(TotalPayReward totalPayReward) {
		this.totalPayReward = totalPayReward;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public List<SystemTool> getToolList() {
		return toolList;
	}

	public void setToolList(List<SystemTool> toolList) {
		this.toolList = toolList;
	}

	public String[] getRewardsArr() {
		return rewardsArr;
	}

	public void setRewardsArr(String[] rewardsArr) {
		this.rewardsArr = rewardsArr;
	}

	public Map<Integer, String> getTypeNameMap() {
		return typeNameMap;
	}

	public void setTypeNameMap(Map<Integer, String> typeNameMap) {
		this.typeNameMap = typeNameMap;
	}

	public Map<Integer, String> getArtifactIdNameMap() {
		return artifactIdNameMap;
	}

	public void setArtifactIdNameMap(Map<Integer, String> artifactIdNameMap) {
		this.artifactIdNameMap = artifactIdNameMap;
	}

	public Map<Integer, String> getEquipmentMap() {
		return equipmentMap;
	}

	public void setEquipmentMap(Map<Integer, String> equipmentMap) {
		this.equipmentMap = equipmentMap;
	}

	public List<SystemHero> getHeroList() {
		return heroList;
	}

	public void setHeroList(List<SystemHero> heroList) {
		this.heroList = heroList;
	}

	public String[] getIdArr() {
		return idArr;
	}

	public void setIdArr(String[] idArr) {
		this.idArr = idArr;
	}

	public String[] getPayMoneyArr() {
		return payMoneyArr;
	}

	public void setPayMoneyArr(String[] payMoneyArr) {
		this.payMoneyArr = payMoneyArr;
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

	@Override
	public TotalPayReward getModel() {
		// TODO Auto-generated method stub
		return totalPayReward;
	}

}
