package com.fantingame.game.common.dao.cache;
import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.common.model.SystemConfigData;

public class ConfigDataDaoCacheImpl extends StaticDataDaoBaseT<String,SystemConfigData>{

	public SystemConfigData get(String configKey) {
		return super.getValue(configKey);
	}
	
	public List<SystemConfigData> getAllConfig(){
		return super.getValueList();
	}
	
	public int getInt(String configKey, int defaultValue) {
		SystemConfigData configData = super.getValue(configKey);
		if (configData != null) {
			return Double.valueOf(configData.getConfigValue()).intValue();
		}
		return defaultValue;
	}
	public int[] getIntArray(String configKey, int[] defaultValue) {
		SystemConfigData configData = this.get(configKey);
		if(configData==null){
			return defaultValue;
		}else{
			String[] array =  configData.getConfigValue().split(",");
			int[] result = new int[array.length];
			for(int i=0;i<array.length;i++){
				result[i] = Integer.valueOf(array[i]);
			}
			return result;
		}
	}
	public String[] getStringArray(String configKey, String[] defaultValue) {
		SystemConfigData configData = this.get(configKey);
		if(configData==null){
			return defaultValue;
		}else{
			String[] array =  configData.getConfigValue().split(",");
			return array;
		}
	}
	public double getDouble(String configKey, double defaultValue) {
		SystemConfigData configData = this.get(configKey);
		if (configData != null) {
			return Double.valueOf(configData.getConfigValue());
		}
		return defaultValue;
	}
	@Override
	protected String getCacheKey(SystemConfigData v) {
		return v.getConfigKey();
	}

}
