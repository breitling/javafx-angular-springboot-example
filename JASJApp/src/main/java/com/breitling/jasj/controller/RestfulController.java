package com.breitling.jasj.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@CrossOrigin(origins = "*")
@RequestMapping("/JAS/v1")
public class RestfulController 
{
	List<String> list = new ArrayList<String>(Arrays.asList("Bob","Billy","Gale","Jo"));
	
	@GetMapping(value = "/users", produces = { "application/json" })
	public ResponseEntity<List<String>> usersGet()
	{
		list.add(list.get(new Random().nextInt(4)));
		
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }
	
//  EXCEPTION HANDLING
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e)
    {
        return new ResponseEntity<String>(makeJson(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
    private String makeJson(String r) 
    {
        return String.format("\"%s\"", r);
    }
}
