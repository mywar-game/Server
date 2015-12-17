package com.fantingame.game.msgbody.client.task;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.task.UserDailyTaskInfoBO;
import java.util.List;
import java.util.ArrayList;

/**一键刷新**/
public class TaskAction_oneClickRefreshRes implements ICodeAble {

		/**用户日常任务列表**/
	private List<UserDailyTaskInfoBO> userDailyTaskInfoList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
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
		
        int userDailyTaskInfoListSize = inputStream.readInt();
		if(userDailyTaskInfoListSize>0){
			userDailyTaskInfoList = new ArrayList<UserDailyTaskInfoBO>();
			for(int userDailyTaskInfoListi=0;userDailyTaskInfoListi<userDailyTaskInfoListSize;userDailyTaskInfoListi++){
				 UserDailyTaskInfoBO entry = new UserDailyTaskInfoBO();entry.decode(inputStream);userDailyTaskInfoList.add(entry);
			}
		}
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
