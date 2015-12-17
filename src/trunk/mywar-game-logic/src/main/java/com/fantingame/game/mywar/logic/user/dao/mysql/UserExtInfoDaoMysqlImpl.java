package com.fantingame.game.mywar.logic.user.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.user.dao.UserExtInfoDao;
import com.fantingame.game.mywar.logic.user.model.UserExtInfo;

public class UserExtInfoDaoMysqlImpl implements UserExtInfoDao {
    @Autowired
	private Jdbc jdbcUser;
	
	@Override
	public UserExtInfo getUserExtInfo(String userId) {
		String sql = "select * from user_ext_info where user_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		return jdbcUser.get(sql, UserExtInfo.class, parameter);
	}

	@Override
	public boolean addAllChargMoney(String userId,int addAmount) {
		String sql = "update user_ext_info set all_charge_money=all_charge_money+? where user_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(addAmount);
		parameter.setString(userId);
		return jdbcUser.update(sql, parameter)>0;
	}

	@Override
	public boolean addOnLineTime(String userId,int addSeconds) {
		String sql = "update user_ext_info set all_online_time=all_online_time+? where user_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(addSeconds);
		parameter.setString(userId);
		return jdbcUser.update(sql, parameter)>0;
	}

	@Override
	public boolean addPackExtendTimes(String userId,int addTimes) {
		String sql = "update user_ext_info set pack_extend_times=pack_extend_times+? where user_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(addTimes);
		parameter.setString(userId);
		return jdbcUser.update(sql, parameter)>0;
	}

	@Override
	public boolean addUserExtInfo(UserExtInfo userExtInfo) {
		return jdbcUser.insert(userExtInfo)>0;
	}

	@Override
	public boolean updateEffective(String userId, int newEffective) {
		String sql = "update user_ext_info set effective=? where user_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(newEffective);
		parameter.setString(userId);
		return jdbcUser.update(sql, parameter)>0;
	}

	@Override
	public boolean updatePrePosition(String userId, String prePosition) {
		String sql = "update user_ext_info set pre_position=? where user_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(prePosition);
		parameter.setString(userId);
		return jdbcUser.update(sql, parameter)>0;
	}

	@Override
	public boolean recordGuideStep(String userId, String recordGuideStep, int guideStep) {
		String sql = "update user_ext_info set record_guide_step = ?, guide_step = ? where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(recordGuideStep);
		param.setInt(guideStep);
		param.setString(userId);		
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<UserExtInfo> getUserEffectiveRankList(int limit) {
		String sql = "select * from user_ext_info order by effective desc limit ?";
		SqlParameter param = new SqlParameter();
		param.setInt(limit);
		
		return this.jdbcUser.getList(sql, UserExtInfo.class, param);
	}

	@Override
	public int getUserEffectiveRank(int effective) {
		String sql = "select count(*) from user_ext_info where effective >= ? order by created_time asc";
		SqlParameter param = new SqlParameter();
		param.setInt(effective);
		
		return this.jdbcUser.getInt(sql, param);
	}

	@Override
	public boolean extendStorehouseNum(String userId, int extendNum) {
		String sql = "update user_ext_info set storehouse_extend_times = storehouse_extend_times + ? where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(extendNum);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

}
