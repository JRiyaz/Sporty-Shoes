package com.sportyshoes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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

    private String name;

    @Column(name = "style_name")
    private String styleName;

    private String color;

    @Column(name = "in_stock_pieces")
    private Integer inStockPieces;

    @Column(name = "sold_pieces")
    private Integer soldPieces;

    @Column(name = "suitable_for")
    private String suitableFor;

    private String type;

    private String material;

    private Double price;

    private String description;

    @Lob
    private Byte[] image;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = OrderEntity.class)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    public ProductEntity(String name, String styleName, String color, Integer inStockPieces,
                         Integer soldPieces, String suitableFor, String type, String material,
                         Double price, String description, Byte[] image) {
        this.name = name;
        this.styleName = styleName;
        this.color = color;
        this.inStockPieces = inStockPieces;
        this.soldPieces = soldPieces;
        this.suitableFor = suitableFor;
        this.type = type;
        this.material = material;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public static String bytesToImageConverter(byte[] imageInBytes) {

        return imageInBytes != null && imageInBytes.length > 0 ? Base64.getEncoder().encodeToString(imageInBytes) : "";
    }
}
