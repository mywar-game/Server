package com.fantingame.game.gamecenter.partener.fantin.service;

public class JReason implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3903591394248613736L;
	public JReason() {
		
	}

	public JReason(String c,String d) {
		this.c=c;
		this.d=d;
	}
	private String c;
	private String d;
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
}
