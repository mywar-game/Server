package com.dataconfig.bo;

/**
 * TEquipmentSynthesisConstant entity. @author MyEclipse Persistence Tools
 */

public class TEquipmentSynthesisConstant implements java.io.Serializable {

	// Fields

	private Integer equipmentId;
	private String buildMeterials1;
	private String buildMeterials2;
	private String buildMeterials3;
	private String equipmentName;

	// Constructors

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	/** default constructor */
	public TEquipmentSynthesisConstant() {
	}

	/** full constructor */
	public TEquipmentSynthesisConstant(String buildMeterials1,
			String buildMeterials2, String buildMeterials3, String equipmentName) {
		this.buildMeterials1 = buildMeterials1;
		this.buildMeterials2 = buildMeterials2;
		this.buildMeterials3 = buildMeterials3;
		this.equipmentName = equipmentName;
	}

	public TEquipmentSynthesisConstant(Integer equipmentId,
			String equipmentName, String buildMeterials1,
			String buildMeterials2, String buildMeterials3) {
		this.buildMeterials1 = buildMeterials1;
		this.buildMeterials2 = buildMeterials2;
		this.buildMeterials3 = buildMeterials3;
		this.equipmentName = equipmentName;
		this.equipmentId = equipmentId;
	}

	// Property accessors

	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getBuildMeterials1() {
		return this.buildMeterials1;
	}

	public void setBuildMeterials1(String buildMeterials1) {
		this.buildMeterials1 = buildMeterials1;
	}

	public String getBuildMeterials2() {
		return this.buildMeterials2;
	}

	public void setBuildMeterials2(String buildMeterials2) {
		this.buildMeterials2 = buildMeterials2;
	}

	public String getBuildMeterials3() {
		return this.buildMeterials3;
	}

	public void setBuildMeterials3(String buildMeterials3) {
		this.buildMeterials3 = buildMeterials3;
	}

}