package com.stats.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.adminTool.constant.AdminToolCMDCode;
import com.framework.cache.CacheManager;
import com.framework.client.http.HttpServersBridge;
import com.framework.client.socket.SocketClient;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.MD5;
import com.framework.config.LocalTools;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.server.msg.MsgBuilder;
import com.framework.server.msg.model.IntM;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.cache.ServerClientCache;
import com.stats.msgbody.AppointStatBean;
import com.stats.msgbody.ReqStatisticsMsg;
import com.stats.msgbody.ResBattleInfoMsg;
import com.stats.msgbody.ResStatisticsMsg;
import com.stats.msgbody.StatBean;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class TaskManagerStatsList extends ALDAdminPageActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * task执行详情
	 */
	private Map<Integer, StatBean> statsMap = new LinkedHashMap<Integer, StatBean>();
	/**
	 * task执行详情
	 */
	private Map<Integer,Map<String,StatBean>> appiontStatsMap = new LinkedHashMap<Integer,Map<String,StatBean>>();
	/**
	 * 命令请求类型
	 * 1 查询 2开启 3关闭
	 */
	private Integer type;
	
	/**
	 * 战斗正在进行的场次
	 */
	private Integer battleIngNum;
	
	/**
	 * 总战斗的场次
	 */
	private Integer totalBattleNum;
	
	/**
	 * 竞技场匹配的房间数量
	 */
	private Integer arenaMatchTimes;
	
	/**
	 * 排位赛匹配的数量
	 */
	private Integer qualifyMatchTimes;
	
	/**
	 * 命令请求类型
	 * 1平台服务器2战斗服务器 3排位赛服务器4.聊天服务器
	 */
	private Integer requestType;
	
	/**
	 * 当前处理类型1:开启2关闭
	 */
	private Integer showType;
	
	/**
	 * task计算平均值
	 */
	private Map<Integer, String> aveageValue = new HashMap<Integer, String>();
	
	public String execute(){
		//默认查询
		if(type == null){
			type = 1;
		}
		if(requestType == null){
			requestType = 1;
		}
		//查询处理平台请求
		ReqStatisticsMsg reqStatisticsMsg = new ReqStatisticsMsg();
		reqStatisticsMsg.setType(type);
		//判断是发送平台服务器,聊天服务器,战斗服务器,或者排位赛服务器
		Msg msg = null;
		Msg battleMsg = null;
		SocketClient chatSocketClient = null;
		TGameServer gameServer = null;
		if(requestType > 1){
			gameServer = DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum());
			if(gameServer == null){
				return ERROR;
			}
		}
		switch(requestType){
		case 1:
			//平台服务器
			msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.TASK_MANAGETQUERY_TASK, reqStatisticsMsg, ResStatisticsMsg.class);
			if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
				CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
				super.setErroCodeNum(commomMsgBody.getErrorCode());
				super.setErroDescrip(commomMsgBody.getErrorDescription()+" 失败！");
				return SUCCESS;
			}
			break;
		case 2:
			//战斗服务器
			chatSocketClient = CacheManager.getCacheInstance(ServerClientCache.class).get(gameServer.getBattleServerId()+"").getSocketClient();
			if(chatSocketClient!=null){
	    		String userSequence = MD5.md5Of16(AdminToolCMDCode.TASK_MANAGETQUERY_TASK+gameServer.getServerCommunicationKey());
	            Msg msg1 =  MsgBuilder.buildMsg(0, "0", 0, "0", 1, 100, AdminToolCMDCode.TASK_MANAGETQUERY_TASK, LocalTools.getLocalConfig().getSystemNum(), "0", SystemConstant.SUCCESS_CODE,userSequence, reqStatisticsMsg);
	            msg = chatSocketClient.sendMsgsToServer(msg1, ResStatisticsMsg.class);
	            //查询战斗信息 --ResBattleInfoMsg
	            String userSequence1 = MD5.md5Of16(AdminToolCMDCode.QUERY_BATTLE_SERVER_BATTLE_INFO+gameServer.getServerCommunicationKey());
	            Msg battleMsg1 =  MsgBuilder.buildMsg(0, "0", 0, "0", 1, 100, AdminToolCMDCode.QUERY_BATTLE_SERVER_BATTLE_INFO, LocalTools.getLocalConfig().getSystemNum(), "0", SystemConstant.SUCCESS_CODE,userSequence1, new CommomMsgBody());
	            battleMsg = chatSocketClient.sendMsgsToServer(battleMsg1, ResBattleInfoMsg.class);
	            //记录统计信息
	            ResBattleInfoMsg battleInfoMsg = (ResBattleInfoMsg)battleMsg.getMsgBody();
	            battleIngNum = battleInfoMsg.getBattleIngNum();
	            totalBattleNum = battleInfoMsg.getTotalBattleNum();
	            chatSocketClient.close();
	            if(msg == null || msg.getMsgHead().getErrorCode()!=SystemConstant.SUCCESS_CODE){
	            	throw new RuntimeException("发送战斗服务器失败:"+((CommomMsgBody)msg.getMsgBody()).getErrorDescription());
	            }
	        }
			break;
		case 3:
			//排位赛服务器
			chatSocketClient = CacheManager.getCacheInstance(ServerClientCache.class).get(gameServer.getOrderServerId()+"").getSocketClient();
			if(chatSocketClient!=null){
	    		String userSequence = MD5.md5Of16(AdminToolCMDCode.TASK_MANAGETQUERY_TASK+gameServer.getServerCommunicationKey());
	            Msg msg1 =  MsgBuilder.buildMsg(0, "0", 0, "0", 1, 100, AdminToolCMDCode.TASK_MANAGETQUERY_TASK, LocalTools.getLocalConfig().getSystemNum(), "0", SystemConstant.SUCCESS_CODE,userSequence, reqStatisticsMsg);
	            msg = chatSocketClient.sendMsgsToServer(msg1, ResStatisticsMsg.class);
	            //查询战斗信息 --ResBattleInfoMsg
	            String userSequence1 = MD5.md5Of16(AdminToolCMDCode.QUERY_QUALIFY_SERVER_BATTLE_INFO+gameServer.getServerCommunicationKey());
	            Msg battleMsg1 =  MsgBuilder.buildMsg(0, "0", 0, "0", 1, 100, AdminToolCMDCode.QUERY_QUALIFY_SERVER_BATTLE_INFO, LocalTools.getLocalConfig().getSystemNum(), "0", SystemConstant.SUCCESS_CODE,userSequence1, new CommomMsgBody());
	            battleMsg = chatSocketClient.sendMsgsToServer(battleMsg1, ResBattleInfoMsg.class);
	            //记录统计信息
	            ResBattleInfoMsg battleInfoMsg = (ResBattleInfoMsg)battleMsg.getMsgBody();
	            battleIngNum = battleInfoMsg.getBattleIngNum();
	            totalBattleNum = battleInfoMsg.getTotalBattleNum();
	            if(battleInfoMsg.getMatchNum() != null && !battleInfoMsg.getMatchNum().equals("")){
	            	String [] values = battleInfoMsg.getMatchNum().split("_");
	            	arenaMatchTimes = Integer.parseInt(values[0]);
	            	qualifyMatchTimes = Integer.parseInt(values[1]);
	            }
	            
	            chatSocketClient.close();
	            if(msg == null || msg.getMsgHead().getErrorCode()!=SystemConstant.SUCCESS_CODE){
	            	throw new RuntimeException("发送战斗服务器失败:"+((CommomMsgBody)msg.getMsgBody()).getErrorDescription());
	            }
	        }
			break;
		case 4:
			//聊天服务器
			chatSocketClient = CacheManager.getCacheInstance(ServerClientCache.class).get(gameServer.getChatServerId()+"").getSocketClient();
			if(chatSocketClient!=null){
	    		String userSequence = MD5.md5Of16(AdminToolCMDCode.TASK_MANAGETQUERY_TASK+gameServer.getServerCommunicationKey());
	            Msg msg1 =  MsgBuilder.buildMsg(0, "0", 0, "0", 1, 100, AdminToolCMDCode.TASK_MANAGETQUERY_TASK, LocalTools.getLocalConfig().getSystemNum(), "0", SystemConstant.SUCCESS_CODE,userSequence, reqStatisticsMsg);
	            msg = chatSocketClient.sendMsgsToServer(msg1, ResStatisticsMsg.class);
	            chatSocketClient.close();
	            if(msg == null ||msg.getMsgHead().getErrorCode()!=SystemConstant.SUCCESS_CODE){
	            	throw new RuntimeException("发送战斗服务器失败:"+((CommomMsgBody)msg.getMsgBody()).getErrorDescription());
	            }
	        }
			break;
			default:
				break;
		}
		ResStatisticsMsg resStatisticsMsg = (ResStatisticsMsg)msg.getMsgBody();
		
		Map<Integer, StatBean> statsMap1 = new HashMap<Integer, StatBean>();
		for (Entry<IntM,StatBean> entry : resStatisticsMsg.getTaskStatBeanMap().entrySet()) {
			StatBean statBean = entry.getValue();
			if(Long.valueOf(statBean.getTimes()) > 0){
				statBean.setAverageTime(Math.round(Long.valueOf(statBean.getAllTime()))/statBean.getTimes()+"");
				statBean.setAverageDataTime(Math.round(Long.valueOf(statBean.getWriteallbytes())/statBean.getTimes())+"");
				statsMap1.put(entry.getKey().value(), statBean);
			}
		}
		//排序
		List<Map.Entry<Integer, StatBean>> mappingList = new ArrayList<Map.Entry<Integer, StatBean>>(statsMap1.entrySet()); 
		Collections.sort(mappingList, new Comparator<Map.Entry<Integer, StatBean>>()
		{	
			public int compare(Map.Entry<Integer, StatBean> mapping1,
					Map.Entry<Integer, StatBean> mapping2) {
				StatBean bean1 =  mapping1.getValue();
				StatBean bean2 =  mapping2.getValue();
				if(bean1.getTimes() > bean2.getTimes()){
					return -1;
				}else if(bean1.getTimes() == bean2.getTimes()){
					if(Long.valueOf(bean1.getAverageTime()).longValue() > Long.valueOf(bean2.getAverageTime())){
						return 1;
					}else{
						return -1;
					}
				}else{
					return 1;
				}
			}
		});
		
		for (Entry<Integer, StatBean> entry : mappingList) {
			statsMap.put(entry.getKey(), entry.getValue());
		}
		//设置显示详细信息
		if(resStatisticsMsg.getAppointStatBeanMap().size() > 0){
			for (Entry<IntM,AppointStatBean> entry : resStatisticsMsg.getAppointStatBeanMap().entrySet()) {
				int key = entry.getKey().value();
				appiontStatsMap.put(key, entry.getValue().getTaskStatBeanMap());
				
				if(entry.getValue().getTaskStatBeanMap().size() > 0){
					int count = 0;
					for (StatBean statBean :  entry.getValue().getTaskStatBeanMap().values()) {
						count += statBean.getTimes();
					}
					if(statsMap.containsKey(key)){
						StatBean statBean = statsMap.get(key);
						count = count/statBean.getTimes();
					}
					if(count > 0 && count< 7){
						aveageValue.put(key, count+"");  
					}else if(count >= 7 && count< 10){
						aveageValue.put(key, "<font color = '#F75000'>" + count + "</font>");  
					}else{
						aveageValue.put(key, "<font color = '#FF0000'>" + count + "</font>");  
					}
				}
			}
		}
		//averageDataTime大于1kb的加红标识 averageTime大于500b
		for (StatBean statBean : statsMap.values()) {
			if(Long.valueOf(statBean.getAverageDataTime()).longValue() >= 1000){
				statBean.setAverageDataTime("<font color = '#FF0000'>" + statBean.getAverageDataTime() + "</font>");
			}
			if(Long.valueOf(statBean.getAverageTime()).longValue() >= 500){
				statBean.setAverageTime("<font color = '#FF0000'>" + statBean.getAverageTime() + "</font>");
			}
		}
		
		showType = resStatisticsMsg.getShowType();
		return SUCCESS;
	}

	public Map<Integer, StatBean> getStatsMap() {
		return statsMap;
	}

	public void setStatsMap(Map<Integer, StatBean> statsMap) {
		this.statsMap = statsMap;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	public Integer getRequestType() {
		return requestType;
	}

	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}

	public Integer getBattleIngNum() {
		return battleIngNum;
	}

	public void setBattleIngNum(Integer battleIngNum) {
		this.battleIngNum = battleIngNum;
	}

	public Integer getTotalBattleNum() {
		return totalBattleNum;
	}

	public void setTotalBattleNum(Integer totalBattleNum) {
		this.totalBattleNum = totalBattleNum;
	}

	public Integer getArenaMatchTimes() {
		return arenaMatchTimes;
	}

	public void setArenaMatchTimes(Integer arenaMatchTimes) {
		this.arenaMatchTimes = arenaMatchTimes;
	}

	public Integer getQualifyMatchTimes() {
		return qualifyMatchTimes;
	}

	public void setQualifyMatchTimes(Integer qualifyMatchTimes) {
		this.qualifyMatchTimes = qualifyMatchTimes;
	}

	public Map<Integer, Map<String, StatBean>> getAppiontStatsMap() {
		return appiontStatsMap;
	}

	public void setAppiontStatsMap(
			Map<Integer, Map<String, StatBean>> appiontStatsMap) {
		this.appiontStatsMap = appiontStatsMap;
	}

	public Map<Integer, String> getAveageValue() {
		return aveageValue;
	}

	public void setAveageValue(Map<Integer, String> aveageValue) {
		this.aveageValue = aveageValue;
	}

}
