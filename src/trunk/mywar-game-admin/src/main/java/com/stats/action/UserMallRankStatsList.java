package com.stats.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bo.UserMallRankStats;
import com.stats.service.UserMallRankStatsService;

public class UserMallRankStatsList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String, List<UserMallRankStats>> statsMap = new HashMap<String, List<UserMallRankStats>>();
	private Map<Integer, String> treasureIdNameMap;
	List<Map.Entry<String, List<UserMallRankStats>>> mappingList = new ArrayList<Map.Entry<String, List<UserMallRankStats>>>();
	
	@Override
	public String execute(){
		UserMallRankStatsService userMallRankStatsService = ServiceCacheFactory.getServiceCache().getService(UserMallRankStatsService.class);
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		treasureIdNameMap = treasureConstantService.findMallTreasureIdNameMap();
		IPage<UserMallRankStats> page = null;
		if (getStartDate() != null && getEndDate() != null) {
			page = userMallRankStatsService.findListInDate(super.getPageSize(), super.getToPage(), getStartDate(), getEndDate());
		} else {
			page = userMallRankStatsService.findList(super.getPageSize(), super.getToPage());
		}
		if(page!=null){
			List<UserMallRankStats> list = (List<UserMallRankStats>)page.getData();
			if(list!=null && list.size()>0){
				for(UserMallRankStats stats : list){
					String date = DateUtil.dateToString(stats.getTime(), DateUtil.LONG_DATE_FORMAT);
					if(statsMap.containsKey(date)){
						statsMap.get(date).add(stats);
					}else{
						List<UserMallRankStats> ll = new ArrayList<UserMallRankStats>();
						ll.add(stats);
						statsMap.put(date, ll);
					}
				}
			}
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		if(statsMap.size()>0){
			for(List<UserMallRankStats> list : statsMap.values()){
				Collections.sort(list, new Comparator<UserMallRankStats>() {

					@Override
					public int compare(UserMallRankStats o1,
							UserMallRankStats o2) {
						// TODO Auto-generated method stub
						if(o1.getRank().intValue()>o2.getRank().intValue()){
							return 1;
						}else{
							return -1;
						}
					}
					
				});
			}
		}
		// 排序
		if (statsMap.size() > 0) {
			mappingList = new ArrayList<Map.Entry<String, List<UserMallRankStats>>>(statsMap.entrySet());
			Collections.sort(mappingList, new Comparator<Map.Entry<String, List<UserMallRankStats>>>() {
				public int compare(Map.Entry<String, List<UserMallRankStats>> mapping1, Map.Entry<String, List<UserMallRankStats>> mapping2) {
					return mapping2.getKey().compareTo(mapping1.getKey());
				}
			});
		}
		return SUCCESS;
	}
	public Map<String, List<UserMallRankStats>> getStatsMap() {
		return statsMap;
	}
	public void setStatsMap(Map<String, List<UserMallRankStats>> statsMap) {
		this.statsMap = statsMap;
	}
	public Map<Integer, String> getTreasureIdNameMap() {
		return treasureIdNameMap;
	}
	public void setTreasureIdNameMap(Map<Integer, String> treasureIdNameMap) {
		this.treasureIdNameMap = treasureIdNameMap;
	}
	public List<Map.Entry<String, List<UserMallRankStats>>> getMappingList() {
		return mappingList;
	}
	public void setMappingList(
			List<Map.Entry<String, List<UserMallRankStats>>> mappingList) {
		this.mappingList = mappingList;
	}
}
