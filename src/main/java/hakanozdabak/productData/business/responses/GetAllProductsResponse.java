package hakanozdabak.productData.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductsResponse {
    private int id;
    private String name;
    private String category;
    private float price;
    private boolean onOffer;
    private int quantity;


}
