package com.system.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.admin.util.Tools;
import com.adminTool.bo.Partner;
import com.adminTool.bo.PartnerQn;
import com.adminTool.service.PartnerQnService;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.service.HomePageService;
import com.system.service.TGameServerService;
import com.tool.email.MailUtil;

/**
 * 所有服务器信息列表
 * @author 
 * 2012-7-25
 */
public class AllServerInfoList extends ALDAdminStatsDatePageActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;
	/**服务器列表**/
	private Map<Integer,String> serverMap;
	/**渠道列表**/
	private List<Partner> partnerList ;
	/**二级渠道列表**/
	private List<PartnerQn> partnerQnList;
	/**查询服务器编号**/
	private Integer sysNum;
	/**查询渠道号**/
	private Integer channel;
	/**二级渠道号**/
	private String qn;
	private String isGrap = "F";
	/**result**/
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	/**总创角**/
	private Integer totalNewReg = 0;
	/**总付费人数**/
	private Integer totalPayUserNum = 0;
	/**总收入**/
	private Double totalPayAmount = 0d;
	/**总活跃人数**/
	private Integer totalAct = 0;

	private String message;
	
	private Double diamondUsed;
	
	private Integer isExportFlag = 0;

	@Override
	public String execute() {
		if (isExportFlag == 0) {
			return findResult(false);
		} else {
			generateExcel();
			return SUCCESS;
		}
	}
	
	/**
	 * 导出excel
	 */
	public synchronized void generateExcel() {
		this.findResult(true);
		try   {
			String path = ServletActionContext.getServletContext().getRealPath("/file");
			// windows下
			if ("\\".equals(File.separator)) {
				path+="/serverInfo.xls";
				path = path.replace("/", "\\");
			}
			// linux下
			if ("/".equals(File.separator)) {
				path+="/serverInfo.xls";
				path = path.replace("\\", "/");
			}
            //  打开文件
			WritableWorkbook book  =  Workbook.createWorkbook( new  File(path));
            //  生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet  =  book.createSheet( "Sheet1 " ,  0 );
			Label label1  =   new  Label( 0 ,  0 ,  "日期" );
			Label label2  =   new  Label( 1 ,  0 ,  "服务器" );
			Label label3  =   new  Label( 2 ,  0 ,  "平台" );
			Label label4 = new Label( 3, 0, "二级平台" );
			Label label5  =   new  Label( 4 ,  0 ,  "累计用户" );
			Label label6  =   new  Label( 5 ,  0 ,  "日活跃" );
			Label label7  =   new  Label( 6 ,  0 ,  "创角数" );
			Label label8  =   new  Label( 7 ,  0 ,  "付费人数" );
			Label label9  =   new  Label( 8 ,  0 ,  "新增付费率" );
			Label label10  =   new  Label( 9 ,  0 ,  "活跃付费率" );
			Label label11  =   new  Label( 10 ,  0 ,  "黏度" );
			Label label12  =   new  Label( 11 ,  0 ,  "收入" );
			Label label13  =   new  Label( 12 ,  0 ,  "ARPU" );
			Label label14  =   new  Label( 13 ,  0 ,  "ARPPU" );
			Label label15  =   new Label( 14, 0, "采集时间");
			Label label16  =   new Label( 15, 0, "当天钻石消耗");
			
//			Label label14  =   new  Label( 13 ,  0 ,  "总留存率" );
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);
			sheet.addCell(label6);
			sheet.addCell(label7);
			sheet.addCell(label8);
			sheet.addCell(label9);
			sheet.addCell(label10);
			sheet.addCell(label11);
			sheet.addCell(label12);
			sheet.addCell(label13);
			sheet.addCell(label14);
			sheet.addCell(label15);
			sheet.addCell(label16);
//			sheet.addCell(label14);
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				label1  =   new  Label( 0 ,  (i+1) ,  map.get("date"));
				label2  =   new  Label( 1 ,  (i+1) ,  map.get("server"));
				label3  =   new  Label( 2 ,  (i+1) ,  map.get("channel"));
				label4  =   new  Label( 3 ,  (i+1) ,  map.get("qn"));
				label5  =   new  Label( 4 ,  (i+1) ,  map.get("totalNum"));
				label6  =   new  Label( 5 ,  (i+1) ,  map.get("dayActive"));
				label7  =   new  Label( 6 ,  (i+1) ,  map.get("newUser"));
				label8  =   new  Label( 7 ,  (i+1) ,  map.get("payUserNum")+"%");
				label9  =   new  Label( 8 ,  (i+1) ,  map.get("newPayRate")+"%");
				label10  =   new  Label( 9 ,  (i+1) ,  map.get("activePayRate"));
				label11  =   new  Label( 10 ,  (i+1) ,  map.get("viscosity"));
				label12  =   new  Label( 11 ,  (i+1) ,  map.get("payAmount"));
				label13  =   new  Label( 12 ,  (i+1) ,  map.get("arpu"));
				label14 = new Label( 13, (i+1), map.get("arppu"));
				label15 = new Label( 14, (i+1), map.get("recordTime"));
				label16 = new Label( 15, (i+1), map.get("usedDiamond"));
//				label14  =   new  Label( 13 ,  (i+1) ,  map.get("totalRemainRate"));
				sheet.addCell(label1);
				sheet.addCell(label2);
				sheet.addCell(label3);
				sheet.addCell(label4);
				sheet.addCell(label5);
				sheet.addCell(label6);
				sheet.addCell(label7);
				sheet.addCell(label8);
				sheet.addCell(label9);
				sheet.addCell(label10);
				sheet.addCell(label11);
				sheet.addCell(label12);
				sheet.addCell(label13);
				sheet.addCell(label14);
				sheet.addCell(label15);
				sheet.addCell(label16);
//				sheet.addCell(label14);
			}
			//  写入数据并关闭文件
			book.write();
			book.close();

       }   catch  (Exception e)  {
           LogSystem.error(e, "generateExcel");
       }
	}
	
	/**
	 * 查询结果
	 * @return
	 */
	public String findResult(boolean isExport){
		TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		HomePageService homePageService = ServiceCacheFactory.getServiceCache().getService(HomePageService.class);
		PartnerQnService partnerQnService = ServiceCacheFactory.getServiceCache().getService(PartnerQnService.class);
		
		serverMap = gameServerService.findServerIdNameMapForOfficialServer();
		partnerList = partnerService.findPartnerList();
		partnerQnList = partnerQnService.getPartnerQnList();
		
		Map<String, Partner> partnerMap = new HashMap<String, Partner>();
		
		if(partnerList!=null && partnerList.size()>0){
			for(Partner p : partnerList){
				partnerMap.put(p.getPNum(), p);
			}
		}
		
		if(super.getStartDate()==null || super.getEndDate()==null){
			Date date = new Date();
			super.setStartDate(date);
			super.setEndDate(date);
		}
		String[] dates = new String[2];//查询日期
		dates[0] = DateUtil.dateToString(super.getStartDate(), DateUtil.LONG_DATE_FORMAT);
		dates[1] = DateUtil.dateToString(super.getEndDate(), DateUtil.LONG_DATE_FORMAT);
		List<Object> pageList = null;
		if(isExport || isGrap.equals("T")){//导出时查出全部数据
			pageList = homePageService.findData(dates, sysNum, channel, qn);
		}else{//不是导出则分页查询
			IPage<Object> page = homePageService.findPageData(dates, sysNum, channel, qn, super.getPageSize(), super.getToPage());
			if(page!=null){
				pageList = (List<Object>)page.getData();
				super.setTotalPage(page.getTotalPage());
				super.setTotalSize(page.getTotalSize());
			}
		}
		if(pageList!=null && pageList.size()>0){
			List<Object> totalList = homePageService.findTotalData(dates, sysNum, channel, qn);
			if(totalList!=null && totalList.size()>0){
				Object[] totalArr = (Object[])totalList.get(0);
				totalNewReg = Integer.valueOf(totalArr[0].toString());
				totalPayUserNum = Integer.valueOf(totalArr[1].toString());
				totalPayAmount = Double.valueOf(totalArr[2].toString());
				diamondUsed = Double.valueOf(totalArr[3].toString());
				totalAct = Integer.valueOf(totalArr[4].toString());
			}
//			Map<String,String> haveMap = new HashMap<String, String>();
			for(int i=0;i<pageList.size();i++){
				Map<String, String> map = new HashMap<String, String>();
				Object[] arr = (Object[])pageList.get(i);
				
				map.put("date", arr[0].toString());
				map.put("sysNum", arr[1].toString());
				map.put("server", serverMap.get(Integer.valueOf(arr[1].toString())));

				if(qn==null || "-1".equals(qn))
					map.put("qn", "全二级渠道");
				else{
					for(PartnerQn pq : partnerQnList){
						if(pq.getQn().equals(""+qn)){
							map.put("qn", pq.getName());
							break;
						}
					}
				}
				if(channel==null || channel.intValue()==0){
					map.put("channel", "全渠道");
				}else{
					map.put("channel", partnerMap.get(channel+"").getPName());
				}
//				String key = map.get("date")+map.get("sysNum")+map.get("channel");
//				if(haveMap.containsKey(key)){
//					continue;
//				}else{
//					haveMap.put(key,null);
//				}
				int totalNum = Integer.valueOf(arr[2].toString());//累积用户
				map.put("totalNum", totalNum+"");
				int dayActive = Integer.valueOf(arr[3].toString());//日活跃
				map.put("dayActive", dayActive+"");
				int newUser = Integer.valueOf(arr[5].toString());//创角数
				map.put("newUser", newUser+"");
				int payUserNum = Integer.valueOf(arr[6].toString());//当天充值人数
				int payAmount = new BigDecimal(arr[7].toString()).intValue();//收入
				int newRegPayUserNum = Integer.valueOf(arr[8].toString());//当天新增注册的付费人数
				double usedDiamond = Double.valueOf(arr[9].toString());//当天钻石消耗
				map.put("payUserNum", payUserNum+"");
				map.put("newPayRate", Tools.decimalFormat((double)newRegPayUserNum*100,(double)newUser, "#.00"));//新增付费率
				map.put("activePayRate", Tools.decimalFormat((double)payUserNum*100,(double)dayActive, "#.00"));//活跃付费率

				//开服30天才显示黏度
				if(DateUtil.isOpenThirtyDays(DateUtil.stringtoDate(arr[0].toString(), DateUtil.LONG_DATE_FORMAT), Integer.valueOf(arr[1].toString()))){
					int monthActive = Integer.valueOf(arr[4].toString());//月活跃
					map.put("viscosity", Tools.decimalFormat((double)dayActive,(double)monthActive, "#.00"));//黏度
				}
				map.put("payAmount", payAmount+"");
				map.put("arpu", Tools.decimalFormat((double)payAmount,(double)dayActive, "#.0"));//ARPU
				map.put("arppu", Tools.decimalFormat((double)payAmount,(double)payUserNum, "#.0"));//ARPPU
				map.put("usedDiamond", usedDiamond + "");
				//				map.put("totalRemainRate", Tools.decimalFormat((double)dayActive,(double)totalNum, "#.00"));//总留存率
				int hourIndex = Integer.valueOf(arr[10].toString());//数据的时间节点
				Date date = DateUtil.stringtoDate(arr[0].toString(), DateUtil.LONG_DATE_FORMAT);
				String str = DateUtil.dateToString(new Date(date.getTime()+(hourIndex+1)*SystemStatsDate.HALF_HOUR_INDEX-1000), DateUtil.FORMAT_ONE);
				map.put("recordTime", str);
				list.add(map);
			}
		}

		return SUCCESS;
	}
	
	public String test(){
		try {
			MailUtil mailUtil = new MailUtil("staff.easou.com", "allen_liu@staff.easou.com",
					"后台首页数据", "allen_liu@staff.easou.com", "easou8888");
			mailUtil.homePageinit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.message = "success";
		return SUCCESS;
	}

	/**
	 * @return the list
	 */
	public List<Map<String, String>> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}

	public Map<Integer, String> getServerMap() {
		return serverMap;
	}

	public void setServerMap(Map<Integer, String> serverMap) {
		this.serverMap = serverMap;
	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

	public Integer getSysNum() {
		return sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getIsGrap() {
		return isGrap;
	}

	public void setIsGrap(String isGrap) {
		this.isGrap = isGrap;
	}

	public Integer getTotalNewReg() {
		return totalNewReg;
	}

	public void setTotalNewReg(Integer totalNewReg) {
		this.totalNewReg = totalNewReg;
	}

	public Integer getTotalPayUserNum() {
		return totalPayUserNum;
	}

	public void setTotalPayUserNum(Integer totalPayUserNum) {
		this.totalPayUserNum = totalPayUserNum;
	}

	public Double getTotalPayAmount() {
		return totalPayAmount;
	}

	public void setTotalPayAmount(Double totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Double getDiamondUsed() {
		return diamondUsed;
	}

	public void setDiamondUsed(Double diamondUsed) {
		this.diamondUsed = diamondUsed;
	}

	public List<PartnerQn> getPartnerQnList() {
		return partnerQnList;
	}

	public void setPartnerQnList(List<PartnerQn> partnerQnList) {
		this.partnerQnList = partnerQnList;
	}

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}
	
	public Integer getTotalAct() {
		return totalAct;
	}

	public void setTotalAct(Integer totalAct) {
		this.totalAct = totalAct;
	}
	
	public Integer getIsExportFlag() {
		return isExportFlag;
	}

	public void setIsExportFlag(Integer isExportFlag) {
		this.isExportFlag = isExportFlag;
	}
}
