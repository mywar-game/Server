package com.fantingame.game.gamecenter.dao.impl.mysql;


import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.gamecenter.dao.DomoUserDao;
import com.fantingame.game.gamecenter.model.DomoUser;

public class DomoUserDaoMysqlImpl implements DomoUserDao {
	@Autowired
	private Jdbc jdbc;
	@Override
	public boolean add(DomoUser domoUser) {
		// TODO Auto-generated method stub
		return this.jdbc.insert(domoUser) > 0;
	}
	
	@Override
	public DomoUser get(String mac,String ifa) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM domo_user where mac = ? and ifa = ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(mac);
		parameter.setString(ifa);
		return this.jdbc.get(sql, DomoUser.class, parameter);
	}
	
	@Override
	public void update(String mac,String ifa,int active,String appVersion,String ip,String userId) {
		// TODO Auto-generated method stub
		String sql = "update domo_user set active = ? , act_time = now() , app_version = ? ,ip = ?, user_id=? where mac=? and ifa=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(active);
		parameter.setString(appVersion);
		parameter.setString(ip);
		parameter.setString(userId);
		parameter.setString(mac);
		parameter.setString(ifa);
	    this.jdbc.update(sql, parameter);
	}

}
