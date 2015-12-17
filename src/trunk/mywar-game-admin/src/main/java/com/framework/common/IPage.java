package com.framework.common;

import java.util.Collection;

/**
 * 
 * 分页接口
 * @author mengchao
 *
 * @param <T>
 */
public interface IPage<T> {

	/**
	 * 获取总大小
	 *
	 * @return 总大小
	 */
	long getTotalSize();

	/**
	 * 获取页面大小
	 *
	 * @return 页面大小
	 */
	int getPageSize();

	/**
	 * 获取总页数
	 *
	 * @return 总页数
	 */
	long getTotalPage();

	/**
	 * 获取当前页数
	 *
	 * @return 当前页数
	 */
	long getCurrentPage();

	/**
	 * 是否为第一页
	 *
	 * @return 是否为第一页
	 */
	boolean isFirstPage();

	/**
	 * 是否为最后一页
	 *
	 * @return 是否为最后一页
	 */
	boolean isLastPage();

	/**
	 * 获取当前页起始记录行
	 *
	 * @return 起始记录行
	 */
	long getPageBegin();

	/**
	 * 获取当前页结束记录行
	 *
	 * @return 结束记录行
	 */
	long getPageEnd();

	/**
	 * 获取页面数据
	 *
	 * @return 页面数据
	 */
	Collection<T> getData();

}
