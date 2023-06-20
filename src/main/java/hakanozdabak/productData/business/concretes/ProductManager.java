package hakanozdabak.productData.business.concretes;

import hakanozdabak.productData.business.abstracts.ProductService;
import hakanozdabak.productData.business.requests.CreateProductRequest;
import hakanozdabak.productData.business.requests.UpdateProductRequest;
import hakanozdabak.productData.business.responses.GetAllProductsResponse;
import hakanozdabak.productData.business.responses.GetByCategoryProductsResponse;
import hakanozdabak.productData.business.rules.ProductBusinessRules;
import hakanozdabak.productData.dataAccess.abstracts.ProductRepository;
import hakanozdabak.productData.entities.concretes.Product;
import hakanozdabak.productData.core.utilities.mappers.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {

    private ProductRepository productRepository;
    private ProductBusinessRules productBusinessRules;
    private ModelMapperService modelMapperService;
    @Override
    public List<GetAllProductsResponse> getAll() {
        List<Product> products = productRepository.findAll();
        List<GetAllProductsResponse> productsResponse= products.stream()
                .map(product -> this.modelMapperService.forResponse().map(product, GetAllProductsResponse.class)).collect(Collectors.toList());
        return productsResponse;
    }

    @Override
    public void add(CreateProductRequest createProductRequest) {
        this.productBusinessRules.checkIfProductNameExists(createProductRequest.getName());
        Product product = this.modelMapperService.forRequest().map(createProductRequest,Product.class);
        this.productRepository.save(product);
    }

    @Override
    public void update(UpdateProductRequest updateProductRequest) {
        Product product = this.modelMapperService.forRequest().map(updateProductRequest,Product.class);
        this.productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> getById(int id) {
        Optional<Product> product = this.productRepository.findById(id);
        return product;
    }

    @Override
    public List<GetByCategoryProductsResponse> getByCategory(String category) {
        List<Product> products = this.productRepository.findByCategory(category);
        List<GetByCategoryProductsResponse> categoryProductsResponses= products.stream()
                .map(product -> this.modelMapperService.forResponse().map(product,GetByCategoryProductsResponse.class)).collect(Collectors.toList());
        return categoryProductsResponses;
    }


}
