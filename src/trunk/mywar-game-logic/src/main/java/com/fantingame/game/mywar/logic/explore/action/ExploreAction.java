package com.fantingame.game.mywar.logic.explore.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.explore.ExploreAction_autoRefreshRes;
import com.fantingame.game.msgbody.client.explore.ExploreAction_exchangeReq;
import com.fantingame.game.msgbody.client.explore.ExploreAction_exchangeRes;
import com.fantingame.game.msgbody.client.explore.ExploreAction_exploreReq;
import com.fantingame.game.msgbody.client.explore.ExploreAction_exploreRes;
import com.fantingame.game.msgbody.client.explore.ExploreAction_getUserExploreInfoRes;
import com.fantingame.game.msgbody.client.explore.ExploreAction_refreshMapRes;
import com.fantingame.game.msgbody.client.explore.UserExploreInfoBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.explore.service.ExploreService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

/**
 * 探索Action
 * 
 * @author yezp
 */
@GameAction
public class ExploreAction {

	@Autowired
	private ExploreService exploreService;
	
	/**
	 * 获取用户探索信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getUserExploreInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();		
		UserExploreInfoBO userExploreInfoBO = this.exploreService.getUserExploreInfo(userId);
		
		ExploreAction_getUserExploreInfoRes res = new ExploreAction_getUserExploreInfoRes();
		res.setUserExploreInfoBO(userExploreInfoBO);
		return res;
	}
	
	/**
	 * 兑换英雄
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble exchange(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		ExploreAction_exchangeReq req = msg.decodeBody(ExploreAction_exchangeReq.class);		
		Map<String, Object> map = this.exploreService.exchange(userId, req.getSystemHeroId());
		
		ExploreAction_exchangeRes res = new ExploreAction_exchangeRes();
		res.setDrop((CommonGoodsBeanBO) map.get("dr"));
		res.setIntegral((Integer) map.get("integral"));
		return res;
	}
	
	/**
	 * 刷新
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble refreshMap(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		ExploreAction_refreshMapRes res = this.exploreService.refreshMap(userId);
		return res;
	}
	
	/**
	 * 探索
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble explore(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		ExploreAction_exploreReq req = msg.decodeBody(ExploreAction_exploreReq.class);
		
		ExploreAction_exploreRes res = this.exploreService.explore(userId, req.getType());
		return res;
	}
	
	/**
	 * 自动刷新，直到刷出有英雄的为止
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble autoRefresh(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		ExploreAction_autoRefreshRes res = this.exploreService.autoRefresh(userId);
		return res;
	}
	
}
