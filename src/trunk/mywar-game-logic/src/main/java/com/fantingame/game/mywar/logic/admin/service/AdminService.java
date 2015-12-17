package com.fantingame.game.mywar.logic.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventManager;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.notify.user.UserBO;
import com.fantingame.game.msgbody.server.admin.AdminAction_addEquipmentOrToolsRes;
import com.fantingame.game.msgbody.server.admin.AdminAction_addHerosRes;
import com.fantingame.game.msgbody.server.admin.AdminAction_addUserMoneyRes;
import com.fantingame.game.msgbody.server.admin.AdminAction_getUserInfoRes;
import com.fantingame.game.msgbody.server.admin.AdminAction_sendMailReq;
import com.fantingame.game.msgbody.server.admin.AdminSendAttachBO;
import com.fantingame.game.msgbody.server.admin.ResUserMailBO;
import com.fantingame.game.mywar.logic.admin.bo.GrantToolBO;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.equip.service.EquipService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.mail.service.MailService;
import com.fantingame.game.mywar.logic.message.service.MessageService;
import com.fantingame.game.mywar.logic.tool.model.SystemTool;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.model.UserExtInfo;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.collect.Lists;

public class AdminService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ToolService toolService;
	@Autowired
	private HeroService heroService;
	@Autowired
	private MailService mailService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private EquipService equipService;
	@Autowired
	private GoodsDealService goodDealService;
	
    /**
     * 重载静态数据
     * 
     * @param className
     * @return
     */
	public boolean reloadStaticData(String className){
		ModuleEventBase baseModuleEvent = new ModuleEventBase();
		baseModuleEvent.addStringValue("className", className);
		ModuleEventManager.getInstance().dispatchEvent(ModuleEventConstant.RELOAD_STATIC_DATA, baseModuleEvent);
		return true;
	}
	
	/**
	 * 发送道具、装备
	 * 
	 * @param list
	 * @param userIdStr
	 * @param isSendAll
	 * @return
	 */
	public AdminAction_addEquipmentOrToolsRes addEquipmentOrTools(List<AdminSendAttachBO> attachList, String userIdStr, boolean isSendAll) {
		AdminAction_addEquipmentOrToolsRes res = new AdminAction_addEquipmentOrToolsRes();
		Map<String, List<AdminSendAttachBO>> failMap = new HashMap<String, List<AdminSendAttachBO>>();
		
		List<String> userIds = null;
		if (isSendAll) {// 全服发放
			userIds = userService.getAllUserIds();
			sendToolsOrEquipMentToUsers(userIds, attachList, failMap);
		} else {
			String[] userIdsAdd = userIdStr.split(",");
			
			GrantToolBO grantToolBo = getUsersByUserIds(userIdsAdd);
			List<User> users = grantToolBo.getList();
			// 记录查询用户失败记录
			String failUserId = grantToolBo.getFailPlayId();
			if (failUserId != null && !failUserId.equals("")) {
				String[] failArray = failUserId.split(",");
				for (int m = 0; m < failArray.length; m++) {
					if (failArray[m] != null && !failArray[m].equals("")) {
						failMap.put(failArray[m], attachList);
					}
				}
			}
			
			userIds = new ArrayList<String>();
			for (User user : users) {
				userIds.add(user.getUserId());
			}			
			sendToolsOrEquipMentToUsers(userIds, attachList, failMap);
		}
		
		res.setResult(failMap);
		return res;
	}
	
	/**
	 * 发送英雄
	 * 
	 * @param attachList
	 * @param userIdStr
	 * @param isSendAll
	 * @return
	 */
	public AdminAction_addHerosRes addHeros(List<AdminSendAttachBO> attachList, String userIdStr, boolean isSendAll) {
		AdminAction_addHerosRes res = new AdminAction_addHerosRes();
		Map<String, List<AdminSendAttachBO>> failMap = new HashMap<String, List<AdminSendAttachBO>>();
		
		List<String> userIds = null;
		if (isSendAll) {// 全服发放
			userIds = userService.getAllUserIds();			
			sendHeros(userIds, attachList, failMap);
		} else {
			String[] userIdsAdd = userIdStr.split(",");
			
			GrantToolBO grantToolBo = getUsersByUserIds(userIdsAdd);
			List<User> users = grantToolBo.getList();
			// 记录查询用户失败记录
			String failUserId = grantToolBo.getFailPlayId();
			if (failUserId != null && !failUserId.equals("")) {
				String[] failArray = failUserId.split(",");
				for (int m = 0; m < failArray.length; m++) {
					if (failArray[m] != null && !failArray[m].equals("")) {
						failMap.put(failArray[m], attachList);
					}
				}
			}
			
			userIds = new ArrayList<String>();
			for (User user : users) {
				userIds.add(user.getUserId());
			}			
			sendHeros(userIds, attachList, failMap);
		}
		
		res.setResult(failMap);		
		return res;
	}
	
	/**
	 * 添加用户英雄
	 * 
	 * @param userIdList
	 * @param attachList
	 * @param failMap
	 */
	public void sendHeros(List<String> userIdList, List<AdminSendAttachBO> attachList, Map<String, List<AdminSendAttachBO>> failMap) {
		List<AdminSendAttachBO> failTempList = null;
		boolean success = true;
		
		for (String userId : userIdList) {			
			for (AdminSendAttachBO sendAttachBO : attachList) {
				try {
					for (int i = 0; i < sendAttachBO.getAttachNum(); i++) {
						heroService.addHero(userId, sendAttachBO.getAttachId(), false, "", GoodsUseType.ADD_BY_ADMIN, null);
					}											
				} catch (Exception e) {
					success = false;
				}
				
				// 记录失败
				if (!success) {
					if (failMap.containsKey(userId)) {
						failMap.get(userId).add(sendAttachBO);
					} else {
						failTempList = new ArrayList<AdminSendAttachBO>();
						failTempList.add(sendAttachBO);
						failMap.put(userId, failTempList);
					}
				}
			}
		}
	}
	
	/**
	 * 根据userId列表获取用户对象
	 * 
	 * @param playerIds
	 * @return
	 */
	private GrantToolBO getUsersByUserIds(String[] userIds) {
		GrantToolBO grantToolBo = new GrantToolBO();
		List<User> users = new ArrayList<User>();
		StringBuffer failUserIdStr = new StringBuffer();
		for (int i = 0; i < userIds.length; i++) {
			try {
				User user = userService.getByUserId(userIds[i]);
				users.add(user);
			} catch (Exception e) {
				failUserIdStr.append(userIds[i]).append(",");
			}
		}
		grantToolBo.setFailPlayId(failUserIdStr.toString());
		grantToolBo.setList(users);
		return grantToolBo;
	}
	
	/**
	 * 发放具体动作
	 * 
	 * @param users
	 *            用户列表
	 * @param attachList
	 *            发放的道具或装备列表
	 * @param failMap
	 *            失败map
	 */
	private void sendToolsOrEquipMentToUsers(List<String> usersIds, List<AdminSendAttachBO> attachList, Map<String, List<AdminSendAttachBO>> failMap) {
		List<AdminSendAttachBO> failTempList = null;
		boolean success = false;
		
		List<GoodsBeanBO> dropToolBOListCheck = Lists.newArrayList();
		for (AdminSendAttachBO sendAttachBO : attachList) {
			GoodsBeanBO goodsBeanBO = new GoodsBeanBO();
			
			// 道具
			if (sendAttachBO.getAttachType() == 1) {
				goodsBeanBO.setGoodsType(GoodsType.tool.intValue);
				goodsBeanBO.setGoodsId(sendAttachBO.getAttachId());
				goodsBeanBO.setGoodsNum(sendAttachBO.getAttachNum());
			} else if (sendAttachBO.getAttachType() == 2) { // 装备
				goodsBeanBO.setGoodsType(GoodsType.equip.intValue);
				goodsBeanBO.setGoodsId(sendAttachBO.getAttachId());
				goodsBeanBO.setGoodsNum(sendAttachBO.getAttachNum());
			}			
		}
		
		for (String userId : usersIds) {
			boolean canSendTool = true;
			if (!userService.checkBagLimit(userId, dropToolBOListCheck)) {
				canSendTool = false;// 不能发道具 背包已经满了
			}
			
			for (AdminSendAttachBO sendAttachBO : attachList) {
				if (sendAttachBO.getAttachType() == 1) {// 发放道具
					try {
						if (canSendTool) {
							SystemTool tool = toolService.getSystemTool(sendAttachBO.getAttachId());
							goodDealService.sendGoods(userId, GoodsType.tool.intValue + "," + tool.getToolId() + "," + sendAttachBO.getAttachNum(), GoodsUseType.ADD_BY_ADMIN);
							success = true;
							// success = toolService.addTool(userId, tool.getToolId(), sendAttachBO.getAttachNum(), GoodsUseType.ADD_BY_ADMIN);
						} else {
							success = false;
						}
					} catch (Exception e) {
						success = false;
					}
				} else if (sendAttachBO.getAttachType() == 2) {// 发放装备
					for (int i = 0; i < sendAttachBO.getAttachNum(); i++) {
						try {
							success = equipService.addEquip(userId, sendAttachBO.getAttachId(), false, GoodsUseType.ADD_BY_ADMIN, null);
						} catch (Exception e) {
							success = false;
							// 记录未发放成功的数量
							sendAttachBO.setAttachNum(sendAttachBO.getAttachNum() - i);
							break;
						}
					}
				} else if (sendAttachBO.getAttachType() == 3) {
					// 发放神器
					for (int i = 0; i < sendAttachBO.getAttachNum(); i++) {
						//String userArtifactId = IDGenerator.getID();
						try {
//							success = equipService.addUserEquip(u, userEquipId, sendAttachBO.getAttachId(), ToolUseType.ADD_ADMIN_ADD);
							// heroService.addArtifacts(u, sendAttachBO.getAttachId(), 1, ToolUseType.ADD_ADMIN_ADD);
							success = true;
						} catch (Exception e) {
							success = false;
							// 记录未发放成功的数量
							sendAttachBO.setAttachNum(sendAttachBO.getAttachNum() - i);
							break;
						}
					}
				} else {
					// 其他类型直接返回失败
					success = false;
				}
				
				// 记录失败
				if (!success) {
					if (failMap.containsKey(userId)) {
						failMap.get(userId).add(sendAttachBO);
					} else {
						failTempList = new ArrayList<AdminSendAttachBO>();
						failTempList.add(sendAttachBO);
						failMap.put(userId, failTempList);
					}
				}
			}
		}
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public AdminAction_getUserInfoRes getUserInfo(String userId) {
		AdminAction_getUserInfoRes res = new AdminAction_getUserInfoRes();
		User user = userService.getByUserId(userId);
		UserExtInfo userExtInfo = userService.getUserExtInfo(userId);
		UserBO userBO = null;
		if (user != null && userExtInfo != null) {
			userBO = userService.creatUserBO(user, userExtInfo);
		}
			
		res.setUser(userBO);
		return res;
	}
	
	/**
	 * 修改用户等级
	 * 
	 * @param userId
	 * @param targetLevel
	 */
	public void updateUserLevel(String userId, int targetLevel) {
		boolean success = userService.adminToolUpdateUserLevel(userId, targetLevel);
		
		if (!success)
			throw new ServiceException(SystemConstant.FAIL_CODE, "修改用户等级失败");
	}
	
	/**
	 * 给用户发钻石
	 * 
	 * @param isSendAll
	 * @param userIds
	 * @param diamond
	 * @return
	 */
	public AdminAction_addUserMoneyRes addUserMoney(boolean isAllUser, String userIds, int diamond) {
		String[] userIdArr = null;
		
		// 全服发放
		if (isAllUser) {
			userIds = "";
			List<String> userIdList = userService.getAllUserIds();
			for (String userId : userIdList) {
				if (userIds.length() == 0) {
					userIds = userId;
				} else {
					userIds = userIds + "," + userId;
				}
			}			
		}
		userIdArr = userIds.split(",");
		
		GrantToolBO grantToolBo = getUsersByUserIds(userIdArr);
		List<User> users = grantToolBo.getList();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(grantToolBo.getFailPlayId());
		
		for (User u : users) {
			boolean success = userService.addMoney(u.getUserId(), diamond, GoodsUseType.ADD_BY_ADMIN);
			if (!success) 
				stringBuilder.append(u.getUserId()).append(",");			
		}		
		
		AdminAction_addUserMoneyRes res = new AdminAction_addUserMoneyRes();
		res.setResult(res.getResult());
		return res;
	}
	
	/**
	 * 发送邮件
	 * 
	 * @param req
	 */
	public void sendMail(AdminAction_sendMailReq req) {
		String title = req.getTitle();
		String content = req.getContent();
		String toolIds = req.getToolIds();
		String lodoIds = req.getLodoIds();
		String target = req.getTarget();
		String sourceId = req.getSourceId();
		String partner = req.getPartner();
		Date date = new Date(req.getDate());
		
		mailService.send(title, content, toolIds, Integer.parseInt(target), lodoIds, Integer.parseInt(sourceId), date, partner);
	}
	
	/**
	 * 发送跑马灯
	 * 
	 * @param content
	 * @param partnerIds
	 */
	public void sendSystemMsg(String content, String partnerIds) {
		messageService.sendSystemMsg(content, partnerIds);
	}
	
	/**
	 * 跳过新手引导
	 * 
	 * @param userId
	 * @param guideStep
	 * @param ip
	 */
	public void recordGuideStep(String userId, int guideStep, String ip) {
		userService.recordGuideStep(userId, guideStep);
	}
	
	/**
	 * 搜索邮件
	 * 
	 * @param userId
	 * @return
	 */
	public List<ResUserMailBO> searchMail(String userId) {
		return mailService.searchMail(userId);
	}
}
