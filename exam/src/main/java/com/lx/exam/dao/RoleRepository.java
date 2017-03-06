package com.lx.exam.dao;


import org.springframework.data.repository.CrudRepository;

import com.lx.exam.po.PoRole;

public interface RoleRepository extends CrudRepository<PoRole, Long>,RoleRepositoryExt{

}
