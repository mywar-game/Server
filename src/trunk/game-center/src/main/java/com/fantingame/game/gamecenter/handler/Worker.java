package com.fantingame.game.gamecenter.handler;


public class Worker {

	private boolean started = true;
	
	@SuppressWarnings("static-access")
	public void work() {
		try {
			QueneManager.instance().checkQueneInfo();
			
			Thread.currentThread().sleep(3000);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void init() {
		if (!started) {
			return;
		}

//		if (!Config.ins().isGameServer()) {
//			return;
//		}
//		started = true;

		new Thread(new Runnable() {			
			public void run() {
				while (true) {					
					work();
				}
			}
		}).start();
	}	
}
