package com.fantingame.game.gamecenter.constant;

public enum PartenerEnum {
	/**
	 * 中国区 android 1001开始
	 */
    FanTin(1001,"梵町安卓"),
    UC(1002,"UC"),
    QiHoo(1003,"奇虎360"),
    Lenovo(1005,"联想"),
    BaiDu(1006,"百度"),
    BaiDuZs(1007,"百度zs"), 
    XiaoMi(1008,"小米"),
    AnZhi(1010,"安智"),
    AppChina(1011,"应用中国"),
    ChangWan(1012,"畅玩"),
    ChinaMobile(1013,"中移动"),
    ShiJia(1014,"世嘉"),
    DuoKu(1015,"多酷"),
    GuGuo(1016,"谷果"),
    HuCn(1017,"hucn"),
    Sqw(1018,"三七玩"),
    KuaiBo(1019,"快播"),
    AnZhiAd(1020,"安智AD"),
    Pps(1021,"pps"),
    KuWo(1022,"酷我"),
    DianJinAdroid(1023,"点金android 即  91 android"),
    GooglePlay(1024,"谷歌应用商店"),
    huaWei(1025, "华为"),
    OPPO(1026, "OPPO"),
    WanDouJia(1027, "豌豆荚"),
    VIVO(1028, "VIVO"),
    AppFameAndroid(1029, "云顶"),
    Snail(1030, "蜗牛"),
    MobileMM(1031, "移动MM"),
    NewDangLe(1032, "当乐"),
    Mzw(1033, "拇指玩"),
    Amigo(1034, "金立"),
    Meizu(1035, "魅族"),
    Dianxin(1036, "电信"),
    PaDa(1037, "艺果"),
    Kudong(1038, "酷动"),
    
    /**
     * 英雄传奇开始 1101开始
     */
    YXCQ_FanTin(1101, "梵町安卓"),
    YXCQ_UC(1102, "UC"),
    YXCQ_QiHoo(1103, "奇虎360"),
    YXCQ_Lenovo(1105, "联想"),
    YXCQ_BaiDu(1106, "百度"),
    YXCQ_BaiDuZs(1107, "百度zs"), 
    YXCQ_XiaoMi(1108, "小米"),
    YXCQ_AnZhi(1110, "安智"),
    YXCQ_AppChina(1111, "应用中国"),
    YXCQ_ChangWan(1112, "畅玩"),
    YXCQ_ChinaMobile(1113, "中移动"),
    YXCQ_ShiJia(1114, "世嘉"),
    YXCQ_DuoKu(1115, "多酷"),
    YXCQ_GuGuo(1116, "谷果"),
    YXCQ_HuCn(1117, "hucn"),
    YXCQ_Sqw(1118, "三七玩"),
    YXCQ_KuaiBo(1119, "快播"),
    YXCQ_AnZhiAd(1120, "安智AD"),
    YXCQ_Pps(1121, "pps"),
    YXCQ_KuWo(1122, "酷我"),
    YXCQ_DianJinAdroid(1123, "点金android 即  91 android"),
    YXCQ_GooglePlay(1124, "谷歌应用商店"),
    YXCQ_huaWei(1125, "华为"),
    YXCQ_OPPO(1126, "OPPO"),
    YXCQ_WanDouJia(1127, "豌豆荚"),
    YXCQ_VIVO(1128, "VIVO"),
    YXCQ_AppFameAndroid(1129, "云顶"),
    YXCQ_Snail(1130, "蜗牛"),
    YXCQ_MobileMM(1131, "移动MM"),
    YXCQ_NewDangLe(1132, "当乐"),
    YXCQ_Mzw(1133, "拇指玩"),
    YXCQ_Amigo(1134, "金立"),
    YXCQ_Meizu(1135, "魅族"),
    YXCQ_Legame(1136, "乐游"),
    YXCQ_Tengxun(1137, "腾讯"),
    
    /**
     * 中国区ios越狱 2001开始
     */
    FanTinIos(2001,"梵町IOS"),
    KuaiYong(2003,"快用"),
    PpAssistant(2004,"pp助手"),
    TongBu(2005,"同步"),
    DianJinIos(2007,"91Ios"),
    ApplePark(2008,"appPark"),
    Itools(2009,"itools"),
    //正版云顶
    AppFameIos(2010, "appFameIos"),
    
    
    AppStore(3001,"正版苹果"),
    AppStoreWeb(100001, "正版苹果Web版"),
    YXCQ_AppStore(3002,"英雄传奇正版苹果"),
    
    //恺英台湾地区
    KaiYingTw(4001,"恺英TW-ios"),
    KaiYingTwAndr(4002,"恺英TW-andr"),
    KaiYingSA(4003,"恺英SA-ios"),
    KaiYingSAAndr(4004,"恺英SA-andr"),
    
    //越南地区
    YUENAN(5001,"越南-ios"),
    YUENANANDR(5002,"越南-andr"),
    YUENANINGAME(5003,"越南-ingame");
    
	private String description;
	private Integer partenerId;
	private PartenerEnum(Integer partenerId,String description){
		this.description = description;
		this.partenerId = partenerId;
    }
	public Integer getPartenerId(){
		return partenerId;
	}
	public String toString(){
		return this.description+",partenerId="+partenerId;
	}
}
