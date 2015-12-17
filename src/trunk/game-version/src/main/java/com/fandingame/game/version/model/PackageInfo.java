package com.fandingame.game.version.model;

import java.io.Serializable;

public class PackageInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 版本
	 */
	private String version;
	
	/**
	 * 兼容版本号，逗号分隔
	 */
	private String versions;

	/**
	 * 完整升级包地址
	 */
	private String fullUrl;
	
	/**
	 * 增量升级包地址
	 */
	private String upgradeUrl;
	
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 是否测试版本，0否，1是
	 */
	private int isTest;
	
	/**
	 * 合作商ID
	 */
	private String partnerId;
	
	public int getIsTest() {
		return isTest;
	}

	public void setIsTest(int isTest) {
		this.isTest = isTest;
	}

	/**
	 * 0表示apk包，1表示资源包
	 */
	private int pkgType;
	
	public int getPkgType() {
		return pkgType;
	}

	public void setPkgType(int pkgType) {
		this.pkgType = pkgType;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
}
