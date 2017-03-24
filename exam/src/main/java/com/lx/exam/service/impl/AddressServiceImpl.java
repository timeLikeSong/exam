package com.lx.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.exam.dao.AddressRepository;
import com.lx.exam.po.PoAddress;
import com.lx.exam.service.itf.AddressService;
@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	AddressRepository addressRepository;
	@Override
	public PoAddress get(Long id) {
		return addressRepository.findOne(id);
	}
	@Override
	public String getAllAddrName(Long id) {
		if(id!=0){
			return getAllAddrName(get(id).getPid())+" "+get(id).getName();
		}
		else{
			return "";
		}
	}

}
