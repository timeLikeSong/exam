package com.lx.exam.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lx.exam.dao.SchoolRepositoryExt;

public class SchoolRepositoryImpl implements SchoolRepositoryExt {
	@PersistenceContext(unitName="EMF")
	EntityManager em;

}
