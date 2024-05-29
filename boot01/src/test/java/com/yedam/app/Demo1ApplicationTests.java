package com.yedam.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Demo1ApplicationTests {
	@Autowired
	EmpMapper empMapper;

	@Test
	void empAllList() {
		// 전체조회
		List<EmpVO> list = empMapper.selectEmpAll();
		for (EmpVO empVO : list) {
			System.out.println(empVO);
		}
	}

}
