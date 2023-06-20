package hakanozdabak.productData.webapi.controllers;

import hakanozdabak.productData.business.abstracts.ProductService;
import hakanozdabak.productData.business.requests.CreateProductRequest;
import hakanozdabak.productData.business.requests.UpdateProductRequest;
import hakanozdabak.productData.business.responses.GetAllProductsResponse;
import hakanozdabak.productData.business.responses.GetByCategoryProductsResponse;
import jakarta.validation.Valid;
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
    public void add(@RequestBody @Valid CreateProductRequest createProductRequest){
        this.productService.add(createProductRequest);
    }
    @PutMapping("/update")
    public void update(UpdateProductRequest updateProductRequest){
        this.productService.update(updateProductRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        this.productService.delete(id);
    }

    @GetMapping("/{category}")
    public List<GetByCategoryProductsResponse> getByCategory(@PathVariable String category){
        return productService.getByCategory(category);
    }

}
