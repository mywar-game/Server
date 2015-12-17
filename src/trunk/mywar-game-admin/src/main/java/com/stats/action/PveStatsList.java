package com.stats.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.SystemForces;
import com.dataconfig.bo.SystemScene;
import com.dataconfig.service.BaPveConstantService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserPveStats;
import com.stats.service.UserPveStatsService;

public class PveStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 3475276590317692485L;
	/**关卡统计数据**/
	private Map<Integer,UserPveStats> statsMap = new HashMap<Integer,UserPveStats>();
	/**大关卡常量数据**/
	private List<SystemScene> sceneList = new ArrayList<SystemScene>();
	private List<SystemScene> tempSceneList;
	

	/**每个大关卡对应小关卡数据**/
	private Map<Integer, List<SystemForces>> map;
	
	/** 过滤关卡**/
	private Integer guanQia = 0;
	
	//闯关总次数
	//过关总次数
	//参与闯关的玩家数
	//每次成功过关的平均闯关次数

	public String execute() {
		BaPveConstantService baPveConstantService = ServiceCacheFactory.getServiceCache().getService(BaPveConstantService.class);
		tempSceneList = baPveConstantService.findSceneList();
		map = baPveConstantService.findSceneMap();
		//关卡日志
		UserPveStatsService userPveStatsService = ServiceCacheFactory.getServiceCache().getService(UserPveStatsService.class);
		statsMap = userPveStatsService.findUserPveStatsMap(super.getStartDate(), super.getEndDate());
		
		/** 新增关卡过滤 **/
		// awefull
		if (guanQia != 0) {
			if (guanQia == 1) {
				// mark篇
				for (SystemScene scene : tempSceneList) {
					int sceneId = scene.getSceneId();
					if ((sceneId >= 1 && sceneId <= 10) || (sceneId >= 131 && sceneId <= 140) || (sceneId >= 221 && sceneId <= 230) || (sceneId >= 311 && sceneId <= 320)) {
						sceneList.add(scene);
					}
				}
			} else if (guanQia == 2) {
				// 索隆篇
				for (SystemScene scene : tempSceneList) {
					int sceneId = scene.getSceneId();
					if ((sceneId >= 31 && sceneId <= 40) || (sceneId >= 121 && sceneId <= 130) || (sceneId >= 211 && sceneId <= 220) || (sceneId >= 301 && sceneId <= 310)) {
						sceneList.add(scene);
					}
				}
			} else if (guanQia == 3) {
				// 盖伦篇
				for (SystemScene scene : tempSceneList) {
					int sceneId = scene.getSceneId();
					if ((sceneId >= 11 && sceneId <= 20) || (sceneId >= 101 && sceneId <= 110) || (sceneId >= 231 && sceneId <= 240) || (sceneId >= 321 && sceneId <= 330)) {
						sceneList.add(scene);
					}
				}
			} else if (guanQia == 4) {
				// 阿隆篇
				for (SystemScene scene : tempSceneList) {
					int sceneId = scene.getSceneId();
					if ((sceneId >= 21 && sceneId <= 30) || (sceneId >= 111 && sceneId <= 120) || (sceneId >= 201 && sceneId <= 210) || (sceneId >= 331 && sceneId <= 340)) {
						sceneList.add(scene);
					}
				}
			} else if (guanQia == 5) {
				// 精英副本
				for (SystemScene scene : tempSceneList) {
					int sceneId = scene.getSceneId();
					if (sceneId == 1001) {
						sceneList.add(scene);
					}
				}
			} else if (guanQia == 6) {
				// 活动副本
				for (SystemScene scene : tempSceneList) {
					int sceneId = scene.getSceneId();
					if (sceneId == 2001) {
						sceneList.add(scene);
					}
				}
			} else if (guanQia == 7) {
				// 试炼塔
				for (SystemScene scene : tempSceneList) {
					int sceneId = scene.getSceneId();
					if (sceneId == 3001 || sceneId == 3002) {
						sceneList.add(scene);
					}
				}
			}
		} else {
			sceneList.addAll(tempSceneList);
		}
		
		return SUCCESS;
	}


	public Map<Integer, UserPveStats> getStatsMap() {
		return statsMap;
	}


	public void setStatsMap(Map<Integer, UserPveStats> statsMap) {
		this.statsMap = statsMap;
	}


	public List<SystemScene> getSceneList() {
		return sceneList;
	}

	public void setSceneList(List<SystemScene> sceneList) {
		this.sceneList = sceneList;
	}

	public Map<Integer, List<SystemForces>> getMap() {
		return map;
	}

	public void setMap(Map<Integer, List<SystemForces>> map) {
		this.map = map;
	}

	public Integer getGuanQia() {
		return guanQia;
	}


	public void setGuanQia(Integer guanQia) {
		this.guanQia = guanQia;
	}


	public List<SystemScene> getTempSceneList() {
		return tempSceneList;
	}


	public void setTempSceneList(List<SystemScene> tempSceneList) {
		this.tempSceneList = tempSceneList;
	}
	
}
