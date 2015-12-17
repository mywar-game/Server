package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.TestUser;
import com.adminTool.dao.TestUserDao;
import com.framework.common.DBSource;
import com.framework.servicemanager.CustomerContextHolder;

public class TestUserService {
	
	private TestUserDao testUserDao;
	
	/**
	 * 插入测试用户数据
	 * @param testUser
	 */
	public void createTestUser(TestUser testUser){
		testUserDao.save(testUser, DBSource.ADMIN);
	}

	/**
	 * 查询所有测试用户数据
	 * @return
	 */
	public List<TestUser> getTestUserList(){
		return testUserDao.find(" from TestUser where sysNum = "+CustomerContextHolder.getSystemNum()+" order by createTime desc", new ArrayList<Object>());
	}

	public void setTestUserDao(TestUserDao testUserDao) {
		this.testUserDao = testUserDao;
	}

	public TestUserDao getTestUserDao() {
		return testUserDao;
	}

}
