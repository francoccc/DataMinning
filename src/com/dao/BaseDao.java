package com.dao;

import java.sql.Connection;

/**
 * BaseDao
 *
 * @author Franco
 */
public abstract class BaseDao implements Dao{

    /** Constant */
    private static String driver="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://127.0.0.1:3306/epet";
    private static String user="root";
    private static String password="root";

    /** Connection */
    private Connection conn;

    private BaseDao() { }

    @Override
    public Connection getConnection() {
        return conn;
    }
}
