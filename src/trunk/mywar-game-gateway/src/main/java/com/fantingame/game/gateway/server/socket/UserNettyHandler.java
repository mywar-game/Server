package com.fantingame.game.gateway.server.socket;


import java.net.InetSocketAddress;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.gateway.server.manager.Session;
import com.fantingame.game.gateway.server.manager.SessionManager;
import com.fantingame.game.gateway.server.manager.UserInfo;
import com.fantingame.game.gateway.server.manager.UserManager;
import com.fantingame.game.server.bridge.IBridgeEntry;
import com.fantingame.game.server.channel.AbstractChannel;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.channel.NettyChannel;
import com.fantingame.game.server.monitor.MonitorService;

public class UserNettyHandler extends SimpleChannelUpstreamHandler {
	
	private IBridgeEntry bridgeEntry;
	
	   @Override
	   public void messageReceived(
	            ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		   Channel channel =  (Channel)ctx.getChannel().getAttachment();
			try {
				Session session = SessionManager
						.getInstance()
						.getSession(channel.getChannelId());
				 if(session!=null){
					 session.setLastRecordTime(System.currentTimeMillis());
				 }
				 byte[] datas = (byte[])e.getMessage();
				 MonitorService.getInstance().markUserIncomingBandwidth(datas.length);
				 if(datas.length>0){
					 bridgeEntry.receivedData(channel, datas);
				 }
			} catch (Exception e1) {
				LogSystem.error(e1, "");
			}
	    }
	   
	    @Override
	    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
	            throws Exception {
	    }
	    @Override
	    public void exceptionCaught(
	            ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
	    	
	    }
	    @Override
	    public void channelConnected(
	            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
	    	NettyChannel channel = new NettyChannel(ctx.getChannel());
			String clientIP = ((InetSocketAddress)ctx.getChannel().getRemoteAddress()).getAddress().getHostAddress();
			channel.addAttribute(AbstractChannel.IP, clientIP);
			ctx.getChannel().setAttachment(channel);
			
			Session userSession = new Session(channel);
			SessionManager.getInstance().addSession(userSession);
	    }
	    @Override
	    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
	    	NettyChannel nettyChannel = (NettyChannel)ctx.getChannel().getAttachment();
			LogSystem.debug("user connection channelClose:"+nettyChannel.getChannelId());
			//清理session
			Object userIdObject = nettyChannel.getAttribute(AbstractChannel.USER_ID);
			if(userIdObject!=null){
				String userId = (String)userIdObject;
				UserInfo userInfo = UserManager.getInstance().getUserInfoByUserId(userId);
				if(userInfo!=null&&userInfo.getSessionId().equals(nettyChannel.getChannelId())){
					UserManager.getInstance().removeUserInfoByUserId(userId);
				}
			}
			//清除session
			Session session = SessionManager.getInstance().delSession(nettyChannel.getChannelId());
			//通知逻辑服及战斗服 用户登出
			if(session!=null){
				UserManager.getInstance().userLogout(session);
			}
			LogSystem.debug("删除了一个session后的数量:"+SessionManager.getInstance().getSessionSize());
	    }
	    
		public IBridgeEntry getBridgeEntry() {
			return bridgeEntry;
		}
		public void setBridgeEntry(IBridgeEntry bridgeEntry) {
			this.bridgeEntry = bridgeEntry;
		}
}
