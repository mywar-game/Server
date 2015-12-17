package com.example.cache;

import java.util.Date;

import com.framework.cache.memcache.MemcacheBase;
import com.framework.plugin.IAppPlugin;
public class ExampleMemcacheCache extends MemcacheBase<Date> implements IAppPlugin {

	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	public void startup() {
		// TODO Auto-generated method stub
        this.put("1", new Date());
        this.put("2", new Date());
//        Thread.
        System.out.println("打印内存中的值:" + get("1"));
//        Collection<String> c = new ArrayList<String>();
//        System.out.println(gets(c));
	}
}
