package com.common.manager;

import com.dao.ComputerDao;
import com.domain.Computer;

/**
 * Computer表管理器
 *
 * @author Franco
 */
public class ComputerManager extends BaseDataManager {

    /** instance */
    private final static ComputerManager instance;
    private final static ComputerDao<Computer> dao = new ComputerDao(Computer.class);

    static {
        instance = new ComputerManager();
    }

    public static ComputerManager getInstance() {
        return instance;
    }

    /**
     * @see BaseDataManager#init()
     */
    @Override
    public void init() {

    }
}
