package com.algorithm;

import com.data.BaseRecord;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Franco
 */
public interface AttrSelector {

    /**
     * 选择最佳的属性,划分节点
     * @param records
     * @param attrs
     * @return Field
     */
    Field select(List<BaseRecord> records, Set<Field> attrs);
}
