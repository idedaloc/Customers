/**
 * 
 */
package com.rest.Services;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rest.api.v1.mapper.CustomerMapper;
import com.rest.api.v1.model.CustomerDTO;
import com.rest.domain.Customer;
import com.rest.repositories.CustomerRepository;

/**
 * @author dedaloc2
 *
 */
public class CustomerServiceImplTest {


	public static final Long ID = 2L;
	public static final String NAME = "Jimmy";
	private static final String LAST_NAME = "Arg";
	
	CustomerService customerService;

	@Mock
	CustomerRepository customerRepository;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		   MockitoAnnotations.initMocks(this);

		   customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
	}

	   @Test
	    public void getAllCategories() throws Exception {

	        //given
	        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

	        when(customerRepository.findAll()).thenReturn(customers);

	        //when
	        List<CustomerDTO> customerDTOS = customerService.getAllCustomer();

	        //then
	        assertEquals(3, customerDTOS.size());

	    }

	    @Test
	    public void getCustomerByName() throws Exception {

	        //given
	        Optional<Customer> customer = Optional.of(new Customer());
	        customer.get().setId(ID);
	        customer.get().setFirstName(NAME);
	        customer.get().setLastName(LAST_NAME);

	        when(customerRepository.findById(anyLong())).thenReturn(customer);

	        //when
	        CustomerDTO customerDTO = customerService.getCostumerById(ID);

	        //then
	        assertEquals(ID, customerDTO.getId());
	        assertEquals(NAME, customerDTO.getFirstName());
	        assertEquals(LAST_NAME, customerDTO.getLastName());

	}
	    
	    @Test
	    public void createNewCustomer() throws Exception {

	        //given
	        CustomerDTO customerDTO = new CustomerDTO();
	        customerDTO.setFirstName("Jim");

	        Customer savedCustomer = new Customer();
	        savedCustomer.setFirstName(customerDTO.getFirstName());
	        savedCustomer.setFirstName(customerDTO.getFirstName());
	        savedCustomer.setId(1l);

	        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

	        //when
	        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

	        //then
	        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
	        assertEquals("/api/v1/customer/1", savedDto.getUrl());
	}
	    
	    @Test
	    public void updatedCustomer() throws Exception{
	    	
	    	//given
	    	CustomerDTO customerDTO = new CustomerDTO();
	        customerDTO.setFirstName("Jim");

	        Customer savedCustomer = new Customer();
	        savedCustomer.setFirstName(customerDTO.getFirstName());
	        savedCustomer.setLastName(customerDTO.getLastName());
	        savedCustomer.setId(1l);
	        
	        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
	        
	        //when
	        CustomerDTO savedCustomerDTO = customerService.updateNewCustomer(1l,customerDTO);	
	        
	        //then	        
	        assertEquals(customerDTO.getFirstName(), savedCustomerDTO.getFirstName());
	        assertEquals("/api/v1/customer/1", savedCustomerDTO.getUrl());	        
	        
	    }
	    
	    @Test
	    public void deleteCustomerById() throws Exception {
	    	Long id = 1L;
	    	
	    	customerService.deleteCustomerById(id);
	    	
	    	verify(customerRepository, times(1)).deleteById(anyLong());
	    }
	
}
