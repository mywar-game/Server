package com.adminTool.action;

import java.util.List;

import com.adminTool.service.SearchByLodoIdService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class SearchByLodoId extends ALDAdminActionSupport {
	private static final long serialVersionUID = 909397051721473295L; 
	private int lodo_id;
	private List<Object>list;
	private List<Object>list2;
	public List<Object> getList2() {
		return list2;
	}
	public void setList2(List<Object> list2) {
		this.list2 = list2;
	}
	private String s;
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	private String []user_id;
	public String[] getUser_id() {
		return user_id;
	}
	public void setUser_id(String[] user_id) {
		this.user_id = user_id;
	}
	public String getPartner_id() {
		return partner_id;
	}
	public int getUser_mapper_id() {
		return user_mapper_id;
	}
	public void setUser_mapper_id(int user_mapper_id) {
		this.user_mapper_id = user_mapper_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public String getServer_id() {
		return server_id;
	}
	public void setServer_id(String server_id) {
		this.server_id = server_id;
	}
	public String getPartner_user_id() {
		return partner_user_id;
	}
	public void setPartner_user_id(String partner_user_id) {
		this.partner_user_id = partner_user_id;
	}
	private int user_mapper_id;
	private String partner_id;
	private String server_id;
	private String partner_user_id;
	
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public int getLodo_id() {
		return lodo_id;
	}
	public void setLodo_id(int lodo_id) {
		this.lodo_id = lodo_id;
	}
	private String isCommit = "F";
	public String execute() {
		if(isCommit.equals("F")){
			return SUCCESS;
		}
		else{
		SearchByLodoIdService service=ServiceCacheFactory
				.getServiceCache().getService(SearchByLodoIdService.class);
		//list2=service.searchLodoId();
		list=service.searchbyLodoId(lodo_id);
		if(list.size()==0){
			return INPUT;
		}else{
	    Object[]object=(Object[]) list.get(0);
	 	partner_id=(String)object[2];
	    lodo_id= (Integer) object[0];
	   	user_mapper_id=(Integer) object[1];
	   	server_id=(String) object[3];
	   	partner_user_id=(String) object[4];
	   	list2=service.searchByLodoId2(lodo_id);
			Character [] character=(Character[]) list2.get(0);
			StringBuffer sb= new StringBuffer();
			for(int i=0;i<character.length;i++){
				sb.append(character[i]+"");
			}
			s=sb.toString();
		return SUCCESS;
		}
		}
	
	}
	
	

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	
}
