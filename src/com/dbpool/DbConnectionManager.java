package com.dbpool;

import java.sql.Connection;
import java.util.HashMap;

/**
 * DBPool管理器
 *
 */
public class DbConnectionManager {

    private static HashMap<String, DbPool> register = new HashMap<>();
    private static HashMap<Connection, String> belongs = new HashMap<>(16);

    static {
        register.put("mainpool", DbPool.getInstance());
    }

    public static Connection getConnetion(String dbpool) {
       return register.get(dbpool).getConnection(0);
    }

    public static Connection getConnetion(String dbpool, long timeout) {
        if(!register.containsKey(dbpool)) {
            return null;
        }
        Connection conn = register.get(dbpool).getConnection(timeout);
        if(conn != null) {
            belongs.put(conn, dbpool);
        }
        return conn;
    }

    public static void release(Connection connection) {
        String dbpool = belongs.get(connection);
        register.get(dbpool).releaseConnection(connection);
    }

    public static void clear(String dbpool) {
        register.get(dbpool).clear();
    }
}
