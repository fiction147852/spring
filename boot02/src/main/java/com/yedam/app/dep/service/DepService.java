package com.yedam.app.dep.service;

import java.util.List;
import java.util.Map;

public interface DepService {
	
	//전체 부서조회
	public List<DepVO> depList();
	
 	//단건 부서조회
	public DepVO depInfo(DepVO depVO);
	
	//부서 등록
	public int depInsert(DepVO depVO);
	
	//부서 수정
	public Map<String, Object> depUpdate(DepVO depVO);
	
	//부서 삭제
	public Map<String, Object> depDelete(DepVO depVO);
}
