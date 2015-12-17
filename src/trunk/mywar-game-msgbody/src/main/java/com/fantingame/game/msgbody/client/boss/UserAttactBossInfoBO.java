package com.fantingame.game.msgbody.client.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户攻击Boss信息对象**/
public class UserAttactBossInfoBO implements ICodeAble {

		/**用户id**/
	private String userId="";
	/**是否掉血**/
	private Integer isBlood=0;
	/**是否躲闪**/
	private Integer bDodge=0;
	/**是否格挡**/
	private Integer bParry=0;
	/**是否暴击**/
	private Integer bCrit=0;
	/**效果ID**/
	private Integer effectId=0;
	/**资源ID**/
	private Integer resId=0;
	/**召唤英雄ID(变身时使用）**/
	private Integer callHeroId=0;
	/**法术反射**/
	private Integer beingAttackState=0;
	/**对Boss伤害值**/
	private Integer hurt=0;
	/**治疗量**/
	private Integer treatment=0;
	/**承受伤害值**/
	private Integer beHit=0;
	/**目标用户**/
	private String targetUserId="";
	/**是否死亡1还鲜活着0挂了**/
	private Integer status=0;
	/**死亡时间**/
	private Long dieTime=0l;
	/**坐标x**/
	private Integer x=0;
	/**坐标y**/
	private Integer y=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeInt(isBlood);

		outputStream.writeInt(bDodge);

		outputStream.writeInt(bParry);

		outputStream.writeInt(bCrit);

		outputStream.writeInt(effectId);

		outputStream.writeInt(resId);

		outputStream.writeInt(callHeroId);

		outputStream.writeInt(beingAttackState);

		outputStream.writeInt(hurt);

		outputStream.writeInt(treatment);

		outputStream.writeInt(beHit);

		outputStream.writeUTF(targetUserId);

		outputStream.writeInt(status);

		outputStream.writeLong(dieTime);

		outputStream.writeInt(x);

		outputStream.writeInt(y);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		isBlood = inputStream.readInt();

		bDodge = inputStream.readInt();

		bParry = inputStream.readInt();

		bCrit = inputStream.readInt();

		effectId = inputStream.readInt();

		resId = inputStream.readInt();

		callHeroId = inputStream.readInt();

		beingAttackState = inputStream.readInt();

		hurt = inputStream.readInt();

		treatment = inputStream.readInt();

		beHit = inputStream.readInt();

		targetUserId = inputStream.readUTF();

		status = inputStream.readInt();

		dieTime = inputStream.readLong();

		x = inputStream.readInt();

		y = inputStream.readInt();


	}
	
		/**用户id**/
    public String getUserId() {
		return userId;
	}
	/**用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**是否掉血**/
    public Integer getIsBlood() {
		return isBlood;
	}
	/**是否掉血**/
    public void setIsBlood(Integer isBlood) {
		this.isBlood = isBlood;
	}
	/**是否躲闪**/
    public Integer getBDodge() {
		return bDodge;
	}
	/**是否躲闪**/
    public void setBDodge(Integer bDodge) {
		this.bDodge = bDodge;
	}
	/**是否格挡**/
    public Integer getBParry() {
		return bParry;
	}
	/**是否格挡**/
    public void setBParry(Integer bParry) {
		this.bParry = bParry;
	}
	/**是否暴击**/
    public Integer getBCrit() {
		return bCrit;
	}
	/**是否暴击**/
    public void setBCrit(Integer bCrit) {
		this.bCrit = bCrit;
	}
	/**效果ID**/
    public Integer getEffectId() {
		return effectId;
	}
	/**效果ID**/
    public void setEffectId(Integer effectId) {
		this.effectId = effectId;
	}
	/**资源ID**/
    public Integer getResId() {
		return resId;
	}
	/**资源ID**/
    public void setResId(Integer resId) {
		this.resId = resId;
	}
	/**召唤英雄ID(变身时使用）**/
    public Integer getCallHeroId() {
		return callHeroId;
	}
	/**召唤英雄ID(变身时使用）**/
    public void setCallHeroId(Integer callHeroId) {
		this.callHeroId = callHeroId;
	}
	/**法术反射**/
    public Integer getBeingAttackState() {
		return beingAttackState;
	}
	/**法术反射**/
    public void setBeingAttackState(Integer beingAttackState) {
		this.beingAttackState = beingAttackState;
	}
	/**对Boss伤害值**/
    public Integer getHurt() {
		return hurt;
	}
	/**对Boss伤害值**/
    public void setHurt(Integer hurt) {
		this.hurt = hurt;
	}
	/**治疗量**/
    public Integer getTreatment() {
		return treatment;
	}
	/**治疗量**/
    public void setTreatment(Integer treatment) {
		this.treatment = treatment;
	}
	/**承受伤害值**/
    public Integer getBeHit() {
		return beHit;
	}
	/**承受伤害值**/
    public void setBeHit(Integer beHit) {
		this.beHit = beHit;
	}
	/**目标用户**/
    public String getTargetUserId() {
		return targetUserId;
	}
	/**目标用户**/
    public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}
	/**是否死亡1还鲜活着0挂了**/
    public Integer getStatus() {
		return status;
	}
	/**是否死亡1还鲜活着0挂了**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**死亡时间**/
    public Long getDieTime() {
		return dieTime;
	}
	/**死亡时间**/
    public void setDieTime(Long dieTime) {
		this.dieTime = dieTime;
	}
	/**坐标x**/
    public Integer getX() {
		return x;
	}
	/**坐标x**/
    public void setX(Integer x) {
		this.x = x;
	}
	/**坐标y**/
    public Integer getY() {
		return y;
	}
	/**坐标y**/
    public void setY(Integer y) {
		this.y = y;
	}

	
	
}
