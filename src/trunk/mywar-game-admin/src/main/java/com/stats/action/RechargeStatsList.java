package com.stats.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.util.Tools;
import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bo.RechargeStats;
import com.stats.service.RechargeStatsService;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class RechargeStatsList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String, List<RechargeStats>> statsMap = new HashMap<String, List<RechargeStats>>();
	private Map<String, Partner> partnerMap;
	private List<Map.Entry<String, List<RechargeStats>>> mappingList = new ArrayList<Map.Entry<String, List<RechargeStats>>>(statsMap.entrySet());
	
	@Override
	public String execute(){
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		RechargeStatsService rechargeStatsService = ServiceCacheFactory.getServiceCache().getService(RechargeStatsService.class);
		partnerMap = partnerService.findAllPartnerMap();
		IPage<RechargeStats> page = null;
		if (getStartDate() != null && getEndDate() != null) {
			page = rechargeStatsService.findListInDate(super.getPageSize(), super.getToPage(), getStartDate(), getEndDate());
		} else {
			page = rechargeStatsService.findList(super.getPageSize(), super.getToPage());
		}
		if(page!=null){
			List<RechargeStats> list = (List<RechargeStats>)page.getData();
			if(list!=null && list.size()>0){
				for(RechargeStats stats : list){
					stats.setOldRegPayNum(stats.getPayUserNum()-stats.getNewRegPayNum());
					stats.setOldRegPayTotalAmount(stats.getPayTotalAmount()-stats.getNewRegPayTotalAmount());
					stats.setAgvNewRegPayValue(Float.valueOf(Tools.decimalFormat((double)stats.getNewRegPayTotalAmount(),(double)stats.getNewReg(), "#.00")));
					stats.setPenetrateRate(Float.valueOf(Tools.decimalFormat((double)stats.getNewRegPayNum(),(double)stats.getNewReg(), "#.00")));
					stats.setPayRate(Float.valueOf(Tools.decimalFormat((double)stats.getPayUserNum(),(double)stats.getDayActive(), "#.00")));
					stats.setArppu(Float.valueOf(Tools.decimalFormat((double)stats.getPayTotalAmount(),(double)stats.getPayUserNum(), "#.00")));
					stats.setArpu(Float.valueOf(Tools.decimalFormat((double)stats.getPayTotalAmount(),(double)stats.getDayActive(), "#.00")));
					String date = DateUtil.dateToString(stats.getTime(), DateUtil.LONG_DATE_FORMAT);
					if(statsMap.containsKey(date)){
						statsMap.get(date).add(stats);
					}else{
						List<RechargeStats> ll = new ArrayList<RechargeStats>();
						ll.add(stats);
						statsMap.put(date, ll);
					}
					// 额外添加，显示服务器名字
					int sysNum = stats.getSysNum();
					TGameServer tGameServer = DataSourceManager.getInstatnce().getGameServerMap().get(sysNum);
					stats.setServerName(tGameServer.getServerAlias());
				}
			}
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		// 排序
		if (statsMap.size() > 0) {
			mappingList = new ArrayList<Map.Entry<String, List<RechargeStats>>>(statsMap.entrySet());
			Collections.sort(mappingList, new Comparator<Map.Entry<String, List<RechargeStats>>>() {
				public int compare(Map.Entry<String, List<RechargeStats>> mapping1, Map.Entry<String, List<RechargeStats>> mapping2) {
					return mapping2.getKey().compareTo(mapping1.getKey());
				}
			});
		}
		return SUCCESS;
	}
	public Map<String, List<RechargeStats>> getStatsMap() {
		return statsMap;
	}
	public void setStatsMap(Map<String, List<RechargeStats>> statsMap) {
		this.statsMap = statsMap;
	}
	public Map<String, Partner> getPartnerMap() {
		return partnerMap;
	}
	public void setPartnerMap(Map<String, Partner> partnerMap) {
		this.partnerMap = partnerMap;
	}
	public List<Map.Entry<String, List<RechargeStats>>> getMappingList() {
		return mappingList;
	}
	public void setMappingList(
			List<Map.Entry<String, List<RechargeStats>>> mappingList) {
		this.mappingList = mappingList;
	}
}
