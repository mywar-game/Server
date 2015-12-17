package com.adminTool.bo;

/**
 * 
 * @author yezp
 */
public class PartnerQn implements java.io.Serializable {

	private Integer id;
	private String partnerId;
	private String name;
	private String qn;

	public PartnerQn() {
	}

	public PartnerQn(String partnerId, String name, String qn) {
		this.partnerId = partnerId;
		this.name = name;
		this.qn = qn;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQn() {
		return this.qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

}
