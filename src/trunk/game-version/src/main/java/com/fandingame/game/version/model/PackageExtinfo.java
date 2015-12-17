package com.fandingame.game.version.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 升级包扩展信息
 * @author dogdog
 *
 */
public class PackageExtinfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 版本是否作废，0否，1是
	 */
	private int isExpire;
	
	private Date updatedTime;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public int getIsExpire() {
		return isExpire;
	}
	public void setIsExpire(int isExpire) {
		this.isExpire = isExpire;
	}
}
