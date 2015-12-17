package com.framework.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.framework.common.IPage;

/**
 * 
 * 实体持久化操作接口
 * 
 * @author mengchao
 * 
 */
public interface IEntityDao<E> {
	/**
	 * 执行更新语句
	 * 
	 * @param hql
	 *            hql语句
	 * 
	 */
	int execute(String hql);

	/**
	 * 执行更新语句
	 * 
	 * @param hql
	 *            hql语句
	 * @param args
	 *            参数, 替换hql中的"?"
	 * 
	 */
	int execute(String hql, List<Object> args);

	/**
	 * 执行更新语句
	 * 
	 * @param hql
	 *            hql语句
	 * @param args
	 *            参数, 替换hql中的":arg"
	 * 
	 */
	int execute(String hql, Map<String, Object> args);

	// 单实体操作 ----

	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 */
	void save(E entity, Integer targetUid);

	/**
	 * 批量保存实体
	 * 
	 * @param entities
	 *            实体集
	 * 
	 */
	void saveBatch(Collection<E> entities, Integer targetUid);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 */
	void update(E entity, Integer targetUid);

	/**
	 * 批量更新实体
	 * 
	 * @param entities
	 *            实体集
	 * 
	 */
	void updateBatch(Collection<E> entities);

	/**
	 * 保存或更新实体
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 */
	void saveOrUpdate(E entity);

	/**
	 * 批量保存或更新实体
	 * 
	 * @param entities
	 *            实体集
	 * 
	 */
	void saveOrUpdateBatch(Collection<E> entities);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 */
	void remove(E entity, Integer targetUid);

	/**
	 * 根据ID删除实体
	 * 
	 * @param id
	 *            实体的ID
	 * 
	 */
	void remove(Long id);

	/**
	 * 批量删除实体
	 * 
	 * @param entities
	 *            待删除实体集
	 * 
	 */
	void removeBatch(Collection<E> entities);

	/**
	 * 查出相关实体进行批量删除
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * 
	 */
	void removeBatch(String queryString, List<Object> args);

	/**
	 * 加载实体
	 * 
	 * @param entity
	 *            实体查询对象
	 * @return 实体对象
	 * 
	 */
	E load(E entity, Integer id);

	/**
	 * 通过ID加载实体
	 * 
	 * @param id
	 *            查询实体的ID
	 * @return 实体对象
	 * 
	 */
	E load(Integer id, Integer targetUid);

	/**
	 * 通过编号加载实体
	 * 
	 * @param code
	 *            查询实体的编号
	 * @return 实体对象
	 * 
	 */
	E loadBy(String propertyName, Object propertyValue, Integer targetUid);

	// 实体HQL查询 ----

	/**
	 * 查询实体
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @return 查询出的实体
	 * 
	 */
	List<E> find(String queryString, Integer targetUid);

	/**
	 * 查询实体
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * @return 查询出的实体
	 * 
	 */
	List<E> find(String queryString, List<Object> args);

	/**
	 * 分页查询实体
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * @param pageSize
	 *            页面大小
	 * @param pageIndex
	 *            当前页码
	 * @return 查询出的实体
	 * 
	 */
	List<E> find(String queryString, List<Object> args, int pageSize,
			int pageIndex);

	/**
	 * 分页查询实体
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * @param pageSize
	 *            页面大小
	 * @param pageIndex
	 *            当前页码
	 * @return 查询出的实体
	 * 
	 */
	IPage<E> findPage(String queryString, List<Object> args, int pageSize,
			int pageIndex);

	/**
	 * 统计实体的个数
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * @return 实体的个数
	 * 
	 */
	int count(String queryString, List<Object> args);

	/**
	 * 查询实体
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * @return 查询出的实体
	 * 
	 */
	List<E> find(String queryString, Map<String, Object> args);

	/**
	 * 分页查询实体
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * @param pageSize
	 *            页面大小
	 * @param pageIndex
	 *            当前页码
	 * @return 查询出的实体
	 * 
	 */
	List<E> find(String queryString, Map<String, Object> args, int pageSize,
			int pageIndex);

	/**
	 * 分页查询实体
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * @param pageSize
	 *            页面大小
	 * @param pageIndex
	 *            当前页码
	 * @return 查询出的实体
	 * 
	 */
	IPage<E> findPage(String queryString, Map<String, Object> args,
			int pageSize, int pageIndex);

