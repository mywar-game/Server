package com.dataconfig.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.TTreasureConstant;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class TTreasureConstantList extends ALDAdminPageActionSupport {

	/**  **/
	private static final long serialVersionUID = 8992358752117544675L;

	/**  **/
	private List<TTreasureConstant> treasureConstantList;
	
	/**  **/
	private Map<Integer, String> treasureIDNameMap;
	
	/**  **/
	private Integer id;
	
	/**  **/
	private String name = "";

	public String execute() {
		//System.out.println("id ====================== "+id);
		//System.out.println("name ==================== "+name);
		try {
			name = URLDecoder.decode(name, "UTF-8").trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//System.out.println("name ==================== "+name);
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		IPage<TTreasureConstant> list = treasureConstantService.findTTreasureConstantPageList(id, name, super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			treasureConstantList = (List<TTreasureConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public String findTreasureIdNameMapByCondition() throws Exception {
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		treasureIDNameMap = treasureConstantService.findTreasureIdNameMapByCondition(id, URLDecoder.decode(name, "UTF-8").trim());
		return "find";
	}
	
	public List<TTreasureConstant> getTreasureConstantList() {
		return treasureConstantList;
	}

	public void setTreasureConstantList(List<TTreasureConstant> treasureConstantList) {
		this.treasureConstantList = treasureConstantList;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap;
	}

	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap;
	}
}
