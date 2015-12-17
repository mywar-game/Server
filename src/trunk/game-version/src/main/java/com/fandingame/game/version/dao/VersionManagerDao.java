package com.fandingame.game.version.dao;

import com.fandingame.game.version.model.VersionManagerApk;
import com.fandingame.game.version.model.VersionManagerRes;

public interface VersionManagerDao {
   /**
    * 查询最新的一个apk包
    * @param partenerId
    * @return
    */
	public VersionManagerApk getLastApkVersion(String partnerId,String qn);
	/**
	 * 获取最后一个正式APK包
	 * @param partenerId
	 * @return
	 */
	public VersionManagerApk getOfficialApkVersion(String partnerId,String qn);
	/**
	 * 查询最新的资源包
	 * @param partenerId
	 * @return
	 */
	public VersionManagerRes getCurrentResVersion(int currentResBigVersion,int currentResSmallVersion,String partnerId);
}
