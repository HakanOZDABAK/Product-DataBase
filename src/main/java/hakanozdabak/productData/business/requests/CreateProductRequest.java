package hakanozdabak.productData.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    private String name;

    private float price;

    private int quantity;

    private boolean onOffer;

    private String category;
}
