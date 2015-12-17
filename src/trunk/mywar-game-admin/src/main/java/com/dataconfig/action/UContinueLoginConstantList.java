package com.dataconfig.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.dataconfig.bo.UContinueLoginConstant;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.TTreasureConstantService;
import com.dataconfig.service.UContinueLoginConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UContinueLoginConstantList extends ALDAdminActionSupport {
	
	private static final long serialVersionUID = -750665319669464281L;

	private List<UContinueLoginConstant> continueLoginConstantList;
	
	private List<List<Map<String, Integer>>> allRewardList = new ArrayList<List<Map<String, Integer>>>();
	
	private Map<Integer, String> treasureIDNameMap;
	
	private Map<Integer, String> equipmentIdNameMap;
	
	public String execute() throws JSONException {
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		treasureIDNameMap = treasureConstantService.findTreasureIdNameMap();
		EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		equipmentIdNameMap = equipmentConstantService.findEquipmentIDNameMap();
		
//		[{"rewardId":1,"rewardType":3,"rewardNum":5000}]
		
		UContinueLoginConstantService continueLoginConstantService = ServiceCacheFactory.getServiceCache().getService(UContinueLoginConstantService.class);
		continueLoginConstantList = continueLoginConstantService.findUContinueLoginConstantList();
		for (UContinueLoginConstant continueLoginConstant : continueLoginConstantList) {
			List<Map<String, Integer>> rewardList = new ArrayList<Map<String, Integer>>();
			String rewardInfo = continueLoginConstant.getRewardInfo();
			if (rewardInfo != null && !"".equals(rewardInfo)) {
				JSONArray rewardInfoJsonArr = new JSONArray(rewardInfo);
				
				for (int i = 0; i < rewardInfoJsonArr.length(); i++) {
					Map<String, Integer> treasureRewardMap = new HashMap<String, Integer>();
					JSONObject treasureRewardJsonObj = rewardInfoJsonArr.getJSONObject(i);
					int rewardId = treasureRewardJsonObj.getInt("rewardId");
					int rewardType = treasureRewardJsonObj.getInt("rewardType");
					int rewardNum = treasureRewardJsonObj.getInt("rewardNum");
					treasureRewardMap.put("rewardId", rewardId);
					treasureRewardMap.put("rewardType", rewardType);
					treasureRewardMap.put("rewardNum", rewardNum);
					rewardList.add(treasureRewardMap);
				}
			}
			allRewardList.add(rewardList);
		}
		return SUCCESS;
	}
	
	public void setContinueLoginConstantList(List<UContinueLoginConstant> continueLoginConstantList) {
		this.continueLoginConstantList = continueLoginConstantList;
	}

	public List<UContinueLoginConstant> getContinueLoginConstantList() {
		return continueLoginConstantList;
	}

	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap;
	}

	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap;
	}

	public void setEquipmentIdNameMap(Map<Integer, String> equipmentIdNameMap) {
		this.equipmentIdNameMap = equipmentIdNameMap;
	}

	public Map<Integer, String> getEquipmentIdNameMap() {
		return equipmentIdNameMap;
	}

	public void setAllRewardList(List<List<Map<String, Integer>>> allRewardList) {
		this.allRewardList = allRewardList;
	}

	public List<List<Map<String, Integer>>> getAllRewardList() {
		return allRewardList;
	}
}
