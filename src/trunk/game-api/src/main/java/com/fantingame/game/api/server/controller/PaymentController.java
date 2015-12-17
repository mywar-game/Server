package com.fantingame.game.api.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.api.common.util.EncryptUtil;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.user.ReqPament;
import com.fantingame.game.server.callback.ICallBackHandler;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.http.HttpUtil;
import com.fantingame.game.server.http.IHttpHandler;
import com.fantingame.game.server.http.Request;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.msg.ServerType;
/**
 * 游戏服务器充值API
 * 
 * 
 * 
 */
public class PaymentController implements IHttpHandler{

	private static final String MD5KEY="99712321$$%@ASDF1467KK98";
    //数据过期时间 60秒钟
	private static final int MAX_ALIVE_TIME = 60;
	
	@Override
	public Object handle(Request request, final Channel nettyChannel) {
		try {
			String partnerId = request.getParameter("partnerId");
			String serverId = request.getParameter("serverId");
			String partnerUserId = request.getParameter("partnerUserId");
			String channel = request.getParameter("channel");
			String orderId = request.getParameter("orderId");
			String strAmount = request.getParameter("amount");
			String strGold = request.getParameter("gold");
			String userIp = request.getParameter("userIp");
			String remark = request.getParameter("remark");
			String timestamp = request.getParameter("timestamp");
            String sign = request.getParameter("sign");
			//md5校验
			String mysign =  EncryptUtil.getMD5((partnerId+serverId+partnerUserId+channel+orderId+strAmount+strGold+ timestamp + MD5KEY));
			LogSystem.info("do payment.partnerId[" + partnerId + "], serverId[" + serverId + "], partnerUserId[" + partnerUserId + "], channel[" + channel + "], orderId[" + orderId + "],  amount[" + strAmount + "], gold["
    				+ strGold + "], userIp[" + userIp + "], remark[" + remark + "]");
			if(!sign.equals(mysign)){
            	LogSystem.info("do payment.partnerId[" + partnerId + "], serverId[" + serverId + "], partnerUserId[" + partnerUserId + "], channel[" + channel + "], orderId[" + orderId + "],  amount[" + strAmount + "], gold["
        				+ strGold + "], userIp[" + userIp + "], remark[" + remark + "]");
            	LogSystem.warn("md5校验失败,ServerMySign="+mysign+",sign="+sign);
            }else{
            	long timestampMilli = Long.valueOf(timestamp);
            	long serverTimeStampMilli = System.currentTimeMillis();
            	if(serverTimeStampMilli-timestampMilli>MAX_ALIVE_TIME*1000){
            		LogSystem.warn("该消息已时间过期,serverTime="+serverTimeStampMilli+",clientTime="+timestampMilli);
            	}else{
					ReqPament reqPament = new ReqPament(partnerId, serverId, partnerUserId, channel, orderId, strAmount, strGold, userIp, remark);
					MsgDispatchCenter.disPatchServerMsg(ServerType.LOGIC_SERVER, "UserAction_payment", reqPament, new ICallBackHandler() {
						@Override
						public void onSuccess(Msg msg,
								com.fantingame.game.server.channel.Channel channel) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("rc", msg.getMsgHead().getErrorCode());
							HttpUtil.renderSuccess(nettyChannel, map);
						}
						@Override
						public void OnFail(Msg msg,
								com.fantingame.game.server.channel.Channel channel) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("rc", msg.getMsgHead().getErrorCode());
							HttpUtil.renderSuccess(nettyChannel, map);
						}
					});
            	}
            }
		}catch (Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rc",SystemConstant.FAIL_CODE);
			HttpUtil.renderSuccess(nettyChannel, map);
			LogSystem.error(e,"");
		}
		return null;
	}
	@Override
	public String getMappingUrl() {
		return "/gameApi/payment.do";
	}
}
