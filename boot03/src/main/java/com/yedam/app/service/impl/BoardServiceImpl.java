package com.yedam.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardVO;

@Service //@Transactional 여기서 동작해야한다			// 서비스 어노테이션 까먹지말것 근데 무슨역할?
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;

	@Override
	public List<BoardVO> boardList() {
		return boardMapper.selectBoardAll();
	}

	@Override
	public BoardVO boardInfo(BoardVO boardVO) {
		return boardMapper.selectBoardInfo(boardVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		int result = boardMapper.boardInsertInfo(boardVO);

		// 삼항 연산자 => 하나의 조건식을 기준으로 두개의 값 중 하나를 결정
//		return result == 1 ? boardVO.getBno() : -1;
		if (result == 1) {
			return boardVO.getBno();
		} else {
			return -1;
		}

	}

	@Override
	public Map<String, Object> boardUpdate(BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = boardMapper.updateBoardInfo(boardVO);
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", boardVO);
		
		return map;
	}

	@Override
	public int boardDelete(int boardNO) {
		return boardMapper.deleteBoardInfo(boardNO);
	}

}
