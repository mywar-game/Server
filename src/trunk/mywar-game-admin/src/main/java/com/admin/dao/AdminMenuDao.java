package com.admin.dao;

import com.admin.bo.AdminMenu;
import com.admin.service.AdminMenuService;
import com.framework.dao.BaseEntityDao;


public class AdminMenuDao extends BaseEntityDao<AdminMenu> {
	/**
	 * 查询最大POWERID
	 * @return
	 */
	public Integer findMaxPowerId() {
		Integer reInt = AdminMenuService.ORDER_INIT;
		Object o = this.getSession().createQuery("select max(powerId) from AdminMenu").uniqueResult();
	    if (o != null) {
	    	reInt = (Integer) o;
	    }
	    return reInt;
	}
	
	/**
	 * 查询最大排序ID
	 */
	public Integer findMaxOrderId(Integer parentId) {
		Integer reInt = 0;
		Object o = this.getSession().createQuery("select max(orderId) from AdminMenu am where am.parentId = " + parentId + "").uniqueResult();
	    if (o != null) {
	    	reInt = (Integer) o;
	    }
	    return reInt;
	}
	/**
	 * 查询最小排序ID
	 */
	public Integer findMinOrderId(Integer parentId) {
		Integer reInt = AdminMenuService.ORDER_INIT;
		Object o = this.getSession().createQuery("select min(orderId) from AdminMenu am where am.parentId = " + parentId + "").uniqueResult();
	    if (o != null) {
	    	reInt = (Integer) o;
	    }
	    return reInt;
	}
}
