package com.dataconfig.service;

import java.util.ArrayList;
import java.util.List;
import com.dataconfig.bo.EEquipmentConstant;
import com.dataconfig.bo.TMallTreasureConstant;
import com.dataconfig.bo.TMallTreasureConstantId;
import com.dataconfig.bo.TTreasureConstant;
import com.dataconfig.constant.TMallTreasureTypeConstant;
import com.dataconfig.dao.TMallTreasureConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class TMallTreasureConstantService {
	
	private TMallTreasureConstantDao tMallTreasureConstantDao;
	
	public TMallTreasureConstantDao gettMallTreasureConstantDao() {
		return tMallTreasureConstantDao;
	}

	public void settMallTreasureConstantDao(
			TMallTreasureConstantDao tMallTreasureConstantDao) {
		this.tMallTreasureConstantDao = tMallTreasureConstantDao;
	}

	/**
	 * 新增商城购买道具或装备
	 * @param tMallTreasureConstant
	 */
	public void addTMallTreasureConstant(TMallTreasureConstant tMallTreasureConstant) {
		tMallTreasureConstantDao.save(tMallTreasureConstant, DBSource.CFG);
	}
	
	/**
	 * 删除商城道具或装备
	 * @param treasureId
	 */
	public void delTMallTreasureConstant(TMallTreasureConstantId treasureId) {
		tMallTreasureConstantDao.remove(findOneTMallTreasureConstant(treasureId), DBSource.CFG);
	}
	
	/**
	 * 修改商城道具或装备信息
	 * @param tMallTreasureConstant
	 */
	public void updateTMallTreasureConstant(TMallTreasureConstant tMallTreasureConstant) {
		tMallTreasureConstantDao.update(tMallTreasureConstant, DBSource.CFG);
	}
	
	/**
	 * 查询商城道具信息
	 * @return 商城道具列表
	 */
	public IPage<TMallTreasureConstant> findAllTMallTreasureConstant(Integer currentPage, Integer pageSize) {
		tMallTreasureConstantDao.closeSession(DBSource.CFG);
		IPage<TMallTreasureConstant> tMallTreasureConstanttList = tMallTreasureConstantDao.findPage("select new TMallTreasureConstant(tm.id.treasureId,tm.id.category,tm.type,tm.originalPrice,tm.price,tm.canArenaBuy,tm.needArenaScore,tm.lastTime,tm.state,tt.treasureName) "
																 + "from TMallTreasureConstant tm , TTreasureConstant tt where tm.id.treasureId = tt.treasureId "
																 + "and tm.id.category = " + TMallTreasureTypeConstant.TMALL_TREASURE_TYPE, new ArrayList<Object>(), pageSize, currentPage);
		return tMallTreasureConstanttList;
	}
	
	
	/**
	 * 查询商城装备信息
	 * @return 商城装备列表
	 */
	public IPage<TMallTreasureConstant> findAllTMallTreasureConstantForEquipment(Integer currentPage, Integer pageSize) {
		tMallTreasureConstantDao.closeSession(DBSource.CFG);
		IPage<TMallTreasureConstant> tMallTreasureConstanttList = tMallTreasureConstantDao.findPage("select new TMallTreasureConstant(tm.id.treasureId,tm.id.category,tm.type,tm.originalPrice,tm.price,tm.canArenaBuy,tm.needArenaScore,tm.lastTime,tm.state,ee.equipmentName) "
																 + "from TMallTreasureConstant tm , EEquipmentConstant ee where tm.id.treasureId = ee.equipmentId "
																 + "and tm.id.category = " + TMallTreasureTypeConstant.TMALL_EQUIPMENT_TYPE, new ArrayList<Object>(), pageSize, currentPage);
		return tMallTreasureConstanttList;
	}
	
	/**
	 * 查询商城某个道具或者装备信息
	 * @return 商城道具信息
	 */
	public TMallTreasureConstant findOneTMallTreasureConstant(TMallTreasureConstantId treasureId) {
		List<TMallTreasureConstant> tMallTreasureConstant = tMallTreasureConstantDao.find("FROM TMallTreasureConstant tm where tm.id.treasureId = " + treasureId.getTreasureId() + " and " + "tm.id.category = " + treasureId.getCategory(), DBSource.CFG);
		return tMallTreasureConstant.get(0);
	}
	
	/**
	 * 查询道具信息
	 * @param treasureId 道具ID
	 * @return
	 */
	public TTreasureConstant findOneTTreasureConstant(int treasureId) {
		TTreasureConstantService tTreasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		TTreasureConstant tTreasureConstant = tTreasureConstantService.findOneTTreasureConstant(treasureId);
		return tTreasureConstant;
	}
	
	/**
	 * 查询装备信息
	 * @param equipmentId 装备ID
	 * @return
	 */
	public EEquipmentConstant findOneEEquipmentConstant(int equipmentId) {
		EEquipmentConstantService eEquipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		EEquipmentConstant eEquipmentConstant = eEquipmentConstantService.findOneEEquipmentConstant(equipmentId);
		return eEquipmentConstant;
	}
}
