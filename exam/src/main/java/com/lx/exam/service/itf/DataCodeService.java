package com.lx.exam.service.itf;

import java.util.List;

import com.lx.exam.po.PoDataCode;
import com.lx.exam.vo.DataCode;

public interface DataCodeService {
	DataCode add(DataCode dataCode);
	boolean delete(Long id);
	boolean delete(DataCode dataCode);
	DataCode edit(DataCode dataCode);
	DataCode get(Long id);
	List<DataCode> list(Long pid);
}
