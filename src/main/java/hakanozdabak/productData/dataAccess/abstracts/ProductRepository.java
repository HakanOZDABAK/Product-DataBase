package hakanozdabak.productData.dataAccess.abstracts;

import hakanozdabak.productData.business.responses.GetByCategoryProductsResponse;
import hakanozdabak.productData.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    boolean existsByName(String name);
    List<Product> findByCategory(String category);


}
