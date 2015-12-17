package com.stats.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.dataconfig.service.UserPayService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.PayIntervalStats;
import com.stats.service.PayIntervalStatsService;

public class PayIntervalStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 2001470949540655168L;

	private List<PayIntervalStats> statsList = new ArrayList<PayIntervalStats>();
	private int isFlag = 0;
	private String sendBeginDate;
	private String sendEndDate;
	
	public String execute() {
		PayIntervalStatsService payIntervalStatsService = ServiceCacheFactory.getServiceCache().getService(PayIntervalStatsService.class);
		if (isFlag == 0) {
			statsList = (List<PayIntervalStats>)payIntervalStatsService.findPageList(super.getPageSize(), super.getToPage()).getData();
		}
		if (isFlag != 0) {
			
			//key:充值区间索引，value：人数
			Map<Long, Integer> statsMap = new LinkedHashMap<Long, Integer>();
			UserPayService userPayService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
			String[] dates = {sendBeginDate, sendEndDate};
			//所有玩家的充值数
			Map<String, String> allUserPayAmountMap = userPayService.getEveryUserPayAmountByDates("ASC", dates);
			if (allUserPayAmountMap != null && allUserPayAmountMap.size() > 0) {
				Set<Entry<String, String>> entrySet = allUserPayAmountMap.entrySet();
				//充值数
				long payAmount;
				//充值区间索引
				long payIntervalIndex;
				for (Entry<String, String> entry : entrySet) {
					payAmount = Integer.valueOf(entry.getValue().split("_")[1]);
					payIntervalIndex = payAmount/100;
					//如果当前区间没人，设为0；否则的话当前区间人数加一
					if (statsMap.get(payIntervalIndex) == null) {
						statsMap.put(payIntervalIndex, 1);
					} else {
						statsMap.put(payIntervalIndex, statsMap.get(payIntervalIndex)+1);
					}
				}
			}
			
			if (statsMap != null && statsMap.size() > 0) {
				Set<Entry<Long, Integer>> entrySet = statsMap.entrySet();
				// 充值区间索引
				long payIntervalIndex;
				PayIntervalStats payIntervalStats;
				
				for (Entry<Long, Integer> entry : entrySet) {
					payIntervalStats = new PayIntervalStats();
					payIntervalStats.setSysNum(CustomerContextHolder.getSystemNum());
					payIntervalIndex = entry.getKey();
					String key = payIntervalIndex*100+"-"+((payIntervalIndex + 1)*100 -1);
					
					payIntervalStats.setPayInterval(key);
					payIntervalStats.setUserNum(entry.getValue());
					statsList.add(payIntervalStats);
				}
			}
		}
		
		return SUCCESS;
	}

	public void setStatsList(List<PayIntervalStats> statsList) {
		this.statsList = statsList;
	}

	public List<PayIntervalStats> getStatsList() {
		return statsList;
	}
	
	public String getSendBeginDate() {
		return sendBeginDate;
	}

	public void setSendBeginDate(String sendBeginDate) {
		this.sendBeginDate = sendBeginDate;
	}

	public String getSendEndDate() {
		return sendEndDate;
	}

	public void setSendEndDate(String sendEndDate) {
		this.sendEndDate = sendEndDate;
	}

	public int getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(int isFlag) {
		this.isFlag = isFlag;
	}
}
