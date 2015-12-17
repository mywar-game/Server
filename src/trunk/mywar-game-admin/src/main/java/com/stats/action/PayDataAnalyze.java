package com.stats.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.util.Tools;
import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bean.PayData;
import com.stats.service.SummaryStatsService;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class PayDataAnalyze extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 0l;
	private String isCommit = "F";
	private List<PayData> payList = new ArrayList<PayData>();
	public String execute() {
		if(isCommit.equals("T")){
			SummaryStatsService summaryStatsService = ServiceCacheFactory.getServiceCache().getService(SummaryStatsService.class);
			PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
			List<Partner> partnerList = partnerService.findPartnerList();
			Map<String, String> partnerMap = new HashMap<String, String>();
			for (Partner p : partnerList) {
				partnerMap.put(p.getPNum(), p.getPName());
			}
			String[] dates = new String[2];
			dates[0] = DateUtil.dateToString(super.getStartDate(), DateUtil.LONG_DATE_FORMAT);
			dates[1] = DateUtil.dateToString(super.getEndDate(), DateUtil.LONG_DATE_FORMAT);
			Map<String,Object[]> payMap = summaryStatsService.findSummaryDataHistory(dates);//根据渠道分组的充值总数、购买道具消耗
			if(payMap.size()>0){
				for(String channel : payMap.keySet()){
					Object[] arr = payMap.get(channel);
					PayData data = new PayData();
//					Map<Integer, TGameServer> tGameServerMap = DataSourceManager.getInstatnce().getGameServerMap();
//					TGameServer tGameServer = tGameServerMap.get(Integer.parseInt(channel));
//					if (tGameServer != null) {
//						
//					}
					data.setChannel(partnerMap.get(channel));
					data.setPayAmount(Double.valueOf(arr[1].toString()));
					Map<String, Integer> userMap = new HashMap<String, Integer>();
					if(!arr[3].toString().equals("")){//将组装起来的玩家编号遍历得出不重复的充值玩家数量
						String[] userArr = arr[3].toString().split(",");
						if(userArr!=null && userArr.length>0){
							for(int i=0;i<userArr.length;i++){
								if(!userArr[i].equals("")){
									userMap.put(userArr[i], 1);
								}
							}
						}
					}
					data.setPayUserNum(userMap.size());
					data.setPayTimes(Integer.valueOf(arr[4].toString()));
					data.setBuyToolConsume(Integer.valueOf(arr[2].toString()));
					data.setArpu(Tools.decimalFormat(data.getPayAmount(),(double)data.getPayUserNum(), "#.00"));
					payList.add(data);
				}
			}
		}
		return SUCCESS;
	}
	public String getIsCommit() {
		return isCommit;
	}
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	public List<PayData> getPayList() {
		return payList;
	}
	public void setPayList(List<PayData> payList) {
		this.payList = payList;
	}	
	
}
