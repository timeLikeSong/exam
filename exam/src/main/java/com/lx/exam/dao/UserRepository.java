package com.lx.exam.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lx.exam.po.PoUser;

public interface UserRepository extends CrudRepository<PoUser, Long>,PagingAndSortingRepository<PoUser, Long>,UserRepositoryExt{
	PoUser findByUsername(String username);

	List<PoUser> findByRealnameIsLike(String realname);

	List<PoUser> findByRealnameIsLikeOrRegDateBetween(String realname,Date start,Date end);
	
	
}
