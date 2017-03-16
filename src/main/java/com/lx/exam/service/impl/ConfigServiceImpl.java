package com.lx.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.exam.dao.ConfigRepository;
import com.lx.exam.po.PoConfig;
import com.lx.exam.service.itf.ConfigService;
@Service
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	ConfigRepository configRepository;

	@Override
	public void add(PoConfig poConfig) {
		configRepository.save(poConfig);
	}

	@Override
	public void delete(PoConfig poConfig) {
		configRepository.delete(poConfig);;;
	}

	@Override
	public void edit(PoConfig poConfig) {
		configRepository.save(poConfig);
	}

	@Override
	public PoConfig getByKey(String name) {
		return configRepository.findByName(name);
	}

}
