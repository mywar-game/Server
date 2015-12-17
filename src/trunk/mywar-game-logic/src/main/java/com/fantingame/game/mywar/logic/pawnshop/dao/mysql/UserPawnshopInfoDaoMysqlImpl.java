package com.fantingame.game.mywar.logic.pawnshop.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.pawnshop.dao.UserPawnshopInfoDao;
import com.fantingame.game.mywar.logic.pawnshop.model.UserPawnshopInfo;

public class UserPawnshopInfoDaoMysqlImpl implements UserPawnshopInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private static final String table = "user_pawnshop_info";
	
	/**
	 * 获取阵营当铺信息
	 * 
	 * @param camp
	 * @return
	 */
	public List<UserPawnshopInfo> getUserPawnshopInfoList(int camp) {
		String sql = "select * from " + table + " where camp = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(camp);
		
		return this.jdbcUser.getList(sql, UserPawnshopInfo.class, param);
	}
	
	/**
	 * 获取当铺信息
	 * 
	 * @param camp
	 * @param mallId
	 * @return
	 */
	public UserPawnshopInfo getUserPawnshopInfo(int camp, int mallId) {
		String sql = "select * from " + table + " where camp = ? and mall_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(camp);
		param.setInt(mallId);
		
		return this.jdbcUser.get(sql, UserPawnshopInfo.class, param);
	}
	
	/**
	 * 更新当铺信息
	 * 
	 * @param userPawnshopInfo
	 * @return
	 */
	public boolean updateUserPawnshopInfo(int camp, int mallId, int num) {
		String sql = "update " + table + " set num = ?, updated_time = now() where camp = ? and mall_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(num);
		param.setInt(camp);
		param.setInt(mallId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
	/**
	 * 批量添加
	 * 
	 * @param list
	 * @return
	 */
	public boolean add(int camp, List<UserPawnshopInfo> list) {
		return this.jdbcUser.insert(list).length > 0;
	}
}
