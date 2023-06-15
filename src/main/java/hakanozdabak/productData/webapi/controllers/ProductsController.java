package hakanozdabak.productData.webapi.controllers;

import hakanozdabak.productData.business.abstracts.ProductService;
import hakanozdabak.productData.business.requests.CreateProductRequest;
import hakanozdabak.productData.business.requests.UpdateProductRequest;
import hakanozdabak.productData.business.responses.GetAllProductsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductsController {
    private ProductService productService;

    @GetMapping("/getall")
    public List<GetAllProductsResponse> getAll(){
        return productService.getAll();

    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(CreateProductRequest createProductRequest){
        this.productService.add(createProductRequest);
    }
    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(UpdateProductRequest updateProductRequest){
        this.productService.update(updateProductRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(int id){
        this.productService.delete(id);
    }

}
