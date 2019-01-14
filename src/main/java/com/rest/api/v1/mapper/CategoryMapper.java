package com.rest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.rest.api.v1.model.CategoryDTO;
import com.rest.domain.Category;

@Mapper
public interface CategoryMapper {
	
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	@Mapping(source = "id", target = "id")
	CategoryDTO categoryToCategoryDTO(Category category);
}
