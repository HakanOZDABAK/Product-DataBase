package hakanozdabak.productData.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name="category")
    private String category;
    @Column(name = "price")
    private float price;
    @Column(name = "on_offer")
    private boolean onOffer;
    @Column(name = "quantity")
    private int quantity;



}
