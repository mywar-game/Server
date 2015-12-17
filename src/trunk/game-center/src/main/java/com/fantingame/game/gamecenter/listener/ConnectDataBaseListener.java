package com.fantingame.game.gamecenter.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fandingame.game.framework.context.SpringMvcListener;
import com.fantingame.game.gamecenter.dao.ServerListDao;

/**
 * 监听数据库连接
 * 
 * @author yezp
 */
public class ConnectDataBaseListener extends SpringMvcListener {
	private static Logger LOG = Logger.getLogger(ConnectDataBaseListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServletContext context = event.getServletContext();
		// 获取web环境下的ApplicationContext
		ApplicationContext appContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);

		try {
			// 验证hw_game_center 连接
			if (!appContext.containsBean("serverListDao")) {
				LOG.error("GameCenter 修改了serverListDao.....");
				System.exit(0);
			}
			ServerListDao serverListDao = (ServerListDao) appContext.getBean("serverListDao");
			serverListDao.getServerListByPartenerId("1001");

		} catch (Exception e) {
			LOG.error("GameCenter服务器，连接数据库失败，请检查数据库连接配置是否正确。");
			System.exit(0);
		}
	}

}
