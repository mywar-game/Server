package com.fantingame.game.mywar.logic.scene.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.plugin.IAppPlugin;
import com.fantingame.game.msgbody.client.scene.SceneAction_changeLineRes;
import com.fantingame.game.msgbody.client.scene.SceneAction_enterRes;
import com.fantingame.game.msgbody.client.scene.SceneAction_loadedRes;
import com.fantingame.game.msgbody.client.scene.UserSceneDataBO;
import com.fantingame.game.msgbody.notify.scene.Scene_enterNotify;
import com.fantingame.game.msgbody.notify.scene.Scene_moveNotify;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.scene.bean.Line;
import com.fantingame.game.mywar.logic.scene.bean.Scene;
import com.fantingame.game.mywar.logic.scene.bean.UserSceneData;
import com.fantingame.game.mywar.logic.scene.dao.cache.SystemMapDaoCache;
import com.fantingame.game.mywar.logic.scene.dao.cache.SystemSceneDaoCache;
import com.fantingame.game.mywar.logic.scene.dao.cache.SystemTransferDaoCache;
import com.fantingame.game.mywar.logic.scene.dao.cache.UserSceneDaoCache;
import com.fantingame.game.mywar.logic.scene.model.SystemMap;
import com.fantingame.game.mywar.logic.scene.model.SystemScene;
import com.fantingame.game.mywar.logic.scene.model.UserScene;
import com.fantingame.game.mywar.logic.setting.service.SettingService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.room.RestrictionsRule;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SceneService implements IAppPlugin,ModuleEventHandler{
	
    @Autowired
	private SystemSceneDaoCache systemSceneDaoCachImpl;
    @Autowired
    private SystemTransferDaoCache systemTransferDaoCache;
    @Autowired
    private SystemMapDaoCache systemMapDaoCache;
    @Autowired
    private UserService userService;
    @Autowired
    private HeroService heroService;
    @Autowired
    private UserSceneDaoCache userSceneDaoCache; 
    @Autowired
    private SettingService settingService;
    private Map<Integer, Scene> sceneMap = new HashMap<Integer,Scene>();
    private Map<String, UserSceneData> userSceneMap = Maps.newConcurrentMap();
    
	//联盟新手存场景id
	public static final int LIAN_MEN_NEW_SCENE = 1001;
	//部落新手存场景id
	public static final int BU_LUO_NEW_SCENE = 2001;
    /**
     * 获取用户已开启场景列表
     * @param userId
     * @return
     */
    public List<Integer> getOpenUserSceneIdList(String userId){
    	List<UserScene> list = userSceneDaoCache.getUserSceneList(userId);
    	List<Integer> result = Lists.newArrayList();
    	if(list!=null&&list.size()>0){
    		for(UserScene userScene:list){
    			result.add(userScene.getSceneId());
    		}
    	}
    	return result;
    }
     
    public static final int OPEN_LEVEL_LIMIT = 2001;
    /**
     * 开启场景
     * @param userId
     * @param sceneId
     * @return
     */
    public void openScene(String userId,int sceneId) {
    	User user = userService.getByUserId(userId);
    	Scene scene = sceneMap.get(sceneId);
    	if (user.getLevel()<scene.getMinLevel()) {
    		throw new ServiceException(OPEN_LEVEL_LIMIT, "等级不足，sceneId="+sceneId+",nowLevel="+user.getLevel()+",sceneMinLevel="+scene.getMinLevel());
    	}
    	
    	if (!userSceneDaoCache.addUserScene(userId, sceneId)) {
    		LogSystem.warn("开启未知错误，可能是已经开启过了，多次开启了？？，sceneId="+sceneId+",nowLevel="+user.getLevel()+",sceneMinLevel="+scene.getMinLevel());
//    		throw new ServiceException(SystemConstant.FAIL_CODE, "开启未知错误，可能是已经开启过了，多次开启了？？，sceneId="+sceneId+",nowLevel="+user.getLevel()+",sceneMinLevel="+scene.getMinLevel());
    	}
    }
    /**
     * 进入场景
     * @param userId
     * @param targetSceneId
     * @param x
     * @param y
     */
    public static final int NOT_OPEN = 2001;
    public SceneAction_enterRes addUser(String userId, int targetSceneId, int x, int y){
    	Scene scene = sceneMap.get(targetSceneId);
    	if (scene == null) {
    		throw new ServiceException(SystemConstant.FAIL_CODE, "不存在的场景id" + targetSceneId);
    	}
    	
    	UserSceneData userSceneData = userSceneMap.get(userId);
    	if (userSceneData == null) {
    		int heroId = heroService.getInSceneHeroId(userId);
    		// 获取用户设置的同屏显示人数
    		int maxUserCount = settingService.getDisplayNum(userId);
    		if (maxUserCount == -1){
    			maxUserCount = scene.getLineMaxUserCount();
    		}
    		
    		userSceneData = new UserSceneData(userId, heroId, maxUserCount);
    		User user = userService.getByUserId(userId);
    		userSceneData.setUserName(user.getName());
        	//是否开启过
//        	if(targetSceneId!=LIAN_MEN_NEW_SCENE&&targetSceneId!=BU_LUO_NEW_SCENE){
//        		if(!userSceneDaoCache.isOpenScene(userId, targetSceneId)){
//            		throw new ServiceException(NOT_OPEN, "还未开启，sceneId="+targetSceneId+",nowLevel="+user.getLevel()+",sceneMinLevel="+scene.getMinLevel());
//        		}
//        	}
    		userSceneMap.put(userId, userSceneData);
    	}
    	
		LogSystem.debug("进入场景消息---->>userName=" + userSceneData.getUserName() + ",posX=" + x + ",posY=" + y);
		scene.doChangeScene(userSceneData, x, y, userSceneMap);
		SceneAction_enterRes action_enterRes = new SceneAction_enterRes();
		action_enterRes.setSceneLineNum(scene.lineSize());
		action_enterRes.setUserLineNum(userSceneData.getLine().getLineNum());
		return action_enterRes;
    }
    
    /**
     * 换线
     * @param userId
     * @param targetSceneId
     * @param targetLineNum
     * @param x
     * @param y
     */
    public SceneAction_changeLineRes changeLine(String userId,int targetSceneId,int targetLineNum){
    	Scene scene = sceneMap.get(targetSceneId);
    	if (scene==null || scene.getSceneType() != Scene.SCENE_TYPE_MULTY) {
    		throw new ServiceException(SystemConstant.FAIL_CODE, "场景为空或场景不是多人场景 不需要加入");
    	}
    	
    	UserSceneData userSceneData = userSceneMap.get(userId);
    	if (userSceneData == null) {
    		throw new ServiceException(SystemConstant.FAIL_CODE, "还没有加入场景，换线？");
    	}
    	
    	if (userSceneData.getScene().getSceneId() != targetSceneId) {
    		throw new ServiceException(SystemConstant.FAIL_CODE, "当前所在线与服务器数据不一致");
    	}
    	
    	int lineNum = userSceneData.getScene().lineSize();
    	if (targetLineNum >= lineNum) {
    		throw new ServiceException(SystemConstant.FAIL_CODE, "线数太大 超过最大线数，serverSize=" + lineNum + ",目的线" + targetLineNum);
    	}
    	
    	userSceneData.getLine().leave(userSceneData, userSceneMap);
    	Line line = userSceneData.getScene().getLine(targetLineNum);
    	
    	userSceneData.setLine(line);
    	userSceneData.getLine().enter(userSceneData, userSceneData.getPosition().x, userSceneData.getPosition().y, userSceneMap);
    	userSceneData.isEntered= false;
    	
    	SceneAction_changeLineRes res = new SceneAction_changeLineRes();
    	res.setUserLineNum(targetLineNum);
    	res.setSceneUsersList(creatUserSceneDataBOList(userId,userSceneData.getLine().getAllUserData()));
    	return res;
    }
    
    /**
     * 移动消息
     * @param userId
     * @param targetX
     * @param targetY
     */
    public void move(String userId, int targetX, int targetY) {
    	UserSceneData userSceneData = userSceneMap.get(userId);
    	if (userSceneData == null) {
    		throw new ServiceException(SystemConstant.FAIL_CODE, "还没有加入场景，换线？");
    	}
    	LogSystem.debug("推送用户移动消息--to "+userSceneData.getUserName()+"from ,x="+userSceneData.getPosition().x+"y="+userSceneData.getPosition().y+",to x="+targetX+",y="+targetY);

    	userSceneData.getPosition().x = targetX;
    	userSceneData.getPosition().y = targetY;
    	Scene_moveNotify notify = new Scene_moveNotify();
    	notify.setUserId(userId);
    	notify.setPosX(targetX);
    	notify.setPosY(targetY);
    	RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);
    	restrictionsRule.addUser(userId);
    	
    	// 进入的消息
    	Scene_enterNotify scene_enterNotify = new Scene_enterNotify();
    	scene_enterNotify.setUserId(userSceneData.getUserId());
    	scene_enterNotify.setHeroId(userSceneData.getHeroId());
    	scene_enterNotify.setPosX(userSceneData.getPosition().x);
    	scene_enterNotify.setPosY(userSceneData.getPosition().y);
    	scene_enterNotify.setUserName(userSceneData.getUserName());
    	RestrictionsRule rule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_TO_THIS_PERSON);    	
    	
    	// 判断只需要给那些人推送用户移动的消息
    	List<String> userIdList = userSceneData.getLine().getAllUser();
    	for (String otherUserId : userIdList) {
    		if (otherUserId.equals(userId))
    			continue;
    		
    		UserSceneData otherUser = userSceneMap.get(otherUserId);
    		if (otherUser == null)
    			continue;
    		
    		// 判断对方的场景里是否加载了你
    		if (otherUser.getScreenUserIdMap().containsKey(userId))
    			continue;
    		
    		// 对方场景的同屏显示未满
    		if (otherUser.getScreenUserIdMap().size() < otherUser.getMaxScreenCount()) {
    			// 加载用户信息，对该用户可见
    			otherUser.getScreenUserIdMap().put(userId, userId);
    			rule.addUser(otherUserId);
    		}
    		
    		// 不给这个用户推送移动的信息
    		restrictionsRule.addUser(otherUserId);    		
    	}
    	
    	MsgDispatchCenter.disPatchUserRoomMsg("Scene_move", userSceneData.getLine().getRoomId(), notify, restrictionsRule);
    	if (rule.getUserSize() > 0)
    		MsgDispatchCenter.disPatchUserRoomMsg("Scene_enter", userSceneData.getLine().getRoomId(), scene_enterNotify, rule);
    }
    
    private List<UserSceneDataBO> creatUserSceneDataBOList(String userId, Collection<UserSceneData> list) {
    	List<UserSceneDataBO> result = Lists.newArrayList();
    	if (list == null) {
    		return null;
    	}
    	
    	for (UserSceneData userSceneData : list) {
    		//过滤掉自己
    		if(userSceneData.getUserId().equals(userId)) {
    			continue;
    		}
    		result.add(creatUserSceneDataBO(userSceneData));
    	}
    	return result;
    }
    
    private UserSceneDataBO creatUserSceneDataBO(UserSceneData userSceneData){
    	UserSceneDataBO userSceneDataBO = new UserSceneDataBO();
    	userSceneDataBO.setHeroId(userSceneData.getHeroId());
    	userSceneDataBO.setPosX(userSceneData.getPosition().x);
    	userSceneDataBO.setPosY(userSceneData.getPosition().y);
    	userSceneDataBO.setUserId(userSceneData.getUserId());
    	userSceneDataBO.setUserName(userSceneData.getUserName());
    	return userSceneDataBO;
    }
    /**
     * 用户进入场景后已加载完成
     * @param userId
     */
    public SceneAction_loadedRes userLoaded(String userId, int sceneId) {
    	UserSceneData userSceneData = userSceneMap.get(userId);
    	if(userSceneData == null || userSceneData.isEntered){
    		throw new ServiceException(SystemConstant.FAIL_CODE, "都没加入场景，你发这个消息干毛啊！或者你已经加载完成过了啊，连续发是几个意思啊！");
    	}
    	if(userSceneData.getScene().getSceneId() != sceneId){
    		throw new ServiceException(SystemConstant.FAIL_CODE, "兄弟搞错了吧！当前场景和服务器的数据不一致啊！scendId="+sceneId+",serverScenId="+userSceneData.getScene().getSceneId());
    	}
    	SceneAction_loadedRes action_loadedRes = new SceneAction_loadedRes();
    	
    	// 加载场景，根据自己的屏蔽信息加载
    	List<UserSceneDataBO> result = Lists.newArrayList();
    	for (UserSceneData otherUser : userSceneData.getLine().getAllUserData()) {
    		// 过滤自己
    		if (otherUser.getUserId().equals(userId))
    			continue;
    		
    		if (userSceneData.getScreenUserIdMap().size() >= userSceneData.getMaxScreenCount())
    			break;
    		
    		result.add(creatUserSceneDataBO(otherUser));
    		userSceneData.getScreenUserIdMap().put(otherUser.getUserId(), otherUser.getUserId());
    	}
    	
    	action_loadedRes.setSceneUsersList(result);
    	userSceneData.isEntered = true;
    	return action_loadedRes;
    }
    
    /**
     * 获取用户的房间id
     * 
     * @param userId
     * @return
     */
    public String getUserSceneRoomId(String userId) {
    	UserSceneData userSceneData = userSceneMap.get(userId);
    	if (userSceneData != null && userSceneData.getLine() != null)
    		return userSceneData.getLine().getRoomId();
    	
    	return null;
    }
    
    /**
     * 用户离开场景  登出或掉线
     * @param userId
     */
    public void userOffLine(String userId){
    	UserSceneData userSceneData = userSceneMap.get(userId);
    	if(userSceneData!=null){
    		userSceneData.isEntered = false;
    		//保存上次所处位置保存
    		if(userSceneData.getScene()!=null){
    			userService.updatePrePosition(userId, userSceneData.getScene().getSceneId()+","+userSceneData.getPosition().x+","+userSceneData.getPosition().y);
    			userSceneData.getScene().levelScence(userSceneData, userSceneMap);
    		}
    		
    		userSceneMap.remove(userId);
    	}
    }
    
    /**
     * 检查普通场景是否需要增加线, 每10秒调用一次. 必须只能有一个线程调用
     */
    public void checkSceneNeedAddNewLine(){
        for (Scene scene : sceneMap.values()){
                try{
                	if(scene.getSceneType() == Scene.SCENE_TYPE_SINGLE){
                		continue;
                	}
                	scene.updateOverLoad();
                    if (scene.needAddNewLine()){ // 需要加线的, 一定是多人场景
                        // 需要新增加一条线
                    	scene.incLine();
                    }
                } catch (Exception e){
                    LogSystem.error(e, "添加场景报错了");
                }
        }
    }
    
	@Override
	public void startup() throws Exception {
		//初始化所有场景
		List<SystemScene> list = systemSceneDaoCachImpl.getAllScene();
		for(int i=0;i<list.size();i++){
			SystemScene systemScene = list.get(i);
			 Scene scene = new Scene(null, null, null, systemScene.getSceneId(), systemScene.getSceneType(), systemScene.getInitLineNum(), systemScene.getLineMaxHeroCount(), systemScene.getMaxLineNum(),systemScene.getMinLevel());
			 sceneMap.put(systemScene.getSceneId(), scene);
		}
	}
	
	@Override
	public void shutdown() throws Exception {
		
	}
	
	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
		String userId = baseModuleEvent.getStringValue("userId", "");
        if(handlerType.equals(ModuleEventConstant.USER_LOGINOUT_EVENT)){
        	userOffLine(userId);
        }
//        else if(handlerType.equals(ModuleEventConstant.AFTER_USER_CREAT_ROLE_EVENT)){
//        	//开启新手村场景
//        	User user = userService.getByUserId(userId);
//        	if(user.getCamp()==1){
//        		this.openScene(userId, LIAN_MEN_NEW_SCENE);
//        	}else{
//        		this.openScene(userId, BU_LUO_NEW_SCENE);
//        	}
//        }
	}
	
	public SystemMap getSystemMap(int mapId) {
		return systemMapDaoCache.getSystemMap(mapId);
	}
	
	public SystemScene getSystemScene(int sceneId) {
		return systemSceneDaoCachImpl.getSystemScene(sceneId);
	}
	
	@Override
	public List<String> getHandlerType() {
		List<String> list = Lists.newArrayList();
		list.add(ModuleEventConstant.USER_LOGINOUT_EVENT);
//		list.add(ModuleEventConstant.AFTER_USER_CREAT_ROLE_EVENT);
		return list;
	}
	
	@Override
	public int order() {
		return 3;
	}
	
	@Override
	public int cpOrder() {
		return 0;
	}
}
