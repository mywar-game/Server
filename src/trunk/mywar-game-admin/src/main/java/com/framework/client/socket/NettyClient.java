package com.framework.client.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.server.bridge.BridgeEntry;
import com.framework.server.msg.Msg;
import com.framework.server.msg.manager.MsgManageTool;
import com.framework.server.socket.netty.codefactory.Decoder;
import com.framework.server.socket.netty.codefactory.Encoder;

public class NettyClient {
	private Channel channel;
	private ChannelHandler handler;
    private ChannelFuture channelFuture;
	private ClientBootstrap bootstrap;
	private long reconnectWaitTime;
	//是否允许重新连接
	private boolean isReconnect;
	
	public NettyClient(String ip, int port, long reconnectWaitTime, BridgeEntry bridgeEntry, boolean isReconnect) {
		ChannelFactory  factory = new NioClientSocketChannelFactory(Executors
				.newCachedThreadPool(), Executors.newCachedThreadPool());
		bootstrap = new ClientBootstrap(factory);

		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);
		bootstrap.setOption("connectTimeoutMillis", 10000);
		bootstrap.setOption("remoteAddress", new InetSocketAddress(
				ip, port));
		this.handler = new NettyClientHander(bridgeEntry);
        this.reconnectWaitTime = reconnectWaitTime;
        this.isReconnect = isReconnect;
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				// TODO Auto-generated method stub
				return Channels.pipeline(new Decoder(), handler, new Encoder());
			}
		});
	}
	
	public void connect() {
		channelFuture = bootstrap.connect();
		channelFuture.awaitUninterruptibly();
		assert channelFuture.isDone();
	     if (!channelFuture.isSuccess()) {
			channelFuture.getCause().printStackTrace();
		} 
	     channel = channelFuture.getChannel();
	}

	public void close() {
		channel.close();
	}
	/**
	 * 判断该客户端是否关闭
	 * @return
	 */
	public boolean isOpen() {
		return channel.isOpen();
	}
	
	public boolean isConnected() {
		return channel.isConnected();
	}
	public boolean isBound() {
		return channel.isBound();
	}
	
	public synchronized  void sendMsgs(List<Msg> msgList) throws IOException {
			byte[] datas = MsgManageTool.saveResponseMsgs(msgList, SystemConstant.JAVA_CLIENT);
			int len = datas.length;
			ChannelBuffer buffer = ChannelBuffers.buffer(len);
			buffer.writeBytes(datas);
			if (channel.isOpen() && channel.isConnected()) {
				channel.write(buffer);
			} else {
				if (isReconnect) {
					reConnect();
					channel.write(buffer);
				}
			}
	}
	
	public synchronized void reConnect() {
		LogSystem.info("准备进行重新连接！");
		    while (!channel.isOpen() && !channel.isConnected()) {
		    	try {
					Thread.sleep(reconnectWaitTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					LogSystem.error(e, "");
				}
				  connect();
		    }
	}

	public static void main(String[] args) throws UnknownHostException, 
			IOException {
//		bodyExample b = new bodyExample();
//		b.setName("123");
//		List<Msg> list = MsgBuilder.buildMsgList(0, "0", 0, "0", 1, 100, 999, 
//				10, "0", 0, b);
//		NettyClient nettyClient = new NettyClient("192.168.110.188", 25250, 10000, null, false);
//		nettyClient.sendMsgs(list);
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		NettyClient nettyClient2 = new NettyClient("192.168.110.188", 25250, 10000, null, false);
//		nettyClient.sendMsgs(list);


		//        SocketClient socketClient = new SocketClient(socket);
		//        socketClient.sendMsgsToServerWithNoAnswer(list);
		//      List<Msg> reList = socketClient.sendMsgsToServer(list, CommomMsgBody.class);
		//        socket.close();
		// sendMsgsToServer("http://192.168.110.188:8080/XXGameServer/servlet/HttpTransfer", list, CommomMsgBody.class);
	}

	public boolean isReconnect() {
		return isReconnect;
	}

	public void setReconnect(boolean isReconnect) {
		this.isReconnect = isReconnect;
	}
}
