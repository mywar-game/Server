package com.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.bo.AdminRole;
import com.admin.dao.AdminRoleDao;
import com.framework.common.DBSource;

public class AdminRoleService {
	
	private AdminRoleDao adminRoleDao;
	
	public List<AdminRole> findAdminRoleList(){
		return adminRoleDao.find("from AdminRole", DBSource.ADMIN);
	}
	
	public void saveOrUpdateAdminRole(AdminRole adminRole){
		adminRoleDao.closeSession(DBSource.ADMIN);
		adminRoleDao.saveOrUpdate(adminRole);
	}
	
	public AdminRole findOneAdminRole(Integer roleId){
		return adminRoleDao.load(roleId, DBSource.ADMIN);
	}
	
	public void delOneAdminRole(AdminRole adminRole){
		adminRoleDao.remove(adminRole, DBSource.ADMIN);
	}
	
	public Map<Integer, String> findIdAndNameMap(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<AdminRole> list = findAdminRoleList();
		for (AdminRole adminRole : list) {
			//超级管理员不能选择
			if (adminRole.getRoleId() == 1) {
				continue;
			}
			map.put(adminRole.getRoleId(), adminRole.getRoleName());
		}
		return map;
	}

	public void setAdminRoleDao(AdminRoleDao adminRoleDao) {
		this.adminRoleDao = adminRoleDao;
	}

	public AdminRoleDao getAdminRoleDao() {
		return adminRoleDao;
	}

}
