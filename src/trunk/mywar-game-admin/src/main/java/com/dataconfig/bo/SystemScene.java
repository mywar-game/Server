package com.dataconfig.bo;

/**
 * SystemScene entity. @author MyEclipse Persistence Tools
 */

public class SystemScene implements java.io.Serializable {

	// Fields

	private Integer sceneId;
	private String sceneName;
	private Short openLevel;
	private String levelInfo;
	private String heroInfo;
	private Integer imgId;
	private Integer sceneType;

	// Constructors

	/** default constructor */
	public SystemScene() {
	}

	/** minimal constructor */
	public SystemScene(String sceneName, Short openLevel, String levelInfo,
			String heroInfo) {
		this.sceneName = sceneName;
		this.openLevel = openLevel;
		this.levelInfo = levelInfo;
		this.heroInfo = heroInfo;
	}

	/** full constructor */
	public SystemScene(String sceneName, Short openLevel, String levelInfo,
			String heroInfo, Integer imgId, Integer sceneType) {
		this.sceneName = sceneName;
		this.openLevel = openLevel;
		this.levelInfo = levelInfo;
		this.heroInfo = heroInfo;
		this.imgId = imgId;
		this.sceneType = sceneType;
	}

	// Property accessors

	public Integer getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public String getSceneName() {
		return this.sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public Short getOpenLevel() {
		return this.openLevel;
	}

	public void setOpenLevel(Short openLevel) {
		this.openLevel = openLevel;
	}

	public String getLevelInfo() {
		return this.levelInfo;
	}

	public void setLevelInfo(String levelInfo) {
		this.levelInfo = levelInfo;
	}

	public String getHeroInfo() {
		return this.heroInfo;
	}

	public void setHeroInfo(String heroInfo) {
		this.heroInfo = heroInfo;
	}

	public Integer getImgId() {
		return this.imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getSceneType() {
		return this.sceneType;
	}

	public void setSceneType(Integer sceneType) {
		this.sceneType = sceneType;
	}

}