package com.adminTool.bean;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* 玩家排行快照  */
public class UserRankSnapshot implements ICodeAble {

	/**  用户编号 **/
	private String userId= new String() ;
	/**  角色名 **/
	private String name= new String() ;
	/**  排行 **/
	private int rank;
	/**  备注 **/
	private String note= new String() ;

	public UserRankSnapshot(){}

	public UserRankSnapshot(String userId , String name , int rank , String note){
		this.userId=userId;
		this.name=name;
		this.rank=rank;
		this.note=note;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userId = dataInputStream.readUTF();
		name = dataInputStream.readUTF();
		rank = dataInputStream.readInt();
		note = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userId);
		dataOutputStream.writeUTF(name);
		dataOutputStream.writeInt(rank);
		dataOutputStream.writeUTF(note);
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

}