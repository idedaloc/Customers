package com.rest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rest.domain.Category;
import com.rest.domain.Customer;
import com.rest.repositories.CategoryRepository;
import com.rest.repositories.CustomerRepository;

@Component
public class Bootstrap implements CommandLineRunner{
	
	private CategoryRepository categoryRepository;
	private CustomerRepository customerRepository;

	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		
		 Category fruits = new Category();
	        fruits.setName("Fruits");

	        Category dried = new Category();
	        dried.setName("Dried");

	        Category fresh = new Category();
	        fresh.setName("Fresh");

	        Category exotic = new Category();
	        exotic.setName("Exotic");

	        Category nuts = new Category();
	        nuts.setName("Nuts");

	        Customer customer = new Customer();
	        customer.setFirstName("L");
	        customer.setLastName("L");
	        
	        customerRepository.save(customer);
	        
	        categoryRepository.save(fruits);
	        categoryRepository.save(dried);
	        categoryRepository.save(fresh);
	        categoryRepository.save(exotic);
	        categoryRepository.save(nuts);


	        System.out.println("Data Loaded = " + categoryRepository.count() );

	}
	
	

}
