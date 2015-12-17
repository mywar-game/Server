package com.fantingame.game.mywar.logic.user.constant;
/**
 * 此处常量为服务器主动改变玩家的一些属性类型定义
 * @author Administrator
 *
 */
public class UserPushProperties {
	//充值会改变
    public static final int MONEY = 1;
    //体力 会自动恢复
    public static final int POWER = 2;
    //活力 会自动恢复
    public static final int ACTIVITY = 3;
    //vip经验
    public static final int VIP_EXP = 4;
    //vip等级
    public static final int VIP_LEVEL = 5;
    //下次体力恢复时间
    public static final int NEXT_POWER_RESUME_TIME = 6;
    //下次活力恢复时间
    public static final int NEXT_ACTIVITY_RESUME_TIME = 7;
    // 金币
    public static final int GOLD = 8;
}
