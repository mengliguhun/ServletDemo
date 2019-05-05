package com.servlet.db.dao;

import com.servlet.db.JDBCUtils;
import com.servlet.db.bean.UserBean;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private static Connection connection;
    private static UserDao userDao;

    public static UserDao getInstance()throws SQLException{
        if (userDao == null){
            userDao = new UserDaoImpl();
        }
        if (connection == null){
            connection = JDBCUtils.getConnection();
        }
        return userDao;
    }
    @Override
    public boolean add(UserBean userBean) throws SQLException {
        //创建QueryRunner类对象
        QueryRunner qr = new QueryRunner();
        String sql = "INSERT INTO users (username,userphone,userpwd)VALUES(?,?,?)";
        //将三个?占位符的实际参数,写在数组中
        Object[] params = {userBean.getUserName(),userBean.getUserPhone(),userBean.getUserPwd()};
        //调用QueryRunner类的方法update执行SQL语句
        int row = qr.update(connection, sql, params);
        System.out.println(row);
        DbUtils.closeQuietly(connection);
        //row>0代表成功
        return row>0;
    }

    @Override
    public boolean update(UserBean userBean) throws SQLException {
        //创建QueryRunner类对象
        QueryRunner qr = new QueryRunner();
        //写修改数据的SQL语句
        String sql = "UPDATE users SET username=?,userphone=?,userpwd=? WHERE id=?";
        //定义Object数组,存储?中的参数
        Object[] params = {userBean.getUserName(),userBean.getUserPhone(),userBean.getUserPwd(),userBean.getUserId()};
        //调用QueryRunner方法update
        int row = qr.update(connection, sql, params);
        DbUtils.closeQuietly(connection);
        //row>0代表成功
        return row>0;
    }

    @Override
    public UserBean findOne(Serializable id) throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql = "SELECT * FROM user where id=?";
        //调用方法，传递结果集实现BeanHandler
        //BeanHandler(Class<T> type)
        UserBean userBean = qr.query(connection, sql,new BeanHandler<>(UserBean.class), id);
        return userBean;
    }

    @Override
    public boolean delete(Serializable id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "delete from users where id=?";
        int row = queryRunner.update(connection,sql,id);
        DbUtils.closeQuietly(connection);
        return row>0;
    }

    @Override
    public List<UserBean> findAll() throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql = "SELECT * FROM users";
        //调用方法传递结果集的实现类BeanListHandler
        //BeanListHandler(Class<T> type)
        List<UserBean> list = qr.query(connection, sql, new BeanListHandler<>(UserBean.class));
        return list;
    }

    @Override
    public UserBean getUserByName(String name) throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql = "SELECT * FROM users where username=?";
        //调用方法，传递结果集实现BeanHandler
        //BeanHandler(Class<T> type)
        UserBean userBean = qr.query(connection, sql,new BeanHandler<>(UserBean.class), name);
        return userBean;
    }
}
