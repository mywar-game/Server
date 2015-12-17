package com.adminTool.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.PlatformConstant;
import com.adminTool.dao.PlatformConstantDao;
import com.framework.common.DBSource;

public class PlatformConstantService {
	
	private PlatformConstantDao platformConstantDao;
	
	public void save(PlatformConstant platformConstant) {
		platformConstantDao.save(platformConstant, DBSource.ADMIN);
	}
	
	/**
	 *  根据条件查到的ID和name对应的map
	 * @return
	 */
	public Map<Integer, String> findPlatformMapByCondition(Integer id, String name) {
		Map<Integer, String> platformMap = new LinkedHashMap<Integer, String>();
		StringBuffer sb = new StringBuffer();
		if (id != null) {
			sb.append("SELECT ID, PLATFORM FROM platform_constant WHERE ID = " + id);
		}
		if (name != null && !"".equals(name)) {
			sb.append("SELECT ID, PLATFORM FROM platform_constant WHERE PLATFORM LIKE '%" + name + "%'");
		}
		if (sb.length() == 0) {
			sb.append("SELECT ID, PLATFORM FROM platform_constant");
		}
		platformConstantDao.closeSession(DBSource.ADMIN);
		List<Object> list = platformConstantDao.findSQL_(sb.toString());
		for (int i = 0; i < list.size(); i++) {
			Integer id1 = Integer.parseInt(((Object[]) list.get(i))[0].toString());
			String platform = ((Object[]) list.get(i))[1].toString();
			platformMap.put(id1, platform);
		}
		return platformMap;
	}

	/**
	 * 获取 platformConstantDao 
	 */
	public PlatformConstantDao getPlatformConstantDao() {
		return platformConstantDao;
	}

	/**
	 * 设置 platformConstantDao 
	 */
	public void setPlatformConstantDao(PlatformConstantDao platformConstantDao) {
		this.platformConstantDao = platformConstantDao;
	}

}
