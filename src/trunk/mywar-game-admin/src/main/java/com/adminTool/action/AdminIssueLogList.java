package com.adminTool.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.adminTool.bo.AdminIssueLog;
import com.adminTool.service.AdminIssueLogService;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class AdminIssueLogList extends ALDAdminPageActionSupport {

	/** **/
	private static final long serialVersionUID = 3159513873887688973L;
	
	/** **/
	private List<AdminIssueLog> list;
	
	/** **/
	private Map<Integer, List<Map<String, Integer>>> allIssueMap = new HashMap<Integer, List<Map<String, Integer>>>();
	
	/** **/
	private Map<Integer, String> treasureIdNameMap;
	
	/** **/
	private Map<Integer, String> equipmentIdNameMap;

	public String execute() throws Exception {
		AdminIssueLogService adminIssueLogService = ServiceCacheFactory.getServiceCache().getService(AdminIssueLogService.class);
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		treasureIdNameMap = treasureConstantService.findTreasureIdNameMap();
		equipmentIdNameMap = equipmentConstantService.findEquipmentIDNameMap();
		
		IPage<AdminIssueLog> pageList = adminIssueLogService.findPageList(DEFAULT_PAGESIZE, super.getToPage());
		if (pageList != null) {
			list = (List<AdminIssueLog>) pageList.getData();
			//id 发放列表
			for (AdminIssueLog adminIssueLog : list) {
				adminIssueLog.setContent(adminIssueLog.getContent().replaceAll("\\r\\n", "<br>"));
				adminIssueLog.setIssueReason(adminIssueLog.getIssueReason().replaceAll("\\r\\n", "<br>"));
				adminIssueLog.setFailUser(adminIssueLog.getFailUser().replace("\\r\\n", "<br>"));
				//本次发放的所有东西
				List<Map<String, Integer>> issueList = new ArrayList<Map<String, Integer>>();
				String mailAttach = adminIssueLog.getMailAttach();
				JSONArray jsonArray = new JSONArray(mailAttach);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					int attachType = jsonObject.getInt("attachType");
					int attachId = jsonObject.getInt("attachId");
					int attachNum = jsonObject.getInt("attachNum");
					//具体发放什么
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("attachType", attachType);
					map.put("attachId", attachId);
					map.put("attachNum", attachNum);
					issueList.add(map);
				}
				allIssueMap.put(adminIssueLog.getId(), issueList);
			}
			
			
			super.setTotalPage(pageList.getTotalPage());
			super.setTotalSize(pageList.getTotalSize());
		}
		return SUCCESS;
	}

	/**
	 * @return the list
	 */
	public List<AdminIssueLog> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<AdminIssueLog> list) {
		this.list = list;
	}

	/**
	 * @return the allIssueMap
	 */
	public Map<Integer, List<Map<String, Integer>>> getAllIssueMap() {
		return allIssueMap;
	}

	/**
	 * @param allIssueMap the allIssueMap to set
	 */
	public void setAllIssueMap(Map<Integer, List<Map<String, Integer>>> allIssueMap) {
		this.allIssueMap = allIssueMap;
	}

	/**
	 * @return the treasureIdNameMap
	 */
	public Map<Integer, String> getTreasureIdNameMap() {
		return treasureIdNameMap;
	}

	/**
	 * @param treasureIdNameMap the treasureIdNameMap to set
	 */
	public void setTreasureIdNameMap(Map<Integer, String> treasureIdNameMap) {
		this.treasureIdNameMap = treasureIdNameMap;
	}

	/**
	 * @return the equipmentIdNameMap
	 */
	public Map<Integer, String> getEquipmentIdNameMap() {
		return equipmentIdNameMap;
	}

	/**
	 * @param equipmentIdNameMap the equipmentIdNameMap to set
	 */
	public void setEquipmentIdNameMap(Map<Integer, String> equipmentIdNameMap) {
		this.equipmentIdNameMap = equipmentIdNameMap;
	}
}
