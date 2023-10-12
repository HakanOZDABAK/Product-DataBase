package hakanozdabak.productData.service;

import hakanozdabak.productData.business.concretes.ProductManager;
import hakanozdabak.productData.business.requests.UpdateProductRequest;
import hakanozdabak.productData.business.responses.GetAllProductsResponse;
import hakanozdabak.productData.business.responses.GetByCategoryProductsResponse;
import hakanozdabak.productData.business.rules.ProductBusinessRules;
import hakanozdabak.productData.core.utilities.mappers.ModelMapperManager;
import hakanozdabak.productData.dataAccess.abstracts.ProductRepository;
import hakanozdabak.productData.entities.concretes.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductManagerTest {


    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductBusinessRules productBusinessRules;

    @Mock
    private ModelMapperManager modelMapperManager;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductManager productManager;
    @Test
    @DisplayName("Should Find Post By Id")
    void shouldFindPostById() {
        Product product = new Product(123,"Milk","Drink",10,true,5);
        GetAllProductsResponse expectedProductResponse = new GetAllProductsResponse(123,"Milk","Drink",10,true,5);
        Mockito.when(productRepository.findById(123)).thenReturn(Optional.of(product));

        Optional<Product> actualPostResponse = productManager.getById(123);

        assertThat(actualPostResponse.get().getId()).isEqualTo(expectedProductResponse.getId());
        assertThat(actualPostResponse.get().getName()).isEqualTo(expectedProductResponse.getName());
        System.out.println("Test was successful");
    }

    @Test
    @DisplayName("Should Get By Category")
    void shouldGetByCategory() {
        Product product = Product.builder()
                .name("Milk")
                .category("Drink")
                .price(10)
                .quantity(10)
                .onOffer(true)
                .build();
        when(productRepository.findByCategory(Mockito.any(String.class))).thenReturn(List.of(product));
        List<GetByCategoryProductsResponse> getByCategoryProductsResponsesLists = productManager.getByCategory(product.getCategory());
assertThat(getByCategoryProductsResponsesLists).isNotNull();
assertThat(getByCategoryProductsResponsesLists.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should Update")
    void shouldUpdate() {
        Product product = Product.builder()
                .id(0)
                .name("Milk")
                .category("Drink")
                .price(10)
                .quantity(10)
                .onOffer(true)
                .build();
        UpdateProductRequest updateProductRequest = UpdateProductRequest.builder()
                .id(0)
                .name("Coke")
                .category("Drink")
                .price(10)
                .quantity(10)
                .onOffer(true)
                .build();

        when(modelMapperManager.forRequest()).thenReturn(modelMapper);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        productRepository.save(product);
        UpdateProductRequest updateProductRequest1 = productManager.update(updateProductRequest);
        assertThat(updateProductRequest1).isNotNull();
        assertThat(updateProductRequest1.getName()).isEqualTo("Coke");
    }




}
