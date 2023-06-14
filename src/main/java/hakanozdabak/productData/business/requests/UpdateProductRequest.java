package hakanozdabak.productData.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    private int id;
    private String name;
    private String category;
    private float price;
    private int quantity;
    private boolean onOffer;


}
