package com.fantingame.game.mywar.logic.test.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.game.mywar.logic.test.model.Test;
import com.google.common.base.Preconditions;

public class TestDaoMysql {
    @Autowired
	private Jdbc jdbcUser;
    @Autowired
    private Jdbc jdbcLog;
    
    
    public void testUser(){
    	this.jdbcUser.update("delete from test",null);
    	Test test = new Test();
    	Preconditions.checkArgument(this.jdbcUser.insert(test)>0,"插入user表失败，请检查数据库连接或权限");
    	Preconditions.checkArgument(this.jdbcUser.update("update test set id=1 limit 1", null)>0,"更新user表失败，请检查数据库连接或权限");
    	Preconditions.checkArgument(this.jdbcUser.get("select * from test where id=1 limit 1", Test.class, null)!=null,"查询user表失败，请检查数据库连接或权限");
    }
    
    public void testLog(){
    	this.jdbcLog.update("delete from test",null);
    	Test test = new Test();
    	Preconditions.checkArgument(this.jdbcLog.insert(test)>0,"插入log表失败，请检查数据库连接或权限");
    	Preconditions.checkArgument(this.jdbcLog.update("update test set id=1 limit 1", null)>0,"更新log表失败，请检查数据库连接或权限");
    	Preconditions.checkArgument(this.jdbcLog.get("select * from test where id=1 limit 1", Test.class, null)!=null,"查询log表失败，请检查数据库连接或权限");
    }
}
