package com.fantingame.game.common.utils;
/**
 * 神器工具类
 * @author Administrator
 *
 */
public class ArtifactUtil {
    /**
     * 求出神器技能升级的百分比
     * @param value
     * @return
     */
	public static int getTrueValue(int value){
		double temp = Math.pow(0.0002*value, 0.74)+Math.pow(0.003*value, 0.34);
		int result = (int)(temp*100);
		return result;
	} 
}
