package com.yedam.app.dep.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.dep.service.DepVO;

public interface DepMapper {
	// 전체조회
	public List<DepVO> selectDepAll();	//selectEmpAll 이게 xml파일 id
	
	// 단건조회
	public DepVO selectDepInfo();	//selectEmpInfo 이게 xml 파일 id
	
	// 등록
	public int insertDepInfo(DepVO depVO);
	
	// 수정
	public int updateDepInfo(@Param("eid")int DepId,		// 수정할
								@Param("info")DepVO depVO);	// 여러개를 입력해서 값을 넘길때 Param을 사용한다.
	
	// 삭제
	public int deleteDepInfo(int depId);
}
