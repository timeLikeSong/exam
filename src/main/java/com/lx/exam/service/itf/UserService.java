package com.lx.exam.service.itf;

import java.util.Date;
import java.util.List;

import com.lx.exam.po.PoUser;

public interface UserService {
	public void save(PoUser user);
	public PoUser get(Long id);
	public void edit(PoUser user);
	public void delete(PoUser user);
	public void delete(Long id);
	
	public PoUser findByUsername(String username);
	public List<PoUser> findByRealnameIsLike(String realname);
	public List<PoUser> findUsers(String realname,Date start,Date end);
	public long getCount();
}
