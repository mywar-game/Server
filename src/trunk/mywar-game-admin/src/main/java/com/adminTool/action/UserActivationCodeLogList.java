package com.adminTool.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.dataconfig.bo.AActivationCode;
import com.dataconfig.service.ActivationCodeService;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserActivationCodeLog;
import com.log.service.UserActivationCodeLogService;

public class UserActivationCodeLogList extends ALDAdminLogPageActionSupport {

	/** **/
	private static final long serialVersionUID = -5493965342481798157L;
	
	/** **/
	private List<UserActivationCodeLog> logList = new ArrayList<UserActivationCodeLog>();
	
	/** **/
	private String searchCode;
	
	/** **/
	private Date createStartTime;

	/** **/
	private Date createEndTime;
	
	/** 搜索的渠道 **/
	private String searchPlatform;
	
//	/** **/
//	private Date effectiveStartTime;
//	
//	/** **/
//	private Date effectiveEndTime;
//	
//	/** **/
//	private Date activationStartTime;
//	
//	/** **/
//	private Date activationEndTime;
	
	private Map<Integer, String> treasureIDNameMap;
	
	private Map<Integer, String> equipmentIdNameMap;
	
	private List<List<Map<String, Integer>>> allRewardList = new ArrayList<List<Map<String, Integer>>>();
	
	public String execute() {
		this.findList();
		return SUCCESS;
	}
	
	public synchronized void generateExcel() {
		this.findList();
		System.out.println(logList);
		try   {
			String filepath = new UserActivationCodeLogList().getClass().getResource("/").toString().substring(5);
			filepath = filepath.substring(1, filepath.length()-16);
            //  打开文件
			WritableWorkbook book  =  Workbook.createWorkbook( new  File( filepath + "file/activationCode.xls" ));
            //  生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet  =  book.createSheet( "Sheet1 " ,  0 );
			//  在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			//  以及单元格内容为test
//			Label label  =   new  Label( 0 ,  0 ,  " asdf " );
			//  将定义好的单元格添加到工作表中
//			sheet.addCell(label);
			//生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
//			Number number  =   new  Number( 1 ,  0 ,  123 );
//			sheet.addCell(number);
			Label label1  =   new  Label( 0 ,  0 ,  "ActivationCode" );
			Label label2  =   new  Label( 1 ,  0 ,  "paltform" );
			Label label3  =   new  Label( 2 ,  0 ,  "effectiveTime" );
			Label label4  =   new  Label( 3 ,  0 ,  "reward" );
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			for (int i = 0; i < logList.size(); i++) {
				
				//奖励信息
				String reward = new String();
				List<Map<String, Integer>> rewardList = allRewardList.get(i);
				if (allRewardList.get(i) != null) {
					for (Map<String, Integer> rewardMap : rewardList) {
						if (rewardMap.get("category") == 1) {
							reward += treasureIDNameMap.get(rewardMap.get("targetId")) + "*" + rewardMap.get("num") + " ";
						}
						if (rewardMap.get("category") == 3) {
							reward += equipmentIdNameMap.get(rewardMap.get("targetId")) + "*" + rewardMap.get("num") + " ";
						}
						if (rewardMap.get("category") == 1) {
							reward += rewardMap.get("num") + this.getText("activationCodeList.rewardInfo.rewardId_1_" + rewardMap.get("targetId")) + " ";
						}
					}
				}
				
				label1  =   new  Label( 0 ,  (i+1) ,  logList.get(i).getActivationCode() );
				label2  =   new  Label( 1 ,  (i+1) ,  logList.get(i).getPlatform() );
				label3  =   new  Label( 2 ,  (i+1) ,  sdf.format(logList.get(i).getEffectiveTime()));
				label4  =   new  Label( 3 ,  (i+1) ,  reward);
				sheet.addCell(label1);
				sheet.addCell(label2);
				sheet.addCell(label3);
				sheet.addCell(label4);
				
			}
			//  写入数据并关闭文件
			book.write();
			book.close();

       }   catch  (Exception e)  {
           LogSystem.error(e, "generateExcel");
       }
	}

