package com.framework.common;

public class ALDAdminPageActionSupport extends ALDAdminActionSupport {
	/**  */
	private static final long serialVersionUID = 1L;
	
	/** 默认分页大小 **/
	public static final int DEFAULT_PAGESIZE = 20;
	
	/** 分页大小 **/
	private int pageSize = DEFAULT_PAGESIZE;

	private int toPage;

	private long totalPage;

	private long totalSize;

	public int getToPage() {
		return toPage;
	}

	public void setToPage(int toPage) {
		this.toPage = toPage;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
