package com.domain;

public class AbstractModel implements JdbcModel, Cloneable {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
