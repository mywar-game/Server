package com.system.manager;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;

import com.framework.common.DBSource;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.Command;
import com.system.constant.ServerConstant;
import com.system.dao.CommandDao;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class ShellThread implements Runnable {

	private static final int TIME_OUT = 1000 * 5 * 60;
	/**命令在数据库中的逐渐编号**/
	private int id;
	private String cmds;
	private ShellExecutor executor; 
	public ShellThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShellThread(int id,String ip, ShellExecutor executor) {
		super();
		this.id=id;
		this.cmds = ip;
		this.executor = executor;
	}

	@Override
	public void run(){
		// TODO Auto-generated method stub
		CommandDao commandDao = ServiceCacheFactory.getServiceCache().getService(CommandDao.class);
		InputStream stdOut = null;
		InputStream stdErr = null;
		String outStr = "";
		String outErr = "";
		int ret = -1;
		Connection conn = null;
		try {
			if (executor.login()) {
				conn = executor.getConn();
				Session session = conn.openSession();
				LogSystem.info("成功连接远程服务器:ip,"+executor.getIp()+",user,"+executor.getUsr()+",passwd,"+executor.getPsword());
				// Execute a command on the remote machine.
				LogSystem.info(executor.getIp()+"执行了命令:"+cmds);
				session.execCommand(cmds);

				stdOut = new StreamGobbler(session.getStdout());
				outStr = executor.processStream(stdOut, executor.getCharset());

				stdErr = new StreamGobbler(session.getStderr());
				outErr = executor.processStream(stdErr, executor.getCharset());

				session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
				LogSystem.info("命令执行信息:"+outStr);
				LogSystem.info("执行结果信息:"+outErr);
				System.out.println("outStr=" + outStr);
				System.out.println("outErr=" + outErr);

				ret = session.getExitStatus();
				LogSystem.info("返回命令码:"+ret);
			} else {
				throw new Exception("登录远程机器失败" + executor.getIp()); // 自定义异常类 实现略
			}
		} catch(Exception e){
			LogSystem.error(e, "发送命令出现异常");
		}finally {
			if (conn != null) {
				conn.close();
			}
			try {
				if(stdOut!=null){
					stdOut.close();
				}
				if(stdErr!=null){
					stdErr.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Command cmd = commandDao.loadBy("id", id, DBSource.ADMIN);
		if(cmd==null){
			return;
		}
		if(ret!=0){//执行命令失败
			cmd.setCurrentCmdStatus(ServerConstant.EXECUTE_FAIL);
			if(ret==-1){//登陆远程机器失败
				cmd.setExecInfo("登陆远程机器失败");
			}else{//执行失败
				cmd.setExecInfo(outErr);
			}
		}else{//执行成功
			if(StringUtils.isBlank(outErr)){
				cmd.setCurrentCmdStatus(ServerConstant.EXECUTE_SUC);
				cmd.setExecInfo("执行成功");
			}else{
				cmd.setCurrentCmdStatus(ServerConstant.EXECUTE_FAIL);
				cmd.setExecInfo("执行过程中出现错误信息,请查看日志");
			}
		}
		commandDao.closeSession(DBSource.ADMIN);
		commandDao.saveOrUpdate(cmd);
	}


}
