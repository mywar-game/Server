package com.framework.dao; 

public class DaoOperatorBean<E> {
	private BaseEntityDao<E> baseEntityDao; 
	private E entity; 
	private int targetUid; 
	private int type; // 操作类型 1 插入 2 修改 3 删除；

	public static final int INSERT = 1; 
	public static final int UPDATE = 2; 
	public static final int DELETE = 3; 

	public DaoOperatorBean() {
		super(); 
		// TODO Auto-generated constructor stub
	}

	public DaoOperatorBean(BaseEntityDao<E> baseEntityDao, E e, int type, int targetUid) {
		super(); 
		this.baseEntityDao = baseEntityDao; 
		this.entity = e; 
		this.type = type; 
		this.targetUid = targetUid; 
	}
	
    public void execute() {
    	if (type == INSERT) {
    		baseEntityDao.save(entity, targetUid); 
    	} else if (type == UPDATE) {
    		baseEntityDao.update(entity, targetUid); 
    	} else if (type == DELETE) {
    		baseEntityDao.remove(entity, targetUid); 
    	} else {
    		throw new NullPointerException("找不到执行的操作类型，必须先设入 type值"); 
    	}
    }
    
	public BaseEntityDao<E> getBaseEntityDao() {
		return baseEntityDao; 
	}

	public void setBaseEntityDao(BaseEntityDao<E> baseEntityDao) {
		this.baseEntityDao = baseEntityDao; 
	}

	public E getE() {
		return entity; 
	}

	public void setE(E e) {
		this.entity = e; 
	}

	public int getType() {
		return type; 
	}

	public void setType(int type) {
		this.type = type; 
	}

}
