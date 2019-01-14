package com.rest.Services;

import java.util.List;

import com.rest.api.v1.model.CategoryDTO;
import com.rest.api.v1.model.CustomerDTO;
import com.rest.api.v1.model.CustomerListDTO;

public interface CustomerService {
	List<CustomerDTO> getAllCustomer();
	CustomerDTO getCostumerById(Long id);
}
