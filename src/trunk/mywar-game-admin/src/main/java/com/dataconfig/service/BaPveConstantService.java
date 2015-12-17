package com.dataconfig.service; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.dataconfig.bo.BaPveConstant;
import com.dataconfig.bo.BaPveConstantId;
import com.dataconfig.bo.SystemForces;
import com.dataconfig.bo.SystemScene;
import com.dataconfig.dao.BaPveConstantDao;
import com.dataconfig.dao.SystemSceneDao;
import com.dataconfig.dao.SystemforcesDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.opensymphony.xwork2.ActionSupport;

public class BaPveConstantService {
	private BaPveConstantDao baPveConstantDao; 
	private SystemforcesDao systemforcesDao;
	private SystemSceneDao systemSceneDao;

	/**
	 * 查询关卡配置分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<BaPveConstant> findBaPveConstantPageList(Integer pveBigId, Integer pveSmallId, String bigName, String smallName, Integer currentPage, Integer pageSize) {
		StringBuffer sb = new StringBuffer(); 
		sb.append("from BaPveConstant c where 1 = 1"); 
		if (bigName != null && !"".equals(bigName)) {
			sb.append(" and c.pveBigName like '%" + bigName + "%'"); 
		}
		if (smallName != null && !"".equals(smallName)) {
			sb.append(" and c.pveSmallName like '%" + smallName + "%'"); 
		}
		if (pveBigId != null) {
			sb.append(" and c.id.pveBigId = " + pveBigId); 
		}
		if (pveSmallId != null) {
			sb.append(" and c.id.pveSmallId = " + pveSmallId); 
		}
//		System.out.println(" == "+ sb.toString()); 
		baPveConstantDao.closeSession(DBSource.CFG); 
		return baPveConstantDao.findPage(sb.toString(), new ArrayList<Object>(), pageSize, currentPage); 
	}
	
	/**
	 * 查询关卡配置数据
	 * @param baPveConstantId
	 * @return
	 */
	public BaPveConstant findOneBaPveConstant(BaPveConstantId baPveConstantId) {
//		CustomerContextHolder.setCustomerType(DBSource.CFG); 
//		baPveConstantDao.findSQL_("select * from ba_pve_constant where PVE_BIG_ID = "+ baPveConstantId.getPveBigId()+ " and PVE_SMALL_ID = "+ baPveConstantId.getPveSmallId()); 
		List<BaPveConstant> list = baPveConstantDao.find("from BaPveConstant baoc where baoc.id.pveBigId = " + baPveConstantId.getPveBigId() + " and baoc.id.pveSmallId = " + baPveConstantId.getPveSmallId(), DBSource.CFG);  
		if (list.size() == 0) {
			return null; 
		}
		return list.get(0); 
	}
	
	/**
	 * 添加关卡配置数据
	 * @param baPveConstant
	 */
	public void addOneBaPveConstant(BaPveConstant baPveConstant) {
		baPveConstantDao.save(baPveConstant, DBSource.CFG); 
	}
	
	/**
	 * 删除关卡配置数据
	 * @param baPveConstantId
	 */
	public void delOneBaPveConstant(BaPveConstantId baPveConstantId) {
		baPveConstantDao.remove(this.findOneBaPveConstant(baPveConstantId), DBSource.CFG); 
	}
	
	/**
	 * 修改关卡配置数据
	 * @param baPveConstantId
	 */
	public void updateOneBaPveConstant(BaPveConstant baPveConstant) {
		baPveConstantDao.update(baPveConstant, DBSource.CFG); 
	}
	
	/**
	 * 查询关卡常量列表，速度极慢！！！
	 * @return
	 */
	public List<BaPveConstant> findBaPveConstantList() {
		baPveConstantDao.closeSession(DBSource.CFG); 
		return baPveConstantDao.findAll(); 
	}

