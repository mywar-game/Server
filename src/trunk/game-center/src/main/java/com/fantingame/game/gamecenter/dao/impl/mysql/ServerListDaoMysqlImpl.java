package com.fantingame.game.gamecenter.dao.impl.mysql;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.gamecenter.dao.ServerListDao;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.model.ServerConfig;
import com.fantingame.game.gamecenter.model.ServerListConfig;
import com.fantingame.common.jdbc.Jdbc;


public class ServerListDaoMysqlImpl implements ServerListDao {

	@Autowired
	private Jdbc jdbc;

	public final static String table = "serverlist";
	
	public final static String server_table = "game_server";

	public final static String columns = "*";
	@Override
	public List<GameServer> getServerListByPartenerId(String partenerId) {
//		String sql = "select * from "+table+" where partener_id = ?";
//		SqlParameter sqlParameter = new SqlParameter();
//		sqlParameter.setString(partenerId);
//		return jdbc.getList(sql, GameServer.class, sqlParameter);
		throw new NotImplementedException();
	}

	public GameServer getServerByServerIdAndPartenerId(String serverId,String partenerId){
//		String sql = "select * from "+table+" where partener_id = ? and server_id=?";
//		SqlParameter sqlParameter = new SqlParameter();
//		sqlParameter.setString(partenerId);
//		sqlParameter.setString(serverId);
//		return jdbc.get(sql, GameServer.class, sqlParameter);
		throw new NotImplementedException();
	}

	public List<ServerListConfig> getAll(){
		String sql = "select * from "+table;
        return jdbc.getList(sql, ServerListConfig.class);
	}
	
	public List<ServerConfig> getAllServer(){
		String sql = "select * from "+server_table;
		return jdbc.getList(sql, ServerConfig.class);
	}
	
	@Override
	public void reload() {
	}

	@Override
	public List<GameServer> getAllServerList() {
		throw new NotImplementedException();
	}
}
