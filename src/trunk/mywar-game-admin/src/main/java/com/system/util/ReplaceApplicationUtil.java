package com.system.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.system.bo.TDbServer;
import com.system.bo.TGameServer;
import com.system.bo.TSecondaryServer;
import com.system.constant.ServerConstant;

public class ReplaceApplicationUtil {
	
	private static  String GAME_FRAME_MODEL_FILE ;
	
	private static  String CHAT_SERVER_MODEL_FILE ;
	
	private static  String ORDER_SERVER_MODEL_FILE ;
	
	private static  String BATTLE_SERVER_MODEL_FILE ;
	
	private static  String ZIP_FILE;
	
	private static SAXReader saxReader;
	
	private static String PATH ;
	
	private static String WRITE_PATH;
	
	static{
		PATH = (new ReplaceApplicationUtil().getClass().getResource("/")).toString().substring(5);
		PATH = PATH.substring(1, PATH.length()-16);
		GAME_FRAME_MODEL_FILE = PATH + "file/modle/applicationContext_gameserver.xml";
		CHAT_SERVER_MODEL_FILE = PATH + "file/modle/applicationContext_chatserver.xml";
		ORDER_SERVER_MODEL_FILE = PATH + "file/modle/applicationContext_orderserver.xml";
		BATTLE_SERVER_MODEL_FILE = PATH + "file/modle/applicationContext_battleserver.xml";
		ZIP_FILE = PATH + "file/";
		WRITE_PATH = PATH+ "file/application.zip";
		saxReader = new SAXReader(); 
	}
	
