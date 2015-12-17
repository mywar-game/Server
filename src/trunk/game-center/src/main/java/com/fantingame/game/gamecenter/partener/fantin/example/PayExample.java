package com.fantingame.game.gamecenter.partener.fantin.example;

import com.fantingame.game.gamecenter.partener.fantin.PayAPI;
import com.fantingame.game.gamecenter.partener.fantin.service.PayBean;

public class PayExample {

	public static void main(String[] args) {
		PayBean bean = PayAPI.getUserCurrency("999");
		System.out.println(bean.getEbNum());
	}
}
