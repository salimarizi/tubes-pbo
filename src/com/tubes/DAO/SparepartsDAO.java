package com.tubes.DAO;

import com.tubes.Model.SparepartsEntity;
import com.tubes.Model.UsersEntity;
import com.tubes.Utility.HibernateUtil;
import com.tubes.Utility.JDBCConnection;
import javafx.collections.FXCollections;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<SparepartsEntity> fetchServiceSparepart(int service_id) {
        List<SparepartsEntity> spareparts = FXCollections.observableArrayList();

        try{
            String query = "SELECT spareparts.id, spareparts.name, spareparts.price, spareparts.quantity " +
                    "FROM spareparts " +
                    "INNER JOIN service_sparepart_relations ON spareparts.id = service_sparepart_relations.sparepart_id " +
                    "WHERE service_sparepart_relations.service_id = " + service_id;

            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();

            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                BigDecimal price = res.getBigDecimal("price");
                int quantity = res.getInt("quantity");

                spareparts.add(new SparepartsEntity(id, name, quantity, price));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return spareparts;
    }
}
