package com.fantingame.game.mywar.logic.hero.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fantingame.game.msgbody.client.hero.HeroAction_autoAmenbRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_careerClearReq;
import com.fantingame.game.msgbody.client.hero.HeroAction_careerClearRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_changeHeroPosReq;
import com.fantingame.game.msgbody.client.hero.HeroAction_changeHeroPosRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_changeSkillPosReq;
import com.fantingame.game.msgbody.client.hero.HeroAction_changeSkillPosRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_changeTeamLeaderReq;
import com.fantingame.game.msgbody.client.hero.HeroAction_changeTeamLeaderRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_disbandReq;
import com.fantingame.game.msgbody.client.hero.HeroAction_getUserBattleInfoReq;
import com.fantingame.game.msgbody.client.hero.HeroAction_getUserBattleInfoRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_getUserCareerClearInfoRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_heroInheritReq;
import com.fantingame.game.msgbody.client.hero.HeroAction_heroInheritRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_heroPromoteReq;
import com.fantingame.game.msgbody.client.hero.HeroAction_heroPromoteRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_promoteHeroStarReq;
import com.fantingame.game.msgbody.client.hero.HeroAction_promoteHeroStarRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_returnJobExpReq;
import com.fantingame.game.msgbody.client.hero.HeroAction_returnJobExpRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_studyLeaderSkillReq;
import com.fantingame.game.msgbody.client.hero.HeroAction_studyLeaderSkillRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_upgradeLeaderSkillReq;
import com.fantingame.game.msgbody.client.hero.HeroAction_upgradeLeaderSkillRes;
import com.fantingame.game.msgbody.client.hero.UserCareerInfoBO;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.MsgBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@GameAction
public class HeroAction {
	
    @Autowired
	private HeroService heroService;
    
