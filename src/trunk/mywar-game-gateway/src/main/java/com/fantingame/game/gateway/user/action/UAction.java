package com.fantingame.game.gateway.user.action;

import java.util.concurrent.TimeoutException;

import org.springframework.util.StringUtils;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.gateway.server.manager.OfflineTokenManager;
import com.fantingame.game.gateway.server.manager.Session;
import com.fantingame.game.gateway.server.manager.SessionManager;
import com.fantingame.game.gateway.server.manager.TokenManager;
import com.fantingame.game.gateway.server.manager.UserManager;
import com.fantingame.game.gateway.server.manager.UserType;
import com.fantingame.game.gateway.user.util.UserUtil;
import com.fantingame.game.msgbody.common.codec.DataCodecFactory;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.server.user.UserAction_loginReq;
import com.fantingame.game.msgbody.test.TestAllStruct;
import com.fantingame.game.msgbody.user.ReqLoadUserMapper;
import com.fantingame.game.msgbody.user.ReqUserLogin;
import com.fantingame.game.msgbody.user.UserToken;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.callback.ICallBackHandler;
import com.fantingame.game.server.channel.AbstractChannel;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.msg.MsgBuilder;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.msg.ServerType;
@GameAction
public class UAction {
	
	  public final static int LOGIN_NOT_LOGIN = 1001;
	  
      public ICodeAble reg(final Msg msg, final Channel channel){
    	  
		  return null;
      }
	  
	  public ICodeAble test(Msg msg ,Channel channel){
	    	 TestAllStruct testAllStruct = msg.decodeBody(TestAllStruct.class);
	    	 LogSystem.info("收到以下内容"+testAllStruct.toString());
	    	 testAllStruct.setStringValue1("我收到了你的消息，这是响应消息");
	    	 return testAllStruct;
	  }
	  
