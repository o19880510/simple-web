package woo.study.web.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import woo.study.web.common.dao.parameter.ArraySqlParameter;
import woo.study.web.common.dao.parameter.EmptySqlParameter;
import woo.study.web.common.dao.parameter.ListSqlParameter;
import woo.study.web.common.dao.parameter.MapSqlParameter;
import woo.study.web.common.dao.parameter.SqlParameter;
import woo.study.web.common.dao.vo.PaginationBean;
import woo.study.web.common.util.GenericsUtils;

@Repository
public class EntityDaoSupport<T>{
	
	/**
	 * 注入JPA实体管理者，注意要显示设置unitName，方便以后多数据源操作
	 */
	@PersistenceContext(unitName = "default")
	private EntityManager	entityManager;
	
	protected Class<T> entityClass;
	
	private static final String SELECT_COUNT_HEADER =  " SELECT COUNT(*) ";
	
	protected final String FROM_ENTITY;
	public EntityDaoSupport(){
		
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
		FROM_ENTITY = " FROM " + entityClass.getCanonicalName() + " ";
	}
	
	
	protected Session getSession() {
		
		if(getEntityManager() == null){
			return null;
		}
		
		return (Session) getEntityManager().getDelegate();
	}
	
	protected PaginationBean<T> findByPaging(String conditionHQL, SqlParameter sqlParamter, int currentPage, int pageSize) {
		
		String dataHql = FROM_ENTITY + conditionHQL;
		String countHql = SELECT_COUNT_HEADER + dataHql ;
		
		int totalCount = queryInt(countHql, sqlParamter);
		
		return findByPaging(conditionHQL, sqlParamter, currentPage, pageSize, totalCount);
	}
	
	protected PaginationBean<T> findByPaging(String conditionHQL, Object[] params, int currentPage, int pageSize) {
		return findByPaging(conditionHQL, new ArraySqlParameter(params), currentPage, pageSize);
	}
	protected PaginationBean<T> findByPaging(String conditionHQL, List<Object> params, int currentPage, int pageSize) {
		return findByPaging(conditionHQL, new ListSqlParameter(params), currentPage, pageSize);
	}
	protected PaginationBean<T> findByPaging(String conditionHQL, Map<String, Object> params, int currentPage, int pageSize) {
		return findByPaging(conditionHQL, new MapSqlParameter(params), currentPage, pageSize);
	}
	protected PaginationBean<T> findByPaging(String conditionHQL, int currentPage, int pageSize) {
		return findByPaging(conditionHQL, EmptySqlParameter.getInstance(), currentPage, pageSize);
	}
	
	
	protected PaginationBean<T> findByPaging(String conditionHQL, SqlParameter sqlParamter, int currentPage, int pageSize, int totalCount) {
		
		String dataHql = FROM_ENTITY + conditionHQL;
		
		PaginationBean<T> pb = new PaginationBean<T>(currentPage, totalCount, pageSize);
		List<T> dataList = getRecordQuery(dataHql, sqlParamter)
								.setFirstResult(pb.getStart())
								.setMaxResults(pb.getPageSize())
								.getResultList();
		pb.setDataList(dataList);
		return pb;
	}
	
	protected PaginationBean<T> findByPaging(String conditionHQL, Object[] params, int currentPage, int pageSize, int totalCount) {
		return findByPaging(conditionHQL, new ArraySqlParameter(params), currentPage, pageSize, totalCount);
	}
	protected PaginationBean<T> findByPaging(String conditionHQL, List<Object> params, int currentPage, int pageSize, int totalCount) {
		return findByPaging(conditionHQL, new ListSqlParameter(params), currentPage, pageSize, totalCount);
	}
	protected PaginationBean<T> findByPaging(String conditionHQL, Map<String, Object> params, int currentPage, int pageSize, int totalCount) {
		return findByPaging(conditionHQL, new MapSqlParameter(params), currentPage, pageSize, totalCount);
	}
	protected PaginationBean<T> findByPaging(String conditionHQL, int currentPage, int pageSize, int totalCount) {
		return findByPaging(conditionHQL, EmptySqlParameter.getInstance(), currentPage, pageSize, totalCount);
	}
	
	
	
	public T get(Serializable id) {
		return getEntityManager().find(entityClass, id);
	}
	public <K> K get(Serializable id, Class<K> clazz) {
		return getEntityManager().find(clazz, id);
	}
	
