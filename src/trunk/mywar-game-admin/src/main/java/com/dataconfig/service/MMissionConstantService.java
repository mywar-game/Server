package com.dataconfig.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.MMissionConstant;
import com.dataconfig.dao.MMissionConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class MMissionConstantService {
	private MMissionConstantDao mMissionConstantDao;

	/**
	 * 查询任务常量列表
	 */
	public IPage<MMissionConstant> findMMissionConstantList(Integer id, String name, String desc, Integer currentPage, Integer pageSize) {
		StringBuffer sb = new StringBuffer();
		sb.append("from MMissionConstant t where 1 = 1");
		if (id != null) {
			sb.append(" and t.missionId = " + id);
		}
		if (name != null && !"".equals(name)) {
			sb.append(" and t.missionName like '%" + name + "%'");
		}
		if (desc != null && !"".equals(desc)) {
			sb.append(" and t.missionDesc like '%" + desc + "%'");
		}
//		System.out.println(" == "+ sb.toString());
		mMissionConstantDao.closeSession(DBSource.CFG);
		return mMissionConstantDao.findPage(sb.toString(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 任务id和任务名MAP
	 * @return
	 */
	public Map<Integer, String> findMissionIdNameMap() {
		Map<Integer, String> missionIdNameMap = new LinkedHashMap<Integer, String>();
		mMissionConstantDao.closeSession(DBSource.CFG);
		List<Object> list = mMissionConstantDao.findSQL_("SELECT MISSION_ID,MISSION_NAME FROM m_mission_constant");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int missionId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
				String missionName = ((Object[]) list.get(i))[1].toString();
				missionIdNameMap.put(missionId, missionName);
			}
		}
		return missionIdNameMap;
	}
	
	/**
	 *  根据条件查到的ID和name对应的map
	 * @return
	 */
	public Map<Integer, String> findMissionIdNameMapByCondition(Integer id, String name) {
		Map<Integer, String> missionIdNameMap = new LinkedHashMap<Integer, String>();
		StringBuffer sb = new StringBuffer();
		if (id != null) {
			sb.append("SELECT MISSION_ID, MISSION_NAME FROM m_mission_constant WHERE MISSION_ID = " + id);
		}
		if (name != null && !"".equals(name)) {
			sb.append("SELECT MISSION_ID, MISSION_NAME FROM m_mission_constant WHERE MISSION_NAME LIKE '%" + name + "%'");
		}
		if (sb.length() == 0) {
			sb.append("SELECT MISSION_ID, MISSION_NAME FROM m_mission_constant");
		}
		mMissionConstantDao.closeSession(DBSource.CFG);
		List<Object> list = mMissionConstantDao.findSQL_(sb.toString());
		for (int i = 0; i < list.size(); i++) {
			Integer missionId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
			String missionName = ((Object[]) list.get(i))[1].toString();
			missionIdNameMap.put(missionId, missionName);
		}
		return missionIdNameMap;
	}
	
	/**
	 * 查询任务常量数据
	 */
	public MMissionConstant findOneMMissionConstant(Integer missionId) {
		return this.mMissionConstantDao.loadBy("missionId", missionId, DBSource.CFG);
	}
	
	/**
	 * 新增任务常量数据
	 */
	public void addMMissionConstant(MMissionConstant mMissionConstant) {
		mMissionConstantDao.save(mMissionConstant, DBSource.CFG);
	}
	
	/**
	 * 删除任务常量数据
	 */
	public void delMMissionConstant(Integer missionId) {
		this.mMissionConstantDao.remove(this.findOneMMissionConstant(missionId), DBSource.CFG);
	}
	
	/**
	 * 更新任务常量数据
	 */
	public void updateOneMMissionConstant(MMissionConstant mMissionConstant) {
		mMissionConstantDao.update(mMissionConstant, DBSource.CFG);
	}
	
	public MMissionConstantDao getmMissionConstantDao() {
		return mMissionConstantDao;
	}

	public void setmMissionConstantDao(MMissionConstantDao mMissionConstantDao) {
		this.mMissionConstantDao = mMissionConstantDao;
	}
}
