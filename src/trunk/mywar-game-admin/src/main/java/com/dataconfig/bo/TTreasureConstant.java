package com.dataconfig.bo;

/**
 * TTreasureConstant entity. @author MyEclipse Persistence Tools
 */

public class TTreasureConstant implements java.io.Serializable {

	// Fields

	private Integer treasureId;
	private String treasureName;
	private String treasureDesc;
	private Integer treasureType;
	private Integer salePrice;
	private Integer canBuy;
	private Integer price;
	private Integer isOverlay;
	private Integer overlayNumMax;
	private String value;
	private Integer isBinding;
	private Integer medalBuy;
	private String pathWay;

	// Constructors

	/** default constructor */
	public TTreasureConstant() {
	}

	/** minimal constructor */
	public TTreasureConstant(String treasureName, String treasureDesc,
			Integer treasureType, Integer salePrice, Integer canBuy,
			Integer price, Integer isOverlay, Integer overlayNumMax,
			Integer isBinding, Integer medalBuy) {
		this.treasureName = treasureName;
		this.treasureDesc = treasureDesc;
		this.treasureType = treasureType;
		this.salePrice = salePrice;
		this.canBuy = canBuy;
		this.price = price;
		this.isOverlay = isOverlay;
		this.overlayNumMax = overlayNumMax;
		this.isBinding = isBinding;
		this.medalBuy = medalBuy;
	}

	/** full constructor */
	public TTreasureConstant(String treasureName, String treasureDesc,
			Integer treasureType, Integer salePrice, Integer canBuy,
			Integer price, Integer isOverlay, Integer overlayNumMax,
			String value, Integer isBinding, Integer medalBuy, String pathWay) {
		this.treasureName = treasureName;
		this.treasureDesc = treasureDesc;
		this.treasureType = treasureType;
		this.salePrice = salePrice;
		this.canBuy = canBuy;
		this.price = price;
		this.isOverlay = isOverlay;
		this.overlayNumMax = overlayNumMax;
		this.value = value;
		this.isBinding = isBinding;
		this.medalBuy = medalBuy;
		this.pathWay = pathWay;
	}

	// Property accessors

	public Integer getTreasureId() {
		return this.treasureId;
	}

	public void setTreasureId(Integer treasureId) {
		this.treasureId = treasureId;
	}

	public String getTreasureName() {
		return this.treasureName;
	}

	public void setTreasureName(String treasureName) {
		this.treasureName = treasureName;
	}

	public String getTreasureDesc() {
		return this.treasureDesc;
	}

	public void setTreasureDesc(String treasureDesc) {
		this.treasureDesc = treasureDesc;
	}

	public Integer getTreasureType() {
		return this.treasureType;
	}

	public void setTreasureType(Integer treasureType) {
		this.treasureType = treasureType;
	}

	public Integer getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getCanBuy() {
		return this.canBuy;
	}

	public void setCanBuy(Integer canBuy) {
		this.canBuy = canBuy;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getIsOverlay() {
		return this.isOverlay;
	}

	public void setIsOverlay(Integer isOverlay) {
		this.isOverlay = isOverlay;
	}

	public Integer getOverlayNumMax() {
		return this.overlayNumMax;
	}

	public void setOverlayNumMax(Integer overlayNumMax) {
		this.overlayNumMax = overlayNumMax;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getIsBinding() {
		return this.isBinding;
	}

	public void setIsBinding(Integer isBinding) {
		this.isBinding = isBinding;
	}

	public Integer getMedalBuy() {
		return this.medalBuy;
	}

	public void setMedalBuy(Integer medalBuy) {
		this.medalBuy = medalBuy;
	}

	public String getPathWay() {
		return this.pathWay;
	}

	public void setPathWay(String pathWay) {
		this.pathWay = pathWay;
	}

}