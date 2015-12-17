package com.fantingame.game.mywar.logic;

import com.fandingame.game.framework.context.SpringLoad;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.plugin.AppPluginFactory;


public class LogicRun {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringLoad.getApplicationLoad();
		// 启动各个应用插件
		AppPluginFactory.startup();
		LogSystem.info("服务器启动成功");
	}

}