	/**
	 *  查询符合进入等级的pve关卡常量的ID和关卡对应的map
	 * @return
	 */
	public Map<String, String> findBaPveIdAndNamesEnterLevelMap() {
		Map<String, String> baPveIdEnterLevelAndNames = new HashMap<String, String>(); 
		StringBuffer sql = new StringBuffer(); 
		sql.append("SELECT PVE_BIG_ID, PVE_SMALL_ID, ENTER_LEVEL, PVE_BIG_NAME, PVE_SMALL_NAME FROM ba_pve_constant WHERE PVE_BIG_ID>= 2 AND PVE_BIG_ID <= 52"); 
		baPveConstantDao.closeSession(DBSource.CFG); 
		List<Object> list = baPveConstantDao.findSQL_(sql.toString()); 
		for (int i = 0; i < list.size(); i++) {
			Integer pveBigId = Integer.parseInt(((Object[]) list.get(i))[0].toString()); 
			Integer pveSmallId = Integer.parseInt(((Object[]) list.get(i))[1].toString()); 
			Integer enterLevel = Integer.parseInt(((Object[]) list.get(i))[2].toString()); 
			String pveBigName = ((Object[]) list.get(i))[3].toString(); 
			String pveSmallName = ((Object[]) list.get(i))[4].toString(); 
			baPveIdEnterLevelAndNames.put(pveBigId + "_" + pveSmallId, pveBigName + "," + pveSmallName + "," + enterLevel); 
		}
		return baPveIdEnterLevelAndNames; 
	}
	
	public Map<String, String> findBaPveIdNamesMap() {
		Map<String, String> baPveIdNamesMap = new HashMap<String, String>(); 
		StringBuffer sb = new StringBuffer(); 
		sb.append("SELECT PVE_BIG_ID, PVE_SMALL_ID, PVE_BIG_NAME, PVE_SMALL_NAME FROM ba_pve_constant"); 
		baPveConstantDao.closeSession(DBSource.CFG); 
		List<Object> list = baPveConstantDao.findSQL_(sb.toString()); 
		for (int i = 0; i < list.size(); i++) {
			Integer pveBigId = Integer.parseInt(((Object[]) list.get(i))[0].toString()); 
			Integer pveSmallId = Integer.parseInt(((Object[]) list.get(i))[1].toString()); 
			String pveBigName = ((Object[]) list.get(i))[2].toString(); 
			String pveSmallName = ((Object[]) list.get(i))[3].toString(); 
			baPveIdNamesMap.put(pveBigId + "," + pveSmallId, pveBigName + "," + pveSmallName); 
		}
		return baPveIdNamesMap; 
	}
	
