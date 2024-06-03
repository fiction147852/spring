package com.yedam.app.emp.service;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmpVO {
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	//HandlerAdapter가 파라미터 자동 맵핑 시 적용할 포맷
    @DateTimeFormat(pattern = "yyyy-MM-dd") //-을 사용하는 포맷을쓰겟다고 선언해주면 된다
    private Date hireDate;
    private String jobId;
    private double salary;
    private double commissionPct;
    private int managerId;
    private int departmentId;
}