	public List<T> getAll() {
		String hql = "from " + entityClass.getCanonicalName();
		return queryList(hql);
	}
	
	// queryList  START
	
	protected List<T> queryList(String hql, SqlParameter sqlParamter) {
		return getRecordQuery(hql, sqlParamter).getResultList();
	}
	protected List<T> queryList(String hql) {
		return getRecordQuery(hql, EmptySqlParameter.getInstance()).getResultList();
	}
	protected List<T> queryList(String hql, Object... params) {
		return getRecordQuery(hql, new ArraySqlParameter(params)).getResultList();
	}
	protected List<T> queryList(String hql, List<Object> params) {
		return getRecordQuery(hql, new ListSqlParameter(params)).getResultList();
	}
	protected List<T> queryList(String hql, Map<String, Object> params) {
		return getRecordQuery(hql, new MapSqlParameter(params)).getResultList();
	}
	protected <K> List<K> queryList(String hql, SqlParameter sqlParamter, Class<K> clazz) {
		return getRecordQuery(hql, sqlParamter, clazz).getResultList();
	}
	protected <K> List<K> queryList(String hql, Class<K> clazz) {
		return getRecordQuery(hql, EmptySqlParameter.getInstance(), clazz).getResultList();
	}
	protected <K> List<K> queryList(String hql,Object[] params, Class<K> clazz) {
		return getRecordQuery(hql, new ArraySqlParameter(params), clazz).getResultList();
	}
	protected <K> List<K> queryList(String hql,List<Object> params, Class<K> clazz) {
		return getRecordQuery(hql, new ListSqlParameter(params), clazz).getResultList();
	}
	protected <K> List<K> queryList(String hql, Map<String, Object> params, Class<K> clazz) {
		return getRecordQuery(hql, new MapSqlParameter(params), clazz).getResultList();
	}
	
	protected List<T> queryList(String hql, SqlParameter sqlParamter, int firstResult, int maxResult) {
		return getRecordQuery(hql, sqlParamter).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
	}
	protected List<T> queryList(String hql, Object[] params, int firstResult, int maxResult) {
		return getRecordQuery(hql, new ArraySqlParameter(params)).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
	}
	protected List<T> queryList(String hql, List<Object> params, int firstResult, int maxResult) {
		return getRecordQuery(hql, new ListSqlParameter(params)).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
	}
	protected List<T> queryList(String hql, Map<String, Object> params, int firstResult, int maxResult) {
		return getRecordQuery(hql, new MapSqlParameter(params)).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
	}
	
	
	protected <K> List<K> queryList(String hql, SqlParameter sqlParamter,int firstResult, int maxResult, Class<K> clazz) {
		return getRecordQuery(hql, sqlParamter, clazz).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
	}
	protected <K> List<K> queryList(String hql,int firstResult, int maxResult, Class<K> clazz) {
		return getRecordQuery(hql, EmptySqlParameter.getInstance(), clazz).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
	}
	protected <K> List<K> queryList(String hql, Object[] params, int firstResult, int maxResult, Class<K> clazz) {
		return getRecordQuery(hql, new ArraySqlParameter(params), clazz).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
	}
	protected <K> List<K> queryList(String hql, List<Object> params, int firstResult, int maxResult, Class<K> clazz) {
		return getRecordQuery(hql, new ListSqlParameter(params), clazz).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
	}
	protected <K> List<K> queryList(String hql, Map<String, Object> params, int firstResult, int maxResult, Class<K> clazz) {
		return getRecordQuery(hql, new MapSqlParameter(params), clazz).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
	}
	// queryList  END
	
	
	// queryInt START
	protected int queryInt(String hql, SqlParameter sqlParamter) {
		Number number = getNumberQuery(hql, sqlParamter).getSingleResult();
		return number == null ? 0 : number.intValue();
	}
	protected int queryInt(String hql ) {
		return queryInt(hql, EmptySqlParameter.getInstance());
	}
	protected int queryInt(String hql, Object... params) {
		return queryInt(hql, new ArraySqlParameter(params));
	}
	protected int queryInt(String hql, List<Object> params ) {
		return queryInt(hql, new ListSqlParameter(params));
	}
	protected int queryInt(String hql, Map<String, Object> params ) {
		return queryInt(hql, new MapSqlParameter(params));
	}
	// queryInt END
	
