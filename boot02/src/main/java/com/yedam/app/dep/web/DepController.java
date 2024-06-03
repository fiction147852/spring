package com.yedam.app.dep.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.dep.service.DepService;
import com.yedam.app.dep.service.DepVO;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class DepController {

	@Autowired				//의존성 주입 하는것 
	DepService depService;

	// 부서전체조회
	@GetMapping()
	public String depList(Model model) {
		// 1) 해당기능수행
		List<DepVO> list = depService.depList();
		// 2) 클라이언트에 전달한 데이터 받기
		model.addAttribute("depList", list);
		return "dep/list";
	}

	// 부서 단건조회 : GET
	@GetMapping("depInfo")
	public String depInfo(DepVO depVO, Model model) {
		//1) 해당 기능 수행 -> service
		DepVO findVO = depService.depInfo(depVO);	// 2번 DepVO에서 데이터를 findVO라는이름에다가데이터를 담을것이다 어떻게? depservice의 depInfo로
		//2) 클라이언트에게 전달할 데이터 담기
		model.addAttribute("depInfo", findVO);		// 1번 model이란 보따리에 addAttribute가 담는다는말 뭐를? depInfo라는 이름으로 findVO  데이터를 담는다는말
		
		return "dep/info";
	}
	
	
	// 등록 - 페이지 : GET 		//빈페이지를 불러온다
	@GetMapping("depInsert")
	public String depInsertForm(Model model) {
		model.addAttribute("depVO", new DepVO());
		return "dep/insert";
	}
	
	// 등록 - 처리		=> form 태그를 통한 submit
	@PostMapping("depInsert")				
	public String depInsertProcess(DepVO depVO) {	
		int did = depService.depInsert(depVO);
		String url = null;
		if(did >-1) {
			url = "redirect:depInfo?departmentId=" + did;
		}else {
			url = "redirect:empList";  
		}
		
		return url;
	}
	// 수정 - 페이지
	@GetMapping("depupdate")
	public String swpUpdateForm(Integer departmentId, Model model) {
		DepVO depVO = new DepVO();
		depVO.setDepartmentId(departmentId);
		
		DepVO findVO = depService.depInfo(depVO);
		model.addAttribute("depInfo", findVO);
		
		return "dep/update";
	}
	// 수정 - 처리 : AJAX => QueryString
//	@PostMapping("depupdate")
	@ResponseBody // => AJAX
	public Map<String, Object> depUpdateAJAXQueryString(DepVO depVO){
		return depService.depUpdate(depVO);
	}
	

	// 수정 - 처리 : AJAX => JSON (@RequestBody) 를 요구한다
	@PostMapping("depupdate")
	@ResponseBody // => AJAX
	public Map<String, Object> depUpdateAJAXJSON(@RequestBody DepVO depVO){
		return depService.depUpdate(depVO);
	}
	
	// 삭제 - 처리 //정보를 아예 삭제하기때문에 페이지를 지워버린다
	@GetMapping("depDelete")
	public String depDelete(DepVO depVO) {
		depService.depDelete(depVO);
		
		return "redirect:depList";
	}
}
