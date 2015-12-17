package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.IntegralMall;
import com.adminTool.dao.IntegralMallDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

/**
 * 积分商城配置Service
 * @author wxz
 *
 */
public class IntegralMallService {
private IntegralMallDao integralMallDao;

public IntegralMallDao getIntegralMallDao() {
	return integralMallDao;
}

public void setIntegralMallDao(IntegralMallDao integralMallDao) {
	this.integralMallDao = integralMallDao;
}

 


/**
 * 查询积分商城
 * 
 */
public IPage<IntegralMall> findIntegralMallPageList(int toPage,
		int defaultPagesize){
	integralMallDao.closeSession(DBSource.CFG);
	IPage<IntegralMall>list=integralMallDao.findPage("from IntegralMall", new ArrayList<Object>(), defaultPagesize,
			toPage);
	return list;
}

/**
 * 获取积分商城列表
 * 
 */

public List<IntegralMall>getIntegralMallList(){
	return integralMallDao.find("from IntegralMall", DBSource.CFG);
}

/**
 * 根据id查询
 * 
 */
public IntegralMall getOneIntegralMall(Integer mall_id){
	return integralMallDao.loadBy("mall_id", mall_id, DBSource.CFG);
}

/**
 * 修改积分商城配置
 * 
 */
public void updateIntegralMall(IntegralMall integralMall){
	integralMallDao.update(integralMall, DBSource.CFG);
}
/**
 * 删除积分商城配置
 * 
 */
public void delIntegralMall(Integer mall_id){
	IntegralMall integralMall=getOneIntegralMall(mall_id);
	integralMallDao.remove(integralMall,DBSource.CFG);
}

/**
 *增加积分商城配置 
 *
 */
public void addIntegralMall(IntegralMall integralMall){
	integralMallDao.save(integralMall, DBSource.CFG);
}
/**
 * 查询积分商城的最后一条记录
 * 
 */
public IntegralMall findLastIntegralMall(){
	List<IntegralMall>list=integralMallDao.find("FROM IntegralMall ORDER BY mall_id DESC LIMIT 1 ",DBSource.CFG);
if(list==null||list.size()<=0){
	return null;
}
return list.get(0);
}


}
