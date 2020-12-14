package com.tubes.dao;

import com.tubes.model.UsersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.tubes.utility.hibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UsersDAO implements DAOInterface<UsersEntity> {


    @Override
    public List<UsersEntity> fetchAll() {
        Session s = hibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(UsersEntity.class);
        query.from(UsersEntity.class);

        List<UsersEntity> users = s.createQuery(query).getResultList();
        s.close();
        return users;
    }

    @Override
    public int insertData(UsersEntity object) {
        Session s = hibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int editData(UsersEntity object) {
        Session s = hibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.update(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int deleteData(UsersEntity object) {
        Session s = hibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.delete(object);
        t.commit();
        s.close();
        return 0;
    }
}
