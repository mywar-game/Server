package com.system.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.framework.common.ALDAdminActionSupport;
import com.framework.log.LogSystem;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TGameServer;

public class DownloadingZip extends ALDAdminActionSupport implements ModelDriven<TGameServer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 用于把文件读入内存,和struct中配置的变量一致

	private InputStream inputStream; 
	 
    private String fileName;
    
    public String downLoadingApp() {
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*******封装文件名*******/
//		fileName = "application.zip";
		/*******转换编码后才能支持下载文件中存在中文文件名,但是在服务器端看到的将会是乱码类型的文件名************/
//		fileName =  new String(fileName.getBytes(), "ISO8859-1"); 
		/********获取本地存储文件的路径********/
		String path = ServletActionContext.getServletContext().getRealPath("/file");
		LogSystem.info(path);
//		String destFile = path + fileName;
		/** **********实现下载************ */
		if("\\".equals(File.separator)){
			path += "\\" + fileName;
		}
		if ("/".equals(File.separator)) {
			path += "/" + fileName;
		}
		File file = new File(path);
		LogSystem.info(file.getPath());
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
    }

	@Override
	public TGameServer getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}
