package com.lx.exam.service.itf;

import com.lx.exam.po.PoAddress;

public interface AddressService {
	public PoAddress get(Long id);
	
	public String getAllAddrName(Long id);
}
