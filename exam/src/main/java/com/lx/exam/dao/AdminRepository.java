package com.lx.exam.dao;


import org.springframework.data.repository.CrudRepository;

import com.lx.exam.po.PoAdmin;

public interface AdminRepository extends CrudRepository<PoAdmin, Long>,AdminRepositoryExt{

}
