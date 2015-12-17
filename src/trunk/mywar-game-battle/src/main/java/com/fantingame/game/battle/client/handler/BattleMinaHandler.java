package com.fantingame.game.battle.client.handler;




import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.server.bridge.IBridgeEntry;
import com.fantingame.game.server.channel.AbstractChannel;
import com.fantingame.game.server.channel.MinaChannel;
import com.fantingame.game.server.constant.SystemConstant;

public class BattleMinaHandler extends IoHandlerAdapter {
	public static final String CHANNEL = "CHANNEL";

	public static final String IS_FIRST_EXCEPTION = "IS_FIRST_EXCEPTION";
	
	public static final String IS_FIRST_REQUEST = "IS_FIRST_REQUEST";
	
	private IBridgeEntry bridgeEntry;
	
	public IBridgeEntry getBridgeEntry() {
		return bridgeEntry;
	}

	public void setBridgeEntry(IBridgeEntry bridgeEntry) {
		this.bridgeEntry = bridgeEntry;
	}
	@Override
	public void messageReceived(IoSession ioSession, Object arg1) {
		byte[] bytes = (byte[])arg1;
		MinaChannel channel = (MinaChannel) ioSession.getAttribute(CHANNEL);
		try {
			bridgeEntry.receivedData(channel, bytes);
		} catch (Exception e) {
			LogSystem.error(e, "");
		}
	}
	@Override	
	public void sessionCreated(IoSession ioSession) throws Exception {
		MinaChannel channel = new MinaChannel(ioSession);
		channel.setProtocolType(SystemConstant.SOCKET_CONNECT);
		channel.addAttribute(IS_FIRST_REQUEST, true);
		ioSession.setAttribute(CHANNEL, channel);
		String clientIP = ((InetSocketAddress)ioSession.getRemoteAddress()).getAddress().getHostAddress();
		channel.addAttribute(AbstractChannel.IP, clientIP);
		channel.addAttribute(AbstractChannel.IS_VERIFY_DATA, false);
	}

	@Override
	public void sessionClosed(IoSession ioSession) throws Exception {
		LogSystem.info("sessionClose"+ioSession.getId());
	}
}
