package com.fantingame.game.mywar.logic.forces.dao;

import java.util.List;
import java.util.Map;

import com.fantingame.game.mywar.logic.forces.model.UserForces;

public interface UserForcesDao {
	/**
	 * 获取用户关卡列表
	 * @param userId
	 * @return
	 */
    public List<UserForces> getUserForcesList(String userId);
    /**
     * 更新关卡攻打次数
     * @param userId
     * @param forcesId
     * @param times
     * @return
     */
    public boolean updateForcesTimes(String userId, int mapId, int forcesId, int times, int forcesType);
    /**
     * 添加用户关卡对象
     * @param userForces
     * @return
     */
    public boolean addUserForces(UserForces userForces);
    /**
     * 获取用户关卡信息
     * @param userId
     * @param forcesId
     * @return
     */
    public UserForces getUserForces(String userId, int mapId, int forcesId, int forcesType);
    /**
     * 更新用户关卡信息状态
     * @param userId
     * @param mapId
     * @param forcesId
     * @param forcesType
     * @param status
     * @return
     */
    public boolean updateUserForcesStatus(String userId, int mapId, int forcesId, int status, int attackTimes, int forcesType);
    /**
     * 获取某个地图下的用户关卡信息
     * @param userId
     * @param mapId
     * @return
     */
    public List<UserForces> getUserForcesListByMapId(String userId,int mapId);
    
    /**
     * 获取用户副本关卡信息
     * 
     * @param userId
     * @param bigForcesId
     * @return
     */
    public List<UserForces> getUserForcesListByBigForcesId(String userId, int mapId, int bigForcesId);
    
    /**
     * 获取某个地图下的用户关卡信息Map
     * 
     * @param userId
     * @param mapId
     * @return
     */
    public Map<Integer, UserForces> getUserForcesMapByMapId(String userId, int mapId);
}
