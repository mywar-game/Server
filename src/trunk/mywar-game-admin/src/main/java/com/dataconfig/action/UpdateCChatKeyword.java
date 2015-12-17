package com.dataconfig.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.dataconfig.bo.CChatKeyword;
import com.dataconfig.service.CChatKeywordService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateCChatKeyword extends ALDAdminActionSupport implements ModelDriven<CChatKeyword> {

	/** **/
	private static final long serialVersionUID = 8549702047389464960L;

	/** **/
	private CChatKeyword chatKeyword = new CChatKeyword();
	
	/** **/
	private String newKeyWord;
	
	public String execute() {
		try {
			String keyWord = URLDecoder.decode(chatKeyword.getKeyWord(), "UTF-8").trim();
			chatKeyword.setKeyWord(keyWord);
			newKeyWord = URLDecoder.decode(newKeyWord, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		CChatKeywordService chatKeywordService = ServiceCacheFactory.getServiceCache().getService(CChatKeywordService.class);
		chatKeywordService.updateOneCChatKeyword(chatKeyword.getKeyWord(), newKeyWord);
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

	public String getNewKeyWord() {
		return newKeyWord;
	}

	public void setNewKeyWord(String newKeyWord) {
		this.newKeyWord = newKeyWord;
	}
}
