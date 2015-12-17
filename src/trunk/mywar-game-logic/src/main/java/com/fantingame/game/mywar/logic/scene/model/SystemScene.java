package com.fantingame.game.mywar.logic.scene.model;

public class SystemScene {
    private int sceneId;
    private int sceneType;
    private String sceneName;
    private int mapId;
    private int minLevel;
    private int initLineNum;
    private int lineMaxHeroCount;
    private int maxLineNum;
	public int getSceneId() {
		return sceneId;
	}
	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}
	public int getSceneType() {
		return sceneType;
	}
	public void setSceneType(int sceneType) {
		this.sceneType = sceneType;
	}
	public String getSceneName() {
		return sceneName;
	}
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public int getInitLineNum() {
		return initLineNum;
	}
	public void setInitLineNum(int initLineNum) {
		this.initLineNum = initLineNum;
	}
	public int getLineMaxHeroCount() {
		return lineMaxHeroCount;
	}
	public void setLineMaxHeroCount(int lineMaxHeroCount) {
		this.lineMaxHeroCount = lineMaxHeroCount;
	}
	public int getMaxLineNum() {
		return maxLineNum;
	}
	public void setMaxLineNum(int maxLineNum) {
		this.maxLineNum = maxLineNum;
	}
	public int getMinLevel() {
		return minLevel;
	}
	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}
}
