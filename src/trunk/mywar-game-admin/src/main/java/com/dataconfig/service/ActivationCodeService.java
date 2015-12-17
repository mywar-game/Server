package com.dataconfig.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.util.Tools;
import com.dataconfig.bo.AActivationCode;
import com.dataconfig.dao.ActivationCodeDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class ActivationCodeService {
	
	private ActivationCodeDao activationCodeDao;
	
	public void save(List<AActivationCode> list) {
		activationCodeDao.saveBatch(list, DBSource.CFG);
	}
	
	public IPage<AActivationCode> findPageList(String searchPlatform, String searchCode, Date createStartTime, Date createEndTime, int pageSize, int pageIndex) {
		StringBuffer hql = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		hql.append("from AActivationCode where 1=1");
		if (!Tools.isEmpty(searchPlatform)) {
			hql.append(" and platform = ?");
			args.add(searchPlatform);
		}
		if (!Tools.isEmpty(searchCode)) {
			hql.append(" and activationCode = ?");
			args.add(searchCode);
		}
		if (createStartTime != null && createEndTime != null) {
			hql.append(" and createTime between ? and ?");
			args.add(createStartTime);
			args.add(createEndTime);
		}
		hql.append(" order by createTime desc");
		activationCodeDao.closeSession(DBSource.CFG);
		return activationCodeDao.findPage(hql.toString(), args, pageSize, pageIndex);
	}

	public Map<String, String> findPlatformAndCodesMap() {
		Map<String, String> map = new HashMap<String, String>();
		List<AActivationCode> list = activationCodeDao.find(" from AActivationCode order by platform", DBSource.CFG);
		if (list != null && list.size()>0) {
			for (AActivationCode activationCode : list) {
				String code = "'" + activationCode.getActivationCode() + "'";
				String platform = activationCode.getPlatform();
				if (platform == null) {
					platform = "";
				}
				platform = platform.trim();
				if (map.get(platform) == null) {
					map.put(platform, code);
				} else {
					map.put(platform, map.get(platform) + "," + code);
				}
			}
		}
		return map;
	}
	
	public void setActivationCodeDao(ActivationCodeDao activationCodeDao) {
		this.activationCodeDao = activationCodeDao;
	}

	public ActivationCodeDao getActivationCodeDao() {
		return activationCodeDao;
	}

}
