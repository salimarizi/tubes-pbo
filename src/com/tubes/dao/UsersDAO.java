package com.tubes.DAO;

import com.tubes.Model.ServicesEntity;
import com.tubes.Model.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.tubes.Utility.HibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UsersDAO implements DAOInterface<UsersEntity> {
    @Override
    public List<UsersEntity> fetchAll() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(UsersEntity.class);
        query.from(UsersEntity.class);

        List<UsersEntity> users = s.createQuery(query).getResultList();
        s.close();
        return users;
    }

    @Override
    public int insertData(UsersEntity object){
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int editData(UsersEntity object) {
        Session s = HibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.update(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int deleteData(UsersEntity object) {
        Session s = HibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.delete(object);
        t.commit();
        s.close();
        return 0;
    }
}
