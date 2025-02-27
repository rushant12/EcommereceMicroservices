package com.bank.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bank.customer.entity.Customer;
import com.bank.customer.model.UserResponse;
import com.bank.customer.service.CustomerService;

@RestController
@RequestMapping (value = "/api/v1/customers")
public class CustomerController {
	
	@Autowired
	public CustomerService customerServices;
	
	@PostMapping (value = "/register")
	public ResponseEntity<UserResponse> customerRegistration(@RequestBody Customer customer)
	{
		UserResponse userResponse = customerServices.registerCustomer(customer);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}
	
	@PostMapping (value = "/login")
	public String customerLogin(@RequestParam String username, @RequestParam String password)
	{
		System.out.println(username + "" + password);
		return customerServices.loginCustomer(username, password);
	}
	
}
