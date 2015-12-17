package com.fantingame.game.gateway.server.bridge;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.gateway.server.manager.Session;
import com.fantingame.game.gateway.server.manager.SessionManager;
import com.fantingame.game.gateway.server.manager.UserInfo;
import com.fantingame.game.gateway.server.manager.UserManager;
import com.fantingame.game.gateway.server.service.ServerActionService;
import com.fantingame.game.gateway.util.EncryptUtil;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.common.model.MsgHead;
import com.fantingame.game.server.actionmanager.ActionExcutor;
import com.fantingame.game.server.bridge.IBridgeEntry;
import com.fantingame.game.server.channel.AbstractChannel;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.monitor.MonitorService;
import com.fantingame.game.server.msg.ByteToMsg;
import com.fantingame.game.server.msg.ChannelType;
import com.fantingame.game.server.msg.ServerMsgManager;
import com.fantingame.game.server.msg.ServerToChannelManager;
import com.fantingame.game.server.msg.ServerType;

public class UserChannelBridgeEntry implements IBridgeEntry {
    @Autowired
	private ServerActionService serverActionService; 
	@Override
	public void receivedData(Channel channel, byte[] datas) throws Exception {
		byte[] encodeData = EncryptUtil.encode(datas);
		byte[] decodeData = EncryptUtil.decode(encodeData);
		
		ByteToMsg byteToMsg = new ByteToMsg(decodeData);
		//记录请求数
		MonitorService.getInstance().markOneUserRequest();
		if(byteToMsg.parseByteUser(channel)){
			List<Msg> msgVector = byteToMsg.getMsgVector();
			     //直接转发消息
				 for(Msg msg : msgVector){
				     msg.getMsgHead().setUserSequense(channel.getChannelId());
				     msg.getMsgHead().setFromType(MsgHead.TO_OR_FROM_TYPE_USER);
				     msg.getMsgHead().setMsgType(MsgHead.TYPEOFREQUEST);
//				     ServerType serverType = ServerConstant.getServerTypeByCmdCode(msg.getMsgHead().getCmdCode());
				    List<ServerType> serverList =  serverActionService.getServerTypeListByActionName(msg.getMsgHead().getCmdCode());
				    if(serverList==null||serverList.size()==0){
				    	LogSystem.error(new RuntimeException("不存在的action"+msg.getMsgHead().getCmdCode()), "");
				    	continue;
				    }
					for (ServerType serverType : serverList) {
						String toId = serverType.name();
						// 发送系统消息
						if (toId.equals(ServerType.GATEWAY_SERVER.name())) {
							List<Msg> request = new ArrayList<Msg>(1);
							request.add(msg);
							List<Msg> resultMsg = ActionExcutor.getInstance()
									.excutorAction(request, channel);
							if (resultMsg != null && resultMsg.size() > 0) {
								Session session = SessionManager
										.getInstance()
										.getSession(channel.getChannelId());
								if(session!=null){
									session.sendMsg(resultMsg.get(0));
								}
							}
						} else {
							ChannelType channelType = ServerToChannelManager
									.getInstance().getChannel(serverType);
							// 校验用户是否登录了
							Object o = channel
									.getAttribute(AbstractChannel.USER_ID);
							String userId = null;
							if (o != null) {
								userId = (String) o;
								msg.getMsgHead().setFromID(userId);
								
								UserInfo userInfo = UserManager.getInstance()
										.getUserInfoByUserId(userId);
								if (userInfo != null && (channel.getChannelId()).equals(userInfo.getSessionId())) {
									// 先递增并获取
									int clientSequenese = msg.getMsgHead().getMsgSequense();
									int serverSequenese = userInfo.getSequense().incrementAndGet();
									
									System.out.println("userId" + userInfo.getUserId() + ",client msgSequenese:" + clientSequenese);
									System.out.println("userId" + userInfo.getUserId() + ",server sequenese:" + serverSequenese);
									
									ServerMsgManager.getInstance().tryPublishEvent(channelType, msg);
									return;
								}
							}
							// 返回没有登录的错误消息
							msg.getMsgHead().setErrorCode(1001);
							msg.getMsgHead().setMsgType(MsgHead.TYPEOFRESPONSE);
							Session session = SessionManager.getInstance()
									.getSession(channel.getChannelId());
							//因在登陆前 没有用户id  所以需要直接flush数据给用户
							if(session!=null){
								session.flushAll(msg);
							}
						}
					}
				 }
			}
	}
}
