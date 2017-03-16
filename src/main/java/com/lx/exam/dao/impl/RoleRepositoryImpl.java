package com.lx.exam.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lx.exam.dao.RoleRepositoryExt;

public class RoleRepositoryImpl implements RoleRepositoryExt {
	@PersistenceContext(unitName="EMF")
	EntityManager em;
}
