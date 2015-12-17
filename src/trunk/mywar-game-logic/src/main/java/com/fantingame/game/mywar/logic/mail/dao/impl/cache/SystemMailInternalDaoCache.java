package com.fantingame.game.mywar.logic.mail.dao.impl.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.mail.model.SystemMailInternal;

/**
 * 系统内部邮件
 * 
 * @author yezp
 */
public class SystemMailInternalDaoCache extends StaticDataDaoBaseT<Integer, SystemMailInternal> {

	@Override
	protected Integer getCacheKey(SystemMailInternal v) {
		return v.getInternalMailId();
	}

	public SystemMailInternal getMail(int mailId) {
		return super.getValue(mailId);
	}
	
}
