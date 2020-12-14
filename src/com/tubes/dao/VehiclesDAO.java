package com.tubes.dao;

import com.tubes.model.VehiclesEntity;
import com.tubes.dao.DAOInterface;
import com.tubes.utility.hibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class VehiclesDAO implements DAOInterface<VehiclesEntity> {
    @Override
    public List<VehiclesEntity> fetchAll() {
        Session s = hibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(VehiclesEntity.class);
        query.from(VehiclesEntity.class);

        List<VehiclesEntity> vehicles = s.createQuery(query).getResultList();
        s.close();
        return vehicles;
    }

    @Override
    public int insertData(VehiclesEntity object) {
        Session s = hibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int editData(VehiclesEntity object) {
        Session s = hibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.update(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int deleteData(VehiclesEntity object) {
        Session s = hibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.delete(object);
        t.commit();
        s.close();
        return 0;
    }
}
