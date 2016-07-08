package com.lutongnet.base.dao;

import java.util.List;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

public class EmptyEntityManager implements EntityManager{

	@Override
	public void persist(Object paramObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T merge(T paramT) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Object paramObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T find(Class<T> paramClass, Object paramObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T find(Class<T> paramClass, Object paramObject, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T find(Class<T> paramClass, Object paramObject, LockModeType paramLockModeType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T find(Class<T> paramClass, Object paramObject, LockModeType paramLockModeType,
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getReference(Class<T> paramClass, Object paramObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFlushMode(FlushModeType paramFlushModeType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FlushModeType getFlushMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void lock(Object paramObject, LockModeType paramLockModeType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lock(Object paramObject, LockModeType paramLockModeType, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object paramObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object paramObject, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object paramObject, LockModeType paramLockModeType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object paramObject, LockModeType paramLockModeType, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(Object paramObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LockModeType getLockMode(Object paramObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperty(String paramString, Object paramObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createQuery(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> paramCriteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery(String paramString, Class<T> paramClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createNamedQuery(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String paramString, Class<T> paramClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createNativeQuery(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createNativeQuery(String paramString, Class paramClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createNativeQuery(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void joinTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T unwrap(Class<T> paramClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDelegate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EntityTransaction getTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Metamodel getMetamodel() {
		// TODO Auto-generated method stub
		return null;
	}


}
