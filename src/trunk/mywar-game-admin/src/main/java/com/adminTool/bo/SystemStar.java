package com.adminTool.bo;

/**
 * 星图
 * @author Administrator
 *
 */
public class SystemStar {

	private Integer starId;
	private Integer starType;
	private Integer imgId;
	private Integer needLevel;
	private String rewards;
	
	private String showRewards;
	
	public SystemStar() {
	}

	public SystemStar(Integer starId, Integer starType, Integer imgId,
			Integer needLevel, String rewards) {
		super();
		this.starId = starId;
		this.starType = starType;
		this.imgId = imgId;
		this.needLevel = needLevel;
		this.rewards = rewards;
	}
	
	public Integer getStarId() {
		return starId;
	}

	public void setStarId(Integer starId) {
		this.starId = starId;
	}

	public Integer getStarType() {
		return starType;
	}

	public void setStarType(Integer starType) {
		this.starType = starType;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getNeedLevel() {
		return needLevel;
	}

	public void setNeedLevel(Integer needLevel) {
		this.needLevel = needLevel;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public String getShowRewards() {
		return showRewards;
	}

	public void setShowRewards(String showRewards) {
		this.showRewards = showRewards;
	}

}
