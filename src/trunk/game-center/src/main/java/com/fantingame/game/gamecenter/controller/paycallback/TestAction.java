package com.fantingame.game.gamecenter.controller.paycallback;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestAction {
	@RequestMapping("/test.do")
	public ModelAndView test() {
		return new ModelAndView("test"); 
	}
}
