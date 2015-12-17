package com.example.cache;


import com.framework.cache.oscache.OsCacheBase;
import com.framework.plugin.IAppPlugin;

public class ExampleLocalCache extends OsCacheBase<String> implements IAppPlugin {


	public void shutdown() {
		// TODO Auto-generated method stub

	}

	public void startup() {
		// TODO Auto-generated method stub
		System.out.println("OsCache startup!");
        this.put("1", "test");
        this.put("2", "test2");
	}

}
