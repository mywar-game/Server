package com.dataconfig.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.dataconfig.bo.CChatKeyword;
import com.dataconfig.service.CChatKeywordService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddCChatKeyword extends ALDAdminActionSupport implements ModelDriven<CChatKeyword> {

	/** **/
	private static final long serialVersionUID = 8646761258293691492L;

	/** **/
	private CChatKeyword chatKeyword = new CChatKeyword();
	
	public String execute() {
		try {
			String keyWord = URLDecoder.decode(chatKeyword.getKeyWord(), "UTF-8").trim();
			chatKeyword.setKeyWord(keyWord);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		CChatKeywordService chatKeywordService = ServiceCacheFactory.getServiceCache().getService(CChatKeywordService.class);
		chatKeywordService.addCChatKeyword(chatKeyword);
		return SUCCESS;
	}
	
	@Override
	public CChatKeyword getModel() {
		// TODO Auto-generated method stub
		return chatKeyword;
	}

	public CChatKeyword getChatKeyword() {
		return chatKeyword;
	}

	public void setChatKeyword(CChatKeyword chatKeyword) {
		this.chatKeyword = chatKeyword;
	}
}
