package com.tubes.DAO;

import com.tubes.Model.ServicesEntity;
import com.tubes.Model.UsersEntity;
import com.tubes.Utility.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public List<UsersEntity> login(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM UsersEntity WHERE username = :username AND password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<UsersEntity> users = query.list();
        session.close();

        return users;
    }
}
