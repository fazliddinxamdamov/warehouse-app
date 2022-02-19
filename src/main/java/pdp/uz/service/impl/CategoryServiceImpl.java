package pdp.uz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pdp.uz.entity.Category;
import pdp.uz.helpers.MapstructMapper;
import pdp.uz.helpers.Utils;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.CategoryAddDto;
import pdp.uz.model.CategoryDto;
import pdp.uz.repository.CategoryRepo;
import pdp.uz.service.CategoryService;

import java.util.List;
import java.util.Optional;

import static pdp.uz.model.ApiResponse.response;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final MapstructMapper mapstructMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, MapstructMapper mapstructMapper) {
        this.categoryRepo = categoryRepo;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAll(Pageable pageable) {
        Page<Category> page = categoryRepo.findAll(pageable);
        List<Category> list = page.getContent();
        if (list.isEmpty()) {
            return response(HttpStatus.NOT_FOUND);
        }
        List<CategoryDto> resultList = mapstructMapper.toCategoryDto(list);
        Long totalCount = categoryRepo.count();
        return response(resultList, totalCount);
    }

    @Override
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getParents() {
        List<Category> parents = categoryRepo.findAllParentCategories();
        if (parents.isEmpty()) {
            return response(HttpStatus.NOT_FOUND);
        }
        return response(mapstructMapper.toCategoryDto(parents));
    }

    @Override
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getChildren(Long id) {
        Category parentCategory = validate(id);
        List<Category> children = categoryRepo.findAllChildren(parentCategory);
        if (children.isEmpty()) {
            return response("Category children not found!", HttpStatus.NOT_FOUND);
        }
        return response(mapstructMapper.toCategoryDto(children));
    }

    @Override
    public ResponseEntity<ApiResponse<CategoryDto>> add(CategoryAddDto dto) {
        String name = dto.getName();
        if (Utils.isEmpty(name)) {
            return response("Category name should not be null!", HttpStatus.BAD_REQUEST);
        } else {
            Optional<Category> categoryOpt = categoryRepo.findByName(name);
            if (categoryOpt.isPresent()) {
                return response("This name is already exist!", HttpStatus.FORBIDDEN);
            }
        }

        Long parentCategoryId = dto.getParentCategoryId();
        Category parentCategory = null;

        if (!Utils.isEmpty(parentCategoryId)) {
            Optional<Category> parentCategoryOpt = categoryRepo.findById(parentCategoryId);
            if (!parentCategoryOpt.isPresent()) {
                return response("Parent Category id = " + parentCategoryId + ", not found!", HttpStatus.NOT_FOUND);
            }
            parentCategory = parentCategoryOpt.get();
            if (!parentCategory.getActive()) {
                return response("Parent Category id = " + parentCategoryId + ", is inactive!", HttpStatus.FORBIDDEN);
            }
        }

        Category category = mapstructMapper.toCategory(dto);
        category.setParentCategory(parentCategory);

        Category savedCategory = categoryRepo.save(category);

        CategoryDto categoryDto = mapstructMapper.toCategoryDto(savedCategory);

        return response(categoryDto, HttpStatus.CREATED);
    }

    @Override
    public Category validate(Long id) {
        Optional<Category> categoryOpt = categoryRepo.findById(id);
        if (!categoryOpt.isPresent()) {
            throw new RuntimeException("Category id = " + id + ", not found!");
        }
        Category category = categoryOpt.get();
        if (!category.getActive()) {
            throw new RuntimeException("Category id = " + id + ", is inactive!");
        }
        return category;
    }
}
