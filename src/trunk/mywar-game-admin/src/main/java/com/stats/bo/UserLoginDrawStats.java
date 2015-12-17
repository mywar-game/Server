package com.stats.bo;

import java.util.Date;

/**
 * 大富翁抽奖
 * @author Administrator
 *
 */
public class UserLoginDrawStats {

	private Integer id;
	private Integer sysNum;
	private Date time; 
	
	private Integer diamondUsePeopleCount;
	private Integer totalPeopleCount;
	private Integer diamondUseCount;
	private Integer totalCount;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysNum() {
		return sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getDiamondUsePeopleCount() {
		return diamondUsePeopleCount;
	}

	public void setDiamondUsePeopleCount(Integer diamondUsePeopleCount) {
		this.diamondUsePeopleCount = diamondUsePeopleCount;
	}

	public Integer getTotalPeopleCount() {
		return totalPeopleCount;
	}

	public void setTotalPeopleCount(Integer totalPeopleCount) {
		this.totalPeopleCount = totalPeopleCount;
	}

	public Integer getDiamondUseCount() {
		return diamondUseCount;
	}

	public void setDiamondUseCount(Integer diamondUseCount) {
		this.diamondUseCount = diamondUseCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public UserLoginDrawStats() {}
}
