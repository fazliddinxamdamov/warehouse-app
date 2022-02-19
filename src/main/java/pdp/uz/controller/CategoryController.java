package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.CategoryAddDto;
import pdp.uz.model.CategoryDto;
import pdp.uz.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // ADMIN, USER, GUEST
    @PreAuthorize("hasAuthority('GET_ALL_CATEGORY')")
    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                                 @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryService.getAll(pageable);
    }

    // ADMIN, USER
    @PreAuthorize("hasAuthority('PARENT_CATEGORY')")
    @GetMapping("/parents")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getParents() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return categoryService.getParents();
    }

    // ADMIN, USER
    @PreAuthorize("hasAuthority('CHILDREN_CATEGORY')")
    @GetMapping("/{id}/children")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getChildren(@PathVariable(value = "id") Long id) {
        return categoryService.getChildren(id);
    }

    // ADMIN
    @PreAuthorize("hasAuthority('ADD_CATEGORY')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CategoryDto>> add(@Valid @RequestBody CategoryAddDto dto) {
        return categoryService.add(dto);
    }
}
