package com.tubes.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T> {

    List<T> fetchAll();

    int insertData(T object);

    int editData(T object);

    int deleteData(T object);

}
