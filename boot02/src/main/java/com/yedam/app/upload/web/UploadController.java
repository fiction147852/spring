package com.yedam.app.upload.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UploadController {
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@GetMapping("formUpload")
	public void formUpLoadPage() {
		
	}
	
	
	@PostMapping("uploadForm")
	public String formUploadFile(@RequestPart MultipartFile[] images) {
		log.info(uploadPath);
		for(MultipartFile image : images) {
			log.info(image.getContentType());			//개별 파일의 종류
			log.info(image.getOriginalFilename());		//사용자가 넘겨준 실제 파일이름
			log.info(String.valueOf(image.getSize()));	//파일크기
			
			String fileName = image.getOriginalFilename();
			String saveName = uploadPath + File.separator + fileName;	//File.separator를 써야 경로로 인정된다 자바는 슬러쉬/를 인식못한다
			
			log.debug("saveName : " + saveName);
			
			Path savePath = Paths.get(saveName);	//해당경로를 이용해서 실제 파일을 끄집어낸다
			try {
				image.transferTo(savePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "formUpload";
	}
	
	@GetMapping("upload")	
	public void getUploadPath() {
		
	}
	
	
	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles) {
	    
		List<String> imageList = new ArrayList<>();
		
	    for(MultipartFile uploadFile : uploadFiles){
	    	if(uploadFile.getContentType().startsWith("image") == false){
	    		System.err.println("this file is not image type");
	    		return null;
	        }
	  
	        String originalName = uploadFile.getOriginalFilename();
	        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
	        
	        System.out.println("fileName : " + fileName);
	    
	        //날짜 폴더 생성
	        String folderPath = makeFolder();	//
	        //UUID 생성
	        String uuid = UUID.randomUUID().toString();	//uuid 현재시점 식별자코드
	        
	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;
	        
	        String saveName = uploadPath + File.separator + uploadFileName;
	        
	        Path savePath = Paths.get(saveName);
	        System.out.println("path : " + saveName);
	        
	        try {
	        	uploadFile.transferTo(savePath); // 파일 저장
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        
	        // DB에 해당 경로 저장
	        // 1) 사용자가 업로드할 때 사용한 파일명
	        // 2) 실제 서버에 업로드할 때 사용한 경로
	        imageList.add(setImagePath(uploadFileName));
	     }
	    
	    return imageList;
	}
	
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));	//심플하게하려고 코드를 이렇게 썻다 왜?어떤걸?
		String folderPath = str.replace("/", File.separator);
		File uploadPathFoler = new File(uploadPath, folderPath);
		
		if (uploadPathFoler.exists() == false) {		//경로가 존재하는지 안하는지 체크
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory라는 코드로 생성한다
			// mkdir 
			// mkdir 
		}
		
		return folderPath;
	}
	
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}
}
