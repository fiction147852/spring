package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.servicce.EmpVO;

public interface EmpMapper {
	// 전체조회
	public List<EmpVO> selectEmpAll();	//selectEmpAll 이게 xml파일 id
	
	// 단건조회
	public EmpVO selectEmpInfo(EmpVO empVO);	//selectEmpInfo 이게 xml 파일 id
	
	// 등록
	public int insertEmpInfo(EmpVO empVO);
	
	// 수정
	public int updateEmpInfo(@Param("eid")int empId,
								@Param("info")EmpVO empVO);
	
	// 삭제
	public int deleteEmpInfo(int empId);
	
}
