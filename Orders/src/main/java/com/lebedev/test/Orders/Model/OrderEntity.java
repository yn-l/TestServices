package com.lebedev.test.Orders.Model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @Column(name = "order_id", nullable=false, updatable=false)
/*
    @GeneratedValue(generator="my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="orders_order_id_seq", allocationSize=1)
*/
    private Long orderId;

    private String owner;
    private String address;
    private Timestamp date;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderProductsEntity> products;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Set<OrderProductsEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderProductsEntity> products) {
        this.products = products;
    }

    public void add(OrderProductsEntity item) {
        if (item != null) {
            if (products == null) {
                products = new HashSet<>();
            }
            products.add(item);
        }
    }

    public void setProductsFromMap(Map<Long, Integer> products) {
        Set<OrderProductsEntity> entrySet = products.entrySet().stream()
                .filter(entry -> entry.getKey()!=null && entry.getValue()!=null && entry.getKey()>0 && entry.getValue() > 0)
                .map(entry -> {
                    var ope = new OrderProductsEntity();
                    ope.setProductId(entry.getKey());
                    ope.setOrderId(ope.getOrderId());
                    ope.setAmount(entry.getValue());
                    return ope;
                }).collect(Collectors.toSet());

        this.products = entrySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity that)) return false;
        return Objects.equals(getOrderId(), that.getOrderId()) && Objects.equals(getOwner(), that.getOwner()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getProducts(), that.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getOwner(), getAddress(), getDate(), getProducts());
    }
}
