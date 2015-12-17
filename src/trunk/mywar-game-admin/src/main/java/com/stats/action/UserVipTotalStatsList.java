package com.stats.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bo.UserVipTotalStats;
import com.stats.service.UserVipTotalStatsService;

public class UserVipTotalStatsList extends ALDAdminStatsDatePageActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Map<String, List<UserVipTotalStats>> statsMap = new HashMap<String, List<UserVipTotalStats>>();
	private Map<String, Partner> partnerMap;
	
	@Override
	public String execute(){
		
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		UserVipTotalStatsService userVipTotalStatsService = ServiceCacheFactory.getServiceCache().getService(UserVipTotalStatsService.class);
		partnerMap = partnerService.findAllPartnerMap();
		
		IPage<UserVipTotalStats> page = null;
		if (getStartDate() != null && getEndDate() != null) {
			page = userVipTotalStatsService.findListInDate(super.getPageSize(), super.getToPage(), getStartDate(), getEndDate());
		} else {
			page = userVipTotalStatsService.findList(super.getPageSize(), super.getToPage());
		}
		if(page!=null){
			List<UserVipTotalStats> list = (List<UserVipTotalStats>)page.getData();
			if(list!=null && list.size()>0){
				for(UserVipTotalStats stats : list){
					String date = DateUtil.dateToString(stats.getTime(), DateUtil.LONG_DATE_FORMAT);
					String[] arr = stats.getVipInfo().split(",");
					for(int i=0;i<arr.length;i++){
						String[] vip = arr[i].split("_");
						int vLevel = Integer.parseInt(vip[0]);
						if(vLevel==0){
							stats.setV0(Integer.parseInt(vip[1]));
						}else if(vLevel==1){
							stats.setV1(Integer.parseInt(vip[1]));
						}else if(vLevel==2){
							stats.setV2(Integer.parseInt(vip[1]));
						}else if(vLevel==3){
							stats.setV3(Integer.parseInt(vip[1]));
						}else if(vLevel==4){
							stats.setV4(Integer.parseInt(vip[1]));
						}else if(vLevel==5){
							stats.setV5(Integer.parseInt(vip[1]));
						}else if(vLevel==6){
							stats.setV6(Integer.parseInt(vip[1]));
						}else if(vLevel==7){
							stats.setV7(Integer.parseInt(vip[1]));
						}else if(vLevel==8){
							stats.setV8(Integer.parseInt(vip[1]));
						}else if(vLevel==9){
							stats.setV9(Integer.parseInt(vip[1]));
						}else if(vLevel==10){
							stats.setV10(Integer.parseInt(vip[1]));
						}
					}
					if(statsMap.containsKey(date)){
						statsMap.get(date).add(stats);
					}else{
						List<UserVipTotalStats> ll = new ArrayList<UserVipTotalStats>();
						ll.add(stats);
						statsMap.put(date, ll);
					}
				}
			}
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
	public Map<String, List<UserVipTotalStats>> getStatsMap() {
		return statsMap;
	}
	public void setStatsMap(Map<String, List<UserVipTotalStats>> statsMap) {
		this.statsMap = statsMap;
	}
	public Map<String, Partner> getPartnerMap() {
		return partnerMap;
	}
	public void setPartnerMap(Map<String, Partner> partnerMap) {
		this.partnerMap = partnerMap;
	}
}
