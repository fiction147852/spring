package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service //AOP가 적용될 유일한 BEAN
public class EmpServiceImpl implements EmpService{
	//기능상 연결될 매퍼 추가
	@Autowired	//@AutoWired는 개별로 지정해줘야한다 같이하면 안됨
	EmpMapper empMapper;
	
//	@Autowired
//	DeptMapper deptMapper;
	

	@Override
	public List<EmpVO> empList() {
		return empMapper.selectEmpAll();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		
		return result == 1 ? empVO.getEmployeeId() : -1 ;
	}

	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) {	//MAP은 LIST만큼 자주 사용한다 / 많은 양을 담을수있어서. value에 들어가는게 object인데 담을수없는값이 없다 
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;	//하나는 boolean타입 하나는 class타입 그렇기에 MAP을 사용한다
		
		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		
		if(result ==1) {
			inSuccessed = true;
		}
		
		map.put("result", inSuccessed);
		map.put("target", empVO);
		/*
		 * {
		 * 		"result" : true,
		 * 		"target" : { emloyeeId : 100, lastName : "King" }
		 * 
		 */
		return map;
	}

	@Override
	public Map<String, Object> empDelete(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		
		int reuslt = empMapper.deleteEmpInfo(empVO.getEmployeeId());
		
		if(reuslt == 1) {
			map.put("employeeId", empVO.getEmployeeId());
		}
		return map;
	}
	
}
