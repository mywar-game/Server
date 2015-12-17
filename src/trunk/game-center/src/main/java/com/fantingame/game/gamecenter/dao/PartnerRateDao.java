package com.fantingame.game.gamecenter.dao;

import java.util.List;

import com.fantingame.game.gamecenter.model.PartnerRate;

public interface PartnerRateDao {

	/**
	 * 重新加载
	 */
	public void reload();
	
	/**
	 * 获取所有的数据
	 * 
	 * @return
	 */
	public List<PartnerRate> getAll();
	
	/**
	 * 根据渠道获取对应的倍率
	 * 
	 * @param partnerId
	 * @return
	 */
	public int getRate(String partnerId);
}
