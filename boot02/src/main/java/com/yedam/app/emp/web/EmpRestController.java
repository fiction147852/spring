package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

	import com.yedam.app.emp.service.EmpService;
	import com.yedam.app.emp.service.EmpVO;

@RestController	//@Controller + 모든 메소드에 @ResponseBody를 적용했다고본다 
				//@ResponseBody는 바로 응답한다 라는것이다  => AJAX용으로 만들겠다
						//이 내부에 있는 모든 컨트롤러는 아작스가 되는것이다
public class EmpRestController {
	@Autowired
	EmpService empService;
	
	//전체조회	: GET(메소드) => emps
	@GetMapping("emps")
	public List<EmpVO> empList(){	//원래 모델을썻지만 rest방식일때는 model을 안쓴다 그래서 한꺼번에 전달해야한다
		return empService.empList();
	}
	
		
	//단건조회 : GET(메소드) => emps/employeeId=100 사용안됨 //쿼리스트링을 사용할수가없다  
				      // => emps/100 이라고 사용	{}를 패스 베리어블*(PathVariable)라고한다
	@GetMapping("emps/{id}")
	public EmpVO empInfo(@PathVariable(name = "id") //pathvariable은 
									Integer employeeId) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeeId);
		return empService.empInfo(empVO);
	}
	//등록 : POST(메소드) => emps
	@PostMapping("emps")	//@RequestBody : JSON
	public int empInsert(@RequestBody EmpVO empVO) { //JSON형식으로 받는건 requestBody다
		return empService.empInsert(empVO);
	}
		
	
	//수정 : PUT(메소드) => emps/100	=> 한대상을 지칭해줘야한다 등록된 대상을상대로 할건 경로에 매핑해야한다
	@PutMapping("emps/{id}") //바디에 json형식으로 집어넣는다 ?? 질문
	public Map<String, Object> empUpdate(	//경로를 통해서 수정할 Target(대상)
											@PathVariable(name = "id") Integer  employeeId,
											//수정할 정보는 JSON 포맷으로
											@RequestBody EmpVO empVO){
		empVO.setEmployeeId(employeeId);
		return empService.empUpdate(empVO);
	}
	
	//삭제 : DELETE(메소드) => emps/100
	@DeleteMapping("emps/{employeeId}")
	public Map<String, Object> dempDelete(@PathVariable Integer employeeId){
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeeId);
		return empService.empDelete(empVO);
	}
	

}
