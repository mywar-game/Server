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
import com.stats.bo.UserVipStats;
import com.stats.service.UserVipStatsService;

public class UserVipStatsList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String, List<UserVipStats>> statsMap = new HashMap<String, List<UserVipStats>>();
	private Map<String, Partner> partnerMap;
	@Override
	public String execute(){
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		UserVipStatsService userVipStatsService = ServiceCacheFactory.getServiceCache().getService(UserVipStatsService.class);
		partnerMap = partnerService.findAllPartnerMap();
		IPage<UserVipStats> page = null;
		if (getStartDate() != null && getEndDate() != null) {
			page = userVipStatsService.findListInDate(super.getPageSize(), super.getToPage(), getStartDate(), getEndDate());
		} else {
			page = userVipStatsService.findList(super.getPageSize(), super.getToPage());
		}
		if(page!=null){
			List<UserVipStats> list = (List<UserVipStats>)page.getData();
			if(list!=null && list.size()>0){
				for(UserVipStats stats : list){
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
						List<UserVipStats> ll = new ArrayList<UserVipStats>();
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
	public Map<String, List<UserVipStats>> getStatsMap() {
		return statsMap;
	}
	public void setStatsMap(Map<String, List<UserVipStats>> statsMap) {
		this.statsMap = statsMap;
	}
	public Map<String, Partner> getPartnerMap() {
		return partnerMap;
	}
	public void setPartnerMap(Map<String, Partner> partnerMap) {
		this.partnerMap = partnerMap;
	}
}
