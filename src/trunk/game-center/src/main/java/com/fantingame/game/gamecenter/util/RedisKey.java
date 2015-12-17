package com.fantingame.game.gamecenter.util;

/**
 * redis key
 * 
 * @author jacky
 * 
 */
public class RedisKey {

	private final static String prefix = "heroworld_local";

	/**
	 * 系统武将列表key
	 * 
	 * @return
	 */
	public static String getSystemHeroKey() {
		return prefix + "_" + "system_hero_list";
	}

	/**
	 * 用户等级配置key
	 * 
	 * @return
	 */
	public static String getSystemUserLevelKey() {
		return prefix + "_" + "system_user_level_list";
	}

	/**
	 * 获取系统装备key
	 * 
	 * @return
	 */
	public static String getSystemEquipKey() {
		return prefix + "_" + "system_equip_list";
	}

	/**
	 * 获取商户信息KEY
	 * 
	 * @return
	 */
	public static String getPartnerConfigKey() {
		return prefix + "_" + "partner_config";
	}

	/**
	 * 命令key
	 * 
	 * @return
	 */
	public static String getCommandKey(int priority) {
		return prefix + "_" + "command_list_key_" + priority;
	}

	/**
	 * 异步插入运营日志key
	 * 
	 * @return
	 */
	public static String getLogOperatorKey() {
		return prefix + "_" + "log_oprator_list_key_";
	}

	/**
	 * 在线时间key
	 * 
	 * @return
	 */
	public static String getOnlineUserKey() {
		return prefix + "_" + "online_user_key";
	}

	/**
	 * 获取怪物部队怪物cache key
	 * 
	 * @param forcesId
	 * @return
	 */
	public static String getForcesMonsterCacheKey(int forcesId) {
		return prefix + "_" + "forces_monster_key_" + forcesId;
	}

	/**
	 * 获取用户缓存key
	 * 
	 * @return
	 */
	public static String getUserCacheKey() {
		return prefix + "_user_list_cache_key";
	}

	/**
	 * 获取用户扩展信息缓存key
	 * 
	 * @return
	 */
	public static String getUserExtinfoCacheKeyPreFix() {
		return prefix + "_user_extinfo_cache_key";
	}

	/**
	 * 获取用户缓存key(playerId)
	 * 
	 * @return
	 */
	public static String getUserByPlayerIdCacheKey() {
		return prefix + "_player_list_cache_key";
	}

	/**
	 * 系统任务缓存key
	 * 
	 * @return
	 */
	public static String getSystemTaskKey() {
		return prefix + "_" + "system_task_list_cache_key";
	}

	public static String getSystemMailKey() {
		return prefix + "_" + "system_mail_list_cache_key";
	}

	/**
	 * 后置任务缓存key
	 * 
	 * @param systemTaskId
	 * @return
	 */
	public static String getSystemTaskPosTaskKey(int systemTaskId) {
		return prefix + "_" + "system_task_post_task_list_" + systemTaskId;
	}

	/**
	 * 怪物部队军队缓存key
	 * 
	 * @param monsterId
	 * @return
	 */
	public static String getSystemMonsterCacheKey(int monsterId) {
		return prefix + "_" + "system_monster_cache_" + monsterId;
	}

	/**
	 * 获取系统怪物部队缓存key
	 * 
	 * @param forcesid
	 * @return
	 */
	public static String getSystemForcesCacheKey(int forcesid) {
		return prefix + "_" + "system_forces_cache_" + forcesid;
	}

	/**
	 * 获取用户装备缓存key
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserEquipListCacheKey(String userId) {
		return prefix + "_" + "user_equip_list_cache_" + userId;
	}

	/**
	 * 获取用户英雄缓存key
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserHeroListCacheKey(String userId) {
		return prefix + "_" + "user_hero_list_cache_" + userId;
	}

	/**
	 * 获取武将组合技能缓存key
	 * 
	 * @param heroId
	 * @return
	 */
	public static String getHeroSkillCacheKey() {
		return prefix + "_" + "hero_skill_list_cache_key";
	}

	/**
	 * 排名积分配置表
	 * 
	 * @return
	 */
	public static String getRankScoreCfgCacheKey() {
		return prefix + "_" + "rank_score_config_key";
	}

	/**
	 * 获取争霸信息
	 * 
	 * @return
	 */
	public static String getPkInfoKey() {
		return prefix + "_" + "pk_info_key";
	}

	/**
	 * 获取用户排名信息
	 * 
	 * @return
	 */
	public static String getRankScoreInfoKey() {
		return prefix + "_" + "rank_score_info_key";
	}

	/**
	 * 获取争霸赛奖励信息
	 * 
	 * @return
	 */
	public static String getPkAwardKey() {
		return prefix + "_" + "pk_award_key";
	}

	/**
	 * 获取财富排行KEY
	 * 
	 * @return
	 */
	public static String getWealthRankKey() {
		return prefix + "_" + "wealth_rank_key";
	}

