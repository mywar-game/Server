package com.framework.common;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 分页实现
 * 
 * @author mengchao
 *
 * @param <T>
 */
public class Page<T> implements IPage<T> {

	private long totalSize;

	private int pageSize;

	private long totalPage;

	private long currentPage;

	private Collection<T> data;
	

	public Page(Collection<T> data, long totalSize, int pageSize, int currentPage) {
		this.data = (data == null ? new ArrayList<T>(0) : data);
		this.totalSize = totalSize;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPage = calcTotalPage();
	}
	
	private long calcTotalPage() {
		long t = getTotalSize();
		long p = getPageSize();
		if (t == 0  || p == 0)
			return 0;
		long r = t % p;
		long pages = (t - r) / p;
		if (r > 0)
			pages += 1;
		return pages;
	}
	
	public long getCurrentPage() {
		return this.currentPage;
	}

	public Collection<T> getData() {
		return this.data;
	}

	public long getPageBegin() {
		return (this.pageSize * this.currentPage);
	}

	public long getPageEnd() {
		return getPageBegin() + getData().size();
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public long getTotalSize() {
		return this.totalSize;
	}


	public boolean isFirstPage() {
		return this.currentPage == 0;
	}

	public boolean isLastPage() {
		return this.currentPage == this.totalPage;
	}

}
