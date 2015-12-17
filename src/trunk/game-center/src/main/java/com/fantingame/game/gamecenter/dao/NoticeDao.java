package com.fantingame.game.gamecenter.dao;

import com.fantingame.game.gamecenter.model.AmendNotice;
import com.fantingame.game.gamecenter.model.Notice;
import com.fantingame.game.gamecenter.model.SpecialNotice;


public interface NoticeDao {
	/**
	 * 根据serverId获取公告
	 * @param serverId
	 * @return
	 */
	public Notice getNotice(String serverId);

	/**
	 * 获取修正公告
	 * 
	 * @param serverId
	 * @param partnerId
	 * @return
	 */
	public AmendNotice getAmendNotice(String serverId, String partnerId);

	/**
	 * 获取特殊公告
	 * 
	 * @param serverId
	 * @param partnerId
	 * @return
	 */
	public SpecialNotice getSpecialNotice(String partnerId);
}
