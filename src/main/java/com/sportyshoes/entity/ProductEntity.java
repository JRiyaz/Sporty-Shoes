package com.sportyshoes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Base64;

@Entity
@Table(name = "products")
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max = 30, message = "Product name must be min 3 and max 30 characters")
    @NotEmpty(message = "Please enter product name")
    private String name;

    @Size(min = 3, max = 30, message = "Product style name must be min 3 and max 30 characters")
    @NotEmpty(message = "Please enter style name")
    @Column(name = "style_name")
    private String styleName;

    @Size(min = 3, max = 30, message = "Brand name must be min 3 and max 30 characters")
    @NotEmpty(message = "Please enter brand name")
    private String brand;

    @Size(min = 3, max = 30, message = "Product color must be min 3 and max 30 characters")
    @NotEmpty(message = "Please enter product color")
    private String color;

    @Min(value = 1, message = "Stock must be min 1")
    private Integer stock;

    private Integer sold;

    @Size(min = 3, max = 30, message = "Suitable must be min 3 and max 30 characters")
    @NotEmpty(message = "Please select suitable for")
    @Column(name = "suitable_for")
    private String suitableFor;

    @Size(min = 3, max = 30, message = "Product type must be min 3 and max 30 characters")
    @NotEmpty(message = "Please enter product type")
    private String type;

//    @Size(min = 3, max = 30, message = "Product material must be min 3 and max 30 characters}")
//    @NotEmpty(message = "Please enter product material")
//    private String material;

    @Min(value = 100, message = "Price must be min 100")
    private Double price;

    @Size(min = 3, max = 1000, message = "Product description must be min 3 and max 1000 characters")
    @NotEmpty(message = "Please enter product description")
    @Column(length = 1000)
    private String description;

    @Lob
    @Column(length = Integer.MAX_VALUE)
    @NotEmpty(message = "Please select product image")
    private byte[] image;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = OrderEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public ProductEntity(String name,
                         String styleName,
                         String brand,
                         String color,
                         Integer stock, Integer sold,
                         String suitableFor,
                         String type,
                         Double price,
                         String description,
                         byte[] image) {
        this.name = name;
        this.styleName = styleName;
        this.brand = brand;
        this.color = color;
        this.stock = stock;
        this.sold = sold;
        this.suitableFor = suitableFor;
        this.type = type;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public static String bytesToImageConverter(byte[] imageInBytes) {

        return imageInBytes != null && imageInBytes.length > 0 ? Base64.getEncoder().encodeToString(imageInBytes) : "";
    }
}
