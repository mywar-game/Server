package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.adminTool.bo.AdminMail;
import com.adminTool.bo.SearchMailClass;
import com.adminTool.msgbody.ResUserMail;
import com.adminTool.service.SearchMailService;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 邮件查询
 * @author Administrator
 *
 */
public class SearchMail extends ALDAdminActionSupport {

	private static final long serialVersionUID = -5685859472467806197L;
	
	private static final String REQ_URL = "searchMail.do";

	private String isCommit = "F";
	
	/**玩家标识**/
	private Integer lodoId;
	
	/**玩家名**/
	private String userName;

	/**邮件标识**/
	private Integer systemMailId;
	
	/**服务器**/
	private String servergroup;
	
	List<SearchMailClass> searchMailClassList = new ArrayList<SearchMailClass>();


	@SuppressWarnings({ "deprecation", "unused", "unchecked" })
	@Override
	public String execute() {
		
		if (isCommit == "T" || isCommit.equalsIgnoreCase("T")) {
			
			boolean retFlag = false;
			
			// 没有查询条件
			if ((lodoId == 0) && (lodoId == null) && (systemMailId == null)) {
				return SUCCESS; 
			}
			
			// 先找出条件的数据,待用
			SearchMailService searchMailService = ServiceCacheFactory.getServiceCache().getService(SearchMailService.class);
			List<AdminMail> mailList = searchMailService.find(Integer.toString(lodoId), systemMailId, servergroup);
			// 查询群邮件
			if (systemMailId == null || systemMailId.equals(null)) {
				System.out.println("查询群邮件");
				int sysNum = CustomerContextHolder.getSystemNum();
				List<AdminMail> qunMailList = searchMailService.findQunMail(Integer.toString(sysNum));
				mailList.addAll(qunMailList);
			} else {
				// 查询的是群邮件id
				System.out.println("查询群邮件");
				List<AdminMail> qunMailList = searchMailService.findQunMailById(systemMailId);
				mailList.addAll(qunMailList);
			}
			
			// 如果不传送lodoId
			String lodoIds = "";
			if ((lodoId == 0) && (lodoId == null)) {
				// 找不到邮件
				if (mailList == null || mailList.size() == 0) {
					return SUCCESS;
				} else {
					for (AdminMail mail : mailList) {
						// 可能有几个lodoIds;
						lodoIds = mail.getLodoIds();
						break;
					}
				}
			}
			UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class); 		
			List<com.adminTool.bo.User> list = userService.findUserByLodoIdAndUserName(lodoId, userName);
			
			if(list==null || list.size()==0){
				super.setErroDescrip("玩家不存在！");
				return SUCCESS;
			}
			List<ResUserMail> resUserMailList = new ArrayList<ResUserMail>();
			
			for(com.adminTool.bo.User user : list){
				
				StringBuilder sb = new StringBuilder();
				StringBuilder sb1 = new StringBuilder();
				sb.append("&userId=").append(user.getUserId());
				//sb.append("&systemMailId=").append(systemMailId);
				
				// 签名验证
				sb1.append(user.getUserId());
				//sb1.append(systemMailId);
				
				String response = HttpClientBridge.sendToGameServer(REQ_URL, sb.toString(), sb1.toString());
				if (response == null) {
					super.setErroCodeNum(SystemConstant.FAIL_CODE);
					super.setErroDescrip("查询出现异常,请查看日志！");
				} else if (response != null && response.equals("")) {
					// 没有数据
					retFlag = true;
				} else {
					JSONObject jsonObject = JSONObject.fromObject(response);
					if(jsonObject.containsKey(HttpClientBridge.RETURN_RC) && jsonObject.getInt(HttpClientBridge.RETURN_RC) != HttpClientBridge.RETURN_SUCCESS_CODE){
						super.setErroCodeNum(Integer.parseInt(jsonObject.get(HttpClientBridge.FAIL).toString()));
						super.setErroDescrip("查询失败！" + jsonObject);
					}else{
						Object object = jsonObject.get(HttpClientBridge.SUCCESS);
						if (object != null) {
							resUserMailList = JSONArray.toList((JSONArray) object, ResUserMail.class);
						}
						// 成功返回数据
						retFlag = true;
					}
				}
			}
			
			if (retFlag) {
				//封装
				int index = 0;
				for (AdminMail adminMail : mailList) {
					
					SearchMailClass searchMail = new SearchMailClass();
					searchMail.setID(index++);
					searchMail.setTool(adminMail.getToolIds());
					searchMail.setSystemMailId(adminMail.getAdminMailId());
					searchMail.setTitle(adminMail.getTitle());
					searchMail.setCreateTime(adminMail.getCreatedTime());
					searchMail.setReceiveStatus(0);
					searchMail.setStatus(0);
					
					for (ResUserMail u : resUserMailList) {
						// 映射回来
						if (u.getSourceId().equals(adminMail.getAdminMailId())) {
							searchMail.setReceiveStatus(u.getReceiveStatus());
							searchMail.setStatus(u.getStatus());
						}
					}
					if (searchMail.getReceiveStatus() == 0) {
						searchMail.setReceiveStatusName("未领取");
					} else if (searchMail.getReceiveStatus() == 1) {
						searchMail.setReceiveStatusName("已领取");
					}
					
					if (searchMail.getStatus() == 0) {
						searchMail.setStatusName("新邮件");
					} else if (searchMail.getStatus() == 1) {
						searchMail.setStatusName("已读");
					} else if (searchMail.getStatus() == 2) {
						searchMail.setStatusName("已删除");
					} 
					searchMailClassList.add(searchMail);
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

	public Integer getSystemMailId() {
		return systemMailId;
	}

	public void setSystemMailId(Integer systemMailId) {
		this.systemMailId = systemMailId;
	}

	public int getLodoId() {
		return lodoId;
	}

	public void setLodoId(int lodoId) {
		this.lodoId = lodoId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServergroup() {
		return servergroup;
	}

	public void setServergroup(String servergroup) {
		this.servergroup = servergroup;
	}
	
	public List<SearchMailClass> getSearchMailClassList() {
		return searchMailClassList;
	}

	public void setSearchMailClassList(List<SearchMailClass> searchMailClassList) {
		this.searchMailClassList = searchMailClassList;
	}
}

