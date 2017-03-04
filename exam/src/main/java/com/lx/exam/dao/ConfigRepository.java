package com.lx.exam.dao;


import org.springframework.data.repository.CrudRepository;

import com.lx.exam.po.PoConfig;

public interface ConfigRepository extends CrudRepository<PoConfig, Long>,ConfigRepositoryExt{
	PoConfig findByConfigKey(String key);
}
