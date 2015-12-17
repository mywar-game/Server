package com.fantingame.game.gamecenter.partener.fantin.example;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.fantingame.game.gamecenter.partener.fantin.service.EucAPIException;
import com.fantingame.game.gamecenter.partener.fantin.service.EucToken;
import com.fantingame.game.gamecenter.partener.fantin.service.JBean;
import com.fantingame.game.gamecenter.partener.fantin.service.JBody;
import com.fantingame.game.gamecenter.partener.fantin.service.JDesc;
import com.fantingame.game.gamecenter.partener.fantin.service.JHead;
import com.fantingame.game.gamecenter.partener.fantin.service.JReason;
import com.fantingame.game.gamecenter.partener.fantin.util.GsonUtil;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class GSONTest {
	static Type headType = new TypeToken<JHead>() {}.getType();
	static Type bodyType = new TypeToken<Map<String, Object>>() {}.getType();
	static Type descType = new TypeToken<List<JReason>>() {}.getType();
	static Type beanType = new TypeToken<Map<String, JsonElement>>() {}.getType();
	
	public static void main(String[] args) throws EucAPIException {
		JHead jhead = new JHead();
		jhead.setPartnerId("1234");
		jhead.setAppId("4321");
		
		JDesc jdesc=new JDesc();
		jdesc.addReason("1", "error1");
		jdesc.addReason("2", "error2");
		
		JBody jbody=new JBody();
		jbody.putContent("token", new EucToken("token111"));

		JBean jbean=new JBean();
		jbean.setHead(jhead);
		jbean.setDesc(jdesc);
		jbean.setBody(jbody);
		String json=GsonUtil.toJson(jbean);
		System.out.println(json);
		JBean newBean=GsonUtil.extraJsonBean(json);
		JBody body = newBean.getBody();
		System.out.println(body.get("token"));
	}

}
