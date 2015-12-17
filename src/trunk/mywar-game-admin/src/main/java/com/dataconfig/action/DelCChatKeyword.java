package com.dataconfig.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.dataconfig.bo.CChatKeyword;
import com.dataconfig.service.CChatKeywordService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelCChatKeyword extends ALDAdminActionSupport implements ModelDriven<CChatKeyword> {

	/** **/
	private static final long serialVersionUID = -1722669901449696785L;

	/** **/
	private CChatKeyword chatKeyword = new CChatKeyword();
	
	public void executeDel() {
		try {
			String keyWord = URLDecoder.decode(chatKeyword.getKeyWord(), "UTF-8").trim();
			chatKeyword.setKeyWord(keyWord);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		CChatKeywordService chatKeywordService = ServiceCacheFactory.getServiceCache().getService(CChatKeywordService.class);
		chatKeywordService.delCChatKeyword(chatKeyword.getKeyWord());
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
