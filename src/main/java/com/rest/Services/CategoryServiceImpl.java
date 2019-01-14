/**
 * 
 */
package com.rest.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rest.api.v1.mapper.CategoryMapper;
import com.rest.api.v1.model.CategoryDTO;
import com.rest.repositories.CategoryRepository;

/**
 * @author dedaloc2
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryMapper categoryMapper;
	private CategoryRepository categoryRepository;
	
	
	public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
		super();
		this.categoryMapper = categoryMapper;
		this.categoryRepository = categoryRepository;
	}

	/* (non-Javadoc)
	 * @see com.rest.Services.CategoryService#getAllCategories()
	 */
	@Override
	public List<CategoryDTO> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll()
									.stream()
									.map(categoryMapper::categoryToCategoryDTO)
									.collect(Collectors.toList());
	}

	/* (non-Javadoc)
	 * @see com.rest.Services.CategoryService#getCategoryByName(java.lang.String)
	 */
	@Override
	public CategoryDTO getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
	}

}
