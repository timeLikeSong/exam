package com.lx.exam.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.lx.exam.dao.ConfigRepositoryExt;

public class ConfigRepositoryImpl implements ConfigRepositoryExt {
	@PersistenceContext(unitName="EMF")
	EntityManager em;

}
