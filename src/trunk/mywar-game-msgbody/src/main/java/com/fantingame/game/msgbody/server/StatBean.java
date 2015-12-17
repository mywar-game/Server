package com.fantingame.game.msgbody.server;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

import java.io.IOException;

/* 管理后台查询task执行情况,cmdCode=6022  */
public class StatBean implements ICodeAble {

	/**  执行次数 **/
	private int times;
	/**  执行总时间 **/
	private String allTime= new String() ;
	/**  流量总计字节数 **/
	private String writeallbytes= new String() ;
	/**  统计时长 **/
	private String timeDiffence= new String() ;

	public StatBean(){}

	public StatBean(int times , String allTime , String writeallbytes , String timeDiffence){
		this.times=times;
		this.allTime=allTime;
		this.writeallbytes=writeallbytes;
		this.timeDiffence=timeDiffence;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		times = dataInputStream.readInt();
		allTime = dataInputStream.readUTF();
		writeallbytes = dataInputStream.readUTF();
		timeDiffence = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(times);
		dataOutputStream.writeUTF(allTime);
		dataOutputStream.writeUTF(writeallbytes);
		dataOutputStream.writeUTF(timeDiffence);
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getTimes() {
		return times;
	}

	public void setAllTime(String allTime) {
		this.allTime = allTime;
	}

	public String getAllTime() {
		return allTime;
	}

	public void setWriteallbytes(String writeallbytes) {
		this.writeallbytes = writeallbytes;
	}

	public String getWriteallbytes() {
		return writeallbytes;
	}

	public String getTimeDiffence() {
		return timeDiffence;
	}

	public void setTimeDiffence(String timeDiffence) {
		this.timeDiffence = timeDiffence;
	}

}