package com.lx.exam.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.exam.dao.UserRepository;
import com.lx.exam.po.PoUser;
import com.lx.exam.service.itf.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Override
	@Transactional
	public void save(PoUser user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public PoUser get(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	@Transactional
	public void edit(PoUser user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void delete(PoUser user) {
		userRepository.delete(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public PoUser findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<PoUser> findByRealnameIsLike(String realname) {
		return userRepository.findByRealnameIsLike(realname);
	}

	@Override
	public long getCount() {
		return userRepository.getCount();
	}

	@Override
	public List<PoUser> findUsers(String realname, Date start, Date end) {
		if(start==null){
			start=new Date("0000/01/01");
		}
		if(end==null){
			end=new Date("9999/01/01");
		}
		return userRepository.findByRealnameIsLikeOrRegDateBetween(realname, start, end);
	}
	
	
	
}
