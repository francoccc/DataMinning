package com.dao;

import com.common.Constants;
import com.dbpool.DbConnectionManager;
import com.domain.JdbcModel;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * BaseDao
 *
 * @author Franco
 */
public class BaseDao<T extends JdbcModel> implements Dao{

    /** Connection */
    private Connection conn;
    /** PreStatement */
    private PreparedStatement ps;
    /** ResultSet */
    private ResultSet rs;
    /** Model */
    private Class<T> clazz;
    private String className;

    /**
     * 构造函数
     */
    public BaseDao(Class<T> clazz) {
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
    public List<T> getAll() {
        String sql = "SELECT * FROM " + className;
        conn = getConnection();
        List<T> model = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                while(rs.next()) {
                    T instance = clazz.newInstance();
                    Field[] fields = clazz.getDeclaredFields();
                    int columnIndex = 1;
                    for (Field field : fields) {
                        field.setAccessible(true);
                        field.set(instance, rs.getObject(columnIndex++));
                    }
                    model.add(instance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

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
        return model;
    }
}
