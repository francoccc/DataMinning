package com.dbpool;

import com.ReadXMLConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DbPool
 *
 * @author Franco
 */
public class DbPool {

    /** instance */
    private static DbPool instance;

    private static ReadXMLConfig config = ReadXMLConfig.getInstance();
    private static String driver;
    private String url;
    private String user;
    private String password;
    private int maxPool;
    private int maxConn;
    private int maxExpire;
    private LinkedList<Connection> connections = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    private AtomicInteger connNum = new AtomicInteger(0);

    static {
        driver = config.getConfigValue("dbpool.driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("driver not found!");
        }
        instance = new DbPool();
    }

    private DbPool() {
        initPool();
    }

    /**
     *
     * @return instance
     */
    public static DbPool getInstance() {
        return instance;
    }

    /**
     * pooled
     *
     */
    private void initPool() {
        url = config.getConfigValue("dbpool.url");
        user = config.getConfigValue("dbpool.user");
        password = config.getConfigValue("dbpool.password");
        maxPool = Integer.parseInt(config.getConfigValue("dbpool.maxpool"));
        maxConn = Integer.parseInt(config.getConfigValue("dbpool.maxconn"));
        try {
            for(int i = 1; i <= maxPool; i++) {
                Connection conn = DriverManager.getConnection(url, user, password);
                connections.add(conn);
            }
        } catch (SQLException e) {
            System.out.println("connect database failure");
            e.printStackTrace();
        }
    }

    protected Connection getConnection(long timeout) {
        Connection conn = null;
        lock.lock();
        try {
            long lastTime = System.currentTimeMillis();
            while(true) {
                long currentTime = System.currentTimeMillis();
                if(timeout > 0 && currentTime - lastTime > timeout) {
                    System.out.println("get connection time-out.");
                    break;
                }
                if(!connections.isEmpty() && connNum.get() <= maxConn) {
                    conn = connections.removeFirst();
                    connNum.incrementAndGet();
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
        return conn;
    }

    protected void releaseConnection(Connection conn) {
        connections.add(conn);
        connNum.decrementAndGet();
    }

    protected void clear() {
        lock.lock();
        Connection conn = null;
        boolean retry = false;
        try {
            while(!connections.isEmpty()) {
                conn = connections.removeFirst();
                conn.close();
            }
        } catch (SQLException e) {
            connections.add(conn);
            System.out.println("connection close error!");
            System.out.println("retry..");
            retry = true;
        } finally {
            lock.unlock();
            if(retry) {
                this.clear();
            }
        }
    }
}