	/**
	 * 统计实体的个数
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * @return 实体的个数
	 * 
	 */
	int count(String queryString, Map<String, Object> args);

	/**
	 * 查询实体
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * @return 查询出的实体
	 * 
	 */
	List<E> find(String queryString, Object args);

	/**
	 * 分页查询实体
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * @param pageSize
	 *            页面大小
	 * @param pageIndex
	 *            当前页码
	 * @return 查询出的实体
	 * 
	 */
	List<E> find(String queryString, Object args, int pageSize, int pageIndex);

	/**
	 * 分页查询实体
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * @param pageSize
	 *            页面大小
	 * @param pageIndex
	 *            当前页码
	 * @return 查询出的实体
	 * 
	 */
	IPage<E> findPage(String queryString, Object args, int pageSize,
			int pageIndex);

	/**
	 * 统计实体的个数
	 * 
	 * @param queryString
	 *            查询命令串(HQL)
	 * @param args
	 *            查询参数
	 * @return 实体的个数
	 * 
	 */
	int count(String queryString, Object args);

	// 实体条件查询 ----

	/**
	 * 查询符合依据的实体
	 * 
	 * @param criteria
	 *            依据
	 * @return 符合依据的数据
	 * 
	 */
	List<E> findBy(DetachedCriteria criteria);

	/**
	 * 分页查询符合依据的实体
	 * 
	 * @param criteria
	 *            依据
	 * @param pageSize
	 *            页面大小
	 * @param pageIndex
	 *            当前页码
	 * @return 符合依据的当前页数据
	 * 
	 */
	List<E> findBy(DetachedCriteria criteria, int pageSize, int pageIndex);

	/**
	 * 分页查询符合依据的实体
	 * 
	 * @param criteria
	 *            依据
	 * @param pageSize
	 *            页面大小
	 * @param pageIndex
	 *            当前页码
	 * @return 符合依据的当前页数据
	 * 
	 */
	IPage<E> findPageBy(DetachedCriteria criteria, int pageSize, int pageIndex);

	/**
	 * 统计符合依据的实体的个数
	 * 
	 * @param criteria
	 *            依据
	 * @return 实体的个数
	 * 
	 */
	int countBy(DetachedCriteria criteria);

	/**
	 * 查询符合依据的实体
	 * 
	 * @param entity
	 *            实体依据
	 * @return 符合依据的数据
	 * 
	 */
	List<E> findBy(E entity);

	/**
	 * 分页查询符合依据的实体
	 * 
	 * @param entity
	 *            实体依据
	 * @param pageSize
	 *            页面大小
	 * @param pageIndex
	 *            当前页码
	 * @return 符合依据的当前页数据
	 * 
	 */
	List<E> findBy(E entity, int pageSize, int pageIndex);

	/**
	 * 分页查询符合依据的实体
	 * 
	 * @param entity
	 *            实体依据
	 * @param pageSize
	 *            页面大小
	 * @param pageIndex
	 *            当前页码
	 * @return 符合依据的当前页数据
	 * 
	 */
	IPage<E> findPageBy(E entity, int pageSize, int pageIndex);

	/**
	 * 统计符合依据的实体的个数
	 * 
	 * @param entity
	 *            实体依据
	 * @return 实体的个数
	 * 
	 */
	int countBy(E entity);

	// 实体全集合查询 ----

	/**
	 * 查询所有实体
	 * 
	 * @return 所有实体
	 */
	List<E> findAll();

	/**
	 * 分页查询所有实体
	 * 
	 * @param pageSize
	 *            页面大小
	 * @param pageIndex
	 *            当前页码
	 * @return 当前页数据
	 * 
	 */
	List<E> findAll(int pageSize, int pageIndex);

	/**
	 * 分页查询所有实体
	 * 
	 * @param pageSize
	 *            页面大小
	 * @param pageIndex
	 *            当前页码
	 * @return 当前页数据
	 * 
	 */
	IPage<E> findPageAll(int pageSize, int pageIndex);

	/**
	 * 统计所有实体
	 * 
	 * @return 所有实体的个数
	 * 
	 */
	int countAll();
}
