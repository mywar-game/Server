package com.stats.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* 管理后台查询战斗信息情况,cmdCode=5801  */
public class ResBattleInfoMsg implements ICodeAble {

	/**  正在战斗的次数 **/
	private int battleIngNum;
	/**  总战斗的次数 **/
	private int totalBattleNum;
	/**  竞技场匹配人数_排位赛匹配人数 **/
	private String matchNum= new String() ;

	public ResBattleInfoMsg(){}

	public ResBattleInfoMsg(int battleIngNum , int totalBattleNum , String matchNum){
		this.battleIngNum=battleIngNum;
		this.totalBattleNum=totalBattleNum;
		this.matchNum=matchNum;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		battleIngNum = dataInputStream.readInt();
		totalBattleNum = dataInputStream.readInt();
		matchNum = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(battleIngNum);
		dataOutputStream.writeInt(totalBattleNum);
		dataOutputStream.writeUTF(matchNum);
	}

	public void setBattleIngNum(int battleIngNum) {
		this.battleIngNum = battleIngNum;
	}

	public int getBattleIngNum() {
		return battleIngNum;
	}

	public void setTotalBattleNum(int totalBattleNum) {
		this.totalBattleNum = totalBattleNum;
	}

	public int getTotalBattleNum() {
		return totalBattleNum;
	}

	public void setMatchNum(String matchNum) {
		this.matchNum = matchNum;
	}

	public String getMatchNum() {
		return matchNum;
	}

}