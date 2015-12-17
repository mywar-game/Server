package com.adminTool.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.dataconfig.bo.RegLink;
import com.dataconfig.constant.ParamConfigConstant;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.RegLinkService;
import com.dataconfig.service.SParamConfigService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserRegLinkLogService;

public class UserRegLinkLogList extends ALDAdminLogPageActionSupport {

	/** **/
	private static final long serialVersionUID = -7943237935418838031L;
	
	/** **/
	private List<RegLink> logList = new ArrayList<RegLink>();
	
	/** **/
	private String searchRegLink;
	
	/** **/
	private Date createStartTime;

	/** **/
	private Date createEndTime;
	
//	/** **/
//	private Date effectiveStartTime;
//	
//	/** **/
//	private Date effectiveEndTime;
//	
//	/** **/
//	private Date activationStartTime;
//	
//	/** **/
//	private Date activationEndTime;
	
	private Map<Integer, String> treasureIDNameMap;
	
	private Map<Integer, String> equipmentIdNameMap;
	
	private List<List<Map<String, Integer>>> allRewardList = new ArrayList<List<Map<String, Integer>>>();
	
	public String execute() {
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		treasureIDNameMap = treasureConstantService.findTreasureIdNameMap();
		EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		equipmentIdNameMap = equipmentConstantService.findEquipmentIDNameMap();
		
		RegLinkService regLinkService = ServiceCacheFactory.getServiceCache().getService(RegLinkService.class);
	 	UserRegLinkLogService logService = ServiceCacheFactory.getServiceCache().getService(UserRegLinkLogService.class);
		IPage<RegLink> pageList = regLinkService.findPageList(searchRegLink, createStartTime, createEndTime, super.getPageSize(), super.getToPage());
		if (pageList != null) {
			logList = (List<RegLink>)pageList.getData();
			super.setTotalSize(pageList.getTotalSize());
			super.setTotalPage(pageList.getTotalPage());
			
			StringBuffer regLinkIds = new StringBuffer();
			for (int i = 0; i < logList.size(); i++) {
				regLinkIds.append("'").append(logList.get(i).getRegLinkId()).append("'");
				if (i < (logList.size()-1)) {
					regLinkIds.append(",");
				}
			}
			
			Map<String, Integer> map = logService.findMap(regLinkIds.toString());
			if (map != null && map.size() > 0) {
				for (RegLink regLink : logList) {
					String regLinkId = regLink.getRegLinkId();
					if (map.get(regLinkId) == null) {
						regLink.setUseNum(0);
					} else {
						regLink.setUseNum(map.get(regLinkId));
					}
				}
			}
			
			SParamConfigService paramConfigService = ServiceCacheFactory.getServiceCache().getService(SParamConfigService.class);
			String url = paramConfigService.findOneSParamConfig(ParamConfigConstant.INSTRODUCTOR_URL).getValue1();
			for (RegLink regLink : logList) {
				regLink.setRegLinkId(url + "?linkId=" + regLink.getRegLinkId());
				regLink.setUseNum(regLink.getUseNum()==null? 0:regLink.getUseNum());
				
				List<Map<String, Integer>> rewardList = new ArrayList<Map<String, Integer>>();
				String rewardInfo = regLink.getReward();
				if (rewardInfo != null && !"".equals(rewardInfo)) {
					try {
						JSONArray rewardInfoJsonArr = new JSONArray(rewardInfo);
						for (int i = 0; i < rewardInfoJsonArr.length(); i++) {
							Map<String, Integer> rewardMap = new HashMap<String, Integer>();
							JSONObject treasureRewardJsonObj = rewardInfoJsonArr.getJSONObject(i);
							int targetId = treasureRewardJsonObj.getInt("targetId");
							int category = treasureRewardJsonObj.getInt("category");
							int num = treasureRewardJsonObj.getInt("num");
							rewardMap.put("targetId", targetId);
							rewardMap.put("category", category);
							rewardMap.put("num", num);
							rewardList.add(rewardMap);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				allRewardList.add(rewardList);
			}
		}
		return SUCCESS;
	}

	public void setLogList(List<RegLink> logList) {
		this.logList = logList;
	}

	public List<RegLink> getLogList() {
		return logList;
	}

	public void setSearchRegLink(String searchRegLink) {
		this.searchRegLink = searchRegLink;
	}

	public String getSearchRegLink() {
		return searchRegLink;
	}

	public void setCreateStartTime(Date createStartTime) {
		this.createStartTime = createStartTime;
	}

	public Date getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}

	public Date getCreateEndTime() {
		return createEndTime;
	}

	/**
	 * 获取 treasureIDNameMap 
	 */
	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap;
	}

	/**
	 * 设置 treasureIDNameMap 
	 */
	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap;
	}

	/**
	 * 获取 equipmentIdNameMap 
	 */
	public Map<Integer, String> getEquipmentIdNameMap() {
		return equipmentIdNameMap;
	}

	/**
	 * 设置 equipmentIdNameMap 
	 */
	public void setEquipmentIdNameMap(Map<Integer, String> equipmentIdNameMap) {
		this.equipmentIdNameMap = equipmentIdNameMap;
	}

	/**
	 * 获取 allRewardList 
	 */
	public List<List<Map<String, Integer>>> getAllRewardList() {
		return allRewardList;
	}

	/**
	 * 设置 allRewardList 
	 */
	public void setAllRewardList(List<List<Map<String, Integer>>> allRewardList) {
		this.allRewardList = allRewardList;
	}
}
