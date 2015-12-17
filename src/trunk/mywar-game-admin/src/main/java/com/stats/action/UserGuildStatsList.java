package com.stats.action; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.adminTool.constant.AdminToolCMDCode;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserPayHistoryLogService;
import com.stats.bean.Guild;
import com.stats.msgbody.ResAllGuildAndGuildMembers;

public class UserGuildStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 1445944846580528925L; 

	private List<Map<String, Object>> statsList = new ArrayList<Map<String, Object>>(); 
	
	@SuppressWarnings("unchecked")
	public String execute() {
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.ALL_GUILD_GUILDMEMBERS, new CommomMsgBody(), ResAllGuildAndGuildMembers.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 获取失败！");
			return SUCCESS;
		}
		ResAllGuildAndGuildMembers res = (ResAllGuildAndGuildMembers)msg.getMsgBody();
		
		//Map<军团Id，军团信息>  所有军团map
		Map<String, Guild> guildMap = res.getGuildMap(); 
		//map<军团id, 军团userIds> 所有军团玩家id
		Map<String, String> guildUserIdsMap = res.getGuildUserIdsMap(); 
		
		UserPayHistoryLogService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayHistoryLogService.class); 
		//Map<军团id，军团所有玩家充值总数> 
		Map<Long, Integer> guildIdAndAllUserPayAmountMap = new HashMap<Long, Integer>(); 
		
		//查出军团所有成员充值，放入map中去
		Set<Entry<String, String>> entrySet = guildUserIdsMap.entrySet(); 
		for (Entry<String, String> entry : entrySet) {
			//当前军团的所有玩家充值总数
			Integer guildAllUserPayAmount = userPayHistoryLogService.getPayAmountByUserIds(entry.getValue().replace("[", "").replace("]", "")); 
			guildIdAndAllUserPayAmountMap.put(Long.valueOf(entry.getKey()), guildAllUserPayAmount); 
		}
		//排序找出充值总数前十的军团
		Set<Entry<Long, Integer>> entrySet2 = guildIdAndAllUserPayAmountMap.entrySet(); 
		Entry<Long, Integer>[] entryArr = entrySet2.toArray(new Entry[entrySet2.size()]); 
		Arrays.sort(entryArr, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Integer key1 = Integer.valueOf(((Entry) arg0).getValue().toString()); 
				Integer key2 = Integer.valueOf(((Entry) arg1).getValue().toString()); 
				return key1.compareTo(key2); 
			}
		}); 
		//在排序好的 entryArray中间前十的放到orderMap
//		System.out.println("前十 =========================== "); 
		Map<Long, Integer> orderMap = new LinkedHashMap<Long, Integer>(); 
		Integer size = entryArr.length > 10 ? 10 : entryArr.length; 
		for (int i = size - 1; i >= 0; i--) {
			 Entry<Long, Integer> entry = entryArr[i]; 
//			 System.out.println(entry.getKey()+" = "+entry.getValue()); 
			 orderMap.put(entry.getKey(), entry.getValue()); 
		}
		//将军团信息和军团所有玩家充值总数放到statsList中
		Set<Entry<Long, Integer>> entrySet3 = orderMap.entrySet(); 
		for (Entry<Long, Integer> entry : entrySet3) {
			Long guildId = entry.getKey(); 
			Integer guildAllUserPayAmount = entry.getValue(); 
			Map<String, Object> statsMap = new LinkedHashMap<String, Object>(); 
			statsMap.put("guild", guildMap.get(guildId + "")); 
			statsMap.put("guildAllUserPayAmount", guildAllUserPayAmount); 
			statsList.add(statsMap); 
		}
		return SUCCESS; 
	}

	public void setStatsList(List<Map<String, Object>> statsList) {
		this.statsList = statsList; 
	}

	public List<Map<String, Object>> getStatsList() {
		return statsList; 
	}
}
