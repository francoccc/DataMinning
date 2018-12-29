package com.dao;

import java.sql.Connection;
import java.util.List;

public interface Dao {

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
    List<Object> getAll();
}
