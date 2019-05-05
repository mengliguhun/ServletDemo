package com.servlet.db.dao;

import com.servlet.db.bean.UserBean;

import java.sql.SQLException;

public interface UserDao extends IDao<UserBean>{
   UserBean getUserByName(String name) throws SQLException;
}
