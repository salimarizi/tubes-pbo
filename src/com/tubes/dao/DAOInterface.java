package com.tubes.DAO;

import java.util.List;

public interface DAOInterface<E> {

    public List<E> fetchAll();

    public int insertData(E data);

    public int editData(E data);

    public int deleteData(E data);
}
