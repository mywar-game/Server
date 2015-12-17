package com.fantingame.game.gamecenter.partener.yxcq.tengxun;

import java.util.HashMap;
import java.util.Map;

import com.fantingame.game.gamecenter.partener.PaymentObj;

/**
 * 参数变化频繁，不能把参数写死
 * @author Administrator
 *
 */
public class TengxunPayParamsObj extends PaymentObj {

	public TengxunPayParamsObj() {}
	
	private Map<String, String> params = new HashMap<String, String>();

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
}
