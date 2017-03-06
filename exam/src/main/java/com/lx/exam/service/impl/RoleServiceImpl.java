package com.lx.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.exam.dao.RoleRepository;
import com.lx.exam.po.PoDataCode;
import com.lx.exam.po.PoRole;
import com.lx.exam.service.itf.RoleService;
import com.lx.exam.vo.Role;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;
	@Override
	public Role add(Role role) {
		PoRole poRole = new PoRole(role);
		return new Role(roleRepository.save(poRole));
	}

	@Override
	@Transactional
	public boolean delete(Role role) {
		if(role!=null){
			return delete(role.getId());
		}
		return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		if(id!=null){
			roleRepository.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public Role edit(Role role) {
		PoRole poRole = roleRepository.findOne(role.getId());
		poRole.wrap(role);
		roleRepository.save(poRole);
		return new Role(poRole);
	}

	@Override
	public Role get(Long id) {
		return new Role(roleRepository.findOne(id));
	}

	@Override
	public List<Role> list() {
		List<PoRole> poRoles;
		List<Role> roles = new ArrayList<Role>();
		poRoles=(List<PoRole>) roleRepository.findAll();
		for(PoRole poRole : poRoles){
			roles.add(new Role(poRole));
		}
		return roles;
	}

}
