package com.lx.exam.service.itf;

import java.util.List;

import com.lx.exam.vo.Role;

public interface RoleService {
	Role add(Role role);
	boolean delete(Role role);
	boolean delete(Long id);
	Role edit(Role role);
	Role get(Long id);
	List<Role> list();
}
