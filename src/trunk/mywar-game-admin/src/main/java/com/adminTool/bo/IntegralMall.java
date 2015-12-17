package com.adminTool.bo;

import java.io.Serializable;

public class IntegralMall implements Serializable {
	private Integer mall_id;
	private Integer type;
	private Integer tool_type;
	private Integer tool_id;
	private Integer need_integrate;
	private Integer num;
	private Double discount;
	
	
	private String toolName;
	private String toolTypeName;
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	
	
	public String getToolTypeName() {
		return toolTypeName;
	}
	public void setToolTypeName(String toolTypeName) {
		this.toolTypeName = toolTypeName;
	}
	public IntegralMall() {
	}
	public IntegralMall(Integer mall_id, Integer type, Integer tool_type,
			Integer tool_id, Integer need_integrate, Integer num,
			Double discount) {
		this.mall_id = mall_id;
		this.type = type;
		this.tool_type = tool_type;
		this.tool_id = tool_id;
		this.need_integrate = need_integrate;
		this.num = num;
		this.discount = discount;
	}
	public Integer getMall_id() {
		return mall_id;
	}
	public void setMall_id(Integer mall_id) {
		this.mall_id = mall_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getTool_type() {
		return tool_type;
	}
	public void setTool_type(Integer tool_type) {
		this.tool_type = tool_type;
	}
	public Integer getTool_id() {
		return tool_id;
	}
	public void setTool_id(Integer tool_id) {
		this.tool_id = tool_id;
	}
	public Integer getNeed_integrate() {
		return need_integrate;
	}
	public void setNeed_integrate(Integer need_integrate) {
		this.need_integrate = need_integrate;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	
	
}
