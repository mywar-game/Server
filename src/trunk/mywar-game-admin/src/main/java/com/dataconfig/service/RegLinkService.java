package com.dataconfig.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.admin.util.Tools;
import com.dataconfig.bo.RegLink;
import com.dataconfig.dao.RegLinkDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class RegLinkService {
	
	private RegLinkDao regLinkDao;
	
	public void save(RegLink regLink) {
		regLinkDao.save(regLink, DBSource.CFG);
	}
	
	public IPage<RegLink> findPageList(String searchCode, Date createStartTime, Date createEndTime, int pageSize, int pageIndex) {
		StringBuffer hql = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		hql.append("from RegLink where 1=1");
		if (!Tools.isEmpty(searchCode)) {
			hql.append(" and regLinkId = ?");
			args.add(searchCode);
		}
		if (createStartTime != null && createEndTime != null) {
			hql.append(" and createTime between ? and ?");
			args.add(createStartTime);
			args.add(createEndTime);
		}
		regLinkDao.closeSession(DBSource.CFG);
		return regLinkDao.findPage(hql.toString(), args, pageSize, pageIndex);
	}

	public void setRegLinkDao(RegLinkDao regLinkDao) {
		this.regLinkDao = regLinkDao;
	}

	public RegLinkDao getRegLinkDao() {
		return regLinkDao;
	}

}
