package com.fantingame.game.msgbody.server;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* 缓存使用情况响应消息体cmdCode=6026  */
public class ResMemcacheInfoList implements ICodeAble {

	/**  总计缓存信息 **/
	private MemcacheInfo memcacheInfo= new MemcacheInfo() ;
	/**  缓存信息 **/
	private List<MemcacheInfo> memcacheInfoList= new ArrayList<MemcacheInfo>() ;

	public ResMemcacheInfoList(){}

	public ResMemcacheInfoList(MemcacheInfo memcacheInfo){
		this.memcacheInfo=memcacheInfo;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		memcacheInfo.decode(dataInputStream);
		int memcacheInfoListSize = dataInputStream.readInt();
		for(int i=0;i<memcacheInfoListSize;i++)
		{
			MemcacheInfo t = new  MemcacheInfo();
			t.decode(dataInputStream);
			memcacheInfoList.add(t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		memcacheInfo.encode(dataOutputStream);
		if(memcacheInfoList!=null)
		{
			dataOutputStream.writeInt(memcacheInfoList.size());
			for(int i=0,size=memcacheInfoList.size();i<size;i++)
			{
				MemcacheInfo t= (MemcacheInfo)memcacheInfoList.get(i);
				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

	}

	public void setMemcacheInfo(MemcacheInfo memcacheInfo) {
		this.memcacheInfo = memcacheInfo;
	}

	public MemcacheInfo getMemcacheInfo() {
		return memcacheInfo;
	}

	public void addMemcacheInfoList(MemcacheInfo value ) {
		memcacheInfoList.add(value);
	}

	public void delMemcacheInfoList(int index) {
		memcacheInfoList.remove(index);
	}

	public MemcacheInfo getMemcacheInfoList(int index) {
		return memcacheInfoList.get(index);
	}

	public int getMemcacheInfoListSize() {
		return memcacheInfoList.size();
	}

}