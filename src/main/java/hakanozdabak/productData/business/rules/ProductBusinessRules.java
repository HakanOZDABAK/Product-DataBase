package hakanozdabak.productData.business.rules;

import hakanozdabak.productData.core.utilities.exceptions.BusinessException;
import hakanozdabak.productData.dataAccess.abstracts.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductBusinessRules {
    private ProductRepository productRepository;
    public void checkIfProductNameExists(String name){
       if(this.productRepository.existsByName(name)){
           throw new BusinessException("Product name already exists");
       }

    }
}
