package egovframework.example.category.service;

import java.util.List;


public interface ICategoryService<T> {
	T findById(Long id) throws Exception;
    T findByName(String name) throws Exception;
    List<T> getAllList() throws Exception;
    T insert(T category) throws Exception;
    Boolean delete(Long id) throws Exception;
    T update(Long id, T category) throws Exception;
    List<T> findAllByNameContains(String name) throws Exception;
    
    List<T> findBySearch(SearchCategoryDto searchDto) throws Exception;
    int countBySearch(SearchCategoryDto searchDto) throws Exception;
}