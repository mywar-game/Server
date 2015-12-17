package com.stats.msgbody;


import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* 管理后台查询task执行情况,cmdCode=6022  */
public class StatBean implements ICodeAble {

	/**  执行次数 **/
	private int times;
	/**  执行总时间 **/
	private String allTime= new String() ;
	/**  流量总计字节数 **/
	private String writeallbytes= new String() ;
	/**  平均时间 **/
	private String averageTime;
	/**  平均字节/秒 **/
	private String averageDataTime;
	/**  统计时长 **/
	private String timeDiffence= new String() ;
	/**  正在进行的战斗场次 **/
	private int battleIngNum;
	/**  总战斗场次 **/
	private int totalBattleNum;
	/**  竞技场匹配的房间数量 **/
	private int arenaBattleNum;
	/**  排位赛匹配的房间数量 **/
	private int qualifyBattleNum;

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

	public String getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(String averageTime) {
		this.averageTime = averageTime;
	}

	public String getAverageDataTime() {
		return averageDataTime;
	}

	public void setAverageDataTime(String averageDataTime) {
		this.averageDataTime = averageDataTime;
	}

	public String getTimeDiffence() {
		return timeDiffence;
	}

	public void setTimeDiffence(String timeDiffence) {
		this.timeDiffence = timeDiffence;
	}

	public int getBattleIngNum() {
		return battleIngNum;
	}

	public void setBattleIngNum(int battleIngNum) {
		this.battleIngNum = battleIngNum;
	}

	public int getTotalBattleNum() {
		return totalBattleNum;
	}

	public void setTotalBattleNum(int totalBattleNum) {
		this.totalBattleNum = totalBattleNum;
	}

	public int getArenaBattleNum() {
		return arenaBattleNum;
	}

	public void setArenaBattleNum(int arenaBattleNum) {
		this.arenaBattleNum = arenaBattleNum;
	}

	public int getQualifyBattleNum() {
		return qualifyBattleNum;
	}

	public void setQualifyBattleNum(int qualifyBattleNum) {
		this.qualifyBattleNum = qualifyBattleNum;
	}

}