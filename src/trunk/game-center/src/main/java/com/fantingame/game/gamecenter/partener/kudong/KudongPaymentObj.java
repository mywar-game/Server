package com.fantingame.game.gamecenter.partener.kudong;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class KudongPaymentObj extends PaymentObj {

	public KudongPaymentObj() {}
	
	private String uid;
	private String oid;
	private String gold;
	private String sid;
	private String time;
	private String eif;
	private String sign;
	
	@Override
	public String toString() {
		return "KudongPaymentObj [uid=" + uid + ", oid=" + oid + ", gold="
				+ gold + ", sid=" + sid + ", time=" + time + ", eif=" + eif
				+ ", sign=" + sign + "]";
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getEif() {
		return eif;
	}
	public void setEif(String eif) {
		this.eif = eif;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
