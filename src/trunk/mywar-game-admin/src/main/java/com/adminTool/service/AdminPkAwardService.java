package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.Activity;
import com.adminTool.bo.AdminPkAward;
import com.adminTool.dao.AdminPkAwardDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

/**
 * Pk奖励Service
 * 
 */
public class AdminPkAwardService {

	private AdminPkAwardDao adminPkAwardDao;

	/**
	 * 查询奖励列表
	 * 
	 * 
	 */
	public IPage<AdminPkAward> findPkAwardPageList(int toPage,
			int defaultPagesize) {
		adminPkAwardDao.closeSession(DBSource.CFG);
		IPage<AdminPkAward> list = adminPkAwardDao.findPage(
				"from AdminPkAward", new ArrayList<Object>(), defaultPagesize,
				toPage);
		return list;
	}
	
	/**
	 * 查询最后一条记录
	 */
	public AdminPkAward finLastAdminPkAward() {
		List<AdminPkAward> pkAwardList = adminPkAwardDao
				.find("FROM AdminPkAward ORDER BY id DESC LIMIT 1 ",
						DBSource.CFG);
		if (pkAwardList == null || pkAwardList.size() <= 0) {
			return null;
		}

		return pkAwardList.get(0);
	}

	/**
	 * 获取Pk奖励列表
	 * 
	 * @return
	 */
	public List<AdminPkAward> getAdminPkAwardList() {
		return adminPkAwardDao.find("from AdminPkAward", DBSource.CFG);
	}

	/**
	 * 添加奖励列表
	 * 
	 * @param adminPkAward
	 */
	public void addAdminPkAward(AdminPkAward adminPkAward) {
		adminPkAwardDao.save(adminPkAward, DBSource.CFG);
	}

	/**
	 * 修改PK奖励
	 * 
	 * @param adminPkAward
	 */
	public void updateAdminPkAward(AdminPkAward adminPkAward) {
		adminPkAwardDao.update(adminPkAward, DBSource.CFG);
	}

	/**
	 * 删除Pk奖励
	 * 
	 * @param id
	 */
	public void delAdminPkAward(Integer id) {
		AdminPkAward adminPkAward = getOneAdminPkAward(id);
		adminPkAwardDao.remove(adminPkAward, DBSource.CFG);
	}

	/**
	 * 根据id查找Pk奖励
	 *             
	 * @param id
	 * @return
	 */
	public AdminPkAward getOneAdminPkAward(Integer id) {
		return adminPkAwardDao.loadBy("id", id, DBSource.CFG);
	}

	public AdminPkAwardDao getAdminPkAwardDao() {
		return adminPkAwardDao;
	}

	public void setAdminPkAwardDao(AdminPkAwardDao adminPkAwardDao) {
		this.adminPkAwardDao = adminPkAwardDao;
	}

}
