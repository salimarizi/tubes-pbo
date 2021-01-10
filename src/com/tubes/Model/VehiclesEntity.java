package com.tubes.Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "vehicles", schema = "db_bengkel", catalog = "")
public class VehiclesEntity {
    private int id;
    private int userId;
    private String name;
    private String policeNumber;
    private String color;
    private String type;
    private Collection<ServicesEntity> servicesById;
    private UsersEntity usersByUserId;

    public VehiclesEntity() {
    }

    public VehiclesEntity(Integer id, String name, String policeNumber, String color, String type, UsersEntity usersByUserId) {
        setId(id);
        setName(name);
        setPoliceNumber(policeNumber);
        setColor(color);
        setType(type);
        setUsersByUserId(usersByUserId);
    }

    public VehiclesEntity(String name, String policeNumber, String color, String type, UsersEntity usersByUserId) {
        setUserId(userId);
        setName(name);
        setPoliceNumber(policeNumber);
        setColor(color);
        setType(type);
        setUsersByUserId(usersByUserId);
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    @Column(name = "police_number")
    public String getPoliceNumber() {
        return policeNumber;
    }

    public void setPoliceNumber(String policeNumber) {
        this.policeNumber = policeNumber;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehiclesEntity that = (VehiclesEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(name, that.name) &&
                Objects.equals(policeNumber, that.policeNumber) &&
                Objects.equals(color, that.color) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, policeNumber, color, type);
    }

    @OneToMany(mappedBy = "vehiclesByVehicleId")
    public Collection<ServicesEntity> getServicesById() {
        return servicesById;
    }

    public void setServicesById(Collection<ServicesEntity> servicesById) {
        this.servicesById = servicesById;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @Override
    public String toString() {
        return name;
    }
}
