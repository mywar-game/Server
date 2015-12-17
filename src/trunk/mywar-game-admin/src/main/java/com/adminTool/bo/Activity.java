package com.adminTool.bo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 活动
 * 
 * @author yezp
 */
public class Activity implements Serializable {

	private static final long serialVersionUID = -2457981271117495204L;
	/**
	 * 活动id
	 */
	private Integer activityId;
	/**
	 * 活动显示类型
	 */
	private Integer activityShowType;
	/**
	 * 活动类型
	 */
	private Integer activityType;
	/**
	 * 活动名称
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
	private Timestamp startTime;
	/**
	 * 结束时间
	 */
	private Timestamp endTime;
	/**
	 * 开展周数
	 */
	private String openWeeks;
	/**
	 * 开放时间
	 */
	private String openTime;
	/**
	 * 参数
	 */
	private String param;
	/**
	 * 是否显示(0不显示，1显示)
	 */
	private Integer display;
	/**
	 * 是否开启(0开启 1 关闭)
	 */
	private Integer status;
	/**
	 * 顺序
	 */
	private Integer sort;
	/**
	 * 图片id
	 */
	private Integer imgId;

	public Activity() {
	}

	public Activity(Integer activityId, Integer activityShowType,
			Integer activityType, String activityName, String activityTitle,
			String activityDesc, Timestamp startTime, Timestamp endTime,
			String openWeeks, String openTime, String param, Integer display,
			Integer status, Integer sort, Integer imgId, String refreshClassName) {
		this.activityId = activityId;
		this.activityShowType = activityShowType;
		this.activityType = activityType;
		this.activityName = activityName;
		this.activityTitle = activityTitle;
		this.activityDesc = activityDesc;
		this.startTime = startTime;
		this.endTime = endTime;
		this.openWeeks = openWeeks;
		this.openTime = openTime;
		this.param = param;
		this.display = display;
		this.status = status;
		this.sort = sort;
		this.imgId = imgId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getActivityShowType() {
		return activityShowType;
	}

	public void setActivityShowType(Integer activityShowType) {
		this.activityShowType = activityShowType;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getOpenWeeks() {
		return openWeeks;
	}

	public void setOpenWeeks(String openWeeks) {
		this.openWeeks = openWeeks;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}
}
