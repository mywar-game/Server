package com.dataconfig.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.dataconfig.bean.MissionCondition;
import com.dataconfig.bean.MissionPrize;
import com.dataconfig.bo.MMissionConstant;
import com.dataconfig.service.BBuildingConstantService;
import com.dataconfig.service.BTechnologyConstantService;
import com.dataconfig.service.BaPveConstantService;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.MMapAreaService;
import com.dataconfig.service.MMissionConstantService;
import com.dataconfig.service.MMonsterConstantService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * @author hzy
 * 2012-7-21
 */
public class MMissionConstantList extends ALDAdminPageActionSupport {

	/**  */
	private static final long serialVersionUID = -8420425926659273798L;
	
	/**  */
	private List<MMissionConstant> mmissionConstantList;

	/** 所有任务完成条件的map */
	private Map<Integer, List<MissionCondition>> allMissionConditionMap = new HashMap<Integer, List<MissionCondition>>();
	
	/** 所有任务完成奖励的map */
	private Map<Integer, List<MissionPrize>> allMissionPrizeMap = new HashMap<Integer, List<MissionPrize>>();
	
	/**  */
	private Map<Integer, String> missionIdNameMap;

	/**  */
	private Map<Integer, String> equipmentIDNameMap;
	
	/**  */
	private Map<Integer, String> treasureIDNameMap;
	
	/**  */
	private Map<String, String> baPveIdNamesMap;
	
	/**  */
	private Map<Integer, String> userLevelIntervalAreaNamesMap;
	
	/**  */
	private Map<Integer, String> monsterCategoryAndNameMap;
	
	/**  */
	private Map<Integer, String> buildingIDNameMap;
	
	/**  */
	private Map<Integer, String> technologyIdAndNameMap;
	
	/**  */
	private Integer id;
	
	/**  */
	private String name = "";
	
	/**  */
	private String desc = "";
	
	@Override
	public String execute() {
		MMissionConstantService mMissionConstantService = ServiceCacheFactory.getServiceCache().getService(MMissionConstantService.class);
		missionIdNameMap = mMissionConstantService.findMissionIdNameMap();
		EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		equipmentIDNameMap = equipmentConstantService.findEquipmentIDNameMap();
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		treasureIDNameMap = treasureConstantService.findTreasureIdNameMap();
		BaPveConstantService baPveConstantService = ServiceCacheFactory.getServiceCache().getService(BaPveConstantService.class);
		baPveIdNamesMap = baPveConstantService.findBaPveIdNamesMap();
		MMapAreaService  mapAreaService = ServiceCacheFactory.getServiceCache().getService(MMapAreaService.class);
		userLevelIntervalAreaNamesMap = mapAreaService.findUserLevelIntervalAreaNamesMap();
		MMonsterConstantService monsterConstantService = ServiceCacheFactory.getServiceCache().getService(MMonsterConstantService.class);
		monsterCategoryAndNameMap = monsterConstantService.findMonsterCategoryAndNameMap();
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);
		buildingIDNameMap = buildingConstantService.findBuildingIDNameMap();
		BTechnologyConstantService technologyConstantService = ServiceCacheFactory.getServiceCache().getService(BTechnologyConstantService.class);
		technologyIdAndNameMap = technologyConstantService.findTechnologyIdAndNameMap();
		
