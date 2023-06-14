package hakanozdabak.productData.dataAccess.abstracts;

import hakanozdabak.productData.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