	/**
	 * 通关奖励
	 * @param baPveConstant
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> parseDropTaskTreasureInfo(BaPveConstant baPveConstant, Map<Integer, String> treasureIDNameMap) throws Exception {
		List<Map<String, String>> dropTaskTreasureInfoList = new ArrayList<Map<String, String>>(); 
		
		String dropTaskTreasureInfo = baPveConstant.getDropTaskTreasureInfo(); 
		if (dropTaskTreasureInfo != null && !"".equals(dropTaskTreasureInfo)) {
			JSONArray dropTaskTreasureInfoJsonArr = new JSONArray(dropTaskTreasureInfo); 
			for (int i = 0; i < dropTaskTreasureInfoJsonArr.length(); i++) {
				JSONObject dropTaskTreasureInfoJsonObj = dropTaskTreasureInfoJsonArr.getJSONObject(i); 
				Integer rewardType = dropTaskTreasureInfoJsonObj.getInt("rewardType"); 
				Integer rewardId = dropTaskTreasureInfoJsonObj.getInt("rewardId"); 
				Integer rewardNum = dropTaskTreasureInfoJsonObj.getInt("rewardNum"); 
				
				Map<String, String> taskTreasureMap = new HashMap<String, String>(); 
				taskTreasureMap.put("rewardType",  rewardType+ ""); 
				if (rewardType == 1) {
					taskTreasureMap.put("rewardName",  treasureIDNameMap.get(rewardId)); 
				} else {
					taskTreasureMap.put("rewardName",  rewardId+ ""); 
				}
				taskTreasureMap.put("rewardId",  rewardId+ ""); 
				taskTreasureMap.put("rewardNum",  rewardNum+ ""); 
				dropTaskTreasureInfoList.add(taskTreasureMap); 
			}
		}
		return dropTaskTreasureInfoList; 
	}
	
	public List<Map<String, Object>> parseMonsterInfo(BaPveConstant baPveConstant, Map<Integer, String> monsterCategoryAndNameMap) throws Exception {
		List<Map<String, Object>> monsterInfoList = new ArrayList<Map<String, Object>>(); 
		ActionSupport actionSupport = new ActionSupport();
		
		String monsterInfo = baPveConstant.getMonsterInfo(); 
		if (monsterInfo != null && !"".equals(monsterInfo)) {
			JSONArray monsterInfoJsonArr = new JSONArray(monsterInfo); 
			for (int i = 0; i < monsterInfoJsonArr.length(); i++) {
				Map<String, Object> monsterInfoMap = new HashMap<String, Object>(); 
				
				JSONObject monsterInfoJsonObj = monsterInfoJsonArr.getJSONObject(i); 
				int appearType = monsterInfoJsonObj.getInt("appearType"); 
				int num = monsterInfoJsonObj.getInt("num"); 
				String monsterStr = monsterInfoJsonObj.getString("monster"); 
				int time = monsterInfoJsonObj.getInt("time"); 
				monsterInfoMap.put("appearType", appearType); 
				monsterInfoMap.put("num", num); 
				monsterInfoMap.put("time", time); 
				
				List<Map<String, String>> monsterMapList = new ArrayList<Map<String, String>>(); 
				JSONArray monsterJsonArr = new JSONArray(monsterStr); 
				for (int j = 0; j < monsterJsonArr.length(); j++) {
					Map<String, String> monsterMap = new HashMap<String, String>(); 
					
					JSONObject monsterJsonObj = monsterJsonArr.getJSONObject(j); 
					int level = monsterJsonObj.getInt("level"); 
					int monsterId = monsterJsonObj.getInt("monsterId"); 
					monsterMap.put("monsterId", monsterId+""); 
					monsterMap.put("level", level+""); 
					if (monsterId < 1000) {
						monsterMap.put("monsterName", actionSupport.getText("monsterId_extraction_"+monsterId)); 
					} else {
						monsterMap.put("monsterName", monsterCategoryAndNameMap.get(monsterId%1000)); 
					}
					monsterMapList.add(monsterMap); 
				}
				monsterInfoMap.put("monsterMapList", monsterMapList); 
				monsterInfoList.add(monsterInfoMap); 
			}
		}
		return monsterInfoList; 
	}

	public List<Map<String, Integer>> parseMonsterAppearType(BaPveConstant baPveConstant) throws Exception {
		List<Map<String, Integer>> monsterAppearTypeList = new ArrayList<Map<String, Integer>>(); 
		
		String monsterAppearType = baPveConstant.getMonsterAppearType(); 
		if (monsterAppearType != null && !"".equals(monsterAppearType)) {
			JSONArray monsterAppearTypeJsonArr = new JSONArray(monsterAppearType); 
			for (int i = 0; i < monsterAppearTypeJsonArr.length(); i++) {
				Map<String, Integer> monsterAppearTypeMap = new HashMap<String, Integer>(); 
				JSONObject monsterInfoJsonObj = monsterAppearTypeJsonArr.getJSONObject(i); 
				int addr = monsterInfoJsonObj.getInt("addr"); 
				int num = monsterInfoJsonObj.getInt("num"); 
				int time = monsterInfoJsonObj.getInt("time"); 
				monsterAppearTypeMap.put("addr", addr); 
				monsterAppearTypeMap.put("num", num); 
				monsterAppearTypeMap.put("time", time); 
				monsterAppearTypeList.add(monsterAppearTypeMap); 
			}
		}
		return monsterAppearTypeList; 
	}
	
	public List<Map<String, String>> parseReward(BaPveConstant baPveConstant, Map<Integer, String> treasureIDNameMap) throws Exception {
		List<Map<String, String>> rewardList = new ArrayList<Map<String, String>>(); 
		ActionSupport actionSupport = new ActionSupport();
		
		String reward = baPveConstant.getReward(); 
		if (reward != null && !"".equals(reward)) {
			JSONArray rewardJsonArr = new JSONArray(reward); 
			for (int i = 0; i < rewardJsonArr.length(); i++) {
				Map<String, String> rewardMap = new HashMap<String, String>(); 
				JSONObject rewardJsonObj = rewardJsonArr.getJSONObject(i); 
				int num = rewardJsonObj.getInt("num"); 
				int percent = rewardJsonObj.getInt("percent");
				int color = -1;
				if (rewardJsonObj.has("color")) {
					color = rewardJsonObj.getInt("color"); 
				}
				int rewardType = rewardJsonObj.getInt("rewardType"); 
				int targetId = rewardJsonObj.getInt("targetId");
				//根据奖励类型、奖励id决定奖励名
				String targetName;
				switch (rewardType) {
				case 1:
					targetName = actionSupport.getText("userEquipment.quality_"+color)+actionSupport.getText("reward_targetId_1_"+targetId);
					break;
				case 2:
				case 9:
				case 10:
					targetName = treasureIDNameMap.get(targetId);
					break;
				case 3:
					targetName = actionSupport.getText("reward_targetId_3_"+targetId);
					break;
				default:
					targetName = actionSupport.getText("undefine")+targetId+"";
					break;
				}
				rewardMap.put("num", num+""); 
				rewardMap.put("percent", percent+""); 
				rewardMap.put("color", color+""); 
				rewardMap.put("rewardType", rewardType+""); 
				rewardMap.put("targetId", targetId+""); 
				rewardMap.put("targetName", targetName); 
				
				rewardList.add(rewardMap); 
			}
		}
		return rewardList; 
	}
	
	public List<Map<String, String>> parseVipReward(BaPveConstant baPveConstant, Map<Integer, String> treasureIDNameMap) throws Exception {
		List<Map<String, String>> vipRewardList = new ArrayList<Map<String, String>>(); 
		ActionSupport actionSupport = new ActionSupport();
		
		String vipReward = baPveConstant.getVipReward(); 
		if (vipReward != null && !"".equals(vipReward)) {
			JSONArray vipRewardJsonArr = new JSONArray(vipReward); 
			for (int i = 0; i < vipRewardJsonArr.length(); i++) {
				Map<String, String> vipRewardMap = new HashMap<String, String>(); 
				JSONObject vipRewardJsonObj = vipRewardJsonArr.getJSONObject(i); 
				int num = vipRewardJsonObj.getInt("num"); 
				int percent = vipRewardJsonObj.getInt("percent"); 
				int color = -1;
				if (vipRewardJsonObj.has("color")) {
					color = vipRewardJsonObj.getInt("color"); 
				}
				int rewardType = vipRewardJsonObj.getInt("rewardType"); 
				int targetId = vipRewardJsonObj.getInt("targetId");
				//根据奖励类型、奖励id决定奖励名
				String targetName;
				switch (rewardType) {
				case 1:
					targetName = actionSupport.getText("userEquipment.quality_"+color)+actionSupport.getText("reward_targetId_1_"+targetId);
					break;
				case 2:
				case 9:
				case 10:
					targetName = treasureIDNameMap.get(targetId);
					break;
				case 3:
					targetName = actionSupport.getText("reward_targetId_3_"+targetId);
					break;
				default:
					targetName = actionSupport.getText("undefine")+targetId+"";
					break;
				}
				vipRewardMap.put("num", num+""); 
				vipRewardMap.put("percent", percent+""); 
				vipRewardMap.put("color", color+""); 
				vipRewardMap.put("rewardType", rewardType+""); 
				vipRewardMap.put("targetId", targetId+""); 
				vipRewardMap.put("targetName", targetName); 
				vipRewardList.add(vipRewardMap); 
			}
		}
		return vipRewardList; 
	}
	
	public List<Map<String, String>> parseFirstReward(BaPveConstant baPveConstant, Map<Integer, String> treasureIDNameMap) throws Exception {
		List<Map<String, String>> firstRewardList = new ArrayList<Map<String, String>>(); 
		ActionSupport actionSupport = new ActionSupport();
		
		String firstReward = baPveConstant.getFirstReward(); 
		if (firstReward != null && !"".equals(firstReward)) {
			JSONArray firstRewardJsonArr = new JSONArray(firstReward); 
			for (int i = 0; i < firstRewardJsonArr.length(); i++) {
				Map<String, String> firstRewardMap = new HashMap<String, String>(); 
				JSONObject firstRewardJsonObj = firstRewardJsonArr.getJSONObject(i); 
				int num = firstRewardJsonObj.getInt("num"); 
				int color = -1;
				if (firstRewardJsonObj.has("color")) {
					color = firstRewardJsonObj.getInt("color"); 
				}
				int rewardType = firstRewardJsonObj.getInt("rewardType"); 
				int targetId = firstRewardJsonObj.getInt("targetId");
				//根据奖励类型、奖励id决定奖励名
				String targetName;
				switch (rewardType) {
				case 1:
					targetName = actionSupport.getText("userEquipment.quality_"+color)+actionSupport.getText("reward_targetId_1_"+targetId);
					break;
				case 2:
				case 9:
				case 10:
					targetName = treasureIDNameMap.get(targetId);
					break;
				case 3:
					targetName = actionSupport.getText("reward_targetId_3_"+targetId);
					break;
				default:
					targetName = actionSupport.getText("undefine")+targetId+"";
					break;
				}
				firstRewardMap.put("num", num+""); 
				firstRewardMap.put("color", color+""); 
				firstRewardMap.put("rewardType", rewardType+""); 
				firstRewardMap.put("targetId", targetId+""); 
				firstRewardMap.put("targetName", targetName); 
				
				firstRewardList.add(firstRewardMap); 
			}
		}
		return firstRewardList; 
	}
	
	/**
	 * 查询大关卡对应的小关卡数据
	 * @param list
	 * @param map
	 */
	public Map<Integer, List<SystemForces>> findSceneMap(){
		Map<Integer, List<SystemForces>> map = new HashMap<Integer, List<SystemForces>>();
		List<SystemForces> forcesList = systemforcesDao.find("from SystemForces", DBSource.CFG);//小关卡数据
		if(forcesList!=null && forcesList.size()>0){
			for(SystemForces forces : forcesList){//每个大关卡对应的小关卡数据放到map里
				if(map.containsKey(forces.getSceneId())){
					map.get(forces.getSceneId()).add(forces);
				}else{
					List<SystemForces> temp = new ArrayList<SystemForces>();
					temp.add(forces);
					map.put(forces.getSceneId(), temp);
				}
			}
		}
		return map;
	}
	
