package com.fantingame.game.gateway.server.socket;


import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.server.bridge.IBridgeEntry;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.channel.NettyChannel;
import com.fantingame.game.server.monitor.MonitorService;
import com.fantingame.game.server.msg.ServerChannelManager;

public class ServerNettyHandler extends SimpleChannelUpstreamHandler {
	
	private IBridgeEntry bridgeEntry;
	
	   @Override
	   public void messageReceived(
	            ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		   Channel channel =  (Channel)ctx.getChannel().getAttachment();
			try {
				byte[] datas = (byte[])e.getMessage();
				MonitorService.getInstance().markServerIncomingBandwidth(datas.length);
				bridgeEntry.receivedData(channel,datas);
			} catch (Exception e1) {
				LogSystem.error(e1, "");
			}
	    }
	   
	    @Override
	    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
	            throws Exception {
	    }
	    
	    @Override
	    public void channelConnected(
	            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
	    	NettyChannel channel = new NettyChannel(ctx.getChannel());
			ctx.getChannel().setAttachment(channel);
	    }
	    
	    @Override
	    public void channelClosed(
	            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
	    	NettyChannel channel = (NettyChannel)ctx.getChannel().getAttachment();
			LogSystem.info("Server connection channelClose:"+channel.getChannelId());
			ServerChannelManager.getInstance().removeChannel(channel);
	    }
	    
		public IBridgeEntry getBridgeEntry() {
			return bridgeEntry;
		}
		
		public void setBridgeEntry(IBridgeEntry bridgeEntry) {
			this.bridgeEntry = bridgeEntry;
		}
}
