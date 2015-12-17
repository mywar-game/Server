package com.fantingame.game.mywar.logic.test.plugin;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.plugin.IAppPlugin;
import com.fantingame.game.mywar.logic.test.dao.TestDaoMysql;

public class TestPlugin implements IAppPlugin {
    @Autowired
	private TestDaoMysql testDaoMysql;
	@Override
	public void startup() throws Exception {
		testDaoMysql.testUser();
		testDaoMysql.testLog();
	}
	@Override
	public void shutdown() throws Exception {

	}
	@Override
	public int cpOrder() {
		return -1;
	}

}
