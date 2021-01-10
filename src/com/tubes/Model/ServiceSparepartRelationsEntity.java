package com.tubes.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "service_sparepart_relations", schema = "db_bengkel", catalog = "")
public class ServiceSparepartRelationsEntity {
    private int id;
    private Integer serviceId;
    private Integer sparepartId;
    private ServicesEntity servicesByServiceId;
    private SparepartsEntity sparepartsBySparepartId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSparepartId(Integer sparepartId) {
        this.sparepartId = sparepartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceSparepartRelationsEntity that = (ServiceSparepartRelationsEntity) o;
        return id == that.id &&
                Objects.equals(serviceId, that.serviceId) &&
                Objects.equals(sparepartId, that.sparepartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceId, sparepartId);
    }

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    public ServicesEntity getServicesByServiceId() {
        return servicesByServiceId;
    }

    public void setServicesByServiceId(ServicesEntity servicesByServiceId) {
        this.servicesByServiceId = servicesByServiceId;
    }

    @ManyToOne
    @JoinColumn(name = "sparepart_id", referencedColumnName = "id")
    public SparepartsEntity getSparepartsBySparepartId() {
        return sparepartsBySparepartId;
    }

    public void setSparepartsBySparepartId(SparepartsEntity sparepartsBySparepartId) {
        this.sparepartsBySparepartId = sparepartsBySparepartId;
    }
}
