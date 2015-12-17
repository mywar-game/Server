package com.adminTool.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.adminTool.bo.GiftCode;
import com.adminTool.bo.SystemGiftbag;
import com.adminTool.dao.impl.mysql.GiftCodeDaoMysqlImpl;
import com.adminTool.service.SystemGiftbagService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.RandomUtil;
import com.opensymphony.xwork2.ModelDriven;
import com.system.constant.ServerConstant;

/**
 * 生成礼包码
 * 
 * @author yezp
 */
public class CreateGiftCode extends ALDAdminActionSupport implements
		ModelDriven<GiftCode> {

	private static final long serialVersionUID = -6787746373703200247L;
	private GiftCode giftCode = new GiftCode();
	private String isCommit = "F";
	private List<SystemGiftbag> giftbagList;
	private Integer gameWebId;
	private Integer giftbagId;
	private Integer num;
	private Integer codeNum;

	public String execute() {
		Integer dbSourceId = gameWebId + ServerConstant.GIFT_DBKEY_BEGIN_NUMBER;
		SystemGiftbagService giftbagService = ServiceCacheFactory
				.getServiceCache().getService(SystemGiftbagService.class);

		if (isCommit.equals("F")) {
			SystemGiftbag giftbag = giftbagService.getSystemGiftbag(giftbagId,
					dbSourceId);
			giftCode.setGiftBagId(giftbag.getGiftbagId());
			giftCode.setGiftbagName(giftbag.getName());

			return INPUT;
		}

		// 批量生成礼包码		
		Date date = new Date();
		Timestamp createdTime = new Timestamp(date.getTime());
		List<GiftCode> newCodeList = new ArrayList<GiftCode>();
		
		// 获取表格前缀
		SystemGiftbag giftbag = giftbagService.getSystemGiftbag(giftCode.getGiftBagId(), dbSourceId);
		String tablePrefix = giftbag.getTablePrefix();	
		String tableName = "gift_code_" + tablePrefix;	
		
		// JDBC 连接
		GiftCodeDaoMysqlImpl giftCodeDaoMysqlImpl = ServiceCacheFactory.getServiceCache().getService(GiftCodeDaoMysqlImpl.class);
		// 目前的总数量
		int oringeNum = giftbag.getCodeNum();
		// 完成后的总数量
		int totalNum = num + oringeNum;
		
		// 直到所有插入成功
		while(oringeNum < totalNum){
			for (int i = 0; i < totalNum - oringeNum; i++) {
				GiftCode gCode = new GiftCode(giftCode.getGiftBagId(), createdTime,
						giftCode.getEffectiveTime(), giftCode.getLoseTime());
				
				gCode.setCode(tablePrefix + getCode(codeNum));
				newCodeList.add(gCode);
				
				// 每一千条插入一次
				if (i != 0 && i % 1000 == 0) {					
					giftCodeDaoMysqlImpl.addGiftCodeBatch(tableName, newCodeList);
					newCodeList.clear();
				}
			}
			
			if (newCodeList.size() > 0) {
				giftCodeDaoMysqlImpl.addGiftCodeBatch(tableName, newCodeList);
				newCodeList.clear();
			}
			oringeNum = giftCodeDaoMysqlImpl.getGiftCodeCount(tableName, giftCode.getGiftBagId());
		}

		giftCode.setGiftBagId(giftbag.getGiftbagId());
		giftCode.setGiftbagName(giftbag.getName());

		// 更新数量
		giftbag.setCodeNum(totalNum);
		giftbagService.updateSystemGiftbag(giftbag, dbSourceId);
		return INPUT;
	}

	/**
	 * 生成礼包码
	 * 
	 * @param str
	 * @param codeList
	 * @return
	 */
	/*public String getCode(String str, int serial, String curDate) {
		String code = "";
		if (str.length() > 0) {
			code = str + code;
		} else {
			code = RandomUtil.getRandomString(1);
		}

		// 前缀+日期+随机8位+序列码
		code = code + curDate + RandomUtil.getRandomString(8) + serial;
		return code;
	}*/
	
	
	public String getCode(int num) {	
		return RandomUtil.getRandomString(num);
	}

	public Integer getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(Integer codeNum) {
		this.codeNum = codeNum;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Integer getGiftbagId() {
		return giftbagId;
	}

	public void setGiftbagId(Integer giftbagId) {
		this.giftbagId = giftbagId;
	}

	public List<SystemGiftbag> getGiftbagList() {
		return giftbagList;
	}

	public void setGiftbagList(List<SystemGiftbag> giftbagList) {
		this.giftbagList = giftbagList;
	}

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

	public GiftCode getGiftCode() {
		return giftCode;
	}

	public void setGiftCode(GiftCode giftCode) {
		this.giftCode = giftCode;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public GiftCode getModel() {
		// TODO Auto-generated method stub
		return giftCode;
	}

}
