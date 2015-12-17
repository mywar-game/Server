package com.framework.constant;

public class SystemConstant {
//客户端连接协议
public static final int HTTP_CONNECT = 1;
public static final int SOCKET_CONNECT = 2;
//客户端类型
//AS
public static final int AS_CLIENT = 0;
//JAVA
public static final int JAVA_CLIENT = 1001;

//ERROR
public static final int FAIL_CODE = 1;
public static final int SUCCESS_CODE = 0;

// 系统编号
public static final int systemNumbet = 12;

// 重连允许时间长
public static long maxPermiReconnectTime = 20 * 1000;

// 在主服务器,用户游戏列表的维护线程过期时间间隔,当用户从游戏服务器退出,
// 而没有在以下时间下登入主平台,则它会被用户游戏列表中删除
public static long MaxGameUserTimeInterval = 120 * 1000;

// 不论用户有没有从游戏推出，超过这个时间都要删除
public static long MaxGameUserSession = 24 * 60 * 60 * 1000;

// 会话过期，会话关闭的时间
public static int SESSION_OVER_TIME_SESSION_CLOSE_TIME = 600 * 1000;

}
