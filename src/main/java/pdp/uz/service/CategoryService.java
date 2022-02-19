package pdp.uz.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import pdp.uz.entity.Category;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.CategoryAddDto;
import pdp.uz.model.CategoryDto;

import java.util.List;

public interface CategoryService {

    ResponseEntity<ApiResponse<List<CategoryDto>>> getAll(Pageable pageable);

    ResponseEntity<ApiResponse<List<CategoryDto>>> getParents();

    ResponseEntity<ApiResponse<List<CategoryDto>>> getChildren(Long id);

    ResponseEntity<ApiResponse<CategoryDto>> add(CategoryAddDto dto);

    Category validate(Long id);
}
