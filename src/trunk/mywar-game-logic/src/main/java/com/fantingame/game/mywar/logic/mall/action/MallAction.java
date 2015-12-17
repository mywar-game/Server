package com.fantingame.game.mywar.logic.mall.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.mall.MallAction_buyBackReq;
import com.fantingame.game.msgbody.client.mall.MallAction_buyBackRes;
import com.fantingame.game.msgbody.client.mall.MallAction_buyInReq;
import com.fantingame.game.msgbody.client.mall.MallAction_buyInRes;
import com.fantingame.game.msgbody.client.mall.MallAction_buyMysteriousMallReq;
import com.fantingame.game.msgbody.client.mall.MallAction_buyMysteriousMallRes;
import com.fantingame.game.msgbody.client.mall.MallAction_getBuyBackListRes;
import com.fantingame.game.msgbody.client.mall.MallAction_getMysteriousMallInfoRes;
import com.fantingame.game.msgbody.client.mall.MallAction_sellReq;
import com.fantingame.game.msgbody.client.mall.MallAction_sellRes;
import com.fantingame.game.msgbody.client.mall.UserBuyBackInfoBO;
import com.fantingame.game.msgbody.client.mall.UserMysteriousMallBO;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.mall.service.MallService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

@GameAction
public class MallAction {
	
	@Autowired
	private MallService mallService;

	/**
	 * 买入
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble buyIn(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		MallAction_buyInReq req = msg.decodeBody(MallAction_buyInReq.class);
		
		CommonGoodsBeanBO drop = this.mallService.buyIn(userId, req.getMallId());
		MallAction_buyInRes res = new MallAction_buyInRes();
		res.setDrop(drop);
		return res;
	}

	/**
	 * 出售装备，道具
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble sell(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		MallAction_sellReq req = msg.decodeBody(MallAction_sellReq.class);
		
		CommonGoodsBeanBO drop = this.mallService.sell(userId, req.getToolType(), req.getToolId(), req.getToolNum(), req.getUserEquipId());
		MallAction_sellRes res = new MallAction_sellRes();
		res.setDrop(drop);
		return res;
	}
	
	/**
	 * 获取用户回购信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getBuyBackList(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		List<UserBuyBackInfoBO> userBuyBackInfoList = this.mallService.getBuyBackList(userId);
		MallAction_getBuyBackListRes res = new MallAction_getBuyBackListRes();
		res.setUserBuyBackInfoList(userBuyBackInfoList);
		return res;
	}
	
	/**
	 * 回购
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble buyBack(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		MallAction_buyBackReq req = msg.decodeBody(MallAction_buyBackReq.class);
		
		CommonGoodsBeanBO drop = this.mallService.buyBack(userId, req.getBuyBackId());
		MallAction_buyBackRes res = new MallAction_buyBackRes();
		res.setDrop(drop);
		return res;
	}
	
	/**
	 * 获取神秘商店的商品信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getMysteriousMallInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		List<UserMysteriousMallBO> list = this.mallService.getMysteriousMallInfo(userId);
		MallAction_getMysteriousMallInfoRes res = new MallAction_getMysteriousMallInfoRes();
		res.setUserMallList(list);
		return res;
	}
	
	/**
	 * 购买神秘商店商品信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble buyMysteriousMall(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		MallAction_buyMysteriousMallReq req = msg.decodeBody(MallAction_buyMysteriousMallReq.class);
		MallAction_buyMysteriousMallRes res = this.mallService.buyMysteriousMall(userId, req.getMallId());
		return res;
	}
}
