package com.fantingame.game.mywar.logic.task.dao.mysql;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.game.mywar.logic.task.model.SystemTask;

public class SystemTaskDaoMysql{
    @Autowired
	private Jdbc jdbcConfig;
	
	public List<SystemTask> getAll(){
		String sql = "select * from system_task";
		return jdbcConfig.getList(sql, SystemTask.class);
	}

}
