package com.algorithm;

import com.common.MathUtils;
import com.data.BaseRecord;
import com.data.Records;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * ID3
 *
 * @author Franco
 */
public class ID3Selector implements AttrSelector {

    /**
     * 信息增益计算选择最好的属性域返回. gain(Attr) = Entropy(D) - Entropy(Attr)
     * @see com.algorithm.AttrSelector#select(List, Set)
     * @param records
     * @param attrs
     * @return best
     */
    @Override
    public Field select(List<BaseRecord> records, Set<Field> attrs) {
        double entropyD = entropy(records);
        double maxGain = 0D;
        Field best = null;
        for(Field attr : attrs) {
            double entropyAttr = entropy(records, attr);
            double gain = entropyD - entropyAttr;
            if(gain > maxGain) {
                maxGain = gain;
                best = attr;
            }
        }
        return best;
    }

    /**
     * get entropy of recodes
     * @param records
     * @return entropyD
     */
    private double entropy(List<BaseRecord> records) {
        double positive = 0D;
        double negative = 0D;
        for(BaseRecord record : records) {
            if(record.isDecisionAttr()) {
                positive++;
            } else {
                negative++;
            }
        }
        return -positive / records.size() * MathUtils.log2N(positive / records.size()) -
                negative / records.size() * MathUtils.log2N(negative / records.size());
    }

    /**
     * get entropy of records with field
     * @param records
     * @param field
     * @return entropyAttr
     */
    private double entropy(List<BaseRecord> records, Field field) {
        HashMap<Object, List<Integer>> map = new HashMap<>(16);
        field.setAccessible(true);
        for(BaseRecord record : records) {
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
            double positivePart = -positive / records.size() * MathUtils.log2N(positive / sum);
            double negativePart = -negative / records.size() * MathUtils.log2N(negative / sum);
            if(positive == 0) {
                positivePart = 0;
            }
            if(negative == 0) {
                negativePart = 0;
            }
            score += positivePart + negativePart;
        }
        return score;
    }
}
