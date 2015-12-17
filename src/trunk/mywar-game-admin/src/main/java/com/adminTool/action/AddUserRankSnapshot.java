package com.adminTool.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.UserRankSnapshot;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ReqGetUserRankSnapshotTask;
import com.adminTool.msgbody.ResGetUserRankSnapshotTask;
import com.adminTool.service.UserRankSnapshotService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.server.msg.model.UnSynList;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserPayHistoryLogService;

public class AddUserRankSnapshot extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = 1L;
	
	private int createSnapshotType;
	
	public String execute(){
		UserRankSnapshotService userRankSnapshotService = ServiceCacheFactory.getServiceCache().getService(UserRankSnapshotService.class);
		List<UserRankSnapshot> userRankSnapshotList = new ArrayList<UserRankSnapshot>();
		if (createSnapshotType == 6) {
			UserPayHistoryLogService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayHistoryLogService.class);
			userRankSnapshotList = userPayHistoryLogService.findPayAmountTop20(this.getAdminUser().getAdminName());
		} else {
			ReqGetUserRankSnapshotTask req = new ReqGetUserRankSnapshotTask(createSnapshotType);
			Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GET_USER_RANK_SNAPSHOT, req, ResGetUserRankSnapshotTask.class); 
			if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
				CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
				super.setErroCodeNum(commomMsgBody.getErrorCode());
				super.setErroDescrip(commomMsgBody.getErrorDescription()+" 创建快照失败！");
				return SUCCESS;
			}
			ResGetUserRankSnapshotTask res =  (ResGetUserRankSnapshotTask)msg.getMsgBody();
			
			UnSynList<com.adminTool.bean.UserRankSnapshot> list = res.getUserRankSnapshotList();
			if (list != null && list.size() > 0) {
				Timestamp createTime = new Timestamp(System.currentTimeMillis());
				for (com.adminTool.bean.UserRankSnapshot userRankSnapshot : list) {
					UserRankSnapshot u = new UserRankSnapshot();
					u.setUserId(Long.valueOf(userRankSnapshot.getUserId()));
					u.setName(userRankSnapshot.getName());
					u.setRank(userRankSnapshot.getRank());
					u.setNote(userRankSnapshot.getNote());
					u.setSysNum(Integer.valueOf(this.getAdminUser().getExp1()));
					u.setCreateName(this.getAdminUser().getAdminName());
					u.setType(createSnapshotType);
					u.setCreateTime(createTime);
					userRankSnapshotList.add(u);
				}
			}
		}
		userRankSnapshotService.saveUserRankSnapshotList(userRankSnapshotList);
		return SUCCESS;
	}

	public void setCreateSnapshotType(int createSnapshotType) {
		this.createSnapshotType = createSnapshotType;
	}

	public int getCreateSnapshotType() {
		return createSnapshotType;
	}

}
