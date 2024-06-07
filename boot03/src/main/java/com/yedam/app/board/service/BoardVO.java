package com.yedam.app.board.service;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Integer bno;
	private String title;
	private String contents;
	private String writer;
	private Date regdate;
	private Date updatedate;
	private String image;
}
