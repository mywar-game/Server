package com.admin.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.admin.bo.AdminPowerPhysics;
import com.admin.bo.AdminPysicsModule;
import com.admin.dao.AdminPowerPhysicsDao;
import com.framework.common.DBSource;
import com.framework.common.ErrorCode;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;

public class AdminPowerPhysicsService {
	private AdminPowerPhysicsDao adminPowerPhysicsDao;

	public AdminPowerPhysicsDao getAdminPowerPhysicsDao() {
		return adminPowerPhysicsDao;
	}

	public void setAdminPowerPhysicsDao(
			AdminPowerPhysicsDao adminPowerPhysicsDao) {
		this.adminPowerPhysicsDao = adminPowerPhysicsDao;
	}

	/**
	 * 添加一个原子操作
	 * 
	 * @param adminPowerPhysics
	 */
	public void addPowerPhysicsService(AdminPowerPhysics adminPowerPhysics,
			ErrorCode error) {
		adminPowerPhysics.setId(null);
		if (findPowerByActionName(adminPowerPhysics.getActionName()) != null) {
			error.setErrorCode(SystemConstant.FAIL_CODE);
			error.setErrorDisc("adminPowerPysicsService.repeatedModuleName");
			return;
		}
		adminPowerPhysicsDao.save(adminPowerPhysics, DBSource.ADMIN);
	}

	/**
	 * 根据模块ID查询原子操作
	 * 
	 * @param moduleId
	 */
	public List<AdminPowerPhysics> findPowerPhysicsByModuleIdList(
			Integer moduleId) {
		return adminPowerPhysicsDao.find(
				"from AdminPowerPhysics as app where app.moduleId = " + moduleId
						+ "", DBSource.ADMIN);
	}

	// /**
	// * 查询所有的原子操作
	// *
	// */
	// public List<AdminPowerPhysics> findAllPowerPhysicsList() {
	// return adminPowerPhysicsDao.findAll();
	// }
	/**
	 * 查询一个原子操作
	 */
	public AdminPowerPhysics findOneAdminPowerPhysics(Integer id) {
		return adminPowerPhysicsDao.load(id, DBSource.ADMIN);
	}

	/**
	 * 查询所有的原子操作的map结构 menuId映射原子操作
	 * 
	 */
	public Map<String, List<AdminPowerPhysics>> findAllPowerPhysicsMap() {
		List<AdminPowerPhysics> list = adminPowerPhysicsDao.findAll();
		AdminPysicsModuleService aps = ServiceCacheFactory.getServiceCache().getService(AdminPysicsModuleService.class);
		Map<Integer, AdminPysicsModule> moduleMap = aps.findAllModuleMap();

		Map<String, List<AdminPowerPhysics>> reMap = new LinkedHashMap<String, List<AdminPowerPhysics>>();
		List<AdminPowerPhysics> temp = null;
		Integer moduleId = null;
		String moduleName = null;
		for (AdminPowerPhysics app : list) {
			moduleId = app.getModuleId();
			if (moduleMap.containsKey(moduleId)) {
				moduleName = moduleMap.get(moduleId).getName();
			} else {
				moduleName = "undifineModuleName";
			}
			if (reMap.containsKey(moduleName)) {
				reMap.get(moduleName).add(app);
			} else {
				temp = new ArrayList<AdminPowerPhysics>();
				temp.add(app);
				reMap.put(moduleName, temp);
			}
		}
		return reMap;
	}

	/**
	 * 查询最大powerid
	 * 
	 * @return
	 */
	public Integer findMaxPowerId() {
		return adminPowerPhysicsDao.findMaxPowerId();
	}

	/**
	 * 根据ActionName查询权限
	 * 
	 * @param actionName
	 * @return
	 */
	public AdminPowerPhysics findPowerByActionName(String actionName) {
		return adminPowerPhysicsDao.loadBy("actionName", actionName,
				DBSource.ADMIN);
	}

	/**
	 * 删除一个原子操作
	 * 
	 * @param id
	 */
	public void delPowerPhysicsService(Integer id) {
		String queryString = "delete from AdminPowerPhysics as pp where pp.id = "
				+ id + "";
		adminPowerPhysicsDao.execute(queryString);
	}

	/**
	 * 编辑一个原子操作
	 */
	public void updatePowerPhysicsService(AdminPowerPhysics adminPowerPhysics) {
		AdminPowerPhysics ap = adminPowerPhysicsDao.load(adminPowerPhysics
				.getId(), DBSource.ADMIN);
		if (ap == null)
			return;
		ap.setActionName(adminPowerPhysics.getActionName());
		ap.setModuleId(adminPowerPhysics.getModuleId());
		ap.setModuleName(adminPowerPhysics.getModuleName());
		ap.setName(adminPowerPhysics.getName());
		ap.setOrderId(adminPowerPhysics.getOrderId());
	}
}
