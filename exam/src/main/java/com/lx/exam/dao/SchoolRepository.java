package com.lx.exam.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lx.exam.po.PoAddress;
import com.lx.exam.po.PoSchool;

public interface SchoolRepository extends CrudRepository<PoSchool, Long>,SchoolRepositoryExt{
	List<PoSchool> findByNameLike(String schoolName);
}
