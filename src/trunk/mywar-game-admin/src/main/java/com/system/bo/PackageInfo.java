package com.system.bo;

import java.sql.Timestamp;

/**
 * 游戏包信息
 * 
 * @author yezp
 */
public class PackageInfo implements java.io.Serializable {

	private Integer id;
	private String version;
	private String versions;
	private String fullUrl;
	private String upgradeUrl;
	private Timestamp createdTime;
	private Integer pkgType;
	private String description;
	private Integer isTest;
	private String partnerId;

	public PackageInfo() {
	}

	public PackageInfo(Integer pkgType, Integer isTest, String partnerId) {
		this.pkgType = pkgType;
		this.isTest = isTest;
		this.partnerId = partnerId;
	}

	public PackageInfo(String version, String versions, String fullUrl,
			String upgradeUrl, Timestamp createdTime, Integer pkgType,
			String description, Integer isTest, String partnerId) {
		this.version = version;
		this.versions = versions;
		this.fullUrl = fullUrl;
		this.upgradeUrl = upgradeUrl;
		this.createdTime = createdTime;
		this.pkgType = pkgType;
		this.description = description;
		this.isTest = isTest;
		this.partnerId = partnerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getUpgradeUrl() {
		return upgradeUrl;
	}

	public void setUpgradeUrl(String upgradeUrl) {
		this.upgradeUrl = upgradeUrl;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getPkgType() {
		return pkgType;
	}

	public void setPkgType(Integer pkgType) {
		this.pkgType = pkgType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsTest() {
		return isTest;
	}

	public void setIsTest(Integer isTest) {
		this.isTest = isTest;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

}
