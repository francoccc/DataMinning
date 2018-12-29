package com.domain;

public abstract class AbstractDomain implements JdbcModel, Cloneable {

    /**
     * clone
     * @return
     */
    @Override
    public abstract Object clone();
}
