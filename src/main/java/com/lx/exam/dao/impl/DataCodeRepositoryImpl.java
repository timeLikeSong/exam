package com.lx.exam.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lx.exam.dao.DataCodeRepositoryExt;
import com.lx.exam.po.PoDataCode;

public class DataCodeRepositoryImpl implements DataCodeRepositoryExt {
	@PersistenceContext(unitName="EMF")
	EntityManager em;
	
}
