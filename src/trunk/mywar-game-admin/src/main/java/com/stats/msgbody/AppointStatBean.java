package com.stats.msgbody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/* 管理后台查询task执行情况,cmdCode=6022  */
public class AppointStatBean implements ICodeAble {

	/**  key:cmdCode,value:执行结果 **/
	private Map<String,StatBean> taskStatBeanMap=new HashMap<String,StatBean>() ;

	public AppointStatBean(){}

	public void decode(IXInputStream dataInputStream) throws IOException {
		int taskStatBeanMapSize = dataInputStream.readInt();
		
		for(int i=0;i<taskStatBeanMapSize;i++)
		{
			String key = new String();
			key = dataInputStream.readUTF();
			StatBean t = new StatBean();
			t.decode(dataInputStream);
			taskStatBeanMap.put(key,t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		if(taskStatBeanMap!=null)
		{
			dataOutputStream.writeInt(taskStatBeanMap.size());
			Iterator<String> it =taskStatBeanMap.keySet().iterator();;
			while(it.hasNext())
			{
				String key = it.next();
				dataOutputStream.writeUTF(key);
				StatBean t= (StatBean)taskStatBeanMap.get(key);
				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

	}

	public void putTaskStatBeanMap(String key , StatBean value ) {
		taskStatBeanMap.put(key , value);
	}

	public void delTaskStatBeanMap(String key) {
		taskStatBeanMap.remove(key);
	}

	public void delAllTaskStatBeanMap() {
		taskStatBeanMap.clear();
	}

	public StatBean getTaskStatBeanMap(String key) {
		return (StatBean)taskStatBeanMap.get(key);
	}

	public Map<String, StatBean> getTaskStatBeanMap() {
		return taskStatBeanMap;
	}

	public void setTaskStatBeanMap(Map<String, StatBean> taskStatBeanMap) {
		this.taskStatBeanMap = taskStatBeanMap;
	}

}