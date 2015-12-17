package com.fantingame.game.msgbody.notify.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户对象信息**/
public class UserBO implements ICodeAble {

		/**用户编号**/
	private String userId="";
	/**游戏id**/
	private Integer ftId=0;
	/**角色名称**/
	private String roleName="";
	/**用户等级**/
	private Integer level=0;
	/**用户经验**/
	private Integer exp=0;
	/**用户人民币**/
	private Integer money=0;
	/**用户金币**/
	private Integer gold=0;
	/**1联盟，2部落**/
	private Integer camp=0;
	/**体力值**/
	private Integer power=0;
	/**荣誉**/
	private Integer honour=0;
	/**用户vip等级**/
	private Integer vipLevel=0;
	/**vip经验**/
	private Integer vipExp=0;
	/**职业经验**/
	private Integer jobExp=0;
	/**在线时长，单位秒**/
	private Integer allOnLineTime=0;
	/**战斗力**/
	private Integer effective=0;
	/**背包扩展次数**/
	private Integer packExtendTimes=0;
	/**仓库扩展次数**/
	private Integer storehouseExtendTimes=0;
	/**上次登出所处位置格式为(sceneId,x,y),如果为空则说明该玩家刚刚创角，还未进入过游戏**/
	private String prePosition="";
	/**用户注册时间戳**/
	private Long regTime=0l;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeInt(ftId);

		outputStream.writeUTF(roleName);

		outputStream.writeInt(level);

		outputStream.writeInt(exp);

		outputStream.writeInt(money);

		outputStream.writeInt(gold);

		outputStream.writeInt(camp);

		outputStream.writeInt(power);

		outputStream.writeInt(honour);

		outputStream.writeInt(vipLevel);

		outputStream.writeInt(vipExp);

		outputStream.writeInt(jobExp);

		outputStream.writeInt(allOnLineTime);

		outputStream.writeInt(effective);

		outputStream.writeInt(packExtendTimes);

		outputStream.writeInt(storehouseExtendTimes);

		outputStream.writeUTF(prePosition);

		outputStream.writeLong(regTime);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		ftId = inputStream.readInt();

		roleName = inputStream.readUTF();

		level = inputStream.readInt();

		exp = inputStream.readInt();

		money = inputStream.readInt();

		gold = inputStream.readInt();

		camp = inputStream.readInt();

		power = inputStream.readInt();

		honour = inputStream.readInt();

		vipLevel = inputStream.readInt();

		vipExp = inputStream.readInt();

		jobExp = inputStream.readInt();

		allOnLineTime = inputStream.readInt();

		effective = inputStream.readInt();

		packExtendTimes = inputStream.readInt();

		storehouseExtendTimes = inputStream.readInt();

		prePosition = inputStream.readUTF();

		regTime = inputStream.readLong();


	}
	
		/**用户编号**/
    public String getUserId() {
		return userId;
	}
	/**用户编号**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**游戏id**/
    public Integer getFtId() {
		return ftId;
	}
	/**游戏id**/
    public void setFtId(Integer ftId) {
		this.ftId = ftId;
	}
	/**角色名称**/
    public String getRoleName() {
		return roleName;
	}
	/**角色名称**/
    public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**用户等级**/
    public Integer getLevel() {
		return level;
	}
	/**用户等级**/
    public void setLevel(Integer level) {
		this.level = level;
	}
	/**用户经验**/
    public Integer getExp() {
		return exp;
	}
	/**用户经验**/
    public void setExp(Integer exp) {
		this.exp = exp;
	}
	/**用户人民币**/
    public Integer getMoney() {
		return money;
	}
	/**用户人民币**/
    public void setMoney(Integer money) {
		this.money = money;
	}
	/**用户金币**/
    public Integer getGold() {
		return gold;
	}
	/**用户金币**/
    public void setGold(Integer gold) {
		this.gold = gold;
	}
	/**1联盟，2部落**/
    public Integer getCamp() {
		return camp;
	}
	/**1联盟，2部落**/
    public void setCamp(Integer camp) {
		this.camp = camp;
	}
	/**体力值**/
    public Integer getPower() {
		return power;
	}
	/**体力值**/
    public void setPower(Integer power) {
		this.power = power;
	}
	/**荣誉**/
    public Integer getHonour() {
		return honour;
	}
	/**荣誉**/
    public void setHonour(Integer honour) {
		this.honour = honour;
	}
	/**用户vip等级**/
    public Integer getVipLevel() {
		return vipLevel;
	}
	/**用户vip等级**/
    public void setVipLevel(Integer vipLevel) {
		this.vipLevel = vipLevel;
	}
	/**vip经验**/
    public Integer getVipExp() {
		return vipExp;
	}
	/**vip经验**/
    public void setVipExp(Integer vipExp) {
		this.vipExp = vipExp;
	}
	/**职业经验**/
    public Integer getJobExp() {
		return jobExp;
	}
	/**职业经验**/
    public void setJobExp(Integer jobExp) {
		this.jobExp = jobExp;
	}
	/**在线时长，单位秒**/
    public Integer getAllOnLineTime() {
		return allOnLineTime;
	}
	/**在线时长，单位秒**/
    public void setAllOnLineTime(Integer allOnLineTime) {
		this.allOnLineTime = allOnLineTime;
	}
	/**战斗力**/
    public Integer getEffective() {
		return effective;
	}
	/**战斗力**/
    public void setEffective(Integer effective) {
		this.effective = effective;
	}
	/**背包扩展次数**/
    public Integer getPackExtendTimes() {
		return packExtendTimes;
	}
	/**背包扩展次数**/
    public void setPackExtendTimes(Integer packExtendTimes) {
		this.packExtendTimes = packExtendTimes;
	}
	/**仓库扩展次数**/
    public Integer getStorehouseExtendTimes() {
		return storehouseExtendTimes;
	}
	/**仓库扩展次数**/
    public void setStorehouseExtendTimes(Integer storehouseExtendTimes) {
		this.storehouseExtendTimes = storehouseExtendTimes;
	}
	/**上次登出所处位置格式为(sceneId,x,y),如果为空则说明该玩家刚刚创角，还未进入过游戏**/
    public String getPrePosition() {
		return prePosition;
	}
	/**上次登出所处位置格式为(sceneId,x,y),如果为空则说明该玩家刚刚创角，还未进入过游戏**/
    public void setPrePosition(String prePosition) {
		this.prePosition = prePosition;
	}
	/**用户注册时间戳**/
    public Long getRegTime() {
		return regTime;
	}
	/**用户注册时间戳**/
    public void setRegTime(Long regTime) {
		this.regTime = regTime;
	}

	
	
}
