package com.lx.exam.service.itf;

import java.util.List;

import com.lx.exam.po.PoFunction;
import com.lx.exam.vo.Function;

public interface FunctionService {
	public Function add(Function function);
	public boolean delete(Function function);
	public boolean delete(Long id);
	public Function edit(Function function);
	public Function get(Long id);
	public List<Function> list(Long pid);
}
