package com.fantingame.game.mywar.logic.activity.model;

import java.util.Date;

public class SystemActivity {
	/**
	 * 活动ID
	 */
	private int activityId;
	/**
	 * 显示类型
	 */
	private int activityShowType;
	/**
	 * 活动逻辑类型
	 */
	private int activityType;

	/**
	 * 活动名字
	 */
	private String activityName;
	/**
	 * 活动标题
	 */
	private String activityTitle;
	/**
	 * 活动描述
	 */
	private String activityDesc;

	/**
	 * 开始时间
	 */
	private Date startTime;

	/**
	 * 活动结束时间
	 */
	private Date endTime;

	/**
	 * 扩展 参数
	 */
	private String param;

	/**
	 * 开放的星期
	 */
	private String openWeeks;
	/**
	 * 开放的时间段
	 */
	private String openTime;
	/**
	 * 是否显示 0不显示 1显示
	 */
	private int display;
	/**
	 * 活动状态 0开启 1关闭
	 */
	private int status;
	/**
	 * 排序字段
	 */
	private int sort;
	/**
	 * 图片id
	 */
	private int imgId;

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getActivityType() {
		return activityType;
	}

	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getOpenWeeks() {
		return openWeeks;
	}

	public void setOpenWeeks(String openWeeks) {
		this.openWeeks = openWeeks;
	}

	public int getActivityShowType() {
		return activityShowType;
	}

	public void setActivityShowType(int activityShowType) {
		this.activityShowType = activityShowType;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
}