	//游戏服务器文件
	public static void generateApplication(int type ,TGameServer tGameServer,TSecondaryServer chatServer,TSecondaryServer battleServer,
			TSecondaryServer orderServer, TDbServer tMongoDBServer,
			TDbServer tMysqlConfigDBServer, TDbServer tMysqlLogDBServer,
			TDbServer tMemcacheDBServer) {
		//生成对应配置文件信息
		System.out.println("================================="+PATH+"=========================================");
		//解析游戏服务器文件
		Document document = null;
		try {
			String path = null;
			switch(type){
				case ServerConstant.GAME_SERVER_TYPE:
					path = GAME_FRAME_MODEL_FILE;
					break;
				case ServerConstant.CHAT_SERVER_TYPE:
					path = CHAT_SERVER_MODEL_FILE;
					break;
				case ServerConstant.BATTLE_SERVER_TYPE:
					path = BATTLE_SERVER_MODEL_FILE;
					break;
				case ServerConstant.ORDER_SERVER_TYPE:
					path = ORDER_SERVER_MODEL_FILE;
					break;
					default:
						return;
			}
			//生成application服务器配置文件
			generateApplicationByPath(document,saxReader,path,type,tGameServer,chatServer,battleServer,orderServer,tMongoDBServer,tMysqlConfigDBServer,tMysqlLogDBServer,tMemcacheDBServer);
			
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		return;
	}
	
	/**
	 * 生成所有的配置文件
	 * @param document
	 * @param saxReader
	 * @param gameFrameFile
	 * @param type
	 * @param tGameServer
	 * @param chatServer
	 * @param orderServer 
	 * @param battleServer 
	 * @param tMongoDBServer
	 * @param tMysqlLogDBServer 
	 * @param tMysqlConfigDBServer 
	 * @param tMemcacheDBServer 
	 * @throws DocumentException 解析异常
	 */
	private static void generateApplicationByPath(Document document,
			SAXReader saxReader, String gameFrameFile, int type,
			TGameServer tGameServer, TSecondaryServer chatServer,
			TSecondaryServer battleServer, TSecondaryServer orderServer,
			TDbServer tMongoDBServer, TDbServer tMysqlConfigDBServer,
			TDbServer tMysqlLogDBServer, TDbServer tMemcacheDBServer)
			throws DocumentException {
		//替换数值
		document = saxReader.read(new File(gameFrameFile));
		String gameStr = document.asXML(); 
		//转换为字符串
		String gameValue = null;
		switch(type){
			case ServerConstant.GAME_SERVER_TYPE:
				gameValue = generateGameFrame(gameStr,tGameServer,chatServer,battleServer,orderServer,tMongoDBServer,tMysqlConfigDBServer,tMysqlLogDBServer,tMemcacheDBServer);
				break; 
			case ServerConstant.CHAT_SERVER_TYPE:
				gameValue = generateChatServer(gameStr,tGameServer,chatServer,tMysqlConfigDBServer,tMysqlLogDBServer,tMemcacheDBServer);
				break;
			case ServerConstant.BATTLE_SERVER_TYPE:
				gameValue = generateBattleServer(gameStr,tGameServer,battleServer,tMysqlConfigDBServer,tMysqlLogDBServer);
				break;
			case ServerConstant.ORDER_SERVER_TYPE:
				gameValue = generateOrderServer(gameStr,tGameServer,orderServer,tMysqlConfigDBServer,tMemcacheDBServer);
				break;
		}
		//解析生成xml
		document = DocumentHelper.parseText(gameValue);   
		//生成xml文件
		generateXML(document, type);
	}

	/**
	 * 打包生成zip文件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static  String genetateZip() {

		FileOutputStream f;
		try {
			f = new FileOutputStream(WRITE_PATH);
		  ZipOutputStream out2 = new ZipOutputStream(f); 
		  List<File> files = getSubFiles(new File(ZIP_FILE));  ;
		  
	        for (int i = 0 , j = files.size(); i < j; i++) 
	        { 
	            System.out.println("Writing file " + files.get(i).getPath()); 
	            DataInputStream in = new DataInputStream(new FileInputStream( 
	            		files.get(i))); 
	            out2.putNextEntry(new ZipEntry(getAbsFileName(ZIP_FILE,files.get(i)))); 
	            int c; 
	            while ((c = in.read()) != -1) 
	                out2.write(c); 
	            in.close(); 
	        } 
	        out2.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        // ZipOutputStream out = new ZipOutputStream(new 
        // DataOutputStream(f)); 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
		return WRITE_PATH;
	}
	
		/**
		 * 设置zip信息,获取文件名
		 */
	   private static String getAbsFileName(String baseDir, File realFileName){  
	        File real=realFileName;  
	        File base=new File(baseDir);  
	        String ret=real.getName();  
	        while (true) {  
	            real=real.getParentFile();  
	            if(real==null)   
	                break;  
	            if(real.equals(base))   
	                break;  
	            else 
	                ret=real.getName()+"/"+ret;  
	        }  
	        return ret;  
	    } 
	   
   /**
    * 设置读取文件列表
    * @param baseDir
    * @return
    */
   @SuppressWarnings("unchecked")
	private static List getSubFiles(File baseDir){  
	        List ret=new ArrayList();  
	        File[] tmp=baseDir.listFiles();  
	        for (int i = 0; i <tmp.length; i++) {  
	            if(tmp[i].isFile() && tmp[i].getName().indexOf(".zip") == -1)  
	                ret.add(tmp[i]);  
	        }  
	        return ret;  
	    }  

	/**
	 * 生成对应的xml文件
	 * @param document
	 * @param type
	 */
	public static  String generateXML(Document document ,int type) {
		StringBuffer path = new StringBuffer();
		String name;
		if(type == ServerConstant.GAME_SERVER_TYPE){
			name = "gameserver.xml";
		}else if(type == ServerConstant.CHAT_SERVER_TYPE){
			name = "chatserver.xml";
		}else if(type == ServerConstant.BATTLE_SERVER_TYPE){
			name = "battleserver.xml";
		}else{
			name = "orderserver.xml";
		}
		
		path.append(ZIP_FILE).append("applicationContext_").append(name);
		File xmlfile= new File(new String(path));
		
//		FileOutputStream fos = null;
		XMLWriter writer;
		try {
			
			OutputFormat outFmt = new OutputFormat("\t", true); 
		    outFmt.setEncoding("UTF-8"); 
			writer = new XMLWriter(
	         new FileOutputStream(xmlfile),outFmt); //保存文档
			//流写入
			writer.write(document);
			
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(path);
		
	}

	/**
	 * 生成gameFrame文件
	 * @param gameStr
	 * @param tGameServer
	 * @param chatServer
	 * @param orderServer 
	 * @param battleServer 
	 * @param tDbServer
	 * @return
	 */
//	--------------------
//	[MONGO:USER_IP]        Mongo用户数据库IP
//	[MONGO:USER_PORT]      Mongo用户数据库端口
//	[MONGO:USER_DATEBASE]  Mongo用户数据库名
//	[MONGO:USER_USER]      Mongo用户数据库用户名
//	[MONGO:USER_PASSWORD]  Mongo用户数据库密码
//	-----------------------------------------------
//	[MYSQL:CFG_IP]         mysql配置数据库服务器IP
//	[MYSQL:CFG_PORT]       mysql配置数据库端口
//	[MYSQL:CFG_DATANAME]   mysql 配置数据库名
//	[MYSQL:CFG_USER]       mysql 配置数据库用户名
//	[MYSQL:CFG_PASSWORD]   mysql 配置数据库密码
//	-----------------------------------------------
//	[MYSQL:LOG_IP]         mysql日志数据库服务器IP
//	[MYSQL:LOG_PORT]       mysql日志数据库端口
//	[MYSQL:LOG_DATANAME]   mysql日志数据库名
//	[MYSQL:LOG_USER]       mysql日志数据库用户名
//	[MYSQL:LOG_PASSWORD]   mysql日志数据库密码
//	-----------------------------------------------
//	[MEM:IP]               memcached缓存IP 
//	[MEM:PORT]             memcached缓存端口
//	-----------------------------------------------
//	[CONFIG:SYSTEM_PORT]      系统开放端口号  12368
//	[CONFIG:SYSTEM_NUM]       系统编号
//	[CONFIG:SYSTEM_KEY]       系统通讯key
//	[CONFIG:SYSTEM_CHATSERVER_IP]   聊天服务器IP
//	[CONFIG:SYSTEM_CHATSERVER_PORT] 聊天服务器端口
//	[CONFIG:SYSTEM_BATTLESERVER_IP]   战斗服务器IP
//	[CONFIG:SYSTEM_BATTLESERVER_PORT] 战斗服务器端口
//	[CONFIG:SYSTEM_ORDERSERVER_IP]   排位赛服务器IP
//	[CONFIG:SYSTEM_ORDERSERVER_PORT] 排位赛服务器端口
//	[CONFIG:SYSTEM_LOCAL_IP]           本机IP
//	[CONFIG:SYSTEM_WEBSERVER_PORT]     web服务器端口号，即tomcat的端口号
//	[CONFIG:SYSTEM_WEBSERVER_FILELIST] 工程部署在web服务器下的目录名
//	[CONFIG:SYSTEM_LOGIN_KEY]          URL直接登录key 
//	[CONFIG:SYSTEM_INDEXPAGE]          游戏首页地址 比如 http://xxx
	private static  String generateGameFrame(String gameStr, TGameServer tGameServer,
			TSecondaryServer chatServer, TSecondaryServer battleServer,
			TSecondaryServer orderServer, TDbServer tMongoDBServer,
			TDbServer tMysqlConfigDBServer, TDbServer tMysqlLogDBServer,
			TDbServer tMemcacheDBServer) {
	
		String value = gameStr.replaceAll("\\[MONGO:USER_IP]", tMongoDBServer.getServerIp())
		.replaceAll("\\[MONGO:USER_PORT]", tMongoDBServer.getServerPort()+"")
		.replaceAll("\\[MONGO:USER_DATEBASE]", tMongoDBServer.getDbName())
		.replaceAll("\\[MONGO:USER_USER]", tMongoDBServer.getUserName())
		.replaceAll("\\[MONGO:USER_PASSWORD]", tMongoDBServer.getPassword())
		
		.replaceAll("\\[MYSQL:CFG_IP]", tMysqlConfigDBServer.getServerIp())
		.replaceAll("\\[MYSQL:CFG_PORT]", tMysqlConfigDBServer.getServerPort()+"")
		.replaceAll("\\[MYSQL:CFG_DATANAME]", tMysqlConfigDBServer.getDbName())
		.replaceAll("\\[MYSQL:CFG_USER]", tMysqlConfigDBServer.getUserName())
		.replaceAll("\\[MYSQL:CFG_PASSWORD]", tMysqlConfigDBServer.getPassword())
		
		.replaceAll("\\[MYSQL:LOG_IP]", tMysqlLogDBServer.getServerIp())
		.replaceAll("\\[MYSQL:LOG_PORT]", tMysqlLogDBServer.getServerPort()+"")
		.replaceAll("\\[MYSQL:LOG_DATANAME]", tMysqlLogDBServer.getDbName())
		.replaceAll("\\[MYSQL:LOG_USER]", tMysqlLogDBServer.getUserName())
		.replaceAll("\\[MYSQL:LOG_PASSWORD]", tMysqlLogDBServer.getPassword())
		
		.replaceAll("\\[MEM:IP]", tMemcacheDBServer.getServerIp())
		.replaceAll("\\[MEM:PORT]", tMemcacheDBServer.getServerPort()+"")
		
		.replaceAll("\\[CONFIG:SYSTEM_PORT]", tGameServer.getServerPoint()+"")
		.replaceAll("\\[CONFIG:SYSTEM_NAME]", tGameServer.getServerAlias())
		.replaceAll("\\[CONFIG:SYSTEM_NUM]", tGameServer.getServerId()+"")
		.replace("[CONFIG:SYSTEM_KEY]", tGameServer.getServerCommunicationKey())
		.replaceAll("\\[CONFIG:SYSTEM_CHATSERVER_IP]", chatServer.getServerIp())
		.replaceAll("\\[CONFIG:SYSTEM_CHATSERVER_PORT]", chatServer.getServerPort()+"")
		.replaceAll("\\[CONFIG:SYSTEM_BATTLESERVER_IP]", battleServer.getServerIp())
		.replaceAll("\\[CONFIG:SYSTEM_BATTLESERVER_PORT]", battleServer.getServerPort()+"")
		.replaceAll("\\[CONFIG:SYSTEM_ORDERSERVER_IP]", orderServer.getServerIp())
		.replaceAll("\\[CONFIG:SYSTEM_ORDERSERVER_PORT]", orderServer.getServerPort()+"")
		.replaceAll("\\[CONFIG:SYSTEM_LOCAL_IP]", tGameServer.getServerIp())
		.replaceAll("\\[CONFIG:SYSTEM_WEBSERVER_PORT]", tGameServer.getWebOpenPort()+"")
		.replaceAll("\\[CONFIG:SYSTEM_WEBSERVER_FILELIST]", tGameServer.getWebServerPath())
		.replaceAll("\\[CONFIG:SYSTEM_LOGIN_KEY]", tGameServer.getLoginEncryption())
		.replaceAll("\\[CONFIG:SYSTEM_INDEXPAGE]", tGameServer.getGameServerUrl());
		
		return value;
	}
	
	/**
	 * 生成排位赛文件
	 * @param gameStr
	 * @param tGameServer 
	 * @param tGameServer
	 * @param secondaryServer
	 * @param orderServer 
	 * @param battleServer 
	 * @param tDbServer
	 * @param tMemcacheDBServer 
	 * @param tMysqlLogDBServer 
	 * @param tMysqlConfigDBServer 
	 * @return
	 */
//	--------------------
//	[MYSQL:CFG_IP]         mysql配置数据库服务器IP
//	[MYSQL:CFG_PORT]       mysql配置数据库端口
//	[MYSQL:CFG_DATANAME]   mysql 配置数据库名
//	[MYSQL:CFG_USER]       mysql 配置数据库用户名
//	[MYSQL:CFG_PASSWORD]   mysql 配置数据库密码
//	-----------------------------------------------
//	[MEM:IP]               memcached缓存IP 
//	[MEM:PORT]             memcached缓存端口
//	-----------------------------------------------
//	[CONFIG:SYSTEM_PORT]   系统开放端口号  12374
//	[CONFIG:SYSTEM_NUM]    系统编号
//	[CONFIG:SYSTEM_KEY]    系统通讯key
	private static  String generateOrderServer(String gameStr,
			TGameServer tGameServer, TSecondaryServer orderServer, TDbServer tMysqlConfigDBServer,
			TDbServer tMemcacheDBServer) {

		String value = gameStr.replaceAll("\\[MYSQL:CFG_IP]", tMysqlConfigDBServer.getServerIp())
		.replaceAll("\\[MYSQL:CFG_PORT]", tMysqlConfigDBServer.getServerPort()+"")
		.replaceAll("\\[MYSQL:CFG_DATANAME]", tMysqlConfigDBServer.getDbName())
		.replaceAll("\\[MYSQL:CFG_USER]", tMysqlConfigDBServer.getUserName())
		.replaceAll("\\[MYSQL:CFG_PASSWORD]", tMysqlConfigDBServer.getPassword())
		
		.replaceAll("\\[MEM:IP]", tMemcacheDBServer.getServerIp())
		.replaceAll("\\[MEM:PORT]", tMemcacheDBServer.getServerPort()+"")
		.replaceAll("\\[CONFIG:SYSTEM_PORT]", orderServer.getServerPort()+"")
		.replaceAll("\\[CONFIG:SYSTEM_PORT1]", (orderServer.getServerPort()+1)+"")
		.replaceAll("\\[CONFIG:SYSTEM_NUM]", orderServer.getServerId()+"")
		.replace("[CONFIG:SYSTEM_KEY]", tGameServer.getServerCommunicationKey());
		return value;
	}
	
	
	/**
	 * 生成gameFrame文件
	 * @param gameStr
	 * @param tGameServer 
	 * @param tGameServer
	 * @param chatServer
	 * @param orderServer 
	 * @param battleServer 
	 * @param tMongoDBServer
	 * @param tMemcacheDBServer 
	 * @param tMysqlLogDBServer 
	 * @param tMysqlConfigDBServer 
	 * @return
	 */
//	--------------------
//	[MYSQL:CFG_IP]         mysql配置数据库服务器IP
//	[MYSQL:CFG_PORT]       mysql配置数据库端口
//	[MYSQL:CFG_DATANAME]   mysql 配置数据库名
//	[MYSQL:CFG_USER]       mysql 配置数据库用户名
//	[MYSQL:CFG_PASSWORD]   mysql 配置数据库密码
//	-----------------------------------------------
//	[MYSQL:LOG_IP]         mysql日志数据库服务器IP
//	[MYSQL:LOG_PORT]       mysql日志数据库端口
//	[MYSQL:LOG_DATANAME]   mysql日志数据库名
//	[MYSQL:LOG_USER]       mysql日志数据库用户名
//	[MYSQL:LOG_PASSWORD]   mysql日志数据库密码
//	-----------------------------------------------
//	[MEM:IP]               memcached缓存IP 
//	[MEM:PORT]             memcached缓存端口
//	-----------------------------------------------
//	[CONFIG:SYSTEM_PORT]   系统开放端口号  12370
//	[CONFIG:SYSTEM_NUM]    系统编号
//	[CONFIG:SYSTEM_KEY]    系统通讯key
//	-----------------------------------------------
	private  static  String generateChatServer(String gameStr,
			TGameServer tGameServer, TSecondaryServer chatServer,
			TDbServer tMysqlConfigDBServer, TDbServer tMysqlLogDBServer,
			TDbServer tMemcacheDBServer) {

		String value = gameStr.replaceAll("\\[MYSQL:CFG_IP]", tMysqlConfigDBServer.getServerIp())
		.replaceAll("\\[MYSQL:CFG_PORT]", tMysqlConfigDBServer.getServerPort()+"")
		.replaceAll("\\[MYSQL:CFG_DATANAME]", tMysqlConfigDBServer.getDbName())
		.replaceAll("\\[MYSQL:CFG_USER]", tMysqlConfigDBServer.getUserName())
		.replaceAll("\\[MYSQL:CFG_PASSWORD]", tMysqlConfigDBServer.getPassword())
		
		.replaceAll("\\[MYSQL:LOG_IP]", tMysqlLogDBServer.getServerIp())
		.replaceAll("\\[MYSQL:LOG_PORT]", tMysqlLogDBServer.getServerPort()+"")
		.replaceAll("\\[MYSQL:LOG_DATANAME]", tMysqlLogDBServer.getDbName())
		.replaceAll("\\[MYSQL:LOG_USER]", tMysqlLogDBServer.getUserName())
		.replaceAll("\\[MYSQL:LOG_PASSWORD]", tMysqlLogDBServer.getPassword());
		
		if(tMemcacheDBServer != null){
			value = value.replaceAll("\\[MEM:IP]", tMemcacheDBServer.getServerIp())
			.replaceAll("\\[MEM:PORT]", tMemcacheDBServer.getServerPort()+"");
		}
		
		value = value.replaceAll("\\[CONFIG:SYSTEM_PORT]", chatServer.getServerPort()+"")
		.replaceAll("\\[CONFIG:SYSTEM_NUM]", chatServer.getServerId()+"")
		.replace("[CONFIG:SYSTEM_KEY]", tGameServer.getServerCommunicationKey());
		
		return value;
	}
	
	
	/**
	 * 生成gameFrame文件
	 * @param gameStr
	 * @param tGameServer 
	 * @param tGameServer
	 * @param secondaryServer
	 * @param orderServer 
	 * @param battleServer 
	 * @param tDbServer
	 * @param tMemcacheDBServer 
	 * @param tMysqlLogDBServer 
	 * @param tMysqlConfigDBServer 
	 * @return
	 */
	//战斗服务器文件 符号替换说明
//	------------------
//	[MYSQL:CFG_IP]         mysql配置数据库服务器IP
//	[MYSQL:CFG_PORT]       mysql配置数据库端口
//	[MYSQL:CFG_DATANAME]   mysql 配置数据库名
//	[MYSQL:CFG_USER]       mysql 配置数据库用户名
//	[MYSQL:CFG_PASSWORD]   mysql 配置数据库密码
//	---------------------------------------------
//	[CONFIG:SYSTEM_PORT]   系统开放端口号  12372
//	[CONFIG:SYSTEM_NUM]    系统编号
//	[CONFIG:SYSTEM_KEY]    系统通讯key
//	---------------------------------------------
	private static String generateBattleServer(String gameStr,
			TGameServer tGameServer, TSecondaryServer battleServer,
			TDbServer tMysqlConfigDBServer,
			TDbServer tMysqlLogDBServer) {

		String value = gameStr.replaceAll("\\[CONFIG:SYSTEM_PORT]", battleServer.getServerPort()+"")
		.replaceAll("\\[CONFIG:SYSTEM_NUM]", battleServer.getServerId()+"")
		.replaceAll("\\[CONFIG:SYSTEM_PORT1]", (battleServer.getServerPort()+1)+"")
		.replace("[CONFIG:SYSTEM_KEY]", tGameServer.getServerCommunicationKey());
		
		if(tMysqlLogDBServer != null){
			value =value.replaceAll("\\[MYSQL:CFG_IP]", tMysqlConfigDBServer.getServerIp())
			.replaceAll("\\[MYSQL:CFG_PORT]", tMysqlConfigDBServer.getServerPort()+"")
			.replaceAll("\\[MYSQL:CFG_DATANAME]", tMysqlConfigDBServer.getDbName())
			.replaceAll("\\[MYSQL:CFG_USER]", tMysqlConfigDBServer.getUserName())
			.replaceAll("\\[MYSQL:CFG_PASSWORD]", tMysqlConfigDBServer.getPassword());
		}
	
		
		
		return value;
	}

//	public static void main(String[] args) {
//		
//		 SAXReader saxReader = new SAXReader(); 
//		 Document document;
//		 String gameValue ;
//		 XMLWriter writer;
//		//替换数值
//		try {
//			saxReader.setEncoding("UTF-8");
//			document = saxReader.read(new File("F:/applicationContext_gameserver.xml"));
//			gameValue = document.asXML();
//			document = DocumentHelper.parseText(gameValue);
//			
//			 writer = new XMLWriter(
//	             new FileOutputStream("F:/app.xml")); //保存文档
//				//流写入
//				writer.write(document);
//				
//				writer.close();
//		} catch (DocumentException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
		
}
