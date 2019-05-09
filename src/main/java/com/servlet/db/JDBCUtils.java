package com.servlet.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
    //数据库连接池
    private static ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
    /**
     * 配置DataSource
     */
//    static {
//        try {
//            pooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
//            pooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai");
//            pooledDataSource.setUser("root");
//            pooledDataSource.setPassword("yin123456");
//            pooledDataSource.setInitialPoolSize(3);
//            pooledDataSource.setMaxPoolSize(10);
//            pooledDataSource.setMinPoolSize(3);
//            pooledDataSource.setAcquireIncrement(3);
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//    }

    public static Connection getConnection() throws SQLException {
        return pooledDataSource.getConnection();
    }

    public static DataSource getDataSource (){
        return pooledDataSource;
    }

    public static void release(ResultSet resultSet, Statement statement,Connection connection){
        if (resultSet != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
        release(statement,connection);
    }

    public static void release(Statement statement,Connection connection){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }
}
