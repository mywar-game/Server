package com.adminTool.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.PartnerQn;
import com.adminTool.dao.PartnerQnDao;
import com.framework.common.DBSource;

/**
 * qn Service
 * 
 * @author yezp
 */
public class PartnerQnService {

	private PartnerQnDao partnerQnDao;

	/**
	 * 查询qnMap
	 * 
	 * @return
	 */
	public Map<String, PartnerQn> findAllQnMap() {
		Map<String, PartnerQn> map = new HashMap<String, PartnerQn>();
		List<PartnerQn> qnList = getPartnerQnList();

		for (PartnerQn qn : qnList) {
			map.put(qn.getQn(), qn);
		}

		return map;
	}

	/**
	 * 获取Qn列表
	 * 
	 * @return
	 */
	public List<PartnerQn> getPartnerQnList() {
		return partnerQnDao.find("from PartnerQn", DBSource.ADMIN);
	}

	public PartnerQnDao getPartnerQnDao() {
		return partnerQnDao;
	}

	public void setPartnerQnDao(PartnerQnDao partnerQnDao) {
		this.partnerQnDao = partnerQnDao;
	}

}
