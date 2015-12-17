package com.adminTool.bean;

import java.io.IOException;
import java.util.Date;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/**
 * 玩家用户表
 */
public class UserBean implements ICodeAble {
	
	/** 用户id */
	private long userId;
	
	/** 用户名 */
	private String userName;
	
	/** 用户密码 */
	//private String password; 
	
	/** 玩家角色名称 */
	private String name;
	
	/** 用户头像 */
	private int userImage;
	
	/** 用户性别(0男1女) */
	private int sex;

	/** 平台账号*/
	private String fromID;
	
	/** 玩家排行 */
	private int rank;
	
	/** 玩家等级 */
	private int level;
	
	/** 粮食 */
	private int grain;
	
	/** 矿石 */
	private int mineral;
	
	/** 玩家声望 */
	private int renown;
	
	/** 玩家状态 (0新手，1正常) */
	private int state;
	
	/**1.禁言  2封号 3踢线结束时间**/
	private Date stateFinishTime;
	
	/**1.禁言  2封号 3踢线**/
	private int stateTreatment;
	
	/** 玩家军团编号 */
	private long guildId;
	
	/** 军团名称 */
	private String guildName;
	
	/** 玩家游戏币 */
	private int money;
	
	/** 玩家金币 */
	private int golden;
	
	/** 玩家银币 */
	private int silver;
	
	/** 最后一次登录时间 */
	private Date lastLogOutTime;

	/** 资源存储上线  */
	private int maxResourceStore;
	
	/** 玩家所在区域地图 准备位置,不是d里那个areaId(区域id（例如北京，上海）-街道id（一个区域当中有多个街道）-街道中玩家位置)*/
	private String areaId;
	
	/**玩家解锁关卡位置:例如1_4代表解锁到大关卡1小关卡4这个位置,当玩家把当前区域的图推完之后将他设置成"FINISH"**/
	private String unlockPosition;
	
	/**ppve关卡解锁位置如果是空字符串则表示此城市的ppve还没解锁 如果是1_4则表示解锁到这个关卡位置**/
	private String unlockPositionPpve;
	
	/** 退出军团时间 */
	private Date exitGuildTime;
	
	/** 玩家恶名值  */
	private int usersNotorietyValue; 
	/** 玩家个性签名  */	
	private String sign;
	/** 竞技场积分 */
	private int arenaScore;
	/**玩家成就常量编号,没有则是0**/
	private int achievementId;
	/**账号类型1正常账号2测试账号 */
	private int type;
	/** 上次被进攻城市的时间 */
	private Date lastBeAttackCityTime;
	/** 进攻城市战斗状态 0.正常1.战斗中*/
	private int battleState;

	public int getAchievementId() {
		return achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}

	public int getUsersNotorietyValue() {
		return usersNotorietyValue;
	}

	public void setUsersNotorietyValue(int usersNotorietyValue) {
		this.usersNotorietyValue = usersNotorietyValue;
	}

	public String getUnlockPositionPpve() {
		return unlockPositionPpve;
	}

	public void setUnlockPositionPpve(String unlockPositionPpve) {
		this.unlockPositionPpve = unlockPositionPpve;
	}

	public String getUnlockPosition() {
		return unlockPosition;
	}

	public void setUnlockPosition(String unlockPosition) {
		this.unlockPosition = unlockPosition;
	}

	public int getGolden() {
		return golden;
	}

	public void setGolden(int golden) {
		this.golden = golden;
	}

	public int getSilver() {
		return silver;
	}

