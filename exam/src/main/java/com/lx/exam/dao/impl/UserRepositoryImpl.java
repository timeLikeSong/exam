package com.lx.exam.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lx.exam.dao.UserRepositoryExt;

public class UserRepositoryImpl implements UserRepositoryExt {
	@PersistenceContext(unitName="EMF")
	EntityManager em;
	@Override
	public long getCount() {
		Query query = em.createQuery("select count(*) from PoAddress");
		return (long)query.getSingleResult();
	}
	
}
