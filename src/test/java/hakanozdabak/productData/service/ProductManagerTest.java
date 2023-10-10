package hakanozdabak.productData.service;

import hakanozdabak.productData.business.concretes.ProductManager;
import hakanozdabak.productData.business.requests.UpdateProductRequest;
import hakanozdabak.productData.business.responses.GetAllProductsResponse;
import hakanozdabak.productData.business.rules.ProductBusinessRules;
import hakanozdabak.productData.core.utilities.mappers.ModelMapperManager;
import hakanozdabak.productData.core.utilities.mappers.ModelMapperService;
import hakanozdabak.productData.dataAccess.abstracts.ProductRepository;
import hakanozdabak.productData.entities.concretes.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

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
    @DisplayName("Should Update By If")
    void shouldUpdateById() {
        Product product = Product.builder()
                .name("Milk")
                .category("Drink")
                .price(10)
                .quantity(10)
                .onOffer(true)
                .build();

        when(productRepository.findById(0)).thenReturn(Optional.ofNullable(product));
        Optional<Product> expectedProduct = productManager.getById(0);
        assertThat(expectedProduct.get().getName()).isEqualTo(product.getName());

    }


}
