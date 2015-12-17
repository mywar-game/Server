package com.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.bo.AdminMenu;
import com.admin.dao.AdminMenuDao;
import com.framework.common.DBSource;
import com.framework.common.ErrorCode;
import com.framework.common.SystemCode;

public class AdminMenuService {
 private AdminMenuDao adminMenuDao;
 public static final int ORDER_INIT = 100000;
public AdminMenuDao getAdminMenuDao() {
	return adminMenuDao;
}

public void setAdminMenuDao(AdminMenuDao adminMenuDao) {
	this.adminMenuDao = adminMenuDao;
}

/**
 * 添加一个菜单项
 * @param adminMenu
 * @param error
 */
public void addAdminMenuService(AdminMenu adminMenu, ErrorCode error) {
	adminMenu.setId(null);
	if (adminMenu.getParentId() != 0) {
		AdminMenu am = adminMenuDao.load(adminMenu.getParentId(), DBSource.ADMIN);
		if (am == null) {
			error.setErrorCode(SystemCode.SYS_FAIL);
			error.setErrorDisc("adminMenuService.menuParentNodeNotExist");
			return;
		}
	}
	error.setErrorCode(SystemCode.SYS_SUCESS);
	adminMenuDao.save(adminMenu, DBSource.ADMIN);
}

public AdminMenu findOneAdminMenu(Integer id) {
	return adminMenuDao.load(id, DBSource.ADMIN);
}
/**
 * 删除一个菜单项
 */

public void delAdminMenuService(Integer Id, ErrorCode error) {

	String queryString = "from AdminMenu as am where am.parentId = " + Id + "";
    List<AdminMenu> am = adminMenuDao.find(queryString, DBSource.ADMIN);
    if (am == null || am.size() == 0) {
 		/**
 		 * 删除节点本身
 		 */
    		error.setErrorCode(SystemCode.SYS_SUCESS);
 		adminMenuDao.execute("delete from AdminMenu as am where am.id = " + Id + "");
 		error.setErrorCode(SystemCode.SYS_SUCESS);
     } else {
         error.setErrorCode(SystemCode.SYS_FAIL);
         error.setErrorDisc("adminMenuService.existChidNodeCannotDelete");
         return;
     }

}

/**
 * 修改一个菜单项
 */
public void updateAdminMenuService(AdminMenu adminMenu, ErrorCode error) {
	if (adminMenu.getParentId() != 0) {
		AdminMenu am = adminMenuDao.loadBy("parentId", adminMenu.getParentId(), DBSource.ADMIN);
		if (am == null) {
			error.setErrorCode(SystemCode.SYS_FAIL);
			error.setErrorDisc("adminMenuService.menuParentNodeNotExist");
			return;
		}
	}	
	error.setErrorCode(SystemCode.SYS_SUCESS);
	String queryString = "update AdminMenu am set am.menuName = '" + adminMenu.getMenuName() + "', "
						 + "am.menuPath = '" + adminMenu.getMenuPath() + "', am.orderId = "
						 + "" + adminMenu.getOrderId() + ", am.ifLeaf = '" + adminMenu.getIfLeaf() + "' where am.id = " + adminMenu.getId() + "";
	adminMenuDao.execute(queryString);
 }

/**
 * 查询所有叶子菜单项
 */
public List<AdminMenu> findAllLeafList() {
	return adminMenuDao.find("from AdminMenu as am where am.ifLeaf = 'T'", DBSource.ADMIN);
}

/**
 * 查询所有一级节点
 */
public List<AdminMenu> findAllRootList() {
	return adminMenuDao.find("from AdminMenu as am where am.parentId = 0 order by am.orderId desc", DBSource.ADMIN);
}
/**
 * 查询所有菜单生成的map
 */
public Map<Integer, List<AdminMenu>> findOneToTwoMap() {
	Map<Integer, List<AdminMenu>> reMap = new HashMap<Integer, List<AdminMenu>>();
	List<AdminMenu> list = findAllList();
	List<AdminMenu> tempList = null;
	for (AdminMenu am:list) {
           //放入自己的父IDMap中  没有则生成
			if (!reMap.containsKey(am.getParentId())) {
				tempList = new ArrayList<AdminMenu>();
				tempList.add(am);
                reMap.put(am.getParentId(), tempList);
			} else {
				tempList = reMap.get(am.getParentId());
				tempList.add(am);
			}
}
	return reMap;
}
/**
 * 查询所有菜单
 * @return
 */
public List<AdminMenu> findAllList() {
	return adminMenuDao.find("from AdminMenu am order by am.orderId desc", DBSource.ADMIN);
}
/**
 * 获得最大权限ID
 */
public Integer findMaxPowerId() {
	return adminMenuDao.findMaxPowerId();
}

/**
 * 获取一个类别下最高权限值
 */
public Integer findMaxOrderId(Integer parentId) {
	return adminMenuDao.findMaxOrderId(parentId);
}
/**
 * 获取一个类别下最高权限值
 */
public Integer findMinOrderId(Integer parentId) {
	return adminMenuDao.findMinOrderId(parentId);
}
}
