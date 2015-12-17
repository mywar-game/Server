package com.fandingame.game.version.model;
/**
 * 资源版本管理bean
 * @author Administrator
 *
 */
public class VersionManagerRes {
  private int id;
  private int resBigVersion;
  private int resSmallVersion;
  private int beUpdateBigVersion;
  private int beUpdateSmallVersion;
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
public int getResBigVersion() {
	return resBigVersion;
}
public void setResBigVersion(int resBigVersion) {
	this.resBigVersion = resBigVersion;
}
public int getResSmallVersion() {
	return resSmallVersion;
}
public void setResSmallVersion(int resSmallVersion) {
	this.resSmallVersion = resSmallVersion;
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
public int getBeUpdateBigVersion() {
	return beUpdateBigVersion;
}
public void setBeUpdateBigVersion(int beUpdateBigVersion) {
	this.beUpdateBigVersion = beUpdateBigVersion;
}
public int getBeUpdateSmallVersion() {
	return beUpdateSmallVersion;
}
public void setBeUpdateSmallVersion(int beUpdateSmallVersion) {
	this.beUpdateSmallVersion = beUpdateSmallVersion;
}

public String createVersionString(){
	return resBigVersion+"."+resSmallVersion;
}
}
