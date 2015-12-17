package com.fantingame.game.msgbody.user;

import java.io.IOException;
import java.util.Date;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

public class ResGetUserInfo implements ICodeAble {
    
	private String userId;

	private long gameId;

	private String username;

	private int level;

	private long goldNum;

	private long copper;

	private long exp;

	private Date regTime;

	private int vipLevel;

	private long vipExpired;

	private int guideStep;

	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);
		outputStream.writeLong(gameId);
		outputStream.writeUTF(username);
		outputStream.writeInt(level);
		outputStream.writeLong(goldNum);
		outputStream.writeLong(copper);
		outputStream.writeLong(exp);
		outputStream.writeLong(regTime.getTime());
		outputStream.writeInt(vipLevel);
		outputStream.writeLong(vipExpired);
		outputStream.writeInt(guideStep);
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();
		gameId = inputStream.readLong();
		username = inputStream.readUTF();
		level = inputStream.readInt();
		goldNum = inputStream.readLong();
		copper = inputStream.readLong();
		exp = inputStream.readLong();
		regTime = new Date(inputStream.readLong());
		vipLevel = inputStream.readInt();
		vipExpired = inputStream.readLong();
		guideStep = inputStream.readInt();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getGoldNum() {
		return goldNum;
	}

	public void setGoldNum(long goldNum) {
		this.goldNum = goldNum;
	}

	public long getCopper() {
		return copper;
	}

	public void setCopper(long copper) {
		this.copper = copper;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public int getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}

	public long getVipExpired() {
		return vipExpired;
	}

	public void setVipExpired(long vipExpired) {
		this.vipExpired = vipExpired;
	}

	public int getGuideStep() {
		return guideStep;
	}

	public void setGuideStep(int guideStep) {
		this.guideStep = guideStep;
	}

}
