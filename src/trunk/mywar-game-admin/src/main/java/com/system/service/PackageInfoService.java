package com.system.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.IPage;
import com.system.bo.PackageInfo;
import com.system.dao.PackageInfoDao;

/**
 * 游戏包信息
 * 
 * @author yezp
 */
public class PackageInfoService {

	private PackageInfoDao packageInfoDao;

	/**
	 * 分页查找
	 * 
	 * @param dbSourceId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<PackageInfo> findPackageInfoPageList(Integer dbSourceId,
			int toPage, int defaultPagesize) {
		packageInfoDao.closeSession(dbSourceId);
		IPage<PackageInfo> packageInfos = packageInfoDao.findPage(
				"from PackageInfo", new ArrayList<Object>(), defaultPagesize,
				toPage);
		return packageInfos;
	}

	/**
	 * 获取包信息
	 * 
	 * @param dbSourceId
	 * @return
	 */
	public List<PackageInfo> getPackageInfoList(Integer dbSourceId) {
		return packageInfoDao.find("from PackageInfo", dbSourceId);
	}

	/**
	 * 添加包信息
	 * 
	 * @param packageInfo
	 * @param dbSourceId
	 */
	public void addPackageInfo(PackageInfo packageInfo, Integer dbSourceId) {
		packageInfoDao.save(packageInfo, dbSourceId);
	}

	/**
	 * 删除包信息
	 * 
	 * @param id
	 * @param dbSourceId
	 */
	public void delPackageInfo(Integer id, Integer dbSourceId) {
		PackageInfo info = getPackageInfoById(id, dbSourceId);
		packageInfoDao.remove(info, dbSourceId);
	}

	/**
	 * 根据id获取包信息
	 * 
	 * @param id
	 * @param dbSourceId
	 * @return
	 */
	public PackageInfo getPackageInfoById(Integer id, Integer dbSourceId) {
		return packageInfoDao.loadBy("id", id, dbSourceId);
	}

	/**
	 * 根据版本号获取游戏包信息
	 * 
	 * @param version
	 * @param dbSourceId
	 * @return
	 */
	public List<PackageInfo> getInfoListByVersion(String version,
			Integer dbSourceId) {
		return packageInfoDao.find("from PackageInfo where version = '"
				+ version + "'", dbSourceId);
	}

	/**
	 * 修改包信息
	 * 
	 * @param info
	 * @param dbSourceId
	 */
	public void updatePackageInfo(PackageInfo info, Integer dbSourceId) {
		packageInfoDao.update(info, dbSourceId);
	}

	public PackageInfoDao getPackageInfoDao() {
		return packageInfoDao;
	}

	public void setPackageInfoDao(PackageInfoDao packageInfoDao) {
		this.packageInfoDao = packageInfoDao;
	}

}