	/**
	 * 获取通关排行KEY
	 * 
	 * @return
	 */
	public static String getForcesRankKey() {
		return prefix + "_" + "forces_rank_key";
	}

	/**
	 * 获取攻击力排行KEY
	 * 
	 * @return
	 */
	public static String getAttackRankKey() {
		return prefix + "_" + "attack_rank_key";
	}

	/**
	 * 获取用户背包key前缀
	 * 
	 * @return
	 */
	public static String getUserGiftBagKeyPre() {
		return prefix + "_" + "user_gift_bag_key";
	}

	/**
	 * 获取防御力排行KEY
	 * 
	 * @return
	 */
	public static String getDefenceRankKey() {
		return prefix + "_" + "defence_rank_key";
	}

	/**
	 * 获取生命值排行KEY
	 * 
	 * @return
	 */
	public static String getHpRankKey() {
		return prefix + "_" + "hp_rank_key";
	}

	/**
	 * 获取战斗力排行KEY
	 * 
	 * @return
	 */
	public static String getPowerRankKey() {
		return prefix + "_" + "power_rank_key";
	}

	/**
	 * 获取排行榜统计时间KEY
	 * 
	 * @return
	 */
	public static String getRankStatTimeKey() {
		return prefix + "_" + "rank_stat_time_key";
	}

	/**
	 * 获取酒馆掉落池游标key
	 * 
	 * @return
	 */
	public static String getTavernDropPoolIndKey(int type) {
		return "tavern_drop_pool_ind_key_" + type;
	}

	/**
	 * 获取用户关卡表key 大关卡的键
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserFoceSceneIdKey(String userId) {
		return prefix + "_user_forces_sceneId" + userId;
	}

	/**
	 * 获取用户关卡表key 小关卡的键
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserFoceForceIdKey(String userId) {
		return prefix + "_user_forces_foceId_" + userId;
	}

	/**
	 * 获取用户关卡表key forcetype关卡类型的键
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserFoceForceTypeKey(String userId) {
		return prefix + "_user_forces_foceType_" + userId;
	}

	public static String getUserTavernKeyPre() {
		return prefix + "_user_tavern_";
	}

	/**
	 * 用户活跃度活动key前缀
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserActivityTaskKeyPre() {
		return prefix + "user_activity_task";
	}

	/**
	 * 用户大关卡打过记录
	 * 
	 * @return
	 */
	public static String getUserSceneKeyPre() {
		return prefix + "user_scene";
	}
	/**
	 * 用户神器表
	 * @return
	 */
	public static String getUserArtifactKeyPre(){
		return prefix + "user_artifact";
	}
	/**
	 * 用户任务记录表
	 * 
	 * @return
	 */
	public static String getUserTaskKeyPre() {
		return prefix + "user_task";
	}

	/**
	 * 用户商城表记录缓存key
	 * 
	 * @return
	 */
	public static String getUserMallLogKeyPre() {
		return prefix + "user_mall_log";
	}

	/**
	 * 用户活跃度活动key
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserActivityRewardLogKey(String userId) {
		return prefix + "_user_activity_reward_log_" + userId;
	}

	/**
	 * 用户道具key前缀
	 * 
	 * @return
	 */
	public static String getUserToolKeyPre() {
		return prefix + "_user_tool_";
	}

	/**
	 * 用户兑换契约英雄缓存key缓存前缀
	 * 
	 * @return
	 */
	public static String getUserHeroExchangeKeyPre() {
		return prefix + "_user_hero_exchange_";
	}

	/**
	 * 用户每日领取奖励key前缀
	 * 
	 * @return
	 */
	public static String getUserDailyGainKeyPre() {
		return prefix + "_user_daily_gain_log_";
	}

	/**
	 * 品质排行
	 * 
	 * @return
	 */
	public static String getHeroColorRankKey() {
		return prefix + "_" + "hero_color_rank_key";
	}

	public static String getSystemStatusKey() {
		return prefix + "_" + "system_status_key";
	}

	public static String getUserTokenCacheKey() {
		return prefix + "_" + "user_token_cache_key";
	}

	public static String getOfflineUserTokenCacheKey() {
		return prefix + "_" + "offline_user_token_cache_key";
	}

	public static String getUserSweepCacheKey() {
		return prefix + "_" + "user_sweep_cache_key";
	}

	public static String getUserAmendEmbattleTimeCacheKey() {
		return prefix + "_" + "user_amend_embattle_cache_key";
	}
	public static String getUserMallInfoCacheKey(String userId) {
		return prefix + "_" + "user_mall_info_cache_key_" + userId;
	}
	
	public static String getUserTotalDayPayRewardCacheKey(){
		return prefix+"_"+"user_total_day_pay_reward_key";
	}
	
	public static String getUserDrawCacheKey(){
		return prefix+"_"+"user_draw_key";
	}
	public static void main(String[] args) {
		System.out.println(getSystemMailKey());
	}

}
