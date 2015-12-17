package com.adminTool.action;

import com.adminTool.bo.User;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ReqModifyUserArenaScoreAndRank;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 修改玩家竞技场积分和排行
 * @author hzy
 * 2013-3-12
 */
public class ModifyUserArenaScoreAndRank extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = 8698317453262632662L;
	
	/** 玩家角色名 */
	private String name;
	
	/**  竞技场积分 **/
	private Integer arenaScore;
	
	/**  排位赛等级 **/
	private Integer arenaRank;
	
	private String isCommit = "F";

	public String execute(){
		//没提交，返回修改页面
		if ("F".equals(isCommit)) {
			return SUCCESS;
		}
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		User user = userService.findUserByName(name.trim());
		if (user == null) {
			super.setErroDescrip("角色名为(" + name + ")的玩家不存在，修改失败！");
			return SUCCESS;
		}
		if (arenaScore == null || arenaRank == null) {
			super.setErroDescrip("请填写！");
			return SUCCESS;
		}
		ReqModifyUserArenaScoreAndRank req = new ReqModifyUserArenaScoreAndRank();
		req.setUserId(user.getUserId()+"");
		req.setArenaScore(arenaScore);
		req.setArenaRank(arenaRank);
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.MODIFY_USER_ARENA_SCORE_AND_RANK, req, CommomMsgBody.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 修改失败！");
			return SUCCESS;
		}
		super.setErroDescrip("修改成功！");
		return SUCCESS;
	}
	
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setArenaScore(Integer arenaScore) {
		this.arenaScore = arenaScore;
	}

	public Integer getArenaScore() {
		return arenaScore;
	}

	public void setArenaRank(Integer arenaRank) {
		this.arenaRank = arenaRank;
	}

	public Integer getArenaRank() {
		return arenaRank;
	}

}
