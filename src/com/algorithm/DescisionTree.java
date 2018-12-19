package com.algorithm;

import com.common.TreeNode;
import com.data.BaseRecord;

import java.util.List;

/**
 *
 * @author Franco
 */
public class DescisionTree {

    AttrSelector attrSelector;

    public DescisionTree(AttrSelector attrSelector) {
        this.attrSelector = attrSelector;
    }

    public TreeNode create() {
        TreeNode node = new TreeNode();
        return node;
    }

    private boolean getNodeAttr(List<BaseRecord> records) {
        int positive = 0;
        int negative = 0;
        for(int i = 0; i < records.size(); i++) {
            if(records.get(i).isDecisionAttr()) {
                positive++;
            } else {
                negative++;
            }
        }
        return positive > negative;
    }

    private boolean isAllInSameClass(List<BaseRecord> records) {
        boolean key = records.get(0).isDecisionAttr();
        for(BaseRecord record : records) {
            if(record.isDecisionAttr() != key) {
                return false;
            }
        }
        return true;
    }
}
