package com.system.service;

import java.util.List;

import com.system.bo.LoginServerStatus;
import com.system.constant.ServerConstant;
import com.system.dao.LoginServerStatusDao;

/**
 * 登陆服务器Service
 * 
 * @author yezp
 */
public class LoginServerStatusService {

	private LoginServerStatusDao loginServerStatusDao;

	/**
	 * 获取区服务器的开启状态
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public int getLoginServerStatusInt(int dbSourceId) {
		List<LoginServerStatus> statusList = loginServerStatusDao.find(
				"from LoginServerStatus", dbSourceId);
		if (statusList.size() == 1) {
			LoginServerStatus status = statusList.get(0);
			return status.getStatus();
		}

		return ServerConstant.SERVER_STATUS_OPEN;
	}

	public LoginServerStatus getLoginServerStatus(int dbSourceId) {
		List<LoginServerStatus> statusList = loginServerStatusDao.find(
				"from LoginServerStatus", dbSourceId);
		if (statusList.size() == 1) {
			LoginServerStatus status = statusList.get(0);
			return status;
		}

		return null;
	}

	/**
	 * 设置服务器开启关闭状态
	 * 
	 * @param status
	 * @param dbSourceId
	 */
	public void setLoginServerStatus(LoginServerStatus status, int dbSourceId) {
		delLoginServerStatus(status, dbSourceId);
		addLoginServerStatus(status, dbSourceId);
	}

	/**
	 * 添加状态
	 * 
	 * @param status
	 * @param dbSourceId
	 */
	public void addLoginServerStatus(LoginServerStatus status, int dbSourceId) {
		loginServerStatusDao.save(status, dbSourceId);
	}

	/**
	 * 删除服务器状态
	 * 
	 * @param status
	 * @param dbSourceId
	 */
	public void delLoginServerStatus(LoginServerStatus status, int dbSourceId) {
		loginServerStatusDao.closeSession(dbSourceId);
		loginServerStatusDao.execute("delete from LoginServerStatus");
	}

	public LoginServerStatusDao getLoginServerStatusDao() {
		return loginServerStatusDao;
	}

	public void setLoginServerStatusDao(
			LoginServerStatusDao loginServerStatusDao) {
		this.loginServerStatusDao = loginServerStatusDao;
	}

}
