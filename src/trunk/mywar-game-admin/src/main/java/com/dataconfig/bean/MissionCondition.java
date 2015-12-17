package com.dataconfig.bean;

import java.io.Serializable;

public class MissionCondition implements Serializable {
	
	private static final long serialVersionUID = 234894095064300309L;
	
	private int id;
	private String conditionDesc;
	private int operatorNum; 
	private int targetType;
	private int targetNum;
	private int num;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConditionDesc() {
		return conditionDesc;
	}
	public void setConditionDesc(String conditionDesc) {
		this.conditionDesc = conditionDesc;
	}
	public int getOperatorNum() {
		return operatorNum;
	}
	public void setOperatorNum(int operatorNum) {
		this.operatorNum = operatorNum;
	}
	public int getTargetType() {
		return targetType;
	}
	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}
	public int getTargetNum() {
		return targetNum;
	}
	public void setTargetNum(int targetNum) {
		this.targetNum = targetNum;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getNum() {
		return num;
	}
}
