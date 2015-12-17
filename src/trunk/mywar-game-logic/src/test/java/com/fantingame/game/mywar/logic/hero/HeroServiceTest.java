package com.fantingame.game.mywar.logic.hero;


import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.framework.testcore.ServiceTest;
import com.fantingame.game.mywar.logic.activity.service.ActivityService;

public class HeroServiceTest extends ServiceTest {
 
	public void testGetInSceneHeroId(){
		ActivityService activityService = ServiceCacheFactory.getServiceCache().getService(ActivityService.class);
		activityService.receiveGiftBag("2c9b7df1dc1540479e5af031198f5127", "m1112p21a");
	}
}
