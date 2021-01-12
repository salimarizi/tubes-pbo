package com.tubes.DAO;

import com.tubes.Model.ServicesEntity;
import com.tubes.Model.SparepartsEntity;
import com.tubes.Model.UsersEntity;
import com.tubes.Model.VehiclesEntity;
import com.tubes.Utility.HibernateUtil;
import com.tubes.Utility.JDBCConnection;
import javafx.collections.FXCollections;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ServicesDAO implements DAOInterface<ServicesEntity> {

    @Override
    public List<ServicesEntity> fetchAll() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(ServicesEntity.class);
        query.from(ServicesEntity.class);
        List<ServicesEntity> services = s.createQuery(query).getResultList();
        return services;
    }

    public List<ServicesEntity> fetchUserService(int user_id) {
        List<ServicesEntity> services = FXCollections.observableArrayList();

        try{
            String query = "SELECT services.id AS id, services.vehicle_id AS vehicle_id, services.technician_id AS technician_id, services.date AS date, services.problem AS problem, services.action AS action " +
                    "FROM services " +
                    "INNER JOIN vehicles ON services.vehicle_id = vehicles.id " +
                    "INNER JOIN users  ON vehicles.user_id = users.id " +
                    "WHERE users.id = " + user_id;

            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();

            while (res.next()){
                int id = res.getInt("id");
                int vehicle_id = res.getInt("vehicle_id");
                int technician_id = res.getInt("technician_id");
                Timestamp date = res.getTimestamp("date");
                String problem = res.getString("problem");
                String action = res.getString("action");

                VehiclesEntity vehicle = new VehiclesEntity();
                vehicle.setId(vehicle_id);

                UsersEntity technician = new UsersEntity();
                technician.setId(technician_id);

                services.add(new ServicesEntity(id, vehicle, technician, date, problem, action));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return services;
    }

    @Override
    public int insertData(ServicesEntity object){
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int editData(ServicesEntity object){
        Session s = HibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.update(object);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int deleteData(ServicesEntity object){
        Session s = HibernateUtil.getSession();
        Transaction t  = s.beginTransaction();
        s.delete(object);
        t.commit();
        s.close();
        return 0;
    }
}
