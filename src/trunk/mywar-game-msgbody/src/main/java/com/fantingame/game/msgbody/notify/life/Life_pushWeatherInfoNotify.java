package com.fantingame.game.msgbody.notify.life;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.notify.life.WeatherInfoBO;

/**推送天气预报**/
public class Life_pushWeatherInfoNotify implements ICodeAble {

		/**天气信息对象**/
	private WeatherInfoBO weatherInfo=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		weatherInfo.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		weatherInfo=new WeatherInfoBO();    
		weatherInfo.decode(inputStream);


	}
	
		/**天气信息对象**/
    public WeatherInfoBO getWeatherInfo() {
		return weatherInfo;
	}
	/**天气信息对象**/
    public void setWeatherInfo(WeatherInfoBO weatherInfo) {
		this.weatherInfo = weatherInfo;
	}

	
	
}
