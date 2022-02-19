package pdp.uz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.entity.Attachment;
import pdp.uz.entity.Category;
import pdp.uz.entity.Measurement;
import pdp.uz.entity.Product;
import pdp.uz.helpers.MapstructMapper;
import pdp.uz.helpers.Utils;
import pdp.uz.model.ProductAddDto;
import pdp.uz.model.ProductDto;
import pdp.uz.repository.ProductRepo;
import pdp.uz.service.AttachmentService;
import pdp.uz.service.CategoryService;
import pdp.uz.service.MeasurementService;
import pdp.uz.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final MapstructMapper mapstructMapper;
    private final CategoryService categoryService;
    private final MeasurementService measurementService;
    private final AttachmentService attachmentService;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, MapstructMapper mapstructMapper, CategoryService categoryService, MeasurementService measurementService, AttachmentService attachmentService) {
        this.productRepo = productRepo;
        this.mapstructMapper = mapstructMapper;
        this.categoryService = categoryService;
        this.measurementService = measurementService;
        this.attachmentService = attachmentService;
    }

    @Override
    public ProductDto get(Long id) {
        Product product = validate(id);
        return mapstructMapper.toProductDto(product);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> list = productRepo.findAll();
        return mapstructMapper.toProductDto(list);
    }

    @Override
    public List<ProductDto> getAllByCategory(Long categoryId) {
        List<Product> list = productRepo.findAllByCategoryId(categoryId);
        return mapstructMapper.toProductDto(list);
    }

    @Override
    public List<ProductDto> getAllByMeasurement(Long measurementId) {
        List<Product> list = productRepo.findAllByMeasurementId(measurementId);
        return mapstructMapper.toProductDto(list);
    }

    @Override
    public ProductDto add(ProductAddDto dto) {
        String name = dto.getName();
        if (Utils.isEmpty(name)) {
            throw new RuntimeException("Product name should not be null!");
        } else {
            Optional<Product> productOpt = productRepo.findByName(name);
            if (productOpt.isPresent()) {
                throw new RuntimeException("This name is already exist!");
            }
        }
        Category category = categoryService.validate(dto.getCategoryId());
        Measurement measurement = measurementService.validate(dto.getMeasurementId());
        Attachment attachment = attachmentService.validate(dto.getAttachmentId());

        Product product = mapstructMapper.toProduct(dto);
        product.setCategory(category);
        product.setMeasurement(measurement);
        product.setAttachment(attachment);
        product.setCode(Utils.generateCode());
        product.setActive(true);

        Product savedProduct = productRepo.save(product);

        return mapstructMapper.toProductDto(savedProduct);
    }

    @Override
    public Product validate(Long id) {
        Optional<Product> productOpt = productRepo.findById(id);
        if (!productOpt.isPresent()) {
            throw new RuntimeException("Product id = " + id + ", not found!");
        }
        Product product = productOpt.get();
        if (!product.getActive()) {
            throw new RuntimeException("Product id = " + id + ", is inactive!");
        }
        return product;
    }
}
