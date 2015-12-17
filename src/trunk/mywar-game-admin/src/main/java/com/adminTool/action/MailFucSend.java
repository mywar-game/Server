package com.adminTool.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.adminTool.bo.AdminMail;
import com.adminTool.bo.DropTool;
import com.adminTool.bo.Partner;
import com.adminTool.service.MailToolService;
import com.adminTool.service.PartnerService;
import com.adminTool.service.UserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.GameWeb;
import com.system.bo.TGameServer;
import com.system.service.GameWebService;
import com.system.service.TGameServerService;
/**
 * 专门发送一些垃圾运营需求的乱七八糟的邮件
 * @author Administrator
 *
 */
public class MailFucSend extends ALDAdminActionSupport implements
		ModelDriven<AdminMail> {

	private static final long serialVersionUID = -3632307771707801095L;
	private List<TGameServer> tgameServerList;
	private List<Partner> partnerList;

	private String mailAttach;
	private String tempMailAttach;
	private Map<String, String> tools;

	private AdminMail adminMail = new AdminMail();
	private String isCommit = "F";
	private Map<GameWeb, List<TGameServer>> gameWebMap = new HashMap<GameWeb, List<TGameServer>>();
	private Date startDate;
	private Date endDate;
	
	public String execute() {
		MailToolService mailToolService = ServiceCacheFactory.getServiceCache()
				.getService(MailToolService.class);
		TGameServerService tgs = ServiceCacheFactory.getServiceCache()
				.getService(TGameServerService.class);
		PartnerService pts = ServiceCacheFactory.getServiceCache().getService(
				PartnerService.class);
		tgameServerList = tgs.findTGameServerList();
		partnerList = pts.findPartnerList();

		tools = mailToolService.findTools();

		// add

		GameWebService gameWebService = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);

		List<GameWeb> list = gameWebService.findGameWebList();
		for (GameWeb gameWeb : list) {

			List<TGameServer> l = new ArrayList<TGameServer>();
			for (TGameServer gameServer : tgameServerList) {
				if (gameServer.getGameWebServerId() == gameWeb.getServerId()) {
					l.add(gameServer);
				}
			}

			gameWebMap.put(gameWeb, l);
		}

		// end add

		if ("F".equals(isCommit)) {
			return INPUT;
		} else {
			if (startDate==null || endDate==null){
				setErroDescrip("时间不能为空");
				return INPUT;
			} 
			String[] serverIds = adminMail.getServerIds().split(", ");
			if(serverIds.length!=1){
				setErroDescrip("只能发送单个服务器");
				return INPUT;
			}
			if (adminMail.getTarget()== null) {
				setErroDescrip("发送对象不能为空");
				return INPUT;
			}
			String toolIds = "";
			String tempToolIds = "";
			if (mailAttach.length() != 0) {
				toolIds = mailAttach.substring(0, mailAttach.length() - 1);
			}
			if (tempMailAttach.length() != 0) {
				tempToolIds = tempMailAttach.substring(0, tempMailAttach.length() - 1);
			}
			
			if(adminMail.getTarget().intValue()!=2){
				if(mailToolService.getDropToolsList(toolIds).size()!=mailToolService.getDropToolsList(tempToolIds).size()){
					setErroDescrip("数据异常!");
					return INPUT;
				}
			}
			// 检测是否有正式服  正式服需要限制检查，测试服不需要    
			boolean isOffical = false;
			for (TGameServer gameServer : tgameServerList) {
				if (gameServer.getOfficial() == 1) {
					for (String serverId : serverIds) {
						if (serverId.equals("" + gameServer.getServerId())) {
							isOffical = true;
							break;
						}
					}
					if (isOffical)
						break;
				}
			}
			// 检测数量是否超过限制
			if (isOffical) {
				if (!toolIds.equals("")) {
					List<DropTool> dropToolsList = mailToolService
							.getDropToolsList(toolIds);
					String response = mailToolService.check(dropToolsList);

					if (response != null && !"".equals(response)) {
						setErroDescrip(response);
						return INPUT;
					}
				}
			}
			adminMail.setCreatedTime(new Timestamp(new Date().getTime()));
			adminMail.setUpdatedTime(new Timestamp(new Date().getTime()));
			adminMail.setStatus(0);
			adminMail.setDate(new Date());
			adminMail.setPartner("-1");
			// 发送者，谁敢再乱发。
			adminMail.setSenderm(super.getAdminUser().getAdminName());
			UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
			CustomerContextHolder.setSystemNum(Integer.parseInt(adminMail.getServerIds()));
			List<Object> lodoIds = null;
			switch (adminMail.getTarget().intValue()) {
			case 1:
				adminMail.setTarget(2);
				lodoIds = userService.findLevelRankList();
				if(lodoIds==null || lodoIds.size()==0){
					setErroDescrip("不存在符合条件的玩家");
					return INPUT;
				}else{
					Map<String, List<DropTool>> map = mailToolService.getDropToolsMap(tempToolIds);
					for(String key : map.keySet()){
						StringBuffer lodoIdsSb = new StringBuffer();
						StringBuffer dropsSb = new StringBuffer();
						StringBuffer toolListSb = new StringBuffer();
						List<DropTool> drops = map.get(key);
						for(DropTool tool : drops){
							dropsSb.append(tool.getToolType()).append(",").append(tool.getToolId()).append(",").append(tool.getNumber()).append("|");
							toolListSb.append("\n").append(tools.get(tool.getToolType()+","+tool.getToolId())).append(" * ").append(tool.getNumber());
						}
						String[] rankArr = key.split("_");
						int fromRank = Integer.parseInt(rankArr[0]);
						int toRank = Integer.parseInt(rankArr[1]);
						
						for(int i=0;i<lodoIds.size();i++){
							if(i+1>=fromRank && i+1<=toRank){
								lodoIdsSb.append(lodoIds.get(i)+",");
							}
						}
						AdminMail mail = adminMail;
						mail.setToolList(toolListSb.toString());
						mail.setToolIds(dropsSb.substring(0, dropsSb.length()-1));
						if(lodoIdsSb.length()==0){//不存在匹配的用户
							continue;
						}
						mail.setLodoIds(lodoIdsSb.substring(0, lodoIdsSb.length()-1));
						mailToolService.addMail(mail);
					}
				}
				break;
			case 2:
				adminMail.setTarget(2);
				adminMail.setToolIds(toolIds);
				lodoIds = userService.findPayList(DateUtil.dateToString(startDate, DateUtil.FORMAT_ONE), DateUtil.dateToString(endDate, DateUtil.FORMAT_ONE));
				if(lodoIds==null || lodoIds.size()==0){
					setErroDescrip("不存在符合条件的玩家");
					return INPUT;
				}else{
					StringBuffer sb = new StringBuffer();
					for(int i=0;i<lodoIds.size();i++){
						if(i%50==0){
							if(i==0){
								sb.append(lodoIds.get(i)+",");
							}else{
								AdminMail mail = adminMail;
								mail.setLodoIds(sb.substring(0, sb.length()-1));
								mailToolService.addMail(mail);
								sb = new StringBuffer(lodoIds.get(i)+",");
							}
						}else{
							sb.append(lodoIds.get(i)+",");
						}
					}
					if(sb.length()!=0){
						adminMail.setLodoIds(sb.substring(0, sb.length()-1));
						mailToolService.addMail(adminMail);
					}
					
				}
				break;
			case 3:
				adminMail.setTarget(2);
				lodoIds = userService.findPkRankList();
				if(lodoIds==null || lodoIds.size()==0){
					setErroDescrip("不存在符合条件的玩家");
					return INPUT;
				}else{
					Map<String, List<DropTool>> map = mailToolService.getDropToolsMap(tempToolIds);
					for(String key : map.keySet()){
						StringBuffer lodoIdsSb = new StringBuffer();
						StringBuffer dropsSb = new StringBuffer();
						StringBuffer toolListSb = new StringBuffer();
						List<DropTool> drops = map.get(key);
						for(DropTool tool : drops){
							dropsSb.append(tool.getToolType()).append(",").append(tool.getToolId()).append(",").append(tool.getNumber()).append("|");
							toolListSb.append("\n").append(tools.get(tool.getToolType()+","+tool.getToolId())).append(" * ").append(tool.getNumber());
						}
						String[] rankArr = key.split("_");
						int fromRank = Integer.parseInt(rankArr[0]);
						int toRank = Integer.parseInt(rankArr[1]);
						
						for(int i=0;i<lodoIds.size();i++){
							if(i+1>=fromRank && i+1<=toRank){
								lodoIdsSb.append(lodoIds.get(i)+",");
							}
						}
						AdminMail mail = adminMail;
						mail.setToolList(toolListSb.toString());
						mail.setToolIds(dropsSb.substring(0, dropsSb.length()-1));
						if(lodoIdsSb.length()==0){//不存在匹配的用户
							continue;
						}
						mail.setLodoIds(lodoIdsSb.substring(0, lodoIdsSb.length()-1));
						mailToolService.addMail(mail);
					}
				}
				break;
			default:
				break;
			}
			return SUCCESS;
		}
	}
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<51;i++){
			if(i%50==0){
				if(i==0){
					sb.append("1"+",");
				}else{
					sb = new StringBuffer();
				}
			}else{
				sb.append("1"+",");
			}
		}
		System.out.print(sb.toString());
	}

	public List<TGameServer> getTgameServerList() {
		return tgameServerList;
	}

	public void setTgameServerList(List<TGameServer> tgameServerList) {
		this.tgameServerList = tgameServerList;
	}

	@Override
	public AdminMail getModel() {
		return adminMail;
	}

	public Map<String, String> getTools() {
		return tools;
	}

	public void setTools(Map<String, String> tools) {
		this.tools = tools;
	}

	public String getMailAttach() {
		return mailAttach;
	}

	public void setMailAttach(String mailAttach) {
		this.mailAttach = mailAttach;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

	public AdminMail getAdminMail() {
		return adminMail;
	}

	public void setAdminMail(AdminMail adminMail) {
		this.adminMail = adminMail;
	}

	public Map<GameWeb, List<TGameServer>> getGameWebMap() {
		return gameWebMap;
	}

	public void setGameWebMap(Map<GameWeb, List<TGameServer>> gameWebMap) {
		this.gameWebMap = gameWebMap;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getTempMailAttach() {
		return tempMailAttach;
	}
	public void setTempMailAttach(String tempMailAttach) {
		this.tempMailAttach = tempMailAttach;
	}

}
