package com.sportyshoes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "orders")
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bill_amount")
    private Double billAmount;

    @Column(name = "bill_paid")
    private Boolean billPaid;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<ProductEntity> products;

    public OrderEntity(Double billAmount, Boolean billPaid, List<ProductEntity> products) {
        this.billAmount = billAmount;
        this.billPaid = billPaid;
        this.products = products;
    }
}
