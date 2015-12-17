package com.fantingame.game.msgbody.client.task;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.task.UserDailyTaskInfoBO;

/**刷新五星任务**/
public class TaskAction_refreshFiveStarRes implements ICodeAble {

		/**五星的日常任务**/
	private UserDailyTaskInfoBO userDailyTaskInfo=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userDailyTaskInfo.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userDailyTaskInfo=new UserDailyTaskInfoBO();    
		userDailyTaskInfo.decode(inputStream);


	}
	
		/**五星的日常任务**/
    public UserDailyTaskInfoBO getUserDailyTaskInfo() {
		return userDailyTaskInfo;
	}
	/**五星的日常任务**/
    public void setUserDailyTaskInfo(UserDailyTaskInfoBO userDailyTaskInfo) {
		this.userDailyTaskInfo = userDailyTaskInfo;
	}

	
	
}
