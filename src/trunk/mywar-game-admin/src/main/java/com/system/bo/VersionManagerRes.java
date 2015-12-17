package com.system.bo;

/**
 * 资源包版本
 * 
 * @author yezp
 */
public class VersionManagerRes implements java.io.Serializable {

	private Integer id;
	private int resBigVersion;
	private int resSmallVersion;
	private int beUpdateBigVersion;
	private int beUpdateSmallVersion;
	private int isTest;
	private String partnerId;
	private String url;
	private String description;

	public VersionManagerRes() {
	}

	public VersionManagerRes(int resBigVersion, int resSmallVersion,
			int beUpdateBigVersion, int beUpdateSmallVersion, int isTest,
			String partnerId, String url) {
		this.resBigVersion = resBigVersion;
		this.resSmallVersion = resSmallVersion;
		this.beUpdateBigVersion = beUpdateBigVersion;
		this.beUpdateSmallVersion = beUpdateSmallVersion;
		this.isTest = isTest;
		this.partnerId = partnerId;
		this.url = url;
	}

	public VersionManagerRes(int resBigVersion, int resSmallVersion,
			int beUpdateBigVersion, int beUpdateSmallVersion, int isTest,
			String partnerId, String url, String description) {
		this.resBigVersion = resBigVersion;
		this.resSmallVersion = resSmallVersion;
		this.beUpdateBigVersion = beUpdateBigVersion;
		this.beUpdateSmallVersion = beUpdateSmallVersion;
		this.isTest = isTest;
		this.partnerId = partnerId;
		this.url = url;
		this.description = description;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getResBigVersion() {
		return this.resBigVersion;
	}

	public void setResBigVersion(int resBigVersion) {
		this.resBigVersion = resBigVersion;
	}

	public int getResSmallVersion() {
		return this.resSmallVersion;
	}

	public void setResSmallVersion(int resSmallVersion) {
		this.resSmallVersion = resSmallVersion;
	}

	public int getBeUpdateBigVersion() {
		return this.beUpdateBigVersion;
	}

	public void setBeUpdateBigVersion(int beUpdateBigVersion) {
		this.beUpdateBigVersion = beUpdateBigVersion;
	}

	public int getBeUpdateSmallVersion() {
		return this.beUpdateSmallVersion;
	}

	public void setBeUpdateSmallVersion(int beUpdateSmallVersion) {
		this.beUpdateSmallVersion = beUpdateSmallVersion;
	}

	public int getIsTest() {
		return this.isTest;
	}

	public void setIsTest(int isTest) {
		this.isTest = isTest;
	}

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
