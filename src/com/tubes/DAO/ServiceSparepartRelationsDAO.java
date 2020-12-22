package com.tubes.DAO;

import com.tubes.Model.ServiceSparepartRelationsEntity;
import com.tubes.Utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ServiceSparepartRelationsDAO implements DAOInterface<ServiceSparepartRelationsEntity> {
    @Override
    public List<ServiceSparepartRelationsEntity> fetchAll() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(ServiceSparepartRelationsEntity.class);
        query.from(ServiceSparepartRelationsEntity.class);

        List<ServiceSparepartRelationsEntity> serviceSpareparts = s.createQuery(query).getResultList();
        s.close();
        return serviceSpareparts;
    }

    @Override
    public int insertData(ServiceSparepartRelationsEntity object) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int editData(ServiceSparepartRelationsEntity object) {
        Session s = HibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.update(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int deleteData(ServiceSparepartRelationsEntity object) {
        Session s = HibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.delete(object);
        t.commit();
        s.close();
        return 0;
    }
}
