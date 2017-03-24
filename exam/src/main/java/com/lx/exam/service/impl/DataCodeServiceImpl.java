package com.lx.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.exam.dao.DataCodeRepository;
import com.lx.exam.po.PoDataCode;
import com.lx.exam.service.itf.DataCodeService;
import com.lx.exam.vo.DataCode;
@Service
public class DataCodeServiceImpl implements DataCodeService {
	@Autowired
	DataCodeRepository dataCodeRepository;
	
	@Override
	public DataCode add(DataCode dataCode) {
		PoDataCode poDataCode = new PoDataCode(dataCode);
		return new DataCode(dataCodeRepository.save(poDataCode));
	}
	@Override
	@Transactional
	public boolean delete(Long id) {
		if(id!=null){
			List<PoDataCode> list = dataCodeRepository.findByPoDataCodeId(id);
			if(list.size()>0){
				for(PoDataCode poDataCode : list){
					delete(poDataCode.getId());
				}
			}
//			if(list!=null && list.size()>0){
//				dataCodeRepository.delete(list);
//			}
			dataCodeRepository.delete(id);
			return true;
		}
		return false;
	}
	public boolean delete(Long[] ids){
		return false;
	}
	@Override
	public DataCode edit(DataCode dataCode) {
		PoDataCode poDataCode = dataCodeRepository.findOne(dataCode.getId());
		poDataCode.wrap(dataCode);
		dataCodeRepository.save(poDataCode);
		return new DataCode(poDataCode);
	}
	@Override
	public DataCode get(Long id) {
		return new DataCode(dataCodeRepository.findOne(id));
	}
	public List<DataCode> list(Long pid){
		List<PoDataCode> poDataCodes;
		List<DataCode> dataCodes = new ArrayList<DataCode>();
		if(pid==null || pid ==0){
			poDataCodes = (List<PoDataCode>) dataCodeRepository.findAll();
		}
		else{
			poDataCodes = (List<PoDataCode>) dataCodeRepository.findByPoDataCodeId(pid);
		}
		for(PoDataCode poDataCode:poDataCodes){
			dataCodes.add(new DataCode(poDataCode));
		}
		return dataCodes;
	}
	@Override
	public boolean delete(DataCode dataCode) {
		if(dataCode!=null){
			return delete(dataCode.getId());
		}
		return false;
	}
	
}
