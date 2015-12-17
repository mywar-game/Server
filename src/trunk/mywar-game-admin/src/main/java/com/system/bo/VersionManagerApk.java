package com.system.bo;

/**
 * APK版本管理
 * 
 * @author yezp
 */
public class VersionManagerApk implements java.io.Serializable {

	private Integer id;
	private int apkBigVersion;
	private int apkSmallVersion;
	private int isTest;
	private String partnerId;
	private String url;
	private String qn;
	private String qnName;
	private String description;

	public VersionManagerApk() {
	}

	public VersionManagerApk(int apkBigVersion, int apkSmallVersion,
			int isTest, String partnerId, String url) {
		this.apkBigVersion = apkBigVersion;
		this.apkSmallVersion = apkSmallVersion;
		this.isTest = isTest;
		this.partnerId = partnerId;
		this.url = url;
	}

	public VersionManagerApk(int apkBigVersion, int apkSmallVersion,
			int isTest, String partnerId, String url, String description) {
		this.apkBigVersion = apkBigVersion;
		this.apkSmallVersion = apkSmallVersion;
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

	public int getApkBigVersion() {
		return this.apkBigVersion;
	}

	public void setApkBigVersion(int apkBigVersion) {
		this.apkBigVersion = apkBigVersion;
	}

	public int getApkSmallVersion() {
		return this.apkSmallVersion;
	}

	public void setApkSmallVersion(int apkSmallVersion) {
		this.apkSmallVersion = apkSmallVersion;
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

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public String getQnName() {
		return qnName;
	}

	public void setQnName(String qnName) {
		this.qnName = qnName;
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
