package com.fantingame.game.mywar.logic.scene.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.scene.SceneAction_changeLineReq;
import com.fantingame.game.msgbody.client.scene.SceneAction_enterReq;
import com.fantingame.game.msgbody.client.scene.SceneAction_loadedReq;
import com.fantingame.game.msgbody.client.scene.SceneAction_moveReq;
import com.fantingame.game.msgbody.client.scene.SceneAction_openReq;
import com.fantingame.game.msgbody.client.scene.SceneAction_openRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.scene.service.SceneService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

@GameAction
public class SceneAction {
	@Autowired
	private SceneService sceneService;
    /**
     * 进入场景
     * @param msg
     * @param channel
     * @return
     */
	public ICodeAble enter(Msg msg,Channel channel){
		String userId = msg.getMsgHead().getFromID();
		SceneAction_enterReq action_enterReq = msg.decodeBody(SceneAction_enterReq.class);
		return sceneService.addUser(userId, action_enterReq.getSceneId(), action_enterReq.getPosX(), action_enterReq.getPosY());
	}
	/**
	 * 加载完成
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble loaded(Msg msg,Channel channel){
		String userId = msg.getMsgHead().getFromID();
		SceneAction_loadedReq req = msg.decodeBody(SceneAction_loadedReq.class);
		return sceneService.userLoaded(userId, req.getSceneId());
	}
	/**
	 * 换线
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble changeLine(Msg msg,Channel channel){
		String userId = msg.getMsgHead().getFromID();
		SceneAction_changeLineReq req = msg.decodeBody(SceneAction_changeLineReq.class);
		return sceneService.changeLine(userId, req.getSceneId(), req.getTargetLineNum());
	}
	/**
	 * 移动
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble move(Msg msg,Channel channel){
		String userId = msg.getMsgHead().getFromID();
		SceneAction_moveReq req = msg.decodeBody(SceneAction_moveReq.class);
		sceneService.move(userId, req.getPosX(), req.getPosY());
		return null;
	}
	/**
	 * 开启场景
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble open(Msg msg,Channel channel){
		String userId = msg.getMsgHead().getFromID();
		SceneAction_openReq req = msg.decodeBody(SceneAction_openReq.class);
		sceneService.openScene(userId, req.getSceneId());
		SceneAction_openRes res = new SceneAction_openRes();
		res.setSceneId(req.getSceneId());
		return req;
	}
}
