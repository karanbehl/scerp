package com.scerp.ecommercesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


import com.scerp.ecommercesystem.domain.CustomerEntity;
import com.scerp.ecommercesystem.domain.ToDoEvent;
import com.scerp.ecommercesystem.repository.CustomerRepository;
import com.scerp.ecommercesystem.service.ToDoService;


@RestController
@RequestMapping("/")
public class ToDoController {
	@Autowired
	private ToDoService toDoService;

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping
	public Iterable<ToDoEvent> getToDoEvent() {
		return toDoService.getAllnonDoneEvents();
	}
	
	
	@GetMapping
	@RequestMapping(value="/customerSignup", method=RequestMethod.POST)
	public String signUp(@RequestBody CustomerEntity customerinfo)
	{
		CustomerEntity cxentity = customerRepository.findByCustomerEmail(customerinfo.getEmail());
		if(cxentity!=null)
			return "Cx already Registed";
		else
		customerRepository.save(customerinfo);
		return "customer Signed Up!!";
	}
	
	@RequestMapping(value="/customerLogin", method=RequestMethod.POST)
	public String login(@RequestBody CustomerEntity customerinfo)
	{
		CustomerEntity cxentity = customerRepository.findByCustomerEmail(customerinfo.getEmail());
		if(cxentity.getPassword().equals(customerinfo.getPassword()))
			return "Login Successful";
		else
			return "Login Un-successful";
	}
	
	
	
}
