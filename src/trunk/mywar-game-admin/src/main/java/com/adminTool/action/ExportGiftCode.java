package com.adminTool.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

//import jxl.Workbook;
//import jxl.write.Label;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.adminTool.bo.GiftCode;
import com.adminTool.bo.SystemGiftbag;
import com.adminTool.dao.impl.mysql.GiftCodeDaoMysqlImpl;
import com.adminTool.service.SystemGiftbagService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.constant.ServerConstant;

/**
 * 导出礼包码
 * 
 * @author yezp
 */
public class ExportGiftCode extends ALDAdminActionSupport {

	private static final long serialVersionUID = -3165698371613214285L;
	private Integer giftBagId;
	private Integer gameWebId;

	public String execute() {
		SystemGiftbagService giftbagService = ServiceCacheFactory
				.getServiceCache().getService(SystemGiftbagService.class);

		Integer dbSourceId = gameWebId + ServerConstant.GIFT_DBKEY_BEGIN_NUMBER;
		SystemGiftbag systemGiftbag = giftbagService.getSystemGiftbag(giftBagId, dbSourceId);
		
		String tableName = "gift_code_" + systemGiftbag.getTablePrefix();
		GiftCodeDaoMysqlImpl giftCodeDaoMysqlImpl = ServiceCacheFactory.getServiceCache().getService(GiftCodeDaoMysqlImpl.class);
		List<GiftCode> codeList = giftCodeDaoMysqlImpl.getGiftCodeListById(tableName, giftBagId);

		String giftbagName = systemGiftbag.getName();
		String fileName = giftBagId.toString();

		// 分段
		int segment = (int) Math.ceil(codeList.size() / 500000.0);
		
 		for (int j = 1; j <= segment; j++) {
			String path = ServletActionContext.getServletContext().getRealPath(
					"/file");
			// windows下
			if ("\\".equals(File.separator)) {
				path += "/" + fileName + "_" + j + ".xlsx";
				path = path.replace("/", "\\");
			}
			// linux下
			if ("/".equals(File.separator)) {
				path += "/" + fileName + "_" + j + ".xlsx";
				path = path.replace("\\", "/");
			}

			// 打开文件
			try {
				File file = new File(path);
				if (!file.exists()) {
					boolean flag = file.createNewFile();
					if (!flag) {
						return "";
					}
				}
				FileOutputStream output = new FileOutputStream(file);
				
				XSSFWorkbook excel2007 = new XSSFWorkbook();
				 
				XSSFSheet sheet2007 = excel2007.createSheet();
				XSSFRow row2007 = sheet2007.createRow(0);
				
				XSSFCell cell0 = row2007.createCell(0);
				cell0.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式  
                cell0.setCellValue("礼包码");
                
                XSSFCell cell1 = row2007.createCell(1);
				cell1.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式  
                cell1.setCellValue("礼包名称");
                
                XSSFCell cell2 = row2007.createCell(2);
				cell2.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式  
                cell2.setCellValue("生效时间");
                
                XSSFCell cell3 = row2007.createCell(3);
				cell3.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式  
                cell3.setCellValue("失效时间");
                
                XSSFCell cell4 = row2007.createCell(4);
				cell4.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式  
                cell4.setCellValue("创建时间");
                
                for (int i = (j - 1) * 500000; i < codeList.size() && i < (j * 500000); i++) {
                	XSSFRow tempRow = sheet2007.createRow(i + 1);
                	GiftCode code = codeList.get(i);
                	
                	XSSFCell tempCell0 = tempRow.createCell(0);
                	tempCell0.setCellType(XSSFCell.CELL_TYPE_STRING);
                	tempCell0.setCellValue(code.getCode());
                	
                	XSSFCell tempCell1 = tempRow.createCell(1);
                	tempCell1.setCellType(XSSFCell.CELL_TYPE_STRING);
                	tempCell1.setCellValue(giftbagName);
                	
                	XSSFCell tempCell2 = tempRow.createCell(2);
                	tempCell2.setCellType(XSSFCell.CELL_TYPE_STRING);
                	tempCell2.setCellValue(code.getEffectiveTime().toString());
                	
                	XSSFCell tempCell3 = tempRow.createCell(3);
                	tempCell3.setCellType(XSSFCell.CELL_TYPE_STRING);
                	tempCell3.setCellValue(code.getLoseTime().toString());
                	
                	XSSFCell tempCell4 = tempRow.createCell(4);
                	tempCell4.setCellType(XSSFCell.CELL_TYPE_STRING);
                	tempCell4.setCellValue(code.getCreatedTime().toString());
                }
                
                excel2007.write(output);  
                output.close();
                System.out.println("-------【完成写入】-------");       
                
//				WritableWorkbook book = Workbook.createWorkbook(new File(path));
//				WritableSheet sheet = book.createSheet("Sheet1", 0);
//				Label l1 = new Label(0, 0, "礼包码");
//				Label l2 = new Label(1, 0, "礼包名称");
//				Label l3 = new Label(2, 0, "生效时间");
//				Label l4 = new Label(3, 0, "失效时间");
//				Label l5 = new Label(4, 0, "创建时间");
//
//				sheet.addCell(l1);
//				sheet.addCell(l2);
//				sheet.addCell(l3);
//				sheet.addCell(l4);
//				sheet.addCell(l5);
//
//				for (int i = (j - 1) * 300000; i < codeList.size() && i < (j * 300000); i++) {
//					GiftCode code = codeList.get(i);
//					l1 = new Label(0, (i-((j-1)*300000) + 1), code.getCode());
//					l2 = new Label(1, (i-((j-1)*300000) + 1), giftbagName);
//					l3 = new Label(2, (i-((j-1)*300000) + 1), code.getEffectiveTime().toString());
//					l4 = new Label(3, (i-((j-1)*300000) + 1), code.getLoseTime().toString());
//					l5 = new Label(4, (i-((j-1)*300000) + 1), code.getCreatedTime().toString());
//
//					sheet.addCell(l1);
//					sheet.addCell(l2);
//					sheet.addCell(l3);
//					sheet.addCell(l4);
//					sheet.addCell(l5);
//				}

//				book.write();
//				book.close();
			} catch (Exception e) {
				LogSystem.error(e, "exportCodeExcel");
			}
		}
		
		return SUCCESS;
	}

	public Integer getGiftBagId() {
		return giftBagId;
	}

	public void setGiftBagId(Integer giftBagId) {
		this.giftBagId = giftBagId;
	}

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

}
