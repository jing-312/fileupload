package com.bit2017.fileupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit2017.fileupload.service.FileUploadService;

@Controller
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping( "/form" )
	public String form() {
		return "form";
	}
	
	@RequestMapping( "/upload" )
	public String upload(
		@RequestParam("email") String email,
		@RequestParam("file1") MultipartFile file1,
		Model model
		){
		
		String url = fileUploadService.restore( file1 );
		
		model.addAttribute( "imageURL", url );
		return "result";
	}
}	

