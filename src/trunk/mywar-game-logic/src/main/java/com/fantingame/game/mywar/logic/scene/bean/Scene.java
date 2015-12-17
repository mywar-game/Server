package com.fantingame.game.mywar.logic.scene.bean;

import java.util.Arrays;
import java.util.Map;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.server.room.RoomManager;


public class Scene {
	
    //场景npc对象池
    private final SceneNpc[] sceneNpcs;
    //传送点对象池
    private final Transfer[] transfers;
    //关卡对象池
    private final Forces[] forces;
    //线列表
    private Line[] lines = new Line[0];
    //各个线的负载情况
    private int[] overLoadNum = new int[0];
    //场景id即房间id
    private final int sceneId;
    private final int sceneType;
    private final int minLevel;
    private final int initLineNum;
    private final int lineMaxUserCount;
    private final int maxLineNum;
    //单人场景
    public static final int SCENE_TYPE_SINGLE = 1;
    //多人场景
    public static final int SCENE_TYPE_MULTY = 2;
    
    public Scene(SceneNpc[] sceneNpcs,Transfer[] transfers,Forces[] forces,int sceneId,int sceneType,int initLineNum,int lineMaxUserCount,int maxLineNum,int minLevel){
    	this.sceneNpcs = sceneNpcs;
    	this.transfers = transfers;
    	this.forces = forces;
    	this.sceneId = sceneId;
    	this.sceneType = sceneType;
    	this.initLineNum = initLineNum;
    	this.lineMaxUserCount = lineMaxUserCount;
    	this.maxLineNum = maxLineNum;
    	this.minLevel = minLevel;
    	initLine();
    }
    public void initLine(){
    	if(initLineNum>0&&sceneType==SCENE_TYPE_MULTY){
    		for(int i=0;i<initLineNum;i++){
    			incLine();
    		}
    	}
    }
    
    
    public void doChangeScene(UserSceneData userSceneData, int posx, int posY, Map<String, UserSceneData> userSceneDataMap) {
    	if (userSceneData==null) {
    		LogSystem.error(new NullPointerException("场景数据不能为Null"), "");
    		return;
    	}
    	userSceneData.isEntered = false;
    	
    	//先退出前面那个场景
    	if (userSceneData.getScene() != null) {
    		userSceneData.getScene().levelScence(userSceneData, userSceneDataMap);
    	}
    	this.enterScence(userSceneData, posx, posY, userSceneDataMap);
    }
    /**
     * 进入场景
     * @param userSceneData
     */
    public void enterScence(UserSceneData userSceneData, int x, int y, Map<String, UserSceneData> userSceneDataMap){
    	userSceneData.setScene(this);
    	Line line = this.getRandomLine();
    	line.enter(userSceneData, x, y, userSceneDataMap);
    }
    /**
     * 离开场景
     * @param userSceneData
     */
    public void levelScence(UserSceneData userSceneData, Map<String, UserSceneData> userSceneDataMap){
    	userSceneData.setScene(null);
    	if(userSceneData.getLine()!=null){
    		userSceneData.getLine().leave(userSceneData, userSceneDataMap);
    	}
    }
    /**
     * 获取一条线信息
     * @param lineNum
     * @return
     */
    public Line getLine(int lineNum){
        Line[] array = lines;
        if (lineNum <= 0 || lineNum > array.length){ // line 从1开始
            // 不算是bug, 可能英雄刚上线, 他以前所在的线现在没有了
            return null;
        }
        return array[lineNum];
    }
    /**
     * 新增一个line
     */
    public Line incLine(){
    	if(sceneType==SCENE_TYPE_SINGLE){
    		LogSystem.warn("sceneId="+sceneId+"为单人副本,不用创建线！");
    		return null;
    	}
    	int indexNewLine = lines.length;

    	Line[] newLineArray = Arrays.copyOf(lines, lines.length + 1);
    	Line line = new Line(indexNewLine,this);
    	newLineArray[indexNewLine] = line;
        int[] overLoadNew = Arrays.copyOf(overLoadNum, overLoadNum.length + 1);
        overLoadNew[overLoadNum.length] = -1; // 新建的场景的人数肯定小于默认人数
        overLoadNum = overLoadNew; // 先改变heroCountArray,
                                    // 保证别人读取normalSceneArray时,
                                    // heroCountArray的改变也是可见的
        RoomManager.getInstatnce().addRoom(line.getRoomId(), line);
        lines = newLineArray;
        return line;
    }
    
    /**
     * 每隔一定时间获取各个地图各条线”当前承载的玩家数“数据， 将该数据与该地图的默认人数容量进行对比：
     * 若某地图已开启的某条线路中“当前承载玩家数”>=“默认人数容量”时，检测： 1)
     * 该地图已开启的其他线路中是否存在当前承载玩家人数<默认人数容量的线路 如果存在，则略过，不自动创建新的线路 如果不存在，则继续检测条件2） 2)
     * 该地图“当前已开启的线路条数”是否 < “开启线路条数上限” 如果是，则为该地图创建一条新的线路； 如果否，则略过，不自动创建新的线路。
     * 
     * @return
     */
    public boolean needAddNewLine(){
    	if(sceneType == SCENE_TYPE_SINGLE){
    		return false;
    	}
    	int[] overLoadNumTempArray = overLoadNum;
        if (overLoadNumTempArray.length >= maxLineNum){
            // 已经是最大线数了
            return false;
        }
        for (int overLoad : overLoadNumTempArray){
            if (overLoad == -1){
                // 存在一条线, 人数少于默认人数, 不增加线
                return false;
            }
        }
        // 所有线都超过了默认人数
        return true;
    }
    /**
     * 线的数量
     * @return
     */
    public int lineSize(){
    	return lines.length;
    }
    /**
     * 更新各条线的负载
     */
    public void updateOverLoad(){
    	int[] overLoadNumTempArray = overLoadNum;
    	Line[] linesTemp = lines;
        int lenLimit = Math.min(overLoadNumTempArray.length, linesTemp.length);
        for (int i = 0; i < lenLimit; i++){
            int c = linesTemp[i].getAllUser().size();
            int count = c - lineMaxUserCount;
            if (count < 0){
            	overLoadNumTempArray[i] = -1;
            } else{
            	overLoadNumTempArray[i] = count / lineMaxUserCount;
            }
        }
    }
    /**
     * 随机获取一条线
     * @return
     */
    public Line getRandomLine(){
    	int[] overLoadNumTempArray = overLoadNum;
    	Line[] linesTemp = lines;
        assert linesTemp.length > 0;
        if (linesTemp.length == 1){
            return linesTemp[0];
        }
        int lenLimit = Math.min(overLoadNumTempArray.length, linesTemp.length);
        int min = overLoadNumTempArray[0];
        int minIndex = 0;
        for (int i = 1; i < lenLimit; i++){
            if (overLoadNumTempArray[i] < min){
                min = overLoadNumTempArray[i];
                minIndex = i;
            }
        }
        return linesTemp[minIndex];
    }
    
	public SceneNpc[] getSceneNpcs() {
		return sceneNpcs;
	}
	public Transfer[] getTransfers() {
		return transfers;
	}
	public int getSceneId() {
		return sceneId;
	}
	public int getSceneType() {
		return sceneType;
	}
	public int getInitLineNum() {
		return initLineNum;
	}
	public int getLineMaxUserCount() {
		return lineMaxUserCount;
	}
	public int getMaxLineNum() {
		return maxLineNum;
	}
	public Forces[] getForces() {
		return forces;
	}
	public int getMinLevel() {
		return minLevel;
	}
}
