package com.fantingame.game.api.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.api.common.util.ApiConstant;
import com.fantingame.game.api.common.util.Assert;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.server.admin.AdminAction_sendMailReq;
import com.fantingame.game.server.callback.ICallBackHandler;
import com.fantingame.game.server.http.HttpUtil;
import com.fantingame.game.server.http.IHttpHandler;
import com.fantingame.game.server.http.Request;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.msg.ServerType;

/**
 * 发送邮件
 * 
 * @author yezp
 */
public class SendMail implements IHttpHandler {

	@Override
	public Object handle(Request request, final Channel nettyChannel) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String toolIds = request.getParameter("toolIds");
		String lodoIds = request.getParameter("lodoIds");
		String target = request.getParameter("target");
		String date = request.getParameter("date");
		String sourceId = request.getParameter("sourceId");
		String partner = request.getParameter("partner");
		
		Assert.notEmtpy(title, ApiConstant.PARAM_ERROR, "邮件标题不能为空.title[" + title + "]");
		Assert.notEmtpy(content, ApiConstant.PARAM_ERROR, "邮件内容不能为空.content[" + content + "]");
		Assert.notEmtpy(target, ApiConstant.PARAM_ERROR, "目标类型不能为空.target[" + target + "]");
		Assert.notEmtpy(sourceId, ApiConstant.PARAM_ERROR, "源ID不能为空.sourceId[" + sourceId + "]");
		Assert.notEmtpy(date, ApiConstant.PARAM_ERROR, "date不能为空.date[" + date + "]");
		
		long sendDate = Long.valueOf(date);
		AdminAction_sendMailReq req = new AdminAction_sendMailReq();
		req.setTitle(title);
		req.setContent(content);
		req.setToolIds(toolIds);
		req.setLodoIds(lodoIds);
		req.setDate(sendDate);
		req.setSourceId(sourceId);
		req.setTarget(target);
		req.setPartner(partner);		
		
		final Map<String, Object> map = new HashMap<String, Object>();
		MsgDispatchCenter.disPatchServerMsg(ServerType.LOGIC_SERVER, "AdminAction_sendMail", req, new ICallBackHandler() {
			@Override
			public void onSuccess(Msg msg, com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("发送邮件成功。");
				map.put("rc", SystemConstant.SUCCESS_CODE);
				HttpUtil.renderSuccess(nettyChannel, map);
			}
			
			@Override
			public void OnFail(Msg msg, com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("发送邮件失败。");
				map.put("rc", msg.getMsgHead().getErrorCode());
				HttpUtil.renderSuccess(nettyChannel, map);				
			}
		});	
		
		return null;
	}

	@Override
	public String getMappingUrl() {
		return "/gameApi/sendMail.do";
	}

}
