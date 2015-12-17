package com.framework.server.manager;

import java.util.List;

import com.framework.plugin.IAppPlugin;
/**
 * 服务器管理
 * @author mengc
 *
 */
public class ServerManager implements IAppPlugin {
    //服务器列表
	private List<IServer> serverList;

	public List<IServer> getServerList() {
		return serverList;
	}

	public void setServerList(List<IServer> serverList) {
		this.serverList = serverList;
	}
	/**
	 * 启动所有服务
	 */
	public void startAllServer() {
		for (IServer server:serverList) {
			server.start();
		}
	}
	/**
	 * 关闭所有服务
	 */
	public void shutDownAllServer() {
		for (IServer server:serverList) {
			server.shutdown();
		}
	}
	/**
	 * 重启所有服务
	 */
	public void restartAllServer() {
		for (IServer server:serverList) {
			server.restart();
		}
	}

	public void shutdown() {
		// TODO Auto-generated method stub
		shutDownAllServer();
	}

	public void startup() {
		// TODO Auto-generated method stub
		startAllServer();
	}
}
