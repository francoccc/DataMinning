package com.data;

import com.common.MathUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Franco
 */
public class Records<T extends BaseRecord>{

    private List<T> records;

    public Records() {
        records = new ArrayList<>();
    }

    public Records(List<T> records) {
        this.records = records;
    }

    public double entropy() {
        double positive = 0D;
        double negative = 0D;
        for(T record : records) {
            if(record.isDecisionAttr()) {
                positive++;
            } else {
                negative++;
            }
        }
        return -positive / records.size() * MathUtils.log2N(positive / records.size()) -
                negative / records.size() * MathUtils.log2N(negative / records.size());
    }

    public double entropy(Field field) {
        HashMap<Object, List<Integer>> map = new HashMap<>(16);
        field.setAccessible(true);
        for(T record : records) {
            try {
                Object value = field.get(record);
                List<Integer> counts = map.get(value);
                if(null == counts) {
                    counts = new ArrayList<>(2);
                    counts.add(0); counts.add(0);
                    map.put(value, counts);
                }
                if(record.isDecisionAttr()) {
                    counts.set(0, counts.get(0) + 1);
                } else {
                    counts.set(1, counts.get(1) + 1);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        double score = 0D;
        for(Object value : map.keySet()) {
            double positive = map.get(value).get(0);
            double negative = map.get(value).get(1);
            double sum = positive + negative;
            score += -positive / records.size() * MathUtils.log2N(positive / sum)
                     -negative / records.size() * MathUtils.log2N(negative / sum);
        }
        return score;
    }
}
