package com.stats.action;
import java.util.List;

import com.admin.util.Tools;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.NormalDataStats;
import com.stats.service.NormalDataStatsService;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class NormalDataStatsList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private List<NormalDataStats> statsList;
	@Override
	public String execute() throws Exception {
		NormalDataStatsService normalDataStatsService =ServiceCacheFactory.getServiceCache().getService(NormalDataStatsService.class);
		IPage<NormalDataStats> page = null;
		//判断是否是条件查询
		if (getStartDate() != null && getEndDate() != null) {
			page = normalDataStatsService.findListInDate(super.getPageSize(), super.getToPage(), getStartDate(), getEndDate());
		} else {
			page = normalDataStatsService.findList(super.getPageSize(), super.getToPage());
		}
		if (page != null) {
			statsList = (List<NormalDataStats>) page.getData();
			if(statsList!=null && statsList.size()>0){
				for(NormalDataStats normalDataStats : statsList){
					normalDataStats.setAgvOnline(normalDataStats.getTotalOnline()/288);
					normalDataStats.setActiveRate(Tools.decimalFormat((double)normalDataStats.getDayActive(),(double)normalDataStats.getAgvOnline(), "#.0"));
					//normalDataStats.setAgvTime(Tools.decimalFormat((double)normalDataStats.getTotalOnline(),(double)normalDataStats.getDayActive(), "#.00"));
					normalDataStats.setAgvLoginNum(normalDataStats.getDayActive()==0?0:normalDataStats.getLoginTotalNum()/normalDataStats.getDayActive());
					normalDataStats.setActivePayRate(Tools.decimalFormat((double)normalDataStats.getPayNum(),(double)normalDataStats.getDayActive(), "#.0"));
					double totalIncome = 0d;
					if(normalDataStats.getChannelIncomeInfo()!=null && !normalDataStats.getChannelIncomeInfo().equals("")){
						String[] arr = normalDataStats.getChannelIncomeInfo().split(",");//渠道收入信息
						for(int i=0;i<arr.length;i++){
							int lastIndex = arr[i].lastIndexOf("_");
							totalIncome += Double.valueOf(arr[i].substring(lastIndex + 1));
							// totalIncome+=Double.valueOf(arr[i].split("_")[1]); 
						}
					}
					normalDataStats.setDayIncome(totalIncome);
					normalDataStats.setArpu(Tools.decimalFormat(normalDataStats.getDayIncome(),(double)normalDataStats.getDayActive(), "#.00"));
					normalDataStats.setArppu(Tools.decimalFormat(normalDataStats.getDayIncome(),(double)normalDataStats.getPayNum(), "#.00"));
					normalDataStats.setRegArpu(Tools.decimalFormat(normalDataStats.getDayIncome(),(double)normalDataStats.getNewReg(), "#.00"));
					int sysNum = normalDataStats.getSysNum();
					TGameServer tGameServer = DataSourceManager.getInstatnce().getGameServerMap().get(sysNum);
					normalDataStats.setServerName(tGameServer.getServerAlias());
				}
			}
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
	public List<NormalDataStats> getStatsList() {
		return statsList;
	}
	public void setStatsList(List<NormalDataStats> statsList) {
		this.statsList = statsList;
	}
	
}