	// queryFirst START
	protected T queryFirst(String hql, SqlParameter sqlParamter) {
		
		TypedQuery<T> queryRecords = getEntityManager().createQuery(hql, entityClass);
		sqlParamter.setParamter(queryRecords);
		queryRecords.setFirstResult(0);
		queryRecords.setMaxResults(1);
		
		List<T> list = queryRecords.getResultList();
		
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
	protected T queryFirst(String hql) {
		return queryFirst(hql, EmptySqlParameter.getInstance());
	}
	protected T queryFirst(String hql, Object... params) {
		return queryFirst(hql, new ArraySqlParameter(params));
	}
	protected T queryFirst(String hql, List<Object> params ) {
		return queryFirst(hql, new ListSqlParameter(params));
	}
	protected T queryFirst(String hql, Map<String, Object> params ) {
		return queryFirst(hql, new MapSqlParameter(params));
	}
	
	protected <K> K queryFirst(String hql, SqlParameter sqlParamter, Class<K> clazz) {
		List<K> list = queryList(hql, sqlParamter, clazz);
		return list.size() == 0 ? null : list.get(0);
	}
	protected <K> K  queryFirst(String hql, Class<K> clazz ) {
		return queryFirst(hql, EmptySqlParameter.getInstance(), clazz);
	}
	protected <K> K  queryFirst(String hql, Object[] params, Class<K> clazz ) {
		return queryFirst(hql, new ArraySqlParameter(params), clazz);
	}
	protected <K> K  queryFirst(String hql, List<Object> params , Class<K> clazz ) {
		return queryFirst(hql, new ListSqlParameter(params), clazz);
	}
	protected <K> K  queryFirst(String hql, Map<String, Object> params , Class<K> clazz ) {
		return queryFirst(hql, new MapSqlParameter(params), clazz);
	}
	// queryFirst END
	
	
	
	// update START
	protected void update(String hql, SqlParameter sqlParamter) {
		Query query = getEntityManager().createQuery(hql);
		sqlParamter.setParamter(query);
		query.executeUpdate();
	}
	protected void update(String hql) {
		update(hql, EmptySqlParameter.getInstance());
	}
	protected void update(String hql, Object... params) {
		update(hql, new ArraySqlParameter(params));
	}
	protected void update(String hql, List<Object> params) {
		update(hql, new ListSqlParameter(params));
	}
	protected void update(String hql, Map<String, Object> params) {
		update(hql, new MapSqlParameter(params));
	}
	// update END
	
	public void update(Object obj) {
		
		getEntityManager().merge(obj);
	}
	
	public void save(Object obj) {
		
		getEntityManager().persist(obj);
	}
	
	
	/**
	 * 删除用户
	 */
	public void remove(Serializable id) {
		T t = get(id);
		getEntityManager().remove(t);
	}
	
	/**
	 * 删除用户
	 */
	public void remove(T obj) {
		getEntityManager().remove(obj);
	}
	
	
	
	public boolean existsByProperty(String propertyName, String propertyValue, Serializable id) {
		if (id == null) {
			return existsByProperty(propertyName, propertyValue);
		} else {
			String hql = "from " + entityClass.getCanonicalName() + " where " + propertyName + " = ? " + " and id <> ? ";
			return queryList(hql, new ArraySqlParameter(propertyValue, id) ).size() > 0;
		}
	}
	
	public boolean existsByName(String name, Integer id) {
		return existsByProperty("name", name, id);
	}
	
	public boolean existsByProperty(String propertyName, String propertyValue) {
		String hql = "from " + entityClass.getCanonicalName() + " where " + propertyName + " = ? ";
		return queryList(hql, new ArraySqlParameter(propertyValue)).size() > 0;
	}
	
	protected TypedQuery<T> getRecordQuery(String dataHQL, SqlParameter sqlParamter){
		TypedQuery<T> queryRecords = getEntityManager().createQuery(dataHQL, entityClass);
		sqlParamter.setParamter(queryRecords);
		return queryRecords;
	}
	
	protected <K> TypedQuery<K> getRecordQuery(String dataHQL, SqlParameter sqlParamter, Class<K> clazz){
		TypedQuery<K> queryRecords = getEntityManager().createQuery(dataHQL, clazz);
		sqlParamter.setParamter(queryRecords);
		return queryRecords;
	}
	
	protected TypedQuery<Number> getNumberQuery(String dataHQL, SqlParameter sqlParamter){
		TypedQuery<Number> queryRecords = getEntityManager().createQuery(dataHQL, Number.class);
		sqlParamter.setParamter(queryRecords);
		return queryRecords;
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager =  entityManager;
	}
}
