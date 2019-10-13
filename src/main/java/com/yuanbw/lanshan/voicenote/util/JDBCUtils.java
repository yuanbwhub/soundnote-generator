package com.yuanbw.lanshan.voicenote.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {
    private static Connection conn;
    private static ComboPooledDataSource ds = new ComboPooledDataSource();

    public static Connection getConnection() {
        try {
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://118.24.22.59:3306/weixin");
            ds.setUser("root");
            ds.setPassword("123456");
            ds.setInitialPoolSize(5);
            ds.setMinPoolSize(1);
            ds.setMaxPoolSize(10);
            ds.setMaxStatements(50);
            ds.setAcquireRetryAttempts(30);
            ds.setMaxIdleTime(60);
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
