package com.servlet.db.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface IDao<T>{
    boolean add(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    T findOne(Serializable id) throws SQLException;
    boolean delete(Serializable id) throws SQLException;
    List<T> findAll() throws SQLException;
}