	/**
	 * 查询关卡数据
	 * @return
	 */
	public List<SystemScene> findSceneList(){
		return systemSceneDao.find("from SystemScene", DBSource.CFG);//大关卡数据
	}
	
	/**
	 * 查询forces数据
	 * @return
	 */
	public Map<Integer, SystemForces> findForces(){
		List<SystemForces> list = systemforcesDao.find("from SystemForces", DBSource.CFG);
		Map<Integer, SystemForces> map = new HashMap<Integer, SystemForces>();
		if(list!=null && list.size()>0){
			for(SystemForces forces : list){
				map.put(forces.getForcesId(), forces);
			}
		}
		return map;
	}
	
	public void setBaPveConstantDao(BaPveConstantDao baPveConstantDao) {
		this.baPveConstantDao = baPveConstantDao; 
	}

	public BaPveConstantDao getBaPveConstantDao() {
		return baPveConstantDao; 
	}

	public SystemforcesDao getSystemforcesDao() {
		return systemforcesDao;
	}

	public void setSystemforcesDao(SystemforcesDao systemforcesDao) {
		this.systemforcesDao = systemforcesDao;
	}

	public SystemSceneDao getSystemSceneDao() {
		return systemSceneDao;
	}

	public void setSystemSceneDao(SystemSceneDao systemSceneDao) {
		this.systemSceneDao = systemSceneDao;
	}
}
