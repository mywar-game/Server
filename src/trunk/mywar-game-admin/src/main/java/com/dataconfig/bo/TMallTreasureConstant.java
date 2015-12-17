package com.dataconfig.bo;

/**
 * TMallTreasureConstant entity. @author MyEclipse Persistence Tools
 */

public class TMallTreasureConstant implements java.io.Serializable {

	// Fields

	private TMallTreasureConstantId id;
	private Integer type;
	private Integer originalPrice;
	private Integer price;
	private Integer canArenaBuy;
	private Integer needArenaScore;
	private Integer lastTime;
	private Integer state;
	private String treasureName;

	// Constructors

	/** default constructor */
	public TMallTreasureConstant() {
	}

	/** full constructor */
	public TMallTreasureConstant(TMallTreasureConstantId id, Integer type,
			Integer originalPrice, Integer price, Integer canArenaBuy,
			Integer needArenaScore, Integer lastTime, Integer state) {
		this.id = id;
		this.type = type;
		this.originalPrice = originalPrice;
		this.price = price;
		this.canArenaBuy = canArenaBuy;
		this.needArenaScore = needArenaScore;
		this.lastTime = lastTime;
		this.state = state;
	}
	
	public TMallTreasureConstant(Integer treasureId, Integer category, Integer type, 
			Integer originalPrice, Integer price, Integer canArenaBuy, 
			Integer needArenaScore, Integer lastTime, Integer state , String treasureName) {
		TMallTreasureConstantId id = new TMallTreasureConstantId();
		id.setCategory(category);
		id.setTreasureId(treasureId);
		this.id = id;
		this.type = type;
		this.originalPrice = originalPrice;
		this.price = price;
		this.canArenaBuy = canArenaBuy;
		this.needArenaScore = needArenaScore;
		this.lastTime = lastTime;
		this.state = state;
		this.treasureName = treasureName;
	}

	// Property accessors

	public TMallTreasureConstantId getId() {
		return this.id;
	}

	public void setId(TMallTreasureConstantId id) {
		this.id = id;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOriginalPrice() {
		return this.originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCanArenaBuy() {
		return this.canArenaBuy;
	}

	public void setCanArenaBuy(Integer canArenaBuy) {
		this.canArenaBuy = canArenaBuy;
	}

	public Integer getNeedArenaScore() {
		return this.needArenaScore;
	}

	public void setNeedArenaScore(Integer needArenaScore) {
		this.needArenaScore = needArenaScore;
	}

	public Integer getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Integer lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getTreasureName() {
		return treasureName;
	}

	public void setTreasureName(String treasureName) {
		this.treasureName = treasureName;
	}

}