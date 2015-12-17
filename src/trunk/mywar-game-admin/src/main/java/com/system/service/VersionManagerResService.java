package com.system.service;
import java.util.List;

import com.system.bo.VersionManagerRes;
import com.system.dao.VersionManagerResDao;

/**
 * 资源版本管理
 * 
 * @author yezp
 */
public class VersionManagerResService {

	private VersionManagerResDao versionManagerResDao;

	/**
	 * 分页
	 * 
	 * @param dbSourceId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public List<VersionManagerRes> findVersionResList(Integer dbSourceId) {
		versionManagerResDao.closeSession(dbSourceId);
		return versionManagerResDao.findAll();
	}

	/**
	 * 添加
	 * 
	 * @param res
	 * @param dbSourceId
	 */
	public void addVersionManagerRes(VersionManagerRes res, Integer dbSourceId) {
		versionManagerResDao.save(res, dbSourceId);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param dbSourceId
	 */
	public void delVersionManagerRes(Integer id, Integer dbSourceId) {
		VersionManagerRes res = getVersionManagerRes(id, dbSourceId);
		versionManagerResDao.remove(res, dbSourceId);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @param dbSourceId
	 * @return
	 */
	public VersionManagerRes getVersionManagerRes(Integer id, Integer dbSourceId) {
		return versionManagerResDao.loadBy("id", id, dbSourceId);
	}

	/**
	 * 编辑
	 * 
	 * @param res
	 * @param dbSourceId
	 */
	public void updateVersionManagerRes(VersionManagerRes res,
			Integer dbSourceId) {
		versionManagerResDao.update(res, dbSourceId);
	}

	/**
	 * 获取当前最大的版本信息
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public VersionManagerRes getMaxVersion(int dbSourceId) {
		String sql = "select MAX(res_big_version) as big, MAX(res_small_version) as small from version_manager_res where res_big_version = (select MAX(res_big_version) from version_manager_res)";

		versionManagerResDao.closeSession(dbSourceId);
		VersionManagerRes res = new VersionManagerRes();
		List<Object> list = versionManagerResDao.findSQL_(sql);
		if (list.size() == 1 && ((Object[]) list.get(0))[0] != null) {
			res.setBeUpdateBigVersion(Integer.parseInt(((Object[]) list.get(0))[0]
					.toString()));
			res.setBeUpdateSmallVersion(Integer.parseInt(((Object[]) list
					.get(0))[1].toString()));

			res.setResBigVersion(res.getBeUpdateBigVersion());
			res.setResSmallVersion(res.getBeUpdateSmallVersion() + 1);
		}

		return res;
	}

	public VersionManagerRes getMaxVersionByPartnerId(String partnerId,
			int dbSourceId) {
		String sql = "select MAX(res_big_version) as big, MAX(res_small_version) as small from version_manager_res "
				+ " where res_big_version = (select MAX(res_big_version) from version_manager_res where partner_id = '"
				+ partnerId + "')" + " and partner_id = '" + partnerId + "'";

		versionManagerResDao.closeSession(dbSourceId);
		VersionManagerRes res = new VersionManagerRes();
		List<Object> list = versionManagerResDao.findSQL_(sql);
		if (list.size() == 1 && ((Object[]) list.get(0))[0] != null) {
			res.setBeUpdateBigVersion(Integer.parseInt(((Object[]) list.get(0))[0]
					.toString()));
			res.setBeUpdateSmallVersion(Integer.parseInt(((Object[]) list
					.get(0))[1].toString()));

			res.setResBigVersion(res.getBeUpdateBigVersion());
			res.setResSmallVersion(res.getBeUpdateSmallVersion());
		}

		return res;
	}

	public VersionManagerResDao getVersionManagerResDao() {
		return versionManagerResDao;
	}

	public void setVersionManagerResDao(
			VersionManagerResDao versionManagerResDao) {
		this.versionManagerResDao = versionManagerResDao;
	}

}
