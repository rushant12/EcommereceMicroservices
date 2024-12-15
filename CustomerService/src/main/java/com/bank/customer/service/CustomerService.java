package com.bank.customer.service;

import com.bank.customer.entity.Customer;
import com.bank.customer.model.UserResponse;

public interface CustomerService {
	
	public UserResponse registerCustomer(Customer customer);
	
	public String loginCustomer(String username, String password);


}