package com.dataconfig.service;

import java.util.ArrayList;
import java.util.List;

import com.dataconfig.bo.CChatKeyword;
import com.dataconfig.dao.CChatKeywordDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class CChatKeywordService {
	
	private CChatKeywordDao cChatKeywordDao;
	
	/**
	 * 新增聊天屏蔽关键字常量数据
	 * @param cChatKeyword
	 */
	public void addCChatKeyword(CChatKeyword cChatKeyword) {
		cChatKeywordDao.save(cChatKeyword, DBSource.CFG);
	}
	
	/**
	 * 删除聊天屏蔽关键字常量数据
	 * @param keyWord
	 */
	public void delCChatKeyword(String keyWord) {
		cChatKeywordDao.remove(findOneCChatKeyword(keyWord), DBSource.CFG);
	}
	
	/**
	 * 修改聊天屏蔽关键字常量数据
	 * @param cChatKeyword
	 */
	public void updateOneCChatKeyword(String keyword, String newKeyWord) {
		cChatKeywordDao.closeSession(DBSource.CFG);
		cChatKeywordDao.executeSQL_("update c_chat_keyword set KEY_WORD = \"" + newKeyWord + "\" where KEY_WORD = \"" + keyword + "\"");
	}

	/**
	 * 查询聊天屏蔽关键字常量数据
	 * @param keyWord
	 * @return
	 */
	public CChatKeyword findOneCChatKeyword(String keyWord) {
		return this.cChatKeywordDao.loadBy("keyWord", keyWord, DBSource.CFG);
	}
	
	/**
	 * 查询聊天屏蔽关键字常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<CChatKeyword> findCChatKeywordPageList(Integer currentPage, Integer pageSize) {
		cChatKeywordDao.closeSession(DBSource.CFG);
		return cChatKeywordDao.findPage("from CChatKeyword", new ArrayList<Object>(), pageSize, currentPage);
	}

	public List<CChatKeyword> findCChatKeywordList() {
		cChatKeywordDao.closeSession(DBSource.CFG);
		return cChatKeywordDao.findAll();
	}
	
	public CChatKeywordDao getcChatKeywordDao() {
		return cChatKeywordDao;
	}

	public void setcChatKeywordDao(CChatKeywordDao cChatKeywordDao) {
		this.cChatKeywordDao = cChatKeywordDao;
	}
}