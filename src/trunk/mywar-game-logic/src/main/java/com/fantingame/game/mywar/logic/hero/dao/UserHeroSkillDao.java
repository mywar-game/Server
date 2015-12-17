package com.fantingame.game.mywar.logic.hero.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.hero.model.UserHeroSkill;

public interface UserHeroSkillDao {
	  /**
	   * 获取所有用户的技能
	   * @param userId
	   * @return
	   */
      public List<UserHeroSkill> getUserHeroSkillList(String userId);
      /**
       * 获取英雄身上的所有技能
       * @param userId
       * @param userHeroId
       * @return
       */
      public List<UserHeroSkill> getUserHeroSkillList(String userId,String userHeroId);
      
      /**
       * 获取用户的团长技能
       * 
       * @param userId
       * @param systemHeroSkillId
       * @return
       */
      public UserHeroSkill getUserHeroSkill(String userId, int systemHeroSkillId);
      
      /**
       * 根据用户英雄技能id获取
       * 
       * @param userId
       * @param userHeroSkillId
       * @return
       */
      public UserHeroSkill getUserHeroSkill(String userId, String userHeroSkillId);
      
      /**
       * 更新技能位置
       * @param userHeroSkillId
       * @param pos
       * @return
       */
      public boolean updateUserHeroSkillPos(String userId,String userHeroSkillId,int pos);
      
      /**
       * 更新技能等级
       * @param userHeroSkillId
       * @param level
       * @return
       */
      public boolean updateUserHeroSkillLevel(String userId,String userHeroSkillId,int level);
      /**
       * 更新技能中的英雄id
       * @param userId
       * @param userHeroSkillId
       * @param userHeroId
       * @return
       */
      public boolean updateUserHeroId(String userId,String userHeroSkillId,String userHeroId);
      /**
       * 添加技能
       * @param userHeroSkill
       * @return
       */
      public boolean addUserHeroSkill(UserHeroSkill userHeroSkill);
      /**
       * 批量添加用户技能对象
       * @param userId
       * @param userHeroSkillList
       * @return
       */
      public boolean addUserHeroSkillList(String userId,List<UserHeroSkill> userHeroSkillList);
      
      /**
       * 更新用户技能等级、经验
       * 
       * @param userId
       * @param userHeroSkillId
       * @param level
       * @param exp
       * @return
       */
      public boolean updateUserHeroSkill(String userId, String userHeroSkillId, int level, int exp);
}
