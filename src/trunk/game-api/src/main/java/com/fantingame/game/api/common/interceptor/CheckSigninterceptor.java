package com.fantingame.game.api.common.interceptor;

import java.util.Date;

import org.jboss.netty.channel.Channel;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.api.common.util.EncryptUtil;
import com.fantingame.game.server.config.LocalTools;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.http.IHttpPreHandler;
import com.fantingame.game.server.http.Request;


public class CheckSigninterceptor implements IHttpPreHandler {

	private String[] getValues(String queryString){
		String[] result=new String[]{};
		if(queryString!=null&&!queryString.equals("")){
			String[] tempArray = queryString.split("&");
			result = new String[tempArray.length-2];
			int i=0;
			for(String str:tempArray){
				if(str!=null&&!str.equals("")){
					String[] entryArray = str.split("=");
					if(!entryArray[0].equals("md5sign") && !entryArray[0].equals("timestamp")){
						result[i] = entryArray[1];
						i++;
					}
				}
			}
		}
		return result;
	}
	@Override
	public void preHandler(Request request, Channel channel) {
//		String signOld = request.getParameter("sign");
		String sign = request.getParameter("md5sign");
		long now = System.currentTimeMillis();
		String sendTime = request.getParameter("timestamp");
		String str = request.getRequestURI();
		String[] valuesArray = getValues(str);
		if(sendTime==null){
			throw new ServiceException(SystemConstant.FAIL_CODE, "发送时间字段没有填写");
		}
		long timestamp = Long.valueOf(sendTime);
		if ((now - timestamp) > 1000 * 60) {
			LogSystem.info("请求时间超出范围：" + timestamp + "(" + new Date(timestamp) + ")");
			throw new ServiceException(SystemConstant.FAIL_CODE, "请求时间超出范围（10s）");
		}
		String md5Str = "";
		for(String strValueTemp:valuesArray){
			md5Str = md5Str+strValueTemp;
		}
		md5Str = md5Str+timestamp + LocalTools.getLocalConfig().getMd5Key();
//		System.out.println("befor:"+md5Str);
		String md5 = EncryptUtil.getMD5(md5Str);
		LogSystem.info("after:"+md5);
		if (!md5.toLowerCase().equals(sign.toLowerCase())) {
			LogSystem.info("请求签名不正确：" + sign + "(" + md5 + ")");
			throw new ServiceException(SystemConstant.FAIL_CODE, "请求签名不正确");
		}
	}
	@Override
	public String getInterceptorUrl() {
		return "/gameAdminApi/";
	}

}
