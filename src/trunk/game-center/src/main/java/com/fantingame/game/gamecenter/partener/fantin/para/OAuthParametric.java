package com.fantingame.game.gamecenter.partener.fantin.para;

import java.util.Map;

import com.fantingame.game.gamecenter.partener.fantin.service.EucAPIException;
import com.fantingame.game.gamecenter.partener.fantin.service.EucService;
import com.fantingame.game.gamecenter.partener.fantin.service.JBody;
import com.fantingame.game.gamecenter.partener.fantin.service.JHead;
import com.fantingame.game.gamecenter.partener.fantin.service.Md5SignUtil;
import com.fantingame.game.gamecenter.partener.fantin.service.RequestInfo;

/**
 * 外部加密鉴权接口
 * 
 * @author jay
 * @since 2013.01.22
 * @version 1.0
 */
public class OAuthParametric implements AuthParametric<RequestInfo> {

	public JHead getVeriHeader(JBody jBody, EucService service, RequestInfo info) {
		// TODO Auto-generated method stub
		JHead head = service.buildDefaultRequestHeader();
		try {
			String sign = getSign(jBody,service.getKey());
			head.setSign(sign);
			head.setFlowCode(System.currentTimeMillis() + "");	// 流水号
			if(null!=info) {
				if(info.getQn()!=null)
					head.setQn(info.getQn());	//渠道
				if(info.getAppId()!=null)
					head.setAppId(info.getAppId());	//应用/游戏id
				if(info.getAgent()!=null)
					head.setAgent(info.getAgent());	//客户端agent
				if(info.getSource()!=null)
					head.setSource(info.getSource());
			}
		} catch (EucAPIException e) {
			// TODO error vericode
//			LOG.error("produce vericode is error...", e);
		}
		return head;
	}
	
	@SuppressWarnings("unchecked")
	public String getSign(JBody jBody,String key)throws EucAPIException{
		return Md5SignUtil.sign((Map)jBody, key);
		
	}
}
