package com.framework.task;

import com.framework.server.msg.Msg;
import com.framework.server.msg.model.ICodeAble;

public interface ITask {
   public ICodeAble execute(Msg msg);
}
