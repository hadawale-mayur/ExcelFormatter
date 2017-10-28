package com.sarang.formatter.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormatterController {
	
	@RequestMapping(value="/welcome.do",method=RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("welcome");
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/error.do",method=RequestMethod.GET)
	public ModelAndView error() {
		return new ModelAndView("error");
	}
	
	@RequestMapping(value="/format.do",method=RequestMethod.POST)
	public ResponseEntity<byte[]> format(@RequestParam("file") MultipartFile file) throws IOException {
		
		byte[] contents = file.getBytes();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
	    String filename = file.getOriginalFilename();
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
	    return response;
	}
	

}
