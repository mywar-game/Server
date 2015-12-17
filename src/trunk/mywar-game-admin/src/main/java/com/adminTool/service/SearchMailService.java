package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.AdminMail;
import com.adminTool.dao.SearchMailDao;
import com.framework.common.DBSource;

/**
 * 查询邮件
 * @author Administrator
 *
 */
public class SearchMailService {

	private SearchMailDao searchMailDao;
	
	/**
	 * 群邮件
	 * @param serverId
	 * @return
	 */
	public List<AdminMail> findQunMail(String serverId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" from AdminMail m where (m.target = 1 or m.target = 3) and m.sendServerIds like '%");
		sb.append(serverId);
		sb.append("%'");
		return searchMailDao.find(sb.toString(), DBSource.ADMIN);
	}
	
	public List<AdminMail> findQunMailById(Integer systemMailId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" from AdminMail m where m.adminMailId = ");
		sb.append(systemMailId);
		sb.append(" and m.lodoIds = '");
		sb.append("'");
		return searchMailDao.find(sb.toString(), DBSource.ADMIN);
	}

	public List<AdminMail> find(String lodoIds, Integer systemMailId, String serverId) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" from AdminMail where");
		if (lodoIds != null && !lodoIds.equals("")) {
			sb.append(" lodoIds like '%");
			sb.append(lodoIds);
			sb.append("%'");
		}
		
		if (lodoIds != null && !lodoIds.equals("") && systemMailId != null) {
			sb.append(" and");
			sb.append(" adminMailId='");
			sb.append(systemMailId);
			sb.append("'");
		} else if (lodoIds == null || lodoIds.equals("")){
			sb.append(" adminMailId='");
			sb.append(systemMailId);
			sb.append("'");
		}
		
//		if (serverId != null) {
//			sb.append(" and");
//			sb.append(" serverIds like '%");
//			sb.append(serverId);
//			sb.append("%'");
//		}
		return searchMailDao.find(sb.toString(), DBSource.ADMIN);
	}
	
	public SearchMailDao getSearchMailDao() {
		return searchMailDao;
	}

	public void setSearchMailDao(SearchMailDao searchMailDao) {
		this.searchMailDao = searchMailDao;
	}
	
}
