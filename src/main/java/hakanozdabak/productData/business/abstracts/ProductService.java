package hakanozdabak.productData.business.abstracts;

import hakanozdabak.productData.business.requests.CreateProductRequest;
import hakanozdabak.productData.business.requests.UpdateProductRequest;
import hakanozdabak.productData.business.responses.GetAllProductsResponse;
import hakanozdabak.productData.business.responses.GetByCategoryProductsResponse;
import hakanozdabak.productData.entities.concretes.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<GetAllProductsResponse> getAll();
    void add(CreateProductRequest createProductRequest);
    void update(UpdateProductRequest updateProductRequest);
    void delete(int id);
    Optional<Product> getById(int id);
    List<GetByCategoryProductsResponse> getByCategory(String category);
}
