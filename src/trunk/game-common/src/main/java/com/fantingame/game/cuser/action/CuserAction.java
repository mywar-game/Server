package com.fantingame.game.cuser.action;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.utils.EncryptUtil;
import com.fantingame.game.cuser.model.UserMapper;
import com.fantingame.game.cuser.service.UserMapperService;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.user.ReqLoadUserMapper;
import com.fantingame.game.msgbody.user.ReqPament;
import com.fantingame.game.msgbody.user.UserToken;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.MsgBuilder;

@GameAction
public class CuserAction {
	@Autowired
	private UserMapperService userMapperService;
	
	
	public ICodeAble loadUserToken(Msg msg,Channel channel){
    	ReqLoadUserMapper reqLoadUserMapper = msg.decodeBody(ReqLoadUserMapper.class);
		LogSystem.debug("logic开始创建用户token,partnerUserId="+reqLoadUserMapper.getPartnerUserId()+",time="+System.currentTimeMillis());
		UserMapper userMapper = userMapperService.loadUserMapper(reqLoadUserMapper.getPartnerUserId(), reqLoadUserMapper.getServerId(), reqLoadUserMapper.getPartnerId(), reqLoadUserMapper.getQn(), reqLoadUserMapper.getImei(), reqLoadUserMapper.getMac(), reqLoadUserMapper.getIdfa(),reqLoadUserMapper.getIp(),reqLoadUserMapper.getMobile());
		UserToken userToken = createUserToken(userMapper,reqLoadUserMapper.getExtInfo());
		LogSystem.debug("logic开始成功返回"+",time="+System.currentTimeMillis());
    	return userToken;
	}
    public ICodeAble payment(Msg msg,Channel channel){
    	ReqPament pament = msg.decodeBody(ReqPament.class);
    	BigDecimal amount = new BigDecimal(Double.valueOf(pament.getAmount()));
    	int gold = Integer.valueOf(pament.getGold());
    	userMapperService.doPayment(pament.getPartnerId(), pament.getServerId(), pament.getPartnerUserId(), pament.getChannel(), pament.getOrderId(), amount, gold, pament.getUserIp(), pament.getRemark());
    	return MsgBuilder.EMPTY_BODY;
    }
    
	private UserToken createUserToken(UserMapper userMapper,String extInfo) {
		UserToken ut = new UserToken();
		ut.setToken(EncryptUtil.getMD5(userMapper.getUserId() + System.currentTimeMillis() + UUID.randomUUID()));
		ut.setPartnerUserId(userMapper.getPartnerUserId());
		ut.setPartnerId(userMapper.getPartnerId());
		ut.setServerId(userMapper.getServerId());
		ut.setUserId(userMapper.getUserId());
		ut.setExtInfo(extInfo);
		return ut;
	}
}
