package egovframework.example.category.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.category.service.CategoryDto;
import egovframework.example.category.service.SearchCategoryDto;

@Mapper("categoryMapper")
public interface CategoryMapper {
	void insert(CategoryDto dto) throws Exception;
	void update(CategoryDto dto) throws Exception;
	void delete(Long id) throws Exception;
	CategoryDto findById(Long id) throws Exception;
	
	List<CategoryDto> findBySearch(SearchCategoryDto search) throws Exception;
	int countBySearch(SearchCategoryDto search) throws Exception;
}
