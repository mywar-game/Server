package com.stats.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.dataconfig.bo.EEquipmentConstant;
import com.dataconfig.service.EEquipmentConstantService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserEquipmentLogService;

/**
 * 玩家装备统计列表
 * @author hzy
 * 2012-4-18
 */
public class UserEquipmentModifyStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 5418766029964271697L;

	private List<Map<String, String>> statsList = new ArrayList<Map<String, String>>();
	
	/**
	 * 0 不排序<br>
	 * 1 按强化次数升序排序<br>
	 * 2 按强化次数降序排序<br>
	 * 3 按镶嵌次数升序排序<br>
	 * 4 按镶嵌次数降序排序<br>
	 * 5 按打造次数升序排序<br>
	 * 6 按打造次数降序排序
	 */
	private int order = 0;
	
	private int level = 0;
	
	private Map<Integer, EEquipmentConstant> equipmentConstantMap;
	
	//强化日志service获得装备ID及对应装备强化次数
	private Map<Integer, Integer> strengthenMap = new LinkedHashMap<Integer, Integer>();
	//镶嵌日志service获得装备ID及对应装备镶嵌次数
	private Map<Integer, Integer> insetMap = new LinkedHashMap<Integer, Integer>();
	//打造日志service获得装备ID及对应装备打造次数
	private Map<Integer, Integer> synthesisMap = new LinkedHashMap<Integer, Integer>();
	
	public String execute() {
//		super.setPageSize(3);
//		System.out.println("super.getToPage() ========================== "+ super.getToPage());
		EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		equipmentConstantMap = equipmentConstantService.findIdEquipmentMap();
		
		UserEquipmentLogService userEquipmentLogService = ServiceCacheFactory.getServiceCache().getService(UserEquipmentLogService.class);
		
//		System.out.println("order ============================ "+ order);
		switch (order) {
		case 0: 
			//正常获得三个map
//			System.out.println("不排序");
			strengthenMap = userEquipmentLogService.findModifyAndCountMap(1, order);
			insetMap = userEquipmentLogService.findModifyAndCountMap(2, order);
			synthesisMap = userEquipmentLogService.findModifyAndCountMap(3, order);
			break;
		case 1: 
		case 2: 
			//按强化次数排序获得强化后的map, 其他正常
			if (order == 1) {
//				System.out.println("强化次数 升序");
				strengthenMap = userEquipmentLogService.findModifyAndCountMap(1, order);
			}
			if (order == 2) {
//				System.out.println("强化次数 降序");
				strengthenMap = userEquipmentLogService.findModifyAndCountMap(1, order);
			}
			insetMap = userEquipmentLogService.findModifyAndCountMap(2, 0);
			synthesisMap = userEquipmentLogService.findModifyAndCountMap(3, 0);
			this.addStatsMapInOrder(strengthenMap);
			break;
		case 3: 
		case 4: 
			if (order == 3) {
//				System.out.println("镶嵌次数 升序");
				insetMap = userEquipmentLogService.findModifyAndCountMap(2, order);
			}
			if (order == 4) {
//				System.out.println("镶嵌次数 降序");
				insetMap = userEquipmentLogService.findModifyAndCountMap(2, order);
			}
			//按镶嵌次数排序
			strengthenMap = userEquipmentLogService.findModifyAndCountMap(1, 0);
			synthesisMap = userEquipmentLogService.findModifyAndCountMap(3, 0);
			this.addStatsMapInOrder(insetMap);
			break;
		case 5: 
		case 6: 
			if (order == 5) {
//				System.out.println("打造次数 升序");
				synthesisMap = userEquipmentLogService.findModifyAndCountMap(3, order);
			}
			if (order == 6) {
//				System.out.println("打造次数 降序");
				synthesisMap = userEquipmentLogService.findModifyAndCountMap(3, order);
			}
			//按打造次数排序
			strengthenMap = userEquipmentLogService.findModifyAndCountMap(1, 0);
			insetMap = userEquipmentLogService.findModifyAndCountMap(2, 0);
			this.addStatsMapInOrder(synthesisMap);
			break;
		default : 
			return ERROR;
		}
		
		//按等级查询的话，就遍历装备map，将不是条件等级的equipmentId记录到templist中，再遍历templist，在装备map里一个一个删除
		List<Integer> forLevelList = new ArrayList<Integer>();
		if (level != 0) {
			Set<Entry<Integer, EEquipmentConstant>> entrySet = equipmentConstantMap.entrySet();
			for (Entry<Integer, EEquipmentConstant> entry : entrySet) {
				EEquipmentConstant equipment = entry.getValue();
				if (equipment.getEquipmentLevel() != level) {
					forLevelList.add(equipment.getEquipmentId());
				}
			}
		}
		for (Integer equipmentId : forLevelList) {
			equipmentConstantMap.remove(equipmentId);
		}
		//如果没有没有排序，则按装备列表来一个一个放
		//如果排序了，这里是把statsList加满到一页
		int i = 0;
		Set<Entry<Integer, EEquipmentConstant>> entrySet = equipmentConstantMap.entrySet();
		for (Entry<Integer, EEquipmentConstant> entry : entrySet) {
			EEquipmentConstant equipment = entry.getValue();
//			System.out.println("i ========================= "+ i);
			//超过一页大小则不再放
			if (statsList.size() >= DEFAULT_PAGESIZE) {
//				System.out.println("break ========================= ");
				break;
			}
			//从第 页数*每页  开始放
			if (i == super.getToPage() * DEFAULT_PAGESIZE) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				Integer strengthenAmount = strengthenMap.get(equipment.getEquipmentId());
				Integer insetAmount = insetMap.get(equipment.getEquipmentId());
				Integer synthesisAmount = synthesisMap.get(equipment.getEquipmentId());
//				map.put("equipmentName", equipment.getEquipmentName()+ equipment.getEquipmentId());
				map.put("equipmentName", equipment.getEquipmentName());
				map.put("equipmentLevel", equipment.getEquipmentLevel() + "");
				map.put("strengthenAmount", (strengthenAmount == null ? 0 : strengthenAmount) + "");
				map.put("insetAmount", (insetAmount == null ? 0 : insetAmount) + "");
				map.put("synthesisAmount", (synthesisAmount == null ? 0 : synthesisAmount) + "");
				statsList.add(map);
			} else {
				i++;
			}
		}
		super.setTotalPage(equipmentConstantMap.size() / DEFAULT_PAGESIZE + 1);
		super.setTotalSize(equipmentConstantMap.size());
		return 	SUCCESS;
	}

	private void addStatsMapInOrder(Map<Integer, Integer> map) {
		int j = 0;
		//从排序map搞起
		Set<Entry<Integer, Integer>> entrySet = map.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {
			Integer equipmentId = entry.getKey();
			//能不能放
			if (statsList.size() >= DEFAULT_PAGESIZE) {
//				System.out.println("break ========================= ");
				break;
			}
			//从第 页数*每页  个开始放（分页）
			if (j == super.getToPage() * DEFAULT_PAGESIZE) {
				EEquipmentConstant equipment = equipmentConstantMap.remove(equipmentId);
				
				//按等级查询
				if (level != 0) {
					if (equipment.getEquipmentLevel() != level) {
						continue;
					}
				}
				Map<String, String> statsMap = new LinkedHashMap<String, String>();
				Integer strengthenAmount = entry.getValue();
				Integer insetAmount = insetMap.get(equipment.getEquipmentId());
				Integer synthesisAmount = synthesisMap.get(equipment.getEquipmentId());
//				map.put("equipmentName", equipment.getEquipmentName()+ equipment.getEquipmentId());
				statsMap.put("equipmentName", equipment.getEquipmentName());
				statsMap.put("equipmentLevel", equipment.getEquipmentLevel() + "");
				statsMap.put("strengthenAmount", (strengthenAmount == null ? 0 : strengthenAmount) + "");
				statsMap.put("insetAmount", (insetAmount == null ? 0 : insetAmount) + "");
				statsMap.put("synthesisAmount", (synthesisAmount == null ? 0 : synthesisAmount) + "");
				
				statsList.add(statsMap);
			} else {
				j++;
			}
		}
	}
	
	public void setStatsList(List<Map<String, String>> statsList) {
		this.statsList = statsList;
	}

	public List<Map<String, String>> getStatsList() {
		return statsList;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return order;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
}
