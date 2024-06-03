package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller // => 사용자의 요청(Endpoint)에 대한 처리
			// :  url + method <=> service <=> view		//요청에대한 서비스를 화면으로
public class EmpController {
	// 해당 컨트롤러에서 제공하는 서비스들 추가
	@Autowired
	EmpService empService;
	
	// GET  : 조회, 빈페이지				//등록하거나 수정할때 GET방식 안쓴다. 왜? 보안이 좋지않다 왜? 경로에 붙어있기때문에 쉽게본다
	// POST : 데이터 조작(등록, 수정, 삭제)		// POST는 HEADER를 한번 거쳐서 오기때문에 보안이 있따
	
	// 전체조회 : GET
	@GetMapping("empList")		//이 괄호가 경로다
	public String empList(Model model) {	//Model = Request + response 를 합쳐놓은것 그냥 Model 사용하면 된다 구분하지말고
		// 1) 해당 기능 수행 -> Service
		List<EmpVO> list = empService.empList();
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("empList", list); 		// Model에 값을 담는다 add attribute
		return "emp/list";				// 3) 데이터를 출력할 페이지 결정 / 이 return이 페이지에 대한정보를 반환한다 정도라고 알면된다	//왜 model에 return안하는이유는 메모리에있어서 리턴하지 않아도 전달받는다
			//classpath:/templates/			emp/list		.html
			// prefix						return			subfix
	}
	
	// 단건조회 : GET
	@GetMapping("empInfo")		//커맨드 객체 => QueryString = 
	public String empInfo(EmpVO empVO, Model model) {
		// 1) 해당 기능 수행 -> Service
		EmpVO findVO = empService.empInfo(empVO);
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("empInfo", findVO);
		
		return "emp/info"; // 3) 데이터를 출력할 페이지 결정
		// "classpath:/templates/" + "emp/info" + "thml"	//이작업을 뷰 리절브가 한다
		// => classpath:/templates/emp/info.html			//이작업을 뷰 리절브가 한다
		// classpath: => src/main/resources 를 가리킨다 -> 내부적으로 src 리소시즈를 가리키고있따
	}
	
	// 등록 - 페이지 : GET 		//빈페이지를 불러온다
	@GetMapping("empInsert")
	public String empInsertForm(Model model) {
		model.addAttribute("empVO", new EmpVO());
		return "emp/insert";
	}
	
	// 등록 - 처리		=> form 태그를 통한 submit
	@PostMapping("empInsert")				
	public String empInsertProcess(EmpVO empVO) {	//등록할건 한사람에대한 정보를 받아서 insert할것이다 / EmpVO empVO는 맞는데 어떤포멧을할지 정해야한다 -> 커맨드객체(쿼리 string) or ??? 
		int eid = empService.empInsert(empVO);
		String url = null;
		if(eid > -1) {
			// 정상적으로 등록된 경우		//redirect라고 되있는걸 return받게된다면 다시 핸들러 어댑터를 돌린다 => 무슨말?
			url = "redirect:empInfo?employeeId=" + eid;		// 2)프로젝트 내부에있는 컨트롤러 아무거나 써주면 된다		// 3) 페이지가 아니라특정한 데이터값을 반환하는것 responsBody (페이지가아닌 데이터가보이게딘다)
		}else {
			// 정상적으로 등록 되지 않은 경우
			url = "redirect:empList";  //redirect: 을 꼭 써야한다 / 이건 컨트롤러가아닌 ???를 호출한다
		}
		
		return url;		//원래는 페이지를 정해야하는 return에
	}
	
	
	// 수정 - 페이지
	@GetMapping("empupdate")
	public String empUpdateForm(Integer employeeId, Model model) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeeId);
		
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("empInfo", findVO);
		
		return "emp/update";
	}
	
	//update.html에서 처리하는데 쿼리스트링과 제이슨방식 두가지를 잘 봇것
	// 수정 - 처리 : AJAX => QueryString	
//	@PostMapping("empupdate")
	@ResponseBody // => AJAX
	public Map<String, Object> empUpdateAJAXQueryString(EmpVO empVO){
		return empService.empUpdate(empVO);
	}
	

	// 수정 - 처리 : AJAX => JSON (@RequestBody) 를 요구한다
	@PostMapping("empupdate")
	@ResponseBody // => AJAX
	public Map<String, Object> empUpdateAJAXJSON(@RequestBody EmpVO empVO){
		return empService.empUpdate(empVO);
	}
	
	// 삭제 - 처리		//정보를 아예 삭제하기때문에 페이지를 지워버린다  
	@GetMapping("empdelete")			//이게 경로다
	public String empDelete(EmpVO empVO) {
		empService.empDelete(empVO);
		return "redirect:empList";		
	}
}
