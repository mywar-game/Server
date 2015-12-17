package com.fantingame.game.log.service;


public interface IGameLog {
	/**
	 * 返回每日建库脚本
	 * @return
	 */
    public String createTableSQL(String date);
    
    
}
