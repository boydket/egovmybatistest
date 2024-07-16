package egovframework.example.category.web;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.example.category.service.CategoryDto;
import egovframework.example.category.service.ICategoryService;
import egovframework.example.category.service.SearchCategoryDto;
import egovframework.example.sample.service.SampleDefaultVO;

@Controller
public class EgovCategoryController {

	@Resource(name = "categoryService")
	private ICategoryService<CategoryDto> categoryService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@RequestMapping(value = "/egovCategoryList.do")
	public String selectList(@ModelAttribute("searchVO") SearchCategoryDto searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CategoryDto> list = categoryService.findBySearch(searchVO);
		model.addAttribute("resultList", list);

		int totCnt = categoryService.countBySearch(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "category/egovCategoryList";
	}

	@RequestMapping(value = "/addCategory.do", method = RequestMethod.POST)
	public String addCategoryView(@ModelAttribute("searchVO") SearchCategoryDto searchVO, Model model) throws Exception {
		model.addAttribute("categoryDto", new CategoryDto());
		return "category/egovCategoryRegister";
	}
	
	@RequestMapping(value = "/insertCategory.do", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute("searchVO") SearchCategoryDto searchVO, CategoryDto categoryDto
			, BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {

		categoryService.insert(categoryDto);
		status.setComplete();
		return "forward:/egovCategoryList.do";
	}

	@RequestMapping("/updateCategoryView.do")
	public String updateCategoryView(@RequestParam("selectedId") Long id, @ModelAttribute("searchVO") SearchCategoryDto searchVO, Model model) throws Exception {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(id);
		model.addAttribute(selectCategory(categoryDto, searchVO));
		return "category/egovCategoryRegister";
	}

	public CategoryDto selectCategory(CategoryDto categoryDto, @ModelAttribute("searchVO") SearchCategoryDto searchVO) throws Exception {
		return categoryService.findById(categoryDto.getId());
	}

	@RequestMapping("/updateCategory.do")
	public String updateCategory(@ModelAttribute("searchVO") SearchCategoryDto searchVO, CategoryDto categoryDto
			, BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {
		categoryService.update(categoryDto.getId(), categoryDto);
		status.setComplete();
		return "forward:/egovCategoryList.do";
	}

	@RequestMapping("/deleteCategory.do")
	public String deleteCategory(CategoryDto categoryDto, @ModelAttribute("searchVO") SearchCategoryDto searchVO, SessionStatus status) throws Exception {
		categoryService.delete(categoryDto.getId());
		status.setComplete();
		return "forward:/egovCategoryList.do";
	}
}
