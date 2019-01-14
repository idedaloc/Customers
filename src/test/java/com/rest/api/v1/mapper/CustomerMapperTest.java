package com.rest.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.api.v1.model.CustomerDTO;
import com.rest.domain.Customer;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class CustomerMapperTest {

	public static final String NAME = "Joe";
	public static final long ID = 1L;
	public static final String LAST_NAME = "Allias";
	
	CustomerMapper customerMapper = CustomerMapper.INSTANCE;


	@Test
	public void customerTocustomerDTO() throws Exception {

		//given
		Customer customer = new Customer();
		customer.setFirstName(NAME);
		customer.setLastName(LAST_NAME);
		customer.setId(ID);

		//when
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

		//then
		assertEquals(Long.valueOf(ID), customerDTO.getId());
		assertEquals(NAME, customerDTO.getFirstName());
		assertEquals(LAST_NAME, customerDTO.getLastName());

	}

}


