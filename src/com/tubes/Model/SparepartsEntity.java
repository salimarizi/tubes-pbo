package com.tubes.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "spareparts", schema = "db_bengkel", catalog = "")
public class SparepartsEntity {
    private int id;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private Collection<ServiceSparepartRelationsEntity> serviceSparepartRelationsById;

    public SparepartsEntity(){

    }

    public SparepartsEntity(int id, String name, Integer quantity, BigDecimal price){
        setId(id);
        setName(name);
        setQuantity(quantity);
        setPrice(price);
    }

    public SparepartsEntity(String name, Integer quantity, BigDecimal price){
        setName(name);
        setQuantity(quantity);
        setPrice(price);
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SparepartsEntity that = (SparepartsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price);
    }

    @OneToMany(mappedBy = "sparepartsBySparepartId")
    public Collection<ServiceSparepartRelationsEntity> getServiceSparepartRelationsById() {
        return serviceSparepartRelationsById;
    }

    public void setServiceSparepartRelationsById(Collection<ServiceSparepartRelationsEntity> serviceSparepartRelationsById) {
        this.serviceSparepartRelationsById = serviceSparepartRelationsById;
    }

    @Override
    public String toString() {
        return name;
    }
}
