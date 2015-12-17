package com.fantingame.game.gamecenter.controller.paycallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.dao.ServerListDao;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.partener.kaiying.KaiYingSASdk;
import com.fantingame.game.gamecenter.partener.kaiying.KaiYingTwPaymentObj;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class KaiYingSAController {
	private static Logger LOG = Logger.getLogger(KaiYingSAController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;
	@Autowired
	private ServerListDao serverListDao;

	@RequestMapping(value = "/webApi/kaiYingSAPayment.do", method = RequestMethod.POST)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		PartnerService ps = serviceFactory.getBean(PartenerEnum.KaiYingSA.getPartenerId());
		KaiYingTwPaymentObj data = new KaiYingTwPaymentObj();
		data.setTs(req.getParameter("ts"));
		data.setSig(req.getParameter("sig"));
		data.setKda(req.getParameter("kda"));
		data.setUserId(req.getParameter("user_id"));
		data.setSid(req.getParameter("sid"));
		data.setNumber(req.getParameter("number"));
		data.setAmount(req.getParameter("amount"));
		data.setRoleId(req.getParameter("role_id"));
		data.setPartnerOrderId(req.getParameter("order_id"));
		data.setActive1(req.getParameter("active1"));
		data.setActive2(req.getParameter("active2"));
		data.setPayRef(req.getParameter("pay_ref"));
		data.setAppExtra1(req.getParameter("app_extra1"));
		data.setAppExtra2(req.getParameter("app_extra2"));
		try {
			if(ps.doPayment(data)){
				map.put("ret", 0);
				map.put("msg", "充值成功");
			}else{
				map.put("ret", -1);
				map.put("msg", "充值失败,请查看日志");
			}
		} catch (Exception e) {
			LOG.error("kaiyingtw payment error!",e);
			map.put("ret", -1);
			map.put("msg", "游戏服出现异常");
		}
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		ModelAndView modelView = new ModelAndView();
		modelView.setView(view);
		return modelView;
	}
	
	/**
	 * 恺英平台充值总入口
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/webApi/kaiYingSAIOSPayment.do", method = RequestMethod.POST)
	public ModelAndView kaiying(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		ModelAndView modelView = new ModelAndView();
		if(KaiYingSASdk.instance().getPartnerId(Integer.parseInt(req.getParameter("sid")))==4004){//android
			PartnerService ps = serviceFactory.getBean(PartenerEnum.KaiYingSAAndr.getPartenerId());
			KaiYingTwPaymentObj data = new KaiYingTwPaymentObj();
			data.setTs(req.getParameter("ts"));
			data.setSig(req.getParameter("sig"));
			data.setKda(req.getParameter("kda"));
			data.setUserId(req.getParameter("user_id"));
			data.setSid(req.getParameter("sid"));
			data.setNumber(req.getParameter("number"));
			data.setAmount(req.getParameter("amount"));
			data.setRoleId(req.getParameter("role_id"));
			data.setPartnerOrderId(req.getParameter("order_id"));
			data.setActive1(req.getParameter("active1"));
			data.setActive2(req.getParameter("active2"));
			data.setPayRef(req.getParameter("pay_ref"));
			data.setAppExtra1(req.getParameter("app_extra1"));
			data.setAppExtra2(req.getParameter("app_extra2"));
			try {
				if(ps.doPayment(data)){
					map.put("ret", 0);
					map.put("msg", "充值成功");
				}else{
					map.put("ret", -1);
					map.put("msg", "充值失败,请查看日志");
				}
			} catch (Exception e) {
				LOG.error("kaiyingtw payment error!",e);
				map.put("ret", -1);
				map.put("msg", "游戏服出现异常");
			}
		}else{//ios
			map = GameApiSdk.getInstance().kaiying(req);
		}
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}
	
	
	/**
	 * 恺英获取服务器列表
	 * 
	 * @return
	 */
	// TODO 实现获取服务器列表
	@RequestMapping("/webApi/getKySAServerList.do")
	public ModelAndView getKyServerList(HttpServletRequest req) {
		List<GameServer> list = serverListDao.getAllServerList();
		List<Map<String, String>> slist = new ArrayList<Map<String,String>>();
		if(list!=null && list.size()>0){
			for(GameServer server : list){
				Map<String, String> map = new HashMap<String, String>();
				map.put("server_name", server.getServerId()+" "+server.getServerName());
				map.put("server_id", KaiYingSASdk.instance().getServerId(server.getServerId())+"");
				map.put("server_status", server.getStatus()+"");
				slist.add(map);
			}
		}
		List<Map<String, String>> slist1 = GameApiSdk.getInstance().getServers();
		if(slist1!=null && slist1.size()>0){
			slist.addAll(slist1);
		}
		Map<String, List<Map<String, String>>> sMap = new HashMap<String, List<Map<String,String>>>();
		sMap.put("slist", slist);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ret", 0);
		map.put("msg", sMap);
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}
	
	/**
	 * 恺英获取服务器列表
	 * 
	 * @return
	 */
	// TODO 实现获取服务器列表
	@RequestMapping("/webApi/getSAServers.do")
	public ModelAndView getServers(HttpServletRequest req) {
		List<GameServer> list = serverListDao.getAllServerList();
		List<Map<String, String>> slist = new ArrayList<Map<String,String>>();
		if(list!=null && list.size()>0){
			for(GameServer server : list){
				Map<String, String> map = new HashMap<String, String>();
				map.put("server_name", server.getServerId()+" "+server.getServerName());
				map.put("server_id", KaiYingSASdk.instance().getServerId(server.getServerId())+"");
				map.put("server_status", server.getStatus()+"");
				slist.add(map);
			}
		}
		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();
		map.put("slist", slist);
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}
	
	/**
	 * 恺英获取服务器列表
	 * 
	 * @return
	 */
	// TODO 实现获取服务器列表
	@RequestMapping("/webApi/getSAUser.do")
	public ModelAndView getUser(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		int kingnetid = Integer.parseInt(req.getParameter("kingnetid"));
		int sid = Integer.parseInt(req.getParameter("sid"));
		Map<String, Object> user = null;
		if(KaiYingSASdk.instance().getPartnerId(sid)==4003){//ios的查询
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(KaiYingSASdk.instance().getServerId(sid), KaiYingSASdk.instance().getPartnerId(sid)+"");
			user = GameApiSdk.getInstance().getUser(kingnetid+"", KaiYingSASdk.instance().getServerId(sid), KaiYingSASdk.instance().getPartnerId(sid)+"", gameServer);
		}else{//android的查询
			user = GameApiSdk.getInstance().getUserTemp(kingnetid+"", sid+"");
		}
		if(user==null){
			result.put("ret", 3);
			result.put("msg", "查询出错");
		}else{
			result.put("ret", 0);
			result.put("msg", user);
		}
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(result);
		modelView.setView(view);
		return modelView;
	}
	
	
	/**
	 * 恺英获取服务器列表
	 * 
	 * @return
	 */
	// TODO 实现获取服务器列表
	@RequestMapping("/webApi/getSAUserTemp.do")
	public ModelAndView getUserTemp(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		int kingnetid = Integer.parseInt(req.getParameter("kingnetid"));
		int sid = Integer.parseInt(req.getParameter("sid"));
		GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(KaiYingSASdk.instance().getServerId(sid), KaiYingSASdk.instance().getPartnerId(sid)+"");
		Map<String, Object> user = GameApiSdk.getInstance().getUser(kingnetid+"", KaiYingSASdk.instance().getServerId(sid), KaiYingSASdk.instance().getPartnerId(sid)+"", gameServer);
		result.put("user", user);
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(result);
		modelView.setView(view);
		return modelView;
	}
	
	
	
	
}
