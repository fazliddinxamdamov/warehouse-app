package pdp.uz.service;

import org.springframework.web.multipart.MultipartFile;
import pdp.uz.entity.Product;
import pdp.uz.model.ProductAddDto;
import pdp.uz.model.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto get(Long id);

    List<ProductDto> getAll();

    List<ProductDto> getAllByCategory(Long categoryId);

    List<ProductDto> getAllByMeasurement(Long measurementId);

    ProductDto add(ProductAddDto dto);

    Product validate(Long id);
}
