package com.framework.server.msg.model;

import java.util.ArrayList;
import java.util.Collection;

public class UnSynList<T> extends ArrayList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7552809700491910587L;
	
	public UnSynList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnSynList(Collection<? extends T> c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	public UnSynList(int initialCapacity) {
		super(initialCapacity);
		// TODO Auto-generated constructor stub
	}
    
}
