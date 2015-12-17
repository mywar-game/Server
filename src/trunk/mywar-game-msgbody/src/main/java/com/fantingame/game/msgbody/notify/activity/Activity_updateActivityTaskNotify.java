package com.fantingame.game.msgbody.notify.activity;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.activity.UserActivityTaskRewardBO;
import java.util.List;
import java.util.ArrayList;

/**活跃度信息变更推送接口**/
public class Activity_updateActivityTaskNotify implements ICodeAble {

		/**需要更新的用户活跃度信息**/
	private List<UserActivityTaskRewardBO> updateUserActivityTaskList=null;
	/**用户活跃度**/
	private Integer point=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(updateUserActivityTaskList==null||updateUserActivityTaskList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(updateUserActivityTaskList.size());
		}
		if(updateUserActivityTaskList!=null&&updateUserActivityTaskList.size()>0){
			for(int updateUserActivityTaskListi=0;updateUserActivityTaskListi<updateUserActivityTaskList.size();updateUserActivityTaskListi++){
				updateUserActivityTaskList.get(updateUserActivityTaskListi).encode(outputStream);
			}
		}		outputStream.writeInt(point);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int updateUserActivityTaskListSize = inputStream.readInt();
		if(updateUserActivityTaskListSize>0){
			updateUserActivityTaskList = new ArrayList<UserActivityTaskRewardBO>();
			for(int updateUserActivityTaskListi=0;updateUserActivityTaskListi<updateUserActivityTaskListSize;updateUserActivityTaskListi++){
				 UserActivityTaskRewardBO entry = new UserActivityTaskRewardBO();entry.decode(inputStream);updateUserActivityTaskList.add(entry);
			}
		}		point = inputStream.readInt();


	}
	
		/**需要更新的用户活跃度信息**/
    public List<UserActivityTaskRewardBO> getUpdateUserActivityTaskList() {
		return updateUserActivityTaskList;
	}
	/**需要更新的用户活跃度信息**/
    public void setUpdateUserActivityTaskList(List<UserActivityTaskRewardBO> updateUserActivityTaskList) {
		this.updateUserActivityTaskList = updateUserActivityTaskList;
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
