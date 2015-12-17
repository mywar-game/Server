package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.AdminMarquee;
import com.adminTool.dao.AdminMarqueeDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

/**
 * 跑马灯Service
 * 
 * @author yezp
 */
public class AdminMarqueeService {

	private AdminMarqueeDao adminMarqueeDao;

	/**
	 * 查询跑马灯
	 * 
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<AdminMarquee> findMarqueePageList(int toPage,
			int defaultPagesize) {
		adminMarqueeDao.closeSession(DBSource.ADMIN);
		IPage<AdminMarquee> list = adminMarqueeDao.findPage(
				"from AdminMarquee", new ArrayList<Object>(), defaultPagesize,
				toPage);
		return list;
	}

	/**
	 * 获取跑马灯列表
	 * 
	 * @return
	 */
	public List<AdminMarquee> getAdminMarqueeList() {
		return adminMarqueeDao.find("from AdminMarquee", DBSource.ADMIN);
	}

	/**
	 * 添加跑马灯
	 * 
	 * @param adminMarquee
	 */
	public void addAdminMarquee(AdminMarquee adminMarquee) {
		adminMarqueeDao.save(adminMarquee, DBSource.ADMIN);
	}

	/**
	 * 修改跑马灯
	 * 
	 * @param adminMarquee
	 */
	public void updateAdminMarquee(AdminMarquee adminMarquee) {
		adminMarqueeDao.update(adminMarquee, DBSource.ADMIN);
	}

	/**
	 * 删除跑马灯
	 * 
	 * @param id
	 */
	public void delAdminMarquee(Integer id) {
		AdminMarquee adminMarquee = getOneAdminMarquee(id);
		adminMarqueeDao.remove(adminMarquee, DBSource.ADMIN);
	}

	/**
	 * 根据id查找跑马灯
	 * 
	 * @param id
	 * @return
	 */
	public AdminMarquee getOneAdminMarquee(Integer id) {
		return adminMarqueeDao.loadBy("id", id, DBSource.ADMIN);
	}

	public AdminMarqueeDao getAdminMarqueeDao() {
		return adminMarqueeDao;
	}

	public void setAdminMarqueeDao(AdminMarqueeDao adminMarqueeDao) {
		this.adminMarqueeDao = adminMarqueeDao;
	}

}
