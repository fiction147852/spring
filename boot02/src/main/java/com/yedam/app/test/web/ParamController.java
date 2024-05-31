package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@Controller
public class ParamController {
	// QueryString(질의문자열) : key=value&key=value&...
	// method는 상관없음
	// Content-type : application/x-www-form-urlencoded				json타입을 보낼땐 변경해줘야한다. 뭐를?
		
	// QueryString => 커맨드객체  => @RequestParam : 기본타입, 단일값
//	@RequestMapping(path="comobj",
//					method= {RequestMethod.GET, RequestMethod.POST})
	
	
	@RequestMapping(path="reqparm", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	//
	public String commandObject(@RequestParam Integer employeeId,	//필수
											  String lastName,		//생략가능	/어노케이션이 없기때문에 생략가능 /어노케이션이 무엇인지?
								@RequestParam(name="message",							//이 name값은 덮어쓰겠다 라는말?
											  defaultValue="No message",		//필수값인 정보가 없다면 default값으로 이걸 보낸다 no message 
											  required = true) String msg) {
		String result = "";
		result += "path : /reqparm \n";
		result += "\t employee_id : "  +employeeId;
		result += "\t last_name : " + lastName;
		result += "\t message : " + msg;
		return result;
	}
	
	// @PathVariable : 경로에 값을 포함하는 방식, 단일 값  /경로에 해당하기대문에 method, content-type이 뭐든 상관없다 /질문
	// Method는 상관없음
	// Content-type도 상관없음 
	//rest API 방식  요즘 자주 사용하는것
	@RequestMapping("path/{id}") 	// path/Hong, path/1234  => //포멧상 path/{id} 인데 /뒤에 뭐든 있긴해야한다라는말 그래서 값을 넘기지 않을거라고해서 안넘기면 404가뜰것이니까 뭐라도넣는단 말
	@ResponseBody
	public String pathVariable(@PathVariable String id) {
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
		
	}
	
	// @RequestBody : JSON 포맷, 배열 or 객체
	// METHOD : POST, PUT
	// Content-tupe : application/json
	@PostMapping("resbodyList")
	@ResponseBody
	public String requesBodyList(@RequestBody List<EmpVO> list) {
		String result = "path : /resbodyList \n";
		for(EmpVO empVO : list) {
			result += "\t employee_id : " + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			result += "\n";
		}
		return result;
	}
}


