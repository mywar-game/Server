package com.fantingame.game.mywar.logic.scene.bean;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 用户场景信息
 * 
 * @author Administrator
 *
 */
public class UserSceneData {
	// 用户id
	private String userId;
	// 用户名称
	private String userName;
	// 英雄模型id
	private Integer heroId;
	// 所在场景
	private Scene scene;
	// 所在分线
	private Line line;
	// 位置
	private Position position;
	// 用户设置最多同屏显示几人
	private int maxScreenCount;
	// 同屏显示的用户idMap
	private Map<String, String> screenUserIdMap;
	// 状态 true 已经进入 并可以收发消息了 false还没有进入完毕
	public volatile boolean isEntered;

	public UserSceneData(String userId, Integer heroId, Integer maxScreenCount) {
		super();
		this.userId = userId;
		this.heroId = heroId;
		position = new Position();
		screenUserIdMap = Maps.newConcurrentMap();
		this.maxScreenCount = maxScreenCount;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getHeroId() {
		return heroId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setHeroId(Integer heroId) {
		this.heroId = heroId;
	}

	public Map<String, String> getScreenUserIdMap() {
		return screenUserIdMap;
	}

	public void setScreenUserIdMap(Map<String, String> screenUserIdMap) {
		this.screenUserIdMap = screenUserIdMap;
	}

	public int getMaxScreenCount() {
		return maxScreenCount;
	}

	public void setMaxScreenCount(int maxScreenCount) {
		this.maxScreenCount = maxScreenCount;
	}

}
