package com.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.persistence.IdIncrementRepository;

@RestController
public class SequenceWebController {
	 @Autowired
	 IdIncrementRepository repo;
	
	 @GetMapping  (value="v1/nextid/", produces=MediaType.APPLICATION_JSON_VALUE)
	 public long getNextId(HttpServletRequest httpRequest){
		 return repo.getNextIncrement();
	 }
}
