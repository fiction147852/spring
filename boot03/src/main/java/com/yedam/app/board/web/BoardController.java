package com.yedam.app.board.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardVO;
import com.yedam.app.service.impl.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService; // 이건 어떤 조건으로 작성하는지 정리할것

	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) {
		List<BoardVO> list = boardService.boardList();
		model.addAttribute("boards", list);

		return "board/boardList";
		//classpath:/templates/ + board/boardList + .html
		//어디에 뭘 만들어야하는지는 이걸 보면 알수있다
	}

	// 단건조회 : URI - boardInfo / PARAMETER - boardVO(QueryString)
	// Return - board/boardInfo
	@GetMapping("boardInfo")
	public String boardInfo(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);

		return "board/boardInfo";
	}

	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertForm(Model model) {	//일반적인 <form/> 활용
//		model.addAttribute("boardVO", new BoardVO());
		return "board/boardInsert";
	}

	// 등록 - 처리 : URI - boardInsert / PARAMETER - boardVO(QueryString)
	// RETURN - 전체조회 다시 호출
	@PostMapping("boardInsert")
	public String boardInsertProcess(BoardVO boardVO) {	//대체로객체로 값을 받는데 queryString으로 받느냐 json으로 받느냐에따라 어노테이션이달라진다
			//등록이 커맨드객체를 많이쓰는데 왜?
			//등록은 submit을 활용하는경우가 많은데 이건 자동으로 json이 되지 않는다 왜? from태그가 갖고있는것중에 json은 없다
			//multipartformdata와 queyrString 두개가 있다 //나중에 정리 필요
			
//		int bno = boardService.boardInsert(boardVO);
//		String url = null;
//		if (bno > -1) {
//			url = "redirect:boardInfo?boardNo" + bno;
//		} else {
//			url = "redirect:boardList";
//		}
		boardService.insertBoard(boardVO);
		return "redirect:boardList";
		
	}

	// 수정 - 페이지 : URI - boardUpdate / PARAMETER - boardVO(QueryString)
	// RETURN - board/boardUpdate
	@GetMapping("boardUpdate")
	public String boardUpdateForm(Integer boardNo, Model model) {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(boardNo);
		
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);
		
		return "board/boardUpdate";	//redirect는 GetMapping만 요구한다 다른건 안된다
	}

	
	// 수정 - 처리 : URI - boardUpdate / PARAMETER - boardVO(JSON)
	// RETURN - 수정결과 데이터(Map)
	@PostMapping("boardUpdate")
	@ResponseBody
	public Map<String, Object> boardUpdateAJAXJSON(@RequestBody BoardVO boardVO){
		return boardService.boardUpdate(boardVO);
	}
	// 삭제 - 처리 : URI - boardDelete / PARAMETER - Integer
	// RETURN - 전체조회 다시 호출
	@GetMapping("boardDelete")
	public String boardDelete(Integer bno) {
		boardService.boardDelete(bno);
		return "redirect:boardList";
	}
	
	
}
