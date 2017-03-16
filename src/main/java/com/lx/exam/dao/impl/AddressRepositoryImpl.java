package com.lx.exam.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lx.exam.dao.AddressRepositoryExt;

public class AddressRepositoryImpl implements AddressRepositoryExt {
	@PersistenceContext(unitName="EMF")
	EntityManager em;

}
