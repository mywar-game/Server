package com.fantingame.game.gamecenter.dao.impl.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fantingame.game.gamecenter.dao.PartnerRateDao;
import com.fantingame.game.gamecenter.dao.impl.mysql.PartnerRateDaoMysqlImpl;
import com.fantingame.game.gamecenter.model.PartnerRate;

public class PartnerRateDaoCacheImpl implements PartnerRateDao {

	private Map<String, Integer> map = new HashMap<String, Integer>();
	
	private PartnerRateDaoMysqlImpl partnerRateDaoMysqlImpl;

	public PartnerRateDaoMysqlImpl getPartnerRateDaoMysqlImpl() {
		return partnerRateDaoMysqlImpl;
	}

	public void setPartnerRateDaoMysqlImpl(
			PartnerRateDaoMysqlImpl partnerRateDaoMysqlImpl) {
		this.partnerRateDaoMysqlImpl = partnerRateDaoMysqlImpl;
	}

	private void init() {
		List<PartnerRate> rateList = partnerRateDaoMysqlImpl.getAll();
		map.clear();
		for (PartnerRate partnerRate : rateList) {
			map.put(partnerRate.getPartnerId(), partnerRate.getRate());
		}		
	}

	@Override
	public List<PartnerRate> getAll() {
		return partnerRateDaoMysqlImpl.getAll();
	}	
	
	@Override
	public void reload() {
		init();
	}

	@Override
	public int getRate(String partnerId) {
		if (map.containsKey(partnerId))
			return map.get(partnerId);
		
		int rate = this.partnerRateDaoMysqlImpl.getRate(partnerId);
		map.put(partnerId, rate);
		return rate;
	}
	
}
