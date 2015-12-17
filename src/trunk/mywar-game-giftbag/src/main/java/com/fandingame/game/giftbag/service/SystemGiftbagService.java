package com.fandingame.game.giftbag.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.giftbag.constant.ServiceReturnCode;
import com.fandingame.game.giftbag.dao.impl.mysql.GiftCodeDaoMysqlImpl;
import com.fandingame.game.giftbag.dao.impl.mysql.GiftCodeLogDaoMysqlImpl;
import com.fandingame.game.giftbag.dao.impl.mysql.GiftbagDropToolDaoMysqlImpl;
import com.fandingame.game.giftbag.dao.impl.mysql.GiftbagTypeLimitDaoMysqlImpl;
import com.fandingame.game.giftbag.dao.impl.mysql.SystemGiftbagDaoMysqlImpl;
import com.fandingame.game.giftbag.model.GiftCode;
import com.fandingame.game.giftbag.model.GiftCodeLog;
import com.fandingame.game.giftbag.model.GiftbagDropTool;
import com.fandingame.game.giftbag.model.GiftbagTypeLimit;
import com.fandingame.game.giftbag.model.GoodsBeanBO;
import com.fandingame.game.giftbag.model.SystemGiftbag;

/**
 * 礼包相关业务层
 * 
 * @author yezp
 */
public class SystemGiftbagService {
	
	private static Logger logger = Logger.getLogger(SystemGiftbagService.class);

	@Autowired
	private GiftCodeDaoMysqlImpl giftCodeDao;
	@Autowired
	private SystemGiftbagDaoMysqlImpl systemGiftbagDao;
	@Autowired
	private GiftCodeLogDaoMysqlImpl giftCodeLogDao;
	@Autowired
	private GiftbagTypeLimitDaoMysqlImpl giftbagTypeLimitDao;
	@Autowired
	private GiftbagDropToolDaoMysqlImpl giftbagDropToolDao;
	
	/**
	 * 检测是否可以领取礼包码
	 * 
	 * @param userId
	 * @param level
	 * @param vipLevel
	 * @param serverId
	 * @return
	 */
	public Map<String, Object> checkGiftCode(String userId, String code, int userLevel, int userVipLevel, String serverId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String tablePostfix = code.substring(0, 1);
		
		GiftCode giftCode = this.giftCodeDao.get(tablePostfix, code);
		GiftCodeLog giftCodeLog = this.giftCodeLogDao.get(tablePostfix, code);
		
		if (giftCode == null) {
			map.put("rc", ServiceReturnCode.HAS_NOT_THIS_CODE);
			logger.info("没有这个礼包码。");
			return map;
		}
		
		if (giftCodeLog != null) {
			map.put("rc", ServiceReturnCode.REWARD_HAS_RECEIVED);
			logger.info("该礼包码已被领取。");
			return map;
		}
		
		//校验礼包吗是否在生效时间内
		Date now = new Date();
		if(now.before(giftCode.getEffectiveTime()) || now.after(giftCode.getLoseTime())) {
			String message = "领取礼包码礼包出错.还没有生效.userId[" + userId + "], code[" + code + "]";
			logger.info(message);
			map.put("rc", ServiceReturnCode.GIFTBAG_NOT_IN_EFFECTIVE_TIME);
			
			return map;
		}
		
		SystemGiftbag systemGiftbag = this.systemGiftbagDao.get(giftCode.getGiftBagId());
		GiftbagTypeLimit limitConfig = this.giftbagTypeLimitDao.get(systemGiftbag.getType());
		
		int timesLimit = 1;
		int minLevel = 1;
		int minVipLevel = 0;
		
		if (limitConfig != null) {
			String serverIds = limitConfig.getServerIds();			
			if (serverIds.indexOf(serverId) == -1) {
				String message = "领取礼包码礼包出错.礼包码对该服无效.userId[" + userId
						+ "], code[" + code + "], serverIds[" + serverIds + "], serverId[" + serverId + "]";
				logger.info(message);
				map.put("rc", ServiceReturnCode.GIFTCODE_NOT_IN_SERVER);
				return map;
			}
			
			timesLimit = limitConfig.getLimitTimes();
			minLevel = limitConfig.getMinLevel();
			minVipLevel = limitConfig.getMinVipLevel();
		} 
		
		if (userLevel < minLevel) {
			String message = "领取激活码礼包失败，等级不足.userId[" + userId + "]";
			logger.info(message);
			
			map.put("rc", ServiceReturnCode.LEVEL_NOT_ENOUGH);
			return map;
		}
		
		if (userVipLevel < minVipLevel) {
			String message = "领取激活码礼包失败，vip等级不足.userId[" + userId + "]";
			logger.info(message);
			
			map.put("rc", ServiceReturnCode.VIP_LEVEL_NOT_ENOUGH);
			return map;
		}
		
		List<GiftbagDropTool> giftBagDropToolList = this.giftbagDropToolDao.getGiftbagDropToolList(systemGiftbag.getGiftbagId());
		List<GoodsBeanBO> rewardList = new ArrayList<GoodsBeanBO>();
		for (GiftbagDropTool dropTool : giftBagDropToolList) {
			GoodsBeanBO good = new GoodsBeanBO(dropTool.getToolType(), dropTool.getToolId(), dropTool.getToolNum());
			rewardList.add(good);			
		}
		
		map.put("rc", ServiceReturnCode.SUCCESS);
		map.put("timesLimit", timesLimit);
		map.put("rewardList", rewardList);
		map.put("type", systemGiftbag.getType());
		map.put("giftbagId", giftCode.getGiftBagId());
		return map;
	}
	
	public Map<String, Object> recordGiftCodeLog(String userId, String code, int giftbagId, String partnerId, String qn, String serverId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String tablePostfix = code.substring(0, 1);
		
		// 更新为已经使用
		GiftCodeLog log = new GiftCodeLog();
		log.setCode(code);
		log.setUserId(userId);
		log.setCreatedTime(new Date());
		log.setServerId(serverId);
		log.setPartenerId(partnerId);
		log.setQn(qn);
		log.setGiftBagId(giftbagId);
					
		try {
			giftCodeLogDao.addGiftCodeLog(log, tablePostfix);
			map.put("rc", ServiceReturnCode.SUCCESS);
		} catch (Exception e) {
			String message = "领取礼包码礼包出错.礼包码无效.userId[" + userId + "], code[" + code + "]";
			logger.warn(message);
			map.put("rc", ServiceReturnCode.FAILD);
		}		
		
		return map;
	}
}
