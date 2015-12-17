package com.framework.task;


import java.util.List;

import com.framework.server.msg.Msg;
import com.framework.server.msg.model.SynList;

public interface ITaskExcutor {
  public List<Msg> excutorTask(SynList<Msg> msgs, int type);
}
