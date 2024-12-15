package com.bank.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bank.customer.authentication.JWTUtility;
import com.bank.customer.entity.Customer;
import com.bank.customer.model.UserResponse;
import com.bank.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	JWTUtility JWTUtility;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserResponse registerCustomer(Customer customer) {
		
		UserResponse userResponse = new UserResponse();
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		if(customer != null) {
		customerRepository.save(customer);
		userResponse.setMessage("User Registered Successfully");
		userResponse.setCode("Success");
		}
		return userResponse;
	}

	@Override
	public String loginCustomer(String username, String password) {
		
	Customer customer =	customerRepository.findByUsername(username).
		orElseThrow(() -> new RuntimeException("Customer not found!"));
		
		if(passwordEncoder.matches(password, customer.getPassword())) {
			return JWTUtility.generateToken(username);
		}else {
			throw new RuntimeException("Invalid Credential");
		}
	}
	
	
	
}
