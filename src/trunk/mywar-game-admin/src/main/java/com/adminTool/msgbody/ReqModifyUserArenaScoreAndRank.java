package com.adminTool.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/* ModifyUserArenaScoreAndRankTask请求消息体，cmdCode=6025  */
public class ReqModifyUserArenaScoreAndRank implements ICodeAble {

	/**  用户编号 **/
	private String userId= new String() ;
	/**  竞技场积分 **/
	private int arenaScore;
	/**  排位赛等级 **/
	private int arenaRank;

	public ReqModifyUserArenaScoreAndRank(){}

	public ReqModifyUserArenaScoreAndRank(String userId , int arenaScore , int arenaRank){
		this.userId=userId;
		this.arenaScore=arenaScore;
		this.arenaRank=arenaRank;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userId = dataInputStream.readUTF();
		arenaScore = dataInputStream.readInt();
		arenaRank = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userId);
		dataOutputStream.writeInt(arenaScore);
		dataOutputStream.writeInt(arenaRank);
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setArenaScore(int arenaScore) {
		this.arenaScore = arenaScore;
	}

	public int getArenaScore() {
		return arenaScore;
	}

	public void setArenaRank(int arenaRank) {
		this.arenaRank = arenaRank;
	}

	public int getArenaRank() {
		return arenaRank;
	}

}