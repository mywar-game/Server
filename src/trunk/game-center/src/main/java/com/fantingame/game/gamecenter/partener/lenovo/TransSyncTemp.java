package com.fantingame.game.gamecenter.partener.lenovo;

import java.io.Serializable;

/** 
* @Description: trans_sync_temp 
* @CreateDate: 2011-06-14
* @UpdateDate: 2011-06-14
* @author wenzhonghu
* @version 2.0
* @Copyright:爱贝,Copyright (c) 2011
*/
public class TransSyncTemp implements Serializable {

	private static final long serialVersionUID = 1L;
	private String appid;
	private String exorderno;
	private String transid;
	private String waresid;
	private Integer feetype;
	private Integer money;
	private Integer count;
	private Integer result;
	private Integer transtype;
	private String transtime;
	private String cpprivate;
	private String paytype;
	
	public TransSyncTemp() {
	}

	public String getExorderno() {
		return exorderno;
	}

	public void setExorderno(String exorderno) {
		this.exorderno = exorderno;
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public String getWaresid() {
		return waresid;
	}

	public void setWaresid(String waresid) {
		this.waresid = waresid;
	}

	public Integer getFeetype() {
		return feetype;
	}

	public void setFeetype(Integer feetype) {
		this.feetype = feetype;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getTranstype() {
		return transtype;
	}

	public void setTranstype(Integer transtype) {
		this.transtype = transtype;
	}

	public String getTranstime() {
		return transtime;
	}

	public void setTranstime(String transtime) {
		this.transtime = transtime;
	}



	public String getCpprivate() {
		return cpprivate;
	}


	public void setCpprivate(String cpprivate) {
		this.cpprivate = cpprivate;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

}
