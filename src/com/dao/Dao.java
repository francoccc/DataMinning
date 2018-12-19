package com.dao;

import java.sql.Connection;

public interface Dao {

    /**
     *
     * 获取数据库连接
     */
    Connection getConnection();
}
