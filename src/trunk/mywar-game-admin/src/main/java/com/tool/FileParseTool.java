package com.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class FileParseTool {
	public static void readExcel(File file) {
		try {
			InputStream is = new FileInputStream(file);
			Workbook wb = Workbook.getWorkbook(is);
			int wbNum = wb.getNumberOfSheets();
			for (int i = 0; i < wbNum; i++) {
				Sheet sheet = wb.getSheet(i);
				String sheetName = sheet.getName();
				System.out.println("sheetName=" + sheetName);
				if (sheet != null) {
					// 获取表格总列数
					int rsColumns = sheet.getColumns();
					// 获取表格总行数
					int rsRows = sheet.getRows();
					// 循环文件里的数据
					for (int j = 0; j < rsRows; j++) {
						Cell[] cells = sheet.getRow(j);
						for (int k = 0; k < rsColumns; k++) {
							System.out.print(cells[k].getContents() + "----");
						}
						System.out.println();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
