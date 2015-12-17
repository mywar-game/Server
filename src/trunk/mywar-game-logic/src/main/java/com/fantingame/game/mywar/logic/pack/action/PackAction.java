package com.fantingame.game.mywar.logic.pack.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.pack.PackAction_abandonToolReq;
import com.fantingame.game.msgbody.client.pack.PackAction_abandonToolRes;
import com.fantingame.game.msgbody.client.pack.PackAction_extendPackReq;
import com.fantingame.game.msgbody.client.pack.PackAction_extendPackRes;
import com.fantingame.game.msgbody.client.pack.PackAction_extendStorehouseReq;
import com.fantingame.game.msgbody.client.pack.PackAction_extendStorehouseRes;
import com.fantingame.game.msgbody.client.pack.PackAction_storehouseInOrOutReq;
import com.fantingame.game.msgbody.client.pack.PackAction_storehouseInOrOutRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.pack.service.PackService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

@GameAction
public class PackAction {
	@Autowired
	private PackService packService;
	
//    /**
//     * 扩展背包
//     * @param msg
//     * @param channel
//     * @return
//     */
//	public ICodeAble extendPack(Msg msg,Channel channel){
//		String userId = msg.getMsgHead().getFromID();
//		PackAction_extendPackReq req = msg.decodeBody(PackAction_extendPackReq.class);
//		packService.userBackPackChangePos(userId, req.getToolId(), req.getTargetPos());
//		return null;
//	}
	
	/**
	 * 扩展背包
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble extendPack(Msg msg, Channel channel){
		String userId = msg.getMsgHead().getFromID();
		PackAction_extendPackReq req = msg.decodeBody(PackAction_extendPackReq.class);
		CommonGoodsBeanBO drop = packService.extendPack(userId, req.getExtendNum());
		
		PackAction_extendPackRes res = new PackAction_extendPackRes();
		res.setDrop(drop);
		return res;
	}
	
	/**
	 * 背包放弃道具
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble abandonTool(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		PackAction_abandonToolReq req = msg.decodeBody(PackAction_abandonToolReq.class);
		PackAction_abandonToolRes res = this.packService.abandonTool(userId, req.getToolType(), req.getToolId(), req.getToolNum(), req.getUserEquipId(), req.getUserGemstoneId());		
		return res;
	}
	
	/**
	 * 仓库存取
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble storehouseInOrOut(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		PackAction_storehouseInOrOutReq req = msg.decodeBody(PackAction_storehouseInOrOutReq.class);
		PackAction_storehouseInOrOutRes res = this.packService.storehouseInOrOut(userId, req.getType(), req.getToolType(), req.getToolId(), req.getToolNum(), req.getUserEquipId(), req.getUserGemstoneId());
		return res;
	}
	
	/**
	 * 扩展仓库格子
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble extendStorehouse(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		PackAction_extendStorehouseReq req = msg.decodeBody(PackAction_extendStorehouseReq.class);
		PackAction_extendStorehouseRes res = this.packService.extendStorehouse(userId, req.getExtendNum());
		return res;
	}
}
