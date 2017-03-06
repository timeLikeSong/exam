package com.lx.exam.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lx.exam.po.PoFunction;

public interface FunctionRepository extends CrudRepository<PoFunction, Long>,FunctionRepositoryExt{
	List<PoFunction> findByPoFunctionId(Long pid);
}
