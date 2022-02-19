package pdp.uz.helpers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import pdp.uz.entity.*;
import pdp.uz.model.*;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapstructMapper {

    Warehouse toWarehouse(WarehouseAddDto dto);

    WarehouseDto toWarehouseDto(Warehouse warehouse);

    List<WarehouseDto> toWarehouseDto(List<Warehouse> warehouses);

    User toUser(UserAddDto dto);

    UserDto toUserDto(User user);

    Category toCategory(CategoryAddDto dto);

    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDto(List<Category> categories);

    Measurement toMeasurement(MeasurementAddDto dto);

    MeasurementDto toMeasurementDto(Measurement measurement);

    List<MeasurementDto> toMeasurementDto(List<Measurement> measurements);

    Product toProduct(ProductAddDto dto);

    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDto(List<Product> products);

    AttachmentDto toAttachmentDto(Attachment attachment);

    Currency toCurrency(CurrencyAddDto dto);

    CurrencyDto toCurrencyDto(Currency currency);

    List<CurrencyDto> toCurrencyDto(List<Currency> currencies);

    Supplier toSupplier(SupplierAddDto dto);

    SupplierDto toSupplierDto(Supplier supplier);

    List<SupplierDto> toSupplierDto(List<Supplier> suppliers);

    @Mapping(source = "currency.name", target = "currency")
    InputDto toInputDto(Input input);

    List<InputDto> toInputDto(List<Input> inputs);

    @Mapping(target = "productCode", source = "product.code")
    @Mapping(target = "product", source = "product.name")
    @Mapping(target = "measurement", source = "product.measurement.name")
    @Mapping(target = "category", source = "product.category.name")
    @Mapping(target = "inputId", source = "input.id")
    @Mapping(target = "date", source = "input.date")
    InputProductDto toInputProductDto(InputProduct inputProduct);

    List<InputProductDto> toInputProductDto(List<InputProduct> inputProducts);

    Client toClient(ClientAddDto dto);

    ClientDto toClientDto(Client savedClient);

    @Mapping(target = "currency", source = "currency.name")
    OutputDto toOutputDto(Output output);

    List<OutputDto> toOutputDto(List<Output> outputs);

    @Mapping(target = "product", source = "product.name")
    @Mapping(target = "measurement", source = "product.measurement.name")
    @Mapping(target = "category", source = "product.category.name")
    @Mapping(target = "outputId", source = "output.id")
    @Mapping(target = "date", source = "output.date")
    OutputProductDto toOutputProductDto(OutputProduct outputProduct);

    List<OutputProductDto> toOutputProductDto(List<OutputProduct> outputProducts);
}
