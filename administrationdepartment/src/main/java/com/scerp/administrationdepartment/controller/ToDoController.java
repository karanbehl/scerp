package com.scerp.administrationdepartment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scerp.administrationdepartment.domain.AdminEntity;
import com.scerp.administrationdepartment.domain.EmployeeEntity;
import com.scerp.administrationdepartment.domain.ToDoEvent;
import com.scerp.administrationdepartment.service.ToDoService;

@RestController
@RequestMapping("/")
public class ToDoController {
	@Autowired
	private ToDoService toDoService;

	@GetMapping
	public Iterable<ToDoEvent> getToDoEvent() {
		return toDoService.getAllnonDoneEvents();
	}


	@RequestMapping(value = "/addEmplpoyee", method = RequestMethod.POST)
	public String addEmployee(@RequestBody EmployeeEntity employeeInfo, @RequestBody AdminEntity adminInfo) {
		return toDoService.addEmployeeService(employeeInfo,adminInfo);
	}
	

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
	public String deleteEmployee(@RequestBody EmployeeEntity employeeInfo, @RequestBody AdminEntity adminInfo) {
		return toDoService.deleteEmployeeService(employeeInfo,adminInfo);
	}
	
	@RequestMapping(value = "/modifyEmployee", method = RequestMethod.POST)
	public String modifyEmployee(@RequestBody EmployeeEntity employeeInfo, @RequestBody AdminEntity adminInfo) {
		return toDoService.modifyEmployeeService(employeeInfo,adminInfo);
	}

	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public String adminLogin(@RequestBody AdminEntity adminEntity) {
		return toDoService.adminLoginService(adminEntity);
	}
	
}