package com.rest.Services;

import java.util.List;

import com.rest.api.v1.model.CategoryDTO;

public interface CategoryService {
	List<CategoryDTO> getAllCategories();
	CategoryDTO getCategoryByName(String name);
}
