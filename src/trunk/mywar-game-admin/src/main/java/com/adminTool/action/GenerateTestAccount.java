package com.adminTool.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.TestUser;
import com.adminTool.bo.UserType;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ReqGenerateTestAccount;
import com.adminTool.msgbody.ResGenerateTestAccount;
import com.adminTool.msgbody.UserAccountInfo;
import com.adminTool.service.TestUserService;
import com.adminTool.service.UserTypeService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.SystemCode;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;

public class GenerateTestAccount extends ALDAdminActionSupport {
	
	/** * */
	private static final long serialVersionUID = 1L;

	private int generateNum;
	
	private int areaId;
	
	private String isCommit = "F";
	
	private List<UserAccountInfo> userAccountList = new ArrayList<UserAccountInfo>();
	
	public String execute(){
		TestUserService testUserService = ServiceCacheFactory.getServiceCache().getService(TestUserService.class);
		UserTypeService userTypeService = ServiceCacheFactory.getServiceCache().getService(UserTypeService.class);
		if ("T".equals(this.getIsCommit())) {
			if (generateNum <= 0) {
				super.setErroCodeNum(SystemCode.SYS_FAIL); 
				super.setErroDescrip("生成个数必须大于0"); 
				return SUCCESS; 
			}
			if (areaId <= 0 || areaId>10) {
				super.setErroCodeNum(SystemCode.SYS_FAIL); 
				super.setErroDescrip("区域编号必须在0~10之间"); 
				return SUCCESS; 
			}
			ReqGenerateTestAccount reqGenerateTestAccount = new ReqGenerateTestAccount();
			reqGenerateTestAccount.setGenerateNum(generateNum);
			reqGenerateTestAccount.setAreaId(areaId);
			
			Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GENERATE_TEST_ACCOUNT, reqGenerateTestAccount, ResGenerateTestAccount.class);
			if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
				CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
				super.setErroCodeNum(commomMsgBody.getErrorCode());
				super.setErroDescrip(commomMsgBody.getErrorDescription()+" 生成高级测试账号失败！");
				return SUCCESS;
			}
			ResGenerateTestAccount resGenerateTestAccount = (ResGenerateTestAccount)msg.getMsgBody();
			
			for(UserAccountInfo info : resGenerateTestAccount.getAccountList()){//新生成的数据存后台管理数据库
				TestUser testuser = new TestUser();
				testuser.setSysNum(CustomerContextHolder.getSystemNum());
				testuser.setCreateTime(new Timestamp(Long.valueOf(info.getCreateTime())));
				testuser.setPassword(info.getPassword());
				testuser.setUserId(info.getUserId());
				testuser.setUserName(info.getUserName());
				testUserService.createTestUser(testuser);
				
				UserType userType = new UserType();
				userType.setSysNum(CustomerContextHolder.getSystemNum());
				userType.setUserId(info.getUserId());
				userType.setType(2);
				userTypeService.saveOrUpdateUserType(userType);
			}
		}
		List<TestUser> list = testUserService.getTestUserList();
		if(list!=null && list.size()>0){//重新查询后台管理的数据
			for(TestUser user : list){
				UserAccountInfo info = new UserAccountInfo();
				info.setCreateTime((user.getCreateTime()+"").substring(0, (user.getCreateTime()+"").length()-2));
				info.setPassword(user.getPassword());
				info.setUserId(user.getUserId());
				info.setUserName(user.getUserName());
				userAccountList.add(info);
			}
		}
		return SUCCESS;
	}

	public List<UserAccountInfo> getUserAccountList() {
		return userAccountList;
	}

	public void setUserAccountList(List<UserAccountInfo> userAccountList) {
		this.userAccountList = userAccountList;
	}

	public String getIsCommit() {
		return isCommit; 
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit; 
	}

	public int getGenerateNum() {
		return generateNum; 
	}

	public void setGenerateNum(int generateNum) {
		this.generateNum = generateNum; 
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	
}
