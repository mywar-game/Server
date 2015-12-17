package com.fantingame.game.mywar.logic.goods.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.mywar.logic.goods.deal.IDealGoods;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.collect.Maps;

public class GoodsDealService implements BeanPostProcessor{
    private Map<Integer,IDealGoods> goodsSendLogicList = Maps.newHashMap();
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if(bean instanceof IDealGoods){
			IDealGoods send = ((IDealGoods)bean);
			goodsSendLogicList.put(send.getGoodsType().intValue, send);
			LogSystem.info("加载物品"+send.getGoodsType().remark+"的处理逻辑！");
		}
		return bean;
	}
	/**
	 * 发奖励
	 * @param userId
	 * @param rewards
	 * @return
	 */
	public CommonGoodsBeanBO sendGoods(String userId,String goodsIds,int goodsUseType){
		List<GoodsBeanBO> list = GoodsHelper.parseDropGoods(goodsIds);
		return sendGoods(userId,list,goodsUseType);
	}
    /**
     * 给奖励
     * @param userId
     * @param list
     * @return
     */
	public CommonGoodsBeanBO sendGoods(String userId,List<GoodsBeanBO> list,int goodsUseType){
		CommonGoodsBeanBO drop = new CommonGoodsBeanBO();
		if(list == null || list.size() == 0){
			LogSystem.warn("发送奖励的物品列表为空..userId"+userId);
		}
		for(GoodsBeanBO dropGoods:list){
			sendOneGoods(userId, dropGoods.getGoodsType(), dropGoods.getGoodsId(), dropGoods.getGoodsNum(), drop,goodsUseType);
		}
		return drop;
	}
	/**
	 * 减奖励
	 * @param userId
	 * @param goodsIds
	 * @return
	 */
	public CommonGoodsBeanBO reduceGoods(String userId,String goodsIds,int goodsUseType){
		List<GoodsBeanBO> list = GoodsHelper.parseDropGoods(goodsIds);
		return reduceGoods(userId,list,goodsUseType);
	}
	/**
	 * 减奖励
	 * @param userId
	 * @param reduceList
	 * @return
	 */
	public CommonGoodsBeanBO reduceGoods(String userId,List<GoodsBeanBO> reduceList,int goodsUseType){
		CommonGoodsBeanBO drop = new CommonGoodsBeanBO();
		if(reduceList == null || reduceList.size() == 0){
			LogSystem.warn("减掉奖励的物品列表为空..userId"+userId);
		}
		for(GoodsBeanBO dropGoods:reduceList){
			reduceOneGoods(userId, dropGoods.getGoodsType(), dropGoods.getGoodsId(), dropGoods.getGoodsNum(), drop,goodsUseType);
		}
		return drop;
	}
	/**
	 * 发送掉落物品
	 * @param userId
	 * @param goodsType
	 * @param goodsId
	 * @param goodsNum
	 * @return
	 */
	private void sendOneGoods(String userId,int goodsType,int goodsId,int goodsNum,CommonGoodsBeanBO drop,int goodsUseType){
		IDealGoods sender = goodsSendLogicList.get(goodsType);
		if(sender==null){
			throw new ServiceException(SystemConstant.FAIL_CODE, "不存在类型,goodsType["+goodsType+"],的处理逻辑。。userId["+userId+"],goodsId["+goodsId+"],goodsNum["+goodsNum+"]");
		}
		sender.sendGoods(userId, goodsType, goodsId, goodsNum,drop,goodsUseType);
	}
	/**
	 * 减物品
	 * @param userId
	 * @param goodsType
	 * @param goodsId
	 * @param goodsNum
	 * @param drop
	 */
	private void reduceOneGoods(String userId,int goodsType,int goodsId,int goodsNum,CommonGoodsBeanBO reduce,int goodsUseType){
		IDealGoods sender = goodsSendLogicList.get(goodsType);
		if(sender==null){
			throw new ServiceException(SystemConstant.FAIL_CODE, "不存在类型,goodsType["+goodsType+"],的处理逻辑。。userId["+userId+"],goodsId["+goodsId+"],goodsNum["+goodsNum+"]");
		}
		sender.reduceGoods(userId, goodsType, goodsId, goodsNum,reduce,goodsUseType);
	}
}
