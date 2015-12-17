package com.example.bo;

import java.util.Date;

/**
 * Test entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Test implements java.io.Serializable {
	// Fields

	private Integer id;
	private String p2;
	private String p1;
	private Date creattime;
	private String name;

	// Constructors

	/** default constructor */
	public Test() {
	}

	/** full constructor */
	public Test(String p2, String p1, Date creattime, String name) {
		this.p2 = p2;
		this.p1 = p1;
		this.creattime = creattime;
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getP2() {
		return this.p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP1() {
		return this.p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public Date getCreattime() {
		return this.creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}