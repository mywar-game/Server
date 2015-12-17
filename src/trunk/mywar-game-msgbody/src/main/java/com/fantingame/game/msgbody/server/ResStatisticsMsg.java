package com.fantingame.game.msgbody.server;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/* 管理后台查询task执行情况,cmdCode=6022  */
public class ResStatisticsMsg implements ICodeAble {

	/**  当前是否开启:1开启2关闭 **/
	private int showType;
	/**  key:cmdCode,value:执行结果 **/
	private Map<Integer,StatBean> taskStatBeanMap=new HashMap<Integer,StatBean>() ;
	/**  key:cmdCode,value:引用cache信息 **/
	private Map<Integer,AppointStatBean> appointStatBeanMap=new HashMap<Integer,AppointStatBean>() ;

	public ResStatisticsMsg(){}

	public ResStatisticsMsg(int showType){
		this.showType=showType;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		showType = dataInputStream.readInt();
		int taskStatBeanMapSize = dataInputStream.readInt();
		
		for(int i=0;i<taskStatBeanMapSize;i++)
		{
			Integer key = dataInputStream.readInt();
			StatBean t = new StatBean();
			t.decode(dataInputStream);
			taskStatBeanMap.put(key,t);
		}

		int appointStatBeanMapSize = dataInputStream.readInt();
		
		for(int i=0;i<appointStatBeanMapSize;i++)
		{
			Integer key =dataInputStream.readInt();
			AppointStatBean t = new AppointStatBean();
			t.decode(dataInputStream);
			appointStatBeanMap.put(key,t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(showType);
		if(taskStatBeanMap!=null)
		{
			dataOutputStream.writeInt(taskStatBeanMap.size());
			Iterator<Integer> it =taskStatBeanMap.keySet().iterator();;
			while(it.hasNext())
			{
				Integer key = it.next();
				dataOutputStream.writeInt(key);
				StatBean t= (StatBean)taskStatBeanMap.get(key);
				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

		if(appointStatBeanMap!=null)
		{
			dataOutputStream.writeInt(appointStatBeanMap.size());
			Iterator<Integer> it =appointStatBeanMap.keySet().iterator();;
			while(it.hasNext())
			{
				Integer key = it.next();
				dataOutputStream.writeInt(key);
				AppointStatBean t= (AppointStatBean)appointStatBeanMap.get(key);
				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

	}

	public void setShowType(int showType) {
		this.showType = showType;
	}

	public int getShowType() {
		return showType;
	}

	public void putTaskStatBeanMap(int key , StatBean value ) {
		taskStatBeanMap.put(new Integer(key) , value);
	}

	public void delTaskStatBeanMap(int key) {
		taskStatBeanMap.remove(new Integer(key));
	}

	public void delAllTaskStatBeanMap() {
		taskStatBeanMap.clear();
	}

	public StatBean getTaskStatBeanMap(int key) {
		return (StatBean)taskStatBeanMap.get(new Integer(key));
	}

	public void putAppointStatBeanMap(int key , AppointStatBean value ) {
		appointStatBeanMap.put(new Integer(key) , value);
	}

	public void delAppointStatBeanMap(int key) {
		appointStatBeanMap.remove(new Integer(key));
	}

	public void delAllAppointStatBeanMap() {
		appointStatBeanMap.clear();
	}

	public AppointStatBean getAppointStatBeanMap(int key) {
		return (AppointStatBean)appointStatBeanMap.get(new Integer(key));
	}

	public Map<Integer, StatBean> getTaskStatBeanMap() {
		return taskStatBeanMap;
	}

	public void setTaskStatBeanMap(Map<Integer, StatBean> taskStatBeanMap) {
		this.taskStatBeanMap = taskStatBeanMap;
	}

	public Map<Integer, AppointStatBean> getAppointStatBeanMap() {
		return appointStatBeanMap;
	}

	public void setAppointStatBeanMap(Map<Integer, AppointStatBean> appointStatBeanMap) {
		this.appointStatBeanMap = appointStatBeanMap;
	}

}