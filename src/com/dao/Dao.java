package com.dao;

import com.domain.JdbcModel;

import java.sql.Connection;
import java.util.List;

public interface Dao <T extends JdbcModel>{

    /**
     * 获取数据库连接
     */
    Connection getConnection();

    /**
     * 返回数据库连接
     */
    void release(Connection conn);

    /**
     * 获取表
     */
    List<T> getAll();
}
