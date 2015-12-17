package com.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.bo.AdminPysicsModule;
import com.admin.dao.AdminPysicsModuleDao;

public class AdminPysicsModuleService {
private AdminPysicsModuleDao adminPysicsModuleDao;

public AdminPysicsModuleDao getAdminPysicsModuleDao() {
	return adminPysicsModuleDao;
}

public void setAdminPysicsModuleDao(AdminPysicsModuleDao adminPysicsModuleDao) {
	this.adminPysicsModuleDao = adminPysicsModuleDao;
}

/**
 * 查找所有功能模块
 * @return
 */
public List<AdminPysicsModule> findAllModuleList() {
	return adminPysicsModuleDao.findAll();
}

/**
 * 查找所有功能模块MAP结构
 */
public Map<Integer, AdminPysicsModule> findAllModuleMap() {
	List<AdminPysicsModule> list = adminPysicsModuleDao.findAll();
	Map<Integer, AdminPysicsModule> map = new HashMap<Integer, AdminPysicsModule>();
	for (AdminPysicsModule ap:list) {
		map.put(ap.getId(), ap);
	}
	return map;
}
}
