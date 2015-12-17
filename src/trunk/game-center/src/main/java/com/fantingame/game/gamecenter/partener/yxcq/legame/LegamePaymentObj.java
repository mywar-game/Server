package com.fantingame.game.gamecenter.partener.yxcq.legame;

import com.fantingame.game.gamecenter.partener.PaymentObj;

public class LegamePaymentObj extends PaymentObj {

	public LegamePaymentObj() {}
	private String order_no;
	private String create_time;
	private String amount;
	private String status;
	private String sstatus;
	private String cp_id;
	private String game_id;
	private String uid;
	private String cp_ext;
	private String checksign;
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSstatus() {
		return sstatus;
	}
	public void setSstatus(String sstatus) {
		this.sstatus = sstatus;
	}
	public String getCp_id() {
		return cp_id;
	}
	public void setCp_id(String cp_id) {
		this.cp_id = cp_id;
	}
	public String getGame_id() {
		return game_id;
	}
	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCp_ext() {
		return cp_ext;
	}
	public void setCp_ext(String cp_ext) {
		this.cp_ext = cp_ext;
	}
	public String getChecksign() {
		return checksign;
	}
	public void setChecksign(String checksign) {
		this.checksign = checksign;
	}
}
