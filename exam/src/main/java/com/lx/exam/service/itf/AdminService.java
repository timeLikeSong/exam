package com.lx.exam.service.itf;

import java.util.List;

import com.lx.exam.vo.Admin;

public interface AdminService {
	Admin add(Admin admin);
	boolean delete(Admin admin);
	boolean delete(Long id);
	Admin edit(Admin admin);
	Admin get(Long id);
	List<Admin> list();
}
