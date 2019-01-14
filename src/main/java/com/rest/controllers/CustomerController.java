package com.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.Services.CustomerService;
import com.rest.api.v1.model.CustomerDTO;
import com.rest.api.v1.model.CustomerListDTO;

@Controller
@RequestMapping("/api/v1/customer/")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	 
	@GetMapping
	public ResponseEntity<CustomerListDTO> getAllCategories(){
		return new ResponseEntity<CustomerListDTO>(
				new CustomerListDTO(customerService.getAllCustomer()), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable Long id){
		return new ResponseEntity<CustomerDTO>(
				customerService.getCostumerById(id), HttpStatus.OK);
	}


}
