package com.framework.config;

public class LocalKeyConfig {
  //游戏服务器和登录服务器之间的md5校验key
  private String md5Str;
 //WebService md5key
  private String webServiceKey;
public String getWebServiceKey() {
	return webServiceKey;
}

public void setWebServiceKey(String webServiceKey) {
	this.webServiceKey = webServiceKey;
}

public String getMd5Str() {
	return md5Str;
}

public void setMd5Str(String md5Str) {
	this.md5Str = md5Str;
}
}
