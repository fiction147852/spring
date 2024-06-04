package com.yedam.app.dep.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.dep.service.DepVO;

public interface DepMapper {
	// 전체조회
	public List<DepVO> selectDepAll();	
	
	// 단건조회
	public DepVO selectDepInfo(DepVO depVO);	
	
	// 등록
	public int insertDepInfo(DepVO depVO);
	
	// 수정
	public int updateDepInfo(@Param("did")int DepId,		// 수정할
								@Param("info")DepVO depVO);	// 여러개를 입력해서 값을 넘길때 Param을 사용한다.
	
	// 삭제
	public int deleteDepInfo(int depId);
}
