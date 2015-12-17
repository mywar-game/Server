package com.admin.service;

import java.util.ArrayList;
import java.util.Date;

import com.admin.bo.AdminUser;
import com.admin.bo.AdminUserLog;
import com.admin.dao.AdminUserDao;
import com.admin.dao.AdminUserLogDao;
import com.framework.common.DBSource;
import com.framework.common.ErrorCode;
import com.framework.common.IPage;
import com.framework.common.SystemCode;


public class AdminUserService {
	private AdminUserDao adminUserDao;
	private AdminUserLogDao adminUserLogDao;
	// 初始功能权限值
	public static final String INITAL_POWER = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
	// 初始菜单权限值
	public static final String INITAL_MENU_POWER = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

	public AdminUserDao getAdminUserDao() {
		return adminUserDao;
	}

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	/**
	 * 添加一个管理员
	 * 
	 * @param adminUser
	 * @param error
	 */
	public void addAdminUserService(AdminUser adminUser, ErrorCode error) {
		adminUser.setId(null);
		error.setErrorCode(SystemCode.SYS_SUCESS);
		AdminUser au = adminUserDao.loadBy("adminName",
				adminUser.getAdminName(), DBSource.ADMIN);
		if (au != null) {
			error.setErrorCode(SystemCode.SYS_FAIL);
			error.setErrorDisc("用户已经存在");
			return;
		} else {
			adminUserDao.save(adminUser, DBSource.ADMIN);
		}
	}

	/**
	 * 根据一个ID删除一个管理员
	 * 
	 * @param adminUser
	 * @param error
	 */
	public void delAdminUserService(Integer Id) {
		adminUserDao.closeSession(DBSource.ADMIN);
		adminUserDao.execute("delete from AdminUser au where au.id = " + Id
				+ "");
	}

	/**
	 * 解锁密码
	 * 
	 * @param adminUser
	 * @param error
	 */
	public void updateLock(Integer Id, Integer loginFailTimes) {
		adminUserDao.closeSession(DBSource.ADMIN);
		adminUserDao.execute("update AdminUser au set au.loginFailTimes="
				+ loginFailTimes + " where au.id = " + Id);
	}

	/**
	 * 修改密码有效期
	 * 
	 * @param adminUser
	 * @param error
	 */
	public void updatePwdDueTime(Integer Id, String dueTime) {
		adminUserDao.closeSession(DBSource.ADMIN);
		adminUserDao.execute("update AdminUser au set au.dueTime='" + dueTime
				+ "' where au.id = " + Id);
	}

	/**
	 * 查询一个管理员用户
	 * 
	 * @param Id
	 * @return
	 */
	public AdminUser findOneAdmin(Integer Id) {
		return adminUserDao.load(Id, DBSource.ADMIN);
	}

	/**
	 * 修改管理员密码
	 */
	public void updateAdminUserService(Integer Id, String oldPassword,
			String newPassword, ErrorCode error) {
		AdminUser au = adminUserDao.load(Id, DBSource.ADMIN);
		if (au == null) {
			error.setErrorCode(SystemCode.SYS_FAIL);
			error.setErrorDisc("用户不存在");
			return;
		} else if (!au.getPassword().equals(oldPassword)) {
			error.setErrorCode(SystemCode.SYS_FAIL);
			error.setErrorDisc("旧密码错误");
			return;
		} else {
			au.setPassword(newPassword);
			error.setErrorCode(SystemCode.SYS_SUCESS);
			return;
		}
	}

	/**
	 * 修改管理员信息
	 */
	public void updateAdminUser(Integer adminUserId, String menuPowerString,
			String powerString, Integer roleId) {
		AdminUser adminUser = adminUserDao.load(adminUserId, DBSource.ADMIN);
		adminUser.setMenuPowerString(menuPowerString);
		adminUser.setPowerString(powerString);
		adminUser.setRoleId(roleId);
	}

	/**
	 * 修改功能权限
	 * 
	 * @param id
	 * @param powerString
	 */
	public void updateAdminUserPowerService(Integer id, String powerString) {
		AdminUser au = adminUserDao.load(id, DBSource.ADMIN);
		if (au != null) {
			au.setPowerString(powerString);
		}
	}

	/**
	 * 修改菜单权限
	 * 
	 * @param id
	 * @param powerString
	 */
	public void updateAdminUserMenuPowerService(Integer id,
			String menuPowerString) {
		AdminUser au = adminUserDao.load(id, DBSource.ADMIN);
		if (au != null) {
			au.setMenuPowerString(menuPowerString);
		}
	}

	/**
	 * 查询管理员列表
	 */
	public IPage<AdminUser> findAdminUserList(Integer currentPage,
			Integer pageSize) {
		return adminUserDao.findPage("from AdminUser", new ArrayList<Object>(),
				pageSize, currentPage);
	}

	/**
	 * 校验用户名和密码
	 */
	public AdminUser findAdminUser(String adminUser, String password,
			ErrorCode error) {
		error.setErrorCode(SystemCode.SYS_FAIL);
		Date date = new Date();
		AdminUser au = adminUserDao.loadBy("adminName", adminUser,
				DBSource.ADMIN);
		if (au == null) {
			error.setErrorDisc("用户名不存在");
			return null;
		} else {
			if (au.getId().intValue() != 1) {// 非admin用户需要判断密码有效期和登陆失败次数
				if (au.getDueTime().getTime() < date.getTime()) {
					error.setErrorDisc("密码已过了有效期,请联系管理员!");
					// 记录管理员登陆日志
					AdminUserLog log = new AdminUserLog();
					log.setAdminName(au.getAdminName());
					log.setState(3);
					log.setTime(date);
					saveLog(log);
					return null;
				}
				if (au.getLoginFailTimes().intValue() >= 5) {
					error.setErrorDisc("账号连续登陆失败次数超过5次,密码已锁定,请联系管理员!");
					// 记录管理员登陆日志
					AdminUserLog log = new AdminUserLog();
					log.setAdminName(au.getAdminName());
					log.setState(4);
					log.setTime(date);
					saveLog(log);
					return null;
				}
			}
			if (!au.getPassword().equals(password)) {
				error.setErrorDisc("密码不正确");
				if (au.getId().intValue() != 1) {
					// 登陆失败次数 不用处理超级管理员
					updateLock(au.getId(), au.getLoginFailTimes() + 1);// 登陆失败次数+1
				}
				// 记录管理员登陆日志
				AdminUserLog log = new AdminUserLog();
				log.setAdminName(au.getAdminName());
				log.setState(2);
				log.setTime(date);
				saveLog(log);
				return null;
			}
		}
		error.setErrorCode(SystemCode.SYS_SUCESS);
		return au;
	}

	/**
	 * 保存管理员登陆日志
	 * 
	 * @param log
	 */
	public void saveLog(AdminUserLog log) {
		adminUserLogDao.saveUnsyn(log, DBSource.ADMIN);
	}

	public AdminUserLogDao getAdminUserLogDao() {
		return adminUserLogDao;
	}

	public void setAdminUserLogDao(AdminUserLogDao adminUserLogDao) {
		this.adminUserLogDao = adminUserLogDao;
	}
}