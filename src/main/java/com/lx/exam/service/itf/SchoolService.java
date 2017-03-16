package com.lx.exam.service.itf;

import java.util.List;

import com.lx.exam.po.PoSchool;

public interface SchoolService {
	List<PoSchool> listSchoolByName(String schoolName);

	PoSchool get(Long id);
}
