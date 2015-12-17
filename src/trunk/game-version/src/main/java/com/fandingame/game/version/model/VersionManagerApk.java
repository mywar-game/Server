package com.fandingame.game.version.model;

public class VersionManagerApk {
	  private int id;
	  private int apkBigVersion;
	  private int apkSmallVersion;
	  private int isTest;
	  private String partnerId;
	  private String url;
	  private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getApkBigVersion() {
		return apkBigVersion;
	}
	public void setApkBigVersion(int apkBigVersion) {
		this.apkBigVersion = apkBigVersion;
	}
	public int getApkSmallVersion() {
		return apkSmallVersion;
	}
	public void setApkSmallVersion(int apkSmallVersion) {
		this.apkSmallVersion = apkSmallVersion;
	}
	public int getIsTest() {
		return isTest;
	}
	public void setIsTest(int isTest) {
		this.isTest = isTest;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String creatVersionString(){
		return apkBigVersion+"."+apkSmallVersion;
	}
}
