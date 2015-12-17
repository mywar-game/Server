package com.framework.task;

import com.framework.server.msg.model.ICodeAble;


public abstract class TaskEntry implements ITask {
    private int cmdCode;
	private ICodeAble requestMsgBody;

	public ICodeAble getRequestMsgBody() {
		return requestMsgBody;
	}
	public void setRequestMsgBody(ICodeAble requestMsgBody) {
		this.requestMsgBody = requestMsgBody;
	}
	public int getCmdCode() {
		return cmdCode;
	}
	public void setCmdCode(int cmdCode) {
		this.cmdCode = cmdCode;
	}
}
