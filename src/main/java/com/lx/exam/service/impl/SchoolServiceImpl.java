package com.lx.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.exam.dao.SchoolRepository;
import com.lx.exam.po.PoSchool;
import com.lx.exam.service.itf.SchoolService;
@Service
public class SchoolServiceImpl implements SchoolService{
	@Autowired
	SchoolRepository schoolRepository;
	@Override
	public List<PoSchool> listSchoolByName(String schoolName) {
		return schoolRepository.findByNameLike("%"+schoolName+"%");
	}
	@Override
	public PoSchool get(Long id) {
		return schoolRepository.findOne(id);
	}
	
}
