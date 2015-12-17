package com.dataconfig.action;

import java.util.ArrayList;
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
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.MMapAreaService;
import com.dataconfig.service.MMissionConstantService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author hzy
 * 2012-7-21
 */
public class AddMMissionConstant extends ALDAdminActionSupport implements ModelDriven<MMissionConstant> {

	/**  */
	private static final long serialVersionUID = -8007274858443129907L;

	/**  */
	private MMissionConstant mmissionConstant = new MMissionConstant();
	
	/**  */
	private List<MissionCondition> mmissionConditionList = new ArrayList<MissionCondition>();
	
	/**  */
	private List<MissionPrize> missionPrizeList = new ArrayList<MissionPrize>();
	
	/**  */
	private Map<Integer, String> equipmentIDNameMap;
	
	/**  */
	private Map<Integer, String> treasureIDNameMap;
	
	/**  */
	private Map<Integer, String> buildingIDNameMap;
	
	/**  */
	private Map<Integer, String> userLevelIntervalAreaNamesMap;
	
	/**  */
	private Map<Integer, String> technologyIdAndNameMap;
	
	/**  */
	private String isCommit = "F";
	
	@Override
	public String execute() throws Exception {
		EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		equipmentIDNameMap = equipmentConstantService.findEquipmentIDNameMap();
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		treasureIDNameMap = treasureConstantService.findTreasureIdNameMap();
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);
		buildingIDNameMap = buildingConstantService.findBuildingIDNameMap();
		MMapAreaService  mapAreaService = ServiceCacheFactory.getServiceCache().getService(MMapAreaService.class);
		userLevelIntervalAreaNamesMap = mapAreaService.findUserLevelIntervalAreaNamesMap();
		BTechnologyConstantService technologyConstantService = ServiceCacheFactory.getServiceCache().getService(BTechnologyConstantService.class);
		technologyIdAndNameMap = technologyConstantService.findTechnologyIdAndNameMap();
		
		MMissionConstantService mMissionConstantService = ServiceCacheFactory.getServiceCache().getService(MMissionConstantService.class);
		if ("F".equals(this.isCommit)) {
			mmissionConstant.setMissionAreaLimit(0);
			mmissionConstant.setIsTheFirst("0");
			mmissionConstant.setNpcId(1);
			return INPUT;
		} else {
			if (mMissionConstantService.findOneMMissionConstant(mmissionConstant.getMissionId()) != null) {
				try {
					//显示任务完成条件信息
					if (mmissionConstant.getMissionCondition() != null) {
						JSONArray jsonArray = new JSONArray(mmissionConstant.getMissionCondition());
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject json = jsonArray.getJSONObject(i);
							MissionCondition mmissionCondition = new MissionCondition();
							mmissionCondition.setId(json.getInt("id"));
							mmissionCondition.setConditionDesc(json.getString("conditionDesc"));
							mmissionCondition.setNum(json.getInt("num"));
							mmissionCondition.setOperatorNum(json.getInt("operatorNum"));
							mmissionCondition.setTargetNum(json.getInt("targetNum"));
							mmissionCondition.setTargetType(json.getInt("targetType"));
							
							mmissionConditionList.add(mmissionCondition);
						}
					}
					
					if (mmissionConstant.getMissionPrize() != null) {
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
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				super.setErroDescrip(this.getText("addMMissionConstantJsp.missionIdExist", new String[]{mmissionConstant.getMissionId() + ""}));
				return ERROR;
			}
			mMissionConstantService.addMMissionConstant(mmissionConstant);
			return "ok";
		}
	}
	
	@Override
	public MMissionConstant getModel() {
		return mmissionConstant;
	}

	/**
	 * @return the mmissionConstant
	 */
	public MMissionConstant getMmissionConstant() {
		return mmissionConstant;
	}

	/**
	 * @param mmissionConstant the mmissionConstant to set
	 */
	public void setMmissionConstant(MMissionConstant mmissionConstant) {
		this.mmissionConstant = mmissionConstant;
	}

	/**
	 * @return the mmissionConditionList
	 */
	public List<MissionCondition> getMmissionConditionList() {
		return mmissionConditionList;
	}

	/**
	 * @param mmissionConditionList the mmissionConditionList to set
	 */
	public void setMmissionConditionList(
			List<MissionCondition> mmissionConditionList) {
		this.mmissionConditionList = mmissionConditionList;
	}

	/**
	 * @return the missionPrizeList
	 */
	public List<MissionPrize> getMissionPrizeList() {
		return missionPrizeList;
	}

	/**
	 * @param missionPrizeList the missionPrizeList to set
	 */
	public void setMissionPrizeList(List<MissionPrize> missionPrizeList) {
		this.missionPrizeList = missionPrizeList;
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
	 * @return the isCommit
	 */
	public String getIsCommit() {
		return isCommit;
	}

	/**
	 * @param isCommit the isCommit to set
	 */
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
}
