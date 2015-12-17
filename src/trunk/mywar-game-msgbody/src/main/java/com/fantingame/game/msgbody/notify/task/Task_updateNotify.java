package com.fantingame.game.msgbody.notify.task;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.notify.task.UserTaskBO;
import java.util.List;
import java.util.ArrayList;

/**任务状态变更推送接口**/
public class Task_updateNotify implements ICodeAble {

		/**需要更新的用户任务信息，不存在就添加，存在就覆盖**/
	private List<UserTaskBO> updateUserTaskList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(updateUserTaskList==null||updateUserTaskList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(updateUserTaskList.size());
		}
		if(updateUserTaskList!=null&&updateUserTaskList.size()>0){
			for(int updateUserTaskListi=0;updateUserTaskListi<updateUserTaskList.size();updateUserTaskListi++){
				updateUserTaskList.get(updateUserTaskListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int updateUserTaskListSize = inputStream.readInt();
		if(updateUserTaskListSize>0){
			updateUserTaskList = new ArrayList<UserTaskBO>();
			for(int updateUserTaskListi=0;updateUserTaskListi<updateUserTaskListSize;updateUserTaskListi++){
				 UserTaskBO entry = new UserTaskBO();entry.decode(inputStream);updateUserTaskList.add(entry);
			}
		}
	}
	
		/**需要更新的用户任务信息，不存在就添加，存在就覆盖**/
    public List<UserTaskBO> getUpdateUserTaskList() {
		return updateUserTaskList;
	}
	/**需要更新的用户任务信息，不存在就添加，存在就覆盖**/
    public void setUpdateUserTaskList(List<UserTaskBO> updateUserTaskList) {
		this.updateUserTaskList = updateUserTaskList;
	}

	
	
}
