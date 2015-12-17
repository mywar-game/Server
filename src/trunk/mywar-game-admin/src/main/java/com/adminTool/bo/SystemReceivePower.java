package com.adminTool.bo;

// 体力恢复
public class SystemReceivePower implements java.io.Serializable {

	private Integer id;
	private Integer type;
	private String remark;
	private String startTime;
	private String endTime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public SystemReceivePower() {
		
	}
	
	public boolean equals(Object object) {
	    return true;
	}

	public int hashCode() {
	   return 1;
	}
		
}
