package com.fantingame.game.mywar.logic.pack.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.pack.model.UserBackPackExtend;

public class UserBackPackExtendDaoMysql {
    @Autowired
	private Jdbc jdbcUser;
    /**
     * 添加背包扩展信息
     * @param userBackPackExtend
     * @return
     */
    public boolean addUserBackPackExtend(UserBackPackExtend userBackPackExtend){
    	return jdbcUser.insert(userBackPackExtend)>0;
    }
    /**
     * 更新用户背包扩展
     * @param userId
     * @param pos
     * @param toolId
     * @return
     */
    public boolean updateUserBackPackExtend(String userId,int pos,int toolId){
    	String sql = "update user_back_pack_extend set tool_id=? where user_id=? and pos=?";
    	SqlParameter sqlParameter = new SqlParameter();
    	sqlParameter.setInt(toolId);
    	sqlParameter.setString(userId);
    	sqlParameter.setInt(pos);
    	return jdbcUser.update(sql, sqlParameter)>0;
    }
    /**
     * 获取用户背包扩展信息列表
     * @param userId
     * @return
     */
    public List<UserBackPackExtend> getUserBackPackExtendsList(String userId){
    	String sql = "select * from user_back_pack_extend where user_id=?";
    	SqlParameter sqlParameter = new SqlParameter();
    	sqlParameter.setString(userId);
    	return jdbcUser.getList(sql, UserBackPackExtend.class, sqlParameter);
    }
}
