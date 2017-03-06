package com.lx.exam.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lx.exam.po.PoDataCode;

public interface DataCodeRepository extends CrudRepository<PoDataCode, Long>,DataCodeRepositoryExt{
	List<PoDataCode> findByPoDataCodeId(Long pid);
}
