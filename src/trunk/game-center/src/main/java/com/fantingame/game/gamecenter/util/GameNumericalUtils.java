package com.fantingame.game.gamecenter.util;

/**
 * 游戏数值相关算法工具
 * @author CJ
 *
 */
public class GameNumericalUtils {
	/**
	 * 经验值算法，7 * 等级 + 10
	 * @param level 战败者的等级
	 * @return
	 */
	public static int getExp(int level){
		return 7 * level + 10;
	}
	
	/**
	 * 判断是否升级，算法：2*(等级+2)^3
	 * @param exp   当前经验
	 * @param level 当前等级
	 * @return 返回值大于0则表示升级
	 */
	public static int isLevelUp(int exp, int level){
		return (int)(exp - expLimit(level));
	}
	
	/**
	 * 获取当前等级的经验上限，经验值超过这个上限即可升级
	 * @param level
	 * @return
	 */
	public static int expLimit(int level){
		return (int)(2 * Math.pow((level + 2), 3));
	}
}
