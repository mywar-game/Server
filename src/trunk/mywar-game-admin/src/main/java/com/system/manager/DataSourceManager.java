package com.system.manager;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.jetty.util.log.Log;
import org.logicalcobwebs.proxool.ConnectionPoolManager;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.logicalcobwebs.proxool.ProxoolException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.framework.common.DBSource;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.DynamicDataSource;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.servicemanager.SessionFactoryAutoInsertDao;
import com.framework.util.DateUtil;
import com.system.bo.GameWeb;
import com.system.bo.TDbServer;
import com.system.bo.TGameServer;
import com.system.constant.ServerConstant;
import com.system.dao.TDbServerDao;
import com.system.dao.TGameServerDao;
import com.system.service.GameWebService;

/**
 * 数据源管理器 有游戏服务器连接信息管理 和 配置常量数据库数据源管理
 * 
 * @author ws
 * 
 */
public class DataSourceManager {
	private static DataSourceManager manager;

	private Map<Integer, TGameServer> gameServerMap = new LinkedHashMap<Integer, TGameServer>();

	private Map<Integer, TDbServer> dbServerMap = new HashMap<Integer, TDbServer>();

	private DataSourceManager() {
	}

	public static DataSourceManager getInstatnce() {
		if (manager == null) {
			manager = new DataSourceManager();
		}
		return manager;
	}

