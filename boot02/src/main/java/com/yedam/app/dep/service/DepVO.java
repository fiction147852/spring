package com.yedam.app.dep.service;

import lombok.Data;

@Data
public class DepVO {
	private Integer departmentId;
	private String departmentName;
	private int managerId;
	private Integer locationId;
}
