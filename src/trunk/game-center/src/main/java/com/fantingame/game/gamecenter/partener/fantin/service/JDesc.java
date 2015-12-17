package com.fantingame.game.gamecenter.partener.fantin.service;

import java.util.ArrayList;

public class JDesc extends ArrayList<JReason> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6170163626846069961L;

	public void addReason(String code, String desc) {		
		add(new JReason(code, desc));
	}

	public JReason getReason(int index) {
		return get(index);
	}
}
