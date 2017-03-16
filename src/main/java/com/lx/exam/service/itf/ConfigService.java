package com.lx.exam.service.itf;

import com.lx.exam.po.PoConfig;

public interface ConfigService {
	void add(PoConfig poConfig);
	void delete(PoConfig poConfig);
	void edit(PoConfig poConfig);
	PoConfig getByKey(String key);
}
