package com.fantingame.game.mywar.logic.mail.dao.impl.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.mail.dao.SystemMailDao;
import com.fantingame.game.mywar.logic.mail.dao.impl.mysql.SystemMailDaoMysqlImpl;
import com.fantingame.game.mywar.logic.mail.model.SystemMail;

public class SystemMailDaoCacheImpl extends BaseCacheDao<SystemMail> implements SystemMailDao {

	@Autowired
	private SystemMailDaoMysqlImpl systemMailDaoMysqlImpl;
	
	public SystemMailDaoMysqlImpl getSystemMailDaoMysqlImpl() {
		return systemMailDaoMysqlImpl;
	}

	public void setSystemMailDaoMysqlImpl(SystemMailDaoMysqlImpl systemMailDaoMysqlImpl) {
		this.systemMailDaoMysqlImpl = systemMailDaoMysqlImpl;
	}

	@Override
	protected SystemMail loadFromDb(String key) {
		return this.systemMailDaoMysqlImpl.getSystemMail(key);
	}
	
	public List<SystemMail> getSystemMailByTime(Date date) {
		List<SystemMail> systemMailList = this.systemMailDaoMysqlImpl.getListAll();
		List<SystemMail> list = new ArrayList<SystemMail>();
		for (SystemMail systemMail : systemMailList) {
			if (date == null || systemMail.getCreatedTime().after(date))
				list.add(systemMail);
		}
		
		return list;
	}
	
	public boolean add(SystemMail systemMail) {
		if (this.systemMailDaoMysqlImpl.add(systemMail)) {
			super.addEntry(systemMail.getSystemMailId(), systemMail);
			return true;
		}
		
		return false;
	}

	@Override
	public SystemMail getSystemMail(String systemMailId) {
		return super.getEntry(systemMailId);
	}
}
