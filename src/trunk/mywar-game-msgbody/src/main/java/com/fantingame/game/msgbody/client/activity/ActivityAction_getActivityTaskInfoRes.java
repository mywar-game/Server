package com.fantingame.game.msgbody.client.activity;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.activity.UserActivityTaskBO;
import java.util.List;
import java.util.ArrayList;
import com.fantingame.game.msgbody.client.activity.UserActivityTaskRewardBO;

/**查看用户活跃度信息--小助手**/
public class ActivityAction_getActivityTaskInfoRes implements ICodeAble {

		/**用户活跃度信息列表**/
	private List<UserActivityTaskBO> userActivityTaskList=null;
	/**领取的奖励日志列表**/
	private List<UserActivityTaskRewardBO> rewardLogList=null;
	/**用户活跃度**/
	private Integer point=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userActivityTaskList==null||userActivityTaskList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userActivityTaskList.size());
		}
		if(userActivityTaskList!=null&&userActivityTaskList.size()>0){
			for(int userActivityTaskListi=0;userActivityTaskListi<userActivityTaskList.size();userActivityTaskListi++){
				userActivityTaskList.get(userActivityTaskListi).encode(outputStream);
			}
		}		
        if(rewardLogList==null||rewardLogList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(rewardLogList.size());
		}
		if(rewardLogList!=null&&rewardLogList.size()>0){
			for(int rewardLogListi=0;rewardLogListi<rewardLogList.size();rewardLogListi++){
				rewardLogList.get(rewardLogListi).encode(outputStream);
			}
		}		outputStream.writeInt(point);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userActivityTaskListSize = inputStream.readInt();
		if(userActivityTaskListSize>0){
			userActivityTaskList = new ArrayList<UserActivityTaskBO>();
			for(int userActivityTaskListi=0;userActivityTaskListi<userActivityTaskListSize;userActivityTaskListi++){
				 UserActivityTaskBO entry = new UserActivityTaskBO();entry.decode(inputStream);userActivityTaskList.add(entry);
			}
		}		
        int rewardLogListSize = inputStream.readInt();
		if(rewardLogListSize>0){
			rewardLogList = new ArrayList<UserActivityTaskRewardBO>();
			for(int rewardLogListi=0;rewardLogListi<rewardLogListSize;rewardLogListi++){
				 UserActivityTaskRewardBO entry = new UserActivityTaskRewardBO();entry.decode(inputStream);rewardLogList.add(entry);
			}
		}		point = inputStream.readInt();


	}
	
		/**用户活跃度信息列表**/
    public List<UserActivityTaskBO> getUserActivityTaskList() {
		return userActivityTaskList;
	}
	/**用户活跃度信息列表**/
    public void setUserActivityTaskList(List<UserActivityTaskBO> userActivityTaskList) {
		this.userActivityTaskList = userActivityTaskList;
	}
	/**领取的奖励日志列表**/
    public List<UserActivityTaskRewardBO> getRewardLogList() {
		return rewardLogList;
	}
	/**领取的奖励日志列表**/
    public void setRewardLogList(List<UserActivityTaskRewardBO> rewardLogList) {
		this.rewardLogList = rewardLogList;
	}
	/**用户活跃度**/
    public Integer getPoint() {
		return point;
	}
	/**用户活跃度**/
    public void setPoint(Integer point) {
		this.point = point;
	}

	
	
}
