package com.fantingame.game.api.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.netty.channel.Channel;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.api.common.util.ApiConstant;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.server.admin.AdminAction_addUserMoneyReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_addUserMoneyRes;
import com.fantingame.game.server.callback.ICallBackHandler;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.http.HttpUtil;
import com.fantingame.game.server.http.IHttpHandler;
import com.fantingame.game.server.http.Request;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.msg.ServerType;

/**
 * 发送钻石
 * 
 * @author yezp
 */
public class AddUserMoney implements IHttpHandler {

	@Override
	public Object handle(Request request, final Channel nettyChannel) {
		String allUser = request.getParameter("allUser");
		String diamondStr = request.getParameter("gold");
		String userIds = request.getParameter("userIdStr");
		if (allUser == null || allUser.equals("") 
				|| diamondStr == null || diamondStr.equals(""))
			throw new ServiceException(ApiConstant.PARAM_ERROR, "参数错误");
		
		int isAllUser = Integer.parseInt(allUser);
		if ((isAllUser != ApiConstant.SERVER && isAllUser != ApiConstant.USER)
				|| (isAllUser == ApiConstant.USER && StringUtils.isBlank(userIds)))
			throw new ServiceException(ApiConstant.PARAM_ERROR, "参数错误");
			
		final Map<String, Object> map = new HashMap<String, Object>();
		boolean isSendAll = (isAllUser == ApiConstant.SERVER ? true : false);
		int diamond = Integer.parseInt(diamondStr);
		
		AdminAction_addUserMoneyReq req = new AdminAction_addUserMoneyReq();
		req.setIsSendAll(isSendAll);
		req.setDiamond(diamond);
		req.setUserIds(userIds);
		
		MsgDispatchCenter.disPatchServerMsg(ServerType.LOGIC_SERVER, "AdminAction_addUserMoney", req, new ICallBackHandler() {
			@Override
			public void onSuccess(Msg msg, com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("发送钻石成功。");
				map.put("rc", SystemConstant.SUCCESS_CODE);
				AdminAction_addUserMoneyRes res = msg.decodeBody(AdminAction_addUserMoneyRes.class);
				map.put("result", res.getResult());
				HttpUtil.renderSuccess(nettyChannel, map);
			}
			
			@Override
			public void OnFail(Msg msg, com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("发送钻石成功。");
				map.put("rc", msg.getMsgHead().getErrorCode());
				AdminAction_addUserMoneyRes res = msg.decodeBody(AdminAction_addUserMoneyRes.class);
				map.put("result", res.getResult());
				HttpUtil.renderSuccess(nettyChannel, map);
			}
		});		
		
		return null;
	}

	@Override
	public String getMappingUrl() {
		return "/gameAdminApi/addUserMoney.do";
	}

}
