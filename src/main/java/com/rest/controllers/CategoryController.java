package com.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Services.CategoryService;
import com.rest.api.v1.model.CategoryDTO;
import com.rest.api.v1.model.CategoryListDTO;

@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	 
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CategoryListDTO getAllCategories(){
		return new CategoryListDTO(categoryService.getAllCategories());
	}
	
	@GetMapping("{name}")	
	@ResponseStatus(HttpStatus.OK)
	public CategoryDTO getCategoryByName(@PathVariable String name){
		return categoryService.getCategoryByName(name);
	}


}
