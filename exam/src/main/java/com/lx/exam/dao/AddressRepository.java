package com.lx.exam.dao;


import org.springframework.data.repository.CrudRepository;

import com.lx.exam.po.PoAddress;

public interface AddressRepository extends CrudRepository<PoAddress, Long>,AddressRepositoryExt{

}
