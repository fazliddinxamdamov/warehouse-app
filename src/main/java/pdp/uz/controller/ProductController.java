package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.ProductAddDto;
import pdp.uz.model.ProductDto;
import pdp.uz.service.ProductService;

import java.util.List;

import static pdp.uz.model.ApiResponse.response;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> get(@PathVariable(value = "id") Long id) {
        ProductDto productDto = productService.get(id);
        return response(productDto);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAll() {
        return response(productService.getAll());
    }

    @GetMapping("/get/category/{id}/products")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllByCategory(@PathVariable(value = "id") Long categoryId) {
        List<ProductDto> list = productService.getAllByCategory(categoryId);
        return response(list);
    }

    @GetMapping("/get/measurement/{id}/products")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllByMeasurement(@PathVariable(value = "id") Long measurementId) {
        List<ProductDto> list = productService.getAllByMeasurement(measurementId);
        return response(list);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ProductDto>> add(@RequestBody ProductAddDto dto) {
        ProductDto productDto = productService.add(dto);
        return response(productDto, HttpStatus.CREATED);
    }
}