	  /**
	   * 登录
	   * @param msg
	   * @param channel
	   * @return
	   */
      public ICodeAble login(final Msg msg, final Channel channel){
    	    System.out.println("===============================-----------登陆消息的sequense"+msg.getMsgHead().getMsgSequense());
		    ReqUserLogin reqLogin = msg.decodeBody(ReqUserLogin.class);
			UserToken userToken = checkUserLogin(reqLogin.getToken(), reqLogin.getUserId(), reqLogin.getPartnerId(), reqLogin.getServerId());
			if (userToken != null) {
				//踢出其他用户
            	UserUtil.kickOutOtherUser(reqLogin.getUserId());
            	//登录网关服务器
				UserManager.getInstance().userLoginIn(reqLogin.getUserId(),reqLogin.getToken(),UserType.COMMOM, channel);
			}
			//给逻辑服发送用户登录校验 消息
			UserAction_loginReq roleAction_loginReq = new UserAction_loginReq();
			roleAction_loginReq.setUserId(reqLogin.getUserId());
			Object o = channel.getAttribute(AbstractChannel.IP);
			roleAction_loginReq.setUserIp(o==null?"":(String)o);
			
			//通知逻辑服 进行登录操作
			MsgDispatchCenter.disPatchServerProxUserMsg(ServerType.LOGIC_SERVER, "UserAction_login", roleAction_loginReq,new ICallBackHandler() {
				@Override
				public void onSuccess(Msg msg2, Channel channel2) {
					Session session = SessionManager.getInstance().getSession(channel.getChannelId());
					session.sendMsg(MsgBuilder.buildResponseMsg(msg, msg2.getMsgHead().getErrorCode(), null));
				}
				@Override
				public void OnFail(Msg msg2, Channel channel2) {
					Session session = SessionManager.getInstance().getSession(channel.getChannelId());
					session.sendMsg(MsgBuilder.buildResponseMsg(msg, msg2.getMsgHead().getErrorCode(), null));
				}
			},reqLogin.getUserId());
			return null;
      }
      /**
       * 获取用户token
       * @param msg
       * @param channel
       * @return
       * @throws TimeoutException
       */
      public ICodeAble ulogin(final Msg inMsg, final Channel inChannel) throws TimeoutException{
	    	ReqLoadUserMapper reqLoadUserMapper = inMsg.decodeBody(ReqLoadUserMapper.class);
	    	
			LogSystem.debug("gateway开始创建用户token,partnerUserId="+reqLoadUserMapper.getPartnerUserId()+",time="+System.currentTimeMillis());
//			Msg result = MsgDispatchCenter.disPatchBlockServerMsg(ServerType.LOGIC_SERVER, "CuserAction_loadUserToken", reqLoadUserMapper);
//            if(result.getMsgHead().getErrorCode()!=SystemConstant.SUCCESS_CODE){
//            	throw new ServiceException(result.getMsgHead().getErrorCode(), "加载用户token失败"+",time="+System.currentTimeMillis());
//            }else{
//				LogSystem.debug("用户创建token成功。"+",time="+System.currentTimeMillis());
//				UserToken userToken = result.decodeBody(UserToken.class);
//				TokenManager.getInstance().setToken(userToken.getToken(), userToken);
//				return userToken;
//            }
			MsgDispatchCenter.disPatchServerMsg(ServerType.LOGIC_SERVER, "CuserAction_loadUserToken", reqLoadUserMapper, new ICallBackHandler() {
				@Override
				public void onSuccess(Msg msg,
						com.fantingame.game.server.channel.Channel channel) {
					LogSystem.debug("用户创建token成功。"+",time="+System.currentTimeMillis());
					UserToken userToken = msg.decodeBody(UserToken.class);
					TokenManager.getInstance().setToken(userToken.getToken(), userToken);
					Msg resultMsg = MsgBuilder.buildResponseMsg(inMsg, msg.getMsgHead().getErrorCode(), userToken);
					byte[] res = DataCodecFactory.getInstance()
							.encodeMsgServer(resultMsg);
					inChannel.write(res);
				}
				@Override
				public void OnFail(Msg msg,
						com.fantingame.game.server.channel.Channel channel) {
					LogSystem.warn("用户创建token失败，code="+msg.getMsgHead().getErrorCode()+",time="+System.currentTimeMillis());
					Msg resultMsg = MsgBuilder.buildResponseMsg(inMsg, msg.getMsgHead().getErrorCode(), MsgBuilder.EMPTY_BODY);
					byte[] res = DataCodecFactory.getInstance()
							.encodeMsgServer(resultMsg);
					inChannel.write(res);
				}
			});
            return null;
      }
	  /**
	   * 检查用户是否登陆
	   * @param token
	   * @param userId
	   * @param partnerId
	   * @param serverId
	   * @return
	   */
	  private UserToken checkUserLogin(String token, String userId, String partnerId, String serverId) {
			//如果在测试userId中 则直接返回token
			if (!StringUtils.hasText(token) || !StringUtils.hasText(userId) || !StringUtils.hasText(partnerId) || !StringUtils.hasText(serverId)) {
				throw new ServiceException(SystemConstant.FAIL_CODE, "参数不正确[" + token + ":" + userId + ":" + partnerId + "]");
			}
			UserToken userToken = TokenManager.getInstance().getToken(token);
			if (userToken == null) {
				UserToken offlineUserToken = OfflineTokenManager.getInstance().getToken(token);
				if(offlineUserToken==null){
					throw new ServiceException(LOGIN_NOT_LOGIN, "userToken为空[" + token + ":" + userId + ":" + partnerId + "]");
				}else{
					userToken = offlineUserToken;
					TokenManager.getInstance().setToken(token, offlineUserToken);
					OfflineTokenManager.getInstance().removeToken(token);
				}
			}
			if (!userId.equals(userToken.getUserId()) || !partnerId.equals(userToken.getPartnerId()) || !serverId.equals(userToken.getServerId())) {
				throw new ServiceException(LOGIN_NOT_LOGIN, "userToken与缓存不匹配[" + token + ":" + userId + ":" + partnerId + "]");
			}
			return userToken;
		}
}
