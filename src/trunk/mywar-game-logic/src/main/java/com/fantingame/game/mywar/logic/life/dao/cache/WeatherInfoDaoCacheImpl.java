package com.fantingame.game.mywar.logic.life.dao.cache;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.life.dao.WeatherInfoDao;
import com.fantingame.game.mywar.logic.life.dao.mysql.WeatherInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.life.model.WeatherInfo;

public class WeatherInfoDaoCacheImpl extends BaseCacheDao<WeatherInfo> implements
		WeatherInfoDao {

	public WeatherInfoDaoMysqlImpl weatherInfoDaoMysqlImpl;

	public WeatherInfoDaoMysqlImpl getWeatherInfoDaoMysqlImpl() {
		return weatherInfoDaoMysqlImpl;
	}

	public void setWeatherInfoDaoMysqlImpl(WeatherInfoDaoMysqlImpl weatherInfoDaoMysqlImpl) {
		this.weatherInfoDaoMysqlImpl = weatherInfoDaoMysqlImpl;
	}

	@Override
	protected WeatherInfo loadFromDb(String mapId) {
		WeatherInfo info = this.weatherInfoDaoMysqlImpl.getWeatherInfo(Integer.parseInt(mapId));
		super.addEntry(mapId + "", info);
		return info;
	}

	@Override
	public WeatherInfo getWeatherInfo(int mapId) {
		return super.getEntry(mapId + "");
	}

	@Override
	public boolean deleteWeather(int mapId) {
		if (this.weatherInfoDaoMysqlImpl.deleteWeather(mapId)) {
			super.delete(Integer.toString(mapId));
			
			return true;
		}
		
		return false;
	}

	@Override
	public WeatherInfo getWeatherInfo() {		
		return this.weatherInfoDaoMysqlImpl.getWeatherInfo();
	}

	@Override
	public boolean addWeatherInfo(WeatherInfo info) {
		if (this.weatherInfoDaoMysqlImpl.addWeatherInfo(info)) {
			super.addEntry(info.getMapId() + "", info);
		}
		
		return false;
	}

}
