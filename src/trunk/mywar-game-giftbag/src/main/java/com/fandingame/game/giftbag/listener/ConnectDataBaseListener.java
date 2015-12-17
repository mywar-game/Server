package com.fandingame.game.giftbag.listener;

import javax.servlet.ServletContextEvent;

import com.fandingame.game.framework.context.SpringMvcListener;

/**
 * 监听数据库连接
 * 
 * @author yezp
 */
public class ConnectDataBaseListener extends SpringMvcListener {
//	private static Logger LOG = Logger.getLogger(ConnectDataBaseListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
//		ServletContext context = event.getServletContext();
//		// 获取web环境下的ApplicationContext
//		ApplicationContext appContext = WebApplicationContextUtils
//				.getRequiredWebApplicationContext(context);
//
//		try {
//			// 验证mywar_game_common 连接
//			if (!appContext.containsBean("giftCodeDao")) {
//				LOG.error("Giftbag 修改了giftCodeDao.....");
//				System.exit(0);
//			}
//			
//			ActiveCodeDao activeCodeDao = (ActiveCodeDao) appContext
//					.getBean("activeCodeDao");
//			activeCodeDao.getLoginServerBean();
//
//		} catch (Exception e) {
//			LOG.error("Giftbag服务器，连接数据库失败，请检查数据库连接配置是否正确。");
//			System.exit(-1);
//		}
	}



}
