package com.dbpool;

import com.common.Constants;

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
       return getConnetion(dbpool, 0);
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
    Connection conn2 = DbConnectionManager.getConnetion(Constants.DB);
    public static void clear(String dbpool) {
        register.get(dbpool).clear();
    }

    public static void main(String[] args) {
        /*
        Connection conn1 = DbConnectionManager.getConnetion(Constants.DB);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn2 = DbConnectionManager.getConnetion(Constants.DB);
                System.out.println("thread sleep.");
                try {
                    Thread.currentThread().sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread awake.");
                DbConnectionManager.release(conn2);
                System.out.println("realease connection");
            }
        }).start();
        Connection conn3 = DbConnectionManager.getConnetion(Constants.DB, 10000);
        if(null != conn3) {
            System.out.println("connect success");
        }*/
    }
}
