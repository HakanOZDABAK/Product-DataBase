package hakanozdabak.productData.webapi.controllers;

import hakanozdabak.productData.business.abstracts.ProductService;
import hakanozdabak.productData.business.requests.CreateProductRequest;
import hakanozdabak.productData.business.requests.UpdateProductRequest;
import hakanozdabak.productData.business.responses.GetAllProductsResponse;
import hakanozdabak.productData.business.responses.GetByCategoryProductsResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
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
    public void update(@RequestBody UpdateProductRequest updateProductRequest){
        ResponseEntity.ok();
        this.productService.update(updateProductRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        this.productService.delete(id);

    }

    @GetMapping("/{category}")
    public ResponseEntity<List<GetByCategoryProductsResponse>> getByCategory(@PathVariable String category){

        return ResponseEntity.ok(productService.getByCategory(category));
    }

}
