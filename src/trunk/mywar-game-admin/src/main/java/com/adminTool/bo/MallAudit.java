package com.adminTool.bo;

import java.io.Serializable;
import java.sql.Timestamp;


public class MallAudit implements Serializable{

	private static final long serialVersionUID = -8730607765451005681L;
	
	private Integer id;
	private Timestamp time;
	private String status;
	private String description;
	
	public MallAudit() {
		
	}
	
	
	public MallAudit(Integer id, Timestamp time, String status,  String description) {
		super();
		this.id = id;
		this.time = time;
		this.status = status;
		this.description = description;
	}

	

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
