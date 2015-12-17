package com.fantingame.game.gamecenter.partener.uc;

import com.fantingame.game.gamecenter.partener.PaymentObj;

/**
 * 支付回调的响应内容类。
 */
public class PayCallbackResponse extends PaymentObj{
	String sign = "";
	PayCallbackResponseData data;
	
	public String getSign(){
		return this.sign;
	}
	public void setSign(String sign){
		this.sign =sign;
	}
	
	public PayCallbackResponseData getData(){
		return this.data;
	}
	public void setData(PayCallbackResponseData data){
		this.data = data;
	}
	
	public class PayCallbackResponseData{
		private String orderId;
		private int gameId;
		private int serverId;
		private int ucid;
		private int payWay;
		private String amount;
		private String callbackInfo;
		private String orderStatus;
		private String failedDesc="";
		
		public String getOrderId(){
			return this.orderId;
		}
		public void setOrderId(String orderId){
			this.orderId = orderId;
		}
		public int getGameId(){
			return this.gameId;
		}
		
		public void setGameId(int gameId){
			this.gameId = gameId;
		}
		public int getServerId(){
			return this.serverId;
		}
		
		public void setServerId(int serverId){
			this.serverId = serverId;
		}
		
		public int getUcid(){
			return this.ucid;
		}
		
		public void setUcid(int ucid){
			this.ucid = ucid;
		}
		public int getPayWay(){
			return this.payWay;
		}
		
		public void setPayWay(int payWay){
			this.payWay = payWay;
		}
		public String getAmount(){
			return this.amount;
		}
		
		public void setAmount(String amount){
			this.amount = amount;
		}
		
		public String getCallbackInfo(){
			return this.callbackInfo;
		}
		public void setCallbackInfo(String callbackInfo){
			this.callbackInfo = callbackInfo;
		}
		public String getOrderStatus(){
			return this.orderStatus;
		}
		public void setOrderStatus(String orderStatus){
			this.orderStatus = orderStatus;
		}
		public String getFailedDesc(){
			return this.failedDesc;
		}
		public void setFailedDesc(String failedDesc){
			this.failedDesc = failedDesc;
		}
	}

	
}
