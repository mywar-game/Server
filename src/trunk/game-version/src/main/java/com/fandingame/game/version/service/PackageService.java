package com.fandingame.game.version.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.version.bo.PackageInfoBO;
import com.fandingame.game.version.constant.BlackListType;
import com.fandingame.game.version.dao.ActiveCodeDao;
import com.fandingame.game.version.dao.PackageExtinfoDao;
import com.fandingame.game.version.dao.PackageSettingsDao;
import com.fandingame.game.version.dao.VersionManagerDao;
import com.fandingame.game.version.exception.ServiceException;
import com.fandingame.game.version.model.LoginServerStatus;
import com.fandingame.game.version.model.PackageExtinfo;
import com.fandingame.game.version.model.PackageSettings;
import com.fandingame.game.version.model.VersionManagerApk;
import com.fandingame.game.version.model.VersionManagerRes;
import com.fandingame.game.version.util.EncryptUtil;



public class PackageService {
	/**
	 * 未知错误
	 */
	public static final int UNKNOWN_ERROR = 2000;
	/**
	 * 参数错误
	 */
	public static final int PARAM_ERROR = 2001;
	/**
	 * 校验签名错误
	 */
	public static final int SIGN_CHECK_ERROR = 2002;
	/**
	 * 登录验证失败
	 */
	public static final int LOGIN_VALID_FAILD = 2003;
	/**
	 * 游戏服务器登录失败
	 */
	public static final int LOGIN_GAME_FAILD = 2004;

	/**
	 * 版本失效，无法升级以及登陆
	 */
	public static final int VERSION_EXPIRE = 2005;

	/**
	 * 服务器关闭
	 */
	public static final int SERVER_CLOSE = 2006;
	/**
	 * 校验key
	 */
	public static final String signKey="gCvKaE0tTcWtHsPkbRdE";
//	@Autowired
//	protected PackageInfoDao packageInfoDao;
	@Autowired
	protected PackageSettingsDao packageSettingsDao;
	@Autowired
	protected PackageExtinfoDao packageExtinfoDao;
	@Autowired
	private ActiveCodeDao activeCodeDao;
	@Autowired
	private VersionManagerDao versionManagerDao; 
	
