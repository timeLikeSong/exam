package com.lx.exam.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lx.exam.dao.FunctionRepositoryExt;

public class FunctionRepositoryImpl implements FunctionRepositoryExt {
	@PersistenceContext(unitName="EMF")
	EntityManager em;
}
