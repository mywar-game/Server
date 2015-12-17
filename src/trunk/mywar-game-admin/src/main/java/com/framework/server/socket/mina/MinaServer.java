package com.framework.server.socket.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.framework.log.LogSystem;
import com.framework.server.manager.IServer;
import com.framework.server.socket.mina.codefactory.CodeFactory;
import com.framework.server.threadpool.ThreadPoolBean;


public class MinaServer implements IServer {
    private IoAcceptor acceptor;
	private String address;
	private int port;
	private int maxReadBufferSize;
	private ThreadPoolBean pool;
	private IoHandler handler;
	private ProtocolCodecFactory codeFactory;
	public void restart() {
		// TODO Auto-generated method stub
		shutdown();
		start();
	}
	public void shutdown() {
		// TODO Auto-generated method stub
		acceptor.dispose();
	}
	public void start() {
		// TODO Auto-generated method stub
		acceptor = new NioSocketAcceptor(); 
		DefaultIoFilterChainBuilder filterChain = acceptor.getFilterChain();
		//�����
		filterChain.addLast("codecfactory", new ProtocolCodecFilter(codeFactory));
		//�̳߳�
		filterChain.addLast("pool", new ExecutorFilter(pool.getThreadPoolExecutor()));
//		filterChain.addLast("logging", new LoggingFilter()); // 添加日志过滤器

		//���ô����߼�
		acceptor.setHandler(handler);
		//���ö{������С
		acceptor.getSessionConfig().setMaxReadBufferSize(maxReadBufferSize);
		
		try {
			if (address == null) {
				acceptor.bind(new InetSocketAddress(port));
			} else {
			acceptor.bind(new InetSocketAddress(address, port));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LogSystem.info("mina server start....");
		LogSystem.info("address" + address + "port = " + port + "maxReadBufferSize" + maxReadBufferSize);
	}
	
	public static void main(String[] args) {
		ThreadPoolBean threadPoolBean = new ThreadPoolBean(10, 20, 10, 10000);
		ProtocolCodecFactory protocolCodecFactory = new CodeFactory();
		MinaHandler hadler = new MinaHandler();
		MinaServer minaServer = new MinaServer();
		minaServer.setCodeFactory(protocolCodecFactory);
		minaServer.setHandler(hadler);
		minaServer.setPool(threadPoolBean);
		minaServer.setMaxReadBufferSize(1024);
		minaServer.setAddress("localhost");
		minaServer.setPort(12369);
		minaServer.start();
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ThreadPoolBean getPool() {
		return pool;
	}
	public void setPool(ThreadPoolBean pool) {
		this.pool = pool;
	}
	public IoHandler getHandler() {
		return handler;
	}

	public void setHandler(IoHandler handler) {
		this.handler = handler;
	}

	public ProtocolCodecFactory getCodeFactory() {
		return codeFactory;
	}

	public void setCodeFactory(ProtocolCodecFactory codeFactory) {
		this.codeFactory = codeFactory;
	}

	public int getMaxReadBufferSize() {
		return maxReadBufferSize;
	}

	public void setMaxReadBufferSize(int maxReadBufferSize) {
		this.maxReadBufferSize = maxReadBufferSize;
	}

}
