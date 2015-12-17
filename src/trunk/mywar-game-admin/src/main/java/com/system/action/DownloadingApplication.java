package com.system.action;

import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TDbServer;
import com.system.bo.TGameServer;
import com.system.bo.TSecondaryServer;
import com.system.constant.ServerConstant;
import com.system.service.TDbServerService;
import com.system.service.TGameServerService;
import com.system.service.TSecondaryServerService;
import com.system.util.ReplaceApplicationUtil;

public class DownloadingApplication extends ALDAdminActionSupport implements ModelDriven<TGameServer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TGameServer tgameServer = new TGameServer();
	
	public synchronized  void downLoadingApp() {
		//查询游戏服务器编号
		TGameServerService tgameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		tgameServer = tgameServerService.findOneTGameServer(tgameServer.getServerId());
		if(tgameServer == null){
			return;
		}
		//查询从服务器信息
		TSecondaryServerService secondaryServerService = ServiceCacheFactory.getServiceCache().getService(TSecondaryServerService.class);
		//查询db数据库信息
		TDbServerService dbServerService = ServiceCacheFactory.getServiceCache().getService(TDbServerService.class);
		
		//--------------生成游戏服务器数据--------------
		TSecondaryServer chatServer = secondaryServerService.findOneTSecondaryServer(tgameServer.getChatServerId());
		TSecondaryServer battleServer = secondaryServerService.findOneTSecondaryServer(tgameServer.getBattleServerId());
		TSecondaryServer orderServer = secondaryServerService.findOneTSecondaryServer(tgameServer.getOrderServerId());
		if (chatServer == null || battleServer == null || orderServer == null) {
			return;
		}
		//查询数据库信息
		TDbServer tMongoDBServer =  dbServerService.findOneTDbServer(tgameServer.getMongoServerCode());
		TDbServer tMysqlConfigDBServer =  dbServerService.findOneTDbServer(tgameServer.getConfigDbServerCode());
		TDbServer tMysqlLogDBServer =  dbServerService.findOneTDbServer(tgameServer.getLogDbServerCode());
		TDbServer tMemcacheDBServer =  dbServerService.findOneTDbServer(tgameServer.getCacheDbServerCode());
		//数据库配置信息
		if (tMongoDBServer == null || tMysqlConfigDBServer == null
				|| tMysqlLogDBServer == null || tMemcacheDBServer == null) {
			return;
		}
		//-----生成application
		ReplaceApplicationUtil.generateApplication(ServerConstant.GAME_SERVER_TYPE,tgameServer, chatServer,
				battleServer, orderServer, tMongoDBServer,
				tMysqlConfigDBServer, tMysqlLogDBServer, tMemcacheDBServer);
		//-------------------------------------------------------//
		
		//------生成聊天服务器数据-----------------查询聊天服务器连接数据库信息
		//查询数据库信息
		TDbServer tMysqlConfigDBChatServer =  dbServerService.findOneTDbServer(chatServer.getConfigDbServerCode());
		TDbServer tMysqlLogDBChatServer =  dbServerService.findOneTDbServer(chatServer.getLogDbServerCode());
		TDbServer tMemcacheDBChatServer =  dbServerService.findOneTDbServer(chatServer.getCacheDbServerCode());
		ReplaceApplicationUtil.generateApplication(ServerConstant.CHAT_SERVER_TYPE,tgameServer, chatServer,
				null, null, null,tMysqlConfigDBChatServer, tMysqlLogDBChatServer, tMemcacheDBChatServer);
		//-------------------------------------------------------//
		
		//------------------生成战斗服务器数据-------------------
		TDbServer tMysqlConfigDBBattleServer =  dbServerService.findOneTDbServer(battleServer.getConfigDbServerCode());
		TDbServer tMysqlLogDBChatBattleServer =  dbServerService.findOneTDbServer(battleServer.getLogDbServerCode());
		TDbServer tMemcacheDBBattleServer =  dbServerService.findOneTDbServer(battleServer.getCacheDbServerCode());
		ReplaceApplicationUtil.generateApplication(ServerConstant.BATTLE_SERVER_TYPE,tgameServer, null,
				battleServer, null, null,tMysqlConfigDBBattleServer, tMysqlLogDBChatBattleServer, tMemcacheDBBattleServer);
		//-------------------------------------------------------//
		
		//---------------生成排位赛服务器数据-----------------
		TDbServer tMysqlConfigDBOrderServer =  dbServerService.findOneTDbServer(orderServer.getConfigDbServerCode());
		TDbServer tMysqlLogDBChatOrderServer =  dbServerService.findOneTDbServer(orderServer.getLogDbServerCode());
		TDbServer tMemcacheDBOrderServer =  dbServerService.findOneTDbServer(orderServer.getCacheDbServerCode());
		ReplaceApplicationUtil.generateApplication(ServerConstant.ORDER_SERVER_TYPE,tgameServer, null,
				null, orderServer, null,tMysqlConfigDBOrderServer, tMysqlLogDBChatOrderServer, tMemcacheDBOrderServer);
		//-------------------------------------------------------//
		//确认文件读写完毕
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//生成zip包
		ReplaceApplicationUtil.genetateZip();
		
//		/*******封装文件名*******/
//		fileName = "application.zip";
//		/*******转换编码后才能支持下载文件中存在中文文件名,但是在服务器端看到的将会是乱码类型的文件名************/
////		fileName =  new String(fileName.getBytes(), "ISO8859-1"); 
//		/********获取本地存储文件的路径********/
//		String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes/file");
////		String destFile = path + fileName;
//		/** **********实现下载************ */
//		File file = new File(path + "\\" + fileName);
//		System.out.println(file.getPath());
	}
	
	@Override
	public TGameServer getModel() {
		// TODO Auto-generated method stub
		return tgameServer;
	}

	public TGameServer getTgameServer() {
		return tgameServer;
	}

	public void setTgameServer(TGameServer tgameServer) {
		this.tgameServer = tgameServer;
	}
}
