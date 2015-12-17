package com.dataconfig.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.sql.Timestamp;

import org.apache.commons.beanutils.BeanUtils;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.dataconfig.bo.TMallTreasureConstant;
import com.dataconfig.bo.TMallTreasureConstantId;
import com.dataconfig.service.TMallTreasureConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateTMallTreasureConstant  extends ALDAdminActionSupport implements ModelDriven<TMallTreasureConstant> {
	
	/**  */
	private static final long serialVersionUID = 3322498038619334250L;

	private TMallTreasureConstant tmallTreasureConstant = new TMallTreasureConstant();
	
	private String isCommit = "F";
	
	private Integer treasureId;
	
	private Integer category;
	
	public String execute() {
		TMallTreasureConstantService tMallTreasureConstantService = ServiceCacheFactory.getServiceCache().getService(TMallTreasureConstantService.class);
		if ("F".equals(isCommit)) {
			TMallTreasureConstantId id = new TMallTreasureConstantId();
			id.setCategory(category);
			id.setTreasureId(treasureId);
			try {
				String keyWord = URLDecoder.decode(tmallTreasureConstant.getTreasureName(), "UTF-8").trim();
				tmallTreasureConstant = tMallTreasureConstantService.findOneTMallTreasureConstant(id);
				tmallTreasureConstant.setTreasureName(keyWord);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return INPUT;
		} else {
			//记录日志
			AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
			adminChangeConstantLog.setSysNum(Integer.valueOf(super.getAdminUser().getExp1()));
			adminChangeConstantLog.setAdminName(super.getAdminUser().getAdminName());
			adminChangeConstantLog.setChangeTime(new Timestamp(System.currentTimeMillis()));
			adminChangeConstantLog.setConstantName("TMallTreasureConstant");
			adminChangeConstantLog.setChangeType(3);
			//改动细节
			StringBuffer changeDetail = new StringBuffer();
			//原来的商品
			TMallTreasureConstant beforeMallTreasureConstant = tMallTreasureConstantService.findOneTMallTreasureConstant(tmallTreasureConstant.getId());
			changeDetail.append("改动"+super.getText("mallTreasureConstant.id.category_"+tmallTreasureConstant.getId().getCategory())+"类型的"+tmallTreasureConstant.getId().getTreasureId()+"记录：<br/>");
			//属性名
			String propertyName = "";
			//原来的属性的值
			String beforeValue = "";
			//修改之后的属性的值
			String afterValue = "";
			//商城常量的所有属性
			for (Field field : TMallTreasureConstant.class.getDeclaredFields()) {
				propertyName = field.getName();
				try {
					//主键不能改，所以只在乎不是主键的时候
					if (!"id".equals(propertyName)) {
						beforeValue = BeanUtils.getProperty(beforeMallTreasureConstant, propertyName);
						afterValue = BeanUtils.getProperty(tmallTreasureConstant, propertyName);
						if (beforeValue != null && afterValue != null && !beforeValue.equals(afterValue)) {
							changeDetail.append("<b>"+super.getText("mallTreasureConstant."+propertyName)+"</b>由<b> "+beforeValue+" </b>改成<b> "+afterValue+" </b><br/>");
						}
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
			adminChangeConstantLog.setChangeDetail(changeDetail.toString());
			AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory.getServiceCache().getService(AdminChangeConstantLogService.class);
			adminChangeConstantLogService.saveOne(adminChangeConstantLog);
			
			tMallTreasureConstantService.updateTMallTreasureConstant(tmallTreasureConstant);
			return SUCCESS;
		}
	}
	
	@Override
	public TMallTreasureConstant getModel() {
		return tmallTreasureConstant;
	}

	public TMallTreasureConstant getTmallTreasureConstant() {
		return tmallTreasureConstant;
	}

	public void setTmallTreasureConstant(TMallTreasureConstant tmallTreasureConstant) {
		this.tmallTreasureConstant = tmallTreasureConstant;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public void setTreasureId(Integer treasureId) {
		this.treasureId = treasureId;
	}

	public Integer getTreasureId() {
		return treasureId;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getCategory() {
		return category;
	}
	
}
