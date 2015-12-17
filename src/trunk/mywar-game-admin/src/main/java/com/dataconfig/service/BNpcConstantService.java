package com.dataconfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.BNpcConstant;
import com.dataconfig.dao.BNpcConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class BNpcConstantService {
	
	private BNpcConstantDao bNpcConstantDao;
	
	/**
	 * 新增npc常量数据
	 * @param bNpcConstant
	 */
	public void addBNpcConstant(BNpcConstant bNpcConstant) {
		bNpcConstantDao.save(bNpcConstant, DBSource.CFG);
	}
	
	/**
	 * 删除npc常量数据
	 * @param npcId
	 */
	public void delBNpcConstant(Integer npcId) {
		bNpcConstantDao.remove(findOneBNpcConstant(npcId), DBSource.CFG);
	}
	
	/**
	 * 修改npc常量数据
	 * @param bNpcConstant
	 */
	public void updateOneBNpcConstant(BNpcConstant bNpcConstant) {
		bNpcConstantDao.update(bNpcConstant, DBSource.CFG);
	}

	/**
	 * 查询npc常量数据
	 * @param npcId
	 * @return
	 */
	public BNpcConstant findOneBNpcConstant(Integer npcId) {
		return this.bNpcConstantDao.loadBy("npcId", npcId, DBSource.CFG);
	}
	
	/**
	 * 查询npc常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<BNpcConstant> findBNpcConstantList(Integer currentPage, Integer pageSize) {
		bNpcConstantDao.closeSession(DBSource.CFG);
		return bNpcConstantDao.findPage("from BNpcConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public Map<Integer, String> findNpcIdAndNameMap() {
		Map<Integer, String> npcIdAndNameMap = new HashMap<Integer, String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select new BNpcConstant(npcId, npcName) from BNpcConstant");
		List<BNpcConstant> npcIdAndNameList = bNpcConstantDao.find(sql.toString(), DBSource.CFG);;
		if (npcIdAndNameList != null && npcIdAndNameList.size() > 0) {
			for (BNpcConstant npc : npcIdAndNameList) {
				npcIdAndNameMap.put(npc.getNpcId(), npc.getNpcName());
			}
		}
		return npcIdAndNameMap;
	}
	public BNpcConstantDao getbNpcConstantDao() {
		return bNpcConstantDao;
	}

	public void setbNpcConstantDao(BNpcConstantDao bNpcConstantDao) {
		this.bNpcConstantDao = bNpcConstantDao;
	}
}