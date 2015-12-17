package com.system.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TDbServer;
import com.system.bo.TGameServer;
import com.system.bo.TSecondaryServer;
import com.system.constant.ServerConstant;
import com.system.service.TDbServerService;
import com.system.service.TGameServerService;

public class AddTGameServer extends ALDAdminActionSupport implements
		ModelDriven<TGameServer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7625543941895179523L;

	private TGameServer tgameServer = new TGameServer();

	private String isCommit = "F";

	private List<TDbServer> tdbServersForLog = new ArrayList<TDbServer>();

	private List<TDbServer> tdbServersForConfig = new ArrayList<TDbServer>();

	private List<TDbServer> tdbServersForUser = new ArrayList<TDbServer>();
	
	private List<TDbServer> tdbServersForCache = new ArrayList<TDbServer>();

	private List<TDbServer> tdbServersForMongo = new ArrayList<TDbServer>();

	private List<TSecondaryServer> battleServerList = new ArrayList<TSecondaryServer>();

	private List<TSecondaryServer> chatServerList = new ArrayList<TSecondaryServer>();

	private List<TSecondaryServer> orderServerList = new ArrayList<TSecondaryServer>();
	private String gameWebServerId;

	public String execute() {
		TGameServerService gameServerService = ServiceCacheFactory
				.getServiceCache().getService(TGameServerService.class);
		if ("F".equals(this.isCommit)) {
			TGameServer tgameServer1 = gameServerService.findLastTGameServer();
			// 设置属性默认值
			if (tgameServer1 == null) {
				tgameServer
						.setServerId(ServerConstant.GAME_SERVER_BEGIN_NUMBER);
			} else {
				// 系统编号累加1
				tgameServer.setServerId(tgameServer1.getServerId() + 1);
			}
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(ServerConstant.GAME_SERVER_NAME)
					.append(ServerConstant.CONNECT)
					.append(tgameServer.getServerId());
			tgameServer.setServerName(new String(stringBuffer));
			tgameServer.setServerAlias(tgameServer.getServerName());
			tgameServer.setServerPoint(ServerConstant.GAME_SERVER_PORT);
			tgameServer.setWebOpenPort(ServerConstant.GAME_SERVER_WEB_PORT);

			// 设置查询db数值服务器
			TDbServerService tdbServerService = ServiceCacheFactory
					.getServiceCache().getService(TDbServerService.class);
			List<Object> obects = tdbServerService.findAllDBServer();
			if (obects != null && obects.size() > 0) {
				for (Object object : obects) {
					Object[] objs = (Object[]) object;
					TDbServer dbServer = new TDbServer();
					dbServer.setDbServerId(Integer.parseInt(objs[0].toString()));
					int type = Integer.parseInt(objs[1].toString());
					dbServer.setServerName(objs[2].toString());
					dbServer.setServerIp(objs[3].toString());
					// 加入对应列表信息--1.表示mysq配置l数据库 2.mysq日志l数据库 3.用户数据库 4.缓存数据库5.mongo数据库'
					switch (type) {
					case 1:
						tdbServersForConfig.add(dbServer);
						break;
					case 2:
						tdbServersForLog.add(dbServer);
						break;
					case 3:
						tdbServersForUser.add(dbServer);
						break;
					case 4:
						tdbServersForCache.add(dbServer);
						break;
					case 5:
						tdbServersForMongo.add(dbServer);
						break;
					}
				}

			}

			// 设置战斗,聊天,排位赛服务器
			// TSecondaryServerService tsecondaryServerService =
			// ServiceCacheFactory.getServiceCache().getService(TSecondaryServerService.class);
			// List<Object> serverObects =
			// tsecondaryServerService.findAllServerList();
			// if(serverObects != null && serverObects.size() > 0){
			// for (Object object : serverObects) {
			// Object [] objs = (Object [])object;
			// TSecondaryServer tsecondaryServer = new TSecondaryServer();
			// tsecondaryServer.setServerId(Integer.parseInt(objs[0].toString()));
			// int type = Integer.parseInt(objs[1].toString());
			// tsecondaryServer.setServerName(objs[2].toString());
			// tsecondaryServer.setServerIp(objs[3].toString());
			// //加入对应列表信息--1聊天服务器 2战斗服务器 3排位赛服务器
			// switch(type){
			// case 1:
			// chatServerList.add(tsecondaryServer);
			// break;
			// case 2:
			// battleServerList.add(tsecondaryServer);
			// break;
			// case 3:
			// orderServerList.add(tsecondaryServer);
			// break;
			// }
			// }
			// }

			return INPUT;
		} else {
			TDbServerService service = ServiceCacheFactory.getServiceCache()
					.getService(TDbServerService.class);
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(ServerConstant.GAME_SERVER_NAME)
					.append(ServerConstant.CONNECT)
					.append(tgameServer.getServerId());
			tgameServer.setServerName(new String(stringBuffer));
			tgameServer.setLoginEncryption("6789123456abc");
			tgameServer.setTimeZoneId("GMT+8");
			TDbServer dbServer = service.findOneTDbServer(tgameServer
					.getLogDbServerCode());
			if (dbServer == null) {
				super.setErroDescrip("日志库服务器不存在");
				return INPUT;
			}
			// 检查日志库是否连通的
			Connection con = null; // 创建用于连接数据库的Connection对象
			Statement st = null;
			try {
				// 加载MySql的驱动类
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://" + dbServer.getServerIp() + ":"
						+ dbServer.getServerPort() + "/" + dbServer.getDbName();
				con = DriverManager.getConnection(url, dbServer.getUserName(),
						dbServer.getPassword());// 创建数据连接
				st = con.createStatement();
				st.executeQuery("select count(*) from user");// 验证数据库是否通
				con.close();
			} catch (SQLException e) {
				super.setErroDescrip("连接日志库失败,请查看日志库是否设置正确！");
				return INPUT;
			} catch (ClassNotFoundException e) {
				super.setErroDescrip("找不到驱动程序类 ，加载驱动失败！");
				return INPUT;
			}
			tgameServer.setServerOpernTime(new Timestamp(new Date().getTime()));
			gameServerService.addOneTGameServer(tgameServer);

			// 记录日志
			AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
			adminChangeConstantLog.setSysNum(0);
			adminChangeConstantLog.setAdminName(super.getAdminUser()
					.getAdminName());
			adminChangeConstantLog.setChangeTime(new Timestamp(System
					.currentTimeMillis()));
			adminChangeConstantLog.setConstantName("AddTGameServer");
			adminChangeConstantLog.setChangeType(1);
			adminChangeConstantLog.setChangeDetail("添加");
			AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
					.getServiceCache().getService(
							AdminChangeConstantLogService.class);
			adminChangeConstantLogService.saveOne(adminChangeConstantLog);
			return SUCCESS;
		}
	}

	public List<TDbServer> getTdbServersForLog() {
		return tdbServersForLog;
	}

	public void setTdbServersForLog(List<TDbServer> tdbServersForLog) {
		this.tdbServersForLog = tdbServersForLog;
	}

	public List<TDbServer> getTdbServersForUser() {
		return tdbServersForUser;
	}

	public void setTdbServersForUser(List<TDbServer> tdbServersForUser) {
		this.tdbServersForUser = tdbServersForUser;
	}

	public List<TDbServer> getTdbServersForConfig() {
		return tdbServersForConfig;
	}

	public void setTdbServersForConfig(List<TDbServer> tdbServersForConfig) {
		this.tdbServersForConfig = tdbServersForConfig;
	}

	public List<TDbServer> getTdbServersForCache() {
		return tdbServersForCache;
	}

	public void setTdbServersForCache(List<TDbServer> tdbServersForCache) {
		this.tdbServersForCache = tdbServersForCache;
	}

	public List<TDbServer> getTdbServersForMongo() {
		return tdbServersForMongo;
	}

	public void setTdbServersForMongo(List<TDbServer> tdbServersForMongo) {
		this.tdbServersForMongo = tdbServersForMongo;
	}

	public TGameServer getTgameServer() {
		return tgameServer;
	}

	public void setTgameServer(TGameServer tgameServer) {
		this.tgameServer = tgameServer;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public List<TSecondaryServer> getBattleServerList() {
		return battleServerList;
	}

	public void setBattleServerList(List<TSecondaryServer> battleServerList) {
		this.battleServerList = battleServerList;
	}

	public List<TSecondaryServer> getChatServerList() {
		return chatServerList;
	}

	public void setChatServerList(List<TSecondaryServer> chatServerList) {
		this.chatServerList = chatServerList;
	}

	public List<TSecondaryServer> getOrderServerList() {
		return orderServerList;
	}

	public void setOrderServerList(List<TSecondaryServer> orderServerList) {
		this.orderServerList = orderServerList;
	}

	public String getGameWebServerId() {
		return gameWebServerId;
	}

	public void setGameWebServerId(String gameWebServerId) {
		this.gameWebServerId = gameWebServerId;
	}

	@Override
	public TGameServer getModel() {
		// TODO Auto-generated method stub
		return tgameServer;
	}

}
