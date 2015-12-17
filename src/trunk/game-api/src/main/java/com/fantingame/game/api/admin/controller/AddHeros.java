package com.fantingame.game.api.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.netty.channel.Channel;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.api.common.util.ApiConstant;
import com.fantingame.game.api.common.util.json.Json;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.server.admin.AdminAction_addHerosReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_addHerosRes;
import com.fantingame.game.msgbody.server.admin.AdminSendAttachBO;
import com.fantingame.game.server.callback.ICallBackHandler;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.http.HttpUtil;
import com.fantingame.game.server.http.IHttpHandler;
import com.fantingame.game.server.http.Request;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.msg.ServerType;

/**
 * 添加英雄
 * 
 * @author yezp
 */
public class AddHeros implements IHttpHandler {

	@Override
	public Object handle(Request request, final Channel nettyChannel) {
		String isAllUser = request.getParameter("isAllUser");
		String userIdStr = request.getParameter("userIdStr");
		String content = request.getParameter("content");
		if (isAllUser == null || isAllUser.equals(""))
			throw new ServiceException(ApiConstant.PARAM_ERROR, "参数错误");
		
		int isAllUserInt = Integer.parseInt(isAllUser);		
		if ((isAllUserInt != ApiConstant.SERVER && isAllUserInt != ApiConstant.USER)
				|| StringUtils.isBlank(content)
				|| (isAllUserInt == ApiConstant.USER && StringUtils.isBlank(userIdStr))) {
			throw new ServiceException(ApiConstant.PARAM_ERROR, "参数错误");
		}
		
		List<AdminSendAttachBO> sendAttachList = Json.toList(content, AdminSendAttachBO.class);
		boolean isSendAll = (isAllUserInt == ApiConstant.SERVER ? true : false);
		AdminAction_addHerosReq req = new AdminAction_addHerosReq();
		req.setUserIdStr(userIdStr);
		req.setIsSendAll(isSendAll);
		req.setSendAttachList(sendAttachList);
		
		final Map<String, Object> map = new HashMap<String, Object>();
		MsgDispatchCenter.disPatchServerMsg(ServerType.LOGIC_SERVER, "AdminAction_addHeros", req, new ICallBackHandler() {
			@Override
			public void onSuccess(Msg msg, com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("发送英雄成功。");
				map.put("rc", SystemConstant.SUCCESS_CODE);
				AdminAction_addHerosRes res = msg.decodeBody(AdminAction_addHerosRes.class);
				map.put("result", res.getResult());
				HttpUtil.renderSuccess(nettyChannel, map);
			}
			
			@Override
			public void OnFail(Msg msg, com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("发送英雄失败。");
				map.put("rc", msg.getMsgHead().getErrorCode());
				AdminAction_addHerosRes res = msg.decodeBody(AdminAction_addHerosRes.class);
				map.put("result", res.getResult());
				HttpUtil.renderSuccess(nettyChannel, map);				
			}
		});	
		return null;
	}

	@Override
	public String getMappingUrl() {
		return "/gameAdminApi/addHeros.do";
	}

}
