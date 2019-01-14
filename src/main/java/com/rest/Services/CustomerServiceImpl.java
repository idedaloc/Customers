package com.rest.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rest.api.v1.mapper.CustomerMapper;
import com.rest.api.v1.model.CustomerDTO;
import com.rest.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		super();
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public List<CustomerDTO> getAllCustomer() {

		return customerRepository.findAll()
								.stream()
								.map(customerMapper::customerToCustomerDTO)
								.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCostumerById(Long id) {

		return customerMapper.customerToCustomerDTO(customerRepository.findById(id).get());
	}

}
