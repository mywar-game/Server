package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.CChatKeyword;
import com.dataconfig.service.CChatKeywordService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class CChatKeywordList extends ALDAdminActionSupport {

	/** **/
	private static final long serialVersionUID = -328891041637415462L;
	
	/** **/
	private List<CChatKeyword> chatKeywordList; 
	
	public String execute() {
		CChatKeywordService chatKeywordService = ServiceCacheFactory.getServiceCache().getService(CChatKeywordService.class);
		chatKeywordList = chatKeywordService.findCChatKeywordList();
		return SUCCESS;
	}

	public List<CChatKeyword> getChatKeywordList() {
		return chatKeywordList;
	}

	public void setChatKeywordList(List<CChatKeyword> chatKeywordList) {
		this.chatKeywordList = chatKeywordList;
	}
}
