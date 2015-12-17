package com.fandingame.game.version.model;

import java.io.Serializable;
import java.util.Date;


public class PackageSettings implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String expirePackageMessage;
	private Date updatedTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpirePackageMessage() {
		return expirePackageMessage;
	}

	public void setExpirePackageMessage(String expirePackageMessage) {
		this.expirePackageMessage = expirePackageMessage;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}
