package com.fantingame.game.mywar.logic.life.dao;

import com.fantingame.game.mywar.logic.life.model.WeatherInfo;

public interface WeatherInfoDao {

	/**
	 * 获取当前天气信息
	 * 
	 * @param mapId
	 * @return
	 */
	public WeatherInfo getWeatherInfo(int mapId);

	/**
	 * 获取当前天气信息
	 * 
	 * @return
	 */
	public WeatherInfo getWeatherInfo();
	
	/**
	 * 删除天气信息
	 * 
	 * @return
	 */
	public boolean deleteWeather(int mapId);
	
	/**
	 * 添加
	 * 
	 * @param info
	 * @return
	 */
	public boolean addWeatherInfo(WeatherInfo info);
}
