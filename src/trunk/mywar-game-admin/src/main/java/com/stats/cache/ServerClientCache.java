package com.stats.cache;

import java.util.List;

import com.framework.cache.oscache.OsCacheBase;
import com.framework.client.socket.SocketClientFactory;
import com.framework.plugin.IAppPlugin;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.service.TSecondaryServerService;

public class ServerClientCache extends OsCacheBase<SocketClientFactory> implements
	IAppPlugin {

	@Override
	public void shutdown(){
		// TODO Auto-generated method stub

	}

	@Override
	public void startup(){
		// TODO Auto-generated method stub
		//启动服务器连接缓存
		TSecondaryServerService secondaryServerService = ServiceCacheFactory.getServiceCache().getService(TSecondaryServerService.class);
		List<Object> list = secondaryServerService.findAllServer();
		for (Object object : list) {
			Object [] objs = (Object [])object;
			//设置缓存信息
			String serverId = (Integer)objs[0]+"";
			String ip = (String)objs[1];
			Integer port = (Integer)objs[2];
			Integer serverType = (Integer)objs[3];
			if(serverType > 1){
				port += 1;
			}
			SocketClientFactory chatSocketClientFactory = new SocketClientFactory(ip, port);
			put(serverId, chatSocketClientFactory);
		}
	}
}
