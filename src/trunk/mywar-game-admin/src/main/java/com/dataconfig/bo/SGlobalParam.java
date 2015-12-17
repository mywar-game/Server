package com.dataconfig.bo;

/**
 * SGlobalParam entity. @author MyEclipse Persistence Tools
 */

public class SGlobalParam implements java.io.Serializable {

	// Fields

	private Integer globalKey;
	private String globalValue;
	private String globalDesc;
	private String golobalTransportPat;

	// Constructors

	/** default constructor */
	public SGlobalParam() {
	}

	/** minimal constructor */
	public SGlobalParam(String globalValue) {
		this.globalValue = globalValue;
	}

	/** full constructor */
	public SGlobalParam(String globalValue, String globalDesc,
			String golobalTransportPat) {
		this.globalValue = globalValue;
		this.globalDesc = globalDesc;
		this.golobalTransportPat = golobalTransportPat;
	}

	// Property accessors

	public Integer getGlobalKey() {
		return this.globalKey;
	}

	public void setGlobalKey(Integer globalKey) {
		this.globalKey = globalKey;
	}

	public String getGlobalValue() {
		return this.globalValue;
	}

	public void setGlobalValue(String globalValue) {
		this.globalValue = globalValue;
	}

	public String getGlobalDesc() {
		return this.globalDesc;
	}

	public void setGlobalDesc(String globalDesc) {
		this.globalDesc = globalDesc;
	}

	public String getGolobalTransportPat() {
		return this.golobalTransportPat;
	}

	public void setGolobalTransportPat(String golobalTransportPat) {
		this.golobalTransportPat = golobalTransportPat;
	}

}