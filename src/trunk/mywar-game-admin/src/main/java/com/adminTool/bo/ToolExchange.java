package com.adminTool.bo;

/**
 * 道具兑换
 * 
 * @author yezp
 */
public class ToolExchange implements java.io.Serializable {

	private Integer exchangeId;
	private int activityId;
	private String preExchangeItems;
	private String postExchangeItems;
	private String description;
	private int times;

	private String showPreItems;
	private String showPostItems;

	public ToolExchange() {
	}

	public ToolExchange(int activityId, String preExchangeItems,
			String postExchangeItems, int times) {
		this.activityId = activityId;
		this.preExchangeItems = preExchangeItems;
		this.postExchangeItems = postExchangeItems;
		this.times = times;
	}

	public ToolExchange(int activityId, String preExchangeItems,
			String postExchangeItems, String description, int times) {
		this.activityId = activityId;
		this.preExchangeItems = preExchangeItems;
		this.postExchangeItems = postExchangeItems;
		this.description = description;
		this.times = times;
	}

	public Integer getExchangeId() {
		return this.exchangeId;
	}

	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}

	public int getActivityId() {
		return this.activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getPreExchangeItems() {
		return this.preExchangeItems;
	}

	public void setPreExchangeItems(String preExchangeItems) {
		this.preExchangeItems = preExchangeItems;
	}

	public String getPostExchangeItems() {
		return this.postExchangeItems;
	}

	public void setPostExchangeItems(String postExchangeItems) {
		this.postExchangeItems = postExchangeItems;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTimes() {
		return this.times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public String getShowPreItems() {
		return showPreItems;
	}

	public void setShowPreItems(String showPreItems) {
		this.showPreItems = showPreItems;
	}

	public String getShowPostItems() {
		return showPostItems;
	}

	public void setShowPostItems(String showPostItems) {
		this.showPostItems = showPostItems;
	}

}
