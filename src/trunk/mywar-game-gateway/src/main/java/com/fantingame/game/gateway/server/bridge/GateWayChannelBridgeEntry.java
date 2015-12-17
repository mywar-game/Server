package com.fantingame.game.gateway.server.bridge;

import java.util.ArrayList;
import java.util.List;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.gateway.server.manager.Session;
import com.fantingame.game.gateway.server.manager.SessionManager;
import com.fantingame.game.gateway.server.manager.UserInfo;
import com.fantingame.game.gateway.server.manager.UserManager;
import com.fantingame.game.msgbody.common.codec.DataCodecFactory;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.common.model.MsgHead;
import com.fantingame.game.server.actionmanager.ActionExcutor;
import com.fantingame.game.server.bridge.IBridgeEntry;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.monitor.MonitorService;
import com.fantingame.game.server.msg.ByteToMsg;
import com.fantingame.game.server.msg.ChannelType;
import com.fantingame.game.server.msg.ServerMsgManager;
import com.fantingame.game.server.msg.ServerToChannelManager;
import com.fantingame.game.server.msg.ServerType;
/**
 * 网关服务器收到其他 服务器的消息入口  不压栈 直接处理
 * @author magical
 *
 */
public class GateWayChannelBridgeEntry implements IBridgeEntry {
	
	public void receivedData(Channel channel, byte[] datas) {
		ByteToMsg byteToMsg = new ByteToMsg(datas);
		//统计服务器间请求
		MonitorService.getInstance().markOneServerRequest();
		if (byteToMsg.parseByteServer(channel)) {
			List<Msg> msgVector = byteToMsg.getMsgVector();
			// 直接转发消息
			if (msgVector.size() > 0) {
				for (Msg msg : msgVector) {
				    LogSystem.debug("收到消息并进行处理"+msg.getMsgHead().getCmdCode()+",Time="+System.currentTimeMillis());
					String toId = "";
					int toType = -1;
					if (msg.getMsgHead().getMsgType() == MsgHead.TYPEOFREQUEST
							|| msg.getMsgHead().getMsgType() == MsgHead.TYPEOFNOTIFY) {
						toId = msg.getMsgHead().getToID();
						toType = msg.getMsgHead().getToType();
					} else if (msg.getMsgHead().getMsgType() == MsgHead.TYPEOFRESPONSE) {
						toId = msg.getMsgHead().getFromID();
						toType = msg.getMsgHead().getFromType();
					} else {
						throw new NullPointerException("先设置消息的类型 请求？响应？广播？");
					}
					// 发送系统消息
					if (toType == MsgHead.TO_OR_FROM_TYPE_SYSTEM) {
						if (toId.equals(ServerType.GATEWAY_SERVER.name())) {
							List<Msg> remp = new ArrayList<Msg>(1);
							remp.add(msg);
							List<Msg> resultMsg = ActionExcutor.getInstance()
									.excutorAction(remp, channel);
							if (resultMsg != null && resultMsg.size() > 0) {
								byte[] res = DataCodecFactory.getInstance()
										.encodeMsgServer(resultMsg);
								channel.write(res);
//								ServerType serverType = ServerType.getByName(msg.getMsgHead().getFromID());
//								ChannelType channelType = ServerToChannelManager
//										.getInstance().getChannel(serverType);
//								ServerMsgManager.getInstance().tryPublishEvent(channelType,
//										resultMsg);
							}
						} else {
							ServerType serverType = ServerType.getByName(toId);
							ChannelType channelType = ServerToChannelManager
									.getInstance().getChannel(serverType);
							ServerMsgManager.getInstance().tryPublishEvent(channelType,
									msg);
						}
					} else if (toType == MsgHead.TO_OR_FROM_TYPE_USER) {
						LogSystem.debug("是用户消息");
						UserInfo userInfo = UserManager.getInstance()
								.getUserInfoByUserId(toId);
						if (userInfo != null) {
							Session session = SessionManager.getInstance()
									.getSession(userInfo.getSessionId());
							if (session != null) {
//								System.out.println(System.currentTimeMillis() + "----------- send to user ---" + msg.getMsgHead().getMsgSequense());
//								msg.getMsgHead().setMsgSequense(3);
								session.sendMsg(msg);
							} else {
								LogSystem.warn("用户不在线"
										+ msg.getMsgHead().getToID()
										+ ",session==null 推送消息失败！！");
							}
						} else {
							LogSystem.warn("用户不在线" + msg.getMsgHead().getToID()
									+ ",userInfo==null 推送消息失败！！");
						}
					} else {
						throw new NullPointerException("错误的toType类型"
								+ msg.getMsgHead().getToType());
					}
				}
			}
		}
	}
}
