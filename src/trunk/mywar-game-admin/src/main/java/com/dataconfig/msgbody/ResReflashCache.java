package com.dataconfig.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/** 刷新游戏服务器缓存 */
public class ResReflashCache implements ICodeAble {

	/**  缓存类型 **/
	private int cacheType;

	public ResReflashCache() {
	}

	public ResReflashCache(int cacheType) {
		this.cacheType = cacheType;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		cacheType = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(cacheType);
	}

	public void setCacheType(int cacheType) {
		this.cacheType = cacheType;
	}

	public int getCacheType() {
		return cacheType;
	}

}