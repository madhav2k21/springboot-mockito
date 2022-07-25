package com.techleads.app.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String itemName;
    private Integer qty;
    private Double price;
    @Transient
    private Double value;

    public Item(Integer id, String itemName, Integer qty, Double price) {
        this.id = id;
        this.itemName = itemName;
        this.qty = qty;
        this.price = price;
    }
}
