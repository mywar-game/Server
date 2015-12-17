package com.framework.server.socket.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.framework.config.LocalTools;
import com.framework.log.LogSystem;
import com.framework.server.manager.IServer;
import com.framework.server.socket.netty.codefactory.Decoder;
import com.framework.server.socket.netty.codefactory.Encoder;
import com.framework.server.threadpool.ThreadPoolBean;

public class NettyServer implements IServer {

	private String address;
	private ChannelHandler hander;
	private ThreadPoolBean pool;

	public ThreadPoolBean getPool() {
		return pool;
	}

	public void setPool(ThreadPoolBean pool) {
		this.pool = pool;
	}

	public ChannelHandler getHander() {
		return hander;
	}

	public void setHander(ChannelHandler hander) {
		this.hander = hander;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void restart() {
		// TODO Auto-generated method stub

	}

	public void shutdown() {
		// TODO Auto-generated method stub

	}

	public void start() {
		// TODO Auto-generated method stub
		ChannelFactory channelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
	    ServerBootstrap serverBootstrap = new ServerBootstrap(channelFactory);
	    
	    serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				// TODO Auto-generated method stub
				return Channels.pipeline(new Decoder(), hander, new Encoder());
			}
		});
	    serverBootstrap.setOption("child.tcpNoDelay", true);
	    serverBootstrap.setOption("child.keepAlive", true);
	    InetSocketAddress inetSocketAddress;
	    int port = LocalTools.getLocalConfig().getPort();
	    if (address == null || address.equals("")) {
		     inetSocketAddress = new InetSocketAddress(port);
	    } else {
	    	inetSocketAddress = new InetSocketAddress(address, port);
	    }
	    serverBootstrap.bind(inetSocketAddress);
	    LogSystem.info("netty Server start with port = " + port + ", address = " + inetSocketAddress.getAddress());
	}
	
	public static void main(String[] args) {
		int port = 12369;
		ChannelFactory channelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
	    ServerBootstrap serverBootstrap = new ServerBootstrap(channelFactory);
	    
	    serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				// TODO Auto-generated method stub
				return Channels.pipeline(new Decoder(), new NettyHandler(), new Encoder());
			}
		});
	    serverBootstrap.setOption("child.tcpNoDelay", true);
	    serverBootstrap.setOption("child.keepAlive", true);
	    serverBootstrap.bind(new InetSocketAddress(port));
	    System.out.println("netty Server start with port" + port);
	}
}
