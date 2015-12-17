package com.stats.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserLevelStats;
import com.stats.service.UserLevelStatsService;

public class UserLevelStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 0L;
	private List<UserLevelStats> statsList;
	
	public String execute() {
		UserLevelStatsService userLevelStatsService = ServiceCacheFactory.getServiceCache().getService(UserLevelStatsService.class);
		IPage<UserLevelStats> page = null;
		//判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = userLevelStatsService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
			page = userLevelStatsService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<UserLevelStats>) page.getData();
			if(statsList!=null && statsList.size()>0){
				for(UserLevelStats stats : statsList){
					Map<String, Integer> map = new HashMap<String, Integer>();
					if(stats.getLevelIndexInfo()!=null && !stats.getLevelIndexInfo().equals("")){
						String[] arr = stats.getLevelIndexInfo().split(",");
						for(int i=0;i<arr.length;i++){
							String[] levelArr = arr[i].split("_");
							map.put(levelArr[0], Integer.valueOf(levelArr[1]));
						}
					}
					if(map.containsKey("0")){
						stats.setIndex0(map.get("0"));
					}
					if(map.containsKey("1")){
						stats.setIndex1(map.get("1"));
					}
					if(map.containsKey("2")){
						stats.setIndex2(map.get("2"));
					}
					if(map.containsKey("3")){
						stats.setIndex3(map.get("3"));
					}
					if(map.containsKey("4")){
						stats.setIndex4(map.get("4"));
					}
					if(map.containsKey("5")){
						stats.setIndex5(map.get("5"));
					}
					if(map.containsKey("6")){
						stats.setIndex6(map.get("6"));
					}
					if(map.containsKey("7")){
						stats.setIndex7(map.get("7"));
					}
					if(map.containsKey("8")){
						stats.setIndex8(map.get("8"));
					}
					if(map.containsKey("9")){
						stats.setIndex9(map.get("9"));
					}
					if(map.containsKey("10")){
						stats.setIndex10(map.get("10"));
					}
					if(map.containsKey("11")){
						stats.setIndex11(map.get("11"));
					}
					if(map.containsKey("12")){
						stats.setIndex12(map.get("12"));
					}
					if(map.containsKey("13")){
						stats.setIndex13(map.get("13"));
					}
					if(map.containsKey("14")){
						stats.setIndex14(map.get("14"));
					}
					if(map.containsKey("15")){
						stats.setIndex15(map.get("15"));
					}
					if(map.containsKey("16")){
						stats.setIndex16(map.get("16"));
					}
					if(map.containsKey("17")){
						stats.setIndex17(map.get("17"));
					}
					if(map.containsKey("18")){
						stats.setIndex18(map.get("18"));
					}
					if(map.containsKey("19")){
						stats.setIndex19(map.get("19"));
					}
				}
			}
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
	
		return SUCCESS;
	}

	public void setStatsList(List<UserLevelStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserLevelStats> getStatsList() {
		return statsList;
	}

}
