package com.framework.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.framework.common.IPage;
import com.framework.common.Page;
import com.framework.servicemanager.CustomerContextHolder;



public class BaseEntityDao<E> extends BaseDao implements IEntityDao<E> {

	protected final Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public BaseEntityDao() {
		try {
			entityClass = (Class<E>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			String clsName = getClass().getSimpleName();
			throw new RuntimeException(getClass().getCanonicalName()
					+ "未定义泛型! 继承于:" + BaseEntityDao.class.getCanonicalName()
					+ "的类都必需声明所操作实体的泛型, 如:\npublic class " + clsName
					+ " extends " + BaseEntityDao.class.getSimpleName() + "<"
					+ clsName.substring(0, clsName.length() - 3)
					+ "> implements I" + clsName + "{\n\t...\n}");
		}
	}

	// ---- 实现IEntityDao ----

	public void save(E entity, Integer targetUid) {
		closeSession(targetUid);
		getHibernateTemplate().save(entity);
	}

	public void saveBatch(Collection<E> entities, Integer targetUid) {
		closeSession(targetUid);
		for (E entity : entities) {
			getHibernateTemplate().save(entity);
		}
	}

	public void update(E entity, Integer targetUid) {
		closeSession(targetUid);
		getHibernateTemplate().update(entity);
	}

	public void updateBatch(Collection<E> entities) {
		for (E entity : entities) {
			getHibernateTemplate().update(entity);
		}
	}

	public void saveOrUpdate(E entity) {
		super.getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdateBatch(Collection<E> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	public void remove(E entity, Integer targetUid) {
		closeSession(targetUid);
		getHibernateTemplate().delete(entity);
	}

	public void remove(Long id) {
		getHibernateTemplate().delete(
				getHibernateTemplate().load(entityClass, id));
	}

	public void removeBatch(Collection<E> entities) {
		super.getHibernateTemplate().deleteAll(entities);
	}

	@SuppressWarnings("unchecked")
	public void removeBatch(String strCommand, List<Object> args) {
		if (args == null) {
			args = new ArrayList<Object>();
		}
		removeBatch(getHibernateTemplate().find(strCommand, args.toArray()));
	}

	@SuppressWarnings("unchecked")
	public E load(E entity, Integer id) {
		return (E) getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public E load(Integer id, Integer targetUid) {
		closeSession(targetUid);
		return (E) getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public E loadBy(String propertyName, Object propertyValue, Integer targetUid) {
		closeSession(targetUid);
		List<E> list = getHibernateTemplate().find(
				"from " + entityClass.getName() + " where " + propertyName
						+ " = ?", propertyValue);
		if (list.size() > 0)
			return (E) list.get(0);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public E loadByTwo(String propertyNameOne, Object propertyValueOne, String propertyNameTwo, Object propertyValueTwo, Integer targetUid) {
		closeSession(targetUid);
		
		List<E> list = getHibernateTemplate().find(
				"from " + entityClass.getName() + " where " + propertyNameOne
						+ " = ? and "+propertyNameTwo+" = ?", new Object[]{propertyValueOne,propertyValueTwo});
		if (list.size() > 0)
			return (E) list.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<E> find(String queryString, Integer targetUid) {
		closeSession(targetUid);
		return super.getHibernateTemplate().find(queryString);
	}

	@SuppressWarnings("unchecked")
	public List<E> find(String queryString, Object[] args) {
		return super.getHibernateTemplate().find(queryString, 
				(args == null ? new Object[0] : args));
	}

	@SuppressWarnings("unchecked")
	public List<E> find(String queryString, List<Object> args) {
		return super.getHibernateTemplate().find(queryString, 
				(args == null ? new Object[0] : args.toArray()));
	}

	@SuppressWarnings("unchecked")
	public List<E> find(final String queryString, final List<Object> args, 
			final int pageSize, final int pageIndex) {

		Session session = this.getSession();
		Query query = session.createQuery(queryString);
		query.setFirstResult(pageSize * (pageIndex));
		query.setMaxResults(pageSize);
		if (args != null && args.size() > 0) {
			Object[] values = args.toArray();
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		List<E> resultList = query.list();

		return resultList;
	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPage(final String queryString, final List<Object> args, 
			final int pageSize, final int pageIndex) {

		Session session = this.getSession();
		Query query = session.createQuery(queryString);
		query.setFirstResult(pageSize * (pageIndex));
		query.setMaxResults(pageSize);
		if (args != null && args.size() > 0) {
			Object[] values = args.toArray();
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		return new Page<E>(query.list(), count(queryString, args), pageSize, 
				pageIndex);

	}

	@SuppressWarnings("unchecked")
	public int count(final String queryString, final List<Object> args) {

		Session session = this.getSession();
		Query query = session.createQuery(getCountString(queryString));
		if (args != null && args.size() > 0) {
			Object[] values = args.toArray();
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		Number number = (Number) query.uniqueResult();

		return number.intValue();
	}

	/**
	 * 
	 * @param queryString
	 * @return
	 */
	private String getCountString(final String queryString) {
		String tmp = queryString.trim();
		if (tmp.toLowerCase().startsWith("from ")) {
			return " select count(*) " + queryString;
		}
		if (!tmp.toLowerCase().startsWith("select"))
			throw new RuntimeException(" the query not valid [" + queryString
					+ "]");
		int pos = queryString.toLowerCase().indexOf(" from ");
		if (pos == -1)
			throw new RuntimeException("the query not valid [" + queryString
					+ "]");
		final String where = tmp.substring(pos);
		final String suffix = tmp.substring(7, pos);
		String token[] = suffix.split(",");
		String cntField = token[0];
		String [] str = cntField.split("[(]");
		
		if (str.length > 1) {
			cntField = str[1];
		}

		return " select count(" + cntField + ") " + where;
	}

	
	
	@SuppressWarnings("unchecked")
	public List<E> find(final String queryString, final Map<String, Object> args) {

		Session session = this.getSession();
		Query query = session.createQuery(queryString);
		query.setProperties(args);

		List<E> resultList = query.list();

		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<E> find(final String queryString, 
			final Map<String, Object> args, final int pageSize, 
			final int pageIndex) {

		Session session = this.getSession();
		Query query = session.createQuery(queryString);
		query.setFirstResult(pageSize * (pageIndex));
		query.setMaxResults(pageSize);
		query.setProperties(args);

		List<E> resultList = query.list();

		return resultList;

	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPage(final String queryString, 
			final Map<String, Object> args, final int pageSize, 
			final int pageIndex) {

		Session session = this.getSession();
		Query query = session.createQuery(queryString);
		query.setFirstResult(pageSize * (pageIndex));
		query.setMaxResults(pageSize);
		query.setProperties(args);
		return new Page<E>(query.list(), count(queryString, args), pageSize, 
				pageIndex);

	}

	public int count(final String queryString, final Map<String, Object> args) {

		Session session = this.getSession();
		Query query = session.createQuery(getCountString(queryString));
		query.setProperties(args);
		Number number = (Number) query.uniqueResult();

		return number.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<E> find(final String queryString, final Object args) {

		Session session = this.getSession();
		Query query = session.createQuery(queryString);
		query.setProperties(args);

		List<E> resultList = query.list();
		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<E> find(final String queryString, final Object args, 
			final int pageSize, final int pageIndex) {

		Session session = this.getSession();
		Query query = session.createQuery(queryString);
		query.setFirstResult(pageSize * (pageIndex));
		query.setMaxResults(pageSize);
		query.setProperties(args);
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPage(final String queryString, final Object args, 
			final int pageSize, final int pageIndex) {

		Session session = this.getSession();
		Query query = session.createQuery(queryString);
		query.setFirstResult(pageSize * (pageIndex));
		query.setMaxResults(pageSize);
		query.setProperties(args);
		return new Page<E>(query.list(), count(queryString, args), pageSize, 
				pageIndex);

	}

	public int count(final String queryString, final Object args) {

		Session session = this.getSession();
		Query query = session.createQuery(getCountString(queryString));
		query.setProperties(args);

		Number number = (Number) query.uniqueResult();
		return number.intValue();

	}

	@SuppressWarnings("unchecked")
	public IPage<Object> findSQLPageLimit_(final String queryString, final Object args, 
			final int pageSize, int pageIndex , int limitCount) {
        int tempPageSize = pageSize;
		Session session = this.getSession();
		Query query = session.createSQLQuery(queryString);
		int limitPage = limitCount / pageSize;
        if (pageIndex > limitPage) {
        	pageIndex = limitPage % pageIndex;
        }
        if (pageIndex * pageSize + pageSize > limitCount) {
        	tempPageSize = limitCount - pageIndex * pageSize;
        }
		query.setFirstResult(pageSize * (pageIndex));
		query.setMaxResults(tempPageSize);
		query.setProperties(args);
		return new Page<Object>(query.list(), countSQLLimit(queryString, limitCount, args), pageSize, 
				pageIndex);

	}

	public int countSQLLimit(final String queryString, int limitCount, final Object args) {

		Session session = this.getSession();
		Query query = session.createSQLQuery(getSQLCountStringLimit(queryString, limitCount));
		query.setProperties(args);

		Number number = (Number) query.uniqueResult();
		return number.intValue();

	}
	
	private String getSQLCountStringLimit(final String queryString, int limitCount) {
	    String str = "select count(*) from (" + queryString + " limit " + limitCount + ")bb";
		return str;
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findBy(final DetachedCriteria detachedCriteria) {

		Session session = this.getSession();
		List<E> resultList = detachedCriteria.getExecutableCriteria(session)
				.list();
		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<E> findBy(final DetachedCriteria detachedCriteria, 
			final int pageSize, final int pageIndex) {

		Session session = this.getSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		criteria.setFirstResult(pageSize * (pageIndex));
		criteria.setMaxResults(pageSize);
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPageBy(final DetachedCriteria detachedCriteria, 
			final int pageSize, final int pageIndex) {

		Session session = this.getSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		Integer total = (Integer) criteria
				.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		criteria.setFirstResult(pageSize * (pageIndex));
		criteria.setMaxResults(pageSize);
		return new Page<E>(criteria.list(), total, pageSize, pageIndex);

	}

	public int countBy(final DetachedCriteria detachedCriteria) {

		Session session = this.getSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);

		Number number = (Number) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return number.intValue();

	}

	@SuppressWarnings("unchecked")
	public List<E> findBy(final E entity) {
		return getHibernateTemplate().findByExample(entity);
	}

	@SuppressWarnings("unchecked")
	public List<E> findBy(final E entity, final int pageSize, 
			final int pageIndex) {
		return getHibernateTemplate().findByExample(entity, 
				pageSize * (pageIndex), pageSize);
	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPageBy(final E entity, final int pageSize, 
			final int pageIndex) {

		Session session = this.getSession();
		Criteria criteria = session.createCriteria(entity.getClass());
		criteria.add(Example.create(entity));
		Integer total = (Integer) criteria
				.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		criteria.setFirstResult(pageSize * (pageIndex));
		criteria.setMaxResults(pageSize);
		return new Page<E>(criteria.list(), total, pageSize, pageIndex);

	}

	public int countBy(final E entity) {

		Session session = this.getSession();
		Criteria criteria = session.createCriteria(entity.getClass());
		criteria.add(Example.create(entity));
		Number number = (Number) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return number.intValue();

	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {

		Session session = this.getSession();
		Criteria criteria = session.createCriteria(entityClass);
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<E> findAll(final int pageSize, final int pageIndex) {

		Session session = this.getSession();
		Criteria criteria = session.createCriteria(entityClass);
		criteria.setFirstResult(pageSize * (pageIndex));
		criteria.setMaxResults(pageSize);
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public IPage<E> findPageAll(final int pageSize, final int pageIndex) {

		Session session = this.getSession();
		Criteria criteria = session.createCriteria(entityClass);
		Integer total = (Integer) criteria
				.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		criteria.setFirstResult(pageSize * (pageIndex));
		criteria.setMaxResults(pageSize);
		return new Page(findAll(), total, 100, pageIndex);

	}

	public int countAll() {

		Session session = this.getSession();
		Criteria criteria = session.createCriteria(entityClass);
		Number number = (Number) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return number.intValue();

	}

	public int execute(final String queryString) {
		Session session = this.getSession();
		Query query = session.createQuery(queryString);
		return query.executeUpdate();

	}

	public int execute(final String hql, final List<Object> args) {

		Session session = this.getSession();
		Query query = session.createQuery(hql);
		if (args != null && args.size() > 0) {
			Object[] values = args.toArray();
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.executeUpdate();

	}

	public int execute(final String hql, final Map<String, Object> args) {

		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setProperties(args);
		return query.executeUpdate();

	}
	public int executeSQL_(final String queryString) {
		Session session = this.getSession();
		Query query = session.createSQLQuery(queryString);
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> findNewSQL_(String sql) {
		Session session = this.getSession();
		return session.createSQLQuery(sql).addScalar("user_id", StandardBasicTypes.CHARACTER_ARRAY).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> findSQL_(String sql) {
		Session session = this.getSession();
		Query query = session.createSQLQuery(sql);
		List<Object> objList = query.list();
		return objList;
	}
	/**
	 * 定义返回类型的查询
	 * @param sql
	 * @param columns
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findSQLByType(String sql,String[] columns,org.hibernate.type.Type...params) {
		Session session = this.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		for(int i = 0;i<columns.length;i++){
			query.addScalar(columns[i], params[i]);
		}
		List<Object> objList = query.list();
		return objList;
	}
	@SuppressWarnings("unchecked")
	public IPage<Object> findSQL_Page(final String queryString, final List<Object> args, 
			final int pageSize, final int pageIndex) {

		Session session = this.getSession();
		Query query = session.createSQLQuery(queryString);
		query.setFirstResult(pageSize * (pageIndex));
		query.setMaxResults(pageSize);
		if (args != null && args.size() > 0) {
			Object[] values = args.toArray();
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		return new Page<Object>(query.list(), countSQL_(queryString, args), pageSize, 
				pageIndex);
	}
	
	@SuppressWarnings("unchecked")
	public IPage<Object> findSQL_PageUnion(final String queryString, final List<Object> args, 
			final int pageSize, final int pageIndex) {

		Session session = this.getSession();
		Query query = session.createSQLQuery(queryString);
		query.setFirstResult(pageSize * (pageIndex));
		query.setMaxResults(pageSize);
		if (args != null && args.size() > 0) {
			Object[] values = args.toArray();
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		return new Page<Object>(query.list(), countSQL_Have_Group(queryString, args), pageSize, 
				pageIndex);
	}
	
	@SuppressWarnings("unchecked")
	public IPage<Object> findSQL_Page_Have_Group(final String queryString, final List<Object> args, 
			final int pageSize, final int pageIndex) {

		Session session = this.getSession();
		Query query = session.createSQLQuery(queryString);
		query.setFirstResult(pageSize * (pageIndex));
		query.setMaxResults(pageSize);
		if (args != null && args.size() > 0) {
			Object[] values = args.toArray();
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		return new Page<Object>(query.list(), countSQL_Have_Group(queryString, args), pageSize, 
				pageIndex);
	}
	
	public int countSQL_Have_Group(final String queryString, final List<Object> args) {

		Session session = this.getSession();
		Query query = session.createSQLQuery(getSQLCountStringHaveGroup(queryString));
		if (args != null && args.size() > 0) {
			Object[] values = args.toArray();
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		Number number = (Number) query.uniqueResult();

		return number.intValue();
	}
	
	private String getSQLCountStringHaveGroup(final String queryString) {
	    String str = "select count(*) from (" + queryString + ")bb";
		return str;
	}
	public int countSQL_(final String queryString, final List<Object> args) {

		Session session = this.getSession();
		Query query = session.createSQLQuery(getCountString(queryString));
		if (args != null && args.size() > 0) {
			Object[] values = args.toArray();
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		Number number = (Number) query.uniqueResult();
		return number.intValue();
	}
	
	public int callProcedure(String procString, List<Object> params) {
		SQLQuery query = this.getSession().createSQLQuery(procString);
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Object> callProcedureList(String procString, List<Object> params) {
		SQLQuery query = this.getSession().createSQLQuery(procString);
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return query.list();
	}
	
	public void flush() {
		getHibernateTemplate().flush();
	}
    public void clear() {
    	getHibernateTemplate().clear();
    }
	/**
	 * 异步插入数据库
	 * @param entity
	 * @param targetUid
	 */
	public void saveUnsyn(E entity, Integer targetUid) {
	   DaoOperatorBean<E> oprator = new DaoOperatorBean<E>(this, entity, DaoOperatorBean.INSERT, targetUid);
	   WriteThread.pushOperator(oprator);
	}
	


	/**
	 * 异步批量插入数据库
	 * @param entities
	 * @param targetUid
	 */
	public void saveUnSynBatch(Collection<E> entities, Integer targetUid) {
		for (E entity : entities) {
			saveUnsyn(entity, targetUid);
		}
	}
	

	/**
	 * 异步更新数据
	 * @param entity
	 * @param targetUid
	 */
	public void unSynUpdate(E entity, Integer targetUid) {
		 DaoOperatorBean<E> oprator = new DaoOperatorBean<E>(this, entity, DaoOperatorBean.UPDATE, targetUid);
		 WriteThread.pushOperator(oprator);
	}

	/**
	 * 异步批量更新数据
	 * @param entities
	 */
	public void unSynUpdateBatch(Collection<E> entities, Integer targetUid) {
		for (E entity : entities) {
			unSynUpdate(entity, targetUid);
		}
	}
	
	/**
	 * 异步删除数据
	 * @param entity
	 * @param targetUid
	 */
	public void unSynRemove(E entity, Integer targetUid) {
		 DaoOperatorBean<E> oprator = new DaoOperatorBean<E>(this, entity, DaoOperatorBean.DELETE, targetUid);
		 WriteThread.pushOperator(oprator);
	}
	/**
	 * 异步批量删除数据
	 * @param entities
	 */
	public void unSynRemoveBatch(Collection<E> entities, Integer targetUid) {
		for (E entity:entities) {
			unSynRemove(entity, targetUid);
		}
	}
    
	   public void closeSession(int targetId) {
	    	if (CustomerContextHolder.getCustomerType() == null) {
	    		CustomerContextHolder.setCustomerType(targetId);
	    		return;
	    	}
	    	if (CustomerContextHolder.calcCustomerType(targetId) != CustomerContextHolder.getCustomerType().intValue()) {
	    		if (TransactionSynchronizationManager.hasResource(getSessionFactory())) {
	        		Session session = getSession();
	        		session.flush();
	                session.close();
	    		}
	    		if (TransactionSynchronizationManager.isSynchronizationActive()) {
	    			TransactionSynchronizationManager.clearSynchronization();
	    			TransactionSynchronizationManager.initSynchronization();
	    		}
	    		
//	    		if (TransactionSynchronizationManager.hasResource(getSessionFactory())) {
//	        		TransactionSynchronizationManager.unbindResource(getSessionFactory());
//	    		}
	    		CustomerContextHolder.setCustomerType(targetId);
	    		return;
	    	}
	    }
}
