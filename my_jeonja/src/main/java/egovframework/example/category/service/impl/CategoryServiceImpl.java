package egovframework.example.category.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.example.category.service.CategoryDto;
import egovframework.example.category.service.ICategoryService;
import egovframework.example.category.service.SearchCategoryDto;

@Service("categoryService")
public class CategoryServiceImpl extends EgovAbstractServiceImpl implements ICategoryService<CategoryDto> {

	@Resource(name="categoryMapper")
	private CategoryMapper categoryMapper;
	
	@Override
	public CategoryDto findById(Long id) throws Exception {
		CategoryDto find = this.categoryMapper.findById(id);
		return find;
	}

	@Override
	public List<CategoryDto> getAllList() throws Exception {
		return List.of();
		// List<CategoryDto> list = this.categoryMapper.getAllList();
		// List<ICategory> result = list.stream().map(x -> (ICategory)x).toList();
		// return result;
	}

	@Override
	public CategoryDto insert(CategoryDto entity) throws Exception {
		CategoryDto dto = CategoryDto.builder().name(entity.getName()).build();
		this.categoryMapper.insert(dto);
		return dto;
	}

	@Override
	public Boolean delete(Long id) throws Exception {
		this.categoryMapper.delete(id);
		return true;
	}

	@Override
	public CategoryDto update(Long id, CategoryDto entity) throws Exception {
		CategoryDto dto = CategoryDto.builder().id(id).name(entity.getName()).build();
		this.categoryMapper.update(dto);
		return dto;
	}

	@Override
	public CategoryDto findByName(String name) throws Exception {
		return null;
		// CategoryDto find = this.categoryMapper.findByName(name);
		// return find;
	}

	@Override
	public List<CategoryDto> findAllByNameContains(String name) throws Exception {
		SearchCategoryDto search = (SearchCategoryDto) SearchCategoryDto.builder().name(name).build();
		search.setSearchCondition(0);
		List<CategoryDto> list = this.categoryMapper.findBySearch(search);
		return list;
	}

	@Override
	public List<CategoryDto> findBySearch(SearchCategoryDto searchDto) throws Exception {
		return this.categoryMapper.findBySearch(searchDto);
	}

	@Override
	public int countBySearch(SearchCategoryDto searchDto) throws Exception {
		return this.categoryMapper.countBySearch(searchDto);
	}
}
