package com.fantingame.game.mywar.logic.life.dao.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.life.dao.WeatherInfoDao;
import com.fantingame.game.mywar.logic.life.model.WeatherInfo;

public class WeatherInfoDaoMysqlImpl implements WeatherInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String tableName = "weather_info";

	@Override
	public WeatherInfo getWeatherInfo(int mapId) {
		String sql = "select * from " + tableName + " where map_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(mapId);
		
		return this.jdbcUser.get(sql, WeatherInfo.class, param);
	}

	@Override
	public boolean deleteWeather(int mapId) {
		String sql = "delete from " + tableName;
		return this.jdbcUser.update(sql, new SqlParameter()) > 0;
	}

	@Override
	public WeatherInfo getWeatherInfo() {
		String sql = "select * from " + tableName + " limit 1";
		SqlParameter param = new SqlParameter();
		
		return this.jdbcUser.get(sql, WeatherInfo.class, param);
	}

	@Override
	public boolean addWeatherInfo(WeatherInfo info) {
		return this.jdbcUser.insert(info) > 0;
	}
	
}
