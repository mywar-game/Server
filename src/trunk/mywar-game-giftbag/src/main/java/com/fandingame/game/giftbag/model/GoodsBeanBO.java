package com.fandingame.game.giftbag.model;

/** 通用奖励对象 **/
public class GoodsBeanBO {

	/** 奖励物品类型 **/
	private Integer goodsType;
	/** 奖励物品id **/
	private Integer goodsId;
	/** 奖励物品数量 **/
	private Integer goodsNum;
	
	public GoodsBeanBO() {
		
	}
	
	public GoodsBeanBO(int goodsType, int goodsId, int goodsNum) {
		this.goodsType = goodsType;
		this.goodsId = goodsId;
		this.goodsNum = goodsNum;
	}

	/** 奖励物品类型 **/
	public Integer getGoodsType() {
		return goodsType;
	}

	/** 奖励物品类型 **/
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	/** 奖励物品id **/
	public Integer getGoodsId() {
		return goodsId;
	}

	/** 奖励物品id **/
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/** 奖励物品数量 **/
	public Integer getGoodsNum() {
		return goodsNum;
	}

	/** 奖励物品数量 **/
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

}
