package com.accenture.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.accenture.webapp.domain.Employee;
import com.accenture.webapp.service.EmployeeServiceInterface;

import java.util.ArrayList;


@Controller
@ComponentScan
public class EmployeeController {

	@Autowired
	EmployeeServiceInterface employeeServiceInterface;
	
	@GetMapping("/")
	public String savePage(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("allEmployees", (ArrayList<Employee>)employeeServiceInterface.getAllEmployees());
		return "index";
	}
	
	@GetMapping("/addEmployeePage")
	public String addEmployeePage(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("allEmployees", (ArrayList<Employee>)employeeServiceInterface.getAllEmployees());
		return "addEmployeePage";
	}
	//EDITED HERE
	@GetMapping("/managePage")
	public String managePage(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("allEmployees", (ArrayList<Employee>)employeeServiceInterface.getAllEmployees());
		return "managePage";
	}
	//ENDS HERE
	@PostMapping("/employee/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee,
			final RedirectAttributes redirectAttributes) {
		
		if(employeeServiceInterface.saveEmployee(employee)!=null) {
			redirectAttributes.addFlashAttribute("saveEmployee", "success");
		} else {
			redirectAttributes.addFlashAttribute("saveEmployee", "unsuccess");
		}
		
		return "redirect:/managePage";
	}
	
	@RequestMapping(value = "/employee/{operation}/{empId}", method = RequestMethod.GET)
	public String editRemoveEmployee(@PathVariable("operation") String operation,
			@PathVariable("empId") String empId, final RedirectAttributes redirectAttributes,
			Model model) {
		if(operation.equals("delete")) {
			if(employeeServiceInterface.deleteEmployee(empId)) {
				redirectAttributes.addFlashAttribute("deletion", "success");
			} else {
				redirectAttributes.addFlashAttribute("deletion", "unsuccess");
			}
		} else if(operation.equals("edit")){
		  Employee editEmployee = employeeServiceInterface.findEmployee(empId);
		  if(editEmployee!=null) {
		       model.addAttribute("editEmployee", editEmployee);
		       return "editPage";
		  } else {
			  redirectAttributes.addFlashAttribute("status","notfound");
		  }
		}
		
		return "redirect:/managePage";
	}
	
	@RequestMapping(value = "/employee/update", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute("editEmployee") Employee editEmployee,
			final RedirectAttributes redirectAttributes) {
		if(employeeServiceInterface.editEmployee(editEmployee)!=null) {
			redirectAttributes.addFlashAttribute("edit", "success");
		} else {
			redirectAttributes.addFlashAttribute("edit", "unsuccess");
		}
//		return "redirect:/savepage";
		return "redirect:/managePage";
	}
}
