package hakanozdabak.productData.business.abstracts;

import hakanozdabak.productData.business.requests.CreateProductRequest;
import hakanozdabak.productData.business.requests.UpdateProductRequest;
import hakanozdabak.productData.business.responses.GetAllProductsResponse;

import java.util.List;

public interface ProductService {

    List<GetAllProductsResponse> getAll();
    void add(CreateProductRequest createProductRequest);
    void update(UpdateProductRequest updateProductRequest);
    void delete(int id);
}