	/**
	 * 判断是否存在某个表
	 * 
	 * @param tableName
	 * @return
	 */
	public boolean containsTable(Date time) {
		TGameServer server = gameServerMap.get(CustomerContextHolder
				.getSystemNum());
		Date startDate = DateUtil.getZeroTime(server.getServerOpernTime());// 开服时间的零点时间
		Date endTime = DateUtil.getZeroTime(new Date());// 当天的零点时间
		if (time.getTime() >= startDate.getTime()
				&& time.getTime() <= endTime.getTime()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获得组合好的sql查询语句包括当天的表
	 * 
	 * @param tableName
	 * @param arr
	 * @return
	 */
	public String getUnionSqlCurTable(String tableName, Date[] arr,
			StringBuffer queryStr, String items) {
		StringBuffer sql = new StringBuffer();

		sql.append("(select ");
		sql.append(items);
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where 1=1");
		if (queryStr.length() != 0) {
			sql.append(queryStr.toString());
		}
		sql.append(")");

		for (int i = 0; i < arr.length; i++) {
			String table = tableName + "_"
					+ DateUtil.dateToString(arr[i], DateUtil.FORMAT_FOUR);
			if (containsTable(arr[i])) {
				sql.append(" union all (select ");
				sql.append(items);
				sql.append(" from ");
				sql.append(table);
				sql.append(" where 1=1");
				if (queryStr.length() != 0) {
					sql.append(queryStr.toString());
				}
				sql.append(")");

			}
		}
		return sql.toString();
	}

	/**
	 * 获得组合好的sql查询语句
	 * 
	 * @param tableName
	 * @param arr
	 * @return
	 */
	public String getUnionSql(String tableName, Date[] arr,
			StringBuffer queryStr, String items) {
		StringBuffer sql = new StringBuffer();
		boolean isFirst = true;
		for (int i = 0; i < arr.length; i++) {
			String table = tableName + "_"
					+ DateUtil.dateToString(arr[i], DateUtil.FORMAT_FOUR);
			if (containsTable(arr[i])) {
				if (isFirst) {
					sql.append("(select ");
					sql.append(items);
					sql.append(" from ");
					sql.append(table);
					sql.append(" where 1=1");
					if (queryStr.length() != 0) {
						sql.append(queryStr.toString());
					}
					sql.append(")");
					isFirst = false;
				} else {
					sql.append(" union all (select ");
					sql.append(items);
					sql.append(" from ");
					sql.append(table);
					sql.append(" where 1=1");
					if (queryStr.length() != 0) {
						sql.append(queryStr.toString());
					}
					sql.append(")");
				}
			}
		}
		return sql.toString();
	}

	/**
	 * 采集数据时当天要查询的表名索引
	 * 
	 * @param dates
	 * @return
	 */
	public String getTableIndex(String tableName, String[] dates) {
		Date[] arr = DateUtil.dayStrDiff(
				DateUtil.stringtoDate(dates[0], DateUtil.FORMAT_ONE),
				DateUtil.stringtoDate(dates[1], DateUtil.FORMAT_ONE));
		if (containsTable(arr[0])) {
			return tableName + "_"
					+ DateUtil.dateToString(arr[0], DateUtil.FORMAT_FOUR);
		} else {
			return tableName;
		}
	}

	/**
	 * 初始化数据源 将各个服务器的数据源统一初始化 key规则 -11001 -1代表的是用途 1001代表的服务器的系统编号
	 */
	public void initDataSource() {
		initServerConfigMap();
		// 重新构造数据源
		Map<Integer, DataSource> dataSourceMap = getAllDataSourceMap();
		// 实例化数据源
		DynamicDataSource newDynamicDataSource = new DynamicDataSource();
		newDynamicDataSource.setTargetDataSources(dataSourceMap);
		newDynamicDataSource.setDataSourceCount(dataSourceMap.size());
		newDynamicDataSource.setDefaultTargetDataSource(dataSourceMap
				.get(DBSource.ADMIN));

		newDynamicDataSource.afterPropertiesSet();

		org.springframework.orm.hibernate3.LocalSessionFactoryBean lsessionFactory = ServiceCacheFactory
				.getServiceCache()
				.getService(
						org.springframework.orm.hibernate3.LocalSessionFactoryBean.class);
		// 实例化Spring Session
		org.springframework.orm.hibernate3.LocalSessionFactoryBean newLocalSessionFactoryBean = new org.springframework.orm.hibernate3.LocalSessionFactoryBean();
		newLocalSessionFactoryBean.setHibernateProperties(lsessionFactory
				.getHibernateProperties());
		newLocalSessionFactoryBean.setDataSource(newDynamicDataSource);
		// newLocalSessionFactoryBean.setLobHandler(ServiceCacheFactory.getServiceCache().getService(org.springframework.jdbc.support.lob.DefaultLobHandler.class));

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = null;
		try {
			resources = resolver
					.getResources("classpath*:com/**/bean_config/*.hbm.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		}
		newLocalSessionFactoryBean.setMappingLocations(resources);
		try {
			newLocalSessionFactoryBean.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		}
		org.hibernate.SessionFactory sessionFactory = (org.hibernate.SessionFactory) newLocalSessionFactoryBean
				.getObject();

		SessionFactoryAutoInsertDao autoInsertDao = ServiceCacheFactory
				.getServiceCache()
				.getService(SessionFactoryAutoInsertDao.class);

		// 重新设置所有DAO的数据源
		List<HibernateDaoSupport> listDao = autoInsertDao.getList();
		if (listDao != null && listDao.size() > 0) {
			for (HibernateDaoSupport support : listDao) {
				support.setSessionFactory(sessionFactory);
			}
		}
		LogSystem.info("刷新数据源完成");
	}

	/**
	 * 根据s_server_config服务器配置表 获得所有数据源
	 * 
	 * @return
	 */
	private Map<Integer, DataSource> getAllDataSourceMap() {
		Map<Integer, DataSource> dataSourceMap = new HashMap<Integer, DataSource>();
		ProxoolDataSource dataSourceadmin = (org.logicalcobwebs.proxool.ProxoolDataSource) ServiceCacheFactory
				.getServiceCache().getBeanById("dataSourceAdmin");
		dataSourceMap.put(DBSource.ADMIN, dataSourceadmin);

		try {
			ConnectionPoolManager.getInstance()
					.getConnectionPool(dataSourceadmin.getAlias())
					.shutdown(1, "shutdownit");
			ConnectionPoolManager.getInstance().removeConnectionPool(
					dataSourceadmin.getAlias());
		} catch (ProxoolException e) {

		} catch (Throwable e) {
		}

		// 动态加载game_web的数据源
		GameWebService gameWebService = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		List<GameWeb> list = gameWebService.findGameWebList();

		for (GameWeb gameWeb : list) {
			ProxoolDataSource source = new ProxoolDataSource();
			source.setAlias("gameWeb" + gameWeb.getServerId());
			source.setDriver("com.mysql.jdbc.Driver");
			source.setDriverUrl("jdbc:mysql://" + gameWeb.getDbIp() + ":"
					+ gameWeb.getDbPort() + "/" + gameWeb.getDbName()
					+ "?user=" + gameWeb.getUserName() + "&password="
					+ gameWeb.getPassword() + "&characterEncoding=UTF-8");

			source.setUser(gameWeb.getUserName());
			source.setPassword(gameWeb.getPassword());
			setParam(source);

			dataSourceMap.put(gameWeb.getServerId(), source);
			try {
				ConnectionPoolManager.getInstance()
						.getConnectionPool(source.getAlias())
						.shutdown(1, "shutdownit");
				ConnectionPoolManager.getInstance().removeConnectionPool(
						source.getAlias());
			} catch (ProxoolException e) {
				LogSystem.error(e, "");
			} catch (Throwable e) {
				e.fillInStackTrace();
			}

			ProxoolDataSource giftSource = new ProxoolDataSource();
			giftSource.setAlias("gift" + gameWeb.getServerId());
			giftSource.setDriver("com.mysql.jdbc.Driver");
			giftSource.setDriverUrl("jdbc:mysql://" + gameWeb.getGiftDbIp()
					+ ":" + gameWeb.getGiftDbPort() + "/"
					+ gameWeb.getGiftDbName() + "?user="
					+ gameWeb.getGiftUserName() + "&password="
					+ gameWeb.getGiftPassword() + "&characterEncoding=UTF-8");
			giftSource.setUser(gameWeb.getGiftUserName());
			giftSource.setPassword(gameWeb.getGiftPassword());
			setParam(giftSource);

			dataSourceMap.put(gameWeb.getServerId()
					+ ServerConstant.GIFT_DBKEY_BEGIN_NUMBER, giftSource);
			try {
				ConnectionPoolManager.getInstance()
						.getConnectionPool(giftSource.getAlias())
						.shutdown(1, "shutdownit");
				ConnectionPoolManager.getInstance().removeConnectionPool(
						giftSource.getAlias());
			} catch (ProxoolException e) {
			} catch (Throwable e) {
			}

		}

		for (TGameServer gameServer : gameServerMap.values()) {
			TDbServer configDbServer = dbServerMap.get(gameServer
					.getConfigDbServerCode());
			TDbServer logDbServer = dbServerMap.get(gameServer
					.getLogDbServerCode());
			TDbServer userDbServer = dbServerMap.get(gameServer
					.getUserDbServerCode());

			ProxoolDataSource dataSourceCfg = new ProxoolDataSource();
			dataSourceCfg.setAlias("cfgPool" + gameServer.getServerId());
			dataSourceCfg.setDriver("com.mysql.jdbc.Driver");
			if (configDbServer == null) {
				// 测试看看哪个为空了
				if (gameServer != null) {
					Log.info("---------------" + "gameServer" + " " + gameServer.getServerAlias() + "---------------------");
				} 
				continue;
			}
			dataSourceCfg
					.setDriverUrl("jdbc:mysql://"
							+ configDbServer.getServerIp() + ":"
							+ configDbServer.getServerPort() + "/"
							+ configDbServer.getDbName() + "?user="
							+ configDbServer.getUserName() + "&password="
							+ configDbServer.getPassword()
							+ "&characterEncoding=UTF-8");
			dataSourceCfg.setUser(configDbServer.getUserName());
			dataSourceCfg.setPassword(configDbServer.getPassword());
			setParam(dataSourceCfg);

			String cfgKey = DBSource.CFG + "" + gameServer.getServerId();
			dataSourceMap.put(Integer.valueOf(cfgKey), dataSourceCfg);
			Log.info("----------------------------" + dataSourceMap.size() + "-----------------------------");
			for (int key : dataSourceMap.keySet()) {
				DataSource  source = dataSourceMap.get(key);
				Log.info("source " + key + " :" + source.toString());
			}
			try {
				ConnectionPoolManager.getInstance()
						.getConnectionPool(dataSourceCfg.getAlias())
						.shutdown(1, "shutdownit");
				ConnectionPoolManager.getInstance().removeConnectionPool(
						dataSourceCfg.getAlias());
			} catch (ProxoolException e) {

			} catch (Throwable e) {
			}

			ProxoolDataSource dataSourceLog = new ProxoolDataSource();
			dataSourceLog.setAlias("logPool" + gameServer.getServerId());
			dataSourceLog.setDriver("com.mysql.jdbc.Driver");
			dataSourceLog.setDriverUrl("jdbc:mysql://"
					+ logDbServer.getServerIp() + ":"
					+ logDbServer.getServerPort() + "/"
					+ logDbServer.getDbName() + "?user="
					+ logDbServer.getUserName() + "&password="
					+ logDbServer.getPassword() + "&characterEncoding=UTF-8");
			dataSourceLog.setUser(logDbServer.getUserName());
			dataSourceLog.setPassword(logDbServer.getPassword());
			setParam(dataSourceLog);

			String logKey = DBSource.LOG + "" + gameServer.getServerId();
			dataSourceMap.put(Integer.valueOf(logKey), dataSourceLog);
			
			try {
				ConnectionPoolManager.getInstance()
						.getConnectionPool(dataSourceLog.getAlias())
						.shutdown(1, "shutdownit");
				ConnectionPoolManager.getInstance().removeConnectionPool(
						dataSourceLog.getAlias());
			} catch (ProxoolException e) {
			} catch (Throwable e) {
			}
			
			ProxoolDataSource dataSourceUser = new ProxoolDataSource();
			dataSourceUser.setAlias("userPool" + gameServer.getServerId());
			dataSourceUser.setDriver("com.mysql.jdbc.Driver");
			dataSourceUser.setDriverUrl("jdbc:mysql://"
					+ userDbServer.getServerIp() + ":"
					+ userDbServer.getServerPort() + "/"
					+ userDbServer.getDbName() + "?user="
					+ userDbServer.getUserName() + "&password="
					+ userDbServer.getPassword() + "&characterEncoding=UTF-8");
			dataSourceUser.setUser(userDbServer.getUserName());
			dataSourceUser.setPassword(userDbServer.getPassword());
			setParam(dataSourceUser);

			String userKey = DBSource.USER + "" + gameServer.getServerId();
			dataSourceMap.put(Integer.valueOf(userKey), dataSourceUser);
			
			try {
				ConnectionPoolManager.getInstance()
						.getConnectionPool(dataSourceUser.getAlias())
						.shutdown(1, "shutdownit");
				ConnectionPoolManager.getInstance().removeConnectionPool(
						dataSourceUser.getAlias());
			} catch (ProxoolException e) {
			} catch (Throwable e) {
			}
		}
		
		return dataSourceMap;
	}

	/**
	 * 设置一些活动参数
	 * 
	 * @param dataSource
	 */
	private void setParam(ProxoolDataSource dataSource) {
		dataSource.setMaximumActiveTime(600000);// 连接保持10分钟，如果sql还没执行完肯定是有问题
		dataSource.setMaximumConnectionCount(5);
		dataSource.setMinimumConnectionCount(1);
		dataSource.setSimultaneousBuildThrottle(1); // 这是我们可一次建立的最大连接数。那就是新增的连接请求,但还没有可供使用的连接。由于连接可以使用多线程,
													// //在有限的时间之间建立联系从而带来可用连接，但是我们需要通过一些方式确认一些线程并不是立即响应连接请求的，默认是10
		dataSource.setTestBeforeUse(true);
		dataSource.setHouseKeepingTestSql("select CURRENT_DATE");
	}

	/**
	 * 初始化服务器配置
	 */
	private void initServerConfigMap() {
		TGameServerDao gameServerDao = ServiceCacheFactory.getServiceCache()
				.getService(TGameServerDao.class);
		TDbServerDao dbServerDao = ServiceCacheFactory.getServiceCache()
				.getService(TDbServerDao.class);
		CustomerContextHolder.setCustomerType(DBSource.ADMIN);
		List<TGameServer> gameList = gameServerDao.findAll();
		List<TDbServer> dbList = dbServerDao.findAll();
		gameServerMap.clear();
		dbServerMap.clear();
		// 初始化游戏服务器
		for (TGameServer gameServer : gameList) {
			gameServerMap.put(gameServer.getServerId(), gameServer);
		}
		// 初始化数据库服务器
		for (TDbServer dbServer : dbList) {
			dbServerMap.put(dbServer.getDbServerId(), dbServer);
		}
	}

	/**
	 * @return the gameServerMap
	 */
	public Map<Integer, TGameServer> getGameServerMap() {
		return gameServerMap;
	}

	// 更新服务器缓存配置
	public void updateGameServerMap(TGameServer tGameServer) {
		if (gameServerMap.containsKey(tGameServer.getServerId())) {
			gameServerMap.put(tGameServer.getServerId(), tGameServer);
		}
	}

}