	public void setSilver(int silver) {
		this.silver = silver;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public int getMaxResourceStore() {
		return maxResourceStore;
	}

	public void setMaxResourceStore(int maxResourceStore) {
		this.maxResourceStore = maxResourceStore;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getGrain() {
		return grain;
	}

	public void setGrain(int grain) {
		this.grain = grain;
	}

	public int getMineral() {
		return mineral;
	}

	public void setMineral(int mineral) {
		this.mineral = mineral;
	}

	public int getRenown() {
		return renown;
	}

	public void setRenown(int renown) {
		this.renown = renown;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public int getUserImage() {
		return userImage;
	}

	public void setUserImage(int userImage) {
		this.userImage = userImage;
	}
	
	public Date getLastLogOutTime() {
		return lastLogOutTime;
	}

	public void setLastLogOutTime(Date lastLogOutTime) {
		this.lastLogOutTime = lastLogOutTime;
	}
	
	
	public long getGuildId() {
		return guildId;
	}

	public void setGuildId(long guildId) {
		this.guildId = guildId;
	}

	public Date getExitGuildTime() {
		return exitGuildTime;
	}

	public void setExitGuildTime(Date exitGuildTime) {
		this.exitGuildTime = exitGuildTime;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userId = Long.valueOf(dataInputStream.readUTF());
		userName = dataInputStream.readUTF();
		name = dataInputStream.readUTF();
		userImage = dataInputStream.readInt();
		sex = dataInputStream.readInt();
		fromID = dataInputStream.readUTF();
		rank = dataInputStream.readInt();
		level = dataInputStream.readInt();
		grain = dataInputStream.readInt();
		mineral = dataInputStream.readInt();
		renown = dataInputStream.readInt();
		state = dataInputStream.readInt();
		String str = dataInputStream.readUTF();
		if ("".equals(str)) {
			stateFinishTime = null;
		} else {
			stateFinishTime = new Date(Long.valueOf(str));
		}
		stateTreatment = dataInputStream.readInt();
		guildId = Long.valueOf(dataInputStream.readUTF());
		guildName = dataInputStream.readUTF();
		money = dataInputStream.readInt();
		golden = dataInputStream.readInt();
		silver = dataInputStream.readInt();
		lastLogOutTime = new Date(Long.valueOf(dataInputStream.readUTF()));
		maxResourceStore = dataInputStream.readInt();
		areaId = dataInputStream.readUTF();
		unlockPosition = dataInputStream.readUTF();
		unlockPositionPpve = dataInputStream.readUTF();
		exitGuildTime = new Date(Long.valueOf(dataInputStream.readUTF()));
		usersNotorietyValue = dataInputStream.readInt();
		sign = dataInputStream.readUTF();
		arenaScore = dataInputStream.readInt();
		achievementId = dataInputStream.readInt();
		type = dataInputStream.readInt();
		String str2 = dataInputStream.readUTF();
		if ("".equals(str2)) {
			lastBeAttackCityTime = null;
		} else {
			lastBeAttackCityTime = new Date(Long.valueOf(str2));
		}
		battleState = dataInputStream.readInt();;
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userId + "");
		dataOutputStream.writeUTF(userName);
		dataOutputStream.writeUTF(name);
		dataOutputStream.writeInt(userImage);
		dataOutputStream.writeInt(sex);
		dataOutputStream.writeUTF(fromID);
		dataOutputStream.writeInt(rank);
		dataOutputStream.writeInt(level);
		dataOutputStream.writeInt(grain);
		dataOutputStream.writeInt(mineral);
		dataOutputStream.writeInt(renown);
		dataOutputStream.writeInt(state);
		dataOutputStream.writeUTF(stateFinishTime == null?"":""+stateFinishTime.getTime());
		dataOutputStream.writeInt(stateTreatment);
		dataOutputStream.writeUTF(guildId + "");
		dataOutputStream.writeUTF(guildName+"");
		dataOutputStream.writeInt(money);
		dataOutputStream.writeInt(golden);
		dataOutputStream.writeInt(silver);
		if(lastLogOutTime != null){
			dataOutputStream.writeUTF(lastLogOutTime.getTime()+"");
		} else {
			dataOutputStream.writeUTF("");
		}
		
		dataOutputStream.writeInt(maxResourceStore);
		dataOutputStream.writeUTF(areaId);
		
		if (unlockPosition != null) {
			dataOutputStream.writeUTF(unlockPosition);
		} else {
			dataOutputStream.writeUTF("");
		}
		
		if (unlockPositionPpve != null) {
			dataOutputStream.writeUTF(unlockPositionPpve);
		} else {
			dataOutputStream.writeUTF("");
		}
		
		if(exitGuildTime != null){
			dataOutputStream.writeUTF(exitGuildTime.getTime()+"");
		} else {
			dataOutputStream.writeUTF("");
		}
		
		dataOutputStream.writeInt(usersNotorietyValue);
		
		if(sign==null){
			dataOutputStream.writeUTF("");
		}else{
			dataOutputStream.writeUTF(sign);
		}
		dataOutputStream.writeInt(arenaScore);
		dataOutputStream.writeInt(achievementId);
		dataOutputStream.writeInt(type);
		if(lastBeAttackCityTime != null){
			dataOutputStream.writeUTF(lastBeAttackCityTime.getTime()+"");
		} else {
			dataOutputStream.writeUTF("");
		}
		dataOutputStream.writeInt(battleState);
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	public int getArenaScore() {
		return arenaScore;
	}

	public void setArenaScore(int arenaScore) {
		this.arenaScore = arenaScore;
	}

	public Date getStateFinishTime() {
		return stateFinishTime;
	}

	public void setStateFinishTime(Date stateFinishTime) {
		this.stateFinishTime = stateFinishTime;
	}

	public int getStateTreatment() {
		return stateTreatment;
	}

	public void setStateTreatment(int stateTreatment) {
		this.stateTreatment = stateTreatment;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	public Date getLastBeAttackCityTime() {
		return lastBeAttackCityTime;
	}

	public void setLastBeAttackCityTime(Date lastBeAttackCityTime) {
		this.lastBeAttackCityTime = lastBeAttackCityTime;
	}

	public int getBattleState() {
		return battleState;
	}

	public void setBattleState(int battleState) {
		this.battleState = battleState;
	}
}
