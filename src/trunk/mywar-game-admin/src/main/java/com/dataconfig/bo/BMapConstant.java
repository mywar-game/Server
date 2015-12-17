package com.dataconfig.bo;

/**
 * BMapConstant entity. @author MyEclipse Persistence Tools
 */

public class BMapConstant implements java.io.Serializable {

	// Fields

	private Integer mapId;
	private String mapName;
	private String mapDesc;
	private String type;

	// Constructors

	/** default constructor */
	public BMapConstant() {
	}

	/** full constructor */
	public BMapConstant(String mapName, String mapDesc, String type) {
		this.mapName = mapName;
		this.mapDesc = mapDesc;
		this.type = type;
	}

	// Property accessors

	public Integer getMapId() {
		return this.mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public String getMapName() {
		return this.mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getMapDesc() {
		return this.mapDesc;
	}

	public void setMapDesc(String mapDesc) {
		this.mapDesc = mapDesc;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}