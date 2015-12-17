package com.stats.bo;

import java.util.Date;

/**
 * UserVipStats entity. @author MyEclipse Persistence Tools
 */

public class UserVipStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String partnerId;
	private String vipInfo;
	private Date time;
	
	/**以下是手动添加**/
	private Integer v0;
	private Integer v1;
	private Integer v2;
	private Integer v3;
	private Integer v4;
	private Integer v5;
	private Integer v6;
	private Integer v7;
	private Integer v8;
	private Integer v9;
	private Integer v10;
	// Constructors

	/** default constructor */
	public UserVipStats() {
	}

	/** full constructor */
	public UserVipStats(Integer sysNum, String partnerId, String vipInfo,
			Date time) {
		this.sysNum = sysNum;
		this.partnerId = partnerId;
		this.vipInfo = vipInfo;
		this.time = time;
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

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getVipInfo() {
		return this.vipInfo;
	}

	public void setVipInfo(String vipInfo) {
		this.vipInfo = vipInfo;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getV0() {
		return v0;
	}

	public void setV0(Integer v0) {
		this.v0 = v0;
	}

	public Integer getV1() {
		return v1;
	}

	public void setV1(Integer v1) {
		this.v1 = v1;
	}

	public Integer getV2() {
		return v2;
	}

	public void setV2(Integer v2) {
		this.v2 = v2;
	}

	public Integer getV3() {
		return v3;
	}

	public void setV3(Integer v3) {
		this.v3 = v3;
	}

	public Integer getV4() {
		return v4;
	}

	public void setV4(Integer v4) {
		this.v4 = v4;
	}

	public Integer getV5() {
		return v5;
	}

	public void setV5(Integer v5) {
		this.v5 = v5;
	}

	public Integer getV6() {
		return v6;
	}

	public void setV6(Integer v6) {
		this.v6 = v6;
	}

	public Integer getV7() {
		return v7;
	}

	public void setV7(Integer v7) {
		this.v7 = v7;
	}

	public Integer getV8() {
		return v8;
	}

	public void setV8(Integer v8) {
		this.v8 = v8;
	}

	public Integer getV9() {
		return v9;
	}

	public void setV9(Integer v9) {
		this.v9 = v9;
	}

	public Integer getV10() {
		return v10;
	}

	public void setV10(Integer v10) {
		this.v10 = v10;
	}

}