package com.algorithm;

import com.data.Records;

import java.lang.reflect.Field;
import java.util.Set;

/**
 *
 * @author Franco
 */
public interface AttrSelector {

    Field select(Records records, Set<Field> attrs);
}
