package com.fantingame.game.msgbody.client.task;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.task.UserDailyTaskInfoBO;
import java.util.List;
import java.util.ArrayList;

/**获取用户日常任务信息**/
public class TaskAction_getUserDailyTaskInfoRes implements ICodeAble {

		/**剩余次数**/
	private Integer remainderTimes=0;
	/**用户日常任务列表**/
	private List<UserDailyTaskInfoBO> userDailyTaskInfoList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(remainderTimes);

		
        if(userDailyTaskInfoList==null||userDailyTaskInfoList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userDailyTaskInfoList.size());
		}
		if(userDailyTaskInfoList!=null&&userDailyTaskInfoList.size()>0){
			for(int userDailyTaskInfoListi=0;userDailyTaskInfoListi<userDailyTaskInfoList.size();userDailyTaskInfoListi++){
				userDailyTaskInfoList.get(userDailyTaskInfoListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		remainderTimes = inputStream.readInt();

		
        int userDailyTaskInfoListSize = inputStream.readInt();
		if(userDailyTaskInfoListSize>0){
			userDailyTaskInfoList = new ArrayList<UserDailyTaskInfoBO>();
			for(int userDailyTaskInfoListi=0;userDailyTaskInfoListi<userDailyTaskInfoListSize;userDailyTaskInfoListi++){
				 UserDailyTaskInfoBO entry = new UserDailyTaskInfoBO();entry.decode(inputStream);userDailyTaskInfoList.add(entry);
			}
		}
	}
	
		/**剩余次数**/
    public Integer getRemainderTimes() {
		return remainderTimes;
	}
	/**剩余次数**/
    public void setRemainderTimes(Integer remainderTimes) {
		this.remainderTimes = remainderTimes;
	}
	/**用户日常任务列表**/
    public List<UserDailyTaskInfoBO> getUserDailyTaskInfoList() {
		return userDailyTaskInfoList;
	}
	/**用户日常任务列表**/
    public void setUserDailyTaskInfoList(List<UserDailyTaskInfoBO> userDailyTaskInfoList) {
		this.userDailyTaskInfoList = userDailyTaskInfoList;
	}

	
	
}
