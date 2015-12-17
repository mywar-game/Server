package com.fantingame.game.gamecenter.dao.impl.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.gamecenter.dao.PartnerRateDao;
import com.fantingame.game.gamecenter.model.PartnerRate;

public class PartnerRateDaoMysqlImpl implements PartnerRateDao {

	@Autowired
	private Jdbc jdbc;
	
	public final static String table = "partner_rate";	
	
	@Override
	public void reload() {
	}

	@Override
	public List<PartnerRate> getAll() {
		String sql = "select * from " + table;
		return this.jdbc.getList(sql, PartnerRate.class);
	}

	@Override
	public int getRate(String partnerId) {
		String sql = "select rate from " + table + " where partner_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(partnerId);
		
		return this.jdbc.getInt(sql, param);
	}

}
