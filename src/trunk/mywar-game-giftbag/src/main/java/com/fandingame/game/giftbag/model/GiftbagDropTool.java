package com.fandingame.game.giftbag.model;

/**
 * 礼包掉落物品
 * 
 * @author yezp
 */
@SuppressWarnings("serial")
public class GiftbagDropTool implements java.io.Serializable {

	private int giftbagDropToolId;
	private Integer giftbagId;
	private Integer toolType;
	private Integer toolId;
	private String name;
	private Integer toolNum;
	private Integer isCheck;
	private Integer lowerNum;
	private Integer upperNum;

	private String toolTypeName;

	public GiftbagDropTool() {
	}

	public GiftbagDropTool(int giftbagDropToolId) {
		this.giftbagDropToolId = giftbagDropToolId;
	}

	public GiftbagDropTool(int giftbagDropToolId, Integer giftbagId,
			Integer toolType, Integer toolId, String name, Integer toolNum,
			Integer isCheck, Integer lowerNum, Integer upperNum) {
		this.giftbagDropToolId = giftbagDropToolId;
		this.giftbagId = giftbagId;
		this.toolType = toolType;
		this.toolId = toolId;
		this.name = name;
		this.toolNum = toolNum;
		this.isCheck = isCheck;
		this.lowerNum = lowerNum;
		this.upperNum = upperNum;
	}

	public int getGiftbagDropToolId() {
		return this.giftbagDropToolId;
	}

	public void setGiftbagDropToolId(int giftbagDropToolId) {
		this.giftbagDropToolId = giftbagDropToolId;
	}

	public Integer getGiftbagId() {
		return this.giftbagId;
	}

	public void setGiftbagId(Integer giftbagId) {
		this.giftbagId = giftbagId;
	}

	public Integer getToolType() {
		return this.toolType;
	}

	public void setToolType(Integer toolType) {
		this.toolType = toolType;
	}

	public Integer getToolId() {
		return this.toolId;
	}

	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

	public String getName() {
		return this.name;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getToolNum() {
		return this.toolNum;
	}

	public void setToolNum(Integer toolNum) {
		this.toolNum = toolNum;
	}

	public Integer getLowerNum() {
		return this.lowerNum;
	}

	public void setLowerNum(Integer lowerNum) {
		this.lowerNum = lowerNum;
	}

	public Integer getUpperNum() {
		return this.upperNum;
	}

	public void setUpperNum(Integer upperNum) {
		this.upperNum = upperNum;
	}

	public String getToolTypeName() {
		return toolTypeName;
	}

	public void setToolTypeName(String toolTypeName) {
		this.toolTypeName = toolTypeName;
	}

}
