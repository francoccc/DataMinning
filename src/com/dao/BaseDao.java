package com.dao;

import com.common.Constants;
import com.dbpool.DbConnectionManager;
import com.domain.JdbcModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * BaseDao
 *
 * @author Franco
 */
public class BaseDao implements Dao{

    /** Connection */
    private Connection conn;
    /** PreStatement */
    private PreparedStatement ps;
    /** ResultSet */
    private ResultSet rs;
    /** Model */
    private Class<?> clazz;
    private String className;

    /**
     * 构造函数
     * @param clazz
     */
    public BaseDao(Class<?> clazz) {
        this.clazz = clazz;
        String[] strs = clazz.getName().split("\\.");
        className = strs[strs.length - 1];
    }

    @Override
    public Connection getConnection() {
        return DbConnectionManager.getConnetion(Constants.DB);
    }

    @Override
    public void release(Connection conn) {
        DbConnectionManager.release(conn);
    }

    @Override
    public List<Object> getAll() {
        String sql = "SELECT * FROM " + className;
        conn = getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(className + " sql query error");
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                DbConnectionManager.release(conn);
                conn = null;
            } catch (SQLException e) {
                System.out.println("release connection fail!");
                e.printStackTrace();
            }
        }
        return null;
    }
}
