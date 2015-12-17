package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;


/**
 * UserLogoutLog entity. @author MyEclipse Persistence Tools
 */

public class UserLogoutLog  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String userId;
     private String ip;
     private Timestamp logoutTime;
     private Integer liveMinutes;
     /**
 	 * 角色名
 	 */
 	private String userName;
 	private int lodoId;

    // Constructors

    /** default constructor */
    public UserLogoutLog() {
    }

	/** minimal constructor */
    public UserLogoutLog(String userId, String ip, Timestamp logoutTime) {
        this.userId = userId;
        this.ip = ip;
        this.logoutTime = logoutTime;
    }
    
    /** full constructor */
    public UserLogoutLog(String userId, String ip, Date logoutTime, Integer liveMinutes, String userName,int lodoId) {
        this.userId = userId;
        this.ip = ip;
        this.logoutTime = new Timestamp(logoutTime.getTime());
        this.liveMinutes = liveMinutes;
        this.userName = userName;
        this.lodoId = lodoId;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    public Timestamp getLogoutTime() {
        return this.logoutTime;
    }
    
    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Integer getLiveMinutes() {
        return this.liveMinutes;
    }
    
    public void setLiveMinutes(Integer liveMinutes) {
        this.liveMinutes = liveMinutes;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getLodoId() {
		return lodoId;
	}

	public void setLodoId(int lodoId) {
		this.lodoId = lodoId;
	}

   








}