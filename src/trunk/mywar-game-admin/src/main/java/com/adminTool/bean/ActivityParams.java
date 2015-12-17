package com.adminTool.bean;

import java.sql.Timestamp;

public class ActivityParams {

	 private String beiShu1 ="";
	 private Timestamp startTime1 ;
	 private Timestamp endTime1 ;
	 
	 private String beiShu2 ="";
	 private Timestamp startTime2 ;
	 private Timestamp endTime2 ;
	 
	 public ActivityParams(){}

	 
	public ActivityParams(String beiShu1, Timestamp startTime1,
			Timestamp endTime1, String beiShu2, Timestamp startTime2,
			Timestamp endTime2) {
		super();
		this.beiShu1 = beiShu1;
		this.startTime1 = startTime1;
		this.endTime1 = endTime1;
		this.beiShu2 = beiShu2;
		this.startTime2 = startTime2;
		this.endTime2 = endTime2;
	}


	public String getBeiShu1() {
		return beiShu1;
	}

	public void setBeiShu1(String beiShu1) {
		this.beiShu1 = beiShu1;
	}

	public Timestamp getStartTime1() {
		return startTime1;
	}

	public void setStartTime1(Timestamp startTime1) {
		this.startTime1 = startTime1;
	}

	public Timestamp getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(Timestamp endTime1) {
		this.endTime1 = endTime1;
	}

	public String getBeiShu2() {
		return beiShu2;
	}

	public void setBeiShu2(String beiShu2) {
		this.beiShu2 = beiShu2;
	}

	public Timestamp getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(Timestamp startTime2) {
		this.startTime2 = startTime2;
	}

	public Timestamp getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(Timestamp endTime2) {
		this.endTime2 = endTime2;
	}
	 
 
}
