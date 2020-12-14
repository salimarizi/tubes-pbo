package com.tubes.dao;

import com.tubes.model.ServicesEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.tubes.utility.hibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ServicesDAO implements DAOInterface<ServicesEntity> {

    @Override
    public List<ServicesEntity> fetchAll() {

        Session s = hibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(ServicesEntity.class);
        query.from(ServicesEntity.class);
        List<ServicesEntity> services = s.createQuery(query).getResultList();
        return services;
    }

    @Override
    public int insertData(ServicesEntity object){
        Session s = hibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int editData(ServicesEntity object){
        Session s = hibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.update(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int deleteData(ServicesEntity object){
        Session s = hibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.delete(object);
        t.commit();
        s.close();
        return 0;
    }
}
