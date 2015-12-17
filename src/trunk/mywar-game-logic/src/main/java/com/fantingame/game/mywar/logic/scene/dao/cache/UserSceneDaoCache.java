package com.fantingame.game.mywar.logic.scene.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.scene.dao.mysql.UserSceneDaoMysql;
import com.fantingame.game.mywar.logic.scene.model.UserScene;
import com.google.common.collect.Maps;

public class UserSceneDaoCache extends BaseCacheMapDao<UserScene> {
    @Autowired
	private UserSceneDaoMysql userSceneDaoMysql;
    /**
     * 获取用户开启的场景列表
     * @param userId
     * @return
     */
    public List<UserScene> getUserSceneList(String userId){
    	return super.getMapList(userId);
    }
    /**
     * 判断是否开启了场景
     * @param userId
     * @param sceneId
     * @return
     */
    public boolean isOpenScene(String userId,int sceneId){
    	UserScene userScene = super.getByKey(userId, sceneId+"");
    	return userScene==null?false:true;
    }
    /**
     * 开启某个关卡
     * @param userId
     * @param sceneId
     * @return
     */
    public boolean addUserScene(String userId,int sceneId){
    	UserScene scene = new UserScene();
    	scene.setUserId(userId);
    	scene.setSceneId(sceneId);
    	scene.setCreatedTime(new Date());
    	if(userSceneDaoMysql.addUserScene(scene)){
    	    if(super.isExitKey(userId)){
    			super.addMapValues(userId, sceneId+"",scene);
    		}
    	    return true;
    	}
    	return false;
    }
	@Override
	protected Map<String, UserScene> loadFromDb(String key) {
		Map<String, UserScene> map = Maps.newConcurrentMap();
		List<UserScene> list = userSceneDaoMysql.getUserSceneList(key);
		for(UserScene userScene:list){
			map.put(userScene.getSceneId()+"", userScene);
		}
		return map;
	}

}
