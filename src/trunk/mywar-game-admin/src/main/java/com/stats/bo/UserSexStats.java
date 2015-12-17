package com.stats.bo;

/**
 * UserSexStats entity. @author MyEclipse Persistence Tools
 */

public class UserSexStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Integer sex;
	private Integer num;

	// Constructors

	/** default constructor */
	public UserSexStats() {
	}

	/** full constructor */
	public UserSexStats(Integer sysNum, Integer sex, Integer num) {
		this.sysNum = sysNum;
		this.sex = sex;
		this.num = num;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}