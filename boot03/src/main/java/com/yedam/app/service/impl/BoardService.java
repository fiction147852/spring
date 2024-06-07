package com.yedam.app.service.impl;

import java.util.List;
import java.util.Map;

import com.yedam.app.board.service.BoardVO;

public interface BoardService {
	//전체조회
	public List<BoardVO>  boardList();
	
	//단건조회
	public BoardVO boardInfo (BoardVO boardVO);
	
	//등록
	public int insertBoard(BoardVO boardVO);
	
	//수정
	public Map<String, Object> boardUpdate(BoardVO boardVO);
	
	//삭제
	public int boardDelete(int boardNO);
}
