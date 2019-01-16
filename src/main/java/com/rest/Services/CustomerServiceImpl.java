package com.rest.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rest.api.v1.mapper.CustomerMapper;
import com.rest.api.v1.model.CustomerDTO;
import com.rest.domain.Customer;
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
				//.map(customerMapper::customerToCustomerDTO)
				.map(customer -> {
					CustomerDTO cust = customerMapper.customerToCustomerDTO(customer);
					cust.setUrl("/api/v1/customer/"+customer.getId());
					return cust;
				})																							
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCostumerById(Long id) {
		CustomerDTO customer = customerMapper.customerToCustomerDTO(customerRepository.findById(id)
											.orElseThrow(ResourceNotFoundException::new));
		customer.setUrl("/api/v1/customer/"+customer.getId());
		return customer;

	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
		return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
	}

	@Override
	public CustomerDTO updateNewCustomer(Long id, CustomerDTO customerDTO) {
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		customer.setId(id);
		return saveAndReturnDTO(customer);
	}

	private CustomerDTO saveAndReturnDTO(Customer customer) {

		Customer savedCustomer = customerRepository.save(customer);		
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(savedCustomer);		
		customerDTO.setUrl("/api/v1/customer/"+customerDTO.getId());

		return customerDTO;

	}

	@Override
	public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
		return customerRepository.findById(id).map(customer -> {

			if(customerDTO.getFirstName() != null){
				customer.setFirstName(customerDTO.getFirstName());
			}

			if(customerDTO.getLastName() != null){
				customer.setLastName(customerDTO.getLastName());
			}

			return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
		}).orElseThrow(RuntimeException::new); //todo implement better exception handling;
	}

	@Override
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);		
	}
}
