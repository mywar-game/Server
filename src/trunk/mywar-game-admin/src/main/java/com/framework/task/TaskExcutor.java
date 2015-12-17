package com.framework.task;

import java.util.ArrayList;
import java.util.List;

import com.framework.log.LogSystem;
import com.framework.server.msg.Msg;
import com.framework.server.msg.MsgHead;
import com.framework.server.msg.model.ICodeAble;
import com.framework.server.msg.model.SynList;

public class TaskExcutor implements ITaskExcutor {
    private static TaskExcutor taskExcutor;
    
    private TaskExcutor() {
    };
    
    public static TaskExcutor getInstance() {
    	if (taskExcutor == null) {
    		taskExcutor = new TaskExcutor();
    	}
    	return taskExcutor;
    }
    
	public List<Msg> excutorTask(SynList<Msg> msgs, int type) {
		// TODO Auto-generated method stub
		List<Msg> reList = new ArrayList<Msg>();
		
		if (msgs != null && msgs.size() > 0) {
			for (Msg msg:msgs) {
				if (msg.getMsgHead().getMsgType() == MsgHead.TYPEOFRESPONSE) {
					continue;
				}
				MsgHead msgHead = msg.getMsgHead();
				TaskEntry task = TaskManager.getInstance().findTask(msgHead);
				if (task != null) {
					ICodeAble requestBody = task.getRequestMsgBody();
					try {
						requestBody = requestBody.getClass().newInstance();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						LogSystem.error(e, "");
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						LogSystem.error(e, "");
					}
					if (requestBody == null) {
						LogSystem.error(new NullPointerException("can not find the requestBody of task by cmdCode:" + msgHead.getCmdCode()), "");
					}
					msg.decodeBody(requestBody, type);
					ICodeAble response = task.execute(msg);
					if (response != null) {
						Msg msgTemp = new Msg();
						msgHead.setMsgType(MsgHead.TYPEOFRESPONSE);
						msgTemp.setMsgHead(msgHead);
						msgTemp.setMsgBody(response);
						reList.add(msgTemp);
					}
				} else {
					LogSystem.error(new NullPointerException("can not find task by cmdCode:" + msgHead.getCmdCode()), "");
				}
			}
		}
		return reList;
	}

}
