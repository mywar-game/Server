package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.MStoryConstant;
import com.dataconfig.dao.StoryConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class StoryConstantService {

	private StoryConstantDao storyConstantDao;
	
	/**
	 * 新增剧情常量数据
	 * @param storyConstant
	 */
	public void addOneStoryConstant(MStoryConstant storyConstant) {
		storyConstantDao.save(storyConstant, DBSource.CFG);
	}
	
	/**
	 * 删除剧情常量数据
	 * @param 剧情Id
	 */
	public void delOneStoryConstant(Integer storyId) {
		storyConstantDao.remove(findOneStoryConstant(storyId), DBSource.CFG);
	}
	
	/**
	 * 修改剧情常量数据
	 * @param storyConstant
	 */
	public void updateOneStoryConstant(MStoryConstant storyConstant) {
		storyConstantDao.update(storyConstant, DBSource.CFG);
	}

	/**
	 * 查询剧情常量数据
	 * @param storyId
	 * @return
	 */
	public MStoryConstant findOneStoryConstant(Integer storyId) {
		return this.storyConstantDao.loadBy("storyId", storyId, DBSource.CFG);
	}
	
	/**
	 * 查询剧情常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<MStoryConstant> findStoryConstantList(Integer currentPage, Integer pageSize) {
		storyConstantDao.closeSession(DBSource.CFG);
		return storyConstantDao.findPage("from MStoryConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setStoryConstantDao(StoryConstantDao storyConstantDao) {
		this.storyConstantDao = storyConstantDao;
	}

	public StoryConstantDao getStoryConstantDao() {
		return storyConstantDao;
	}
}
