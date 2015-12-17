package com.system.manager;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class ShellExecutor {
	/** */
	/**  */
	private Connection conn;
	/** */
	/** 远程机器IP */
	private String ip;
	/** */
	/** 用户名 */
	private String usr;
	/** */
	/** 密码 */
	private String psword;
	/**命令类型   1，更新版本包   2，更新静态数据    3，更新结构数据    4开、关服务器**/
	private int commandType;
	private String charset = Charset.defaultCharset().toString();

	private static final int TIME_OUT = 1000 * 5 * 60;

	/** */
	/**
	 * 构造函数
	 * 
	 * @param ip
	 * @param usr
	 * @param ps
	 */
	public ShellExecutor(String ip, String usr, String ps, int commandType) {
		this.ip = ip;
		this.usr = usr;
		this.psword = ps;
		this.commandType=commandType;
	}

	/** */
	/**
	 * 登录
	 * 
	 * @return
	 * @throws IOException
	 */
	public boolean login() throws IOException {
		conn = new Connection(ip);
		conn.connect();
		return conn.authenticateWithPassword(usr, psword);
	}

	/** */
	/**
	 * 执行脚本
	 * 
	 * @param cmds
	 * @return
	 * @throws Exception
	 */
	public int exec(String cmds) throws Exception {
		InputStream stdOut = null;
		InputStream stdErr = null;
		String outStr = "";
		String outErr = "";
		int ret = -1;
		try {
			if (login()) {
				// Open a new {@link Session} on this connection
				Session session = conn.openSession();
				// Execute a command on the remote machine.
				session.execCommand(cmds);

				stdOut = new StreamGobbler(session.getStdout());
				outStr = processStream(stdOut, charset);

				stdErr = new StreamGobbler(session.getStderr());
				outErr = processStream(stdErr, charset);

				session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);

				System.out.println("outStr=" + outStr);
				System.out.println("outErr=" + outErr);

				ret = session.getExitStatus();
			} else {
				throw new Exception("登录远程机器失败" + ip); // 自定义异常类 实现略
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
			stdOut.close();;
			stdErr.close();;
		}
		return ret;
	}

	/** */
	/**
	 * @param in
	 * @param charset
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public String processStream(InputStream in, String charset)
			throws Exception {
		byte[] buf = new byte[1024];
		StringBuilder sb = new StringBuilder();
		while (in.read(buf) != -1) {
			sb.append(new String(buf, charset));
		}
		return sb.toString();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getPsword() {
		return psword;
	}

	public void setPsword(String psword) {
		this.psword = psword;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public int getCommandType() {
		return commandType;
	}

	public void setCommandType(int commandType) {
		this.commandType = commandType;
	}

	public static void main(String args[]) throws Exception {
		ShellExecutor exe = new ShellExecutor("61.174.13.43", "root","wenbo@126.com",0);
		// 执行myTest.sh 参数为java Know dummy
		System.out.println(exe
				.exec("cd /home/watson"));
		// exe.exec("uname -a && date && uptime && who");
	}
}
