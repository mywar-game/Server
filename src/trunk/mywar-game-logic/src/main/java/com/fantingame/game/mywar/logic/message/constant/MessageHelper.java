package com.fantingame.game.mywar.logic.message.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fantingame.game.msgbody.notify.message.ColorBO;
import com.fantingame.game.msgbody.notify.message.MessageBO;

public class MessageHelper {

	public static Pattern pattern = Pattern
			.compile("<color:([^>]*)>([^<]*)</color>");

	public static List<MessageBO> parse(String s) {

		List<MessageBO> list = new ArrayList<MessageBO>();

		Matcher matcher = pattern.matcher(s);

		while (matcher.find()) {

			MessageBO message = new MessageBO();
			message.setTxt(matcher.group(2));
			String[] ss = matcher.group(1).split(",");
			int r = Integer.parseInt(ss[0]);
			int g = Integer.parseInt(ss[1]);
			int b = Integer.parseInt(ss[2]);
			
			ColorBO color = new ColorBO();
			color.setR(r);
			color.setG(g);
			color.setB(b);
			message.setCor(color);

			list.add(message);
		}

		return list;
	}

	public static void main(String[] args) {
		 System.out.println(parse("<color:255,255,255>这是红色</color>"));
	}
}