		try {
			name = URLDecoder.decode(name, "UTF-8").trim();
			desc = URLDecoder.decode(desc, "UTF-8").trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		IPage<MMissionConstant> list = mMissionConstantService.findMMissionConstantList(id, name, desc, super.getToPage(), DEFAULT_PAGESIZE);
		if (list != null) {
			mmissionConstantList = (List<MMissionConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		
		for (MMissionConstant mmissionConstant : mmissionConstantList) {
			try {
//				System.out.println(mmissionConstant.getMissionId());
//				if (mmissionConstant.getMissionId() == 1109) {
//					System.out.println();
//				}
				//显示任务完成条件信息
				if (mmissionConstant.getMissionCondition() != null && !"".equals(mmissionConstant.getMissionCondition())) {
					List<MissionCondition> missionConditionList = new ArrayList<MissionCondition>();
					JSONArray jsonArray1 = new JSONArray(mmissionConstant.getMissionCondition());
					for (int i = 0; i < jsonArray1.length(); i++) {
						JSONObject json = jsonArray1.getJSONObject(i);
						MissionCondition missionCondition = new MissionCondition();
						missionCondition.setId(json.getInt("id"));
						missionCondition.setConditionDesc(json.getString("conditionDesc"));
						missionCondition.setNum(json.getInt("num"));
						missionCondition.setOperatorNum(json.getInt("operatorNum"));
						missionCondition.setTargetNum(json.getInt("targetNum"));
						missionCondition.setTargetType(json.getInt("targetType"));
						
						missionConditionList.add(missionCondition);
					}
					allMissionConditionMap.put(mmissionConstant.getMissionId(), missionConditionList);
				}
				if (mmissionConstant.getMissionPrize() != null && !"".equals(mmissionConstant.getMissionPrize())) {
					//显示任务完成奖励信息
					List<MissionPrize> missionPrizeList = new ArrayList<MissionPrize>();
					JSONArray jsonArray2 = new JSONArray(mmissionConstant.getMissionPrize());
					for (int i = 0; i < jsonArray2.length(); i++) {
						JSONObject json = jsonArray2.getJSONObject(i);
						MissionPrize missionPrize = new MissionPrize();
						missionPrize.setId(json.getInt("id"));
						missionPrize.setCategory(json.getInt("category"));
						missionPrize.setTargetId(json.getInt("targetId"));
						missionPrize.setType(json.getInt("type"));
						missionPrize.setNum(json.getInt("num"));
						missionPrizeList.add(missionPrize);
					}
					allMissionPrizeMap.put(mmissionConstant.getMissionId(), missionPrizeList);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String findMissionIdNameMapByCondition() throws Exception {
		MMissionConstantService mMissionConstantService = ServiceCacheFactory.getServiceCache().getService(MMissionConstantService.class);
		missionIdNameMap = mMissionConstantService.findMissionIdNameMapByCondition(id, URLDecoder.decode(name, "UTF-8").trim());
		return "find";
	}

	/**
	 * @return the mmissionConstantList
	 */
	public List<MMissionConstant> getMmissionConstantList() {
		return mmissionConstantList;
	}

	/**
	 * @param mmissionConstantList the mmissionConstantList to set
	 */
	public void setMmissionConstantList(List<MMissionConstant> mmissionConstantList) {
		this.mmissionConstantList = mmissionConstantList;
	}

	/**
	 * @return the allMissionConditionMap
	 */
	public Map<Integer, List<MissionCondition>> getAllMissionConditionMap() {
		return allMissionConditionMap;
	}

	/**
	 * @param allMissionConditionMap the allMissionConditionMap to set
	 */
	public void setAllMissionConditionMap(
			Map<Integer, List<MissionCondition>> allMissionConditionMap) {
		this.allMissionConditionMap = allMissionConditionMap;
	}

	/**
	 * @return the allMissionPrizeMap
	 */
	public Map<Integer, List<MissionPrize>> getAllMissionPrizeMap() {
		return allMissionPrizeMap;
	}

	/**
	 * @param allMissionPrizeMap the allMissionPrizeMap to set
	 */
	public void setAllMissionPrizeMap(
			Map<Integer, List<MissionPrize>> allMissionPrizeMap) {
		this.allMissionPrizeMap = allMissionPrizeMap;
	}

	/**
	 * @return the missionIdNameMap
	 */
	public Map<Integer, String> getMissionIdNameMap() {
		return missionIdNameMap;
	}

	/**
	 * @param missionIdNameMap the missionIdNameMap to set
	 */
	public void setMissionIdNameMap(Map<Integer, String> missionIdNameMap) {
		this.missionIdNameMap = missionIdNameMap;
	}

	/**
	 * @return the equipmentIDNameMap
	 */
	public Map<Integer, String> getEquipmentIDNameMap() {
		return equipmentIDNameMap;
	}

	/**
	 * @param equipmentIDNameMap the equipmentIDNameMap to set
	 */
	public void setEquipmentIDNameMap(Map<Integer, String> equipmentIDNameMap) {
		this.equipmentIDNameMap = equipmentIDNameMap;
	}

	/**
	 * @return the treasureIDNameMap
	 */
	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap;
	}

	/**
	 * @param treasureIDNameMap the treasureIDNameMap to set
	 */
	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap;
	}

	/**
	 * @return the baPveIdNamesMap
	 */
	public Map<String, String> getBaPveIdNamesMap() {
		return baPveIdNamesMap;
	}

	/**
	 * @param baPveIdNamesMap the baPveIdNamesMap to set
	 */
	public void setBaPveIdNamesMap(Map<String, String> baPveIdNamesMap) {
		this.baPveIdNamesMap = baPveIdNamesMap;
	}

	/**
	 * @return the userLevelIntervalAreaNamesMap
	 */
	public Map<Integer, String> getUserLevelIntervalAreaNamesMap() {
		return userLevelIntervalAreaNamesMap;
	}

	/**
	 * @param userLevelIntervalAreaNamesMap the userLevelIntervalAreaNamesMap to set
	 */
	public void setUserLevelIntervalAreaNamesMap(
			Map<Integer, String> userLevelIntervalAreaNamesMap) {
		this.userLevelIntervalAreaNamesMap = userLevelIntervalAreaNamesMap;
	}

	/**
	 * @return the monsterCategoryAndNameMap
	 */
	public Map<Integer, String> getMonsterCategoryAndNameMap() {
		return monsterCategoryAndNameMap;
	}

	/**
	 * @param monsterCategoryAndNameMap the monsterCategoryAndNameMap to set
	 */
	public void setMonsterCategoryAndNameMap(
			Map<Integer, String> monsterCategoryAndNameMap) {
		this.monsterCategoryAndNameMap = monsterCategoryAndNameMap;
	}

	/**
	 * @return the buildingIDNameMap
	 */
	public Map<Integer, String> getBuildingIDNameMap() {
		return buildingIDNameMap;
	}

	/**
	 * @param buildingIDNameMap the buildingIDNameMap to set
	 */
	public void setBuildingIDNameMap(Map<Integer, String> buildingIDNameMap) {
		this.buildingIDNameMap = buildingIDNameMap;
	}

	/**
	 * @return the technologyIdAndNameMap
	 */
	public Map<Integer, String> getTechnologyIdAndNameMap() {
		return technologyIdAndNameMap;
	}

	/**
	 * @param technologyIdAndNameMap the technologyIdAndNameMap to set
	 */
	public void setTechnologyIdAndNameMap(
			Map<Integer, String> technologyIdAndNameMap) {
		this.technologyIdAndNameMap = technologyIdAndNameMap;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
