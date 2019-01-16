package com.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	 
	@GetMapping()
	public ResponseEntity<CustomerListDTO> getAllCustomers(){
		return new ResponseEntity<CustomerListDTO>(
				new CustomerListDTO(customerService.getAllCustomer()), HttpStatus.OK);
	}
	
//	@GetMapping("/api/v1/customer/")
//	public ResponseEntity<CustomerListDTO> getAllCustomers(){
//		CustomerListDTO customers = new CustomerListDTO(customerService.getAllCustomer());
//		Resource<CustomerListDTO> res = new Resource<CustomerListDTO>(customers);
//		
//		return res;
//	}
	
	@GetMapping("{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
		return new ResponseEntity<CustomerDTO>(
				customerService.getCostumerById(id),HttpStatus.OK);
	}

//	@GetMapping("/api/v1/customer/{id}")
//	public Resource<CustomerDTO> getCustomerById(@PathVariable Long id){
//		CustomerDTO customer = customerService.getCostumerById(id);
//		
//		Resource<CustomerDTO> res = new Resource<CustomerDTO>(customer);
//		
//		ControllerLinkBuilder lingTo = ControllerLinkBuilder.linkTo(
//				ControllerLinkBuilder.methodOn(this.getClass()).getAllCustomers());
//
//		res.add(lingTo.withRel("all-users"));
//
//		return res;
//
//	}
	
	@PostMapping
	public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
		return new 	ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<CustomerDTO> updateNewCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		return new 	ResponseEntity<CustomerDTO>(customerService.updateNewCustomer(id,customerDTO), HttpStatus.OK);
	}

	@PatchMapping("{id}")
	public ResponseEntity<CustomerDTO> patchNewCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		return new 	ResponseEntity<CustomerDTO>(customerService.patchCustomer(id,customerDTO), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteNewCustomer(@PathVariable Long id) {
		customerService.deleteCustomerById(id);
		return new 	ResponseEntity<Void>(HttpStatus.OK);
	}

	
}