	private void findList() {
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		treasureIDNameMap = treasureConstantService.findTreasureIdNameMap();
		EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		equipmentIdNameMap = equipmentConstantService.findEquipmentIDNameMap();
		
		ActivationCodeService codeService = ServiceCacheFactory.getServiceCache().getService(ActivationCodeService.class);
		UserActivationCodeLogService logService = ServiceCacheFactory.getServiceCache().getService(UserActivationCodeLogService.class);
		IPage<AActivationCode> pageList = codeService.findPageList(searchPlatform, searchCode, createStartTime, createEndTime, super.getPageSize(), super.getToPage());
		if (pageList != null) {
			super.setTotalSize(pageList.getTotalSize());
			super.setTotalPage(pageList.getTotalPage());

			List<AActivationCode> codeList = (List<AActivationCode>)pageList.getData();
			if (codeList == null || codeList.size() == 0) {
				return;
			}
			
			StringBuffer codes = new StringBuffer();
			for (int i = 0; i < codeList.size(); i++) {
				codes.append("'").append(codeList.get(i).getActivationCode()).append("'");
				if (i < (codeList.size()-1)) {
					codes.append(",");
				}
			}
			Map<String, UserActivationCodeLog> map = logService.findMap(codes.toString());
			for (AActivationCode activationCode : codeList) {
				UserActivationCodeLog log = new UserActivationCodeLog();
				String code = activationCode.getActivationCode();
				log.setActivationCode(code);
				log.setCreateTime(activationCode.getCreateTime());
				log.setEffectiveTime(activationCode.getEffectiveTime());
				log.setPlatform(activationCode.getPlatform());
				if (map != null && map.size() > 0 && map.get(code) != null) {
					log.setUserId(map.get(code).getUserId());
					log.setActivationTime(map.get(code).getActivationTime());
					log.setName(map.get(code).getName());
				}
				logList.add(log);
				
				List<Map<String, Integer>> rewardList = new ArrayList<Map<String, Integer>>();
				String rewardInfo = activationCode.getReward();
				if (rewardInfo != null && !"".equals(rewardInfo)) {
					try {
						JSONArray rewardInfoJsonArr = new JSONArray(rewardInfo);
						for (int i = 0; i < rewardInfoJsonArr.length(); i++) {
							Map<String, Integer> rewardMap = new HashMap<String, Integer>();
							JSONObject treasureRewardJsonObj = rewardInfoJsonArr.getJSONObject(i);
							int targetId = treasureRewardJsonObj.getInt("targetId");
							int category = treasureRewardJsonObj.getInt("category");
							int num = treasureRewardJsonObj.getInt("num");
							rewardMap.put("targetId", targetId);
							rewardMap.put("category", category);
							rewardMap.put("num", num);
							rewardList.add(rewardMap);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				allRewardList.add(rewardList);
			}
			
		}
	}
	
	/**
	 * @return the searchCode
	 */
	public String getSearchCode() {
		return searchCode;
	}

	/**
	 * @param searchCode the searchCode to set
	 */
	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	/**
	 * @return the createStartTime
	 */
	public Date getCreateStartTime() {
		return createStartTime;
	}

	/**
	 * @param createStartTime the createStartTime to set
	 */
	public void setCreateStartTime(Date createStartTime) {
		this.createStartTime = createStartTime;
	}

	/**
	 * @return the createEndTime
	 */
	public Date getCreateEndTime() {
		return createEndTime;
	}

	/**
	 * @param createEndTime the createEndTime to set
	 */
	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}

//	/**
//	 * @return the effectiveStartTime
//	 */
//	public Date getEffectiveStartTime() {
//		return effectiveStartTime;
//	}
//
//	/**
//	 * @param effectiveStartTime the effectiveStartTime to set
//	 */
//	public void setEffectiveStartTime(Date effectiveStartTime) {
//		this.effectiveStartTime = effectiveStartTime;
//	}
//
//	/**
//	 * @return the effectiveEndTime
//	 */
//	public Date getEffectiveEndTime() {
//		return effectiveEndTime;
//	}
//
//	/**
//	 * @param effectiveEndTime the effectiveEndTime to set
//	 */
//	public void setEffectiveEndTime(Date effectiveEndTime) {
//		this.effectiveEndTime = effectiveEndTime;
//	}
//
//	/**
//	 * @return the activationStartTime
//	 */
//	public Date getActivationStartTime() {
//		return activationStartTime;
//	}
//
//	/**
//	 * @param activationStartTime the activationStartTime to set
//	 */
//	public void setActivationStartTime(Date activationStartTime) {
//		this.activationStartTime = activationStartTime;
//	}
//
//	/**
//	 * @return the activationEndTime
//	 */
//	public Date getActivationEndTime() {
//		return activationEndTime;
//	}
//
//	/**
//	 * @param activationEndTime the activationEndTime to set
//	 */
//	public void setActivationEndTime(Date activationEndTime) {
//		this.activationEndTime = activationEndTime;
//	}

	public void setLogList(List<UserActivationCodeLog> logList) {
		this.logList = logList;
	}

	public List<UserActivationCodeLog> getLogList() {
		return logList;
	}

	/**
	 * 获取 搜索的渠道 
	 */
	public String getSearchPlatform() {
		return searchPlatform;
	}

	/**
	 * 设置 搜索的渠道 
	 */
	public void setSearchPlatform(String searchPlatform) {
		this.searchPlatform = searchPlatform;
	}

	/**
	 * 获取 treasureIDNameMap 
	 */
	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap;
	}

	/**
	 * 设置 treasureIDNameMap 
	 */
	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap;
	}

	/**
	 * 获取 equipmentIdNameMap 
	 */
	public Map<Integer, String> getEquipmentIdNameMap() {
		return equipmentIdNameMap;
	}

	/**
	 * 设置 equipmentIdNameMap 
	 */
	public void setEquipmentIdNameMap(Map<Integer, String> equipmentIdNameMap) {
		this.equipmentIdNameMap = equipmentIdNameMap;
	}

	/**
	 * 获取 allRewardList 
	 */
	public List<List<Map<String, Integer>>> getAllRewardList() {
		return allRewardList;
	}

	/**
	 * 设置 allRewardList 
	 */
	public void setAllRewardList(List<List<Map<String, Integer>>> allRewardList) {
		this.allRewardList = allRewardList;
	}
	
}
