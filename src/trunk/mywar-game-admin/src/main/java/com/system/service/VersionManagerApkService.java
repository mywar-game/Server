package com.system.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.IPage;
import com.system.bo.VersionManagerApk;
import com.system.dao.VersionManagerApkDao;

/**
 * apk版本管理Service
 * 
 * @author yezp
 */
public class VersionManagerApkService {

	private VersionManagerApkDao versionManagerApkDao;

	/**
	 * 分页查找apk版本列表
	 * 
	 * @param dbSourceId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<VersionManagerApk> findVersionApkPageList(Integer dbSourceId,
			int toPage, int defaultPagesize) {
		versionManagerApkDao.closeSession(dbSourceId);
		IPage<VersionManagerApk> versionApks = versionManagerApkDao.findPage(
				"from VersionManagerApk", new ArrayList<Object>(),
				defaultPagesize, toPage);
		return versionApks;
	}

	/**
	 * 添加
	 * 
	 * @param apk
	 * @param dbSourceId
	 */
	public void addVersionManagerApk(VersionManagerApk apk, Integer dbSourceId) {
		versionManagerApkDao.save(apk, dbSourceId);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param dbSourceId
	 */
	public void delVersionManagerApk(Integer id, Integer dbSourceId) {
		VersionManagerApk apk = getVersionManagerApk(id, dbSourceId);
		versionManagerApkDao.remove(apk, dbSourceId);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @param dbSourceId
	 * @return
	 */
	public VersionManagerApk getVersionManagerApk(Integer id, Integer dbSourceId) {
		return versionManagerApkDao.loadBy("id", id, dbSourceId);
	}

	/**
	 * 编辑
	 * 
	 * @param apk
	 * @param dbSourceId
	 */
	public void updateVersionManagerApk(VersionManagerApk apk,
			Integer dbSourceId) {
		versionManagerApkDao.update(apk, dbSourceId);
	}

	/**
	 * 获取同渠道下的最大版本号
	 * 
	 * @param partnerId
	 * @param dbSourceId
	 * @return
	 */
	public VersionManagerApk getMaxVersionByParentId(String partnerId,
			Integer dbSourceId) {
		String sql = "select MAX(apk_big_version) as big, MAX(apk_small_version) as small from version_manager_apk "
				+ " where apk_big_version = (select MAX(apk_big_version) from version_manager_apk where partner_id = '"
				+ partnerId + "') and partner_id = '" + partnerId + "'";

		versionManagerApkDao.closeSession(dbSourceId);
		List<Object> list = versionManagerApkDao.findSQL_(sql);
		VersionManagerApk apk = new VersionManagerApk();

		if (list.size() == 1 && ((Object[]) list.get(0))[0] != null) {
			apk.setApkBigVersion(Integer.parseInt(((Object[]) list.get(0))[0]
					.toString()));
			apk.setApkSmallVersion(Integer.parseInt(((Object[]) list.get(0))[1]
					.toString()));
		}

		return apk;
	}

	/**
	 * 获取apk版本信息列表
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public List<VersionManagerApk> getVersionManagerApkList(Integer dbSourceId) {
		return versionManagerApkDao.find("from VersionManagerApk", dbSourceId);
	}

	/**
	 * 获取当前最大的版本
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public VersionManagerApk getMaxVersion(Integer dbSourceId) {
		String sql = "select MAX(apk_big_version) as big, MAX(apk_small_version) as small from version_manager_apk where apk_big_version = (select MAX(apk_big_version) from version_manager_apk)";

		versionManagerApkDao.closeSession(dbSourceId);
		List<Object> list = versionManagerApkDao.findSQL_(sql);
		VersionManagerApk apk = new VersionManagerApk();
		if (list.size() == 1 && ((Object[]) list.get(0))[0] != null) {
			apk.setApkBigVersion(Integer.parseInt(((Object[]) list.get(0))[0]
					.toString()));
			apk.setApkSmallVersion(Integer.parseInt(((Object[]) list.get(0))[1]
					.toString()));

			apk.setApkSmallVersion(apk.getApkSmallVersion() + 1);
		}

		return apk;
	}

	public VersionManagerApkDao getVersionManagerApkDao() {
		return versionManagerApkDao;
	}

	public void setVersionManagerApkDao(
			VersionManagerApkDao versionManagerApkDao) {
		this.versionManagerApkDao = versionManagerApkDao;
	}

}
