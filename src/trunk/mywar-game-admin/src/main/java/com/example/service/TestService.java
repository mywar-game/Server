package com.example.service;

import com.example.bo.Test;
import com.example.dao.TestDao;

public class TestService {
private TestDao testDao;

public TestDao getTestDao() {
	return testDao;
}

public void setTestDao(TestDao testDao) {
	this.testDao = testDao;
}

public void addTestService() {
	Test test = new Test();
	test.setName("good");
	testDao.saveUnsyn(test, -1);
}


}
