package com.fantingame.game.mywar.logic.scene.dao.mysql;

import java.util.List;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.scene.model.UserScene;

import org.springframework.beans.factory.annotation.Autowired;

public class UserSceneDaoMysql {
	@Autowired
	private Jdbc jdbcUser;

	public List<UserScene> getUserSceneList(String userId) {
		String sql = "select * from user_scene where user_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		return this.jdbcUser.getList(sql, UserScene.class, parameter);
	}

	public boolean addUserScene(UserScene userScene) {
		return this.jdbcUser.insert(userScene) > 0;
	}
}
