package com.algorithm;

import com.common.TreeNode;
import com.data.BaseRecord;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Franco
 */
public class DescisionTree {

    AttrSelector attrSelector;

    public DescisionTree(AttrSelector attrSelector) {
        this.attrSelector = attrSelector;
    }

    public TreeNode create(List<BaseRecord> records, Set<Field> attrSet) {
        TreeNode node = new TreeNode();
        if(isAllInSameClass(records)) {
            node.setDecisionAttr(records.get(0).isDecisionAttr());
            return node;
        }

        if(attrSet.isEmpty()) {
            node.setDecisionAttr(getNodeAttr(records));
            return node;
        }
        Field best = attrSelector.select(records, attrSet);
        attrSet.remove(best);
        List<List<BaseRecord>> recordsList = splitRecords(records, best);
        List<TreeNode> childs = new ArrayList<>();
        for(List<BaseRecord> records1 : recordsList) {
            TreeNode node1 = create(records1, attrSet);
            node1.setField(best);
            try {
                best.setAccessible(true);
                node1.setValue(best.get(records1.get(0)));
            } catch (IllegalAccessException e) {
                System.out.println("method access exception.");
                e.printStackTrace();
            }
            childs.add(node1);
        }
        node.setTreeNodeList(childs);
        attrSet.add(best);
        return node;
    }

    public List<List<BaseRecord>> splitRecords(List<BaseRecord> records, Field best) {
        List<List<BaseRecord>> res = new ArrayList<>();
        for(BaseRecord baseRecord : records) {
            best.setAccessible(true);
            try {
                Object obj = best.get(baseRecord);
                boolean needCreate = true;
                for (List<BaseRecord> records1 : res) {
                    if (null != records1) {
                        Object obj1 = best.get(records1.get(0));
                        if(obj.equals(obj1)) {
                            records.add(baseRecord);
                        }
                    }
                }
                if(needCreate) {
                    List<BaseRecord> records1 = new ArrayList<>();
                    records1.add(baseRecord);
                    res.add(records1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("method access exception.");
            }
        }
        return res;
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
