package com.rest.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rest.Services.CustomerService;
import com.rest.api.v1.model.CustomerDTO;

public class CustomerControllerTest {

	 public static final String NAME = "Jim";

	private static final String LAST_NAME = "Asf";

	    @Mock
	    CustomerService customerService;

	    @InjectMocks
	    CustomerController customerController;

	    MockMvc mockMvc;

	    @Before
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);

	        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

	    }

	    @Test
	    public void testListCustomers() throws Exception {
	        CustomerDTO customer1 = new CustomerDTO();
	        customer1.setId(1l);
	        customer1.setFirstName(NAME);

	        CustomerDTO customer2 = new CustomerDTO();
	        customer2.setId(2l);
	        customer2.setFirstName("Bob");

	        List<CustomerDTO> categories = Arrays.asList(customer1, customer2);

	        when(customerService.getAllCustomer()).thenReturn(categories);

	        mockMvc.perform(get("/api/v1/customer/")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.customers", hasSize(2)));
	    }

	    @Test
	    public void testGetByIdCustomers() throws Exception {
	        CustomerDTO customer1 = new CustomerDTO();
	        customer1.setId(1l);
	        customer1.setFirstName(NAME);
	        customer1.setLastName(LAST_NAME);

	        when(customerService.getCostumerById(anyLong())).thenReturn(customer1);

	        mockMvc.perform(get("/api/v1/customer/1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                ;
	                //.andExpect(jsonPath("$.firstName", equalTo(NAME)));
	}

}
