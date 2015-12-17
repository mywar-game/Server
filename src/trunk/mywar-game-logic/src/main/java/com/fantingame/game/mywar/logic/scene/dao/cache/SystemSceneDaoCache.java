package com.fantingame.game.mywar.logic.scene.dao.cache;


import java.util.List;



import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.scene.model.SystemScene;

public class SystemSceneDaoCache extends StaticDataDaoBaseT<Integer,SystemScene>{


	@Override
	protected Integer getCacheKey(SystemScene v) {
		return v.getSceneId();
	}
    
	public List<SystemScene> getAllScene(){
		return super.getValueList();
	}
	
	public SystemScene getSystemScene(int sceneId){
		return super.getValue(sceneId);
	}
}
