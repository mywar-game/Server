package com.adminTool.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.dao.PartnerDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

/**
 * 渠道商Service
 * 
 * @author yezp
 */
public class PartnerService {
	private PartnerDao partnerDao;

	public PartnerDao getPartnerDao() {
		return partnerDao;
	}

	public void setPartnerDao(PartnerDao partnerDao) {
		this.partnerDao = partnerDao;
	}

	/**
	 * 获得渠道商信息map
	 * 
	 * @return
	 */
	public Map<String, Partner> findAllPartnerMap() {
		List<Partner> list = partnerDao.find(" from Partner", DBSource.ADMIN);
		Map<String, Partner> map = new HashMap<String, Partner>();
		if (list != null && list.size() > 0) {
			for (Partner p : list) {
				map.put(p.getPNum(), p);
			}
		}
		return map;
	}

	/**
	 * 查询渠道商列表
	 * 
	 * @return
	 */
	public List<Partner> findPartnerList() {
		return partnerDao.find(" from Partner", DBSource.ADMIN);
	}

	/**
	 * 根据pid查找渠道商
	 * 
	 * @param pid
	 * @return
	 */
	public Partner getPartnerByPid(int pid) {
		return partnerDao.loadBy("pid", pid, DBSource.ADMIN);
	}

	/**
	 * key: pid, value: Partner
	 * 
	 * @return
	 */
	public Map<String, Partner> getPartnerMap() {
		List<Partner> partnerList = findPartnerList();

		Map<String, Partner> map = new HashMap<String, Partner>();
		for (Partner partner : partnerList) {
			map.put(partner.getPid().toString(), partner);
		}

		return map;
	}

	/**
	 * 分页查找渠道商
	 * 
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<Partner> findPartnerPageList(int toPage, int defaultPagesize) {
		partnerDao.closeSession(DBSource.ADMIN);
		IPage<Partner> partnerList = partnerDao.findPage("from Partner",
				new ArrayList<Object>(), defaultPagesize, toPage);

		return partnerList;
	}

	/**
	 * 添加渠道商
	 * 
	 * @param partner
	 */
	public void addPartner(Partner partner) {
		partnerDao.save(partner, DBSource.ADMIN);
	}

	/**
	 * 修改渠道商
	 * 
	 * @param partner
	 */
	public void updatePartner(Partner partner) {
		partnerDao.update(partner, DBSource.ADMIN);
	}

	/**
	 * 删除渠道商
	 * 
	 * @param pid
	 */
	public void delPartner(Integer pid) {
		// TODO 删除时间
		Date date = new Date();
		Timestamp removeTime = new Timestamp(date.getTime());
		Partner partner = getPartnerByPid(pid);

		partner.setRemoveTime(removeTime);
		partnerDao.remove(partner, DBSource.ADMIN);
	}
}
