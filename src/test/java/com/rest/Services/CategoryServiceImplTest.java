/**
 * 
 */
package com.rest.Services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rest.api.v1.mapper.CategoryMapper;
import com.rest.api.v1.model.CategoryDTO;
import com.rest.domain.Category;
import com.rest.repositories.CategoryRepository;
import com.rest.repositories.CustomerRepository;

/**
 * @author dedaloc2
 *
 */
public class CategoryServiceImplTest {


	public static final Long ID = 2L;
	public static final String NAME = "Jimmy";
	CategoryService categoryService;

	@Mock
	CategoryRepository categoryRepository;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		   MockitoAnnotations.initMocks(this);

		   categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
	}

	   @Test
	    public void getAllCategories() throws Exception {

	        //given
	        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

	        when(categoryRepository.findAll()).thenReturn(categories);

	        //when
	        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

	        //then
	        assertEquals(3, categoryDTOS.size());

	    }

	    @Test
	    public void getCategoryByName() throws Exception {

	        //given
	        Category category = new Category();
	        category.setId(ID);
	        category.setName(NAME);

	        when(categoryRepository.findByName(anyString())).thenReturn(category);

	        //when
	        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

	        //then
	        assertEquals(ID, categoryDTO.getId());
	        assertEquals(NAME, categoryDTO.getName());

	}
	    	    	    
}
