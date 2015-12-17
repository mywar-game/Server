package com.dataconfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.SystemTool;
import com.dataconfig.bo.TTreasureConstant;
import com.dataconfig.dao.SystemToolDao;
import com.dataconfig.dao.TTreasureConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class TTreasureConstantService {

	private TTreasureConstantDao tTreasureConstantDao;
	private SystemToolDao systemToolDao;

	/**
	 * 新增道具常量数据
	 * 
	 * @param tTreasureConstant
	 */
	public void addTTreasureConstant(TTreasureConstant tTreasureConstant) {
		tTreasureConstantDao.save(tTreasureConstant, DBSource.CFG);
	}

	/**
	 * 删除道具常量数据
	 * 
	 * @param treasureId
	 */
	public void delTTreasureConstant(Integer treasureId) {
		tTreasureConstantDao.remove(findOneTTreasureConstant(treasureId),
				DBSource.CFG);
	}

	/**
	 * 修改道具常量数据
	 * 
	 * @param tTreasureConstant
	 */
	public void updateOneTTreasureConstant(TTreasureConstant tTreasureConstant) {
		tTreasureConstantDao.update(tTreasureConstant, DBSource.CFG);
	}

	/**
	 * 查询道具常量数据
	 * 
	 * @param treasureId
	 * @return
	 */
	public TTreasureConstant findOneTTreasureConstant(Integer treasureId) {
		return this.tTreasureConstantDao.loadBy("treasureId", treasureId,
				DBSource.CFG);
	}

	/**
	 * 查询道具常量分页列表
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<TTreasureConstant> findTTreasureConstantPageList(Integer id,
			String name, Integer currentPage, Integer pageSize) {
		String str = "from TTreasureConstant";
		if (id != null) {
			str = "from SystemTool c where c.toolId = " + id;
		}
		if (name != null && !"".equals(name)) {
			str = "from SystemTool c where c.name like '%" + name + "%'";
		}
		System.out.println(" === " + str);
		tTreasureConstantDao.closeSession(DBSource.CFG);
		return tTreasureConstantDao.findPage(str, new ArrayList<Object>(),
				pageSize, currentPage);
	}

	/**
	 * 查询道具常量列表
	 * 
	 * @return
	 */
	public List<TTreasureConstant> findTTreasureConstantList() {
		tTreasureConstantDao.closeSession(DBSource.CFG);
		return tTreasureConstantDao.findAll();
	}

	/**
	 * 查询所有道具常量的ID和name对应的map
	 * 
	 * @return
	 */
	public Map<Integer, String> findTreasureIdNameMap() {

		Map<Integer, String> treasureIDNameMap = new LinkedHashMap<Integer, String>();
		systemToolDao.closeSession(DBSource.CFG);
		List<Object> list = systemToolDao
				.findSQL_("SELECT tool_id, name FROM system_tool");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int treasureId = Integer.parseInt(((Object[]) list.get(i))[0]
						.toString());
				String treasureName = ((Object[]) list.get(i))[1].toString();
				treasureIDNameMap.put(treasureId, treasureName);
			}
		}
		return treasureIDNameMap;

	}

	/**
	 * 获取道具列表
	 * 
	 * @return
	 */
	public List<SystemTool> getToolList() {
		return systemToolDao.find("from SystemTool ", DBSource.CFG);
	}

	/**
	 * 查询所有商城道具常量的ID和name对应的map
	 * 
	 * @return
	 */
	public Map<Integer, String> findMallTreasureIdNameMap() {

		Map<Integer, String> treasureIDNameMap = new LinkedHashMap<Integer, String>();
		systemToolDao.closeSession(DBSource.CFG);
		List<Object> list = systemToolDao
				.findSQL_("SELECT mall_id, name FROM system_mall");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int treasureId = Integer.parseInt(((Object[]) list.get(i))[0]
						.toString());
				String treasureName = ((Object[]) list.get(i))[1].toString();
				treasureIDNameMap.put(treasureId, treasureName);
			}
		}
		return treasureIDNameMap;

	}

	/**
	 * 查询商城道具的名字、单价map
	 * 
	 * @param nameMap
	 * @param priceMap
	 */
	public void findMallTreasureIdNamePriceMap(Map<Integer, String> nameMap,
			Map<Integer, Integer> priceMap) {
		systemToolDao.closeSession(DBSource.CFG);
		List<Object> list = systemToolDao
				.findSQL_("SELECT mall_id, name, amount FROM system_mall");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int treasureId = Integer.parseInt(((Object[]) list.get(i))[0]
						.toString());
				String treasureName = ((Object[]) list.get(i))[1].toString();
				Integer price = Integer.valueOf(((Object[]) list.get(i))[2]
						.toString());
				nameMap.put(treasureId, treasureName);
				priceMap.put(treasureId, price);
			}
		}
	}

	/**
	 * 根据条件查到的ID和name对应的map
	 * 
	 * @return
	 */
	public Map<Integer, String> findTreasureIdNameMapByCondition(Integer id,
			String name) {
		Map<Integer, String> treasureIdNameMap = new LinkedHashMap<Integer, String>();
		StringBuffer sb = new StringBuffer();
		if (id != null) {
			sb.append("SELECT tool_id, name FROM system_tool WHERE tool_id = "
					+ id);
		}
		if (name != null && !"".equals(name)) {
			sb.append("SELECT tool_id, name FROM system_tool WHERE name LIKE '%"
					+ name + "%'");
		}
		if (sb.length() == 0) {
			sb.append("SELECT tool_id, name FROM system_tool");
		}
		systemToolDao.closeSession(DBSource.CFG);
		List<Object> list = systemToolDao.findSQL_(sb.toString());
		for (int i = 0; i < list.size(); i++) {
			Integer treasureId = Integer.parseInt(((Object[]) list.get(i))[0]
					.toString());
			String treasureName = ((Object[]) list.get(i))[1].toString();
			treasureIdNameMap.put(treasureId, treasureName);
		}
		return treasureIdNameMap;
	}
	
	public Map<Integer, String> findSystemHeroIdNameMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		systemToolDao.closeSession(DBSource.CFG);
		List<Object> list = systemToolDao.findSQL_("select system_hero_id, hero_name from system_hero");
		for (int i = 0; i < list.size(); i++) {
			Integer id = Integer.parseInt(((Object[]) list.get(i))[0]
					.toString());
			String name = ((Object[]) list.get(i))[1].toString();
			map.put(id, name);
		}
		return map;
	}
	
	public Map<Integer, String> findSystemEquipIdNameMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		systemToolDao.closeSession(DBSource.CFG);
		List<Object> list = systemToolDao.findSQL_("select equip_id, name from system_equip");
		for (int i = 0; i < list.size(); i++) {
			Integer id = Integer.parseInt(((Object[]) list.get(i))[0]
					.toString());
			String name = ((Object[]) list.get(i))[1].toString();
			map.put(id, name);
		}
		return map;
	}

	public Map<Integer, String> findSystemArtifactIdNameMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		systemToolDao.closeSession(DBSource.CFG);
		List<Object> list = systemToolDao.findSQL_("select artifact_id, name from system_artifact");
		for (int i = 0; i < list.size(); i++) {
			Integer id = Integer.parseInt(((Object[]) list.get(i))[0]
					.toString());
			String name = ((Object[]) list.get(i))[1].toString();
			map.put(id, name);
		}
		return map;
	}
	
	/**
	 * 查询某种类型的道具的ID和name对应的map
	 * 
	 * @return
	 */
	public Map<Integer, String> findTypeTreasureIdNameMap(int type) {
		Map<Integer, String> treasureIDNameMap = new LinkedHashMap<Integer, String>();
		List<TTreasureConstant> treasureConstantList = this
				.findTTreasureConstantList();
		for (TTreasureConstant treasure : treasureConstantList) {
			if (treasure.getTreasureType() == type) {
				treasureIDNameMap.put(treasure.getTreasureId(),
						treasure.getTreasureName());
			}
		}
		return treasureIDNameMap;
	}

	/**
	 * 查询所有道具常量的ID和道具对应的map
	 * 
	 * @return
	 */
	public Map<Integer, TTreasureConstant> findIdTreasureMap() {
		Map<Integer, TTreasureConstant> idTreasureMap = new LinkedHashMap<Integer, TTreasureConstant>();
		List<TTreasureConstant> treasureList = this.findTTreasureConstantList();
		for (TTreasureConstant treasure : treasureList) {
			idTreasureMap.put(treasure.getTreasureId(), treasure);
		}
		return idTreasureMap;
	}

	/**
	 * 查询某type的道具常量的ID和道具对应的map
	 * 
	 * @return
	 */
	public Map<Integer, TTreasureConstant> findIdTreasureMapByType(int type) {
		Map<Integer, TTreasureConstant> idTreasureMap = new LinkedHashMap<Integer, TTreasureConstant>();
		List<TTreasureConstant> treasureList = this.findTTreasureConstantList();
		for (TTreasureConstant treasure : treasureList) {
			if (treasure.getTreasureType() == type) {
				idTreasureMap.put(treasure.getTreasureId(), treasure);
			}
		}
		return idTreasureMap;
	}

	public SystemToolDao getSystemToolDao() {
		return systemToolDao;
	}

	public void setSystemToolDao(SystemToolDao systemToolDao) {
		this.systemToolDao = systemToolDao;
	}

	public TTreasureConstantDao gettTreasureConstantDao() {
		return tTreasureConstantDao;
	}

	public void settTreasureConstantDao(
			TTreasureConstantDao tTreasureConstantDao) {
		this.tTreasureConstantDao = tTreasureConstantDao;
	}
}