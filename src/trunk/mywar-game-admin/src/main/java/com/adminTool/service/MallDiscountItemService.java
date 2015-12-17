package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.MallDiscount;
import com.adminTool.bo.MallDiscountItem;
import com.adminTool.bo.SystemMallDiscount;
import com.adminTool.dao.MallDiscountDao;
import com.adminTool.dao.MallDiscountItemDao;
import com.adminTool.dao.SystemMallDiscountDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class MallDiscountItemService {
	
	private MallDiscountItemDao mallDiscountItemDao;
	private SystemMallDiscountDao systemMallDiscountDao;

	public List<MallDiscountItem> findMallDiscountItemList(String activityId) {
		mallDiscountItemDao.closeSession(DBSource.ADMIN);
		return mallDiscountItemDao.find("from MallDiscountItem where activityId = '"+activityId+"'", activityId);
		
	}
	
	public List<SystemMallDiscount> findAllSystemMallDiscount() {
		List<SystemMallDiscount> list1 = new ArrayList<SystemMallDiscount>();
		systemMallDiscountDao.closeSession(DBSource.CFG);
		List<Object> list = systemMallDiscountDao.findSQL_("select mall_id, name, amount, money_type from system_mall");
		for (Object obj : list) {
			Object[] objArr = (Object[]) obj;
			int mallId = (Integer) objArr[0];
			String name = (String) objArr[1];
			int amount = (Integer) objArr[2];
			int amountType = Integer.valueOf((Byte) objArr[3]);
			
			SystemMallDiscount s = new SystemMallDiscount();
			s.setMallId(mallId);
			s.setOriginalPrice(amount);
			s.setName(name);
			s.setAmountType(amountType);
			list1.add(s);
		}
		return list1;
	}

	public List<MallDiscountItem> findAllMallDiscountItem() {
		mallDiscountItemDao.closeSession(DBSource.ADMIN);
		return mallDiscountItemDao.findAll();
	}
	
	public IPage<SystemMallDiscount> findSystemMallDiscountList(Integer currentPage, Integer pageSize) {
		systemMallDiscountDao.closeSession(DBSource.ADMIN);
		return systemMallDiscountDao.findPage("from SystemMallDiscount", new ArrayList<Object>(), pageSize, currentPage);
	}
	
	public SystemMallDiscountDao getSystemMallDiscountDao() {
		systemMallDiscountDao.closeSession(DBSource.ADMIN);
		return systemMallDiscountDao;
	}

	public void addMallDiscountItem(List<MallDiscountItem> itemList) {
		mallDiscountItemDao.closeSession(DBSource.ADMIN);
		mallDiscountItemDao.saveBatch(itemList, DBSource.ADMIN);
	}
	
	public void updateMallDiscountItem(MallDiscountItem discountItem) {
		mallDiscountItemDao.update(discountItem, DBSource.ADMIN);
	}
	
	public void setSystemMallDiscountDao(SystemMallDiscountDao systemMallDiscountDao) {
		this.systemMallDiscountDao = systemMallDiscountDao;
	}

	public void setMallDiscountItemDao(MallDiscountItemDao mallDiscountItemDao) {
		this.mallDiscountItemDao = mallDiscountItemDao;
	}
	

	public void deleteMallDiscountItems(String activityId) {
		mallDiscountItemDao.closeSession(DBSource.ADMIN);
		String query = "delete from MallDiscountItem as mdi where mdi.activityId = '" + activityId + "'";
		mallDiscountItemDao.execute(query);
	}
}
