//package org.packt.webstore.controller;
//
//import org.packt.webstore.domain.Customer;
//import org.packt.webstore.service.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/customers")
//public class CustomerController {
//
//	@Autowired
//	private CustomerService customerService;
//	
//	@GetMapping
//	public String list(Model model) {
//	
//		model.addAttribute("customers", this.customerService.getAllCustomers());
//		
//		return "customers";
//	
//	}
//	
//	@GetMapping("/add")
//	public String getAddCustomer(@ModelAttribute("newCustomer") Customer customer) {
//		return "addCustomer";
//	}
//	
//	@PostMapping("/add")
//	public String processAddNewCustomerForm(@ModelAttribute("newCustomer") Customer customer) {
//		this.customerService.addCustomer(customer);
//		return "redirect:/customers";
//	}
//	
//}
