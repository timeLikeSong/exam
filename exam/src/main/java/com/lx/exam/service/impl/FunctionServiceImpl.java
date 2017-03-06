package com.lx.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.exam.dao.FunctionRepository;
import com.lx.exam.dao.RoleRepository;
import com.lx.exam.po.PoDataCode;
import com.lx.exam.po.PoFunction;
import com.lx.exam.service.itf.FunctionService;
import com.lx.exam.vo.Function;
@Service
public class FunctionServiceImpl implements FunctionService{
	@Autowired
	FunctionRepository functionRepository;
	@Autowired
	RoleRepository roleRepository;
	@Override
	public Function add(Function function) {
		PoFunction poFunction = new PoFunction(function);
		return new Function(functionRepository.save(poFunction));
	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		if(id!=null){
			List<PoFunction> list = functionRepository.findByPoFunctionId(id);
			if(list!=null && list.size()>0){
				functionRepository.delete(list);
			}
			functionRepository.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Function function) {
		if(function!=null){
			return delete(function.getId());
		}
		return false;
	}
	@Override
	public Function edit(Function function) {
		PoFunction poFunction = functionRepository.findOne(function.getId());
		poFunction.wrap(function);
		functionRepository.save(poFunction);
		return new Function(poFunction);
	}

	@Override
	public Function get(Long id) {
		return new Function(functionRepository.findOne(id));
	}
	@Override
	public List<Function> list(Long pid) {
		List<PoFunction> poFunctions;
		List<Function> functions= new ArrayList<Function>();
		if(pid!=null && pid!=0L){
			poFunctions= functionRepository.findByPoFunctionId(pid);
		}
		else{
			poFunctions= (List<PoFunction>) functionRepository.findAll();
		}
		for(PoFunction poFunction:poFunctions){
			functions.add(new Function(poFunction));
		}
		return functions;
	}
}
