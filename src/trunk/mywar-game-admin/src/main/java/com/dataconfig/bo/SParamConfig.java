package com.dataconfig.bo;

/**
 * SParamConfig entity. @author MyEclipse Persistence Tools
 */

public class SParamConfig implements java.io.Serializable {

	// Fields

	private String configkey;
	private String value1;
	private String value2;
	private String value3;
	private String description;

	// Constructors

	/** default constructor */
	public SParamConfig() {
	}

	/** minimal constructor */
	public SParamConfig(String value1) {
		this.value1 = value1;
	}

	/** full constructor */
	public SParamConfig(String value1, String value2, String value3,
			String description) {
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.description = description;
	}

	// Property accessors

	public String getConfigkey() {
		return this.configkey;
	}

	public void setConfigkey(String configkey) {
		this.configkey = configkey;
	}

	public String getValue1() {
		return this.value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return this.value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return this.value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}