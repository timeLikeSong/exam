package com.lx.exam.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lx.exam.dao.AdminRepositoryExt;
import com.lx.exam.dao.FunctionRepositoryExt;

public class AdminRepositoryImpl implements AdminRepositoryExt {
	@PersistenceContext(unitName="EMF")
	EntityManager em;
}
