package com.framework.server.socket.mina;




import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.server.bridge.BridgeEntry;
import com.framework.server.channle.MinaChannel;

public class MinaHandler implements IoHandler {
	public static final String CHANNEL = "CHANNEL";

	public static final String IS_FIRST_EXCEPTION = "IS_FIRST_EXCEPTION";
	private BridgeEntry bridgeEntry;

	public BridgeEntry getBridgeEntry() {
		return bridgeEntry;
	}

	public void setBridgeEntry(BridgeEntry bridgeEntry) {
		this.bridgeEntry = bridgeEntry;
	}

	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {
		// TODO Auto-generated method stub
		LogSystem.error(new NullPointerException(), arg1.getMessage());
		LogSystem.info("error11:" + arg1.getMessage());
		
	}

	public void messageReceived(IoSession arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		byte[] bytes = (byte[]) arg1;
		MinaChannel minaChannel = (MinaChannel) arg0.getAttribute(CHANNEL);
		bridgeEntry.receivedData(minaChannel, bytes);
	}

	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
//		LogSystem.info("message has been sent!");

	}

	public void sessionClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		LogSystem.info("session closed!");
	}

	public void sessionCreated(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		LogSystem.info("session create!");

	}

	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub
		LogSystem.info("session Idle!");

	}

	public void sessionOpened(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		MinaChannel minaChannel = new MinaChannel(arg0);
		minaChannel.setClientType(SystemConstant.JAVA_CLIENT);
		minaChannel.setProtocolType(SystemConstant.SOCKET_CONNECT);
		
		arg0.setAttribute(CHANNEL, minaChannel);
		LogSystem.info("session opened!");

	}

}
