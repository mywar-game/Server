package com.admin.dao;

import com.admin.bo.AdminPowerPhysics;
import com.framework.dao.BaseEntityDao;


public class AdminPowerPhysicsDao extends BaseEntityDao<AdminPowerPhysics> {

	/**
	 * 查询最大POWERID
	 * @return
	 */
	public Integer findMaxPowerId() {
		Integer reInt = 0;
		Object o = this.getSession().createQuery("select max(powerId) from AdminPowerPhysics app").uniqueResult();
	    if (o != null) {
	    	reInt = (Integer) o;
	    }
	    return reInt;
	}
}
