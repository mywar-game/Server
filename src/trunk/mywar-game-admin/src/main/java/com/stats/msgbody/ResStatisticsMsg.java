package com.stats.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/* 管理后台查询task执行情况,cmdCode=6022  */
public class ResStatisticsMsg implements ICodeAble {

	/**  当前是否开启:1开启2关闭 **/
	private int showType;
	/**  key:cmdCode,value:执行结果 **/
	private Map<IntM,StatBean> taskStatBeanMap=new HashMap<IntM,StatBean>() ;
	/**  key:cmdCode,value:引用cache信息 **/
	private Map<IntM,AppointStatBean> appointStatBeanMap=new HashMap<IntM,AppointStatBean>() ;

	public ResStatisticsMsg(){}

	public ResStatisticsMsg(int showType){
		this.showType=showType;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		showType = dataInputStream.readInt();
		int taskStatBeanMapSize = dataInputStream.readInt();
		
		for(int i=0;i<taskStatBeanMapSize;i++)
		{
			IntM key = new IntM();
			key.decode(dataInputStream);
			StatBean t = new StatBean();
			t.decode(dataInputStream);
			taskStatBeanMap.put(key,t);
		}

		int appointStatBeanMapSize = dataInputStream.readInt();
		
		for(int i=0;i<appointStatBeanMapSize;i++)
		{
			IntM key = new IntM();
			key.decode(dataInputStream);
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
			Iterator<IntM> it =taskStatBeanMap.keySet().iterator();;
			while(it.hasNext())
			{
				IntM key = it.next();
				key.encode(dataOutputStream);
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
			Iterator<IntM> it =appointStatBeanMap.keySet().iterator();;
			while(it.hasNext())
			{
				IntM key = it.next();
				key.encode(dataOutputStream);
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
		taskStatBeanMap.put(new IntM(key) , value);
	}

	public void delTaskStatBeanMap(int key) {
		taskStatBeanMap.remove(new IntM(key));
	}

	public void delAllTaskStatBeanMap() {
		taskStatBeanMap.clear();
	}

	public StatBean getTaskStatBeanMap(int key) {
		return (StatBean)taskStatBeanMap.get(new IntM(key));
	}

	public void putAppointStatBeanMap(int key , AppointStatBean value ) {
		appointStatBeanMap.put(new IntM(key) , value);
	}

	public void delAppointStatBeanMap(int key) {
		appointStatBeanMap.remove(new IntM(key));
	}

	public void delAllAppointStatBeanMap() {
		appointStatBeanMap.clear();
	}

	public AppointStatBean getAppointStatBeanMap(int key) {
		return (AppointStatBean)appointStatBeanMap.get(new IntM(key));
	}

	public Map<IntM, StatBean> getTaskStatBeanMap() {
		return taskStatBeanMap;
	}

	public void setTaskStatBeanMap(Map<IntM, StatBean> taskStatBeanMap) {
		this.taskStatBeanMap = taskStatBeanMap;
	}

	public Map<IntM, AppointStatBean> getAppointStatBeanMap() {
		return appointStatBeanMap;
	}

	public void setAppointStatBeanMap(Map<IntM, AppointStatBean> appointStatBeanMap) {
		this.appointStatBeanMap = appointStatBeanMap;
	}

}