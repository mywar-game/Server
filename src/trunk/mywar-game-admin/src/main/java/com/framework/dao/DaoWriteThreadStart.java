package com.framework.dao;

import com.framework.log.LogSystem;
import com.framework.plugin.IAppPlugin;

public class DaoWriteThreadStart implements IAppPlugin {

	private int maxNum;
	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public void shutdown() {
		// TODO Auto-generated method stub

	}

	public void startup() {
		// TODO Auto-generated method stub
          new Thread(new WriteThread(maxNum)).start();
          LogSystem.info("数据异步写入插件启动！");
	}

}
