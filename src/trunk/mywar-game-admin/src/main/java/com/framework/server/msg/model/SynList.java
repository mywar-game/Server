package com.framework.server.msg.model;

import java.util.Collection;
import java.util.Vector;

public class SynList<T> extends Vector<T> {

	public SynList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SynList(Collection<? extends T> c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	public SynList(int initialCapacity, int capacityIncrement) {
		super(initialCapacity, capacityIncrement);
		// TODO Auto-generated constructor stub
	}

	public SynList(int initialCapacity) {
		super(initialCapacity);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7552809700491910587L;

}
