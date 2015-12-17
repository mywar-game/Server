package com.fantingame.game.mywar.logic.legion.dao.cache;

import java.util.Date;
import java.util.List;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.legion.dao.LegionInfoDao;
import com.fantingame.game.mywar.logic.legion.dao.mysql.LegionInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.legion.model.LegionInfo;

public class LegionInfoDaoCacheImpl extends BaseCacheDao<LegionInfo> implements LegionInfoDao {

	private LegionInfoDaoMysqlImpl legionInfoDaoMysqlImpl;
	
	public LegionInfoDaoMysqlImpl getLegionInfoDaoMysqlImpl() {
		return legionInfoDaoMysqlImpl;
	}

	public void setLegionInfoDaoMysqlImpl(LegionInfoDaoMysqlImpl legionInfoDaoMysqlImpl) {
		this.legionInfoDaoMysqlImpl = legionInfoDaoMysqlImpl;
	}

	@Override
	protected LegionInfo loadFromDb(String legionId) {
		LegionInfo legionInfo = this.legionInfoDaoMysqlImpl.getLegionInfo(legionId);
		if (legionInfo != null)
			super.addEntry(legionId, legionInfo);
		
		return legionInfo;
	}

	@Override
	public LegionInfo getLegionInfo(String legionId) {
		return super.getEntry(legionId);
	}

	@Override
	public boolean isExitLegionName(String legionName) {		
		return this.legionInfoDaoMysqlImpl.isExitLegionName(legionName);
	}

	@Override
	public boolean addLegionInfo(LegionInfo legionInfo) {
		if (this.legionInfoDaoMysqlImpl.addLegionInfo(legionInfo)) {
			super.addEntry(legionInfo.getLegionId(), legionInfo);
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<LegionInfo> getLegionInfoList() {
		return this.legionInfoDaoMysqlImpl.getLegionInfoList();
	}

	@Override
	public boolean updateLegionNotice(String legionId, String notice) {
		if (this.legionInfoDaoMysqlImpl.updateLegionNotice(legionId, notice)) {
			LegionInfo legionInfo = super.getEntry(legionId);
			legionInfo.setNotice(notice);
			legionInfo.setUpdatedTime(new Date());
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateLegionDeclaration(String legionId, String declaration) {
		if (this.legionInfoDaoMysqlImpl.updateLegionDeclaration(legionId, declaration)) {
			LegionInfo legionInfo = super.getEntry(legionId);
			legionInfo.setDeclaration(declaration);
			legionInfo.setUpdatedTime(new Date());
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateLegionPower(String legionId, int power) {
		if (this.legionInfoDaoMysqlImpl.updateLegionPower(legionId, power)) {
			LegionInfo legionInfo = super.getEntry(legionId);
			legionInfo.setPower(power);
			legionInfo.setUpdatedTime(new Date());
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteLegionInfo(String legionId) {
		if (this.legionInfoDaoMysqlImpl.deleteLegionInfo(legionId)) {
			super.delete(legionId);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateLevelAndExp(String legionId, int level, int addExp) {
		if (this.legionInfoDaoMysqlImpl.updateLevelAndExp(legionId, level, addExp)) {
			LegionInfo legionInfo = super.getEntry(legionId);
			legionInfo.setLevel(level);
			legionInfo.setExp(legionInfo.getExp() + addExp);
			legionInfo.setUpdatedTime(new Date());
			
			return true;
		}		
		
		return false;
	}

}
