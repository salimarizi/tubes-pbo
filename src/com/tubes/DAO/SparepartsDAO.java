package com.tubes.DAO;

import com.tubes.Model.SparepartsEntity;
import com.tubes.Utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SparepartsDAO implements DAOInterface<SparepartsEntity> {
    @Override
    public List<SparepartsEntity> fetchAll() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(SparepartsEntity.class);
        query.from(SparepartsEntity.class);

        List<SparepartsEntity> spareparts = s.createQuery(query).getResultList();
        s.close();
        return spareparts;
    }

    @Override
    public int insertData(SparepartsEntity object) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int editData(SparepartsEntity object) {
        Session s = HibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.update(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int deleteData(SparepartsEntity object) {
        Session s = HibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.delete(object);
        t.commit();
        s.close();
        return 0;
    }
}
