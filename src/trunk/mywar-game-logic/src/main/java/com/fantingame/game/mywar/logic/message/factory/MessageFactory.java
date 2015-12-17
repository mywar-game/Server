package com.fantingame.game.mywar.logic.message.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fantingame.game.msgbody.notify.message.MessageBO;
import com.fantingame.game.mywar.logic.message.constant.MessageHelper;

/**
 * 跑马灯消息工厂类
 * 
 * @author jacky
 * 
 */
public class MessageFactory {

	public static List<MessageBO> getMessage(Map<String, String> params,String content) {
			List<MessageBO> msgList = MessageHelper.parse(content);
			return render(msgList, params);
	}

	private static List<MessageBO> render(List<MessageBO> msgList, Map<String, String> params) {
		List<MessageBO> ml = new ArrayList<MessageBO>();
		for (MessageBO message : msgList) {

			MessageBO msg = new MessageBO();
			String text = message.getTxt();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				text = text.replace("#" + entry.getKey() + "#", entry.getValue());
			}
			msg.setTxt(text);
			msg.setCor(message.getCor());

			ml.add(msg);
		}

		return ml;
	}

	public static void main(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", "1233456");
		params.put("content", "fucking bitch");
		//System.out.println(MessageFactory.getMessage(params, "<color:255,255,255>#userId#</color><color:255,255,255>#content#</color>"));
		System.out.println(MessageFactory.getChatContent(params, "<color:255,255,255>#userId#</color><color:255,255,255>#content#</color>"));
	}
	
	public static String getChatContent(Map<String, String> params, String text) {
		for (Map.Entry<String, String> entry : params.entrySet()) {
			text = text.replace("#" + entry.getKey() + "#", entry.getValue());
		}
		
		return text;
	}
}
