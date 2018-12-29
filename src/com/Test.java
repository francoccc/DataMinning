package com;

import com.common.Constants;
import com.dbpool.DbConnectionManager;

import java.sql.Connection;

/**
 * Test
 *
 * @author Franco
 */
public class Test {

    public static void main(String[] args) {
        Connection conn = DbConnectionManager.getConnetion(Constants.DB);
    }
}
