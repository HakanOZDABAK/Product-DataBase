package hakanozdabak.productData.webapi.controllers;

import hakanozdabak.productData.business.abstracts.ProductService;
import hakanozdabak.productData.business.requests.CreateProductRequest;
import hakanozdabak.productData.business.requests.UpdateProductRequest;
import hakanozdabak.productData.business.responses.GetAllProductsResponse;
import hakanozdabak.productData.business.responses.GetByCategoryProductsResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductsController {
    private ProductService productService;

    @GetMapping("/getall")
    public ResponseEntity<List<GetAllProductsResponse>> getAll(){
        return ResponseEntity.ok(this.productService.getAll());


    }
    @PostMapping("/add")
    public void add(@RequestBody @Valid CreateProductRequest createProductRequest){
        this.productService.add(createProductRequest);

    }
    @PutMapping("/update")
    public void update(UpdateProductRequest updateProductRequest){
        ResponseEntity.ok();
        this.productService.update(updateProductRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        ResponseEntity.ok();
        this.productService.delete(id);
    }

    @GetMapping("/{category}")
    public List<GetByCategoryProductsResponse> getByCategory(@PathVariable String category){
        ResponseEntity.ok();
        return productService.getByCategory(category);
    }

}
