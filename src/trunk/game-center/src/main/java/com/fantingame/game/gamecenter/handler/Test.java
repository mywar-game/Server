package com.fantingame.game.gamecenter.handler;

import com.fantingame.game.gamecenter.bo.UserQueneInfo;

public class Test {

	public static void main(String args[]) {
		QueneManager queneManager = QueneManager.instance();
		
		for (int i = 0; i < 500; i++) {
			UserQueneInfo info = queneManager.getUserQueneInfo("s1", "");
			System.out.println("------------waitingTime:" + info.getWaitingTime());
			System.out.println("------------personNum:" + info.getPerson());
		}		
	}	
}
