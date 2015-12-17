package com.adminTool.scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.adminTool.service.UserService;
import com.framework.log.LogSystem;
import com.framework.scheduler.SchedulerEntry;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;

public class CollectUserDataForPlatform extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		LogSystem.info("采集用户信息给平台Collector开始");
		long x = System.currentTimeMillis();
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		
		try {
//			Class.forName("com.mysql.jdbc.Driver");  
////			Connection conn = DriverManager.getConnection("jdbc:mysql://23.21.158.186:3306/wsg_stats?rewriteBatchedStatements=true&characterEncoding=UTF8", "omprus_stats", "wsgame!@#");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://120.197.137.5:3306/zgame_log?rewriteBatchedStatements=true&characterEncoding=UTF8", "zgame", "123456");
//			conn.setAutoCommit(false);
//			
//			List<TGameServer> gameServerList =  gameServerService.findTGameServerList();
//			int a = 0;
//			for (int i = 0; i < gameServerList.size(); i++) {
//				TGameServer gameServer = gameServerList.get(i);
//				if ("GMT-7".equals(gameServer.getTimeZoneId())) {
//					//先删除原来的数据
//					Statement statement = conn.createStatement();
//					statement.executeUpdate("DELETE FROM `player_zs_s" + (a+1)+ "`");
//					conn.commit();
//					statement.close();
//					
//					//插入最新的数据
//					String inertSql = "insert into player_zs_s" + (a+1)+ "(server_code,user_account,user_role,level,total_played,total_money,create_role_time,last_login_time,last_login_ip) VALUES(?,?,?,?,?,?,?,?,?)";  
//					PreparedStatement prest = conn.prepareStatement(inertSql);
//					CustomerContextHolder.setSystemNum(gameServer.getServerId());
//					userService.collectUserSomeInfo(prest, a);
//					prest.executeBatch();
//					conn.commit();  
//					a++;
//				}
//			}
////			for (int i = 0; i < gameServerList.size(); i++) {
////				TGameServer gameServer = gameServerList.get(i);
////				CustomerContextHolder.setSystemNum(gameServer.getServerId());
////				userService.collectUserSomeInfo(prest, i);
////			}
//			conn.close();  
		} catch (Exception e) {
			LogSystem.error(e, e.getMessage());
		}
		LogSystem.info("采集用户信息给平台Collector完毕,用时："+(System.currentTimeMillis() - x));
	}

}
