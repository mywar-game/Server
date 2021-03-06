package com.stats.bo;

import java.util.Date;

/**
 * UserDiamondStats entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class UserDiamondStats implements java.io.Serializable {

	private Integer id;
	private int sysNum;
	private String serverName;
	private Date time;
	private float addTask;
	private float addCreatRole;
	private float addAttackMonster;
	private float addMoneyPay;
	private float addMoneyReturn;
	private float addMoneyDouble;
	private float addAutoResumePower;
	private float addAutoResumeActivity;
	private float addOpenGiftBox;
	private float addPrestigeRewards;
	private float addPackgeExtendReturn;
	private float addStudySkill;
	private float addUpgradeSkillBook;
	private float addByAdmin;
	private float addByReceiveGiftcode;
	private float addPrestigeInvite;
	private float addExploreIntegralExchange;
	private float addByExplore;
	private float addByEmail;
	private float addBuyFromPawnshop;
	private float addSellToPawnshop;
	private float addByLifeSkill;
	private float battleLuckyEvent;
	private float addByCollect;
	private float addByCollectAndAttackMonster;
	private float addBuyinMall;
	private float addBySell;
	private float addByBuyback;

	private float reduceAttackMonster;
	private float reduceOpenGiftBox;
	private float reducePackgeExtend;
	private float reduceStudySkill;
	private float reduceUpgradeLeaderSkill;
	private float reducePrestigeInvite;
	private float reduceExploreRefresh;
	private float reduceExploreAutoRefresh;
	private float reducePawnshop;
	private float reduceSellPawnshop;
	private float reduceCreateLifeBuilder;
	private float reduceOneclickRefreshTask;
	private float reduceRefreshFiveTask;
	private float reduceForcesRelive;
	private float reduceBuyinMall;
	private float reduceBySell;
	private float reduceByBuyback;
	private float reduceRecallHero;
	private float leftDiamond;
	private float activityDiamond;

	public UserDiamondStats() {
	}

	public UserDiamondStats(int sysNum, Date time, float addTask,
			float addCreatRole, float addAttackMonster, float addMoneyPay,
			float addMoneyReturn, float addMoneyDouble,
			float addAutoResumePower, float addAutoResumeActivity,
			float addOpenGiftBox, float addPrestigeRewards,
			float addPackgeExtendReturn, float addStudySkill,
			float addUpgradeSkillBook, float addByAdmin,
			float addByReceiveGiftcode, float addPrestigeInvite,
			float addExploreIntegralExchange, float addByExplore,
			float addByEmail, float addBuyFromPawnshop,
			float addSellToPawnshop, float addByLifeSkill,
			float battleLuckyEvent, float addByCollect,
			float addByCollectAndAttackMonster, float addBuyinMall,
			float addBySell, float addByBuyback, float reduceAttackMonster,
			float reduceOpenGiftBox, float reducePackgeExtend,
			float reduceStudySkill, float reduceUpgradeLeaderSkill,
			float reducePrestigeInvite, float reduceExploreRefresh,
			float reduceExploreAutoRefresh, float reducePawnshop,
			float reduceSellPawnshop, float reduceCreateLifeBuilder,
			float reduceOneclickRefreshTask, float reduceRefreshFiveTask,
			float reduceForcesRelive, float reduceBuyinMall,
			float reduceBySell, float reduceByBuyback, float reduceRecallHero) {
		this.sysNum = sysNum;
		this.time = time;
		this.addTask = addTask;
		this.addCreatRole = addCreatRole;
		this.addAttackMonster = addAttackMonster;
		this.addMoneyPay = addMoneyPay;
		this.addMoneyReturn = addMoneyReturn;
		this.addMoneyDouble = addMoneyDouble;
		this.addAutoResumePower = addAutoResumePower;
		this.addAutoResumeActivity = addAutoResumeActivity;
		this.addOpenGiftBox = addOpenGiftBox;
		this.addPrestigeRewards = addPrestigeRewards;
		this.addPackgeExtendReturn = addPackgeExtendReturn;
		this.addStudySkill = addStudySkill;
		this.addUpgradeSkillBook = addUpgradeSkillBook;
		this.addByAdmin = addByAdmin;
		this.addByReceiveGiftcode = addByReceiveGiftcode;
		this.addPrestigeInvite = addPrestigeInvite;
		this.addExploreIntegralExchange = addExploreIntegralExchange;
		this.addByExplore = addByExplore;
		this.addByEmail = addByEmail;
		this.addBuyFromPawnshop = addBuyFromPawnshop;
		this.addSellToPawnshop = addSellToPawnshop;
		this.addByLifeSkill = addByLifeSkill;
		this.battleLuckyEvent = battleLuckyEvent;
		this.addByCollect = addByCollect;
		this.addByCollectAndAttackMonster = addByCollectAndAttackMonster;
		this.addBuyinMall = addBuyinMall;
		this.addBySell = addBySell;
		this.addByBuyback = addByBuyback;
		this.reduceAttackMonster = reduceAttackMonster;
		this.reduceOpenGiftBox = reduceOpenGiftBox;
		this.reducePackgeExtend = reducePackgeExtend;
		this.reduceStudySkill = reduceStudySkill;
		this.reduceUpgradeLeaderSkill = reduceUpgradeLeaderSkill;
		this.reducePrestigeInvite = reducePrestigeInvite;
		this.reduceExploreRefresh = reduceExploreRefresh;
		this.reduceExploreAutoRefresh = reduceExploreAutoRefresh;
		this.reducePawnshop = reducePawnshop;
		this.reduceSellPawnshop = reduceSellPawnshop;
		this.reduceCreateLifeBuilder = reduceCreateLifeBuilder;
		this.reduceOneclickRefreshTask = reduceOneclickRefreshTask;
		this.reduceRefreshFiveTask = reduceRefreshFiveTask;
		this.reduceForcesRelive = reduceForcesRelive;
		this.reduceBuyinMall = reduceBuyinMall;
		this.reduceBySell = reduceBySell;
		this.reduceByBuyback = reduceByBuyback;
		this.reduceRecallHero = reduceRecallHero;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(int sysNum) {
		this.sysNum = sysNum;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public float getAddTask() {
		return this.addTask;
	}

	public void setAddTask(float addTask) {
		this.addTask = addTask;
	}

	public float getAddCreatRole() {
		return this.addCreatRole;
	}

	public void setAddCreatRole(float addCreatRole) {
		this.addCreatRole = addCreatRole;
	}

	public float getAddAttackMonster() {
		return this.addAttackMonster;
	}

	public void setAddAttackMonster(float addAttackMonster) {
		this.addAttackMonster = addAttackMonster;
	}

	public float getAddMoneyPay() {
		return this.addMoneyPay;
	}

	public void setAddMoneyPay(float addMoneyPay) {
		this.addMoneyPay = addMoneyPay;
	}

	public float getAddMoneyReturn() {
		return this.addMoneyReturn;
	}

	public void setAddMoneyReturn(float addMoneyReturn) {
		this.addMoneyReturn = addMoneyReturn;
	}

	public float getAddMoneyDouble() {
		return this.addMoneyDouble;
	}

	public void setAddMoneyDouble(float addMoneyDouble) {
		this.addMoneyDouble = addMoneyDouble;
	}

	public float getAddAutoResumePower() {
		return this.addAutoResumePower;
	}

	public void setAddAutoResumePower(float addAutoResumePower) {
		this.addAutoResumePower = addAutoResumePower;
	}

	public float getAddAutoResumeActivity() {
		return this.addAutoResumeActivity;
	}

	public void setAddAutoResumeActivity(float addAutoResumeActivity) {
		this.addAutoResumeActivity = addAutoResumeActivity;
	}

	public float getAddOpenGiftBox() {
		return this.addOpenGiftBox;
	}

	public void setAddOpenGiftBox(float addOpenGiftBox) {
		this.addOpenGiftBox = addOpenGiftBox;
	}

	public float getAddPrestigeRewards() {
		return this.addPrestigeRewards;
	}

	public void setAddPrestigeRewards(float addPrestigeRewards) {
		this.addPrestigeRewards = addPrestigeRewards;
	}

	public float getAddPackgeExtendReturn() {
		return this.addPackgeExtendReturn;
	}

	public void setAddPackgeExtendReturn(float addPackgeExtendReturn) {
		this.addPackgeExtendReturn = addPackgeExtendReturn;
	}

	public float getAddStudySkill() {
		return this.addStudySkill;
	}

	public void setAddStudySkill(float addStudySkill) {
		this.addStudySkill = addStudySkill;
	}

	public float getAddUpgradeSkillBook() {
		return this.addUpgradeSkillBook;
	}

	public void setAddUpgradeSkillBook(float addUpgradeSkillBook) {
		this.addUpgradeSkillBook = addUpgradeSkillBook;
	}

	public float getAddByAdmin() {
		return this.addByAdmin;
	}

	public void setAddByAdmin(float addByAdmin) {
		this.addByAdmin = addByAdmin;
	}

	public float getAddByReceiveGiftcode() {
		return this.addByReceiveGiftcode;
	}

	public void setAddByReceiveGiftcode(float addByReceiveGiftcode) {
		this.addByReceiveGiftcode = addByReceiveGiftcode;
	}

	public float getAddPrestigeInvite() {
		return this.addPrestigeInvite;
	}

	public void setAddPrestigeInvite(float addPrestigeInvite) {
		this.addPrestigeInvite = addPrestigeInvite;
	}

	public float getAddExploreIntegralExchange() {
		return this.addExploreIntegralExchange;
	}

	public void setAddExploreIntegralExchange(float addExploreIntegralExchange) {
		this.addExploreIntegralExchange = addExploreIntegralExchange;
	}

	public float getAddByExplore() {
		return this.addByExplore;
	}

	public void setAddByExplore(float addByExplore) {
		this.addByExplore = addByExplore;
	}

	public float getAddByEmail() {
		return this.addByEmail;
	}

	public void setAddByEmail(float addByEmail) {
		this.addByEmail = addByEmail;
	}

	public float getAddBuyFromPawnshop() {
		return this.addBuyFromPawnshop;
	}

	public void setAddBuyFromPawnshop(float addBuyFromPawnshop) {
		this.addBuyFromPawnshop = addBuyFromPawnshop;
	}

	public float getAddSellToPawnshop() {
		return this.addSellToPawnshop;
	}

	public void setAddSellToPawnshop(float addSellToPawnshop) {
		this.addSellToPawnshop = addSellToPawnshop;
	}

	public float getAddByLifeSkill() {
		return this.addByLifeSkill;
	}

	public void setAddByLifeSkill(float addByLifeSkill) {
		this.addByLifeSkill = addByLifeSkill;
	}

	public float getBattleLuckyEvent() {
		return this.battleLuckyEvent;
	}

	public void setBattleLuckyEvent(float battleLuckyEvent) {
		this.battleLuckyEvent = battleLuckyEvent;
	}

	public float getAddByCollect() {
		return this.addByCollect;
	}

	public void setAddByCollect(float addByCollect) {
		this.addByCollect = addByCollect;
	}

	public float getAddByCollectAndAttackMonster() {
		return this.addByCollectAndAttackMonster;
	}

	public void setAddByCollectAndAttackMonster(
			float addByCollectAndAttackMonster) {
		this.addByCollectAndAttackMonster = addByCollectAndAttackMonster;
	}

	public float getAddBuyinMall() {
		return this.addBuyinMall;
	}

	public void setAddBuyinMall(float addBuyinMall) {
		this.addBuyinMall = addBuyinMall;
	}

	public float getAddBySell() {
		return this.addBySell;
	}

	public void setAddBySell(float addBySell) {
		this.addBySell = addBySell;
	}

	public float getAddByBuyback() {
		return this.addByBuyback;
	}

	public void setAddByBuyback(float addByBuyback) {
		this.addByBuyback = addByBuyback;
	}

	public float getReduceAttackMonster() {
		return this.reduceAttackMonster;
	}

	public void setReduceAttackMonster(float reduceAttackMonster) {
		this.reduceAttackMonster = reduceAttackMonster;
	}

	public float getReduceOpenGiftBox() {
		return this.reduceOpenGiftBox;
	}

	public void setReduceOpenGiftBox(float reduceOpenGiftBox) {
		this.reduceOpenGiftBox = reduceOpenGiftBox;
	}

	public float getReducePackgeExtend() {
		return this.reducePackgeExtend;
	}

	public void setReducePackgeExtend(float reducePackgeExtend) {
		this.reducePackgeExtend = reducePackgeExtend;
	}

	public float getReduceStudySkill() {
		return this.reduceStudySkill;
	}

	public void setReduceStudySkill(float reduceStudySkill) {
		this.reduceStudySkill = reduceStudySkill;
	}

	public float getReduceUpgradeLeaderSkill() {
		return this.reduceUpgradeLeaderSkill;
	}

	public void setReduceUpgradeLeaderSkill(float reduceUpgradeLeaderSkill) {
		this.reduceUpgradeLeaderSkill = reduceUpgradeLeaderSkill;
	}

	public float getReducePrestigeInvite() {
		return this.reducePrestigeInvite;
	}

	public void setReducePrestigeInvite(float reducePrestigeInvite) {
		this.reducePrestigeInvite = reducePrestigeInvite;
	}

	public float getReduceExploreRefresh() {
		return this.reduceExploreRefresh;
	}

	public void setReduceExploreRefresh(float reduceExploreRefresh) {
		this.reduceExploreRefresh = reduceExploreRefresh;
	}

	public float getReduceExploreAutoRefresh() {
		return this.reduceExploreAutoRefresh;
	}

	public void setReduceExploreAutoRefresh(float reduceExploreAutoRefresh) {
		this.reduceExploreAutoRefresh = reduceExploreAutoRefresh;
	}

	public float getReducePawnshop() {
		return this.reducePawnshop;
	}

	public void setReducePawnshop(float reducePawnshop) {
		this.reducePawnshop = reducePawnshop;
	}

	public float getReduceSellPawnshop() {
		return this.reduceSellPawnshop;
	}

	public void setReduceSellPawnshop(float reduceSellPawnshop) {
		this.reduceSellPawnshop = reduceSellPawnshop;
	}

	public float getReduceCreateLifeBuilder() {
		return this.reduceCreateLifeBuilder;
	}

	public void setReduceCreateLifeBuilder(float reduceCreateLifeBuilder) {
		this.reduceCreateLifeBuilder = reduceCreateLifeBuilder;
	}

	public float getReduceOneclickRefreshTask() {
		return this.reduceOneclickRefreshTask;
	}

	public void setReduceOneclickRefreshTask(float reduceOneclickRefreshTask) {
		this.reduceOneclickRefreshTask = reduceOneclickRefreshTask;
	}

	public float getReduceRefreshFiveTask() {
		return this.reduceRefreshFiveTask;
	}

	public void setReduceRefreshFiveTask(float reduceRefreshFiveTask) {
		this.reduceRefreshFiveTask = reduceRefreshFiveTask;
	}

	public float getReduceForcesRelive() {
		return this.reduceForcesRelive;
	}

	public void setReduceForcesRelive(float reduceForcesRelive) {
		this.reduceForcesRelive = reduceForcesRelive;
	}

	public float getReduceBuyinMall() {
		return this.reduceBuyinMall;
	}

	public void setReduceBuyinMall(float reduceBuyinMall) {
		this.reduceBuyinMall = reduceBuyinMall;
	}

	public float getReduceBySell() {
		return this.reduceBySell;
	}

	public void setReduceBySell(float reduceBySell) {
		this.reduceBySell = reduceBySell;
	}

	public float getReduceByBuyback() {
		return this.reduceByBuyback;
	}

	public void setReduceByBuyback(float reduceByBuyback) {
		this.reduceByBuyback = reduceByBuyback;
	}

	public float getReduceRecallHero() {
		return this.reduceRecallHero;
	}

	public void setReduceRecallHero(float reduceRecallHero) {
		this.reduceRecallHero = reduceRecallHero;
	}

	public float getLeftDiamond() {
		return leftDiamond;
	}

	public void setLeftDiamond(float leftDiamond) {
		this.leftDiamond = leftDiamond;
	}

	public float getActivityDiamond() {
		return activityDiamond;
	}

	public void setActivityDiamond(float activityDiamond) {
		this.activityDiamond = activityDiamond;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

}