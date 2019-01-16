package com.rest.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.rest.Services.ResourceNotFoundException;
import com.rest.api.v1.model.CustomerDTO;





public class CustomerControllerTest extends AbstractRestControllerTest{

	 public static final String Name = "Jim";

	private static final String LAST_Name = "Asf";

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
	        customer1.setFirstName(Name);

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
	        customer1.setFirstName(Name);
	        customer1.setLastName(LAST_Name);

	        when(customerService.getCostumerById(anyLong())).thenReturn(customer1);

	        mockMvc.perform(get("/api/v1/customer/1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                ;
	                //.andExpect(jsonPath("$.firstName", equalTo(Name)));
	}
	    
	    @Test
	    public void createNewCustomer() throws Exception {
	        //given
	        CustomerDTO customer = new CustomerDTO();
	        customer.setFirstName("Fred");
	        customer.setLastName("Flintstone");

	        CustomerDTO returnDTO = new CustomerDTO();
	        returnDTO.setFirstName(customer.getFirstName());
	        returnDTO.setLastName(customer.getFirstName());
	        returnDTO.setUrl("/api/v1/customers/1");

	        when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);

	        //when/then
	        mockMvc.perform(post("/api/v1/customer/")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString(customer)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.firstName", equalTo("Fred")))
	                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
	}	    	

	    @Test
	    public void testUpdateCustomer() throws Exception {
	        //given
	        CustomerDTO customer = new CustomerDTO();
	        customer.setFirstName("Fred");
	        customer.setLastName("Flintstone");

	        CustomerDTO returnDTO = new CustomerDTO();
	        returnDTO.setFirstName(customer.getFirstName());
	        returnDTO.setLastName(customer.getLastName());
	        returnDTO.setUrl("/api/v1/customers/1");
	        
	        when(customerService.updateNewCustomer(anyLong(),any(CustomerDTO.class))).thenReturn(returnDTO);
	        
	        //when/then
	        mockMvc.perform(put("/api/v1/customer/1")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(asJsonString(customer)))
	        		.andExpect(status().isOk())
	        		.andExpect(jsonPath("$.firstName",equalTo("Fred")))
	        		.andExpect(jsonPath("$.lastName",equalTo("Flintstone")))
	        		.andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));	        		
	     
	        
	    }
	    
	    @Test
	    public void testPatchCustomer() throws Exception {

	        //given
	        CustomerDTO customer = new CustomerDTO();
	        customer.setFirstName("Fred");

	        CustomerDTO returnDTO = new CustomerDTO();
	        returnDTO.setFirstName(customer.getFirstName());
	        returnDTO.setLastName("Flintstone");
	        returnDTO.setUrl("/api/v1/customers/1");

	        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

	        mockMvc.perform(patch("/api/v1/customer/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(customer)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.firstName", equalTo("Fred")))
	                .andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
	                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
	}
	    
	    @Test
	    public void testDeleteCustomer() throws Exception {

	        mockMvc.perform(delete("/api/v1/customer/1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());

	        verify(customerService).deleteCustomerById(anyLong());
	}
	    
	    @Test
	    public void testGetCustomerByIdNotFound() throws Exception {

	        when(customerService.getCostumerById(anyLong())).thenThrow(ResourceNotFoundException.class);

	        mockMvc.perform(get("api/v1/categories/Foo")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isNotFound());
	}

}
