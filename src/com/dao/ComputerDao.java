package com.dao;

import com.domain.JdbcModel;

/**
 *
 * @author Franco
 */
public class ComputerDao<T extends JdbcModel>  extends BaseDao {


    public ComputerDao(Class<T> clazz) {
        super(clazz);
    }
}