	public PackageInfoBO checkVersion(String version,String fr,String mac,String ip, String partnerId,String qn) {
		if(StringUtils.isBlank(fr)){
			fr="xx_imei";
		}
		if(StringUtils.isBlank(mac)){
			mac="xx_mac";
		}
		if(StringUtils.isBlank(ip)){
			mac="xx_ip";
		}
		//检查是否能进入游戏
		ServiceException exception = checkLoginServerIsOpen();
		if(exception!=null){
			if(!activeCodeDao.isInBlackList(fr, mac, ip, BlackListType.ENTERN_LOGIN_SERVER_BLACK_LIST)){
				throw exception;
			}
		}
		checkVersionExpire(version, partnerId);
		int apkBigVer = 0;
		int apkSmallVer = 0;
		int resBigVer = 0;
		int resSmallVer = 0;
		// 版本参数是否合法
		boolean versionInvalid = false;
		if (version != null && !StringUtils.isBlank(version)) {
			String[] verArr = version.split("\\.");
			if (verArr.length == 4) {
				apkBigVer = Integer.parseInt(verArr[0]);
				apkSmallVer = Integer.parseInt(verArr[1]);
				resBigVer = Integer.parseInt(verArr[2]);
				resSmallVer = Integer.parseInt(verArr[3]);
			} else {
				versionInvalid = true;
			}
		} else {
			versionInvalid = true;
		}

		if (versionInvalid) {
			throw new ServiceException(PARAM_ERROR, "版本参数错误,version="+version);
		}
        //最新的一个apk包
		VersionManagerApk apkPackage = versionManagerDao.getLastApkVersion(partnerId,qn);
		PackageInfoBO bo = new PackageInfoBO();
		bo.setVersion("");
		bo.setApkUrl("");
		bo.setResUrl("");
		bo.setDescribe("");
		// 检测APK包升级
		if (apkPackage != null) {
			if (apkPackage.getIsTest() == 1) {
				// 用户不在灰度测试范围内，则获取普通的最后一个包
				if (!activeCodeDao.isInBlackList(fr, mac, ip, BlackListType.UPGRADE_VERSION_BLACK_LIST)) {
					apkPackage = versionManagerDao.getOfficialApkVersion(partnerId,qn);
				}
			}
			// 版本小于当前版本，表示APK包为旧的，则直接返回APK包内容
			if (apkPackage != null && needUpgradeApk(apkBigVer, apkSmallVer, apkPackage)) {
				bo.setVersion(apkPackage.creatVersionString());
				bo.setApkUrl(apkPackage.getUrl());
				bo.setDescribe(apkPackage.getDescription());
				return bo;
			}
		}
		//查询当前资源版本的所有升级包
		List<VersionManagerRes> resList = new ArrayList<VersionManagerRes>();
		int i=0;
		VersionManagerRes resTempPackage = null;
		int nextBigVer = resBigVer;
		int nexSmalVer = resSmallVer;
		while(true){//保护
			if(i>200){
				throw new ServiceException(SERVER_CLOSE, "服务器异常！版本配置错误");
			}
			resTempPackage = versionManagerDao.getCurrentResVersion(nextBigVer, nexSmalVer, partnerId);
			if(resTempPackage==null){
				break;
			}else{
				nextBigVer = resTempPackage.getResBigVersion();
				nexSmalVer = resTempPackage.getResSmallVersion();
				resList.add(resTempPackage);
			}
			i++;
		}
		// 检测资源包升级
		if (resList.size()>0) {
			boolean isAdd = false;
			for(VersionManagerRes resPackage:resList){
				isAdd = false;
				//如果此包是测试包
				if(resPackage.getIsTest()==1){
					if (activeCodeDao.isInBlackList(fr, mac, ip, BlackListType.UPGRADE_VERSION_BLACK_LIST)) {
						isAdd = true;
					}
				}else{
                     isAdd = true;
				}
				if(isAdd){
					bo.setVersion(resPackage.createVersionString());
					String url = resPackage.getUrl();
					if("".equals(bo.getResUrl())){
						bo.setResUrl(url);
					}else{
						bo.setResUrl(bo.getResUrl()+";"+url);
					}
					bo.setDescribe(resPackage.getDescription());
				}
			}
		}
		return bo;
	}
	private String packUrl(String urlStr) {
		 if(StringUtils.isBlank(urlStr)){
			 return "";
		 }
		String[] urlStrArray = urlStr.split("#");
		int idx = RandomUtils.nextInt(urlStrArray.length);
		String url = urlStrArray[idx] + "?" + UUID.randomUUID().toString();
		return url;
	}
	/**
	 * 比较APK版本号，是否需要升级APK
	 * 
	 * @param apkBigVer
	 * @param apkSmallVer
	 * @param fullVer
	 * @return
	 */
	protected boolean needUpgradeApk(int apkBigVer, int apkSmallVer, VersionManagerApk apkPackage) {
		int apkBigVerTmp = apkPackage.getApkBigVersion();
		int apkSmallVerTmp = apkPackage.getApkSmallVersion();
		return apkBigVer < apkBigVerTmp || (apkBigVer == apkBigVerTmp && apkSmallVer < apkSmallVerTmp);
	}

	/**
	 * 比较资源包版本号
	 * 
	 * @param resBigVer
	 * @param resSmallVer
	 * @param fullVer
	 * @return
	 */
	protected boolean needUpgradeRes(int resBigVer, int resSmallVer, String fullVer) {
		String[] verArr = fullVer.split("\\.");
		int resBigVerTmp = Integer.parseInt(verArr[2]);
		int resSmallVerTmp = Integer.parseInt(verArr[3]);
		return resBigVer < resBigVerTmp || (resBigVer == resBigVerTmp && resSmallVer < resSmallVerTmp);
	}

	protected void checkVersionExpire(String version, String partnerId) {
		PackageExtinfo info = packageExtinfoDao.getByVersion(version, partnerId);
		if (info != null && info.getIsExpire() == 1) {
			PackageSettings settings = packageSettingsDao.get();
			throw new ServiceException(VERSION_EXPIRE, settings.getExpirePackageMessage());
		}
	}
	/**
	 * 查看登录服务器是否开启  true 开启  false 关闭
	 * @return
	 */
	private ServiceException checkLoginServerIsOpen(){
		LoginServerStatus loginServerStatus = activeCodeDao.getLoginServerBean();
		if(loginServerStatus!=null){
			if(loginServerStatus.getStatus() == 0){
				return new ServiceException(SERVER_CLOSE, loginServerStatus.getNotice());
			}
		}
		return null;
	}

	protected void checkSign(String token, String partnerId, String serverId, long timestamp, String sign) {
		String serverSign = EncryptUtil.getMD5((token + partnerId + serverId + timestamp + signKey));
		if (!serverSign.toLowerCase().equals(sign.toLowerCase())) {
			throw new ServiceException(SIGN_CHECK_ERROR, "签名校验失败");
		}
	}
}
