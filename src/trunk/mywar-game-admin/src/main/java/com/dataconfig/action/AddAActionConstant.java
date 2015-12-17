package com.dataconfig.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.dataconfig.bo.AActionConstant;
import com.dataconfig.service.AActionConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddAActionConstant extends ALDAdminActionSupport implements ModelDriven<AActionConstant> {

	private static final long serialVersionUID = 4659206707898614455L;
	
	private AActionConstant actionConstant = new AActionConstant();
	
	public String execute() {
		try {
			String desc = URLDecoder.decode(actionConstant.getActionDesc(), "UTF-8").trim();
			actionConstant.setActionDesc(desc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		AActionConstantService actionConstantService = ServiceCacheFactory.getServiceCache().getService(AActionConstantService.class);
		actionConstantService.addAActionConstant(actionConstant);
		return SUCCESS;
	}
	
	@Override
	public AActionConstant getModel() {
		// TODO Auto-generated method stub
		return actionConstant;
	}

	public AActionConstant getActionConstant() {
		return actionConstant;
	}

	public void setActionConstant(AActionConstant actionConstant) {
		this.actionConstant = actionConstant;
	}
}
