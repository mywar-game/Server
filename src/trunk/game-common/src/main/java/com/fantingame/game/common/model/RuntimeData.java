package com.fantingame.game.common.model;

import java.io.Serializable;
import java.util.Date;

public class RuntimeData implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * key
	 */
	private String valueKey;

	/**
	 * value
	 */
	private int value;

	/**
	 * 创建时间
	 */
	private Date createdTime;

	public String getValueKey() {
		return valueKey;
	}

	public void setValueKey(String valueKey) {
		this.valueKey = valueKey;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
