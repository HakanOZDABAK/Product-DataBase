package hakanozdabak.productData.repository;

import hakanozdabak.productData.business.jwt.AuthenticationService;
import hakanozdabak.productData.business.requests.AuthenticationRequest;
import hakanozdabak.productData.business.requests.RegisterRequest;
import hakanozdabak.productData.dataAccess.abstracts.ProductRepository;
import hakanozdabak.productData.entities.concretes.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

public class ProductRepositoryTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ProductRepository productRepository;



    @Test
    void shouldFindProductExistsByName() {
        Product product = Product.builder()
                .name("Coke")
                .category("Drink")
                .price(10)
                .onOffer(true)
                .quantity(10)
                .build();

        when(authenticationService.register(Mockito.any(RegisterRequest.class))).thenReturn(authenticationService.authenticate(Mockito.any(AuthenticationRequest.class)));

        productRepository.save(product);

        assertThat(productRepository.existsByName("Coke")).isNotNull();
    }



    @Test
    void findByCategory() {
    }
}