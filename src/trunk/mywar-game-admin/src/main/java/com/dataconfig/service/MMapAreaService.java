package com.dataconfig.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.MMapArea;
import com.dataconfig.dao.MMapAreaDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class MMapAreaService {
	
	private MMapAreaDao mMapAreaDao;
	
	/**
	 * 新增地图常量数据
	 * @param mMapArea
	 */
	public void addMMapArea(MMapArea mMapArea) {
		mMapAreaDao.save(mMapArea, DBSource.CFG);
	}
	
	/**
	 * 删除地图常量数据
	 * @param areaId
	 */
	public void delMMapArea(Integer areaId) {
		mMapAreaDao.remove(findOneMMapArea(areaId), DBSource.CFG);
	}
	
	/**
	 * 修改地图常量数据
	 * @param mMapArea
	 */
	public void updateOneMMapArea(MMapArea mMapArea) {
		mMapAreaDao.update(mMapArea, DBSource.CFG);
	}

	/**
	 * 查询地图常量数据
	 * @param areaId
	 * @return
	 */
	public MMapArea findOneMMapArea(Integer areaId) {
		return this.mMapAreaDao.loadBy("areaId", areaId, DBSource.CFG);
	}
	
	/**
	 * 查询地图常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<MMapArea> findMMapAreaList(Integer currentPage, Integer pageSize) {
		mMapAreaDao.closeSession(DBSource.CFG);
		return mMapAreaDao.findPage("from MMapArea", new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 *  查询所有地图区域的ID和name对应的map
	 * @return
	 */
	public Map<Integer, String> findMapAreaIdNameMap() {
		Map<Integer, String> mapAreaIdNameMap = new LinkedHashMap<Integer, String>();
		mMapAreaDao.closeSession(DBSource.CFG);
		List<Object> list = mMapAreaDao.findSQL_("SELECT AREA_ID,AREA_NAME FROM m_map_area");
		for (int i = 0; i < list.size(); i++) {
			Integer areaId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
			String areaName = ((Object[]) list.get(i))[1].toString();
			mapAreaIdNameMap.put(areaId, areaName);
		}
		return mapAreaIdNameMap;
	}
	
	public Map<Integer, String> findUserLevelIntervalAreaNamesMap() {
		Map<Integer, String> userLevelIntervalAreaNamesMap = new LinkedHashMap<Integer, String>();
		mMapAreaDao.closeSession(DBSource.CFG);
		List<Object> list = mMapAreaDao.findSQL_("SELECT MAX_LEVEL,AREA_NAME FROM m_map_area");
		for (int i = 0; i < list.size(); i++) {
			Integer levelInterval = Integer.parseInt(((Object[]) list.get(i))[0].toString()) / 10;
			String areaName = ((Object[]) list.get(i))[1].toString();
			if (userLevelIntervalAreaNamesMap.get(levelInterval) != null) {
				userLevelIntervalAreaNamesMap.put(levelInterval, userLevelIntervalAreaNamesMap.get(levelInterval) + "," + areaName);
			} else {
				userLevelIntervalAreaNamesMap.put(levelInterval, areaName);
			}
		}
		return userLevelIntervalAreaNamesMap;
	}

	public MMapAreaDao getmMapAreaDao() {
		return mMapAreaDao;
	}

	public void setmMapAreaDao(MMapAreaDao mMapAreaDao) {
		this.mMapAreaDao = mMapAreaDao;
	}
}