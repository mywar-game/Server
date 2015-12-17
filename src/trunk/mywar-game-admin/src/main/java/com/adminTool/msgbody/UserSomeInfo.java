package com.adminTool.msgbody;

import java.io.Serializable;

/**
 * 用户信息
 * 
 * @author yezp
 */
public class UserSomeInfo implements Serializable {

	private static final long serialVersionUID = -8711904146612278418L;

	/** 用户编号 **/
	private String userId;
	/** 游戏id **/
	private Integer ftId;
	/** 角色名称 **/
	private String roleName;
	/** 用户等级 **/
	private Integer level;
	/** 用户经验 **/
	private Integer exp;
	/** 用户人民币 **/
	private Integer money;
	/** 用户金币 **/
	private Integer gold;
	/** 1联盟，2部落 **/
	private Integer camp;
	/** 体力值 **/
	private Integer power;
	/** 活力值 **/
	private Integer activity;
	/** 下次次体力恢复时间戳 **/
	private Long powerResumTime;
	/** 下次活力恢复时间戳 **/
	private Long activityResumTime;
	/** 声望 **/
	private Integer prestige;
	/** 声望等级 **/
	private Integer prestigeLevel;
	/** 荣誉 **/
	private Integer honour;
	/** 用户vip等级 **/
	private Integer vipLevel;
	/** vip经验 **/
	private Integer vipExp;
	/** 在线时长，单位秒 **/
	private Integer allOnLineTime;
	/** 战斗力 **/
	private Integer effective;
	/** 上次登出所处位置格式为(sceneId,x,y),如果为空则说明该玩家刚刚创角，还未进入过游戏 **/
	private String prePosition;
	/** 用户注册时间戳 **/
	private Long regTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getFtId() {
		return ftId;
	}

	public void setFtId(Integer ftId) {
		this.ftId = ftId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getCamp() {
		return camp;
	}

	public void setCamp(Integer camp) {
		this.camp = camp;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	public Long getPowerResumTime() {
		return powerResumTime;
	}

	public void setPowerResumTime(Long powerResumTime) {
		this.powerResumTime = powerResumTime;
	}

	public Long getActivityResumTime() {
		return activityResumTime;
	}

	public void setActivityResumTime(Long activityResumTime) {
		this.activityResumTime = activityResumTime;
	}

	public Integer getPrestige() {
		return prestige;
	}

	public void setPrestige(Integer prestige) {
		this.prestige = prestige;
	}

	public Integer getPrestigeLevel() {
		return prestigeLevel;
	}

	public void setPrestigeLevel(Integer prestigeLevel) {
		this.prestigeLevel = prestigeLevel;
	}

	public Integer getHonour() {
		return honour;
	}

	public void setHonour(Integer honour) {
		this.honour = honour;
	}

	public Integer getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(Integer vipLevel) {
		this.vipLevel = vipLevel;
	}

	public Integer getVipExp() {
		return vipExp;
	}

	public void setVipExp(Integer vipExp) {
		this.vipExp = vipExp;
	}

	public Integer getAllOnLineTime() {
		return allOnLineTime;
	}

	public void setAllOnLineTime(Integer allOnLineTime) {
		this.allOnLineTime = allOnLineTime;
	}

	public Integer getEffective() {
		return effective;
	}

	public void setEffective(Integer effective) {
		this.effective = effective;
	}

	public String getPrePosition() {
		return prePosition;
	}

	public void setPrePosition(String prePosition) {
		this.prePosition = prePosition;
	}

	public Long getRegTime() {
		return regTime;
	}

	public void setRegTime(Long regTime) {
		this.regTime = regTime;
	}

}
