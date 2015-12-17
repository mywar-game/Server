package com.fantingame.game.msgbody.notify.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.notify.user.UserBO;
import com.fantingame.game.msgbody.notify.task.UserTaskBO;
import java.util.List;
import java.util.ArrayList;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.client.hero.UserHeroSkillBO;
import com.fantingame.game.msgbody.client.tool.UserToolBO;
import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import com.fantingame.game.msgbody.client.gemstone.UserGemstoneBO;
import com.fantingame.game.msgbody.client.activity.SystemActivityBO;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import com.fantingame.game.msgbody.notify.boss.WorldBossInfoBO;
import com.fantingame.game.msgbody.client.boss.UserBossInfoBO;
import com.fantingame.game.msgbody.notify.life.WeatherInfoBO;

/**角色登录接口**/
public class User_pushNotify implements ICodeAble {

		/**用户对象**/
	private UserBO userBO=null;
	/**用户任务列表**/
	private List<UserTaskBO> userTaskList=null;
	/**用户英雄列表**/
	private List<UserHeroBO> userHeroList=null;
	/**用户英雄技能列表**/
	private List<UserHeroSkillBO> userHeroSkillList=null;
	/**用户道具列表**/
	private List<UserToolBO> userToolList=null;
	/**用户装备列表**/
	private List<UserEquipBO> userEquipList=null;
	/**用户宝石列表**/
	private List<UserGemstoneBO> userGemstoneList=null;
	/**系统活动列表**/
	private List<SystemActivityBO> systemActivityList=null;
	/**用户已开启场景id列表**/
	private List<Integer> userOpenSceneIdList=null;
	/**用户已领取声望奖励id列表**/
	private List<Integer> userPrestigeRewardIdList=null;
	/**系统配置信息,详情请查看数值表中系统配置表中的定义**/
	private Map<String,String> systemConfig=null;
	/**是否有新邮件1有0没有**/
	private Integer mailStatus=0;
	/**同屏显示人数**/
	private Integer displayNum=0;
	/**记录已走过的所有新手引导的步奏（99999为跳过新手引导）**/
	private String recordGuideStep="";
	/**开启过的地图**/
	private String openMaps="";
	/**竞技场排名（-1为未进入过竞技场）**/
	private Integer pkRank=0;
	/**每月签到状态0未领取1已领取**/
	private Integer loginRewardStatus30=0;
	/**服务器当前时间**/
	private Long currentTime=0l;
	/**用户弹窗提示**/
	private String tips="";
	/**世界boss的相关信息(空的话boss还未出现)**/
	private WorldBossInfoBO bossInfoBO=null;
	/**用户boss战的相关信息(空的话用户未攻击过boss)**/
	private UserBossInfoBO userBossInfo=null;
	/**当前天气信息**/
	private WeatherInfoBO weatherInfo=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userBO.encode(outputStream);

		
        if(userTaskList==null||userTaskList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userTaskList.size());
		}
		if(userTaskList!=null&&userTaskList.size()>0){
			for(int userTaskListi=0;userTaskListi<userTaskList.size();userTaskListi++){
				userTaskList.get(userTaskListi).encode(outputStream);
			}
		}		
        if(userHeroList==null||userHeroList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userHeroList.size());
		}
		if(userHeroList!=null&&userHeroList.size()>0){
			for(int userHeroListi=0;userHeroListi<userHeroList.size();userHeroListi++){
				userHeroList.get(userHeroListi).encode(outputStream);
			}
		}		
        if(userHeroSkillList==null||userHeroSkillList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userHeroSkillList.size());
		}
		if(userHeroSkillList!=null&&userHeroSkillList.size()>0){
			for(int userHeroSkillListi=0;userHeroSkillListi<userHeroSkillList.size();userHeroSkillListi++){
				userHeroSkillList.get(userHeroSkillListi).encode(outputStream);
			}
		}		
        if(userToolList==null||userToolList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userToolList.size());
		}
		if(userToolList!=null&&userToolList.size()>0){
			for(int userToolListi=0;userToolListi<userToolList.size();userToolListi++){
				userToolList.get(userToolListi).encode(outputStream);
			}
		}		
        if(userEquipList==null||userEquipList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userEquipList.size());
		}
		if(userEquipList!=null&&userEquipList.size()>0){
			for(int userEquipListi=0;userEquipListi<userEquipList.size();userEquipListi++){
				userEquipList.get(userEquipListi).encode(outputStream);
			}
		}		
        if(userGemstoneList==null||userGemstoneList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userGemstoneList.size());
		}
		if(userGemstoneList!=null&&userGemstoneList.size()>0){
			for(int userGemstoneListi=0;userGemstoneListi<userGemstoneList.size();userGemstoneListi++){
				userGemstoneList.get(userGemstoneListi).encode(outputStream);
			}
		}		
        if(systemActivityList==null||systemActivityList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(systemActivityList.size());
		}
		if(systemActivityList!=null&&systemActivityList.size()>0){
			for(int systemActivityListi=0;systemActivityListi<systemActivityList.size();systemActivityListi++){
				systemActivityList.get(systemActivityListi).encode(outputStream);
			}
		}		
        if(userOpenSceneIdList==null||userOpenSceneIdList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userOpenSceneIdList.size());
		}
		if(userOpenSceneIdList!=null&&userOpenSceneIdList.size()>0){
			for(int userOpenSceneIdListi=0;userOpenSceneIdListi<userOpenSceneIdList.size();userOpenSceneIdListi++){
						outputStream.writeInt(userOpenSceneIdList.get(userOpenSceneIdListi));


			}
		}		
        if(userPrestigeRewardIdList==null||userPrestigeRewardIdList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userPrestigeRewardIdList.size());
		}
		if(userPrestigeRewardIdList!=null&&userPrestigeRewardIdList.size()>0){
			for(int userPrestigeRewardIdListi=0;userPrestigeRewardIdListi<userPrestigeRewardIdList.size();userPrestigeRewardIdListi++){
						outputStream.writeInt(userPrestigeRewardIdList.get(userPrestigeRewardIdListi));


			}
		}		
        if(systemConfig==null||systemConfig.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(systemConfig.size());
		}
        
		if(systemConfig!=null&&systemConfig.size()>0){
			for(Entry<String, String> entry:systemConfig.entrySet()){
				outputStream.writeUTF(entry.getKey());
						outputStream.writeUTF(entry.getValue());

;
			}
		}		outputStream.writeInt(mailStatus);

		outputStream.writeInt(displayNum);

		outputStream.writeUTF(recordGuideStep);

		outputStream.writeUTF(openMaps);

		outputStream.writeInt(pkRank);

		outputStream.writeInt(loginRewardStatus30);

		outputStream.writeLong(currentTime);

		outputStream.writeUTF(tips);

		bossInfoBO.encode(outputStream);

		userBossInfo.encode(outputStream);

		weatherInfo.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userBO=new UserBO();    
		userBO.decode(inputStream);

		
        int userTaskListSize = inputStream.readInt();
		if(userTaskListSize>0){
			userTaskList = new ArrayList<UserTaskBO>();
			for(int userTaskListi=0;userTaskListi<userTaskListSize;userTaskListi++){
				 UserTaskBO entry = new UserTaskBO();entry.decode(inputStream);userTaskList.add(entry);
			}
		}		
        int userHeroListSize = inputStream.readInt();
		if(userHeroListSize>0){
			userHeroList = new ArrayList<UserHeroBO>();
			for(int userHeroListi=0;userHeroListi<userHeroListSize;userHeroListi++){
				 UserHeroBO entry = new UserHeroBO();entry.decode(inputStream);userHeroList.add(entry);
			}
		}		
        int userHeroSkillListSize = inputStream.readInt();
		if(userHeroSkillListSize>0){
			userHeroSkillList = new ArrayList<UserHeroSkillBO>();
			for(int userHeroSkillListi=0;userHeroSkillListi<userHeroSkillListSize;userHeroSkillListi++){
				 UserHeroSkillBO entry = new UserHeroSkillBO();entry.decode(inputStream);userHeroSkillList.add(entry);
			}
		}		
        int userToolListSize = inputStream.readInt();
		if(userToolListSize>0){
			userToolList = new ArrayList<UserToolBO>();
			for(int userToolListi=0;userToolListi<userToolListSize;userToolListi++){
				 UserToolBO entry = new UserToolBO();entry.decode(inputStream);userToolList.add(entry);
			}
		}		
        int userEquipListSize = inputStream.readInt();
		if(userEquipListSize>0){
			userEquipList = new ArrayList<UserEquipBO>();
			for(int userEquipListi=0;userEquipListi<userEquipListSize;userEquipListi++){
				 UserEquipBO entry = new UserEquipBO();entry.decode(inputStream);userEquipList.add(entry);
			}
		}		
        int userGemstoneListSize = inputStream.readInt();
		if(userGemstoneListSize>0){
			userGemstoneList = new ArrayList<UserGemstoneBO>();
			for(int userGemstoneListi=0;userGemstoneListi<userGemstoneListSize;userGemstoneListi++){
				 UserGemstoneBO entry = new UserGemstoneBO();entry.decode(inputStream);userGemstoneList.add(entry);
			}
		}		
        int systemActivityListSize = inputStream.readInt();
		if(systemActivityListSize>0){
			systemActivityList = new ArrayList<SystemActivityBO>();
			for(int systemActivityListi=0;systemActivityListi<systemActivityListSize;systemActivityListi++){
				 SystemActivityBO entry = new SystemActivityBO();entry.decode(inputStream);systemActivityList.add(entry);
			}
		}		
        int userOpenSceneIdListSize = inputStream.readInt();
		if(userOpenSceneIdListSize>0){
			userOpenSceneIdList = new ArrayList<Integer>();
			for(int userOpenSceneIdListi=0;userOpenSceneIdListi<userOpenSceneIdListSize;userOpenSceneIdListi++){
				 userOpenSceneIdList.add(inputStream.readInt());
			}
		}		
        int userPrestigeRewardIdListSize = inputStream.readInt();
		if(userPrestigeRewardIdListSize>0){
			userPrestigeRewardIdList = new ArrayList<Integer>();
			for(int userPrestigeRewardIdListi=0;userPrestigeRewardIdListi<userPrestigeRewardIdListSize;userPrestigeRewardIdListi++){
				 userPrestigeRewardIdList.add(inputStream.readInt());
			}
		}        
        int systemConfigSize = inputStream.readInt();
		if(systemConfigSize>0){
			systemConfig = new HashMap<String,String>();
			for(int systemConfigCursor=0;systemConfigCursor<systemConfigSize;systemConfigCursor++){
				String key = inputStream.readUTF();
				systemConfig.put(key,inputStream.readUTF());
			}
		}		mailStatus = inputStream.readInt();

		displayNum = inputStream.readInt();

		recordGuideStep = inputStream.readUTF();

		openMaps = inputStream.readUTF();

		pkRank = inputStream.readInt();

		loginRewardStatus30 = inputStream.readInt();

		currentTime = inputStream.readLong();

		tips = inputStream.readUTF();

		bossInfoBO=new WorldBossInfoBO();    
		bossInfoBO.decode(inputStream);

		userBossInfo=new UserBossInfoBO();    
		userBossInfo.decode(inputStream);

		weatherInfo=new WeatherInfoBO();    
		weatherInfo.decode(inputStream);


	}
	
		/**用户对象**/
    public UserBO getUserBO() {
		return userBO;
	}
	/**用户对象**/
    public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
	}
	/**用户任务列表**/
    public List<UserTaskBO> getUserTaskList() {
		return userTaskList;
	}
	/**用户任务列表**/
    public void setUserTaskList(List<UserTaskBO> userTaskList) {
		this.userTaskList = userTaskList;
	}
	/**用户英雄列表**/
    public List<UserHeroBO> getUserHeroList() {
		return userHeroList;
	}
	/**用户英雄列表**/
    public void setUserHeroList(List<UserHeroBO> userHeroList) {
		this.userHeroList = userHeroList;
	}
	/**用户英雄技能列表**/
    public List<UserHeroSkillBO> getUserHeroSkillList() {
		return userHeroSkillList;
	}
	/**用户英雄技能列表**/
    public void setUserHeroSkillList(List<UserHeroSkillBO> userHeroSkillList) {
		this.userHeroSkillList = userHeroSkillList;
	}
	/**用户道具列表**/
    public List<UserToolBO> getUserToolList() {
		return userToolList;
	}
	/**用户道具列表**/
    public void setUserToolList(List<UserToolBO> userToolList) {
		this.userToolList = userToolList;
	}
	/**用户装备列表**/
    public List<UserEquipBO> getUserEquipList() {
		return userEquipList;
	}
	/**用户装备列表**/
    public void setUserEquipList(List<UserEquipBO> userEquipList) {
		this.userEquipList = userEquipList;
	}
	/**用户宝石列表**/
    public List<UserGemstoneBO> getUserGemstoneList() {
		return userGemstoneList;
	}
	/**用户宝石列表**/
    public void setUserGemstoneList(List<UserGemstoneBO> userGemstoneList) {
		this.userGemstoneList = userGemstoneList;
	}
	/**系统活动列表**/
    public List<SystemActivityBO> getSystemActivityList() {
		return systemActivityList;
	}
	/**系统活动列表**/
    public void setSystemActivityList(List<SystemActivityBO> systemActivityList) {
		this.systemActivityList = systemActivityList;
	}
	/**用户已开启场景id列表**/
    public List<Integer> getUserOpenSceneIdList() {
		return userOpenSceneIdList;
	}
	/**用户已开启场景id列表**/
    public void setUserOpenSceneIdList(List<Integer> userOpenSceneIdList) {
		this.userOpenSceneIdList = userOpenSceneIdList;
	}
	/**用户已领取声望奖励id列表**/
    public List<Integer> getUserPrestigeRewardIdList() {
		return userPrestigeRewardIdList;
	}
	/**用户已领取声望奖励id列表**/
    public void setUserPrestigeRewardIdList(List<Integer> userPrestigeRewardIdList) {
		this.userPrestigeRewardIdList = userPrestigeRewardIdList;
	}
	/**系统配置信息,详情请查看数值表中系统配置表中的定义**/
    public Map<String,String> getSystemConfig() {
		return systemConfig;
	}
	/**系统配置信息,详情请查看数值表中系统配置表中的定义**/
    public void setSystemConfig(Map<String,String> systemConfig) {
		this.systemConfig = systemConfig;
	}
	/**是否有新邮件1有0没有**/
    public Integer getMailStatus() {
		return mailStatus;
	}
	/**是否有新邮件1有0没有**/
    public void setMailStatus(Integer mailStatus) {
		this.mailStatus = mailStatus;
	}
	/**同屏显示人数**/
    public Integer getDisplayNum() {
		return displayNum;
	}
	/**同屏显示人数**/
    public void setDisplayNum(Integer displayNum) {
		this.displayNum = displayNum;
	}
	/**记录已走过的所有新手引导的步奏（99999为跳过新手引导）**/
    public String getRecordGuideStep() {
		return recordGuideStep;
	}
	/**记录已走过的所有新手引导的步奏（99999为跳过新手引导）**/
    public void setRecordGuideStep(String recordGuideStep) {
		this.recordGuideStep = recordGuideStep;
	}
	/**开启过的地图**/
    public String getOpenMaps() {
		return openMaps;
	}
	/**开启过的地图**/
    public void setOpenMaps(String openMaps) {
		this.openMaps = openMaps;
	}
	/**竞技场排名（-1为未进入过竞技场）**/
    public Integer getPkRank() {
		return pkRank;
	}
	/**竞技场排名（-1为未进入过竞技场）**/
    public void setPkRank(Integer pkRank) {
		this.pkRank = pkRank;
	}
	/**每月签到状态0未领取1已领取**/
    public Integer getLoginRewardStatus30() {
		return loginRewardStatus30;
	}
	/**每月签到状态0未领取1已领取**/
    public void setLoginRewardStatus30(Integer loginRewardStatus30) {
		this.loginRewardStatus30 = loginRewardStatus30;
	}
	/**服务器当前时间**/
    public Long getCurrentTime() {
		return currentTime;
	}
	/**服务器当前时间**/
    public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}
	/**用户弹窗提示**/
    public String getTips() {
		return tips;
	}
	/**用户弹窗提示**/
    public void setTips(String tips) {
		this.tips = tips;
	}
	/**世界boss的相关信息(空的话boss还未出现)**/
    public WorldBossInfoBO getBossInfoBO() {
		return bossInfoBO;
	}
	/**世界boss的相关信息(空的话boss还未出现)**/
    public void setBossInfoBO(WorldBossInfoBO bossInfoBO) {
		this.bossInfoBO = bossInfoBO;
	}
	/**用户boss战的相关信息(空的话用户未攻击过boss)**/
    public UserBossInfoBO getUserBossInfo() {
		return userBossInfo;
	}
	/**用户boss战的相关信息(空的话用户未攻击过boss)**/
    public void setUserBossInfo(UserBossInfoBO userBossInfo) {
		this.userBossInfo = userBossInfo;
	}
	/**当前天气信息**/
    public WeatherInfoBO getWeatherInfo() {
		return weatherInfo;
	}
	/**当前天气信息**/
    public void setWeatherInfo(WeatherInfoBO weatherInfo) {
		this.weatherInfo = weatherInfo;
	}

	
	
}
