package com.fantingame.game.gamecenter.dao;

import java.util.List;

import com.fantingame.game.gamecenter.model.GameServer;


/**
 * 服务器列表管理
 * @author magical
 *
 */
public interface ServerListDao {
    /**
     * 根据partenId获取服务器列表
     * @return
     */
	public List<GameServer>  getServerListByPartenerId(String partenerId);
	/**
	 * 根据游戏服务器id和合作商id查询服务器列表
	 * @param serverId
	 * @param partenerId
	 * @return
	 */
	public GameServer getServerByServerIdAndPartenerId(String serverId,String partenerId);
	
	/**
     * 根据partenId获取服务器列表
     * @return
     */
	public List<GameServer>  getAllServerList();
	/**
	 * 重新加载
	 */
	public void reload();
}
