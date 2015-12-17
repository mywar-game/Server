package com.dataconfig.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.dataconfig.bo.UContinueLoginConstant;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.TTreasureConstantService;
import com.dataconfig.service.UContinueLoginConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateUContinueLoginConstant extends ALDAdminActionSupport implements ModelDriven<UContinueLoginConstant> {

	/** * */
	private static final long serialVersionUID = 1L;

	private UContinueLoginConstant continueLoginConstant = new UContinueLoginConstant();
	
	private List<Map<String, Integer>> rewardList = new ArrayList<Map<String, Integer>>();
	
	private Map<Integer, String> treasureIDNameMap;
	
	private Map<Integer, String> equipmentIdNameMap;
	
	private String isCommit = "F";
	
	public String execute() throws Exception {
		UContinueLoginConstantService continueLoginConstantService = ServiceCacheFactory.getServiceCache().getService(UContinueLoginConstantService.class);
		if ("F".equals(isCommit)) {
			TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
			treasureIDNameMap = treasureConstantService.findTreasureIdNameMap();
			EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
			equipmentIdNameMap = equipmentConstantService.findEquipmentIDNameMap();
			
			continueLoginConstant = continueLoginConstantService.findOneUContinueLoginConstant(continueLoginConstant.getId());
			String rewardInfo = continueLoginConstant.getRewardInfo();
			if (rewardInfo != null && !"".equals(rewardInfo)) {
				JSONArray rewardInfoJsonArr = new JSONArray(rewardInfo);
				Map<String, Integer> treasureRewardMap;
				for (int i = 0; i < rewardInfoJsonArr.length(); i++) {
					treasureRewardMap = new HashMap<String, Integer>();
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
			return INPUT;
		} else {
			//记录日志
			AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
			adminChangeConstantLog.setSysNum(Integer.valueOf(super.getAdminUser().getExp1()));
			adminChangeConstantLog.setAdminName(super.getAdminUser().getAdminName());
			adminChangeConstantLog.setChangeTime(new Timestamp(System.currentTimeMillis()));
			adminChangeConstantLog.setConstantName("UContinueLoginConstant");
			adminChangeConstantLog.setChangeType(3);
			//改动细节
			StringBuffer changeDetail = new StringBuffer();
			//原来的商品
			UContinueLoginConstant beforeContinueLoginConstant = continueLoginConstantService.findOneUContinueLoginConstant(continueLoginConstant.getId());
			changeDetail.append("改动"+super.getText("continueLoginConstant.continueDays")+"为 "+continueLoginConstant.getContinueDays()+" 天的记录：<br/>");
			//属性名
			String propertyName = "";
			//原来的属性的值
			String beforeValue = "";
			//修改之后的属性的值
			String afterValue = "";
			//商城常量的所有属性
			for (Field field : UContinueLoginConstant.class.getDeclaredFields()) {
				propertyName = field.getName();
				try {
					//主键不能改，所以只在乎不是主键的时候
					if (!"id".equals(propertyName)) {
						beforeValue = BeanUtils.getProperty(beforeContinueLoginConstant, propertyName);
						afterValue = BeanUtils.getProperty(continueLoginConstant, propertyName);
						if (beforeValue != null && afterValue != null && !beforeValue.equals(afterValue)) {
							changeDetail.append("<b>"+super.getText("continueLoginConstant."+propertyName)+"</b>由<b> "+beforeValue+" </b>改成<b> "+afterValue+" </b><br/>");
						}
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
			adminChangeConstantLog.setChangeDetail(changeDetail.toString());
			AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory.getServiceCache().getService(AdminChangeConstantLogService.class);
			adminChangeConstantLogService.saveOne(adminChangeConstantLog);
			
			continueLoginConstantService.updateOneUContinueLoginConstant(continueLoginConstant);
			return SUCCESS;
		}
	}
	
	@Override
	public UContinueLoginConstant getModel() {
		return continueLoginConstant;
	}

	public void setContinueLoginConstant(UContinueLoginConstant continueLoginConstant) {
		this.continueLoginConstant = continueLoginConstant;
	}

	public UContinueLoginConstant getContinueLoginConstant() {
		return continueLoginConstant;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap;
	}

	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap;
	}

	public void setRewardList(List<Map<String, Integer>> rewardList) {
		this.rewardList = rewardList;
	}

	public List<Map<String, Integer>> getRewardList() {
		return rewardList;
	}

	public void setEquipmentIdNameMap(Map<Integer, String> equipmentIdNameMap) {
		this.equipmentIdNameMap = equipmentIdNameMap;
	}

	public Map<Integer, String> getEquipmentIdNameMap() {
		return equipmentIdNameMap;
	}
}