	/**
	 * 改变英雄位置
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble changeHeroPos(Msg msg,Channel channel){
		String userId = msg.getMsgHead().getFromID();
		HeroAction_changeHeroPosReq req = msg.decodeBody(HeroAction_changeHeroPosReq.class);
		
		final List<UserHeroBO> resHeroList = Lists.newArrayList();
		heroService.changePos(userId, req.getUserHeroId(), req.getPos(), new ModuleEventHandler() {
			@Override
			public int order() {
				return 0;
			}
			@Override
			public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
				if(handlerType.equals("hero")){
					String userId = baseModuleEvent.getStringValue("userId", "");
					String userHeroId = baseModuleEvent.getStringValue("userHeroId", "");
					resHeroList.add(heroService.getUserHeroBO(userId, userHeroId));
				}
			}
			@Override
			public List<String> getHandlerType() {
				return null;
			}
		});
		HeroAction_changeHeroPosRes res = new HeroAction_changeHeroPosRes();
		res.setUpdateHeroList(resHeroList);
		return res;
	}
	
	/**
	 * 自动布阵
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble autoAmenb(Msg msg,Channel channel){
		String userId = msg.getMsgHead().getFromID();
		List<UserHeroBO> resHeroList = Lists.newArrayList();
		final Map<String, UserHeroBO> map = Maps.newConcurrentMap();
		heroService.autoAmenb(userId, new ModuleEventHandler() {
			@Override
			public int order() {
				return 0;
			}
			@Override
			public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
				if(handlerType.equals("hero")){
					String userId = baseModuleEvent.getStringValue("userId", "");
					String userHeroId = baseModuleEvent.getStringValue("userHeroId", "");
					
					map.put(userHeroId, heroService.getUserHeroBO(userId, userHeroId));
					// resHeroList.add(userHeroBO);
				}
			}
			@Override
			public List<String> getHandlerType() {
				return null;
			}
		});
		HeroAction_autoAmenbRes res = new HeroAction_autoAmenbRes();
		for (UserHeroBO userHeroBO : map.values()) {
			resHeroList.add(userHeroBO);
		}
		
		res.setUpdateHeroList(resHeroList);
		return res;
	}
	
	/**
	 * 改变技能位置
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble changeSkillPos(Msg msg,Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		HeroAction_changeSkillPosReq req = msg.decodeBody(HeroAction_changeSkillPosReq.class);
		HeroAction_changeSkillPosRes res = new HeroAction_changeSkillPosRes();
		res.setUpdateHeroSkillPosList(heroService.changeSkillPos(userId, req.getUserSkillId(), req.getPos()));
		return res;
	}
	
	/**
	 * 学习团长技能
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble studyLeaderSkill(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		HeroAction_studyLeaderSkillReq req = msg.decodeBody(HeroAction_studyLeaderSkillReq.class);
		HeroAction_studyLeaderSkillRes res = heroService.studyLeaderSkill(userId, req.getSystemHeroSkillId());
		return res;
	}
	
	/**
	 *  升级团长技能
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble upgradeLeaderSkill(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		HeroAction_upgradeLeaderSkillReq req = msg.decodeBody(HeroAction_upgradeLeaderSkillReq.class);
		HeroAction_upgradeLeaderSkillRes res = heroService.upgradeLeaderSkill(userId, req.getUserHeroSkillId(), req.getSkillToolBOList());
		return res;
	}
	
	/**
	 * 更换队长
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble changeTeamLeader(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		HeroAction_changeTeamLeaderReq req = msg.decodeBody(HeroAction_changeTeamLeaderReq.class);
		
		HeroAction_changeTeamLeaderRes res = this.heroService.changeTeamLeader(userId, req.getUserHeroId());		
		return res;
	}
	
	/**
	 * 遣散英雄
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble disband(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		HeroAction_disbandReq req = msg.decodeBody(HeroAction_disbandReq.class);
		
		this.heroService.disband(userId, req.getUserHeroId());
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 查看阵容
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getUserBattleInfo(Msg msg, Channel channel) {
		HeroAction_getUserBattleInfoReq req = msg.decodeBody(HeroAction_getUserBattleInfoReq.class);
		
		HeroAction_getUserBattleInfoRes res = this.heroService.getUserBattleInfo(req.getUserId());
		return res;
	}
	
	/**
	 * 升星
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble promoteHeroStar(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		HeroAction_promoteHeroStarReq req = msg.decodeBody(HeroAction_promoteHeroStarReq.class);
		
		HeroAction_promoteHeroStarRes res = this.heroService.promoteHeroStar(userId, req.getType(), req.getUserHeroId(), req.getTool());
		return res;
	}
	
	/**
	 * 英雄传承
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble heroInherit(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		HeroAction_heroInheritReq req = msg.decodeBody(HeroAction_heroInheritReq.class);
		
		HeroAction_heroInheritRes res = this.heroService.heroInherit(userId, req.getUserHeroId(), req.getTargetUserHeroId());
		return res;
	}
	
	/**
	 * 获取用户职业信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getUserCareerClearInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		List<UserCareerInfoBO> list = this.heroService.getUserCareerClearInfo(userId);
		HeroAction_getUserCareerClearInfoRes res = new HeroAction_getUserCareerClearInfoRes();
		res.setUserCareerInfoList(list);
		return res;
	}
	
	/**
	 * 解锁职业
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble careerClear(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		HeroAction_careerClearReq req = msg.decodeBody(HeroAction_careerClearReq.class);
		HeroAction_careerClearRes res = this.heroService.careerClear(userId, req.getDetailedCareerId());
		return res;
	}
	
	/**
	 * 解锁职业的回退
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble returnJobExp(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		HeroAction_returnJobExpReq req = msg.decodeBody(HeroAction_returnJobExpReq.class);
		HeroAction_returnJobExpRes res = this.heroService.returnJobExp(userId, req.getDetailedCareerId());		
		return res;
	}
	
	/**
	 * 英雄进阶
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble heroPromote(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		HeroAction_heroPromoteReq req = msg.decodeBody(HeroAction_heroPromoteReq.class);
		HeroAction_heroPromoteRes res = this.heroService.heroPromote(userId, req.getUserHeroId(), req.getProSystemHeroId());
		return res;
	}
}
