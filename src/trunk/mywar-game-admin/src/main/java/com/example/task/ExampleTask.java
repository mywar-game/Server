package com.example.task;


import com.example.msgbody.bodyExample;
import com.example.service.TestService;
import com.framework.common.CommomMsgBody;
import com.framework.server.msg.Msg;
import com.framework.server.msg.model.ICodeAble;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.task.TaskEntry;

public class ExampleTask extends TaskEntry {

	public ICodeAble execute(Msg msg) {
		// TODO Auto-generated method stub
		System.out.println("执行task!msgHead" + super.getCmdCode());
		bodyExample bb = (bodyExample) msg.getMsgBody();
		System.out.println("age:" + bb.getAge() + "---name:" + bb.getName());
//		ArenaService arenaService = ServiceCacheFactory.getServiceCache().
//		getService(ArenaService.class);
//		arenaService.findArenaListService();
         TestService testService = ServiceCacheFactory.getServiceCache()
				.getService(TestService.class);
         testService.addTestService();
		CommomMsgBody body = new CommomMsgBody();
		body.setErrorCode(1000);
		body.setErrorDescription("welcome you!我的家!我爱你");
		return body;
	}
}
