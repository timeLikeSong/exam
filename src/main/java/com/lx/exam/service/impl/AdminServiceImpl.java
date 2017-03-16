package com.lx.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.exam.dao.AdminRepository;
import com.lx.exam.po.PoAdmin;
import com.lx.exam.po.PoRole;
import com.lx.exam.service.itf.AdminService;
import com.lx.exam.vo.Admin;
import com.lx.exam.vo.Role;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminRepository adminRepository;
	@Override
	public Admin add(Admin admin) {
		PoAdmin poAdmin = new PoAdmin(admin);
		return new Admin(adminRepository.save(poAdmin));
	}

	@Override
	public boolean delete(Admin admin) {
		if(admin!=null){
			return delete(admin.getId());
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		if(id!=null){
			adminRepository.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public Admin edit(Admin admin) {
		PoAdmin poAdmin = adminRepository.findOne(admin.getId());
		poAdmin.wrap(admin);
		adminRepository.save(poAdmin);
		return new Admin(poAdmin);
	}

	@Override
	public Admin get(Long id) {
		return new Admin(adminRepository.findOne(id));
	}

	@Override
	public List<Admin> list() {
		List<PoAdmin> poAdmins;
		List<Admin> admins = new ArrayList<Admin>();
		poAdmins=(List<PoAdmin>) adminRepository.findAll();
		for(PoAdmin poAdmin : poAdmins){
			admins.add(new Admin(poAdmin));
		}
		return admins;
	}

}
