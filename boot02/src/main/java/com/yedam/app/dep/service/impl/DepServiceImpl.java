package com.yedam.app.dep.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dep.mapper.DepMapper;
import com.yedam.app.dep.service.DepService;
import com.yedam.app.dep.service.DepVO;

@Service
public class DepServiceImpl implements DepService{
	@Autowired
	DepMapper depMapper;
	
	@Override
	public List<DepVO> depList() {
		return depMapper.selectDepAll();
	}

	@Override
	public DepVO depInfo(DepVO depVO) {
		return depMapper.selectDepInfo(depVO);
	}

	@Override
	public int depInsert(DepVO depVO) {
		int result = depMapper.insertDepInfo(depVO);
		
		return result == 1 ? depVO.getDepartmentId() : -1 ;
	}

	@Override		//Map이 키 값 키 값 으로 값을 받는것이니까 depVO를 받는데 그걸 키값 으로 정의를 해두고 사용한다?
	public Map<String, Object> depUpdate(DepVO depVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		
		int result = depMapper.updateDepInfo(depVO.getDepartmentId(), depVO);
		
		if(result == 1) {
			inSuccessed = true;
		}
		
		map.put("result", inSuccessed);
		map.put("target", depVO);
		
		return map;
	}

	@Override
	public Map<String, Object> depDelete(DepVO depVO) {
		Map<String, Object> map = new HashMap<>();
		
		int result = depMapper.deleteDepInfo(depVO.getDepartmentId());
		
		if(result ==1) {
			map.put("departmentId", depVO.getDepartmentId());
		}
		
		return map;
	}

}
