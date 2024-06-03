package com.yedam.app.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@CrossOrigin
@Controller
public class TestController {
//  원래는 이렇게 하나를 사용했다 하지만 밑에 get post방식으로 나누면서 return도 추가해준다
//	@RequestMapping("/test")	//경로를 지정한다 원래는 path랑 @@@가 있다 정리할것	/url기능이 너무많아져서 나눈것
//	@ResponseBody
//	public String urlTest(String keyword) {
//		return "Server Response : " + keyword;
//	}

//	@RequestMapping(path = "/test", method = RequestMethod.GET)		//이 한줄이 바로밑에 GetMapping으로 한줄로 줄어들게된다 요약본참고할것정리할때
	@GetMapping("test")
	@ResponseBody
	public String urlGetTest(String keyword) {
		return "Server Response : SELECT - " + keyword;
	}

//	@RequestMapping(path = "/test", method = RequestMethod.POST) // url기능이 너무많아져서 나눈것 GET이냐 POST냐
	@PostMapping("test")
	@ResponseBody
	public String urlPostTest(String keyword) {
		return "Server Response : INSERT - " + keyword;
	}

	//thymeleaf
	@Autowired
	EmpService empService;
	
	@GetMapping("thymeleaf")
	public String thymeleaftTest(Model model) {
		EmpVO empVO = new EmpVO();
		EmpVO findVO = empService.empInfo(empVO);
		
		model.addAttribute("empInfo", findVO);
		
		return "test";
		
	}
	
}
