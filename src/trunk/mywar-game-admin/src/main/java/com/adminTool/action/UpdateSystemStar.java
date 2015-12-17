package com.adminTool.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.bo.SystemStar;
import com.adminTool.service.AdminChangeConstantLogService;
import com.adminTool.service.SystemStarService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateSystemStar extends ALDAdminActionSupport {

	private static final long serialVersionUID = -1257878163178293885L;
	
	private Integer[] starId;
	private Integer[] starType;
	private Integer[] starImg;
	private Integer[] needLevel;
	private String[] rewards;
	
	public String execute() {

		if (starId == null) {
			return SUCCESS;
		}
		for (Integer i : starId) {
			SystemStar star = new SystemStar();
			star.setStarId(i);
			if (starType != null && starType.length != 0) {
				star.setStarType(starType[0]);
			}
			if (starImg != null && starImg.length != 0) {
				star.setImgId(starImg[0]);
			}
			if (needLevel != null && needLevel.length != 0) {
				star.setNeedLevel(needLevel[0]);
			}
			if (rewards != null && rewards.length != 0) {
				star.setRewards(rewards[0]);
			}
			// update
			SystemStarService systemStarService = ServiceCacheFactory.getServiceCache().getService(SystemStarService.class);
			systemStarService.saveSystemStar(star);
			
			// 记录日志
			AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
			adminChangeConstantLog.setSysNum(Integer.valueOf(super.getAdminUser().getExp1()));
			adminChangeConstantLog.setAdminName(super.getAdminUser().getAdminName());
			adminChangeConstantLog.setChangeTime(new Timestamp(System.currentTimeMillis()));
			adminChangeConstantLog.setConstantName("SystemStar");
			adminChangeConstantLog.setChangeType(3); 
			adminChangeConstantLog.setChangeDetail("修改 " + super.getText("主键:" + star.getStarId() + " 类型:" + star.getStarType() + " 图id:" + star.getImgId() + " 需要达到等级:" + star.getNeedLevel() + " 奖励:" + star.getRewards() + " 记录<br/>"));
			AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory.getServiceCache().getService(AdminChangeConstantLogService.class);
			adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		}
		return SUCCESS;
	}
	public Integer[] getStarId() {
		return starId;
	}
	public void setStarId(Integer[] starId) {
		this.starId = starId;
	}
	public Integer[] getStarType() {
		return starType;
	}
	public void setStarType(Integer[] starType) {
		this.starType = starType;
	}
	public Integer[] getStarImg() {
		return starImg;
	}
	public void setStarImg(Integer[] starImg) {
		this.starImg = starImg;
	}
	public Integer[] getNeedLevel() {
		return needLevel;
	}
	public void setNeedLevel(Integer[] needLevel) {
		this.needLevel = needLevel;
	}
	public String[] getRewards() {
		return rewards;
	}
	public void setRewards(String[] rewards) {
		this.rewards = rewards;
	}
}
