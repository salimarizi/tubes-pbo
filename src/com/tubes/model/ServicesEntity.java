package com.tubes.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "services", schema = "db_bengkel", catalog = "")
public class ServicesEntity {
    private int id;
    private Timestamp date;
    private String problem;
    private String action;
    private VehiclesEntity vehiclesByVehicleId;
    private UsersEntity usersByTechnicianId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "problem")
    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    @Basic
    @Column(name = "action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicesEntity that = (ServicesEntity) o;
        return id == that.id &&
                Objects.equals(date, that.date) &&
                Objects.equals(problem, that.problem) &&
                Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, problem, action);
    }

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
    public VehiclesEntity getVehiclesByVehicleId() {
        return vehiclesByVehicleId;
    }

    public void setVehiclesByVehicleId(VehiclesEntity vehiclesByVehicleId) {
        this.vehiclesByVehicleId = vehiclesByVehicleId;
    }

    @ManyToOne
    @JoinColumn(name = "technician_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByTechnicianId() {
        return usersByTechnicianId;
    }

    public void setUsersByTechnicianId(UsersEntity usersByTechnicianId) {
        this.usersByTechnicianId = usersByTechnicianId;
    }
}
