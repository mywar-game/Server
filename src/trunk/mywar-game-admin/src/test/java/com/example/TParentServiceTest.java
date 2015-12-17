package com.example;

import com.dataconfig.bo.SSkillConstant;
import com.dataconfig.service.SSkillConstantService;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class TParentServiceTest extends SessionTest {
	public void testSave() {
		int uid = 2;
		//选择数据库
//		CustomerContextHolder.setCustomerType(uid);
//		TParentService tParentService = ServiceCacheFactory.getServiceCache().getService(TParentService.class);
//		//选择表
////		TParent p1 = (TParent) SubClassFactory.getInstance(TParent.class,uid);
//		TParent p1 = (TParent) SubClassFactory.getInstance(TParent.class, uid);
//		if (p1 == null) {
//			System.out.println("还没创建实体Bean");
//			return;
//		}
//		p1.setId(uid);
//		p1.setName("ryan"+uid);
//		tParentService.AddParent(p1);
		SSkillConstantService skillConstantService = ServiceCacheFactory.getServiceCache().getService(SSkillConstantService.class);
		
		CustomerContextHolder.setSystemNum(1001);
//		DataSourceManager.getInstatnce().changeConfigDateSource(1001 + "");
		SSkillConstant constant = skillConstantService.findOneSSkillConstant(1001);
		System.out.println(constant.getSkillName());
		
		CustomerContextHolder.setSystemNum(1002);
//		DataSourceManager.getInstatnce().changeConfigDateSource(1002 + "");
		constant = skillConstantService.findOneSSkillConstant(1001);
		System.out.println(constant.getSkillName());

		
	}
	
//	public void testGet() {
//		int uid = 3;
//		//选择数据库
//		System.out.println("0:--"+CustomerContextHolder.getCustomerType());
//		CustomerContextHolder.setCustomerType(uid);
//		System.out.println("1:--"+CustomerContextHolder.getCustomerType());
//		TParentService tParentService = ServiceCacheFactory.getServiceCache().getService(TParentService.class);
//		TParent p = tParentService.getParent(uid);
//		System.out.println("2:--"+CustomerContextHolder.getCustomerType());
//		System.out.println(p.getName());
//		
//		uid = 2;
//		CustomerContextHolder.setCustomerType(uid);
//		tParentService = ServiceCacheFactory.getServiceCache().getService(TParentService.class);
//		p = tParentService.getParent(uid);
//		System.out.println("3:--"+CustomerContextHolder.getCustomerType());
//		System.out.println(p.getName());
//	}
	
	
	public static void main(String[] args) {
//		TParent p = (TParent) SubClassFactory.getInstance(TParent.class,2);
//		System.out.println(p.getClass().getName());
	}

}
