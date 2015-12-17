package com.system.bo;

/**
 * 黑名单
 * 
 * @author yezp
 */
public class BlackInfo implements java.io.Serializable {

	private Integer id;
	private String value;
	private Integer blackType;
	private Integer valueType;

	public BlackInfo() {
	}

	public BlackInfo(String value, Integer blackType, Integer valueType) {
		this.value = value;
		this.blackType = blackType;
		this.valueType = valueType;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getBlackType() {
		return blackType;
	}

	public void setBlackType(Integer blackType) {
		this.blackType = blackType;
	}

	public Integer getValueType() {
		return valueType;
	}

	public void setValueType(Integer valueType) {
		this.valueType = valueType;
	}

}
