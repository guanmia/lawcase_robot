package com.anyun100.legal.lawcase_robot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.anyun100.legal.lawcase_robot.service.StorageService;


@RestController
@RequestMapping("/lawrobot/api/case")
public class CaseContentDownloadController {
	
	@Autowired
	private StorageService storageService;

	@RequestMapping(value = "/content/download", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> downloadFile(@RequestParam(value = "case_id", required = true) String case_id) { 
		try {
	        FileSystemResource file = new FileSystemResource(storageService.load(case_id).toFile());  
	        HttpHeaders headers = new HttpHeaders();  
	        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");  
	        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));  
	        headers.add("Pragma", "no-cache");  
	        headers.add("Expires", "0");  
	  
	        return ResponseEntity  
	                .ok()  
	                .headers(headers)  
	                .contentLength(file.contentLength())  
	                .contentType(MediaType.parseMediaType("application/octet-stream"))  
	                .body(new InputStreamResource(file.getInputStream()));  
       } catch (Exception e) {
    	   e.printStackTrace();
       }
		return null;
	}
}
