package com.rest.Services;

import java.util.List;

import com.rest.api.v1.model.CustomerDTO;

public interface CustomerService {
	List<CustomerDTO> getAllCustomer();
	CustomerDTO getCostumerById(Long id);
	CustomerDTO createNewCustomer(CustomerDTO customerDTO);
	CustomerDTO updateNewCustomer(Long id, CustomerDTO customerDTO);
	CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
	void deleteCustomerById(Long id);
}
