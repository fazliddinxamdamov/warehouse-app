package pdp.uz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pdp.uz.entity.Input;
import pdp.uz.entity.InputProduct;
import pdp.uz.entity.Product;
import pdp.uz.helpers.MapstructMapper;
import pdp.uz.helpers.Utils;
import pdp.uz.model.ApiResponse;
import pdp.uz.model.InputProductAddDto;
import pdp.uz.model.InputProductDto;
import pdp.uz.model.resp.ProductReport;
import pdp.uz.repository.InputProductRepo;
import pdp.uz.repository.InputRepo;
import pdp.uz.service.InputProductService;
import pdp.uz.service.ProductService;
import static pdp.uz.model.ApiResponse.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InputProductServiceImpl implements InputProductService {
    private final InputProductRepo inputProductRepo;
    private final MapstructMapper mapper;
    private final ProductService productService;
    private final InputRepo inputRepo;


    @Autowired
    public InputProductServiceImpl(InputProductRepo inputProductRepo, MapstructMapper mapper, ProductService productService, InputRepo inputRepo) {
        this.inputProductRepo = inputProductRepo;
        this.mapper = mapper;
        this.productService = productService;
        this.inputRepo = inputRepo;
    }

    @Override
    public ResponseEntity<ApiResponse<InputProductDto>> add(InputProductAddDto dto) {
        return response(mapper.toInputProductDto(addOne(dto)));
    }

    @Override
    public ResponseEntity<ApiResponse<List<InputProductDto>>> addAll(List<InputProductAddDto> dto) {
        List<InputProduct> inputProducts = new ArrayList<>();
        dto.forEach(inputProductAddDto -> inputProducts.add(addOne(inputProductAddDto)));

        List<InputProduct> inputProductList = inputProductRepo.saveAll(inputProducts);
        return response(mapper.toInputProductDto(inputProductList), HttpStatus.CREATED);
    }

    public InputProduct addOne(InputProductAddDto dto) {
        Long inputId = dto.getInputId();
        if (Utils.isEmpty(inputId)) {
            throw new RuntimeException("Input id should not be null!");
        }
        Long productId = dto.getProductId();
        if (Utils.isEmpty(productId)) {
            throw new RuntimeException("Product id should not be null!");
        }
        Double amount = dto.getAmount();
        if (Utils.isEmpty(amount)) {
            throw new RuntimeException("Amount should not be null!");
        }
        Double price = dto.getPrice();
        if (Utils.isEmpty(price)) {
            throw new RuntimeException("Price should not be null!");
        }
        Optional<Input> optionalInput = inputRepo.findById(inputId);
        if (!optionalInput.isPresent()) {
            throw new RuntimeException("Input id = " + inputId + " not found!");
        }

        Product product = productService.validate(productId);
        Input input = optionalInput.get();

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(product);
        inputProduct.setInput(input);
        inputProduct.setAmount(amount);
        inputProduct.setPrice(price);
        inputProduct.setExpireDate(dto.getExpireDate());
        return inputProductRepo.save(inputProduct);
    }

    @Override
    public ResponseEntity<ApiResponse<List<ProductReport>>> get() {
        LocalDate today = LocalDate.now();
        return response(inputProductRepo.findAllByDate(today));
    }

    @Override
    public ResponseEntity<ApiResponse<List<ProductReport>>> get(String date) {
        try {
            LocalDate parse = LocalDate.parse(date);
            return response(inputProductRepo.findAllByDate(parse));
        } catch (Exception e) {
            throw new RuntimeException("Wrong date format is included (User yyyy-mm-dd");
        }
    }
}
