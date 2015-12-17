package com.fantingame.game.gamecenter.dao.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.gamecenter.dao.NoticeDao;
import com.fantingame.game.gamecenter.model.AmendNotice;
import com.fantingame.game.gamecenter.model.Notice;
import com.fantingame.game.gamecenter.model.SpecialNotice;

public class NoticeDaoMysqlImpl implements NoticeDao {

	@Autowired
	private Jdbc jdbc;

	@Override
	public Notice getNotice(String serverId) {
		String sql = "select * from notice where server_id = ?";
		SqlParameter params = new SqlParameter();
		params.setString(serverId);
		return this.jdbc.get(sql, Notice.class, params);
	}

	@Override
	public AmendNotice getAmendNotice(String serverId, String partnerId) {
		String sql = "select * from amend_notice where server_id = ? and partner_id = ? order by updated_time limit 1";
		SqlParameter param = new SqlParameter();
		param.setString(serverId);
		param.setString(partnerId);
		return this.jdbc.get(sql, AmendNotice.class, param);
	}

	@Override
	public SpecialNotice getSpecialNotice(String partnerId) {
		String sql = "select * from special_notice where partner_id = ? order by updated_time limit 1";
		SqlParameter param = new SqlParameter();
		param.setString(partnerId);
		return this.jdbc.get(sql, SpecialNotice.class, param);
	}

}
