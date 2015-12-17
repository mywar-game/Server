package com.adminTool.service;

import java.util.ArrayList;

import com.adminTool.bo.MallAudit;
import com.adminTool.bo.MallDiscount;
import com.adminTool.dao.MallAuditDao;
import com.framework.common.IPage;

public class MallAuditService {

	private MallAuditDao mallAuditDao;
	
	public IPage<MallAudit> findMallAuditList(Integer currentPage, Integer pageSize) {
		return mallAuditDao.findPage("from MallAudit", new ArrayList<Object>(), pageSize, currentPage);
	}

	public MallAuditDao getMallAuditDao() {
		return mallAuditDao;
	}

	public void setMallAuditDao(MallAuditDao mallAuditDao) {
		this.mallAuditDao = mallAuditDao;
	}
	
	
}
