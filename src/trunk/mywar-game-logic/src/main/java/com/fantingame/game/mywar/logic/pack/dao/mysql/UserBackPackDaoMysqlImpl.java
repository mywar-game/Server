package com.fantingame.game.mywar.logic.pack.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.pack.dao.UserBackPackDao;
import com.fantingame.game.mywar.logic.pack.model.UserBackPack;

public class UserBackPackDaoMysqlImpl implements UserBackPackDao {
    @Autowired
	private Jdbc jdbcUser;
	@Override
	public List<UserBackPack> getPackGoodsList(String userId) {
		String sql = "select * from user_back_pack where user_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		return jdbcUser.getList(sql, UserBackPack.class, parameter);
	}

	@Override
	public boolean addUserBackPackGoods(UserBackPack userBackPack) {
		return jdbcUser.insert(userBackPack)>0;
	}
	@Override
	public boolean deleteBackPackGoods(String userId,int userBackPackId) {
		String sql = "delete from user_back_pack where user_back_pack_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(userBackPackId);
		return jdbcUser.update(sql, parameter)>0;
	}

	@Override
	public boolean addUserBackPackGoodsList(String userId,
			List<UserBackPack> userBackPack) {
		jdbcUser.insert(userBackPack);
		return true;
	}

	@Override
	public boolean updateUserGoodsPos(String userId, int userBackPackId, int pos) {
		String sql = "update user_back_pack set pos=?,updated_time=now()  where user_back_pack_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(pos);
		parameter.setInt(userBackPackId);
		return jdbcUser.update(sql, parameter)>0;
	}

}
