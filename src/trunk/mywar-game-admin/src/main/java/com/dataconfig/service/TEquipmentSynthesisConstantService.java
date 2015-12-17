package com.dataconfig.service;

import java.util.ArrayList;
import java.util.List;

import com.dataconfig.bo.TEquipmentSynthesisConstant;
import com.dataconfig.dao.TEquipmentSynthesisConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class TEquipmentSynthesisConstantService {
	
	private TEquipmentSynthesisConstantDao tEquipmentSynthesisConstantDao;
	
	/**
	 * 新增装备常量数据
	 * @param tEquipmentSynthesisConstant
	 */
	public void addTEquipmentSynthesisConstant(TEquipmentSynthesisConstant tEquipmentSynthesisConstant) {
		tEquipmentSynthesisConstantDao.save(tEquipmentSynthesisConstant, DBSource.CFG);
	}
	
	/**
	 * 删除装备常量数据
	 * @param equipmentId
	 */
	public void delTEquipmentSynthesisConstant(int equipmentId) {
		tEquipmentSynthesisConstantDao.remove(findOneTEquipmentSynthesisConstant(equipmentId), DBSource.CFG);
	}
	
	/**
	 * 修改装备常量数据
	 * @param tEquipmentSynthesisConstant
	 */
	public void updateOneTEquipmentSynthesisConstant(TEquipmentSynthesisConstant tEquipmentSynthesisConstant) {
		tEquipmentSynthesisConstantDao.update(tEquipmentSynthesisConstant, DBSource.CFG);
	}

	/**
	 * 查询装备常量数据
	 * @param equipmentId
	 * @return
	 */
	public TEquipmentSynthesisConstant findOneTEquipmentSynthesisConstant(int equipmentId) {
		return this.tEquipmentSynthesisConstantDao.loadBy("equipmentId", equipmentId, DBSource.CFG);
	}
	
	/**
	 * 查询装备常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<TEquipmentSynthesisConstant> findTEquipmentSynthesisConstantPageList(Integer currentPage, Integer pageSize) {
		tEquipmentSynthesisConstantDao.closeSession(DBSource.CFG);
		return tEquipmentSynthesisConstantDao.findPage("select  new TEquipmentSynthesisConstant(te.equipmentId ,ee.equipmentName ,te.buildMeterials1 ,te.buildMeterials2 ,te.buildMeterials3) from TEquipmentSynthesisConstant te , EEquipmentConstant ee WHERE te.equipmentId = ee.equipmentId", new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 查询装备常量列表
	 * @return
	 */
	public  List<TEquipmentSynthesisConstant> findTEquipmentSynthesisConstantList() {
		tEquipmentSynthesisConstantDao.closeSession(DBSource.CFG);
		return tEquipmentSynthesisConstantDao.findAll();
	}
	
	public TEquipmentSynthesisConstantDao gettEquipmentSynthesisConstantDao() {
		return tEquipmentSynthesisConstantDao;
	}

	public void settEquipmentSynthesisConstantDao(TEquipmentSynthesisConstantDao tEquipmentSynthesisConstantDao) {
		this.tEquipmentSynthesisConstantDao = tEquipmentSynthesisConstantDao;
	}
}