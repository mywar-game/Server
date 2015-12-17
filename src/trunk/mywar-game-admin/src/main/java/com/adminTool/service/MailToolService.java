package com.adminTool.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.AdminMail;
import com.adminTool.bo.AdminMailTool;
import com.adminTool.bo.DropTool;
import com.adminTool.bo.GoodsLimitNumber;
import com.adminTool.dao.AdminMailDao;
import com.adminTool.dao.AdminMailToolDao;
import com.adminTool.dao.GoodsLimitNumberDao;
import com.dataconfig.bo.SystemTool;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class MailToolService {
	
	private AdminMailToolDao adminMailToolDao;
	private AdminMailDao adminMailDao;
	private GoodsLimitNumberDao goodsLimitNumberDao;
	private Map<String, Integer> limitNumber ;
	private Map<String, String> limitName ;
	
	public Map<String, String> findTools() {
		Map<String, String> tools = new HashMap<String, String>();
		adminMailToolDao.closeSession(DBSource.ADMIN);
		List<AdminMailTool> list = adminMailToolDao.findAll();
		
		for (AdminMailTool adminMailTool : list) {
			String toolId =  String.valueOf(adminMailTool.getToolType() + "," + String.valueOf(adminMailTool.getToolId()));
			tools.put(toolId, adminMailTool.getToolName());
		}
		
		return tools;
	}
	
	public Map<String, String> findSysTools() {
		TTreasureConstantService treasureConstantService = ServiceCacheFactory
				.getServiceCache().getService(TTreasureConstantService.class);
		Map<String, String> tools = new HashMap<String, String>();
		
		List<SystemTool> list = treasureConstantService.getToolList();
		
		for (SystemTool systemTool : list) {
			String toolId =  String.valueOf(systemTool.getType() + "," + String.valueOf(systemTool.getToolId()));
			tools.put(toolId, systemTool.getName());
		}
		
		return tools;
	}
	
	public void findLimitMap() {
		limitNumber = new HashMap<String, Integer>();
		limitName = new HashMap<String, String>();
		
		adminMailToolDao.closeSession(DBSource.ADMIN);
		List<GoodsLimitNumber> list = goodsLimitNumberDao.findAll();
		
		for (GoodsLimitNumber goodsLimitNumber : list) {
			String toolId =  String.valueOf(goodsLimitNumber.getToolType() + "," + String.valueOf(goodsLimitNumber.getToolId()));
			limitNumber.put(toolId, goodsLimitNumber.getLimitNumber());
			limitName.put(toolId, goodsLimitNumber.getToolName());
			
		}

	}
	
	

	public void deleteMail(int adminMailId) {
		adminMailDao.closeSession(DBSource.ADMIN);
		String query = "delete from AdminMail as am where am.adminMailId = '" + adminMailId + "'";
		adminMailDao.execute(query);
	}
	
	public void updateMail(AdminMail adminMail) {
		adminMailDao.closeSession(DBSource.ADMIN);
		adminMailDao.update(adminMail, DBSource.ADMIN);
	}
	
	public IPage<AdminMail> findAllMail(Integer currentPage, Integer pageSize) {
		adminMailDao.closeSession(DBSource.ADMIN);
		return adminMailDao.findPage("from AdminMail order by system_mail_id desc ", new ArrayList<Object>(), pageSize, currentPage);
	}
	
	public AdminMail findOneMail(int adminMailId) {
		adminMailDao.closeSession(DBSource.ADMIN);
		return adminMailDao.loadBy("adminMailId", adminMailId, DBSource.ADMIN);
	}
	
	public void addMail(AdminMail adminMail) {
		adminMailDao.save(adminMail, DBSource.ADMIN);
	}
	
	
	public void setAdminMailToolDao(AdminMailToolDao adminMailToolDao) {
		this.adminMailToolDao = adminMailToolDao;
	}

	public void setAdminMailDao(AdminMailDao adminMailDao) {
		this.adminMailDao = adminMailDao;
	}

	public GoodsLimitNumberDao getGoodsLimitNumberDao() {
		return goodsLimitNumberDao;
	}

	public void setGoodsLimitNumberDao(GoodsLimitNumberDao goodsLimitNumberDao) {
		this.goodsLimitNumberDao = goodsLimitNumberDao;
	}
	
	public Map<String, List<DropTool>> getDropToolsMap(String toolIds){
		Map<String, List<DropTool>> map = new HashMap<String, List<DropTool>>();
		String tools[] = toolIds.split("\\|");
		for(String tool : tools){
			String toolData[] = tool.split(",");
			DropTool dropTool = new DropTool(Integer.parseInt(toolData[0]),Integer.parseInt(toolData[1]),Integer.parseInt(toolData[2]));
			if(map.containsKey(toolData[3])){
				List<DropTool> dropToolList = map.get(toolData[3]);
				if(merge(dropToolList,dropTool))
					dropToolList.add(dropTool);
				map.put(toolData[3], dropToolList);
			}else{
				List<DropTool> dropToolList = new ArrayList<DropTool>();
				dropToolList.add(dropTool);
				map.put(toolData[3], dropToolList);
			}
		}
		return map;
	}



	public List<DropTool> getDropToolsList(String toolIds) {
		List<DropTool> dropToolList = new ArrayList<DropTool>();
		String tools[] = toolIds.split("\\|");
		
		for(String tool : tools){
			
			String toolData[] = tool.split(",");
			DropTool dropTool = new DropTool(Integer.parseInt(toolData[0]),Integer.parseInt(toolData[1]),Integer.parseInt(toolData[2]));
			if(merge(dropToolList,dropTool))
				dropToolList.add(dropTool);
		}
		return dropToolList;
	}



	private boolean merge(List<DropTool> dropToolList , DropTool dropTool) {
		
		for(DropTool dropToolTemp : dropToolList){
			if(dropToolTemp.getToolType()==dropTool.getToolType()&&dropToolTemp.getToolId()==dropTool.getToolId()){
				dropToolTemp.setNumber(dropToolTemp.getNumber()+dropTool.getNumber());
				return false;
			}
		}
		return true;
		
	}
	
	

	public String check(List<DropTool> dropToolsList) {
		
		findLimitMap();
		
		for(DropTool dropTool : dropToolsList){
			String toolId =  String.valueOf(dropTool.getToolType() + "," + String.valueOf(dropTool.getToolId()));
			if(limitNumber.containsKey(toolId)){
				if(dropTool.getNumber() > limitNumber.get(toolId)){
					return limitName.get(toolId)+" 的发放数量超过限制："+limitNumber.get(toolId);
				}
			}
			
		}
		return "";
	}
	
	public Integer findToolIdByToolName(String toolName){
		
		adminMailDao.closeSession(DBSource.ADMIN);
		return adminMailToolDao.loadBy("toolName", toolName, DBSource.ADMIN).getToolId();
		
		
	}
	
	
}
