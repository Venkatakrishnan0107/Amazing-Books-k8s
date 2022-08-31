package com.amazing.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ORDER_TBL")
public class Order {

    @Id
    @GeneratedValue
    private int orderId;
    private String name;
    private int qty;
    private double price;
}
