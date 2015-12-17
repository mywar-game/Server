package com.fantingame.game.gateway.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.server.msg.ServerType;


public class ServerActionService {
	 //action映射的服务器列表
     private Map<String,List<ServerType>>  allActionMap = new ConcurrentHashMap<String,List<ServerType>>();
     
     /**
      * 获取接口名称对应的服务器列表
      * @param actionName
      * @return
      */
     public List<ServerType> getServerTypeListByActionName(String actionName){
    	 return allActionMap.get(actionName);
     }
     /**
      * 添加action与serverType的映射路径
      * @param actionName
      * @param serverType
      */
     public synchronized void addActionName(String actionName,ServerType serverType){
    	 if(allActionMap.containsKey(actionName)){
    		 allActionMap.get(actionName).add(serverType);
    	 }else{
    		 List<ServerType> serverTypeList = new ArrayList<ServerType>();
    		 serverTypeList.add(serverType);
    		 allActionMap.put(actionName, serverTypeList);
    	 }
    	 LogSystem.info("添加actionName映射"+actionName+",serverType="+serverType);
     }
}
